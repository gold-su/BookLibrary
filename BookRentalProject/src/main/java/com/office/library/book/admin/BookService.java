package com.office.library.book.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.office.library.book.BookVo;

//서비스 클래스 지정 --> IoC 컨테이너에 Bean 공급
@Service
public class BookService {
	final static public int BOOK_ISBN_ALREADY_EXIST = 0; //이미 등록된 도서
	final static public int BOOK_REGISTER_SUCCESS = 1; //신규 도서 등록 성공
	final static public int BOOK_REGISTER_FALL = -1; //신규 도서 등록 실패
	
	//BookDao 클래스로 생성된 객체 자동 주입
	@Autowired
	BookDao bookDao;
	
	//도서 등록
	public int registerBookConfirm(BookVo bookVo) {
		System.out.println("[BookService] registerBookConfirm()");
		//ISBN 코드 확인 --> 동일 서적이 존재하면 새로운 도서로 등록 불가능
		boolean isISBN = bookDao.isISBN(bookVo.getB_isbn());
		
		if(!isISBN) {
			//새로운 책 --> ISBN 미등록 상태
			//신규 도서 등록, 리턴값은 변겨된 레코드 수
			int result = bookDao.insertBook(bookVo);
			
			if(result > 0)
				return BOOK_REGISTER_SUCCESS;
			else
				return BOOK_REGISTER_FALL;
		}
		else {
			return BOOK_ISBN_ALREADY_EXIST;
		}
	}
	
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
