package com.office.library.user.member;

import java.security.SecureRandom;
import java.util.Date;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;


@Service
public class UserMemberService {
	//회원가입 리턴값
	final static public int USER_ACCOUNT_ALREADY_EXIST = 0;
	final static public int USER_ACCOUNT_CREATE_SUCCESS = 1;
	final static public int USER_ACCOUNT_CREATE_FALL = -1;
	
	//DAO 객체 자동 주입
	@Autowired
	UserMemberDao userMemberDao;
	
	//메일전송 객체 자동주입 --> 새 비밀번호 메일전송에 필요
	@Autowired
	JavaMailSenderImpl javaMailSenderImpl;

	
	//회원가입 요청
	public int createAccountConfirm(UserMemberVo userMemberVo) {
		System.out.println("[UserMemberService] createAccountConfirm()");
		//id 중복 검사 --> 해당 id의 계정 존재 확인
		boolean isMember = userMemberDao.isUserMember(userMemberVo.getU_m_id());
		
		if(!isMember) {
			//회원가입 처리, 리턴값은 변경 레코드의 수
			int result = userMemberDao.insertUserAccount(userMemberVo);
			
			if(result > 0) //회원가입 성공
				return USER_ACCOUNT_CREATE_SUCCESS;
			else //회원가입 실패 --> 자료 불일치
				return USER_ACCOUNT_CREATE_FALL;
		}
		else {
			//회원가입 실패 --> 동일한 계정 존재
			return USER_ACCOUNT_ALREADY_EXIST;
		}
	}
	
	//로그인 정보에 해당하는 계정 존재 확인
	public UserMemberVo loginConfirm(UserMemberVo userMemberVo) {
		System.out.println("[UserMemberService] loginConfirm()");
		
		//UserMemberDao 클래스에 요청하여 DB에 해당 계정정보를 SELECT
		UserMemberVo loginedUserMemberVo = userMemberDao.selectUser(userMemberVo);
		
		if(loginedUserMemberVo != null)
			System.out.println("[UserMemberService] USER MEMBER LOGIN SUCCESS!!");
		
		else
			System.out.println("[UserMemberService] USER MEMBER LOGIN FALL!!");
		
		//SELEC된 계정정보를 리턴해 줌, 게정 존재 -- > 로그인 성공, NULL --> 로그인 실패
		return loginedUserMemberVo;
	}
	//회원 정보 수정
	public int modifyAccountConfirm(UserMemberVo userMemberVo) {
		System.out.println("[UserMemberService] modifyAccountConfirm()");
		//DAO 클래스에 정보 업데이트 요청, 리턴값은 변경된 레코드 수
		return userMemberDao.updateUserAccount(userMemberVo);
	}
	
	//회원번호 이용 회원 정보 조회 --> 로그인 상태 유지 목적
	public UserMemberVo getLoginedUserMemberVo(int u_m_no) {
		System.out.println("[UserMemberService] getLoginedUserMemberVo()");
		
		return userMemberDao.selectUser(u_m_no);
	}
	
	//새 비밀번호 생성, 테이블에 업데이트 및 이메일 전송
	public int findPasswordConfirm(UserMemberVo userMemberVo) {
		System.out.println("[UserMemberService] findPasswordConfirm()");
		
		//id, name, mail을 검색조건으로 하여 계정 조회 --> 본인 인증
		UserMemberVo selectedUserMemberVo
			=userMemberDao.selectUser(userMemberVo.getU_m_id(),
										userMemberVo.getU_m_name(),
										userMemberVo.getU_m_mail());
		int result = 0;
		if(selectedUserMemberVo != null) {//해당 계정 존재
			//새로운 비밀번호 생성 후 , 테이블에 업데이트 실행
			String newPassword = createNewPassword();
			result = userMemberDao.updatePassword(userMemberVo.getU_m_id(), newPassword);


			
			//변경된 레코드가 존재하면 업데이트 성공 --> 메일로 새 비밀번호 전송
			if(result>0)
				sendNewPasswordByMail(userMemberVo.getU_m_mail(), newPassword);

			
		}
		//변경된 레코드의 수 리턴 --> 변경 성공 여부 확인
		return result;
	}
	
	//난수발생기 이용 새 비밀번호 생성 --> 관리자 기능과 동일
	private String createNewPassword() {
		
		System.out.println("[UserMemberService] createNewPassword()");
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
		System.out.println("[UserMemberService] NEW PASSWORD: "+stringBuffer.toString());
		return stringBuffer.toString();
	}
	
	//새 비밀번호 이메일 전송 --> 관리자 기능과 동일
	private void sendNewPasswordByMail(String toMailAddr, String newPassword) {
		System.out.println("[AdminMemberService] sendNewPasswordByMail()");
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
