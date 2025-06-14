package com.office.library.book.user;

import java.util.List;
<<<<<<< HEAD

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.office.library.book.BookVo;
import com.office.library.book.HopeBookVo;
//import com.office.library.book.HopeBookVo;
import com.office.library.book.RentalBookVo;
import com.office.library.user.member.UserMemberVo;

@Controller
//@Controller("user.BookController")
@RequestMapping("/book/user")
public class BookController {
=======
>>>>>>> branch 'main' of https://github.com/gold-su/BookLibrary.git

<<<<<<< HEAD
	@Autowired
	BookService bookService;
	
	/*
	 * 도서 검색
	 */
//	@RequestMapping(value = "/searchBookConfirm", method = RequestMethod.GET)
	@GetMapping("/searchBookConfirm")
	public String searchBookConfirm(BookVo bookVo, Model model) {
		System.out.println("[UserBookController] searchBookConfirm()");
		
		String nextPage = "user/book/search_book";
		
		List<BookVo> bookVos = bookService.searchBookConfirm(bookVo);
		
		model.addAttribute("bookVos", bookVos);
		
		return nextPage;
		
	}
	
	/*
	 * 도서 상세
	 */
//	@RequestMapping(value = "/bookDetail", method = RequestMethod.GET)
	@GetMapping("/bookDetail")
	public String bookDetail(@RequestParam("b_no") int b_no, Model model) {
		System.out.println("[UserBookController] bookDetail()");
		
		String nextPage = "user/book/book_detail";
		
		BookVo bookVo = bookService.bookDetail(b_no);
		
		model.addAttribute("bookVo", bookVo);
		
		return nextPage;
		
	}
	
	/*
	 * 도서 대출
	 */
//	@RequestMapping(value = "/rentalBookConfirm", method = RequestMethod.GET)
	@GetMapping("/rentalBookConfirm")
	public String rentalBookConfirm(@RequestParam("b_no") int b_no, HttpSession session) {
		System.out.println("[UserBookController] rentalBookConfirm()");
		
		String nextPage = "user/book/rental_book_ok";
		
		UserMemberVo loginedUserMemberVo = 
				(UserMemberVo) session.getAttribute("loginedUserMemberVo");

//		if (loginedUserMemberVo == null)
//			return "redirect:/user/member/loginForm";
		
		int result = bookService.rentalBookConfirm(b_no, loginedUserMemberVo.getU_m_no());
		
		if (result <= 0)
			nextPage = "user/book/rental_book_ng";
		
		return nextPage;
		
	}
	
	/*
	 * 나의 책장
	 */
//	@RequestMapping(value = "/enterBookshelf", method = RequestMethod.GET)
	@GetMapping("/enterBookshelf")
	public String enterBookshelf(HttpSession session, Model model) {
		System.out.println("[UserBookController] enterBookshelf()");
		
		String nextPage = "user/book/bookshelf";
		
		UserMemberVo loginedUserMemberVo = (UserMemberVo) session.getAttribute("loginedUserMemberVo");
		
		List<RentalBookVo> rentalBookVos = bookService.enterBookshelf(loginedUserMemberVo.getU_m_no());
		
		model.addAttribute("rentalBookVos", rentalBookVos);
		
		return nextPage;
	
	}
	
	/*
	 * 도서 대출 이력
	 */
//	@RequestMapping(value = "/listupRentalBookHistory", method = RequestMethod.GET)
	@GetMapping("/listupRentalBookHistory")
	public String listupRentalBookHistory(HttpSession session, Model model) {
		System.out.println("[UserBookController] listupRentalBookHistory()");
		
		String nextPage = "user/book/rental_book_history";
		
		UserMemberVo loginedUserMemberVo = (UserMemberVo) session.getAttribute("loginedUserMemberVo");
		
		List<RentalBookVo> rentalBookVos = bookService.listupRentalBookHistory(loginedUserMemberVo.getU_m_no());
		
		model.addAttribute("rentalBookVos", rentalBookVos);
		
		return nextPage;
		
	}
	
	/*
	 * 희망 도서 요청
	 */
//	@RequestMapping(value = "/requestHopeBookForm", method = RequestMethod.GET)
	@GetMapping("/requestHopeBookForm")
	public String requestHopeBookForm() {
		System.out.println("[UserBookController] requestHopeBookForm()");
		
		String nextPage = "user/book/request_hope_book_form";
		
		return nextPage;
		
	}
	
	/*
	 * 희망 도서 요청 확인
	 */
//	@RequestMapping(value = "/requestHopeBookConfirm", method = RequestMethod.GET)
	@GetMapping("/requestHopeBookConfirm")
	public String requestHopeBookConfirm(HopeBookVo hopeBookVo, HttpSession session) {
		System.out.println("[UserBookController] requestHopeBookConfirm()");
		
		String nextPage = "user/book/request_hope_book_ok";
		
		UserMemberVo loginedUserMemberVo = (UserMemberVo) session.getAttribute("loginedUserMemberVo");
		hopeBookVo.setU_m_no(loginedUserMemberVo.getU_m_no());
		
		int result = bookService.requestHopeBookConfirm(hopeBookVo);
		
		if (result <= 0)
			nextPage = "user/book/request_hope_book_ng";
		
		return nextPage;
		
	}
	
	/*
	 * 희망 도서 요청 목록
	 */
//	@RequestMapping(value = "/listupRequestHopeBook", method = RequestMethod.GET)
	@GetMapping("/listupRequestHopeBook")
	public String listupRequestHopeBook(HttpSession session, Model model) {
		System.out.println("[UserBookController] listupRequestHopeBook()");
		
		String nextPage = "user/book/list_hope_book";
		
		UserMemberVo loginedUserMemberVo = (UserMemberVo) session.getAttribute("loginedUserMemberVo");
		
		List<HopeBookVo> hopeBookVos = 
				bookService.listupRequestHopeBook(loginedUserMemberVo.getU_m_no());
		
		model.addAttribute("hopeBookVos", hopeBookVos);
		
		return nextPage;
		
	}
	
=======
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.office.library.book.BookVo;
import com.office.library.user.member.UserMemberVo;

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
			String nextPage = "user/book/book_detail";
			//도서번호를 기준으로 도서 검색
			BookVo bookVo = bookService.bookDetail(b_no);
			//조회 결과를 view 페이지에 전달하기 위해 Model 클래스의 속성으로 지정 
			model.addAttribute("bookVo", bookVo);
			
			return nextPage;
		}
		//도서 대출 
		@GetMapping("/rentalBookConfirm")
		public String rentalBookConfirm(@RequestParam("b_no") int b_no, HttpSession session) {
			System.out.println("[UserBookController] rentalBookConfirm()");
			
			String nextPage = "user/book/rental_book_ok";
			
			//로그인 확인 --> 비정상 접속 및 장시간 유휴상태 체크 --> 비정상 시 로그인 페이지로 이동
			UserMemberVo loginedUserMemberVo = 
					(UserMemberVo) session.getAttribute("loginedUserMemberVo");
			if(loginedUserMemberVo == null)
				return "redirect:/user/member/loginForm";
			
			//BookService 클래스에 대출확인 요청, 리턴 결과는 변경된 레코드 수
			int result = bookService.rentalBookConfirm(b_no, loginedUserMemberVo.getU_m_no());
			
			if(result <= 0)
				nextPage = "user/book/rental_book_ng";
			
			return nextPage;
		}
>>>>>>> branch 'main' of https://github.com/gold-su/BookLibrary.git
}
