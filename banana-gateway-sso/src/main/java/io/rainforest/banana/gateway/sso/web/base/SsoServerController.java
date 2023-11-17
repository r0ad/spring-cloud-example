package io.rainforest.banana.gateway.sso.web.base;

import cn.dev33.satoken.sso.SaSsoProcessor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Sa-Token-SSO Server端 Controller 
 */
@RestController
public class SsoServerController {



    /*
     * SSO-Server端：处理所有SSO相关请求
     * 开放接口api说明：https://sa-token.cc/doc.html#/sso/sso-apidoc
     * 或者查看类： cn.dev33.satoken.sso.name.ApiName
     */
    @RequestMapping("/sso/*")
    public Object ssoRequest() {
        return SaSsoProcessor.instance.serverDister();
    }
    

    
}
