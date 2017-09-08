package com.thearc.controller;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thearc.domain.MessageVO;
import com.thearc.domain.PageMaker;
import com.thearc.domain.SearchCriteria;
import com.thearc.service.BoardService;
import com.thearc.service.MessageService;

@Controller
@RequestMapping("/sboard/*")
public class MessageController {
	
	private static final Logger logger = LoggerFactory.getLogger(MessageController.class);

  @Inject
  private MessageService service;
  


 /* @RequestMapping(value = "/", method = RequestMethod.POST)
  public ResponseEntity<String> addMessage(@RequestBody MessageVO vo) {

    ResponseEntity<String> entity = null;
    try {
      service.addMessage(vo);
      entity = new ResponseEntity<>("SUCCESS", HttpStatus.OK);
    } catch (Exception e) {
      e.printStackTrace();
      entity = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
    return entity;
  }*/

  @RequestMapping(value="/mail/listmail",method=RequestMethod.GET)
  public void listmail(@ModelAttribute("cri") SearchCriteria cri,@RequestParam("uid") String uid, Model model) throws Exception{
	
	 logger.info("uid테스트"+uid);
	logger.info("listmail..");
	String targetid=uid;
	model.addAttribute("list", service.listSearchCriteria(cri,targetid));//정보다가져온다.

    PageMaker pageMaker = new PageMaker();
    pageMaker.setCri(cri);

    // pageMaker.setTotalCount(service.listCountCriteria(cri));
    pageMaker.setTotalCount(service.listSearchCount(cri));

    model.addAttribute("pageMaker", pageMaker);
	
	
	/*  service.listmail(vo);*/
  }

  
  @RequestMapping(value = "/mail/readmail", method = RequestMethod.GET)
  public void readmail(@RequestParam("mid") int mid,@ModelAttribute("cri") SearchCriteria cri, Model model) throws Exception {

    logger.info("readmail get ...........");
    
    model.addAttribute(service.readMessage(mid));
  }
  
  @RequestMapping(value = "/mail/registermail", method = RequestMethod.GET)
  public void registermail() throws Exception {

    logger.info("registermail get ...........");
  }
  
  @RequestMapping(value = "/mail/registermail", method = RequestMethod.POST)
  public String registermailPost(MessageVO message, RedirectAttributes rttr) throws Exception {

    logger.info("registermail Post ...........");
    
    service.regist(message);
    
    rttr.addFlashAttribute("msg", "SUCCESS");
    
    return "redirect:/sboard/mail/listmail?uid="+message.getSender();
  }
  
}
