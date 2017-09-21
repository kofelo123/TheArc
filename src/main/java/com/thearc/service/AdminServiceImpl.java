package com.thearc.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.thearc.domain.UserVO;
import com.thearc.persistence.AdminDAO;
import com.thearc.util.EncryptUtil;

@Service
public class AdminServiceImpl implements AdminService {
	
	@Inject
	private EncryptUtil encrypt;
	
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
		user.setUpw(encrypt.getSHA256(user.getUpw()));
		return dao.adminlogin(user);
	}

	@Override
	public void authmodify(UserVO user) throws Exception {
		dao.authmodify(user);
	}

	@Override
	public void userDrop(UserVO user) throws Exception {
		dao.userDrop(user);
	}

}
