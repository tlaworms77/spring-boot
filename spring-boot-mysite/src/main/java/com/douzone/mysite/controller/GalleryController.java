package com.douzone.mysite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.douzone.mysite.service.FileuploadService;
import com.douzone.mysite.service.GalleryService;
import com.douzone.mysite.vo.GalleryVo;
import com.douzone.security.Auth;
import com.douzone.security.Auth.Role;

@Controller
@RequestMapping("/gallery")
public class GalleryController {
	
	@Autowired
	private FileuploadService fileuploadService;
	
	@Autowired
	private GalleryService galleryService;
	
	@RequestMapping("")
	public String index(Model model) {
		List<GalleryVo> list = galleryService.getGalleryList();
		model.addAttribute("list", list);
		return "gallery/index";
	}
	
	@Auth(Role.ADMIN)
	@RequestMapping("/upload")
	public String upload(
		@ModelAttribute GalleryVo galleryVo,
		@RequestParam(value="upload-image") MultipartFile mutipartFile) {
		
		// image serverHD save & get local image path logic 
		String imageUrl = fileuploadService.restore(mutipartFile);
		System.out.println(imageUrl);
		galleryVo.setImageUrl(imageUrl);
		
		System.out.println(galleryVo);
		
		// dao -> galleryvo insert logic
		boolean result = galleryService.uploadGalleryInformation(galleryVo);
		System.out.println("GalleryController's upload <result : " + result + ">");
		return "redirect:/gallery";
	}
	
	@Auth(Role.ADMIN)
	@RequestMapping(value="/delete/{no}", method=RequestMethod.GET)
	public String delete(@PathVariable(value="no") long no) {
		// dao -> no compare to delete logic
		System.out.println("11111");
		boolean result = galleryService.deleteGallery(no);
		System.out.println("GalleryController's delete <result : " + result + ">");
		return "redirect:/gallery";
	}
	
}
