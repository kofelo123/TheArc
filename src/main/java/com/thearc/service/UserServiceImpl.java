package com.thearc.service;


import com.thearc.domain.LoginDTO;
import com.thearc.domain.UserVO;
import com.thearc.mapper.UserMapper;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.ImageHtmlEmail;
import org.apache.commons.mail.resolver.DataSourceUrlResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.net.URL;
import java.util.Date;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper mapper;

	@Resource(name = "ipAddress") // ip 가변적이라서 주입(local-server).
	private String ipAddress;
	
	//mail에 필요한 pw - 암호화처리함
	@Resource(name = "mailPw")
	private String mailPw;

/*	@Autowired
	private EncryptUtil encrypt;*/

	@Autowired
	private PasswordEncoder pwencoder;

	/*
	@Override
	public UserVO login(LoginDTO dto) throws Exception {
		if(dto.getUid() != "test_thearc") {
			dto.setUpw(pwencoder.encode(dto.getUpw()));
		}
		return mapper.login(dto);
	}

	@Override
	public void keepLogin(String uid, String sessionId, Date next) throws Exception {
		mapper.keepLogin(uid, sessionId, next);
	}
*/
	//(Deploy test용)
	@Override
	public UserVO testLogin(LoginDTO dto) throws Exception {

		return mapper.testLogin(dto);
	}

	@Override
	public UserVO checkLoginBefore(String value) {
		return mapper.checkUserWithSessionKey(value);
	}

	@Override
	public void joinPost(UserVO user) throws Exception {
//		user.setUpw(encrypt.getSHA256(user.getUpw()));

		System.out.println("joinPostTest:"+user.getUpw());
		user.setUpw(pwencoder.encode(user.getUpw()));
		System.out.println("joinPostTest:"+user.getUpw());
		mapper.joinPost(user);

		mapper.insertAuth(user);

	}

	@Override
	public UserVO id_checkPost(UserVO uid) throws Exception {

		return mapper.confirmId(uid);
	}

//	@Override
//	public List<AddressVO> findzipnum(AddressVO address) throws Exception {
//		System.out.println("리턴할 UserServiceImpl의 address" + dao.findzipnum(address));
//		return dao.findzipnum(address);
//	}

	@Override
	public void idfindmail(HttpServletRequest request, ModelMap mo, UserVO user) throws Exception {
				 
		 URL url = new URL("http://"+ipAddress+":8080");//이곳 연결안되면 building the MimeMessage failed 500에러 날수있다 주의해야함.
		 ImageHtmlEmail email = new ImageHtmlEmail();
		 email.setDataSourceResolver(new DataSourceUrlResolver(url));
		email.setHostName("smtp.naver.com");
		email.setSmtpPort(587);
		email.setAuthenticator(new DefaultAuthenticator("hlw123", mailPw));
		email.setStartTLSRequired(true);
		
		UserVO user2 = mapper.idfindofmail(user); // form으로 전달된 이메일->db로 전달->id반환 ///mail로 uid,upw,uname가져오는걸로 수정.
		
		String htmlEmailTemplate = "<a href=http://"+ipAddress+":8080/thearc/user/mailcheck?uid="+user2.getUid()+"&upw="+user2.getUpw()+"><img src=\"/thearc/resources/bootstrap/images/2-3.jpg\"></a><br><br>"
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

			System.out.println("hashbyidTest:"+user.getUpw());
			String pw=mapper.getPw(user.getUid());
			if(pwencoder.matches(user.getUpw(),pw)){
				System.out.println("matches-success");
				user.setUpw(pw);
			}

			System.out.println("hashbyidTest:"+user.getUpw());

		return mapper.hashbyid(user);
	}

	@Override
	public void modifypw(UserVO user) throws Exception {
		user.setUpw(pwencoder.encode(user.getUpw()));
		mapper.modifypw(user);
	}

	@Override
	public UserVO mailCheck(UserVO user) throws Exception {

		UserVO userVO = mapper.mailCheck(user);

		return userVO;
	}

	@Override
	public String unameCheck(String uname) throws Exception {



		return mapper.unameCheck(uname);
	}
}
