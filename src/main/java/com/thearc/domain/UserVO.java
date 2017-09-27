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
	private String email4;
	

	private String roadAddrPart1;
	private String addrDetail;
	private String addr1;
	private String phone;
	private String phone2;
	private String phone3;	
	private String phone4;


	private Timestamp indate;
	private String encrypthash; // 해쉬코드값 저장용
	private String authority;
	
	public String getEmail4() {
		return email+"@"+email2;
	}

	public void setEmail4(String email4) {
		this.email4 = email4;
	}
	
	public String getAddr1() {
		return roadAddrPart1 +" "+ addrDetail;
	}

	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}

	
	public String getRoadAddrPart1() {
		return roadAddrPart1;
	}

	public void setRoadAddrPart1(String roadAddrPart1) {
		this.roadAddrPart1 = roadAddrPart1;
	}

	public String getAddrDetail() {
		return addrDetail;
	}

	public void setAddrDetail(String addrDetail) {
		this.addrDetail = addrDetail;
	}

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
		return email;
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
	
	public String getPhone() {
		return phone;
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
	
	public String getPhone4() {
		return phone+"-"+phone2+"-"+phone3;
	}

	public void setPhone4(String phone4) {
		this.phone4 = phone4;
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
				+ ", email2=" + email2 + ", email3=" + email3 + ", roadAddrPart1=" + roadAddrPart1 + ", addrDetail="
				+ addrDetail + ", addr1=" + addr1 + ", phone=" + phone + ", phone2=" + phone2 + ", phone3=" + phone3
				+ ", indate=" + indate + ", encrypthash=" + encrypthash + ", authority=" + authority + "]";
	}
	
}
