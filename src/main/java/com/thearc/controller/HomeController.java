package com.thearc.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mobile.device.Device;
import org.springframework.mobile.device.DeviceUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 * @throws Exception 
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	  public String home(HttpServletRequest request,HttpServletResponse response,Locale locale, Model model) throws Exception {
		
		Device device = DeviceUtils.getCurrentDevice(request);
	   
		if(device.isMobile()){
			response.sendRedirect("/sboard/main");
		}
	    return "home";
	  }
	
	@RequestMapping(value = "/error403", method = RequestMethod.GET)
	public String error403() {
		
		return "error/error403";
	}
	@RequestMapping(value = "/error404", method = RequestMethod.GET)
	public String error404() {
		
		return "error/error404";
	}
	@RequestMapping(value = "/error500", method = RequestMethod.GET)
	public String error500() {
		
		return "error/error500";
	}
	@RequestMapping(value = "/ban", method = RequestMethod.GET)
	public String ban() {
		
		return "error/ban";
	}
	@RequestMapping(value = "/music", method = RequestMethod.GET)
	public void muiscPlayer() {
		
	}
	
	
	
	
}
