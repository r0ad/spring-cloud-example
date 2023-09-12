package io.rainforest.banana.client3.web.demo;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "client1",value = "client1",contextId = "client1",path = "/client1")
public interface Client1 {
    @RequestMapping(method = RequestMethod.GET,value = "/hello2")
    List<String> hello2(@RequestParam("hello") String hello);

    @GetMapping(value = "/")
    String hello(@RequestParam("hello") String hello);

}

