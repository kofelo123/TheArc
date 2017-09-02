package com.thearc.domain;

public class AddressVO {
	private String zip_Num;
	private String sido;
	private String gugun;
	private String dong;
	private String zipCode;
	private String bunji;

	public String getZipNum() {
		return zip_Num;
	}
	public void setZipNum(String zipNum) {
		this.zip_Num = zipNum;
	}
	public String getSido() {
		return sido;
	}


	public void setSido(String sido) {
		this.sido = sido;
	}

	public String getGugun() {
		return gugun;
	}

	public void setGugun(String gugun) {
		this.gugun = gugun;
	}

	public String getDong() {
		return dong;
	}

	public void setDong(String dong) {
		this.dong = dong;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getBunji() {
		return bunji;
	}

	public void setBunji(String bunji) {
		this.bunji = bunji;
	}
	@Override
	public String toString() {
		return "AddressVO [zip_Num=" + zip_Num + ", sido=" + sido + ", gugun=" + gugun + ", dong=" + dong + ", zipCode="
				+ zipCode + ", bunji=" + bunji + "]";
	}
}
