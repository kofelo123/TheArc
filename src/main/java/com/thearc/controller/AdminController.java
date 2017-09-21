package com.thearc.controller;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.thearc.domain.UserVO;
import com.thearc.service.AdminService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	private static Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	@Inject
	private AdminService service;
	
	
	
	
	@RequestMapping(value="/userlist" , method= RequestMethod.GET)
	public void userlist(Model model) throws Exception{
		logger.info("userlist get...");
		/**/model.addAttribute("userVO",service.listuser());
		
		model.addAttribute("sboardNum","userlist");
	}
	
	@RequestMapping(value="/admLogPost" , method=RequestMethod.POST)
	public void admLogPost(UserVO user,Model model) throws Exception{
		logger.info("Admin Login Post..");
		UserVO vo=service.adminlogin(user);
		model.addAttribute("vo", vo);
		
		
	}
	
	@RequestMapping(value="/chartpage" , method=RequestMethod.GET)
	public void charts(Model model)throws Exception{
		logger.info("charts by admin get ..");
		model.addAttribute("sboardNum","chartpage");
	}
	
	@RequestMapping(value="/authmodify" , method=RequestMethod.GET)
	public String charts(Model model,UserVO user)throws Exception{
		service.authmodify(user);
		model.addAttribute("userVO",service.listuser());
		return "/admin/userlist";
	}
	@RequestMapping(value="/userDrop" , method=RequestMethod.GET)
	public String userDrop(Model model,UserVO user)throws Exception{
		service.userDrop(user);
		model.addAttribute("userVO",service.listuser());
		return "/admin/userlist";
	}
	
	
	
}
