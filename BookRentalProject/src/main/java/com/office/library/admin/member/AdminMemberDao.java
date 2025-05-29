package com.office.library.admin.member;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.office.library.user.member.UserMemberVo;

//IoC 컨테이너에 빈(Bean) 객체를 생성하기 위한 애너테이션
@Component
public class AdminMemberDao {

	//아직 데이터베이스를 구축하지 않았기에 DAO 클래스의 소스코드 동작은 불가
	//내용이 비어있는 클래스로 선언
	//그러나 Service 객체에서 AdminMemberDao 객체를 자동주입할 수 있음
	@Autowired
	JdbcTemplate jdbcTemplate;
	//암호화 객체 자동주입
	//BCryptPaawordEndoer는 PasswordEncoder 인터페이스를 구현한 클래스
	@Autowired
	PasswordEncoder passwordEncoder;
	
	
	
	//id 중복검사, 존재하면 true, 존재하지 않으면 false 반환
	public boolean isAdminMember(String a_m_id) {
		//호출여부 확인 위한 출력문
		System.out.println("[AdminMemberDao] isAdminMember()");
		
		//입력된 관리자 아이디에 해당하는 자료의 갯수를 검색
		//queryForObject() 입력 : sql -> 실행쿼리문, Integer.class -> 반환형, a_m_id->입력 id
		String sql = "SELECT COUNT(*) FROM tbl_admin_member WHERE a_m_id = ?";
		int result = jdbcTemplate.queryForObject(sql, Integer.class, a_m_id);
		
		return result > 0 ? true : false;
	}
	
