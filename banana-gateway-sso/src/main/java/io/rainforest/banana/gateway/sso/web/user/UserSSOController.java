package io.rainforest.banana.gateway.sso.web.user;

import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import io.rainforest.banana.gateway.sso.conifg.SSOConfig;
import io.rainforest.banana.gateway.sso.dto.base.Account;
import io.rainforest.banana.gateway.sso.service.user.UserSSOServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserSSOController {
    @Autowired
    private UserSSOServiceI userSSOServiceI;

    // 测试登录  ---- http://localhost:10105/user/doLogin?name=test&pwd=123456
    @GetMapping("login")
    public SaResult login(String name, String pwd) {
        if(StpUtil.isLogin()){
            StpUtil.logout();
        }
        // 此处仅作模拟示例，真实项目需要从数据库中查询数据进行比对
        Account account = userSSOServiceI.getAccount(name, pwd);
        // 此处仅做模拟登录，真实环境应该查询数据进行登录
        if(!ObjectUtils.isEmpty(account)){
            StpUtil.login(account.getUserid());
            return SaResult.ok("登录成功！").setData(StpUtil.getTokenValue());
        }
        return SaResult.error("登录失败！");
    }

    // 查询登录状态  ---- http://localhost:10105/user/isLogin
    @GetMapping("isLogin")
    public SaResult isLogin() {
        return SaResult.data(StpUtil.isLogin());
    }

    // 查询 Token 信息  ---- http://localhost:10105/user/tokenInfo
    @GetMapping("token")
    public SaResult token() {
        return SaResult.data(StpUtil.getTokenInfo());
    }

    // 测试注销  ---- http://localhost:10105/user/logout
    @GetMapping("logout")
    public SaResult logout() {
        StpUtil.logout();
        return SaResult.ok();
    }

    /**
     * 获取用户信息
     * @return
     */
    @GetMapping("userInfo")
    public SaResult userInfo() {

        String loginId = StpUtil.getLoginIdAsString();
        if (loginId == null) {
            return SaResult.error("未登录");
        }
        return SaResult.data(userSSOServiceI.getUserInfo((loginId)));
    }

    /**
     * 获取权限信息
     * @return
     */
    @GetMapping("role")
    public SaResult role() {

        return SaResult.data(StpUtil.getRoleList());
    }
    /**
     * 获取权限信息
     * @return
     */
    @GetMapping("permission")
    public SaResult permission() {
        return SaResult.data(StpUtil.getPermissionList());
    }
}
