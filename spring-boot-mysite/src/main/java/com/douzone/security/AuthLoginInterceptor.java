package com.douzone.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.douzone.mysite.service.UserService;
import com.douzone.mysite.vo.UserVo;

public class AuthLoginInterceptor extends HandlerInterceptorAdapter {
	
	@Autowired
	private UserService userService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		UserVo userVo = userService.getUser(email, password);
		if(userVo == null) {
			response.sendRedirect(request.getContextPath() + "/user/login?result=fail");
			return false;
		}
		
		// 로그인 처리
		HttpSession session = request.getSession(true);
		session.setAttribute("authUser", userVo);

		response.sendRedirect(request.getContextPath() + "/");
		return false;
	}
	
}
