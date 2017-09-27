package com.thearc.service;

import java.util.List;

import com.thearc.domain.BoardVO;
import com.thearc.domain.UserVO;

public interface AdminService {
	
	public List<UserVO> listuser() throws Exception;

	public UserVO adminlogin(UserVO user)throws Exception;
	
	public void authmodify(UserVO user) throws Exception;
	
	public void userDrop(UserVO user) throws Exception;
	
	public List<String> dayBoard() throws Exception;
	
	public List<String> dayReply() throws Exception;
	
	public List<String> cateBoardview() throws Exception;

	public List<String> weekcateBoard() throws Exception;

	public List<String> weekReplyCount() throws Exception;
	
	public void boardDrop(BoardVO board) throws Exception;
}
