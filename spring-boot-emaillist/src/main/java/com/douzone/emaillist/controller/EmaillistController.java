package com.douzone.emaillist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.douzone.emaillist.dao.EmaillistDao;
import com.douzone.emaillist.vo.EmaillistVo;

@Controller
public class EmaillistController {
	
	@Autowired // 이 어노테이션으로 인한 주입을 시켜 개발자가 생성하지않고 사용가능
	private EmaillistDao emaillistDao; // 여기서 비즈니스 객체이다.
	
	/*@RequestMapping("")
	public ModelAndView list() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("list", emaillistDao.getList());
		mav.setViewName("/WEB-INF/views/list.jsp");
		return mav;
	}*/
	
	// 추천하는 방식 String return 방식
	@RequestMapping({"", "/list"})
	public String list(Model model) {
		model.addAttribute("list", emaillistDao.getList());
		return "list";
	}
	
	@RequestMapping("/form")
	public String form() {
		return "form";
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String add(EmaillistVo emaillistVo) {
		emaillistDao.insert(emaillistVo);
		return "redirect:/";
	}
}
