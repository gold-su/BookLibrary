package com.office.library.book.user;

import java.sql.ResultSet;
import java.sql.SQLException;
<<<<<<< HEAD
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.office.library.book.BookVo;
import com.office.library.book.HopeBookVo;
//import com.office.library.book.HopeBookVo;
import com.office.library.book.RentalBookVo;

@Component
//@Component("user.BookDao")
=======
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.office.library.book.BookVo;

//Bean 공급을 위한 애너테이션 설정
@Service
>>>>>>> branch 'main' of https://github.com/gold-su/BookLibrary.git
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


	@Autowired
	JdbcTemplate jdbcTemplate;
	
//	public List<BookVo> selectBooksBySearch(BookVo bookVo) {
//		System.out.println("[BookDao] selectBooks()");
//		
//		String sql =  "SELECT * FROM tbl_book "
//					+ "WHERE b_name LIKE ? "
//					+ "ORDER BY b_no DESC";
//		
//		List<BookVo> bookVos = null;
//		
//		try {
//			
//			bookVos = jdbcTemplate.query(sql, new RowMapper<BookVo>() {
//
//				@Override
//				public BookVo mapRow(ResultSet rs, int rowNum) throws SQLException {
//					
//					BookVo bookVo = new BookVo();
//					
//					bookVo.setB_no(rs.getInt("b_no"));
//					bookVo.setB_thumbnail(rs.getString("b_thumbnail"));
//					bookVo.setB_name(rs.getString("b_name"));
//					bookVo.setB_author(rs.getString("b_author"));
//					bookVo.setB_publisher(rs.getString("b_publisher"));
//					bookVo.setB_publish_year(rs.getString("b_publish_year"));
//					bookVo.setB_isbn(rs.getString("b_isbn"));
//					bookVo.setB_call_number(rs.getString("b_call_number"));
//					bookVo.setB_rantal_able(rs.getInt("b_rantal_able"));
//					bookVo.setB_reg_date(rs.getString("b_reg_date"));
//					bookVo.setB_mod_date(rs.getString("b_mod_date"));
//					
//					return bookVo;
//					
//				}
//				
//			}, "%" + bookVo.getB_name() + "%");
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//			
//		}
//		
//		return bookVos.size() > 0 ? bookVos : null;
//		
//	}
	
	public List<BookVo> selectBooksBySearch(BookVo bookVo) {
		System.out.println("[BookDao] selectBooks()");
		
		String sql =  "SELECT * FROM tbl_book "
					+ "WHERE b_name LIKE ? "
					+ "ORDER BY b_no DESC";
		
		List<BookVo> bookVos = null;
		
		try {
			
			RowMapper<BookVo> rowMapper = BeanPropertyRowMapper.newInstance(BookVo.class);
			bookVos = jdbcTemplate.query(sql, rowMapper, "%" + bookVo.getB_name() + "%");
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
		return bookVos.size() > 0 ? bookVos : null;
		
	}
	
//	public BookVo selectBook(int b_no) {
//		System.out.println("[BookDao] selectBook()");
//		
//		String sql = "SELECT * FROM tbl_book WHERE b_no = ?";
//		
//		List<BookVo> bookVos = null;
//		
//		try {
//			
//			bookVos = jdbcTemplate.query(sql, new RowMapper<BookVo>() {
//
//				@Override
//				public BookVo mapRow(ResultSet rs, int rowNum) throws SQLException {
//					
//					BookVo bookVo = new BookVo();
//					
//					bookVo.setB_no(rs.getInt("b_no"));
//					bookVo.setB_thumbnail(rs.getString("b_thumbnail"));
//					bookVo.setB_name(rs.getString("b_name"));
//					bookVo.setB_author(rs.getString("b_author"));
//					bookVo.setB_publisher(rs.getString("b_publisher"));
//					bookVo.setB_publish_year(rs.getString("b_publish_year"));
//					bookVo.setB_isbn(rs.getString("b_isbn"));
//					bookVo.setB_call_number(rs.getString("b_call_number"));
//					bookVo.setB_rantal_able(rs.getInt("b_rantal_able"));
//					bookVo.setB_reg_date(rs.getString("b_reg_date"));
//					bookVo.setB_mod_date(rs.getString("b_mod_date"));
//					
//					return bookVo;
//					
//				}
//				
//			}, b_no);
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//			
//		}
//		
//		return bookVos.size() > 0 ? bookVos.get(0) : null;
//		
//	}
	
	public BookVo selectBook(int b_no) {
		System.out.println("[BookDao] selectBook()");
		
		String sql = "SELECT * FROM tbl_book WHERE b_no = ?";
		
		List<BookVo> bookVos = null;
		
		try {
			
			RowMapper<BookVo> rowMapper = BeanPropertyRowMapper.newInstance(BookVo.class);
			bookVos = jdbcTemplate.query(sql, rowMapper, b_no);
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
		return bookVos.size() > 0 ? bookVos.get(0) : null;
		
	}
	
	public int insertRentalBook(int b_no, int u_m_no) {
		System.out.println("[BookDao] insertRentalBook()");
		
		String sql =  "INSERT INTO tbl_rental_book(b_no, u_m_no, rb_start_date, rb_reg_date, rb_mod_date) "
					+ "VALUES(?, ?, NOW(), NOW(), NOW())";
		
		int result = -1;
		
		try {
			
			result = jdbcTemplate.update(sql, b_no, u_m_no);
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
		return result;
		
	}
	
	public void updateRentalBookAble(int b_no) {
		System.out.println("[BookDao] updateRentalBookAble()");
		
		String sql =  "UPDATE tbl_book "
					+ "SET b_rantal_able = 0 "
					+ "WHERE b_no = ?";
		
		try {
			
			jdbcTemplate.update(sql, b_no);
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
	}
	
//	public List<RentalBookVo> selectRentalBooks(int u_m_no) {
//		System.out.println("[BookDao] selectRentalBooks()");
//		
//		String sql =  "SELECT * FROM tbl_rental_book rb "
//					+ "JOIN tbl_book b "
//					+ "ON rb.b_no = b.b_no "
//					+ "JOIN tbl_user_member um "
//					+ "ON rb.u_m_no = um.u_m_no "
//					+ "WHERE rb.u_m_no = ? AND rb.rb_end_date = '1000-01-01'";
//		
//		List<RentalBookVo> rentalBookVos = new ArrayList<RentalBookVo>();
//		
//		try {
//			
//			rentalBookVos = jdbcTemplate.query(sql, new RowMapper<RentalBookVo>() {
//
//				@Override
//				public RentalBookVo mapRow(ResultSet rs, int rowNum) throws SQLException {
//					
//					RentalBookVo rentalBookVo = new RentalBookVo();
//					
//					rentalBookVo.setRb_no(rs.getInt("rb_no"));
//					rentalBookVo.setB_no(rs.getInt("b_no"));
//					rentalBookVo.setU_m_no(rs.getInt("u_m_no"));
//					rentalBookVo.setRb_start_date(rs.getString("rb_start_date"));
//					rentalBookVo.setRb_end_date(rs.getString("rb_end_date"));
//					rentalBookVo.setRb_reg_date(rs.getString("rb_reg_date"));
//					rentalBookVo.setRb_mod_date(rs.getString("rb_mod_date"));
//					
//					rentalBookVo.setB_thumbnail(rs.getString("b_thumbnail"));
//					rentalBookVo.setB_name(rs.getString("b_name"));
//					rentalBookVo.setB_author(rs.getString("b_author"));
//					rentalBookVo.setB_publisher(rs.getString("b_publisher"));
//					rentalBookVo.setB_publish_year(rs.getString("b_publish_year"));
//					rentalBookVo.setB_isbn(rs.getString("b_isbn"));
//					rentalBookVo.setB_call_number(rs.getString("b_call_number"));
//					rentalBookVo.setB_rantal_able(rs.getInt("b_rantal_able"));
//					rentalBookVo.setB_reg_date(rs.getString("b_reg_date"));
//					
//					rentalBookVo.setU_m_id(rs.getString("u_m_id"));
//					rentalBookVo.setU_m_pw(rs.getString("u_m_pw"));
//					rentalBookVo.setU_m_name(rs.getString("u_m_name"));
//					rentalBookVo.setU_m_gender(rs.getString("u_m_gender"));
//					rentalBookVo.setU_m_mail(rs.getString("u_m_mail"));
//					rentalBookVo.setU_m_phone(rs.getString("u_m_phone"));
//					rentalBookVo.setU_m_reg_date(rs.getString("u_m_reg_date"));
//					rentalBookVo.setU_m_mod_date(rs.getString("u_m_mod_date"));
//					
//					return rentalBookVo;
//					
//				}
//				
//			}, u_m_no);
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//			
//		}
//		
//		return rentalBookVos;
//		
//	}
	
	public List<RentalBookVo> selectRentalBooks(int u_m_no) {
		System.out.println("[BookDao] selectRentalBooks()");
		
		String sql =  "SELECT * FROM tbl_rental_book rb "
					+ "JOIN tbl_book b "
					+ "ON rb.b_no = b.b_no "
					+ "JOIN tbl_user_member um "
					+ "ON rb.u_m_no = um.u_m_no "
					+ "WHERE rb.u_m_no = ? AND rb.rb_end_date = '1000-01-01'";
		
		List<RentalBookVo> rentalBookVos = new ArrayList<RentalBookVo>();
		
		try {
			
			RowMapper<RentalBookVo> rowMapper = BeanPropertyRowMapper.newInstance(RentalBookVo.class);
			rentalBookVos = jdbcTemplate.query(sql, rowMapper, u_m_no);
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
		return rentalBookVos;
		
	}
	
	public List<RentalBookVo> selectRentalBookHistory(int u_m_no) {
		System.out.println("[BookDao] selectRentalBooks()");
		
		String sql =  "SELECT * FROM tbl_rental_book rb "
					+ "JOIN tbl_book b "
					+ "ON rb.b_no = b.b_no "
					+ "JOIN tbl_user_member um "
					+ "ON rb.u_m_no = um.u_m_no "
					+ "WHERE rb.u_m_no = ? "
					+ "ORDER BY rb.rb_reg_date DESC";
		
		List<RentalBookVo> rentalBookVos = new ArrayList<RentalBookVo>();
		
		try {
			
			rentalBookVos = jdbcTemplate.query(sql, new RowMapper<RentalBookVo>() {

				@Override
				public RentalBookVo mapRow(ResultSet rs, int rowNum) throws SQLException {
					
					RentalBookVo rentalBookVo = new RentalBookVo();
					
					rentalBookVo.setRb_no(rs.getInt("rb_no"));
					rentalBookVo.setB_no(rs.getInt("b_no"));
					rentalBookVo.setU_m_no(rs.getInt("u_m_no"));
					rentalBookVo.setRb_start_date(rs.getString("rb_start_date"));
					rentalBookVo.setRb_end_date(rs.getString("rb_end_date"));
					rentalBookVo.setRb_reg_date(rs.getString("rb_reg_date"));
					rentalBookVo.setRb_mod_date(rs.getString("rb_mod_date"));
					
					rentalBookVo.setB_thumbnail(rs.getString("b_thumbnail"));
					rentalBookVo.setB_name(rs.getString("b_name"));
					rentalBookVo.setB_author(rs.getString("b_author"));
					rentalBookVo.setB_publisher(rs.getString("b_publisher"));
					rentalBookVo.setB_publish_year(rs.getString("b_publish_year"));
					rentalBookVo.setB_isbn(rs.getString("b_isbn"));
					rentalBookVo.setB_call_number(rs.getString("b_call_number"));
					rentalBookVo.setB_rantal_able(rs.getInt("b_rantal_able"));
					rentalBookVo.setB_reg_date(rs.getString("b_reg_date"));
					
					rentalBookVo.setU_m_id(rs.getString("u_m_id"));
					rentalBookVo.setU_m_pw(rs.getString("u_m_pw"));
					rentalBookVo.setU_m_name(rs.getString("u_m_name"));
					rentalBookVo.setU_m_gender(rs.getString("u_m_gender"));
					rentalBookVo.setU_m_mail(rs.getString("u_m_mail"));
					rentalBookVo.setU_m_phone(rs.getString("u_m_phone"));
					rentalBookVo.setU_m_reg_date(rs.getString("u_m_reg_date"));
					rentalBookVo.setU_m_mod_date(rs.getString("u_m_mod_date"));
					
					return rentalBookVo;
					
				}
				
			}, u_m_no);
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
		return rentalBookVos;
		
	}
	
//	public List<RentalBookVo> selectRentalBookHistory(int u_m_no) {
//		System.out.println("[BookDao] selectRentalBooks()");
//		
//		String sql =  "SELECT * FROM tbl_rental_book rb "
//					+ "JOIN tbl_book b "
//					+ "ON rb.b_no = b.b_no "
//					+ "JOIN tbl_user_member um "
//					+ "ON rb.u_m_no = um.u_m_no "
//					+ "WHERE rb.u_m_no = ? "
//					+ "ORDER BY rb.rb_reg_date DESC";
//		
//		List<RentalBookVo> rentalBookVos = new ArrayList<RentalBookVo>();
//		
//		try {
//			
//			RowMapper<RentalBookVo> rowMapper = BeanPropertyRowMapper.newInstance(RentalBookVo.class);
//			rentalBookVos = jdbcTemplate.query(sql, rowMapper, u_m_no);
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//			
//		}
//		
//		return rentalBookVos;
//		
//	}
	
	public int insertHopeBook(HopeBookVo hopeBookVo) {
		System.out.println("[BookDao] insertHopeBook()");
		
		String sql =  "INSERT INTO tbl_hope_book(u_m_no, hb_name, hb_author, hb_publisher, "
					+ "hb_publish_year, hb_reg_date, hb_mod_date, hb_result_last_date) "
					+ "VALUES(?, ?, ?, ?, ?, NOW(), NOW(), NOW())";
		
		int result = -1;
		
		try {
			
			result = jdbcTemplate.update(sql, 
											hopeBookVo.getU_m_no(), 
											hopeBookVo.getHb_name(), 
											hopeBookVo.getHb_author(), 
											hopeBookVo.getHb_publisher(), 
											hopeBookVo.getHb_publish_year());
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
		return result;
		
	}
	
	public List<HopeBookVo> selectRequestHopeBooks(int u_m_no) {
		System.out.println("[BookDao] insertHopeBook()");
		
		String sql = "SELECT * FROM tbl_hope_book WHERE u_m_no = ?";
		
		List<HopeBookVo> hopeBookVos = null;
		
		try {
			
			hopeBookVos = jdbcTemplate.query(sql, new RowMapper<HopeBookVo>() {

				@Override
				public HopeBookVo mapRow(ResultSet rs, int rowNum) throws SQLException {
					
					HopeBookVo hopeBookVo = new HopeBookVo();
					
					hopeBookVo.setHb_no(rs.getInt("hb_no"));
					hopeBookVo.setU_m_no(rs.getInt("u_m_no"));
					hopeBookVo.setHb_name(rs.getString("hb_name"));
					hopeBookVo.setHb_author(rs.getString("hb_author"));
					hopeBookVo.setHb_publisher(rs.getString("hb_publisher"));
					hopeBookVo.setHb_publish_year(rs.getString("hb_publish_year"));
					hopeBookVo.setHb_reg_date(rs.getString("hb_reg_date"));
					hopeBookVo.setHb_mod_date(rs.getString("hb_mod_date"));
					hopeBookVo.setHb_result(rs.getInt("hb_result"));
					hopeBookVo.setHb_result_last_date(rs.getString("hb_result_last_date"));
					
					return hopeBookVo;
					
				}
				
			}, u_m_no);
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
		return hopeBookVos;
		
	}

//	public List<HopeBookVo> selectRequestHopeBooks(int u_m_no) {
//		System.out.println("[BookDao] insertHopeBook()");
//		
//		String sql = "SELECT * FROM tbl_hope_book WHERE u_m_no = ?";
//		
//		List<HopeBookVo> hopeBookVos = null;
//		
//		try {
//			
//			RowMapper<HopeBookVo> rowMapper = BeanPropertyRowMapper.newInstance(HopeBookVo.class);
//			hopeBookVos = jdbcTemplate.query(sql, rowMapper, u_m_no);
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//			
//		}
//		
//		return hopeBookVos;
//		
//	}
	
}
