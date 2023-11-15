package io.rainforest.banana.gateway.sso.dto.base;

import lombok.Data;

@Data
public class Account {
    private Long userid;
    private String username;
    private String password;

}
