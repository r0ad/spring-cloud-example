package io.rainforest.banana.client.service.demo2;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.stereotype.Service;

@Service
public class TestService {

    /**
     * @SentinelResource 注解用来标识资源是否被限流、降级。上述例子上该注解的属性 sayHello 表示资源名。
     * @param name
     * @return
     */
    @SentinelResource(value = "sayHello")
    public String sayHello(String name) {
        return "Hello, " + name;
    }
}