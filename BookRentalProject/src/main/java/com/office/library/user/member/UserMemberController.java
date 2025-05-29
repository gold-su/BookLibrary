package com.office.library.user.member;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user/member")
public class UserMemberController {
	//UserMemberService 객체 자동 주입
	@Autowired
	UserMemberService userMemberService;
	
	//회원가입
	@GetMapping("/createAccountForm")
	public String createAccountForm() {
		System.out.println("[UserMemberController] createAccountForm()");
		
		//회원가입 폼 지정
		String nextPage = "user/member/create_account_form";
		return nextPage;
	}
	//회원가입 확인
	@PostMapping("/createAccountConfirm")
	public String createAccountConfirm(UserMemberVo userMemberVo) {
		System.out.println("[UserMemberController] createAccountConfirm()");
		
		String nextPage = "user/member/create_account_ok";
		//UserMemberService 클래스 호출 회원가입 및 결과 요청, 0/-1 --> 실패 , 1--> 성공
		int result = userMemberService.createAccountConfirm(userMemberVo);
		
		if(result <= 0)
			nextPage = "user/member/create_account_ng";
		return nextPage;
	}
	
	//로그인 확인
	@PostMapping("/loginConfirm")
	public String loginConfirm(UserMemberVo userMemberVo, HttpSession session) {
		System.out.println("[UserMemberController] loginConfirm()");
		
		String nextPage = "user/member/login_ok";
		//Service 클래스에 로그인 확인 요청 --> 리턴값은 로그인된 사용자 정보
		UserMemberVo loginedUserMemberVO = userMemberService.loginConfirm(userMemberVo);   //ppt 40부터
		
		//로그인된 사용자 정보의 유무에 따라 로그인 성공여부 판단
		if(loginedUserMemberVO == null) {
			nextPage = "user/member/login_ng";
		}else {
			session.setAttribute("loginedUserMemberVo", loginedUserMemberVO);
			session.setMaxInactiveInterval(60*30);
		}
		return nextPage;
	}
	
	//로그아웃 --> Session을 비활성화 처리하면 됨
	@GetMapping("/logoutConfirm")
	public String logoutConfirm(HttpSession session) {
		System.out.println("[UserMemberController] logoutConfirm()");
		
		//로그아웃 후 "/'로 리다이렉트 --> 사용자 홈 페이지로 이동
		String nextPage = "redirect:/";
		
		//세션 비활성화 --> 로그아웃 처리
		session.invalidate();
		
		return nextPage;
	}
	//로그안
	@GetMapping("/loginForm")
	public String loginForm() {
		System.out.println("[UserMemberController] loginForm()");
		
		//로그인 페이지로 이동
		String nextPage = "user/member/login_form";
		return nextPage;
	}
	//계정 수정
	@GetMapping("/modifyAccountForm")
	public String modifyAccountForm(HttpSession session) {
		System.out.println("[UserMemberController] modifyAccountForm()");
		
		//계정수정 페이지로 이동
		String nextPage = "user/member/modify_account_form";
		
		//세션에 저장된 정보를 이용하여 로그인 상태 확인 --> 비정상 접속 및 장시간 유휴상황 대비
		UserMemberVo loginedUserMemberVo
				= (UserMemberVo) session.getAttribute("loginedUserMemberVo");
		if(loginedUserMemberVo == null)
			nextPage = "redirect:/user/member/loginForm";
		
		return nextPage;
	}
	
	@PostMapping("/modifyAccountConfirm")
	public String modifyAccountConfirm(UserMemberVo userMemberVo, HttpSession session) {
		System.out.println("[UserMemberController] modifyAccountConfirm()");
		
		String nextPage = "user/member/modify_account_ok";
		
		//서비스 클래스의 modifyAccountConfirm() 메서드 호출 --> 정보 변경 처리
		int result = userMemberService.modifyAccountConfirm(userMemberVo);
		
		if(result > 0) { //리턴값 1 --> 계정 수정 성공, 로그인 상태 유지 위채 정보 조회 및 세션 저장
			UserMemberVo loginedUserMemberVO
			= userMemberService.getLoginedUserMemberVo(userMemberVo.getU_m_no());
			
			session.setAttribute("loginedUserMemberVo", loginedUserMemberVO);
			session.setMaxInactiveInterval(60 * 30);
			
		}
		else {
			//계정 수정 실패
			nextPage = "user/member/modify_account_ng";
		}
		return nextPage;
	}
	//새 비밀번호
	@GetMapping("/findPasswordForm")
	public String findPasswordForm() {
		System.out.println("[UserMemberController] findPasswordForm()");
		
		//비밀번호 찾기 폼으로 이동
		String nextPage = "user/member/find_password_form";
		return nextPage;
	}
	
	//새 비밀번호 확인
	@PostMapping("/findPasswordConfirm") // ✅
	public String findPasswordConfirm(UserMemberVo userMemberVo) {
		System.out.println("[UserMemberController] findPasswordConfirm");
		
		String nextPage = "user/member/find_password_ok";
		
		//사용자 입력 정보를 UserMemberService 클래스에 넘겨 새 비밀번호 생성 요청
		int result = userMemberService.findPasswordConfirm(userMemberVo);
		
		//변경된 레코드의 수가 1이면 새 비밀번호 생성 성공
		if(result <= 0)
			nextPage = "user/member/find_password_ng";
		
		return nextPage;
	}
}
