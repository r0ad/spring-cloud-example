package io.rainforest.banana.client.web.demo2;

import io.rainforest.banana.client.service.demo2.EchoService;
import io.rainforest.banana.client.service.demo2.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private TestService service;
    @Autowired
    private EchoService service2;

    @GetMapping(value = "/hello/{name}")
    public String apiHello(@PathVariable String name) {
        return service.sayHello(name);
    }

    @GetMapping(value = "/hello2")
    public String hello2(String name) {
        return service2.echo(name);
    }
}