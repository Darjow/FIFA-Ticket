package com.springBoot.Spring_Opdracht_Dario;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	
	@GetMapping("/")
	public String redirectFifa() {
		return "redirect:/fifa";
	}
}
