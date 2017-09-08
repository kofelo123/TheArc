package com.thearc.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.thearc.domain.Criteria;
import com.thearc.domain.PageMaker;
import com.thearc.domain.ReplyVO;
import com.thearc.service.ReplyService;

@RestController
@RequestMapping("/replies")
public class ReplyController {

  @Inject
  private ReplyService service;

  @RequestMapping(value = "", method = RequestMethod.POST)
  public ResponseEntity<String> register(@RequestBody ReplyVO vo) { ///코드에서 예외대신에 Response Entity를 이용해서 사용자에게 정보를 전달한다.(HTTP상태코드+데이터 함께 전송가능)

    ResponseEntity<String> entity = null;///ResponseEntity는 메세지와 http status정보를 같이 전송할수있는 스프링제공 클래스.	
    try {
      service.addReply(vo);
      entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
    } catch (Exception e) {
      e.printStackTrace();
      entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
    return entity;
  }

  @RequestMapping(value = "/all/{bno}", method = RequestMethod.GET) ///특정게시물의 모든댓글  / {bno}는 메소드 파라미터에서 @PathVariable로 활용된다.	
  public ResponseEntity<List<ReplyVO>> list(@PathVariable("bno") Integer bno) {

    ResponseEntity<List<ReplyVO>> entity = null;
    try {
      entity = new ResponseEntity<>(service.listReply(bno), HttpStatus.OK);

    } catch (Exception e) {
      e.printStackTrace();
      entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    return entity;
  }

  @RequestMapping(value = "/{rno}", method = { RequestMethod.PUT, RequestMethod.PATCH })///일반적으로 전체 데이터 수정시에 PUT, 일부데이터 수정시에 PATCH를 사용					
  public ResponseEntity<String> update(@PathVariable("rno") Integer rno, @RequestBody ReplyVO vo) {///@RequestBody 는 데이터 전송방식을 JSON 포맷이므로 이를 객체로 처리하기위함

    ResponseEntity<String> entity = null;
    try {
      vo.setRno(rno);
      service.modifyReply(vo);

      entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
    } catch (Exception e) {
      e.printStackTrace();
      entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
    return entity;
  }

  @RequestMapping(value = "/{rno}", method = RequestMethod.DELETE)
  public ResponseEntity<String> remove(@PathVariable("rno") Integer rno) {

    ResponseEntity<String> entity = null;
    try {
      service.removeReply(rno);
      entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
    } catch (Exception e) {
      e.printStackTrace();
      entity = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
    return entity;
  }

  @RequestMapping(value = "/{bno}/{page}", method = RequestMethod.GET)///페이징 처리를 위해  게시물번호/페이지번호
  public ResponseEntity<Map<String, Object>> listPage(
      @PathVariable("bno") Integer bno,
      @PathVariable("page") Integer page) {

    ResponseEntity<Map<String, Object>> entity = null;
    
    try {///cri에 페이지값 set-> PageMaker에 Cri set -> 댓글갯수 데이터 가져옴-> pageMaker에 갯수set과 동시에 페이징 계산-> ResponseEntity로 데이터 전달
      Criteria cri = new Criteria();
      cri.setPage(page);

      PageMaker pageMaker = new PageMaker();
      pageMaker.setCri(cri);

      Map<String, Object> map = new HashMap<String, Object>();
      List<ReplyVO> list = service.listReplyPage(bno, cri);///댓글리스트 뽑아온다

      map.put("list", list);///Ajax로 호출될 떄는 Model사용못하므로 Map으로 보내줘야한다.

      int replyCount = service.count(bno);
      pageMaker.setTotalCount(replyCount);

      map.put("pageMaker", pageMaker);

      entity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);

    } catch (Exception e) {
      e.printStackTrace();
      entity = new ResponseEntity<Map<String, Object>>(HttpStatus.BAD_REQUEST);
    }
    return entity;
  }

}
