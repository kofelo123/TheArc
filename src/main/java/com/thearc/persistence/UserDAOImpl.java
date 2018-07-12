package com.thearc.persistence;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.thearc.domain.AddressVO;
import com.thearc.domain.LoginDTO;
import com.thearc.domain.UserVO;

@Repository
public class UserDAOImpl implements UserDAO {

	@Autowired
	private SqlSession session;

	private static String namespace = "com.thearc.mapper.userMapper";

	@Override
	public UserVO login(LoginDTO dto) throws Exception {

		return session.selectOne(namespace + ".login", dto);
	}

	@Override
	public void keepLogin(String uid, String sessionId, Date next) {

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("uid", uid);
		paramMap.put("sessionId", sessionId);
		paramMap.put("next", next);

		session.update(namespace + ".keepLogin", paramMap);

	}

	@Override
	public UserVO checkUserWithSessionKey(String value) {

		return session.selectOne(namespace + ".checkUserWithSessionKey", value);
	}

	@Override
	public void joinPost(UserVO user) {
		session.insert(namespace + ".join", user);

	}

	@Override
	public UserVO confirmId(UserVO uid) {
		return session.selectOne(namespace + ".confirmId", uid);
	}

	@Override
	public List<AddressVO> findzipnum(AddressVO address) {
		System.out.println("userDaoImpl 전달받은 AddressVO:"+address);
		System.out.println("userDaoImpl 리턴할 AddressVO:"+session.selectList(namespace + ".findzipnum", address));
		return session.selectList(namespace + ".findzipnum", address);
	}

	@Override
	public UserVO idfindofmail(UserVO user) {
		return session.selectOne(namespace + ".idfindofmail", user);
	}

	@Override
	public UserVO hashbyid(UserVO user) {
		return session.selectOne(namespace + ".hashbyid", user);
	}

	@Override
	public void modifypw(UserVO user) {
		session.update(namespace + ".modifypw", user);
	}
}
