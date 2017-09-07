package com.thearc.domain;

import java.util.Date;

public class MessageVO {

	private Integer mid;
	private String targetid;
	private String sender;
	private String message;
	private String title;
	private Date opendate;	
	private Date sendate; // db에 실수로 sendate라고 만들떄 만들었는데 다시 바꾸는거보다 VO고치는게 나을거같아서 바꿈
	private String readcheck;
	
	public String getReadcheck() {
		return readcheck;
	}
	public void setReadcheck(String readcheck) {
		this.readcheck = readcheck;
	}
	public String getTitle() {
		return title;
	}
	public Date getSendate() {
		return sendate;
	}
	public void setSendate(Date sendate) {
		this.sendate = sendate;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	
	public Integer getMid() {
		return mid;
	}
	public void setMid(Integer mid) {
		this.mid = mid;
	}
	public String getTargetid() {
		return targetid;
	}
	public void setTargetid(String targetid) {
		this.targetid = targetid;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Date getOpendate() {
		return opendate;
	}
	public void setOpendate(Date opendate) {
		this.opendate = opendate;
	}

	@Override
	public String toString() {
		return "MessageVO [mid=" + mid + ", targetid=" + targetid + ", sender="
				+ sender + ", message=" + message + ", opendate=" + opendate
				+ ", sendate=" + sendate + "]";
	}
}


 
