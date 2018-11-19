package com.thearc.service;

import com.thearc.domain.BoardVO;
import com.thearc.domain.UserVO;

import java.util.List;
import java.util.Map;

public interface AdminService {
	
	public List<Map<String,String>> listuser() throws Exception;

//	public UserVO adminlogin(UserVO user)throws Exception;
	
	public void authmodify(Map uidAuthority) throws Exception;
	
	public void userDrop(UserVO user) throws Exception;
	
	public List<String> dayBoard() throws Exception;
	
	public List<String> dayReply() throws Exception;
	
	public List<String> cateBoardview() throws Exception;

	public List<String> weekcateBoard() throws Exception;

	public List<String> weekReplyCount() throws Exception;
	
	public void boardDrop(BoardVO board) throws Exception;
}
