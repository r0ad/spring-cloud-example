package io.rainforest.banana.gateway.sso.dto.base;

import lombok.Data;

import java.util.List;

@Data
public class Account {
    private String userid;
    private String username;
    private String password;
    private List<String> permissions;
    private List<String> roles;
}
