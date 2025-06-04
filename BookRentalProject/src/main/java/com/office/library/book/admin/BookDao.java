package com.office.library.book.admin;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper; // ✅ 올바른 RowMapper
import org.springframework.stereotype.Component;

import com.office.library.book.BookVo;

@Component
public class BookDao {
	//JdbcTemplate 객체 자동 주입
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	//ISBN 등록 여부 확인
	public boolean isISBN(String b_isbn) {
		System.out.println("[BookDao] isISBN()");
		
		String sql = "SELECT COUNT(*) FROM tbl_book WHERE b_isbn = ?";
		
		int result = jdbcTemplate.queryForObject(sql, Integer.class, b_isbn);
		
		//검색된 자료가 있으면 ISBN 등록됨 --> 신규등록 불가능
		return result > 0 ? true : false;
	}
	//도서등록
	public int insertBook(BookVo bookVo) {
		System.out.println("[BookDao] insertBook()");
		String sql = "INSERT INTO tbl_book(b_thumbnail, "
				+ "b_name, "
				+ "b_author, "
				+ "b_publisher, "
				+ "b_publish_year, "
				+ "b_isbn, "
				+ "b_call_number, "
				+ "b_rantal_able, "
				+ "b_reg_date, "
				+ "b_mod_date) "
				+ "VALUES(?,?,?,?,?,?,?,?,NOW(),NOW())";
		int result = -1;
		try {
			result = jdbcTemplate.update(sql, bookVo.getB_thumbnail(),
												bookVo.getB_name(),
												bookVo.getB_author(),
												bookVo.getB_publisher(),
												bookVo.getB_publish_year(),
												bookVo.getB_isbn(),
												bookVo.getB_call_number(),
												bookVo.getB_rantal_able() );
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result; //반환값은 변경된 레코드 수
	}
	
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
	
	//UPDATE 쿼리문 실행
	public int updateBook(BookVo bookVo) {
		System.out.println("[BookDao] updateBook()");
		
		List<String> args = new ArrayList<String>();
		
		String sql = "UPDATE tbl_book SET ";
			if (bookVo.getB_thumbnail() != null) {
				sql += "b_thumbnail = ?, ";
				args.add(bookVo.getB_thumbnail());
			}
			sql += "b_name = ?, ";
			args.add(bookVo.getB_name());
			sql += "b_author = ?, ";
			args.add(bookVo.getB_author());
			sql += "b_publisher = ?, ";
			args.add(bookVo.getB_publisher());
			sql += "b_publish_year = ?, ";
			args.add(bookVo.getB_publish_year());
			sql += "b_isbn = ?, ";
			args.add(bookVo.getB_isbn());
			sql += "b_call_number = ?, ";
			args.add(bookVo.getB_call_number());
			sql += "b_rantal_able = ?, ";
			args.add(Integer.toString(bookVo.getB_rantal_able()));
			sql += "b_mod_date = NOW() ";
			sql += "WHERE b_no = ?";
			args.add(Integer.toString(bookVo.getB_no()));
			
		int result = -1;
		try {
			result = jdbcTemplate.update(sql, args.toArray());
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	//도서 삭제
	public int deleteBook(int b_no) {
		System.out.println("[BookDao] deleteBook()");
		
		String sql = "DELETE FROM tbl_book WHERE b_no = ?";
		
		int result = -1;
		try {
			result = jdbcTemplate.update(sql, b_no);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
