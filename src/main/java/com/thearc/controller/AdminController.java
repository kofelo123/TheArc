package com.thearc.controller;

import com.thearc.domain.BoardVO;
import com.thearc.domain.PageMaker;
import com.thearc.domain.SearchCriteria;
import com.thearc.domain.UserVO;
import com.thearc.service.AdminService;
import com.thearc.service.BoardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

@Controller
@RequestMapping("/admin")
public class AdminController {

	private static Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	@Autowired
	private AdminService service;
	
	 @Autowired
	 private BoardService boardservice;
	
	@GetMapping("/userlist")
	public void userlist(Model model) throws Exception{

		model.addAttribute("userVO",service.listuser());
	}
	
	@PostMapping("/admLogPost")
	public String admLogPost(UserVO user,HttpServletResponse response,HttpSession session) throws Exception{
		
		UserVO vo=service.adminlogin(user);
		
		if(vo != null && vo.getUid().equals("admin")) {
			
			session.setAttribute("adminlogin", user);
			
			return "redirect:/admin/userlist";
			
		}else if(vo == null || !vo.getUid().equals("admin")){
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print("<script>alert('관리자 아이디혹은 비밀번호가 일치하지 않습니다.');location.href='/thearc/user/login';</script>");
		out.flush();
		}
		return null;
	}
	
	@GetMapping("/chartpage")
	public void charts(Model model)throws Exception{

		model.addAttribute("chart1",service.dayBoard());
		model.addAttribute("chart2",service.dayReply());///chart2까지 1번그래프
		model.addAttribute("chart3",service.cateBoardview());///chart3까지 2번그래프
		model.addAttribute("chart4",service.weekcateBoard());
		model.addAttribute("chart5",service.weekReplyCount());
	}
	
	@GetMapping("/authmodify")
	public String charts(Model model,UserVO user)throws Exception{
		service.authmodify(user);
		model.addAttribute("userVO",service.listuser());
		return "/admin/userlist";
	}
	
	@GetMapping("/userDrop")
	public String userDrop(Model model,UserVO user)throws Exception{
		service.userDrop(user);
		model.addAttribute("userVO",service.listuser());
		return "/admin/userlist";
	}
	
	@GetMapping("/superadmin/{category}")
	public String superAdmin(@ModelAttribute("cri") SearchCriteria cri, Model model,@PathVariable String category)throws Exception{
		model.addAttribute("list", boardservice.listSearchCriteria(cri,category));
		 PageMaker pageMaker = new PageMaker();
		    pageMaker.setCri(cri);
		    pageMaker.setTotalCount(boardservice.listSearchCount(cri,category));
		    model.addAttribute("pageMaker", pageMaker);
		  return "/admin/superadmin";
	}
	
	@GetMapping("/boardDrop")
	public String boardDrop(Model model,BoardVO board)throws Exception{
		service.boardDrop(board);
		return "redirect:/admin/superadmin/"+board.getCategory();
	}

}
