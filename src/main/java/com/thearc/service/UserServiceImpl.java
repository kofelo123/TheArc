package com.thearc.service;

import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.inject.Inject;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.thearc.domain.AddressVO;
import com.thearc.domain.UserVO;
import com.thearc.domain.LoginDTO;
import com.thearc.util.EncryptUtil;
import com.thearc.persistence.UserDAO;

@Service
public class UserServiceImpl implements UserService{
	
	  @Inject
	  private UserDAO dao;

	  @Override
	  public UserVO login(LoginDTO dto) throws Exception {

	    return dao.login(dto);
	    
	  }
	  
	  @Override
	  public void keepLogin(String uid, String sessionId, Date next)
	      throws Exception {
	    
	    dao.keepLogin(uid, sessionId, next);
	    
	  }

	  @Override
	  public UserVO checkLoginBefore(String value) {
	    
	    return dao.checkUserWithSessionKey(value);
	  }

	  @Override
	  public void joinPost(UserVO user) throws Exception {
		// TODO Auto-generated method stub
		  
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
		return dao.findzipnum(address);
	}

	@Override
	public void idfindmail(HttpServletRequest request, ModelMap mo,UserVO user) throws Exception {
		// TODO Auto-generated method stub
		// 메일 관련 정보
	    String host = "smtp.naver.com";
	    final String username = "hlw123";       //네이버 이메일 주소중 @ naver.com앞주소만 기재합니다.
	    final String password = "ekflrktmaajfl";   //네이버 이메일 비밀번호를 기재합니다.
	    String mailrecipient= user.getEmail3();//form으로 전달된 userVO의 email3을 받아온다
	    
	    UserVO user2 = dao.idfindofmail(user); //form으로 전달된 이메일->db로 전달->id반환
	    
	    String pwUriEnc = EncryptUtil.getSHA256(user2.getUid());///id받아온 값으로 해쉬화적용한다. id가 pk라서 고유함
	    System.out.println("sha256 암호화 해쉬코드 적용으로 인한 고유의값:"+pwUriEnc);
	    
	    String userid=user2.getUid();
	    dao.encrypthash(pwUriEnc,userid);
	    System.out.println(userid);
	    /*System.out.println("허정원"+user2.getUid());*/
	    
	    
	    int port=465;
	    
	    // 메일 내용
	    String recipient = mailrecipient;    //메일을 발송할 이메일 주소를 기재해 줍니다.
	    String subject = "[디아크] 문의하신 계정 정보입니다.";
	    String body = user2.getUname()+"님 반갑습니다."+user2.getUname()+"님의 아이디는:"+user2.getUid()+"입니다."
	    		      +"비밀번호 변경을 원하시면 아래 URI를 눌러주세요"+"http://localhost:8080/user/mailhashcheck?encrypthash="+pwUriEnc+"&uid="+userid;//개행은 어떻게 해야할지?
	    
	    Properties props = System.getProperties();
	     
	     
	    props.put("mail.smtp.host", host);
	    props.put("mail.smtp.port", port);
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.ssl.enable", "true");
	    props.put("mail.smtp.ssl.trust", host);
	      
	    Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
	        String un=username;
	        String pw=password;
	        protected PasswordAuthentication getPasswordAuthentication() {
	            return new PasswordAuthentication(un, pw);
	        }
	    });
	    session.setDebug(true); //for debug
	      
	    Message mimeMessage = new MimeMessage(session);
	    mimeMessage.setFrom(new InternetAddress("hlw123@naver.com"));
	    mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
	    mimeMessage.setSubject(subject);
	    mimeMessage.setText(body);
	    Transport.send(mimeMessage);


	}

	@Override
	public UserVO hashbyid(UserVO user) throws Exception {
		// TODO Auto-generated method stub
		return dao.hashbyid(user);
	}

	@Override
	public void modifypw(UserVO user) throws Exception {
		// TODO Auto-generated method stub
		dao.modifypw(user);
	}
}
