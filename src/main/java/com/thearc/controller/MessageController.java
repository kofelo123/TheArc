package com.thearc.controller;

import com.thearc.domain.MessageVO;
import com.thearc.domain.PageMaker;
import com.thearc.domain.SearchCriteria;
import com.thearc.service.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/sboard/*")
public class MessageController {
	
	private static final Logger logger = LoggerFactory.getLogger(MessageController.class);

  @Autowired
  private MessageService service;
  
  @GetMapping("/mail/listmail")
  public void listmail(@ModelAttribute ("cri") SearchCriteria cri, @RequestParam String uid, Model model) throws Exception{
	
	String targetid=uid;
	model.addAttribute("list", service.listSearchCriteria(cri,targetid));//정보다가져온다.

    PageMaker pageMaker = new PageMaker();
    pageMaker.setCri(cri);

    pageMaker.setTotalCount(service.listSearchCount(cri));

    model.addAttribute("pageMaker", pageMaker);
	
  }

  
  @GetMapping("/mail/readmail")
  public void readmail(@RequestParam int mid,@ModelAttribute ("cri") SearchCriteria cri, Model model) throws Exception {


    model.addAttribute(service.readMessage(mid));
  }
  
  @GetMapping("/mail/registermail")
  public void registermail() throws Exception {

  }
  
  @PostMapping("/mail/registermail")
  public String registermailPost(MessageVO message, RedirectAttributes rttr) throws Exception {

    service.regist(message);
    
    rttr.addFlashAttribute("msg", "SUCCESS");
    
    return "redirect:/sboard/mail/listmail?uid="+message.getSender();
  }
  
}
