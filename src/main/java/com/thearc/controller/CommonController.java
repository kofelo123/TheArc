package com.thearc.controller;

import lombok.extern.log4j.Log4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Log4j
public class CommonController {

	@GetMapping("/accessError")
	public void accessDenied(Authentication auth, Model model) {

		log.info("access Denied : " + auth);

		model.addAttribute("msg", "Access Denied");
	}

	@GetMapping("/customLogin")
	public void loginInput(String error, String logout, Model model) {

		log.info("error: " + error);
		log.info("logout: " + logout);

		if (error != null) {
			model.addAttribute("error", "Login Error Check Your Account");
		}

		if (logout != null) {
			model.addAttribute("logout", "Logout!!");
		}
	}

	//localhost일때만 사용,개발시 간편하게 로그인하도록 버튼 만들어놓은것.
	@GetMapping("/devLogin")
	@PreAuthorize("isAuthenticated()")
	public String devLogin(){



		return "/sboard/main";
	}
//	@GetMapping("/customLogout")
//	public void logoutGET() {
//
//		log.info("custom logout");
//	}

//	@PostMapping("/customLogout")
//	public void logoutPost() {
//
//		log.info("post custom logout");
//	}

}
