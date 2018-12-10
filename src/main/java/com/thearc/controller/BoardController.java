package com.thearc.controller;

import com.thearc.domain.BoardVO;
import com.thearc.domain.LikeVO;
import com.thearc.domain.PageMaker;
import com.thearc.domain.SearchCriteria;
import com.thearc.service.BoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.*;

@Slf4j
@Controller
@RequestMapping("/sboard/*")
public class BoardController {

	 @Autowired
	 private BoardService service;

//	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

	 @RequestMapping(value = "/main", method = RequestMethod.GET)
	  public void main(Locale locale, Model model, SearchCriteria cri) throws Exception {

	 	//공공데이터 - 날씨
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


	 @GetMapping("/faq")
	 public void faq(Model model){

	 }

	  ///9/12 pathVariable을 통한 다중게시판
	@GetMapping("/list/{category}")
	  public String listPage(@ModelAttribute ("cri") SearchCriteria cri, Model model,@PathVariable String category) throws Exception {

	    model.addAttribute("list", service.listSearchCriteria(cri,category));//페이지시작과 끝의 리스트정보를가져온다(if검색정보있을때는 정보에맞게) ///<잘못생각, 검색에 맞게 db에서 게시글들 모두긁어온다.
	//  SearchCritera cri = 검색타입,키워드 속성 가짐. // xml= listsearch - pageStart, pageNum +search에 맞는 모든 리스트데이터 받음
	    //썸네일게시판용	+포토존
	    if(category.equals("thisweek")||category.equals("photo")||category.equals("terarium")||category.equals("leisure")||category.equals("seastory")||category.equals("academy"))
	    	model.addAttribute("thumNail",service.listThumnail(cri,category));
	    //
	    PageMaker pageMaker = new PageMaker();
	    pageMaker.setCri(cri);
	    pageMaker.setTotalCount(service.listSearchCount(cri,category));
	    							//listSearchCount는 게시글갯수 카운트(cri(검색조건에맞는)) 그래서 totalcount를 setter해주는것
	    model.addAttribute("pageMaker", pageMaker);
	    return "/sboard/list";
	  }


	/*public String read(@RequestParam int bno,@RequestParam String uid, @ModelAttribute ("cri") SearchCriteria cri, Model model,@PathVariable String category)///readpage의 url에 파라미터로 붙는 uid cri가 requestParam으로 오는건지*/
	@GetMapping("/readPage/{category}")
	public String read(@RequestParam Map<String,String> map, @ModelAttribute ("cri") SearchCriteria cri, Model model,@PathVariable String category)///readpage의 url에 파라미터로 붙는 uid cri가 requestParam으로 오는건지
			throws Exception {

		int bno  = Integer.parseInt(map.get("bno"));

		model.addAttribute(service.read(bno)); //model.addAttribute의 파라미터 하나있는거라서 view에서 전달시 boardVO가 된다.

/*	    LikeVO likevo = service.checklike(uid,bno);

	    if(uid.length()>0){///로그인일때/(기존 로직과 다른데, tbl_check의 칼럼을 fk로 바꾸면서 비로그인시에 문제가 생기기 때문에 넣어준것.(uid 없으면 fk참조무결성?에 의해 select로 안가져오서 model에 null이 찍힘)
	    	if(likevo==null) // null일경우 넣기위한 로직이다( 왜냐하면 null 인상태로 model.addAttribute가 에러가 났었다. 모델에 객체내용없이 뷰에 전달이 안된다.
	    	service.insertlikedefault(uid, bno); // uid,bno만 넣어주기 떄문에 'n'상태가 된다.      ///근데 굳이 이렇게 안해도 더 간결하게 false를 default로두고  하는 방법도 있지않을까..
	    model.addAttribute(service.checklike(uid,bno));//추천여부체크 ///위에코드랑좀 겹치는데..
	    }*/
		return "/sboard/readPage";
	}
//	  public String read(@RequestParam("bno") int bno, @ModelAttribute("cri") SearchCriteria cri, Model model,@RequestParam("uid") String uid)///readpage의 url에 파라미터로 붙는 uid cri가 requestParam으로 오는건지

	  @PostMapping("/removePage/{category}")
      @PreAuthorize("principal.member.uid == #uid")
	  public String remove(String uid,@RequestParam int bno, SearchCriteria cri, RedirectAttributes rttr,@PathVariable String category) throws Exception {

		log.info("bno Test:"+bno);
//		log.info("uid Test:"+uid);
	    service.remove(bno);

	    rttr.addAttribute("page", cri.getPage());///addFlashAttribute와 달리 URL 파라미터에 붙여주는 방식이다. 그래서 list페이지로 redirect될때 파라미터로 받아낼수있게 cri 넘긴다.
	    rttr.addAttribute("perPageNum", cri.getPerPageNum());
	    rttr.addAttribute("searchType", cri.getSearchType());
	    rttr.addAttribute("keyword", cri.getKeyword());

	    rttr.addFlashAttribute("msg", "SUCCESS");

	    return "redirect:/sboard/list/"+category;
	  }

	  ///modify에 굳이 category필요없긴한데(bno로 식별가능),되돌아갈 방법이 없다. 수정후 돌아가기 위해서 게시판정보가 필요함.다른방법도 있긴할거같은데..
	  @GetMapping("/modifyPage/{category}")
      @PreAuthorize("principal.username == #uid")
	  public String modifyPagingGET(int bno,String uid, @ModelAttribute("cri") SearchCriteria cri, Model model,@PathVariable String category) throws Exception {

	    model.addAttribute(service.read(bno)); ///BoardVO return = category 포함.

	    return "/sboard/modifyPage";
	  }

	  @PostMapping("/modifyPage/{category}")
	  @PreAuthorize("principal.username == #board.writer")
//      @PreAuthorize("isAuthenticated()")
	  public String modifyPagingPOST(BoardVO board, SearchCriteria cri, RedirectAttributes rttr) throws Exception {

	    service.modify(board);

	    rttr.addAttribute("page", cri.getPage());
	    rttr.addAttribute("perPageNum", cri.getPerPageNum());
	    rttr.addAttribute("searchType", cri.getSearchType());
	    rttr.addAttribute("keyword", cri.getKeyword());

	    rttr.addFlashAttribute("msg", "SUCCESS");


	    return "redirect:/sboard/list/"+board.getCategory();
	  }

//	  public String registGET(@PathVariable("category")String category,Model model) throws Exception {

	  @GetMapping("/register/{category}")
	  @PreAuthorize("isAuthenticated()")
	  public String registGET(@PathVariable String category, Model model) throws Exception {

	    model.addAttribute("bvo",new BoardVO());

	    return "/sboard/register";
	  }


	  @PostMapping("/register/{category}")
	  @PreAuthorize("isAuthenticated()")
	  public String registPOST(@ModelAttribute("bvo") @Valid BoardVO board,BindingResult result, RedirectAttributes rttr) throws Exception {

		if(result.hasErrors()) {
			System.out.println("유효성검사 에러");
			return "/sboard/register";
		}


	    service.regist(board);

	    rttr.addFlashAttribute("msg", "SUCCESS");

	    return "redirect:/sboard/list/"+board.getCategory();
	  }


	  @RequestMapping("/getAttach/{bno}")
	  @ResponseBody
	  public List<String> getAttach(@PathVariable Integer bno)throws Exception{
	    return service.getAttach(bno);
	  }

	  @RequestMapping("/getAttachOne/{bno}")//썸네일 게시판용
	  @ResponseBody
	  public String getAttachOne(@PathVariable Integer bno)throws Exception{

		  return service.getAttachOne(bno);
	  }

	  @GetMapping("/calendar")
	  public void calendar(Model model) throws Exception {


	    model.addAttribute("sboardNum", "calendar");
	  }
	

/*
	  @GetMapping("/readPage/like")
	  public String like(@RequestParam int bno,@RequestParam String uid,BoardVO board) throws Exception {
	    logger.info("like add ...........");
	    service.addlike(bno);
	    service.updatelikey(uid,bno);
	    return "redirect:/sboard/readPage/"+board.getCategory()+"?bno="+bno+"&uid="+uid; // 리턴이 되어도 uri 자체가 바뀌진 않는다 그래서 애초에 요청이었던 /sboard/readPage/like?bno=~~~ 이런식으로 된다. -> scm 플레이어에 의한 redirect 충돌에 의한것이었음.

	  }
	  @GetMapping("/readPage/dislike")
	  public String dislike(@RequestParam int bno,@RequestParam String uid,BoardVO board) throws Exception {

	    logger.info("like substraction ...........");
	    service.sublike(bno);
	    service.updateliken(uid,bno);
	    return "redirect:/sboard/readPage/"+board.getCategory()+"?bno="+bno+"&uid="+uid; // 리턴이 되어도 uri 자체가 바뀌진 않는다 그래서 애초에 요청이었던 /sboard/readPage/like?bno=~~~ 이런식으로 된다. -> scm 플레이어에 의한 redirect 충돌에 의한것이었음.

	  }
*/
	 /*
	  @ResponseBody
	  @GetMapping("/readPage/like2")
	  public ResponseEntity<String> like2(int bno,String uid,String category){
		  
		  ResponseEntity<String> entity = null;
		  
		  try {
			  service.addlike(bno);
			  service.updatelikey(uid, bno);
			  entity = new ResponseEntity<String>("Success",HttpStatus.OK);
		  }catch(Exception e) {
			  e.printStackTrace();
		  }
		  
		  return entity;
	  }
	  @ResponseBody
      @GetMapping("/readPage/dislike2")
      public ResponseEntity<String> dislike2(int bno, String uid, String category){

	     ResponseEntity<String> entity = null;

        try{
            service.sublike(bno);
            service.updateliken(uid,bno);
            entity = new ResponseEntity<String>("Success",HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
        }
	     return entity;
      }*/

      @ResponseBody
	  @GetMapping("/readPage/likeCheck")
	  public ResponseEntity<Map<String,Object>> likeCheck(int bno, String uid)throws Exception{

      	ResponseEntity<Map<String,Object>> entity = null;
      	BoardVO boardVO = service.read(bno);
		LikeVO likeVO = service.checklike(uid,bno);

		Map<String,Object> map = new HashMap<>();
		map.put("countLike",boardVO.getCountlike());

      	try{
			if(uid.isEmpty()) {
			    map.put("message","notLogin");
				entity = new ResponseEntity<>(map, HttpStatus.OK);
			}else if(likeVO == null){
				service.insertlikedefault(uid,bno);
				map.put("message","default");
				entity = new ResponseEntity<>(map,HttpStatus.OK);
			}else if(likeVO.getLikecheck().equals("n")){
				map.put("message","ntoy");
				entity = new ResponseEntity<>(map,HttpStatus.OK);
			}else if(likeVO.getLikecheck().equals("y")){
				map.put("message","yton");
				entity = new ResponseEntity<>(map,HttpStatus.OK);
			}
		}catch (Exception e){
      		e.printStackTrace();
		}
		return entity;
	  }

	@ResponseBody
	@GetMapping("/readPage/addLike")
	public ResponseEntity<String> addLike(@RequestParam int bno,@RequestParam String uid) throws Exception {

		ResponseEntity<String> entity = null;

		try{
            service.addlike(bno);
            service.updatelikey(uid,bno);
            entity = new ResponseEntity<>("SUCCESS",HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
        }

		return entity;
	}

	@ResponseBody
	@GetMapping("/readPage/subLike")
	public ResponseEntity<String> subLike(@RequestParam int bno,@RequestParam String uid,BoardVO board) throws Exception {


		ResponseEntity<String> entity = null;

		try{
			service.sublike(bno);
			service.updateliken(uid,bno);
			entity = new ResponseEntity<>("SUCCESS",HttpStatus.OK);
		}catch(Exception e){
			e.printStackTrace();
		}

		return entity;
	}


	  @GetMapping("/fbshare")
	  public void fbshare(@RequestParam int bno)throws Exception{

	  }
	  @GetMapping("/location")
	  public void location()throws Exception{

	  }

	  @GetMapping("/thearc")
	  public void thearc()throws Exception{

	  }

	  @GetMapping("/exhibit")
	  public void exhibit()throws Exception{

	  }

}
