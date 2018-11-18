/*
package com.thearc.interceptor;

import com.thearc.domain.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;


*/
/**
  *
  * @author Jeongwon Heo
  * since 2017. 
  * <pre>
  * << 개정이력(Modification Information) >>
  *
  *      수정일                   수정자                수정내용
  *  -----------    --------    ---------------------------
  *  2018. 7. 19.     허정원               로그인실패 - 인터셉터처리
  *
  * </pre>
  *//*

@Slf4j
public class LoginInterceptor extends HandlerInterceptorAdapter {

  private static final String LOGIN = "login";

 
 //로그인시 이곳에서 인터셉트
  @Override
  public void postHandle(HttpServletRequest request, 
      HttpServletResponse response, Object handler,
      ModelAndView modelAndView) throws Exception {

    HttpSession session = request.getSession();

    ModelMap modelMap = modelAndView.getModelMap();  
    Object userVO = modelMap.get("userVO");
	   
    if (userVO != null) {
    	
      log.info("new login success");
      
      session.setAttribute(LOGIN, userVO);
     
      if (request.getParameter("useCookie") != null) {

        log.info("remember me................");
        Cookie loginCookie = new Cookie("loginCookie", session.getId());
        loginCookie.setPath("/");
        loginCookie.setMaxAge(60 * 60 * 24 * 7);
        response.addCookie(loginCookie);
      }
      Object dest = session.getAttribute("dest");
      System.out.println("destTest:"+dest);///원래 가려고 했던 경로 (AuthInterceptoer로 부터 세션저장됨.)
      
      */
/*아래 ban체크용 *//*

      UserVO uvo=(UserVO) userVO;
      if(uvo.getAuthority().equals("ban")){
    	  session.removeAttribute(LOGIN);
   	   response.sendRedirect("/thearc/ban");
      }else//원래 아래 코드만 있었는데 sendredirect 두번 처리하는 에러떄문에 여기서 분기문을 둠.
      response.sendRedirect(dest != null ? (String)dest : "/thearc/sboard/main"); //로그인성공시 이부분 작동
    
    //로그인 실패시
    }else if(userVO == null){
    	System.out.println("login fail - user is not exist");
    	response.setContentType("text/html; charset=UTF-8");
    	PrintWriter out = response.getWriter();
    	out.print("<script>alert('해당 아이디의 비밀번호가 일치하지 않습니다.');location.href='/thearc/user/login';</script>");
    	out.flush();
//    	response.sendRedirect("/thearc/user/login");
    }
  }




@Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
	  
    HttpSession session = request.getSession();

    if (session.getAttribute(LOGIN) != null) {
      log.info("clear login data before");
      session.removeAttribute(LOGIN);
    }

    return true;
  }
}
*/
