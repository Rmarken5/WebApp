package com.controller;
 
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class HelloController {
	

	@RequestMapping(value = "enterLogin")
	public String showMessage() {
		
		return "redirect:/login";
	}
	@RequestMapping(value = "main")
	public String showMainPage(){
		
		return "main";
	}
	
}