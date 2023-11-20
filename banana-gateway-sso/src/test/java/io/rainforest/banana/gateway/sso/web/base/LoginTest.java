package io.rainforest.banana.gateway.sso.web.base;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest
@AutoConfigureMockMvc
public class LoginTest {

    @Test
    void testLoginSuccess(@Autowired WebTestClient webClient) {
        // 使用@Autowired注解获取WebTestClient对象，用于发送HTTP请求
        webClient
                .get().uri(url -> url.path("/user/login").queryParam("name", "test").queryParam("pwd", "123456").build())
                .accept(MediaType.APPLICATION_JSON)
                .exchange() // 发送GET请求并获取响应
                .expectStatus().isOk() // 断言响应状态码为200
                .expectBody().jsonPath("$.code").isEqualTo(200); // 断言响应体中的jsonPath("$.code")是否等于200
    }

    @Test
    void testLoginFailure(@Autowired WebTestClient webClient) {
        // 使用@Autowired注解获取WebTestClient对象，用于发送HTTP请求
        webClient
                .get().uri(url -> url.path("/user/login").queryParam("name", "test233").queryParam("pwd", "123456").build())
                .accept(MediaType.APPLICATION_JSON)
                .exchange() // 发送GET请求并获取响应
                .expectStatus().isOk() // 断言响应状态码为200
                .expectBody().jsonPath("$.code").isEqualTo(500); // 断言响应体中的jsonPath("$.code")是否等于200
    }

}
