package com.office.library;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	@GetMapping("/")
	public String home() {
		System.out.println("[UserHomeController] home()");
		
		
		return "user/home";
	}
	
}
