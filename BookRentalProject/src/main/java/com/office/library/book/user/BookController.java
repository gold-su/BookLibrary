package com.office.library.book.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
		//도서 상세 보기 --> get 방식으로 전달된 도서번호를 인수로 받음, 결과는 VO객체에 담아 속성으로 추가
		//@ReqeuestMapping(value = "/bookDetail", method = RequestMethod.GET)
		@GetMapping("/bookDetail")
		public String bookDetail(@RequestParam("b_no") int b_no, Model model) {
			System.out.println("[BookController] bookDetail()");
			//실행 후 이동할 view 페이지
			String nextPage = "admin/book/book_detail";
			//도서번호를 기준으로 도서 검색
			BookVo bookVo = bookService.bookDetail(b_no);
			//조회 결과를 view 페이지에 전달하기 위해 Model 클래스의 속성으로 지정 
			model.addAttribute("bookVo", bookVo);
			
			return nextPage;
		}
}
