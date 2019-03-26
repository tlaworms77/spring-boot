package com.douzone.mysite.vo;

public class GalleryVo {
	private long no;
	private String comment;
	private String imageUrl;

	public long getNo() {
		return no;
	}

	public void setNo(long no) {
		this.no = no;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	@Override
	public String toString() {
		return "GalleryVo [no=" + no + ", comment=" + comment + ", imageUrl=" + imageUrl + "]";
	}

}
