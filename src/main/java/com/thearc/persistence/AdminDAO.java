package com.thearc.persistence;

import java.util.List;

import com.thearc.domain.UserVO;

public interface AdminDAO {

	public List<UserVO> listuser()throws Exception;
	
	public UserVO adminlogin(UserVO user) throws Exception;
	
	public void authmodify(UserVO user) throws Exception;

	public void userDrop(UserVO user) throws Exception;
	
}
