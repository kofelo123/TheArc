package com.thearc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mobile.device.Device;
import org.springframework.mobile.device.DeviceUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	
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
