package com.douzone.mysite.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.douzone.dto.JSONResult;
import com.douzone.mysite.service.GuestbookService;
import com.douzone.mysite.vo.GuestbookVo;

@Controller("guestbookAPIController")
@RequestMapping("/guestbook/api")
public class GuestbookController {
	@Autowired
	private GuestbookService guestbookService;


	@RequestMapping( "" )
	public String indexAjax() {
		return "guestbook/index-ajax";
	}
	
	@ResponseBody
	@RequestMapping("/ajax-list")
	public JSONResult getList(
			@RequestParam(value = "page", required = true, defaultValue = "0") int page) {
		
		List<GuestbookVo> list = guestbookService.getAjaxList(page);
		return JSONResult.success(list);
	}
	
	@ResponseBody
	@RequestMapping("/ajax-insert/{page}/{name}/{password}/{message}")
	public JSONResult insert(
			@PathVariable int page, 
			@PathVariable String name, 
			@PathVariable String password,
			@PathVariable String message) {
		
		System.out.println("page:" + page + " name:" + name + " pw:" + password + " msg:" + message);
		
		guestbookService.insert(name, password, message);
		long lastId = guestbookService.getLastId();
		
		System.out.println(lastId);
		List<GuestbookVo> list = guestbookService.getLatelyAddList(page, lastId);
		System.out.println("list : " + list);
		for(GuestbookVo vo : list)
			System.out.println(vo);
		return JSONResult.success(list);
	}
	
//
//	@ResponseBody
//	@RequestMapping("/ajax-delete")
//	public JSONResult delete(
//			@RequestParam(value = "page", required = true, defaultValue = "1") int page,
//			@RequestParam(value = "password", required = true, defaultValue = "") String password,
//			@RequestParam(value = "no", required = true, defaultValue = "") int no) {
//		GuestbookVo vo = new GuestbookVo();
//
//		vo.setPassword(password);
//		vo.setNo(no);
//
//		int count = guestbookService.delete(vo);
//		boolean result = false;
//		System.out.println(count);
//
//		if (count == 1) {
//			result = true;
//		}
//
//		return JSONResult.success(result);
//	}
//

}