package com.office.library.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserHomeController {
	
	@GetMapping({"","/"})
	public String home() {
		System.out.println("[UserHomeController] home()");
		
		String nextPage = "user/home";
		
		return nextPage;
	}
	
	//로그인
	@GetMapping("/loginForm")
	public String loginForm() {
		System.out.println("[UserMemberController] loginForm()");
		
		//로그인 페이지로 이동
		String nextPage = "user/member/login_form";
		return nextPage;
	}
}
