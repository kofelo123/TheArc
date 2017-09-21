package com.thearc.service;

import java.util.List;

import com.thearc.domain.UserVO;

public interface AdminService {
	
	public List<UserVO> listuser() throws Exception;

	public UserVO adminlogin(UserVO user)throws Exception;
	
	public void authmodify(UserVO user) throws Exception;
	
	public void userDrop(UserVO user) throws Exception;
}
