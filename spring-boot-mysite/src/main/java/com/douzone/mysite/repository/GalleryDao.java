package com.douzone.mysite.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.mysite.vo.GalleryVo;

@Repository
public class GalleryDao {

	@Autowired
	private SqlSession sqlSession;
	
	public List<GalleryVo> getList() {
		return sqlSession.selectList("gallery.getList");
	}
	public int insert(GalleryVo galleryVo) {
		return sqlSession.insert("gallery.insertVo", galleryVo);
	}
	public int delete(long no) {
		return sqlSession.delete("gallery.deleteByNo", no);
	}
	
}
