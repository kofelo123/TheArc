package com.thearc.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.thearc.domain.UserVO;
import com.thearc.persistence.AdminDAO;

@Service
public class AdminServiceImpl implements AdminService {
	
	@Inject
	private AdminDAO dao;

	@Override
	public List<UserVO> listuser() throws Exception {
		// TODO Auto-generated method stub
		return dao.listuser();
	}

	@Override
	public UserVO adminlogin(UserVO user) throws Exception {
		// TODO Auto-generated method stub
		return dao.adminlogin(user);
	}

}
