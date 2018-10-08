package com.thearc.domain;

import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class LoginDTO {
	
	@Size(min=1, max=20)
	private String uid;
	
	@Size(min=1, max=50)
	private String upw;
	
	private boolean useCookie;
	
	private String authority;
	
/*	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getUpw() {
		return upw;
	}
	public void setUpw(String upw) {
		this.upw = upw;
	}
	public boolean isUseCookie() {
		return useCookie;
	}
	public void setUseCookie(boolean useCookie) {
		this.useCookie = useCookie;
	}
	@Override
	public String toString() {
		return "LoginDTO [uid=" + uid + ", upw=" + upw + ", useCookie=" + useCookie + ", authority=" + authority + "]";
	}*/
	
	
	
	
}
