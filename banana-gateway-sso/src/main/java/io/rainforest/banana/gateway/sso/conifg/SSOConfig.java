package io.rainforest.banana.gateway.sso.conifg;

import io.rainforest.banana.gateway.sso.dto.base.Account;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@ConfigurationProperties(prefix = "sso")
@Component
@Data
public class SSOConfig {
    private List<Account> account;
}

