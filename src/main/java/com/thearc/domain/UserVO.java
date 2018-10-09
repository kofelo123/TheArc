package com.thearc.domain;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Size;
import java.sql.Timestamp;

@Data
public class UserVO {
	
	@NotBlank
	@Size(min=5 , max=20)
	private String uid;
	
	@NotBlank
	@Size(min=5, max=50)
	private String upw;
			 
	@NotBlank
	@Size(min=2, max=20)
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
		/** 기존:가입할떄 매핑시 자동으로 @ 붙여서 되게끔 처리했더니 -> userlist같은곳에서 이메일을 뽑을떄 id@email@email 이런식의 문제가생겨서 수정*/
		if(email.contains("@"))
			return email;
		return email+"@"+email2;
	}

	// 현재 toString lombok으로 자동생성하면 에러난다(위의 getEmail의 리턴이 일반적인 getter와 다른 커스텀이라서 롬복의 자동생성 toString을 사용할시 자동생성된 getter를 사용해서 문제가생긴다)
	@Override
	public String toString() {
		return "UserVO [uid=" + uid + ", upw=" + upw + ", uname=" + uname +  ", email=" + email
				+ ", email2=" + email2 +", roadAddrPart1="
				+ roadAddrPart1 + ", addrDetail=" + addrDetail + ", addr1=" + addr1 + ", phone=" + phone + ", indate="
				+ indate + ", encrypthash=" + encrypthash + ", authority=" + authority + "]";
	}

/*	public void setEmail(String email) {
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
	}*/


/*
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
	//
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
	}*/
}
