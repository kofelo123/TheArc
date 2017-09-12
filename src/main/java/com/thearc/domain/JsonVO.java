package com.thearc.domain;

public class JsonVO { //객체 하나는 한타임 - 9시,12시,15시,18시
	
	private String time;//기상예보 타겟시간(9,12,15,18시 식별용)
	private String pop;//강수확률
	private String reh;//습도
	private String sky;// 하늘상태(SKY) 코드 : 맑음(1), 구름조금(2), 구름많음(3), 흐림(4)
	private String t3h;//3시간동안의 기온
	
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getPop() {
		return pop;
	}
	public void setPop(String pop) {
		this.pop = pop;
	}
	public String getReh() {
		return reh;
	}
	public void setReh(String reh) {
		this.reh = reh;
	}
	public String getSky() {
		return sky;
	}
	public void setSky(String sky) {
		this.sky = sky;
	}
	public String getT3h() {
		return t3h;
	}
	public void setT3h(String t3h) {
		this.t3h = t3h;
	}
	@Override
	public String toString() {
		return "JsonVO [time=" + time + ", pop=" + pop + ", reh=" + reh + ", sky=" + sky + ", t3h=" + t3h + "]";
	}
	
	
	
}
