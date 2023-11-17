package io.rainforest.banana.gateway.sso.conifg;

import cn.dev33.satoken.stp.StpInterface;
import io.rainforest.banana.gateway.sso.service.user.UserSSOServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 自定义权限加载接口实现类
 * 保证此类被 SpringBoot 扫描，完成 Sa-Token 的自定义权限验证扩展
 */
@Component
public class StpInterfaceImpl implements StpInterface {
    @Autowired
    private UserSSOServiceI userSSOServiceI;

    /**
     * 返回一个账号所拥有的权限码集合 
     */
    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        // 本 list 仅做模拟，实际项目中要根据具体业务逻辑来查询权限
        return userSSOServiceI.getPermissionsByLoginId(loginId);
    }

    /**
     * 返回一个账号所拥有的角色标识集合 (权限与角色可分开校验)
     */
    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        // 本 list 仅做模拟，实际项目中要根据具体业务逻辑来查询角色
        return userSSOServiceI.getRolesByLoginId(loginId);
    }

}
