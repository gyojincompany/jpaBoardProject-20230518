package com.gyojincompany.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {
	
	@RequestMapping(value = "/testform")
	public String testform() {
		return "test";
	}
	
	@PostMapping(value = "/test")
	public String testpost() {
		
		System.out.println("post test 실행!");
		
		return "testpost";
	}
	
	@GetMapping(value = "/test")
	public String testget() {
		System.out.println("post get 실행!");
		return "testget";
	}

}
