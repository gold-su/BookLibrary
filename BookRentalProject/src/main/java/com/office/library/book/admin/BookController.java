package com.office.library.book.admin;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.office.library.admin.member.AdminMemberVo;
import com.office.library.book.BookVo;
import com.office.library.book.admin.util.UploadFileService;

//컨트롤러 클래스 지정 --> IoC 컨테이너에 객체 생성
//@RequestMapping() 지정 --> "/book/admin"의 url로 전급할 때 동작
@Controller
@RequestMapping("/book/admin")
public class BookController {
	//BookService 클래스 자동 주입
	@Autowired
	BookService bookService;
	
	//UploadFileService 클래스 자동 주입
	@Autowired
	UploadFileService uploadFileService;
	
	//도서 등록
	//@RequestMapping(value = "/registerBookForm", method = RequestMethod.GET)
	@GetMapping("/registerBookForm")
	public String registerBookForm() {
		System.out.println("[BookController] registerBookForm()");
		
		String nextPage = "admin/book/register_book_form";
		//"register_book_form.jsp" 페이지로 이동
		return nextPage;
		
	}
	
	//도서 등록 확인, 첨부파일(책 표지 이미지)은 VO 객체와 별도로 전달받음
	//@RequestMapping(value = "/registerBookConfirm", method = RequestMethod.POST)
	@PostMapping("/registerBookConfirm")
	public String registerBookConfirm(BookVo bookVo, @RequestParam("file")MultipartFile file) {
		System.out.println("[BookController] registerBookConfirm()");
		System.out.println(file.getOriginalFilename());
		
		String nextPage = "admin/book/register_book_ok";
		
		//첨부파일 저장 --> UploadFileService 클래스 이용
		String savedFileName = uploadFileService.upload(file);
		System.out.println("test");
		
		if(savedFileName != null) { //첨부파일 업로드 성공한 경우 도서 정보 등록
			bookVo.setB_thumbnail(savedFileName); //첨부파일
			//도서정보 등록 --> BookService 클래스 이용 -- > 리턴값은 변경 레코드 수
			int result = bookService.registerBookConfirm(bookVo);
			
			if(result <= 0)nextPage = "admin/book/register_book_ng";
				
		}
		else {
			//첨부파일 업로드 실패
			nextPage = "admin/book/register_book_ng";
		}
		
		//도서등록 성공 --> register_book_ok.jsp, 실패 --> register_book_ng.jsp
		return nextPage;
	}
	
	//도서 검색
	//@RequeestMapping(value = "/searchBookConfirm", method = RequestMethod.GET)
	@GetMapping("/searchBookConfirm")
	public String searchBookConfirm(BookVo bookVo, Model model) {
		System.out.println("[UserBookController] searchBookConfirm()");
		//결과 검색 후 이동할 view 페이지 지정
		String nextPage = "admin/book/search_book";
		//서비스 클래스를 호출하여 결과를 List<BookVo>로 받아옴
		List<BookVo> bookVos = bookService.searchBookConfirm(bookVo);
		//view 페이지로 데이터를 전달하기 위해 속성으로 등록
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
	
	//도서 수정/
	//@ReqeustMapping(value = "/modifyBookForm", method = RequestMethod.GET)
	@GetMapping("/modifyBookForm")
	//GET 방식으로 전달된 도서번호를 이용하여 DAO 클래스에 도서정보를 요청 --> 수정폼에 정보 표시하기 위함
	//조회된 도서정보는 Model 객체의 속성으로 추가하여 view 페이지에 전달
	//Session을 이용하여 로그인 상태 확인 --> 비장상접속 및 장시간 유휴상태 대비
	public String modifyBookForm(@RequestParam("b_no")int b_no, //도서번호
											Model model, 		//도서정보를 속성으로 전달
											HttpSession session) { //세션 -> 로그인 확인
		System.out.println("[BookController] bookDetail()");
		
		String nextPage = "admin/book/modify_book_form";
		//로그인한 사용자 정보를 세션에서 확인, 로그인 상태가 아니면 로그인 페이지로 이동
		AdminMemberVo loginedAdminMemberVo
			= (AdminMemberVo) session.getAttribute("loginedAdminMemberVo");
		if(loginedAdminMemberVo==null)
			return "redirect:/admin/member//loginForm";
		//로그인한 상태라면 도서정보 조회를 요청하고, 이를 Model 클래스의 속성으로 추가하여 modify_book_form.jsp에 전달
		BookVo bookVo = bookService.modifyBookForm(b_no);
		model.addAttribute("bookVo", bookVo);
		return nextPage;
		
	}
	
	//도서 수정 확인, 표지 이미지는 지정하지 않아도 됨
	@PostMapping("/modifyBookConfirm")
	public String modifyBookConfirm(BookVo bookVo, //자동 전달된 도서 수정 정보
								@RequestParam("file") MultipartFile file, //표지 이미지
								HttpSession session) { //로그인 확인을 session
		System.out.println("[BookContoroller] modifyBookConfirm()");
		String nextPage = "admin/book/modify_book_ok";
		
		//세션을 이용하여 로그인 상태 확인 --> 비정상 접속 및 장기 유휴상태 대비
		AdminMemberVo loginedAdminMemberVo
			= (AdminMemberVo) session.getAttribute("loginedAdminMemberVo");
		if(loginedAdminMemberVo == null)
			return "redirect:/admin/member//loginForm";
		
		//첨부파일(표지 이미지 재설정)이 존재할 경우 업로드
		if(!file.getOriginalFilename().equals("")) {
			//SAVE FLIE
			String savedFileName = uploadFileService.upload(file);
			if(savedFileName != null)
				bookVo.setB_thumbnail(savedFileName);
		}
		//서비스 객체에 수정 요청
		int result = bookService.modifyBookConfirm(bookVo);
		//수정된 레코드가 없으면 수정 작업 실패
		if(result <= 0)
			nextPage = "admin/book/modify_book_ng";
		
		return nextPage;
	}
	
	//도서 삭제
	@GetMapping("/deleteBookConfirm") 

	public String deleteBookConfirm(@RequestParam("b_no") int b_no,
									HttpSession session) {
		System.out.println("[BookController] deleteBookConfirm()");
		
		String nextPage = "admin/book/delete_book_ok";
		//세션을 이용 로그인 상태 확인 --> 비정상 접속 혹은 장시간 유휴 상태 대비
		AdminMemberVo loginedAdminMemberVO
			= (AdminMemberVo) session.getAttribute("loginedAdminMemberVo");
		if(loginedAdminMemberVO == null)
			return "redirect:/admin/member/loginForm";
		
		//도서 삭제 요청
		int result = bookService.deleteBookConfirm(b_no);
		
		if(result <= 0)
			nextPage = "admin/book/delete_book_ng";
		
		return nextPage;
	}
	
	//도서 검색
//	@GetMapping("/searchBookConfirm")
//	public String searchBookConfirm(BookVo bookVo, Model model) {
//		System.out.println("[UserBookController] searchBookConfirm()");
//		
//		String nextPage = "user/book/search_book";
//		
//		//BookSerivce 클래스에 도서 검색 요청
//		List<BookVo> bookVos = bookService.searchBookConfirm(bookVo);
//		
//		//Model 클래스를 이용하여 검색 데이터를 view에 전달
//		model.addAttribute("bookVos", bookVos);
//		
//		return nextPage;
//				
//	}
			
}
