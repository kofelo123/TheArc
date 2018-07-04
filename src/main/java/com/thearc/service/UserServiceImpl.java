package com.thearc.service;

import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.springframework.mail.MailSender;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.thearc.domain.AddressVO;
import com.thearc.domain.LoginDTO;
import com.thearc.domain.UserVO;
import com.thearc.persistence.UserDAO;
import com.thearc.util.EncryptUtil;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.ImageHtmlEmail;
import org.apache.commons.mail.SimpleEmail;
import org.apache.commons.mail.resolver.DataSourceUrlResolver;

@Service
public class UserServiceImpl implements UserService {

	@Inject
	private UserDAO dao;

	@Resource(name = "ipAddress") // ip 가변적이라서 주입(local-server).
	private String ipAddress;
	
	@Inject
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
		// TODO Auto-generated method stub
		user.setUpw(encrypt.getSHA256(user.getUpw()));
		
		dao.joinPost(user);

	}

	@Override
	public UserVO id_checkPost(UserVO uid) throws Exception {
		// TODO Auto-generated method stub

		return dao.confirmId(uid);

	}

	@Override
	public List<AddressVO> findzipnum(AddressVO address) throws Exception {
		// TODO Auto-generated method stub
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
		
//		String pwUriEnc = EncryptUtil.getSHA256(user2.getUid());/// id받아온 값으로
		/// 해쉬화적용한다. id가
		/// pk라서 고유함
		
//		dao.encrypthash(pwUriEnc, userid);
		
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
/*		Email email = new SimpleEmail();
		
		email.setHostName("smtp.naver.com");
		email.setSmtpPort(587);
		email.setAuthenticator(new DefaultAuthenticator("hlw123", "ekflrktmaajfl"));
		email.setStartTLSRequired(true);
		email.setFrom("hlw123@naver.com"); 
		email.setSubject("TestMail");
		email.setMsg("This is a test mail ... :-)");
		email.addTo("kofelo123@dreamwiz.com") ;
		
		email.send();
*/	}
/*	@Override
	public void idfindmail(HttpServletRequest request, ModelMap mo, UserVO user) throws Exception {
		// TODO Auto-generated method stub
		// 메일 관련 정보
		String host = "smtp.naver.com";
		final String username = "hlw123"; // 네이버 이메일 주소중 @ naver.com앞주소만 기재합니다.
		final String password = "ekflrktmaajfl"; // 네이버 이메일 비밀번호를 기재합니다.
		String mailrecipient = user.getEmail3();// form으로 전달된 userVO의 email3을
		// 받아온다
		
		UserVO user2 = dao.idfindofmail(user); // form으로 전달된 이메일->db로 전달->id반환
		
		String pwUriEnc = EncryptUtil.getSHA256(user2.getUid());/// id받아온 값으로
		/// 해쉬화적용한다. id가
		/// pk라서 고유함
		System.out.println("sha256 암호화 해쉬코드 적용으로 인한 고유의값:" + pwUriEnc);
		
		String userid = user2.getUid();
		dao.encrypthash(pwUriEnc, userid);
		System.out.println(userid);
		System.out.println("허정원"+user2.getUid()); 
		
		int port = 465;
		
		// 메일 내용
		String recipient = mailrecipient; // 메일을 발송할 이메일 주소를 기재해 줍니다.
		String subject = "[디아크] 문의하신 계정 정보입니다.";
		String body = user2.getUname() + "님 반갑습니다." + user2.getUname() + "님의 아이디는:" + user2.getUid() + "입니다."
				+ "비밀번호 변경을 원하시면 아래 URI를 눌러주세요" + "http://" + ipAddress + ":8080/user/mailhashcheck?encrypthash="
				+ pwUriEnc + "&uid=" + userid;// 개행은 어떻게 해야할지?
		
		Properties props = System.getProperties();
		
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", port);
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.ssl.enable", "true");
		props.put("mail.smtp.ssl.trust", host);
		
		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			String un = username;
			String pw = password;
			
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(un, pw);
			}
		});
		session.setDebug(true); // for debug
		
		Message mimeMessage = new MimeMessage(session);
		mimeMessage.setFrom(new InternetAddress("hlw123@naver.com"));
		mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
		mimeMessage.setSubject(subject);
		mimeMessage.setText(body);
		Transport.send(mimeMessage);
		
	}
*/
	@Override
	public UserVO hashbyid(UserVO user) throws Exception {
		// TODO Auto-generated method stub
		return dao.hashbyid(user);
	}

	@Override
	public void modifypw(UserVO user) throws Exception {
		// TODO Auto-generated method stub
		user.setUpw(encrypt.getSHA256(user.getUpw()));
		dao.modifypw(user);
	}
}
