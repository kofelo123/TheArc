package com.thearc.interceptor;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;

import com.mysql.fabric.Response;
import com.thearc.domain.UserVO;
import com.thearc.service.UserService;

public class AuthInterceptor extends HandlerInterceptorAdapter {

  private static final Logger logger = LoggerFactory.getLogger(AuthInterceptor.class);
  
  @Inject
  private UserService service;
  
  @Override
  public boolean preHandle(HttpServletRequest request,
      HttpServletResponse response, Object handler) throws Exception {
    
    HttpSession session = request.getSession();   
  
    if(session.getAttribute("login") == null){
    
      logger.info("current user is not logined");
      
      saveDest(request);
      
      Cookie loginCookie = WebUtils.getCookie(request, "loginCookie");
      
      if(loginCookie != null) { 
        
        UserVO userVO = service.checkLoginBefore(loginCookie.getValue());
        
        logger.info("USERVO: " + userVO);
        
        if(userVO != null){
          session.setAttribute("login", userVO);
          
          authorization(request,response);
          return true;
        }
        
      }

      response.sendRedirect("/user/login");
      return false;
    }
    authorization(request,response);

    return true;
  }  
  

  private void saveDest(HttpServletRequest req) {///인증필요에 의한 로그인 이후 가려했던 목적지 저장위한것.

    String uri = req.getRequestURI();

    String query = req.getQueryString();

    if (query == null || query.equals("null")) {
      query = "";
    } else {
      query = "?" + query;
    }

    if (req.getMethod().equals("GET")) {
      logger.info("dest: " + (uri + query));
      req.getSession().setAttribute("dest", uri + query);
    }
  }

  private void authorization(HttpServletRequest request,HttpServletResponse response) throws Exception{
	
	  	String[] url = request.getRequestURI().split("/");
	    String pathName = url[url.length-1];
	    System.out.println("Test:"+pathName); //마지막 path값 얻어오기 -> 이걸로 권한 체크할것..
	    HttpSession session = request.getSession();
	    
	    if(pathName.equals("supporter")){
	    	UserVO vo = (UserVO) session.getAttribute("login");
	    	if(vo.getAuthority().equals("user")){
	    		response.sendRedirect("/error403");
	    	}
	    		
	    }
	    
	    pathName=url[url.length-2]+"/"+url[url.length-1];
	    if(pathName.equals("register/notice")){
	    	UserVO vo = (UserVO) session.getAttribute("login");
	    	if(vo.getAuthority().equals("user")||vo.getAuthority().equals("supporter")){
	    		response.sendRedirect("/error403");
	    	}
	    		
	    }
	    
	  
	  
  }

//  @Override
//  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//
//    HttpSession session = request.getSession();
//
//    if (session.getAttribute("login") == null) {
//
//      logger.info("current user is not logined");
//
//      saveDest(request);
//      
//      response.sendRedirect("/user/login");
//      return false;
//    }
//    return true;
//  }
}

// if(session.getAttribute("login") == null){
//
// logger.info("current user is not logined");
// response.sendRedirect("/user/login");
// return false;
// }
