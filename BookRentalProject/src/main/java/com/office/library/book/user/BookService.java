package com.office.library.book.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
<<<<<<< HEAD
import org.springframework.stereotype.Service;

import com.office.library.book.BookVo;
import com.office.library.book.HopeBookVo;
//import com.office.library.book.HopeBookVo;
import com.office.library.book.RentalBookVo;

@Service
//@Service("user.BookService")
=======
import org.springframework.stereotype.Component;

import com.office.library.book.BookVo;
import com.office.library.book.user.BookDao;

//Bean 공급을 위한 애너테이션 설정
@Component
>>>>>>> branch 'main' of https://github.com/gold-su/BookLibrary.git
public class BookService {
	//BookDao 클래스로 생성된 객체 자동 주입
		@Autowired
		BookDao bookDao;
		
		//도서 검색
		public List<BookVo> searchBookConfirm(BookVo bookVo){
			System.out.println("[BookService] searchBookConfirm()");
			
			//BookDao 클래스에 검색을 요청하고, 결과를 Controller 클래스에 리턴
			return bookDao.selectBooksBySearch(bookVo);
		}
		//도서 상세 보기
		public BookVo bookDetail(int b_no) {
			System.out.println("[BookService] bookDetail()");
			
			//BookDao 클래스에 도서번호를 전달하여 검색을 요청하고, 결과를 Controller 클래스에 리턴
			return bookDao.selectBook(b_no);
		}
		
		//도서번호에 해당하는 도서정보 요청
		public BookVo modifyBookForm(int b_no) {
			System.out.println("[BookService] modifyBookForm()");
			
			//BookDao 클래스에 도서번호를 전달하여 검색을 요청하고, 결과를 Controller 클래스에 리턴
			return bookDao.selectBook(b_no);
		}
		
		//대출 요청 처리
		public int rentalBookConfirm(int b_no, int u_m_no) {
		    System.out.println("[BookService] rentalBookConfirm()");

		    //"도서 대출" 테이블의 레코드 하나 추가
		    int result = bookDao.insertRentalBook(b_no, u_m_no);

		    //대출 처리된 도서의 "대출 가능" --> "대출 중"으로 변경
		    if(result >= 0) {
		        bookDao.updateRentalBookAble(b_no);
		    }

		    //"도서 대출" 테이블에 변경된 레코드 수 반환
		    return result;
		}

	@Autowired
	BookDao bookDao;

	public List<BookVo> searchBookConfirm(BookVo bookVo) {
		System.out.println("[BookService] searchBookConfirm()");
		
		return bookDao.selectBooksBySearch(bookVo);
		
	}
	
	public BookVo bookDetail(int b_no) {
		System.out.println("[BookService] bookDetail()");
		
		return bookDao.selectBook(b_no);
		
	}
	
	public int rentalBookConfirm(int b_no, int u_m_no) {
		System.out.println("[BookService] bookDetail()");
		
		int result = bookDao.insertRentalBook(b_no, u_m_no);
		
		if (result >= 0)
			bookDao.updateRentalBookAble(b_no);
		
		return result;
	}
	
	public List<RentalBookVo> enterBookshelf(int u_m_no) {
		System.out.println("[BookService] enterBookshelf()");
		
		return bookDao.selectRentalBooks(u_m_no);
		
	}
	
	public List<RentalBookVo> listupRentalBookHistory(int u_m_no) {
		System.out.println("[BookService] listupRentalBookHistory()");
		
		return bookDao.selectRentalBookHistory(u_m_no);
		
	}
	
	public int requestHopeBookConfirm(HopeBookVo hopeBookVo) {
		System.out.println("[BookService] requestHopeBookConfirm()");
		
		return bookDao.insertHopeBook(hopeBookVo);
		
	}
	
	public List<HopeBookVo> listupRequestHopeBook(int u_m_no) {
		System.out.println("[BookService] listupRequestHopeBook()");
		
		return bookDao.selectRequestHopeBooks(u_m_no);
		
	}
	
}
