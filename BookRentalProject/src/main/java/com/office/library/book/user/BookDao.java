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
						bookVo.setB_publish_year(rs.getString("b_publish_year"));
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
		
		//글번호를 검색조건으로 SELECT 쿼리문을 실행하여 하나의 레코드를 받아옴
		public BookVo selectBook(int b_no) {
			System.out.println("[BookDao] selectBook()");
			//쿼리문
			String sql = "select * from tbl_book where b_no = ?";
			
			List<BookVo> bookVos = null;
			
			try {
				//쿼리문을 실행하야 결과를 List<T>에 받음
				bookVos = jdbcTemplate.query(
						sql,//1. 쿼리문
						new RowMapper<BookVo>() {
							//2.RowMapper 인터페이스
							 @Override
							 public BookVo mapRow(ResultSet rs, int rowNum)throws SQLException{
								 BookVo bookVo = new BookVo();
								 
								 bookVo.setB_no(rs.getInt("b_no"));
								 bookVo.setB_thumbnail(rs.getString("b_thumbnail"));
								 bookVo.setB_name(rs.getString("b_name"));
								 bookVo.setB_author(rs.getString("b_author"));
								 bookVo.setB_publisher(rs.getString("b_publisher"));
								 bookVo.setB_publish_year(rs.getString("b_publish_year"));
								 bookVo.setB_isbn(rs.getString("b_isbn"));
								 bookVo.setB_call_number(rs.getString("b_call_number"));
								 bookVo.setB_rantal_able(rs.getInt("b_rantal_able"));
								 bookVo.setB_reg_date(rs.getString("b_reg_date"));
								 bookVo.setB_mod_date(rs.getString("b_mod_date"));
								 		
								 return bookVo;
							 }
						},
						b_no);//3. 검색조건
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			//도서번호는 기본키이고, 제목을 클릭하여 인수로 전달받기에, 조회 결과는 오로지 하나
			return bookVos.size() > 0 ? bookVos.get(0) : null;
		}
		// 도서 대출 테이블에 레코드 추가
		public int insertRentalBook(int b_no, int u_m_no) {
		    System.out.println("[BookDao] insertRentalBook()");

		    String sql = "INSERT INTO tbl_rental_book (b_no, u_m_no, rb_start_date, rb_reg_date, rb_mod_date) " +
		                 "VALUES (?, ?, NOW(), NOW(), NOW())";
		    
		    int result = -1;
		    try {
		        result = jdbcTemplate.update(sql, b_no, u_m_no);
		    } catch (Exception e) {
		        e.printStackTrace();
		    }

		    // 리턴 값은 변경된 레코드 수 -> 현재는 1
		    return result;
		}
		
		// 대출된 도서의 "대출가능(1)" -> "대출중(0)"으로 변경
		public void updateRentalBookAble(int b_no) {
		    System.out.println("[BookDao] updateRentalBookAble()");

		    String sql = "UPDATE tbl_book SET b_rantal_able = 0 WHERE b_no = ?";
		    
		    try {
		        jdbcTemplate.update(sql, b_no);
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}


}
