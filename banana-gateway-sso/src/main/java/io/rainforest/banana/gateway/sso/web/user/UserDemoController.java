package io.rainforest.banana.gateway.sso.web.user;

import cn.dev33.satoken.annotation.SaCheckRole;
import cn.dev33.satoken.annotation.SaMode;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import io.rainforest.banana.gateway.sso.dto.base.Account;
import io.rainforest.banana.gateway.sso.service.user.UserSSOServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/userDemo")
@SaCheckRole(value = {"admin", "user"},mode = SaMode.OR)
public class UserDemoController {
    // 查询登录状态  ---- http://localhost:10105/user/isLogin
    @GetMapping("isLogin")
    @SaCheckRole(value = {"admin", "user"},mode = SaMode.OR)
    public SaResult isLogin() {
        return SaResult.data(StpUtil.isLogin());
    }

    // 查询 Token 信息  ---- http://localhost:10105/user/tokenInfo
    @GetMapping("tokenInfo")
    @SaCheckRole(value = {"admin", "user"},mode = SaMode.OR)
    public SaResult tokenInfo() {
        return SaResult.data(StpUtil.getTokenInfo());
    }

}
