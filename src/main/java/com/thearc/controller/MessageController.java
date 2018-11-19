package com.thearc.controller;

import com.thearc.domain.MessageVO;
import com.thearc.domain.PageMaker;
import com.thearc.domain.SearchCriteria;
import com.thearc.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/sboard/message/")
@PreAuthorize("isAuthenticated()")
public class MessageController {
	
  @Autowired
  private MessageService service;
  
  @GetMapping("listMessage")
  public void listMessage(@ModelAttribute ("cri") SearchCriteria cri, @RequestParam String uid, Model model) throws Exception{
	
	String targetid=uid;
	model.addAttribute("list", service.listSearchCriteria(cri,targetid));//정보다가져온다.

    PageMaker pageMaker = new PageMaker();
    pageMaker.setCri(cri);

    pageMaker.setTotalCount(service.listSearchCount(cri,targetid));

    model.addAttribute("pageMaker", pageMaker);
	
  }

  
  @GetMapping("readMessage")
  public void readMessage(@RequestParam int mid,@ModelAttribute ("cri") SearchCriteria cri, Model model) throws Exception {


    model.addAttribute(service.readMessage(mid));
  }

  @GetMapping("registMessage")
  public void registMessage() throws Exception {

  }

  @PostMapping("registMessage")
  public String registMessagePost(MessageVO message, RedirectAttributes rttr) throws Exception {

    service.regist(message);

    rttr.addFlashAttribute("msg", "SUCCESS");

    return "redirect:/sboard/message/listMessage?uid="+message.getSender();
  }
  
}
