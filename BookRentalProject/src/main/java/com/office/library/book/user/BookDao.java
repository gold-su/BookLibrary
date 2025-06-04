package com.office.library.book.user;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.office.library.book.BookVo;

//Bean 공급을 위한 애너테이션 설정
@Service
public class BookDao {
	//JdbcTemplate 클래스 자동 주입
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	//도서 검색
		public List<BookVo> selectBooksBySearch(BookVo bookVo){
			System.out.println("[BookDao selectBooks()");
			//검색 쿼리문
			String sql = "SELECT * FROM tbl_book WHERE b_name LIKE ? ORDER BY b_no DESC";
			//리턴할 List<BookVo> 객체
			List<BookVo> bookVos = null;
			
			try {
				//SELECT 쿼리문 실행
				bookVos = jdbcTemplate.query(
						sql, //인수 1. 쿼리문
						new RowMapper<BookVo>() {//인수 2. RowMapper 인터페이스
					@Override
					public BookVo mapRow (ResultSet rs, int rowNum) throws SQLException {
						BookVo bookVo = new BookVo();
						
						bookVo.setB_no(rs.getInt("b_no"));
						bookVo.setB_thumbnail(rs.getString("b_thumbnail"));
						bookVo.setB_name(rs.getString("b_name"));
						bookVo.setB_author(rs.getString("b_author"));
						bookVo.setB_publisher(rs.getString("b_publisher"));
						bookVo.setB_publisher(rs.getString("b_publish_year"));
						bookVo.setB_isbn(rs.getString("b_isbn"));
						bookVo.setB_call_number(rs.getString("b_call_number"));
						bookVo.setB_rantal_able(rs.getInt("b_rantal_able"));
						bookVo.setB_reg_date(rs.getString("b_reg_date"));
						bookVo.setB_mod_date(rs.getString("b_mod_date"));
						
						return bookVo;
					}
				},
						"%" + bookVo.getB_name()+"%");//인수 3. 검색조건
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			//검색한 내용이 존재하던 그 결과를, 아니면 null 변환
			return bookVos.size() > 0 ? bookVos : null;
		}
}
