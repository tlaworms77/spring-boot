package com.douzone.mysite.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.mysite.vo.GuestbookVo;

@Repository
public class GuestbookDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	public List<GuestbookVo> getList() {
		List<GuestbookVo> list = sqlSession.selectList( "guestbook.getList" );
		for(GuestbookVo vo : list) {
			System.out.println(vo);
		}
		return list;
	}
	
	public List<GuestbookVo> getList( Long startNo ) {
		List<GuestbookVo> list = sqlSession.selectList( "guestbook.getList2", startNo );
		System.out.println( list );
		return list;
	}	
	
	public int delete( GuestbookVo vo ) {
		int count = sqlSession.delete( "guestbook.delete", vo );
		return count;
	}
	
	public int insert( GuestbookVo vo ) {
		return sqlSession.insert( "guestbook.insert", vo );
	}

	public int insert(String name, String password, String message) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("name", name);
		map.put("password", password);
		map.put("message", message);
		return sqlSession.insert("guestbook.insert2", map);
	}	
	
	public List<GuestbookVo> getAjaxList(int page) {
		return sqlSession.selectList("guestbook.getAjaxList", page);
	}

	public long getLastId() {
		return sqlSession.selectOne("guestbook.getLastId");
	}

	public List<GuestbookVo> getLatelyList(int page, long lastId) {
		Map<String, Long> map = new HashMap<String, Long>();
		map.put("page", (long) page);
		map.put("lastId", lastId);
		return sqlSession.selectList("guestbook.getLatelyList", map);
	}

}
