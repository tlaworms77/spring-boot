package com.douzone.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller //POJO
public class HelloController {
	
	/*@ResponseBody //JSON 응답시 이용.
*/	@RequestMapping("/hello")
	public String hello() {
		return "hello";
	}
}
