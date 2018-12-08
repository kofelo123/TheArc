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


}
