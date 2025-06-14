package com.office.library.user.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class UserMemberLoginInterceptor extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler)
			throws Exception{
		//로그인 상태 확인 
		HttpSession session = request.getSession(false);
		if(session != null) {
			//로그인 된 상태 --> true 반환 --> 핸들러 실행됨
			Object object = session.getAttribute("loginedUserMemberVo");
			
			if(object != null) return true;
		}
		
		//이곳에 도달하면 로그인되지 않은 상태 --> 로그인 폼으로 리다이렉트 & false 반환 --> 핸들러 실행되지 않음
		response.sendRedirect(request.getContextPath() + "/user/member/loginForm");
		
		return false;
	}

}
