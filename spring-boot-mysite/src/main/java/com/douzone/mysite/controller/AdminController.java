package com.douzone.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.douzone.mysite.service.FileuploadService;
import com.douzone.mysite.service.SiteService;
import com.douzone.mysite.vo.SiteVo;
import com.douzone.security.Auth;
import com.douzone.security.Auth.Role;

@Auth(Role.ADMIN)
@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private FileuploadService fileuploadService;
	
	@Autowired
	private SiteService siteService;

	@RequestMapping({ "", "/main" })
	public String main(Model model, SiteVo siteVo) {
		siteVo = siteService.getSiteInformation();
		model.addAttribute("site", siteVo);
		return "admin/main";
	}

//	@RequestMapping(value="/main/update", method=RequestMethod.POST)
//	public String update(@ModelAttribute SiteVo siteVo, Model model) {
//		System.out.println(siteVo);
//		boolean result = siteService.updateSiteInformation(siteVo);
//		siteVo = siteService.getSiteInformation();
//		model.addAttribute("site", siteVo);
//		model.addAttribute("result", result);
//		return "admin/main";
//	}

	@RequestMapping("/main/update")
	public String updateSite(
		@ModelAttribute SiteVo siteVo, 
		@RequestParam(value="upload-profile") MultipartFile mutipartFile) {
		
		String profile = fileuploadService.restore(mutipartFile);
		System.out.println(profile);
		siteVo.setProfile(profile);
		
		boolean result = siteService.updateSiteInformation(siteVo);
		System.out.println("AdminController's update <result : " + result + ">");
		
		return "redirect:/admin/main";
	}
	
	@RequestMapping("/board")
	public String board() {
		return "admin/board";
	}
	
	@RequestMapping("/guestbook")
	public String guestbook() {
		return "admin/guestbook";
	}

	@RequestMapping("/user")
	public String user() {
		return "admin/user";
	}
	
}
