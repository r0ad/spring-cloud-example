package io.rainforest.banana.gateway.sso.conifg;

import cn.dev33.satoken.config.SaSsoConfig;
import cn.dev33.satoken.reactor.filter.SaReactorFilter;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import com.dtflys.forest.Forest;
import io.rainforest.banana.gateway.sso.dto.base.Account;
import io.rainforest.banana.gateway.sso.service.user.UserSSOServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ObjectUtils;

/**
 * [Sa-Token 权限认证] 全局配置类 
 */
@Configuration
public class SaTokenConfigure {
    @Autowired
    private UserSSOServiceI userSSOServiceI;
    /**
     * 注册 [Sa-Token全局过滤器] 
     */
    @Bean
    public SaReactorFilter getSaReactorFilter() {
        return new SaReactorFilter()
                // 指定 [拦截路由]
                .addInclude("/**")    /* 拦截所有path */
                // 指定 [放行路由]
                .addExclude("/favicon.ico")
                .addExclude("/user/**")
                // 指定[认证函数]: 每次请求执行 
                .setAuth(obj -> {
//                    System.out.println("---------- sa全局认证");
                    SaRouter.match("/**", () -> StpUtil.checkLogin());
                    // 根据路由划分模块，不同模块不同鉴权
                    // todo 修改为动态权限鉴权，角色权限和路径基于数据库配置
                    SaRouter.match("/admin/**", r -> StpUtil.checkPermission("admin"));
                    SaRouter.match("/goods/**", r -> StpUtil.checkPermission("goods"));
                })
                // 指定[异常处理函数]：每次[认证函数]发生异常时执行此函数 
                .setError(e -> {
//                    System.out.println("---------- sa全局异常 ");
                    return SaResult.error(e.getMessage());
                });
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
        sso.setDoLoginHandle((name, pwd) -> {
            Account account = userSSOServiceI.getAccount(name, pwd);
            // 此处仅做模拟登录，真实环境应该查询数据进行登录
            if(!ObjectUtils.isEmpty(account)){
                StpUtil.login(account.getUserid());
                return SaResult.ok("登录成功！").setData(StpUtil.getTokenValue());
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
