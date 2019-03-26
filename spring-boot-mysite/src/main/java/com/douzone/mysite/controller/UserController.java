package com.douzone.mysite.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.douzone.mysite.service.UserService;
import com.douzone.mysite.vo.UserVo;
import com.douzone.security.Auth;
import com.douzone.security.AuthUser;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/auth", method = RequestMethod.POST)
	public void auth() {
		System.out.println("auth");
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public void logout() {}
	
	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String join(@ModelAttribute UserVo userVo) {
		return "user/join";
	}
	
	// @ModelAttribute UserVo userVo 설정필요!! get에 역시 form tag 에서 없다고 에러를 발생할 수 있다.
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String join(@ModelAttribute @Valid UserVo userVo, BindingResult result, Model model) {
		// @ModelAttribute 를 적용하면 해당 모델이 자동으로 jsp단으로 보내주기 떄문에 userVo를 안 넘겨줘도 넘어가게되있다.
		// @Valid가 되어 있다면 ux를 위해서라도 @ModelAttribute를 하여 적었던 값을 그대로 다시 고정시킬 수 있도록 모델을 넘겨주는게 낳다.
		if (result.hasErrors()) {
//			List<ObjectError> list = result.getAllErrors();
//			for(ObjectError error: list) {
//				System.out.println(error);
//			}

			model.addAllAttributes(result.getModel());

			return "/user/join";
		}

		userService.join(userVo);
		return "redirect:/user/joinsuccess";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "user/login";
	}

	@RequestMapping("/joinsuccess")
	public String joinsuccess() {
		return "user/joinsuccess";
	}

	@Auth(Auth.Role.USER)
	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public String modify(@AuthUser UserVo authUser, Model model) {
		System.out.println(authUser);

		UserVo userVo = userService.getUser(authUser.getNo());
		model.addAttribute("userVo", userVo);
		return "user/modify";
	}

	@Auth
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modify(@AuthUser UserVo authUser, @ModelAttribute UserVo userVo) {

		userVo.setNo(authUser.getNo());
		userService.modifyUser(userVo);

		// session의 authUser 변경
		authUser.setName(userVo.getName());

		return "redirect:/user/modify?result=success";
	}
}
