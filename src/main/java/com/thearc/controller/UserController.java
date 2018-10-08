package com.thearc.controller;

//import com.thearc.domain.AddressVO;
import com.thearc.domain.LoginDTO;
import com.thearc.domain.UserVO;
import com.thearc.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Date;

/**
  *
  * @author Jeongwon Heo
  * since
  * <pre>
  * << 개정이력(Modification Information) >>
  *
  *      수정일                   수정자                수정내용
  *  -----------    --------    ---------------------------
  *  2018. 6. 23.     허정원		 유효성검사(JSR - 303)적용 (회원가입)
  *  2018. 7. 20.     허정원		 관리자페이지 인터셉터처리 관련 수정
  * </pre>
  */



@Controller
@RequestMapping("/user")
public class UserController {

	private static Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService service;

	
	@GetMapping("/login")
	public void loginGET(){
	}
	
	@PostMapping("/loginPost")
	public String loginPOST(LoginDTO dto,BindingResult result, HttpSession session, Model model) throws Exception {

		if(result.hasErrors()) {
			System.out.println("유효성검사 에러");
			return "/user/login";
		}
						
		UserVO vo = service.login(dto);
		System.out.println("loginTest:"+vo);
		
		model.addAttribute("userVO", vo);

		if (dto.isUseCookie()) { /// 로그인폼에서 자동로그인 체크여부 -> LoginDto에 필드 있다.
			int amount = 60 * 60 * 24 * 7;
			Date sessionLimit = new Date(System.currentTimeMillis() + (1000 * amount));
			service.keepLogin(vo.getUid(), session.getId(), sessionLimit);
		}
		
		//인터셉터의 후처리로 인해 일단 컨트롤러가 반환할 view가 필요해서 그냥 login으로 지정해놓은것(의미는없음) , (후처리라 return null은 안됨)
		return "/user/login";
	  }

	@GetMapping("/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response, HttpSession session)throws Exception {

		Object obj = session.getAttribute("login");

		if (obj != null) {
			UserVO vo = (UserVO) obj;
			session.removeAttribute("login");
			session.invalidate();

			Cookie loginCookie = WebUtils.getCookie(request, "loginCookie");

			if (loginCookie != null) {
				loginCookie.setPath("/");
				loginCookie.setMaxAge(0);
				response.addCookie(loginCookie);
				service.keepLogin(vo.getUid(), session.getId(), new Date());/// 아마 현재시간으로 기한을 맞춰서 없애는것일것이다.
			}
		}
		return "redirect:/sboard/main";
	}

	// 회원가입 만들것.
	@GetMapping("/join")
	public void join(Model model) {

		model.addAttribute("uvo", new UserVO());

	}

	@PostMapping("joinPost")
	public String joinPost(@ModelAttribute("uvo") @Valid UserVO user, BindingResult result, RedirectAttributes rttr)throws Exception {

		// 유효성검사
		if (result.hasErrors()) {
			System.out.println("유호성검사 에러");
			return "/user/join";
		}

		service.joinPost(user);

		rttr.addFlashAttribute("msg", "SUCCESS");

		return "redirect:/user/login";
	}
/*
	@GetMapping("/idcheck")
	public void id_check(UserVO user, Model model) throws Exception {/// 여기서 userVO는 아이디값이 setter해서 넘어가는 것일것이다.

		int answer = 0; /// flag같은것인데 boolean false로 디폴트로 해놓고 해도될듯
		UserVO user2 = service.id_checkPost(user);*//* *//*
		if (user2 == null) {
			System.out.println(user2);
			answer = 1;
		} else {
			answer = -1;
		}

		model.addAttribute("user", user);
		model.addAttribute("answer", answer);
		System.out.println(user.getUid());

	}*/

	/**
	 * ajax 방식으로 중복확인 및 유효성검사
	 * @param uid 아이디값넘김
	 * @return
	 * @throws Exception
	 */

	@ResponseBody
	@PostMapping("/idcheck2")
	public ResponseEntity<String> id_check2(String uid) throws Exception {
		System.out.println("idcheck2Test22");
		ResponseEntity<String> entity = null;

		try {
			UserVO vo = new UserVO();
			vo.setUid(uid);
			UserVO user2 = service.id_checkPost(vo);

			if (uid.isEmpty()) {
				entity = new ResponseEntity<String>("Empty", HttpStatus.OK);
			} else if (user2 != null) {
				entity = new ResponseEntity<String>("Duplicate", HttpStatus.OK);
			} else {
				entity = new ResponseEntity<String>("Success", HttpStatus.OK);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return entity;
	}

//	@GetMapping("/findZipNum")
//	public void findZipNum(Model model, AddressVO address) throws Exception {
//
//	}

//	@PostMapping("/findZipNum")
//	public void findZipNumPost(Model model, AddressVO address) throws Exception {
//		/// AddressVO 필드 중 dong을 넘김.
//		System.out.println("findzipnum ...userController");
//		System.out.println(address);
//
//		model.addAttribute("addressList", service.findzipnum(address));
//		System.out.println("returnTest:" + service.findzipnum(address));
//	}

	@GetMapping("/idfind")
	public void idfind() {

	}

	@GetMapping("/idfindmail")
	public void idfindmail(UserVO user, HttpServletRequest request, ModelMap mo) throws Exception {
		/// HttpServletRequest Modelmap 왜가는지?..
		service.idfindmail(request, mo, user);
	}

	@GetMapping("/mailcheck")
	public void mailhashcheck(UserVO user, Model model) throws Exception {

		UserVO user2 = service.hashbyid(user);
		if (user2.getUid() != null)
			model.addAttribute("user", user2);

		// 로직을 어떻게 할지..
		// 1.해쉬코드 검사하는 로직(아이디로 찾아야 될지 동적 sql을 써야할지?) 2.view에 전달 해쉬코드 일치하면 비밀번호 변경폼,
		// 일치x시에 실패 메세지
		// 3.비밀번호 변경하는 로직->(update) 4.처리결과 처리 (간단하게 alert해도 될듯) -> 메인페이지로 redirect

	}

	@PostMapping("/modifypw")
	public String modifypw(UserVO user) throws Exception {
		System.out.println(user.getUid());// input type=hidden으로 가져옴
		System.out.println(user.getUpw());

		service.modifypw(user);

		return "redirect:/user/login";

	}

	@GetMapping("/logintest")
	public void logintest() {

	}

	@GetMapping("/jusoPopup")
	public void jusoPopup() {

	}

	@PostMapping("/jusoPopup")
	public void jusoPopupPost() {

	}

}


