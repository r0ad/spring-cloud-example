package io.rainforest.banana.gateway.sso.web.user;

import cn.dev33.satoken.annotation.SaCheckRole;
import cn.dev33.satoken.annotation.SaMode;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/userDemo")
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
