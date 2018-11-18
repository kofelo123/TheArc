package com.thearc.persistence;

import com.thearc.domain.LoginDTO;
import com.thearc.domain.UserVO;

import java.util.Date;

public interface UserDAO {

	public UserVO login(LoginDTO dto) throws Exception;

	public void keepLogin(String uid, String sessionId, Date next);

	public UserVO checkUserWithSessionKey(String value);

	public void joinPost(UserVO user);

	public UserVO confirmId(UserVO uid);

//	public List<AddressVO> findzipnum(AddressVO address);

	// 아아디 비밀번호 찾기 로직관련
	public UserVO idfindofmail(UserVO user);

	public UserVO hashbyid(UserVO user);

	public void modifypw(UserVO user);

}
