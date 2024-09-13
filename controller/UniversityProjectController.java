package com.universityproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UniversityProjectController {
	
	
	 
	@GetMapping("/dashboard")
	public String dashboard() {
		return "dashboard";
	}
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	
} 