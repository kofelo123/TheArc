package com.thearc.service;

import com.thearc.domain.LoginDTO;
import com.thearc.domain.UserVO;
import org.springframework.ui.ModelMap;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

public interface UserService {

	  public UserVO login(LoginDTO dto) throws Exception;

	  public void keepLogin(String uid, String sessionId, Date next)throws Exception;
	  
	  public UserVO checkLoginBefore(String value);  
	    
	  public void joinPost(UserVO user) throws Exception;
	  
	  public UserVO id_checkPost(UserVO uid) throws Exception;
	  
//	  public List<AddressVO> findzipnum(AddressVO address)throws Exception;



	  public void idfindmail(HttpServletRequest request, ModelMap mo,UserVO user)throws Exception;
	  
	  public UserVO hashbyid(UserVO user)throws Exception;
	  
	  public void modifypw(UserVO user)throws Exception;

	  public UserVO mailCheck(UserVO user)throws Exception;

	  public String unameCheck(String uname)throws Exception;
	
}
