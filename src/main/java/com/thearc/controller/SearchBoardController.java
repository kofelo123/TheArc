package com.thearc.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thearc.service.BoardService;
import com.thearc.domain.BoardVO;
import com.thearc.domain.LikeVO;
import com.thearc.domain.PageMaker;
import com.thearc.domain.SearchCriteria;
import com.thearc.domain.UserVO;


@Controller
@RequestMapping("/sboard/*")
public class SearchBoardController {
	
	 @Inject
	 private BoardService service;

	private static final Logger logger = LoggerFactory.getLogger(SearchBoardController.class);
	
	 @RequestMapping(value = "/main", method = RequestMethod.GET)
	  public void main(Locale locale, Model model, SearchCriteria cri) throws Exception {
	   	  model.addAttribute("weather",service.getWeather());
	   	 
	   	 cri.setPerPageNum(5);
	   	 List<List<BoardVO>> minBoard = new ArrayList<List<BoardVO>>();//미니게시판 - 4개 리스트를 담는 2중forEach사용
	   	 minBoard.add(service.listSearchCriteria(cri,"free"));
	   	 minBoard.add(service.listSearchCriteria(cri,"notice"));
	   	 minBoard.add(service.listSearchCriteria(cri,"news"));
	   	 minBoard.add(service.listSearchCriteria(cri,"visit"));
	   	 model.addAttribute("minBoard", minBoard);
	   	model.addAttribute("photo", service.listSearchCriteria(cri,"photo"));
	   	model.addAttribute("thumNail",service.listThumnail(cri,"photo"));
	   	 	
	  }
	 
	 @RequestMapping(value="/faq",method=RequestMethod.GET)
	 public void faq(Model model){
		 
	 }
	 
	 @RequestMapping(value = "/list/{category}", method = RequestMethod.GET) ///9/12 pathVariable을 통한 다중게시판 테스트
	  public String listPage(@ModelAttribute("cri") SearchCriteria cri, Model model,@PathVariable("category")String category) throws Exception {

	    logger.info(cri.toString());

	    // model.addAttribute("list", service.listCriteria(cri));
	    model.addAttribute("list", service.listSearchCriteria(cri,category));//페이지시작과 끝의 리스트정보를가져온다(if검색정보있을때는 정보에맞게) ///<잘못생각, 검색에 맞게 db에서 게시글들 모두긁어온다.
	//  SearchCritera cri = 검색타입,키워드 속성 가짐. // xml= listsearch - pageStart, pageNum +search에 맞는 모든 리스트데이터 받음 
	    
	    //썸네일게시판용	+포토존
	    if(category.equals("thisweek")||category.equals("photo"))
	    	model.addAttribute("thumNail",service.listThumnail(cri,category));
	    //
	    
	    PageMaker pageMaker = new PageMaker();
	    pageMaker.setCri(cri);

	    // pageMaker.setTotalCount(service.listCountCriteria(cri));
	    pageMaker.setTotalCount(service.listSearchCount(cri,category));
	    							//listSearchCount는 게시글갯수 카운트(cri(검색조건에맞는)) 그래서 totalcount를 setter해주는것
	    model.addAttribute("pageMaker", pageMaker);
	    
	    return "/sboard/list";
	  }

	  @RequestMapping(value = "/readPage/{category}", method = RequestMethod.GET)
	  public String read(@RequestParam("bno") int bno, @ModelAttribute("cri") SearchCriteria cri, Model model,@RequestParam("uid") String uid,@PathVariable("category")String category)///readpage의 url에 파라미터로 붙는 uid cri가 requestParam으로 오는건지
	      throws Exception {
		System.out.println("user테스트:"+uid);
	    model.addAttribute(service.read(bno)); //model.addAttribute의 파라미터 하나있는거라서 view에서 전달시 boardVO가 된다.
	    
	    LikeVO likevo = service.checklike(uid,bno); //
	    System.out.println("likevo테스트:"+likevo); // likevo.get~이 안된다 null 일경우 java nullpoint exception이된다. 그래서 그냥 likevo.tostring식으로 접근한다.
	    System.out.println("uid값 테스트"+uid);
	  
	    if(uid.length()>0){///로그인일때/(기존 로직과 다른데, tbl_check의 칼럼을 fk로 바꾸면서 비로그인시에 문제가 생기기 때문에 넣어준것.(uid 없으면 fk참조무결성?에 의해 select로 안가져오서 model에 null이 찍힘)
	    	if(likevo==null) // null일경우 넣기위한 로직이다( 왜냐하면 null 인상태로 model.addAttribute가 에러가 났었다. 모델에 객체내용없이 뷰에 전달이 안된다. 
	    	service.insertlikedefault(uid, bno); // uid,bno만 넣어주기 떄문에 'n'상태가 된다.      ///근데 굳이 이렇게 안해도 더 간결하게 false를 default로두고  하는 방법도 있지않을까..
	    model.addAttribute(service.checklike(uid,bno));//추천여부체크 ///위에코드랑좀 겹치는데..
	    }
	    return "/sboard/readPage";
	  }

