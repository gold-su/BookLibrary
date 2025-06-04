package com.office.library.book.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.office.library.book.BookVo;
import com.office.library.book.admin.BookDao;

//Bean 공급을 위한 애너테이션 설정
@Component
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
		
		//도서정보 수정
		public int modifyBookConfirm(BookVo bookVo) {
			System.out.println("[BookService] modifyBookConfirm()");
			
			return bookDao.updateBook(bookVo);
		}
		
		//도서 삭제
		public int deleteBookConfirm(int b_no) {
			System.out.println("[BookService] deleteBookConfirm()");
			
			return bookDao.deleteBook(b_no);
		}
}
