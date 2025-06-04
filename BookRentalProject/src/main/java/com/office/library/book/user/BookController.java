package com.office.library.book.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.office.library.book.BookVo;

//Bean 공급 및 /book/user 요청 처리를 위한 애너테이션 설정
@Controller
@RequestMapping("/book/user")
public class BookController {
	//BookService 객체 자동 주입
	@Autowired
	BookService bookService;
	//도서 검색
		@GetMapping("/searchBookConfirm")
		public String searchBookConfirm1(BookVo bookVo, Model model) {
			System.out.println("[UserBookController] searchBookConfirm()");
			
			String nextPage = "user/book/search_book";
			
			//BookSerivce 클래스에 도서 검색 요청
			List<BookVo> bookVos = bookService.searchBookConfirm(bookVo);
			
			//Model 클래스를 이용하여 검색 데이터를 view에 전달
			model.addAttribute("bookVos", bookVos);
			
			return nextPage;
					
		}
}
