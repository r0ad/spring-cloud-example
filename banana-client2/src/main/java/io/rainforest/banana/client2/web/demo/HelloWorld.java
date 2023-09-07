package io.rainforest.banana.client2.web.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorld {
	@GetMapping("/")
	public String hello(String hello){
		return hello+" world";
	}
}
