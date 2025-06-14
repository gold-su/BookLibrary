package com.office.library.book.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.office.library.book.BookVo;
import com.office.library.book.HopeBookVo;
//import com.office.library.book.HopeBookVo;
import com.office.library.book.RentalBookVo;

@Service
//@Service("admin.BookService")
public class BookService {

	final static public int BOOK_ISBN_ALREADY_EXIST = 0;	// 이미 등록된 도서
	final static public int BOOK_REGISTER_SUCCESS = 1;		// 신규 도서 등록 성공
	final static public int BOOK_REGISTER_FAIL = -1;		// 신규 도서 등록 실패
	
	@Autowired
	BookDao bookDao;

	public int registerBookConfirm(BookVo bookVo) {
		System.out.println("[BookService] registerBookConfirm()");
		
		boolean isISBN = bookDao.isISBN(bookVo.getB_isbn());
		
		if (!isISBN) {
			int result = bookDao.insertBook(bookVo);
			
			if (result > 0)
				return BOOK_REGISTER_SUCCESS;
			
			else
				return BOOK_REGISTER_FAIL;
			
		} else {
			return BOOK_ISBN_ALREADY_EXIST;
			
		}
		
	}
	
	public List<BookVo> searchBookConfirm(BookVo bookVo) {
		System.out.println("[BookService] searchBookConfirm()");
		
		return bookDao.selectBooksBySearch(bookVo);
		
	}
	
	public BookVo bookDetail(int b_no) {
		System.out.println("[BookService] bookDetail()");
		
		return bookDao.selectBook(b_no);
		
	}
	
	public BookVo modifyBookForm(int b_no) {
		System.out.println("[BookService] modifyBookForm()");
		
		return bookDao.selectBook(b_no);
		
	}
	
	public int modifyBookConfirm(BookVo bookVo) {
		System.out.println("[BookService] modifyBookConfirm()");
		
		return bookDao.updateBook(bookVo);
		
	}
	
	public int deleteBookConfirm(int b_no) {
		System.out.println("[BookService] deleteBookConfirm()");
		
		return bookDao.deleteBook(b_no);
		
	}
	
	public List<RentalBookVo> getRentalBooks() {
		System.out.println("[BookService] getRentalBooks()");
		
		return bookDao.selectRentalBooks();
		
	}
	
	public int returnBookConfirm(int b_no, int rb_no) {
		System.out.println("[BookService] getRentalBooks()");
		
		int result = bookDao.updateRentalBook(rb_no);
		
		if (result > 0) 
			result = bookDao.updateBook(b_no);
			
		return result;
	}
	
	public List<HopeBookVo> getHopeBooks() {
		System.out.println("[BookService] getHopeBooks()");
		
		return bookDao.selectHopeBooks();
		
	}
	
	public int registerHopeBookConfirm(BookVo bookVo, int hb_no) {
		System.out.println("[BookService] registerHopeBookConfirm()");
		
		boolean isISBN = bookDao.isISBN(bookVo.getB_isbn());
		
		if (!isISBN) {
			int result = bookDao.insertBook(bookVo);
			
			if (result > 0) {
				
				bookDao.updateHopeBookResult(hb_no);
				
				return BOOK_REGISTER_SUCCESS;
				
			} else {
				return BOOK_REGISTER_FAIL;
				
			}
			
		} else {
			return BOOK_ISBN_ALREADY_EXIST;
			
		}
		
	}
	
	public List<BookVo> getAllBooks() {
		System.out.println("[BookService] getAllBooks()");
		
		return bookDao.selectAllBooks();
		
	}
	
	
}
