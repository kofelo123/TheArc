package com.thearc.domain;

import java.sql.Timestamp;

public class UserVO {

	private String uid;
	private String upw;
	private String uname;
	private int upoint;	
	private String email;// 아이디@메일주소 하는과정에서 email과 email2로 나뉘지만 email의 getter에 email+email2가 되어 병합되어 저장된다.
	private String email2;
	private String email3;// 위의 나뉘어지는 상황떄문에 id/pw 찾기 과정에서 이메일 전체값이 필요해서 그냥 만들어서 전달용.. db는 없다.
	private String zipNum;
	private String addr1;
	private String addr2;
	private String phone;
	private String phone2;
	private String phone3;	
	private Timestamp indate;
	private String encrypthash; // 해쉬코드값 저장용
	private String authority;
	
	
	public String getAuthority() {
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
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public int getUpoint() {
		return upoint;
	}
	public void setUpoint(int upoint) {
		this.upoint = upoint;
	}
	public String getEmail() {
		return email+"@"+email2;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEmail2() {
		return email2;
	}
	public void setEmail2(String email2) {
		this.email2 = email2;
	}
	public String getEmail3() {
		return email3;
	}
	public void setEmail3(String email3) {
		this.email3 = email3;
	}
	public String getZipNum() {
		return zipNum;
	}
	public void setZipNum(String zipNum) {
		this.zipNum = zipNum;
	}
	public String getAddr1() {
		return addr1;
	}
	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}
	public String getAddr2() {
		return addr2;
	}
	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}
	public String getPhone() {
		return phone+"-"+phone2+"-"+phone3;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPhone2() {
		return phone2;
	}
	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}
	public String getPhone3() {
		return phone3;
	}
	public void setPhone3(String phone3) {
		this.phone3 = phone3;
	}
	public Timestamp getIndate() {
		return indate;
	}
	public void setIndate(Timestamp indate) {
		this.indate = indate;
	}
	public String getEncrypthash() {
		return encrypthash;
	}
	public void setEncrypthash(String encrypthash) {
		this.encrypthash = encrypthash;
	}
	

	@Override
	public String toString() {
		return "UserVO [uid=" + uid + ", upw=" + upw + ", uname=" + uname + ", upoint=" + upoint + ", email=" + email
				+ ", email2=" + email2 + ", email3=" + email3 + ", zipNum=" + zipNum + ", addr1=" + addr1 + ", addr2="
				+ addr2 + ", phone=" + phone + ", phone2=" + phone2 + ", phone3=" + phone3 + ", indate=" + indate
				+ ", encrypthash=" + encrypthash + ", authority=" + authority + "]";
	}
}
