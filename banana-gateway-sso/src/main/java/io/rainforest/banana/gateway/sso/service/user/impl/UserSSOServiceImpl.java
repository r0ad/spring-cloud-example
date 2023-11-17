package io.rainforest.banana.gateway.sso.service.user.impl;

import io.rainforest.banana.gateway.sso.conifg.SSOConfig;
import io.rainforest.banana.gateway.sso.dto.base.Account;
import io.rainforest.banana.gateway.sso.service.user.UserSSOServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class UserSSOServiceImpl implements UserSSOServiceI {
    @Autowired
    private SSOConfig ssoConfig;
    @Override
    public Account getAccount(String username, String password) {
        for (Account account:ssoConfig.getAccount()){
            if(Objects.equals(username,account.getUsername()) && Objects.equals(password,account.getPassword())) {
                return account;
            }
        }
        return null;
    }

    @Override
    public List<String> getPermissions(String username) {
        for (Account account:ssoConfig.getAccount()){
            if(Objects.equals(username,account.getUsername())) {
                return account.getPermissions();
            }
        }
        return new ArrayList<>();
    }

    @Override
    public List<String> getRoles(String username) {
        for (Account account:ssoConfig.getAccount()){
            if(Objects.equals(username,account.getUsername())) {
                return account.getRoles();
            }
        }
        return new ArrayList<>();
    }

    @Override
    public List<String> getPermissionsByLoginId(Object loginId) {
        for (Account account:ssoConfig.getAccount()){
            if(Objects.equals(loginId,account.getUserid())) {
                return account.getPermissions();
            }
        }
        return new ArrayList<>();
    }

    @Override
    public List<String> getRolesByLoginId(Object loginId) {
        for (Account account:ssoConfig.getAccount()){
            if(Objects.equals(loginId,account.getUserid())) {
                return account.getRoles();
            }
        }
        return new ArrayList<>();
    }

    @Override
    public Account getUserInfo(Long loginId) {
        for (Account account:ssoConfig.getAccount()){
            if(Objects.equals(loginId,account.getUserid())) {
                account.setPassword("这个看不到呢");
                return account;
            }
        }
        return null;
    }
}