	  @RequestMapping(value = "/removePage/{category}", method = RequestMethod.POST)
	  public String remove(@RequestParam("bno") int bno, SearchCriteria cri, RedirectAttributes rttr,@PathVariable("category")String category) throws Exception {

	    service.remove(bno);

	    rttr.addAttribute("page", cri.getPage());///addFlashAttribute와 달리 URL 파라미터에 붙여주는 방식이다. 그래서 list페이지로 redirect될때 파라미터로 받아낼수있게 cri 넘긴다.
	    rttr.addAttribute("perPageNum", cri.getPerPageNum());
	    rttr.addAttribute("searchType", cri.getSearchType());
	    rttr.addAttribute("keyword", cri.getKeyword());

	    rttr.addFlashAttribute("msg", "SUCCESS");

	    return "redirect:/sboard/list/"+category;
	  }

	  @RequestMapping(value = "/modifyPage/{category}", method = RequestMethod.GET)///modify에 굳이 category필요없긴한데(bno로 식별가능),되돌아갈 방법이 없다. 수정후 돌아가기 위해서 게시판정보가 필요함.다른방법도 있긴할거같은데..
	  public String modifyPagingGET(int bno, @ModelAttribute("cri") SearchCriteria cri, Model model,@PathVariable("category")String category) throws Exception {

	    model.addAttribute(service.read(bno)); ///BoardVO return = category 포함.
	    
	    return "/sboard/modifyPage";
	  }

	  @RequestMapping(value = "/modifyPage/{category}", method = RequestMethod.POST)
	  public String modifyPagingPOST(BoardVO board, SearchCriteria cri, RedirectAttributes rttr) throws Exception {

	    logger.info(cri.toString());
	    service.modify(board);

	    rttr.addAttribute("page", cri.getPage());
	    rttr.addAttribute("perPageNum", cri.getPerPageNum());
	    rttr.addAttribute("searchType", cri.getSearchType());
	    rttr.addAttribute("keyword", cri.getKeyword());

	    rttr.addFlashAttribute("msg", "SUCCESS");

	    logger.info(rttr.toString());

	    return "redirect:/sboard/list/"+board.getCategory();
	  }

	  @RequestMapping(value = "/register/{category}", method = RequestMethod.GET)
	  public String registGET(@PathVariable("category")String category,Model model) throws Exception {
		  model.addAttribute(category);
	    logger.info("regist get ...........");
	    
	    
	    return "/sboard/register";
	  }

	  @RequestMapping(value = "/register/{category}", method = RequestMethod.POST)
	  public String registPOST(BoardVO board, RedirectAttributes rttr) throws Exception {
		  
	    logger.info("regist post ...........");
	    logger.info(board.toString());

	    service.regist(board);

	    rttr.addFlashAttribute("msg", "SUCCESS");

	    return "redirect:/sboard/list/"+board.getCategory();
	  }
	  
	  	
	  @RequestMapping("/getAttach/{bno}")
	  @ResponseBody
	  public List<String> getAttach(@PathVariable("bno")Integer bno)throws Exception{
	    
	    return service.getAttach(bno);
	  }  
	  
	  @RequestMapping("/getAttachOne/{bno}")//썸네일 게시판용
	  @ResponseBody
	  public String getAttachOne(@PathVariable("bno")Integer bno)throws Exception{
		  
		  return service.getAttachOne(bno);
	  }  
	  

	  @RequestMapping(value = "/calendar", method = RequestMethod.GET)
	  public void calendar(Model model) throws Exception {

	    logger.info("calendar get ...........");
	    
	    model.addAttribute("sboardNum", "calendar");
	  }
	
	  @RequestMapping(value = "/readPage/like", method = RequestMethod.GET)
	  public String like(@RequestParam("bno") int bno,@RequestParam("uid") String uid,BoardVO board) throws Exception {
		  System.out.println("BoardTest:"+board);
	    logger.info("like add ...........");
	    System.out.println("bno 테스트:"+bno);
	    System.out.println("uid 테스트"+uid);
	    service.addlike(bno);
	    service.updatelikey(uid,bno);
	    return "redirect:/sboard/readPage/"+board.getCategory()+"?bno="+bno+"&uid="+uid; // 리턴이 되어도 uri 자체가 바뀌진 않는다 그래서 애초에 요청이었던 /sboard/readPage/like?bno=~~~ 이런식으로 된다. -> scm 플레이어에 의한 redirect 충돌에 의한것이었음.
	    
	  }
	  @RequestMapping(value = "/readPage/dislike", method = RequestMethod.GET)
	  public String dislike(@RequestParam("bno") int bno,@RequestParam("uid") String uid,BoardVO board) throws Exception {

	    logger.info("like substraction ...........");
	    System.out.println("bno 테스트:"+bno);
	    System.out.println("uid 테스트"+uid);
	    service.sublike(bno);	
	    service.updateliken(uid,bno);
	    return "redirect:/sboard/readPage/"+board.getCategory()+"?bno="+bno+"&uid="+uid; // 리턴이 되어도 uri 자체가 바뀌진 않는다 그래서 애초에 요청이었던 /sboard/readPage/like?bno=~~~ 이런식으로 된다. -> scm 플레이어에 의한 redirect 충돌에 의한것이었음.
	    
	  }
	  
	  
	  @RequestMapping(value="/fbshare", method = RequestMethod.GET)
	  public void fbshare(@RequestParam("bno") int bno)throws Exception{
		  
	  }
	  @RequestMapping(value="/location", method = RequestMethod.GET)
	  public void location()throws Exception{
		  
	  }
	  @RequestMapping(value="/thearc", method = RequestMethod.GET)
	  public void thearc()throws Exception{
		  
	  }
	  @RequestMapping(value="/exhibit", method = RequestMethod.GET)
	  public void exhibit()throws Exception{
		  
	  }
}
