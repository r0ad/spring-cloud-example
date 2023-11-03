package io.rainforest.banana.client.service.demo2.impl;

import io.rainforest.banana.client.service.demo2.EchoService;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

@Component
public class EchoServiceFallback implements EchoService {
    @Override
    public String echo(@PathVariable("str") String str) {
        return "echo fallback";
    }
}