package io.rainforest.banana.client3.web.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HelloWorld {
	@Autowired
	private StoreClient storeClient;

	@GetMapping("/")
	public String hello(String hello){
		return hello+" world";
	}

	@GetMapping("/hello2")
	public List<String> hello2(String hello){
		return storeClient.hello2(hello);
	}
}
