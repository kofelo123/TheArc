package com.thearc.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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

@Slf4j
public class AdminAuthInterceptor extends HandlerInterceptorAdapter{

	private static final String ADMINLOGIN = "adminlogin";
	
	/** 
	 * 관리자 인터셉터 전처리 (로그인처리+관리자인증)
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HttpSession session = request.getSession();
		
		if(session.getAttribute("adminlogin") == null) {
			
			log.info("current user is not admin logined");
			
			response.sendRedirect("/thearc/error403");
		}
		
		return true;
	}
	
	
}
