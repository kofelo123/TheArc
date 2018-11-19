package com.thearc.service;

import com.thearc.domain.BoardVO;
import com.thearc.domain.UserVO;
import com.thearc.mapper.AdminMapper;
import com.thearc.util.EncryptUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class AdminServiceImpl implements AdminService {
	

	@Autowired
	private EncryptUtil encrypt;
	
	@Autowired
	private AdminMapper mapper;

	@Override
	public List<Map<String,String>> listuser() throws Exception {
		// TODO Auto-generated method stub
		return mapper.listuser();
	}

	@Override
	public UserVO adminlogin(UserVO user) throws Exception {
		// TODO Auto-generated method stub
		user.setUpw(encrypt.getSHA256(user.getUpw()));
		return mapper.adminlogin(user);
	}

	@Override
	public void authmodify(Map uidAuthority) throws Exception {

		if (uidAuthority.get("authority").equals("ROLE_MEMBER")) {
			uidAuthority.remove("authority"); /*("ROLE_SUPPORTER");*/
			uidAuthority.put("authority", "ROLE_MEMBER");
		} else if (uidAuthority.get("authority").equals("ROLE_SUPPORTER")) {
			uidAuthority.remove("authority");
			uidAuthority.put("authority", "ROLE_SUPPORTER");
		} else if (uidAuthority.get("authority").equals("ROLE_BAN")){
			uidAuthority.remove("authority");
			uidAuthority.put("authority","ROLE_BAN");
		}else{
		    uidAuthority.remove("authority");
		    uidAuthority.put("authority","ROLE_ADMIN");
		}

		mapper.authmodify(uidAuthority);
	}

	@Override
	public void userDrop(UserVO user) throws Exception {//fk때문에 자식테이블의 컬럼부터 지우고 부모테이블인 tbl_user 지워줘야한다.
		mapper.userDropCheck(user);
		mapper.userDropMessage(user);
		mapper.userDropUser(user);
	}

	@Override
	public List<String> dayBoard() throws Exception {
		return mapper.dayBoard();
	}

	@Override
	public List<String> dayReply() throws Exception {
		return mapper.dayReply();
	}

	@Override
	public List<String> cateBoardview() throws Exception {
		return mapper.cateBoardview();
	}

	@Override
	public List<String> weekcateBoard() throws Exception {
		return mapper.weekcateBoard();
	}

	@Override
	public List<String> weekReplyCount() throws Exception {
		return mapper.weekReplyCount();
	}

	@Override
	public void boardDrop(BoardVO board) throws Exception {
		mapper.boardDrop1(board);
		mapper.boardDrop2(board);
		mapper.boardDrop3(board);
	}

}
