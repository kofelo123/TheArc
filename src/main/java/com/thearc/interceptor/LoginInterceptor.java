package com.thearc.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.thearc.domain.UserVO;

public class LoginInterceptor extends HandlerInterceptorAdapter {

  private static final String LOGIN = "login";
  private static final Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);


 
 //로그인시 이곳에서 인터셉트
  @Override
  public void postHandle(HttpServletRequest request, 
      HttpServletResponse response, Object handler,
      ModelAndView modelAndView) throws Exception {

    HttpSession session = request.getSession();

    ModelMap modelMap = modelAndView.getModelMap();  
    Object userVO = modelMap.get("userVO");
  
	   
    if (userVO != null) {
    	
      logger.info("new login success");
      System.out.println(userVO);
      session.setAttribute(LOGIN, userVO);
     
           
      if (request.getParameter("useCookie") != null) {

        logger.info("remember me................");
        Cookie loginCookie = new Cookie("loginCookie", session.getId());
        loginCookie.setPath("/");
        loginCookie.setMaxAge(60 * 60 * 24 * 7);
        response.addCookie(loginCookie);
      }
      // response.sendRedirect("/");
      Object dest = session.getAttribute("dest");
      System.out.println("destTest:"+dest);///원래 가려고 했던 경로 (AuthInterceptoer로 부터 세션저장됨.)
      
      /*아래 ban체크용 */
      UserVO uvo=(UserVO) userVO;
      if(uvo.getAuthority().equals("ban")){
    	  session.removeAttribute(LOGIN);
   	   response.sendRedirect("/thearc/ban");
      }else//원래 아래 코드만 있었는데 sendredirect 두번 처리하는 에러떄문에 여기서 분기문을 둠.
      response.sendRedirect(dest != null ? (String)dest : "/thearc/"); //로그인성공시 이부분 작동
    }
  }


  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
	  
    HttpSession session = request.getSession();

    if (session.getAttribute(LOGIN) != null) {
      logger.info("clear login data before");
      session.removeAttribute(LOGIN);
    }

    return true;
  }
}
