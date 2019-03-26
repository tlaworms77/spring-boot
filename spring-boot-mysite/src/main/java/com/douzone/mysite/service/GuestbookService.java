package com.douzone.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.mysite.repository.GuestbookDao;
import com.douzone.mysite.vo.GuestbookVo;

@Service
public class GuestbookService {
	
	@Autowired
	private GuestbookDao guestbookDao;
	
	public List<GuestbookVo> getMessageList() {
		return guestbookDao.getList();
	}
	
	public List<GuestbookVo> getMessageList( Long startNo ) {
		return guestbookDao.getList( startNo );
	}	
	
	public boolean deleteMessage( GuestbookVo vo ){
		return 1 == guestbookDao.delete( vo );
	}
	
	public boolean writeMessage( GuestbookVo vo ) {
		int count = guestbookDao.insert(vo);
		return count == 1;
	}

	public List<GuestbookVo> getAjaxList(int page) {
		return guestbookDao.getAjaxList(page);
	}

	public void insert(String name, String password, String message) {
		boolean result = 1 == guestbookDao.insert(name, password, message);
		System.out.println("ajaxGuestbook's insert result <" + result + ">");
	}

	public long getLastId() {
		return guestbookDao.getLastId();
	}

	public List<GuestbookVo> getLatelyAddList(int page, long lastId) {
		return guestbookDao.getLatelyList(page, lastId);
	}
	
}