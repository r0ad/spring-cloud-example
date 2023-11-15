package io.rainforest.banana.gateway.sso.web.base;

import cn.dev33.satoken.config.SaSsoConfig;
import cn.dev33.satoken.sso.SaSsoProcessor;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import com.dtflys.forest.Forest;
import io.rainforest.banana.gateway.sso.conifg.SSOConfig;
import io.rainforest.banana.gateway.sso.dto.base.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Sa-Token-SSO Server端 Controller 
 */
@RestController
public class SsoServerController {

    @Autowired
    private SSOConfig ssoConfig;

    /*
     * SSO-Server端：处理所有SSO相关请求 (下面的章节我们会详细列出开放的接口) 
     */
    @RequestMapping("/sso/*")
    public Object ssoRequest() {
        return SaSsoProcessor.instance.serverDister();
    }
    
    /**
     * 配置SSO相关参数 
     */
    @Autowired
    private void configSso(SaSsoConfig sso) {
        // 配置：未登录时返回的View 
        sso.setNotLoginView(() -> {
            String msg = "当前会话在SSO-Server端尚未登录，请先访问"
                    + "<a href='/sso/doLogin?name=sa&pwd=123456' target='_blank'> doLogin登录 </a>"
                    + "进行登录之后，刷新页面开始授权";
            return msg;
        });
        
        // 配置：登录处理函数
        List<Account> accounts = ssoConfig.getAccount();
        sso.setDoLoginHandle((name, pwd) -> {
            // 此处仅做模拟登录，真实环境应该查询数据进行登录
            for (Account account:accounts){
                if("sa".equals(account.getUsername()) && "123456".equals(account.getPassword())) {
                    StpUtil.login(account.getUserid());
                    return SaResult.ok("登录成功！").setData(StpUtil.getTokenValue());
                }
            }
            return SaResult.error("登录失败！");
        });
        
        // 配置 Http 请求处理器 （在模式三的单点注销功能下用到，如不需要可以注释掉） 
        sso.setSendHttp(url -> {
            try {
                // 发起 http 请求 
                System.out.println("------ 发起请求：" + url);
                return Forest.get(url).executeAsString();
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        });
    }
    
}
