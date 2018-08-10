package com.thearc.domain;

public class LikeVO {

	private int bno;
	private String uid;
	private String likecheck;

	public int getBno() {return bno;}
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
	public String toString() {
		return "LikeVO [bno=" + bno + ", uid=" + uid + ", likecheck="
				+ likecheck
					+ "]";
	}
	
}
