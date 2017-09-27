package com.thearc.controller;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.thearc.domain.BoardVO;
import com.thearc.domain.PageMaker;
import com.thearc.domain.SearchCriteria;
import com.thearc.domain.UserVO;
import com.thearc.service.AdminService;
import com.thearc.service.BoardService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	private static Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	@Inject
	private AdminService service;
	
	 @Inject
	 private BoardService boardservice;
	
	
	@RequestMapping(value="/userlist" , method= RequestMethod.GET)
	public void userlist(Model model) throws Exception{
		logger.info("userlist get...");
		/**/model.addAttribute("userVO",service.listuser());
		
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
		model.addAttribute("chart1",service.dayBoard());
		model.addAttribute("chart2",service.dayReply());///chart2까지 1번그래프
		model.addAttribute("chart3",service.cateBoardview());///chart3까지 2번그래프
		model.addAttribute("chart4",service.weekcateBoard());
		model.addAttribute("chart5",service.weekReplyCount());
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
	@RequestMapping(value="/superadmin/{category}" , method=RequestMethod.GET)
	public String superAdmin(@ModelAttribute("cri") SearchCriteria cri, Model model,@PathVariable("category")String category)throws Exception{
		model.addAttribute("list", boardservice.listSearchCriteria(cri,category));
		 PageMaker pageMaker = new PageMaker();
		    pageMaker.setCri(cri);
		    pageMaker.setTotalCount(boardservice.listSearchCount(cri,category));
		    model.addAttribute("pageMaker", pageMaker);
		  return "/admin/superadmin";
	}
	
	@RequestMapping(value="/boardDrop" , method=RequestMethod.GET)
	public String boardDrop(Model model,BoardVO board)throws Exception{
		service.boardDrop(board);
		return "redirect:/admin/superadmin/"+board.getCategory();
	}
	
	
}
