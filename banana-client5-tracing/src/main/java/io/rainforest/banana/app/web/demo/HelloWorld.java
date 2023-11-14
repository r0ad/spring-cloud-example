package io.rainforest.banana.app.web.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
public class HelloWorld {
	@GetMapping("/")
	public String hello(String hello){
		log.info("this is a test");
		return hello+" world";
	}

	@GetMapping("/hello2")
	@ResponseBody
	public List<String> hello2(String hello){
		List<String> res = new ArrayList<>();
		res.add("test1");
		res.add("test2");
		res.add(hello);
		return res;
	}
}
