package com.thearc.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.Device;
import org.springframework.mobile.device.DeviceUtils;
import org.springframework.social.connect.Connection;
import org.springframework.social.google.api.Google;
import org.springframework.social.google.api.impl.GoogleTemplate;
import org.springframework.social.google.api.plus.Person;
import org.springframework.social.google.api.plus.PlusOperations;
import org.springframework.social.google.connect.GoogleConnectionFactory;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.GrantType;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Locale;

@Slf4j
@Controller
public class HomeController {
	
	@GetMapping("/")
	public String home(HttpServletRequest request,HttpServletResponse response,Locale locale, Model model) throws Exception {
	
		Device device = DeviceUtils.getCurrentDevice(request);
   
		if(device.isMobile()){
			response.sendRedirect("/thearc/sboard/main");
		}
		return "home";
	}

	@GetMapping("/error400")
	public String error400(){
		return "error/error400";
	}

	@GetMapping("/error403")
	public String error403() {
		
		return "error/error403";
	}
	
	@GetMapping("/error404")
	public String error404() {
		
		return "error/error404";
	}
	
	@GetMapping("/error405")
	public String error405() {
		return "error/error405";
	}
	
	@GetMapping("/error500")
	public String error500() {
		
		return "error/error500";
	}
	@GetMapping("/ban")
	public String ban() {
		
		return "error/ban";
	}
	@GetMapping("/music")
	public void muiscPlayer() {
		
	}

	//------------------------------------- 소셜 로그인      ----------------------------------------

	@Autowired
	private GoogleConnectionFactory googleConnectionFactory;
	@Autowired
	private OAuth2Parameters googleOAuth2Parameters;

	private OAuth2Operations oauthOperations;

	// 회원 가입 페이지
	@RequestMapping(value = "/join", method = { RequestMethod.GET, RequestMethod.POST })
	public String join(HttpServletResponse response, Model model) {

		oauthOperations = googleConnectionFactory.getOAuthOperations();
		String url = oauthOperations.buildAuthenticateUrl(GrantType.AUTHORIZATION_CODE, googleOAuth2Parameters);
		System.out.println("/googleLogin, url : " + url);
		model.addAttribute("google_url", url);

		return "/join";
	}


	// ------------------------------------ 구글 콜백 ----------------------------------------

	@RequestMapping(value = "/googleSignInCallback", method = { RequestMethod.GET, RequestMethod.POST })
	public String doSessionAssignActionPage(HttpServletRequest request) throws Exception {

		String code = request.getParameter("code");

		oauthOperations = googleConnectionFactory.getOAuthOperations();
		AccessGrant accessGrant = oauthOperations.exchangeForAccess(code, googleOAuth2Parameters.getRedirectUri(),
				null);

		String accessToken = accessGrant.getAccessToken();
		Long expireTime = accessGrant.getExpireTime();

		if (expireTime != null && expireTime < System.currentTimeMillis()) {
			accessToken = accessGrant.getRefreshToken();
			System.out.printf("accessToken is expired. refresh token = {}", accessToken);

		}

		Connection<Google> connection = googleConnectionFactory.createConnection(accessGrant);
		Google google = connection == null ? new GoogleTemplate(accessToken) : connection.getApi();
		System.out.println(connection);

		PlusOperations plusOperations = google.plusOperations();
		Person profile = plusOperations.getGoogleProfile();
		System.out.println("User Uid : " + profile.getId());
		System.out.println("User Name : " + profile.getDisplayName());
		System.out.println("User Email : " + profile.getAccountEmail());
		System.out.println("User Profile : " + profile.getImageUrl());

		// Access Token 취소
		try {
			System.out.println("Closing Token....");
			String revokeUrl = "https://accounts.google.com/o/oauth2/revoke?token=" + accessToken + "";
			URL url = new URL(revokeUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setDoOutput(true);

			BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
		} catch (Exception e) {

			e.printStackTrace();
		}
		return "redirect:/sboard/main";

	}

///NVAER

	@GetMapping("naverlogin")
	@PostMapping("naverlogin")
	public String naverLogin(){
		return "/user/naverlogin";
	}

	@GetMapping("/user/naverLoginCallBack")
	public String naverLoginCallBack(){
		return "/user/callback";
	}



}