	public int insertAdminAccount(AdminMemberVo adminMemberVo) {
		// 호출여부 확인 위한 출력문
		System.out.println("[AdminMemberDao] insertAdminAccount()");
		
		//데이터베이스 입력을 위한 값(values) --> List로 구현한 후 Array로 전달
		List<String> args = new ArrayList<String>() ;
		
		//쿼리몬 생성 및 args 추가
		String sql = "INSERT INTO tbl_admin_member(";
		//"super admin"은 미리 승인, 일반관리자는 미승인상태(테이블 생성 시 기본값을 0으로 설정)
		if(adminMemberVo.getA_m_id().equals("super admin")) {
			sql += "a_m_approval, ";
			args.add("1");
		}
		
		//입력으로 전달된 값을 추가
		sql += "a_m_id, ";
		args.add(adminMemberVo.getA_m_id());
		sql += "a_m_pw, ";
		args.add(passwordEncoder.encode(adminMemberVo.getA_m_pw()));
		sql += "a_m_name, ";
		args.add(adminMemberVo.getA_m_name());
		sql += "a_m_gender, ";
		args.add(adminMemberVo.getA_m_gender());
		sql += "a_m_part, ";
		args.add(adminMemberVo.getA_m_part());
		sql += "a_m_position, ";
		args.add(adminMemberVo.getA_m_position());
		sql += "a_m_mail, ";
		args.add(adminMemberVo.getA_m_mail());
		sql += "a_m_phone, ";
		args.add(adminMemberVo.getA_m_phone());
		sql += "a_m_reg_date, a_m_mod_date) ";
		
		//super admin"은 입력값이 9개, 일반관리자는 8개 (승인여부)
		if(adminMemberVo.getA_m_id().equals("super admin")) {
			sql += " VALUES(?,?,?,?,?,?,?,?,?, NOW(), NOW())";
		}
		else {
			sql += " VALUES(?,?,?,?,?,?,?,?, NOW(), NOW())";
		}
		
		//jdbcTemplate.update()는 데이터 갱신 (INSERT, UPDATE, DELETE)에 사용
		//						 반환값은 변경된 행의 갯수
		int result = 0;
		try {
			result = jdbcTemplate.update(sql,args.toArray());
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}	
	
	//특정 id의 관리자 정보를 VO 클래스 형식으로 반환
	//jdbcTemplate.query() 메서드는 SELECT 쿼리를 실행
	public AdminMemberVo selectAdmin(AdminMemberVo adminMemberVo) {
		System.out.println("[AdminMemberDao] selectAdmin()");
		
		String sql = "SELECT * FROM tbl_admin_member "
				+ "WHERE a_m_id = ? AND a_m_approval > 0";
		
		List<AdminMemberVo> adminMemberVos = new ArrayList<AdminMemberVo>();
		
		try {
			adminMemberVos = jdbcTemplate.query(
					sql, //입력 1. 실행할 쿼리문
					new RowMapper<AdminMemberVo>() {//입력 2. RowMapper<T> 인터페이스
				//mapRow() 메서드 구현 --> ResultSet으로 반환된 결과를 자바 클래스 형식으로 변환
				@Override
				public AdminMemberVo mapRow(ResultSet rs, int rowNum) throws SQLException {
					AdminMemberVo adminMemberVo = new AdminMemberVo();
					
					//매핑 작업
					adminMemberVo.setA_m_no(rs.getInt("a_m_no"));
					adminMemberVo.setA_m_approval(rs.getInt("a_m_approval"));
					adminMemberVo.setA_m_id(rs.getString("a_m_id"));
					adminMemberVo.setA_m_pw(rs.getString("a_m_pw"));
					adminMemberVo.setA_m_name(rs.getString("a_m_name"));
					adminMemberVo.setA_m_gender(rs.getString("a_m_gender"));
					adminMemberVo.setA_m_part(rs.getString("a_m_part")); 
					adminMemberVo.setA_m_position(rs.getString("a_m_position")); 
					adminMemberVo.setA_m_mail(rs.getString("a_m_mail"));
					adminMemberVo.setA_m_phone(rs.getString("a_m_phone"));
					adminMemberVo.setA_m_reg_date(rs.getString("a_m_reg_date"));
					adminMemberVo.setA_m_mod_date(rs.getString("a_m_mod_date"));
					
					return adminMemberVo;
				}
			},
			adminMemberVo.getA_m_id()); //입력 3. 조회할 조건
			
			System.out.println("adminMemberVos.size(): " + adminMemberVos.size());
			//입력된 비밀번화 select된 첫번째 레코드(id는 기본키)의 비밀번호 비교
			if(adminMemberVos.size()> 0) {
				
				 System.out.println("입력된 비밀번호 (원문): " + adminMemberVo.getA_m_pw());
				 System.out.println("DB에 저장된 암호화된 비밀번호: " + adminMemberVos.get(0).getA_m_pw());
				 System.out.println("비밀번호 일치 여부: " +
				        passwordEncoder.matches(adminMemberVo.getA_m_pw(), adminMemberVos.get(0).getA_m_pw()));
				
				
				if(!passwordEncoder.matches(adminMemberVo.getA_m_pw(), adminMemberVos.get(0).getA_m_pw()))
					adminMemberVos.clear();
			}				
		}catch(Exception e) {
			e.printStackTrace();
		}
		return adminMemberVos.size() > 0 ? adminMemberVos.get(0) : null;
	}
	
	//관리자 목록 반환
	public List<AdminMemberVo> selectAdmins(){
		System.out.println("[AdminMemberDao] selectAdmins()");
		
		String sql = "SELECT * FROM tbl_admin_member";
		
		List<AdminMemberVo> adminMemberVos = new ArrayList<AdminMemberVo>();
		
		try {
			adminMemberVos = jdbcTemplate.query(sql, new RowMapper<AdminMemberVo>() {
				@Override
				public AdminMemberVo mapRow(ResultSet rs, int rowNum) throws SQLException {
					AdminMemberVo adminMemberVo = new AdminMemberVo();
					
					adminMemberVo.setA_m_no(rs.getInt("a_m_no"));
					adminMemberVo.setA_m_approval(rs.getInt("a_m_approval"));
					adminMemberVo.setA_m_id(rs.getString("a_m_id"));
					adminMemberVo.setA_m_pw(rs.getString("a_m_pw"));
					adminMemberVo.setA_m_name(rs.getString("a_m_name"));
					adminMemberVo.setA_m_gender(rs.getString("a_m_gender"));
					adminMemberVo.setA_m_part(rs.getString("a_m_part"));
					adminMemberVo.setA_m_position(rs.getString("a_m_position"));
					adminMemberVo.setA_m_mail(rs.getString("a_m_mail"));
					adminMemberVo.setA_m_phone(rs.getString("a_m_phone"));
					adminMemberVo.setA_m_reg_date(rs.getString("a_m_reg_date"));
					adminMemberVo.setA_m_mod_date(rs.getString("a_m_mod_date"));
					
					return adminMemberVo;
				}
			}); //전체 목록을 조회하기에 검색조건(입력 3)은 없음
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return adminMemberVos;
	}
	
	//MariaDB에 최고관리자 승인 처리 : 승인필드 0 --> 1
	//jdbcTemplate.update() 메서드는 INSERT, UPDATE, DELETE 쿼리문 실행 가능
	public int updateAdminAccount(int a_m_no) {
		System.out.println("[AdminMemberDAO] updateAdminAccount()");
		
		//승인처리를 위해 a_m_approval 필드 값을 변경 : 0 --> 1
		String sql = "UPDATE tbl_admin_member SET a_m_approval = 1 WHERE a_m_no = ?";

		
		int result = -1;
		
		try {
			result = jdbcTemplate.update(sql, a_m_no);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	//계정 정보 수정
	public int updateAdminAccount(AdminMemberVo adminMemberVo) {
		System.out.println("[AdminMemberDao] updateAdminAccount()");
			
		String sql = "UPDATE tbl_admin_member SET "
					+ "a_m_name = ?, "
					+ "a_m_gender = ?, "
					+ "a_m_part = ?, "
					+ "a_m_position = ?, "
					+ "a_m_mail = ?, "
					+"a_m_phone = ?, "
					+"a_m_mod_date = NOW() "
					+"WHERE a_m_no = ?";
		int result = -1;
		try {
			result = jdbcTemplate.update(sql,
					adminMemberVo.getA_m_name(),
					adminMemberVo.getA_m_gender(),
					adminMemberVo.getA_m_part(),
					adminMemberVo.getA_m_position(),
					adminMemberVo.getA_m_mail(),
					adminMemberVo.getA_m_phone(),
					adminMemberVo.getA_m_no());
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return result;
		}

	public AdminMemberVo selectAdmin(int a_m_no) {
		System.out.println("[AdminMemberDao] selectAdmin()");
		String sql = "SELECT * FROM tbl_admin_member WHERE a_m_no = ?";
		List<AdminMemberVo> adminMemberVos = new ArrayList<AdminMemberVo>();
		try {
			adminMemberVos = jdbcTemplate.query(
					sql, //1.select 쿼리문 
					new RowMapper<AdminMemberVo>(){//2.RowMapper<T> 인터페이스 : ResultSet --> VO
						@Override
						public AdminMemberVo mapRow(ResultSet rs, int rowNum) throws SQLException{
							AdminMemberVo adminMemberVo = new AdminMemberVo();
							
							adminMemberVo.setA_m_no(rs.getInt("a_m_no"));
							adminMemberVo.setA_m_id(rs.getString("a_m_id"));
							adminMemberVo.setA_m_pw(rs.getString("a_m_pw"));
							adminMemberVo.setA_m_name(rs.getString("a_m_name"));
							adminMemberVo.setA_m_gender(rs.getString("a_m_gender"));
							adminMemberVo.setA_m_part(rs.getString("a_m_part"));
							adminMemberVo.setA_m_position(rs.getString("a_m_position"));
							adminMemberVo.setA_m_mail(rs.getString("a_m_mail"));
							adminMemberVo.setA_m_phone(rs.getString("a_m_phone"));
							adminMemberVo.setA_m_reg_date(rs.getString("a_m_reg_date"));
							adminMemberVo.setA_m_mod_date(rs.getString("a_m_mod_date"));
							
							return adminMemberVo;
						}
					},
					a_m_no); //3.where 검색 조건
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return adminMemberVos.size()>0? adminMemberVos.get(0) : null;
	}

	//id, name, mail 조건 계정정보 조회 --> 새 비밀번호 생성 본인 인증 목적
	public AdminMemberVo selectUser(String a_m_id, String a_m_name, String a_m_mail) {
		System.out.println("[AdminMemberDao] selectAdmin()");
		
		String sql = "SELECT * FROM  tbl_admin_member "
				+ "WHERE a_m_id = ? AND a_m_name = ? AND a_m_mail = ?";
		List<AdminMemberVo> adminMemberVos = new ArrayList<AdminMemberVo>();
		try {
			adminMemberVos = jdbcTemplate.query(sql,  //1.select 쿼리문
					new RowMapper<AdminMemberVo>() {	//2.RowMapper<T> 인터페이스
				@Override 
				public AdminMemberVo mapRow(ResultSet rs, int rowNum) throws SQLException {
					AdminMemberVo adminMemberVo = new AdminMemberVo();
					
					adminMemberVo.setA_m_no(rs.getInt("a_m_no"));

					adminMemberVo.setA_m_id(rs.getString("a_m_id"));

					adminMemberVo.setA_m_pw(rs.getString("a_m_pw"));

					adminMemberVo.setA_m_name(rs.getString("a_m_name"));

					adminMemberVo.setA_m_gender(rs.getString("a_m_gender"));

					adminMemberVo.setA_m_part(rs.getString("a_m_part"));
					
					adminMemberVo.setA_m_position(rs.getString("a_m_position"));
					
					adminMemberVo.setA_m_mail(rs.getString("a_m_mail"));
					
					adminMemberVo.setA_m_phone(rs.getString("a_m_phone"));
					
					adminMemberVo.setA_m_reg_date(rs.getString("a_m_reg_date"));
					
					adminMemberVo.setA_m_mod_date(rs.getString("a_m_mod_date"));
					
					return adminMemberVo;
				}
				
			},
					a_m_id, a_m_name, a_m_mail //3. where 검색 조건
					
					);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return adminMemberVos.size() > 0 ? adminMemberVos.get(0) : null;
	}
	

	//새 비밀번호 테이블에 등록
	public int updatePassword(String a_m_id, String newPassword) {
		System.out.println("[AdminMemberDao] updatePssword()");
		//UPDATE 쿼리문
		String sql = "UPDATE tbl_admin_member SET "
				+ "a_m_pw = ?, a_m_mod_date = NOW() "
				+ "where a_m_id = ?";
		
		int result = -1;
		
		//update 실행, 리턴값은 변경된 레코드 수
		try {
			result =jdbcTemplate.update(sql, passwordEncoder.encode(newPassword), a_m_id);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return result;
		
	}
}
