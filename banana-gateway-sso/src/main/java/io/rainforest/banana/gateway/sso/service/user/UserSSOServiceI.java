package io.rainforest.banana.gateway.sso.service.user;

import io.rainforest.banana.gateway.sso.dto.base.Account;

import java.util.List;

/**
 * 用户信息获取
 */
public interface UserSSOServiceI {
    /**
     * 查询账户匹配信息
     * @param username
     * @param password
     * @return
     */
    Account getAccount(String username, String password);

    /**
     * 查询账户权限信息
     * @param username
     * @return
     */
    List<String> getPermissions(String username);

    /**
     * 查询账户角色信息
     * @param username
     * @return
     */
    List<String> getRoles(String username);
    /**
     * 查询账户权限信息ByLoginId
     * @param loginId
     * @return
     */
    List<String> getPermissionsByLoginId(String loginId);

    /**
     * 查询账户角色信息ByLoginId
     * @param loginId
     * @return
     */
    List<String> getRolesByLoginId(String loginId);

    /**
     * 获取用户信息
     * @param loginId
     * @return
     */
    Account getUserInfo(String loginId);
}
