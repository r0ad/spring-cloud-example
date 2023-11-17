package io.rainforest.banana.gateway.sso.web.user;

import cn.dev33.satoken.annotation.SaCheckRole;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试注解权限校验
 * 实际测试中基于注解的权限并未生效。基于filter的权限拦截生效了。
 */
@RestController
@RequestMapping("/demoUser")
public class UserDemoController {
    // 查询登录状态  ---- http://localhost:10105/user/isLogin
    @GetMapping("isLogin")
    @SaCheckRole("user")
    public SaResult isLogin() {
        return SaResult.data(StpUtil.isLogin());
    }

    // 查询 Token 信息  ---- http://localhost:10105/user/tokenInfo
    @GetMapping("tokenInfo")
    @SaCheckRole("user")
    public SaResult tokenInfo() {
        return SaResult.data(StpUtil.getTokenInfo());
    }

}
