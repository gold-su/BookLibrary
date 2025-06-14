package com.office.library.book.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.office.library.book.BookVo;
import com.office.library.book.HopeBookVo;
//import com.office.library.book.HopeBookVo;
import com.office.library.book.RentalBookVo;

@Service
//@Service("user.BookService")
public class BookService {

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
