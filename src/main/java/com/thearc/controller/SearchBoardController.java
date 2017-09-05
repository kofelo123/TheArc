package com.thearc.controller;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/sboard/*")
public class SearchBoardController {

	private static final Logger logger = LoggerFactory.getLogger(SearchBoardController.class);
	
	 @RequestMapping(value = "/main", method = RequestMethod.GET)
	  public void main(Locale locale, Model model) {
	   	  
	  }
	 
	 @RequestMapping(value="/faq",method=RequestMethod.GET)
	 public void faq(Model model){
		 
	 }
}
