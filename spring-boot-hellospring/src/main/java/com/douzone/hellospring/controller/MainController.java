package com.douzone.hellospring.controller;

import java.io.Writer;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {
	@ResponseBody
	//request mapping : 메스드 단톡 매핑
	@RequestMapping({"/main", ""})
	public String main() {
		return "MainController:main()";
	}
	

	@ResponseBody
	//request mapping : 메스드 단톡 매핑
	@RequestMapping("/main/a/b/c/d")
	public String main3(HttpServletRequest request, Writer out) {
		return "MainController:main3()";
	}
}
