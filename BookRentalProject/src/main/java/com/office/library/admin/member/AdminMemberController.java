package com.office.library.admin.member;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.office.library.user.member.UserMemberVo;

@Controller
@RequestMapping("/admin/member")
public class AdminMemberController {
	
	//Service 클래스 자동 주입
	@Autowired
	AdminMemberService adminMemberService;
	
	
	@RequestMapping(value = {"","/"}, method = RequestMethod.GET)
	public String home() {
		//효율여부 확인 위한 출력문
		System.out.println("[AdmingHomeController] home()");
		
		//다음 페이지로 "admin/home.jsp" 호출
		String nextPage = "admin/home";
		return nextPage;
	}
	//RequestMapping(value = "/loginForm", method = RequestMethod.GET)
	@GetMapping("/loginForm")
	public String loginForm() {
		System.out.println("[AdminMemberController] loginFomr()");
		
		String nextPage = "admin/member/login_form";
		
		return nextPage;
	}
	
	@PostMapping("/loginConfirm")
	public String loginConfirm(AdminMemberVo adminMemberVo,HttpSession session) {
		System.out.println("[AdminMemberController] loginConfirm()");

		
		//AdminHomeController의 home() 호출 --> admin/home.jsp 응답
		String nextPage = "admin/member/login_ok";
		
		AdminMemberVo loginedAdminMemberVo = adminMemberService.loginConfirm(adminMemberVo);
		if(loginedAdminMemberVo == null) {
			nextPage = "admin/member/login_ng";
		}
		else {
			//로그인에 성공하면 계정정보르 세션에 저장, 지속시간은 30분
			session.setAttribute("loginedAdminMemberVo", loginedAdminMemberVo);
			session.setMaxInactiveInterval(60 * 30);
		}
		
		return nextPage;
		
	}
	
	//로그아웃 확인
	//@RequestMapping(value = "/logoutConfirm",method = RequestMethod.GET)
	@GetMapping("/logoutConfirm")
	public String logoutConfirm(HttpSession session) {
		System.out.println("[AdminMemberController] logoutConfirm()");
		
		//AdminHomeController의 home() 호출 --> admin/home.jsp 응답
		String nextPage = "redirect:/admin";
		
		session.invalidate();
		
		return nextPage;
	}
	
	
	
	//회원가입 처리 루틴
	@RequestMapping(value="/createAccountForm", method = RequestMethod.GET)
	public String createAccountForm() {
		//호출 확인 위한 출력문
		System.out.println("[AdminMemberConrtoller] createAccountForm()");
		
		//다음에 호출한 jsp 페이지 지정
		String nextPage = "admin/member/create_account_form";
		return nextPage;
	}
	
	@RequestMapping(value="/createAccountConfirm", method=RequestMethod.POST)
	public String createAccountConfirm(AdminMemberVo adminMemberVo) {
		//호출 확인 위한 출력문
		System.out.println("[AdminMemberController] createAccountConfirm()");
		//데이터 전달여부 확인을 위한 출력문
				System.out.println("a_m_id : "+ adminMemberVo.getA_m_id());
				
		//Service 클래스 호출
		int result = adminMemberService.createAccountConfirm(adminMemberVo);
		
		//다음 이동할 jsp 페이지 지정
		String nextPage = "admin/member/create_account_ok";
		//회원가입 실패
		if(result<=0)nextPage = "admin/member/create_account_ng";
		
		return nextPage;
	}
	
	//관리자 목록(ModelAndView 사용)
	//@RequestMapping(value = "/listipAdmin", method = RequestMethod.GET)
	@GetMapping("/listupAdmin")
	public ModelAndView listupAdmin() {
		System.out.println("[AdminMemberController] modifyAccountConfirm()");
		
		String nextPage = "admin/member/listup_admins";
		
		List<AdminMemberVo> adminMemberVos = adminMemberService.listupAdmin();
		
		ModelAndView modelAndView = new ModelAndView(); //ModelAndView 객체를 생성
		modelAndView.setViewName(nextPage);				//ModelAndView 뷰를 설정
		modelAndView.addObject("adminMemberVos", adminMemberVos); //ModelAndView에 데이터를 추가
		
		return modelAndView; 							//modelAndView를 반환
		
	}
	
	//관리자 승인
	@GetMapping("/setAdminApproval")
	public String setAdminApproval(@RequestParam("a_m_no") int a_m_no) {
		System.out.println("[AdminMemberController] setAdminApproval()");
		
		String nextPage = "redirect:/admin/member/listupAdmin";
		
		adminMemberService.setAdminApproval(a_m_no);
		
		return nextPage;
	}
	
	//계정 수정
	@GetMapping("/modifyAccountForm")
	public String modifyAccountForm(HttpSession session) {
		System.out.println("[AdminMemberController] modifyAccountForm()");
			
		//계정수정 페이지로 이동
		String nextPage = "admin/member/modify_account_form";
		//세션에 저장된 정보를 이용하여 로그인 상태 확인 --> 비정상 접속 및 장시간 유휴상황 대비
		AdminMemberVo loginedUserMemberVo
				= (AdminMemberVo) session.getAttribute("loginedAdminMemberVo");
		
		//현재 로그인 되어있지 않은 상황에서는 로그인 페이지로 이동
		if(loginedUserMemberVo == null)
			nextPage = "redirect:/admin/member/loginForm";
		//관리자 회원정보 수정 페이지	
		return nextPage;
	}
	

	@PostMapping("/modifyAccountConfirm")
	public String modifyAccountConfirm(AdminMemberVo adminMemberVo, HttpSession session) {
		System.out.println("[AdminMemberController] modifyAccountConfirm()");
		
		String nextPage = "admin/member/modify_account_ok";
		
		//서비스 클래스의 modifyAccountConfirm() 메서드 호출 --> 정보 변경 처리
		int result = adminMemberService.modifyAccountConfirm(adminMemberVo);
		
		if(result > 0) { //리턴값 1 --> 계정 수정 성공, 로그인 상태 유지 위채 정보 조회 및 세션 저장
			AdminMemberVo loginedAdminMemberVO 
				= adminMemberService.getLoginedAdminMemberVo(adminMemberVo.getA_m_no());
			
			session.setAttribute("loginedAdminMemberVo", loginedAdminMemberVO);
			session.setMaxInactiveInterval(60 * 30);
			
		}
		else {
			//계정 수정 실패
			nextPage = "admin/member/modify_account_ng";
		}
		return nextPage;
	}

	//새 비밀번호
	@GetMapping("/findPasswordForm")
	public String findPasswordForm() {
		System.out.println("[AdminMemberController] findPasswordForm()");
		
		//비밀번호 찾기 폼으로 이동
		String nextPage = "admin/member/find_password_form";
		return nextPage;
	}
	
	//새 비밀번호 확인
	@PostMapping("/findPasswordConfirm") // ✅
	public String findPasswordConfirm(AdminMemberVo adminMemberVo) {
		System.out.println("[AdminMemberController] findPasswordConfirm");
		
		String nextPage = "admin/member/find_password_ok";
		
		//사용자 입력 정보를 UserMemberService 클래스에 넘겨 새 비밀번호 생성 요청
		int result = adminMemberService.findPasswordConfirm(adminMemberVo);
		
		//변경된 레코드의 수가 1이면 새 비밀번호 생성 성공
		if(result <= 0)
			nextPage = "admin/member/find_password_ng";
		
		return nextPage;
	}	
}
