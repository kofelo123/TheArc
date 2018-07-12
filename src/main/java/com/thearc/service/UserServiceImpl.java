package com.thearc.service;

import java.net.URL;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.ImageHtmlEmail;
import org.apache.commons.mail.resolver.DataSourceUrlResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.thearc.domain.AddressVO;
import com.thearc.domain.LoginDTO;
import com.thearc.domain.UserVO;
import com.thearc.persistence.UserDAO;
import com.thearc.util.EncryptUtil;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO dao;

	@Resource(name = "ipAddress") // ip 가변적이라서 주입(local-server).
	private String ipAddress;
	
	@Autowired
	private EncryptUtil encrypt;

	@Override
	public UserVO login(LoginDTO dto) throws Exception {
		dto.setUpw(encrypt.getSHA256(dto.getUpw()));
		return dao.login(dto);
	}

	@Override
	public void keepLogin(String uid, String sessionId, Date next) throws Exception {

		dao.keepLogin(uid, sessionId, next);

	}

	@Override
	public UserVO checkLoginBefore(String value) {

		return dao.checkUserWithSessionKey(value);
	}

	@Override
	public void joinPost(UserVO user) throws Exception {
		user.setUpw(encrypt.getSHA256(user.getUpw()));
		
		dao.joinPost(user);

	}

	@Override
	public UserVO id_checkPost(UserVO uid) throws Exception {

		return dao.confirmId(uid);
	}

	@Override
	public List<AddressVO> findzipnum(AddressVO address) throws Exception {
		System.out.println("리턴할 UserServiceImpl의 address" + dao.findzipnum(address));
		return dao.findzipnum(address);
	}

	@Override
	public void idfindmail(HttpServletRequest request, ModelMap mo, UserVO user) throws Exception {
				 
		 URL url = new URL("http://"+ipAddress+":8080");//이곳 연결안되면 building the MimeMessage failed 500에러 날수있다 주의해야함.
		 ImageHtmlEmail email = new ImageHtmlEmail();
		 email.setDataSourceResolver(new DataSourceUrlResolver(url));
		email.setHostName("smtp.naver.com");
		email.setSmtpPort(587);
		email.setAuthenticator(new DefaultAuthenticator("hlw123", "ekflrktmaajfl"));
		email.setStartTLSRequired(true);
		
		UserVO user2 = dao.idfindofmail(user); // form으로 전달된 이메일->db로 전달->id반환 ///mail로 uid,upw,uname가져오는걸로 수정.
		
		String htmlEmailTemplate = "<a href=http://"+ipAddress+":8080/thearc/user/mailhashcheck?uid="+user2.getUid()+"&upw="+user2.getUpw()+"><img src=\"/thearc/resources/bootstrap/images/2-3.jpg\"></a><br><br>"
						+user2.getUname() +  " 님의 아이디는 " + user2.getUid() + " 입니다.<br/>"
						+" 위의 이미지링크를 클릭하시면 비밀번호를 변하실수 있습니다.";
						
		email.setFrom("hlw123@naver.com"); 
		email.setSubject("[디아크]"+user2.getUname()+"님 문의하신 계정 정보입니다.");
		email.setMsg("This is a test mail ... :-)");
		email.addTo(user.getEmail()) ;
		email.setCharset("utf-8");
		email.setHtmlMsg(htmlEmailTemplate);
		email.setTextMsg("Your email client does not support HTML messages");
		email.send();
	}
	@Override
	public UserVO hashbyid(UserVO user) throws Exception {
		return dao.hashbyid(user);
	}

	@Override
	public void modifypw(UserVO user) throws Exception {
		user.setUpw(encrypt.getSHA256(user.getUpw()));
		dao.modifypw(user);
	}
}
