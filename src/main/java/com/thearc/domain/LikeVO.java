package com.thearc.domain;

public class LikeVO {

	private int bno;
	private String uid;
	private String likecheck;
	
	
	public int getBno() {
		return bno;
	}
	public void setBno(int bno) {
		this.bno = bno;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getLikecheck() {
		return likecheck;
	}
	public void setLikecheck(String likecheck) {
		this.likecheck = likecheck;
	}
	@Override
	public String toString() { // tostring은 sysout에서 그냥 객체이름을 쓰면 이것이 적용되는것같다.
		return "LikeVO [bno=" + bno + ", uid=" + uid + ", likecheck="
				+ likecheck
					+ "]";
	}
	
}
