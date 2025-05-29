package com.office.library.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller //애너테이션 추가 --> 프로젝트 실행 시 IoC 컨테이너에 Bean 객체로 생성
@RequestMapping("/admin")
public class AdminHomeController {
	@RequestMapping(value = {"","/"}, method = RequestMethod.GET)
	public String home() {
		//호출여부 확인 위한 출력문
		System.out.println("[AdminHomeController] home()");
		
		//다음 페이지로 "admin/home.jsp"호출
		String nextPage = "admin/home";
		return nextPage;
	}

}
