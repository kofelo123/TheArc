package com.thearc.domain;

import java.sql.Timestamp;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;


public class UserVO {
	
	
//	@Pattern(regexp="[[a-zA-Z]가-힣]{1,}", message="아이디를 입력해주세요.")
	@NotBlank(message ="아이디를 입력해야합니다.")
	@Size(min=5 , max=20 , message="5~20 글자로 입력")
	private String uid;
	
	@NotBlank(message ="비밀번호를 입력해야합니다.")
	@Size(min=5, max=50, message="5~50 글자로 입력")
	private String upw;
			 
	/*@Pattern(regexp="[a-zA-Z가-힣]{1,}", message="이름을 입력해주세요.")*/
	
	@NotBlank(message = "이름을 입력해야합니다.")
	@Size(min=2, max=20, message="5~20 글자로 입력")
	private String uname;
	

	private String email;// 아이디@메일주소 하는과정에서 email과 email2로 나뉘지만 email의 getter에 email+email2가 되어 병합되어 저장된다.
	private String email2;
	private String roadAddrPart1;
	private String addrDetail;
	private String addr1;
	private String phone;
	private Timestamp indate;
	private String encrypthash; // 해쉬코드값 저장용
	private String authority;
	

	
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
	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
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
		return "UserVO [uid=" + uid + ", upw=" + upw + ", uname=" + uname +  ", email=" + email
				+ ", email2=" + email2 +", roadAddrPart1="
				+ roadAddrPart1 + ", addrDetail=" + addrDetail + ", addr1=" + addr1 + ", phone=" + phone + ", indate="
				+ indate + ", encrypthash=" + encrypthash + ", authority=" + authority + "]";
	}
	
}
