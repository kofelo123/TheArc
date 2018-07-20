package com.thearc.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
  *
  * @author Jeongwon Heo
  * since 2018. 7. 20.
  * <pre>
  * << 개정이력(Modification Information) >>
  *
  *      수정일                   수정자                수정내용
  *  -----------    --------    ---------------------------
  *  2018. 7. 20.     허정원		 관리자 인터셉터 생성
  *
  * </pre>
  */
public class AdminAuthInterceptor extends HandlerInterceptorAdapter{

	private static final Logger logger = LoggerFactory.getLogger(AdminAuthInterceptor.class);
	
	private static final String ADMINLOGIN = "adminlogin";
	
	/** 
	 * 관리자 인터셉터 전처리 (로그인처리+관리자인증)
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HttpSession session = request.getSession();
		
		if(session.getAttribute("adminlogin") == null) {
			
			logger.info("current user is not admin logined");
			
			response.sendRedirect("/thearc/error403");
		}
		
		return true;
	}
	
	
}
