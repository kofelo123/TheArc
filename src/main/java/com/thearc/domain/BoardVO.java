package com.thearc.domain;

import java.util.Arrays;
import java.util.Date;

import javax.validation.constraints.Size;

public class BoardVO {
	
	private Integer bno;
	
	@Size(min=1, max=35)
	private String title;
	
	private String content;
	private String writer;
	private Date regdate;
	private int viewcnt;
	private int replycnt;
	private int countlike;
	private String[] files;
	private String category;///다중게시판을 위해 만들었는데. 기능적으로 VO에 넣을 필요는 없어보인다 but view에서 식별(제목등)할떄 필요할 수 있을것으로보여서 만듬.
	
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public int getCountlike() {
		return countlike;
	}
	public void setCountlike(int countlike) {
		this.countlike = countlike;
	}
	
		
	public String[] getFiles() {
		return files;
	}
	public void setFiles(String[] files) {
		this.files = files;
	}
	public int getReplycnt() {
		return replycnt;
	}
	public void setReplycnt(int replycnt) {
		this.replycnt = replycnt;
	}
	public Integer getBno() {
		return bno;
	}
	public void setBno(Integer bno) {
		this.bno = bno;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public int getViewcnt() {
		return viewcnt;
	}
	public void setViewcnt(int viewcnt) {
		this.viewcnt = viewcnt;
	}
	@Override
	public String toString() {
		return "BoardVO [bno=" + bno + ", title=" + title + ", content=" + content + ", writer=" + writer + ", regdate="
				+ regdate + ", viewcnt=" + viewcnt + ", replycnt=" + replycnt + ", countlike=" + countlike + ", files="
				+ Arrays.toString(files) + ", category=" + category + "]";
	}
	
	
}
