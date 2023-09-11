package io.rainforest.banana.client1.web.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class HelloWorld {
	@GetMapping("/")
	public String hello(String hello){
		return hello+" world";
	}

	@GetMapping("/hello2")
	public List<String> hello2(String hello){
		List<String> res = new ArrayList<>();
		res.add("test1");
		res.add("test2");
		res.add(hello);
		return res;
	}
}
