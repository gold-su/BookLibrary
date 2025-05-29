package com.office.library.admin.member;

import java.security.SecureRandom;
import java.util.Date;
import java.util.List;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Service //IoC 컨테이너에 객체를 생성하기 위한 애너테이션
public class AdminMemberService {
	//리턴값 상수 선언
	final static public int ADMIN_ACCOUNT_ALREADY_EXIST = 0;
	final static public int ADMIN_ACCOUNT_CREATE_SUCCESS = 1;
	final static public int ADMIN_ACCOUNT_CREATE_FAIL = -1;

	
	//DAO 객체 자동주입
	@Autowired
	AdminMemberDao adminMemberDao;
	
	//메일보내기 기능
	@Autowired
	JavaMailSenderImpl javaMailSenderImpl;
	
	public int createAccountConfirm(AdminMemberVo adminMemberVo) {
		//호출 확인 위한 출력
		System.out.println("[AdminMemberService] createAccountConfirm()");
		
		//DAO 클래스에 아이디 중복검사 요청, 아이디 존재 --> TRUE, 아이디 미존재 --> fasle
		//boolean isMember = adminMemberDao.isAdminMember(adminMemberVo.getA_m_id());
//		boolean isMember = true;
		//DAO 클래스에 입력 요청 --> 입력성공 : 1, 입력실패 0
		//int result = adminMemberDap/insertAdminAccount(adminMemberVo);
		int result = 0;
		
		if(adminMemberDao.isAdminMember(adminMemberVo.getA_m_id())) {
			return ADMIN_ACCOUNT_ALREADY_EXIST;
		}else {
			//if(result > 0) return ADMIN_ACCOUNT_CREATE_SUCCESS;
			result = adminMemberDao.insertAdminAccount(adminMemberVo);
			return result > 0 ? ADMIN_ACCOUNT_CREATE_SUCCESS : ADMIN_ACCOUNT_CREATE_FAIL;
		}
	}
	
	public AdminMemberVo loginConfirm(AdminMemberVo adminMemberVo) {
		System.out.println("[AdminMemberService] loginConfirm()");
		
		AdminMemberVo loginedAdminMemberVo = adminMemberDao.selectAdmin(adminMemberVo);
		
		if(loginedAdminMemberVo != null)
			System.out.println("[AdminMemberService] ADMIN MEMBER LOGIN SUCCESS!!");
		else
			System.out.println("[AdminMemberService] ADMIN MEMBER LOGIN FALL!!");
		
		return loginedAdminMemberVo;
	}
	
	//관리자 목록을 List 형식으로 반환
	public List<AdminMemberVo> listupAdmin(){
		System.out.println("[AdminMemberService] listupAdmin()");
		return adminMemberDao.selectAdmins();
	}
	
	//일반관리자 승인 처리
	public void setAdminApproval(int a_m_no) {
		System.out.println("[adminMemberService] setAdminApproval()");
		
		int result = adminMemberDao.updateAdminAccount(a_m_no);

		if(result > 0) {
			System.out.println("[AdminMemberService] " + a_m_no + " is approved!");
		}
		else {
			System.out.println("[AdminMemberService] " + a_m_no + " is not approved!");
		}
	}
	
	//회원 정보 수정
	public int modifyAccountConfirm(AdminMemberVo adminMemberVo) {
		System.out.println("[AdminMemberService] modifyAccountConfirm()");
		//DAO 클래스에 정보 업데이트 요청, 리턴값은 변경된 레코드 수
		return adminMemberDao.updateAdminAccount(adminMemberVo);
	}
		
	//회원번호 이용 회원 정보 조회 --> 로그인 상태 유지 목적
	public AdminMemberVo getLoginedAdminMemberVo(int a_m_no) {
		System.out.println("[AdminMemberService] getLoginedUserMemberVo()");
			
		return adminMemberDao.selectAdmin(a_m_no);
	}
	
	//새 비밀번호 생성, 테이블에 업데이트 및 이메일 전송
		public int findPasswordConfirm(AdminMemberVo adminMemberVo) {
			System.out.println("[AdminMemberService] findPasswordConfirm()");
			
			//id, name, mail을 검색조건으로 하여 계정 조회 --> 본인 인증
			AdminMemberVo selectedAdminMemberVo
				=adminMemberDao.selectUser(adminMemberVo.getA_m_id(),
											adminMemberVo.getA_m_name(),
											adminMemberVo.getA_m_mail());
			int result = 0;
			if(selectedAdminMemberVo != null) {//해당 계정 존재
				//새로운 비밀번호 생성 후 , 테이블에 업데이트 실행
				String newPassword = createNewPassword();
				result = adminMemberDao.updatePassword(adminMemberVo.getA_m_id(), newPassword);


				
				//변경된 레코드가 존재하면 업데이트 성공 --> 메일로 새 비밀번호 전송
				if(result>0)
					sendNewPasswordByMail(adminMemberVo.getA_m_mail(), newPassword);

				
			}
			//변경된 레코드의 수 리턴 --> 변경 성공 여부 확인
			return result;
		}
		
		//난수발생기 이용 새 비밀번호 생성 --> 관리자 기능과 동일
		private String createNewPassword() {
			
			System.out.println("[AdminMemberService] createNewPassword()");
			//비밀번호의 구성요소로 사용할 문자 배열
			char[] chars = new char[] {
					'0','1','2','3','4','5','6','7','8','9',
					'a','b','c','d','e','f','g','h','i','j',

					'k','l','m','n','o','p','q','r','s','t',
					'u','v','w','x','y','z'
			};
			//난수발생기를 이용 8자의 새로운 비밀번호 조합
			StringBuffer stringBuffer = new StringBuffer();
			SecureRandom secureRandom = new SecureRandom();
			secureRandom.setSeed(new Date().getTime());
			int index = 0;
			int length = chars.length;
			for(int i = 0; i < 8; i++) {
				index = secureRandom.nextInt(length);
				if(index % 2 == 0)//짝수 위치 대문자
					stringBuffer.append(String.valueOf(chars[index]).toUpperCase());
				else //홀수 위치 소문자
					stringBuffer.append(String.valueOf(chars[index]).toLowerCase());
			}
			//생성된 비밀번호 콘솔 출력 및 리턴
			System.out.println("[AdminMemberService] NEW PASSWORD: "+stringBuffer.toString());
			return stringBuffer.toString();
		}
		
		//새 비밀번호 이메일 전송 --> 관리자 기능과 동일
		private void sendNewPasswordByMail(String toMailAddr, String newPassword) {
			System.out.println("[UserMemberService] sendNewPasswordByMail()");
			final MimeMessagePreparator mimeMessagePreparator = new MimeMessagePreparator() {
				
				@Override
				public void prepare(MimeMessage mimeMessage) throws Exception{
					final MimeMessageHelper mimeMessageHelper  // ✅
						= new MimeMessageHelper(mimeMessage, true, "UTF-8");
					mimeMessageHelper.setTo("pds020130@gmail.com"); //수신 가능한 개인 메일 주소
					mimeMessageHelper.setTo(toMailAddr);
					mimeMessageHelper.setSubject("[한국 도서관] 새 비밀번호 안내입니다.");
					mimeMessageHelper.setText("새 비밀번호 : " + newPassword, true);
					
				}
			};
			javaMailSenderImpl.send(mimeMessagePreparator); // ✅ 소문자 변수
		}
		
		
}
