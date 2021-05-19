package com.example;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsersInfo {
	
	@RequestMapping("/hi")
	public String hi() {
		return "hi!";
	}

}
