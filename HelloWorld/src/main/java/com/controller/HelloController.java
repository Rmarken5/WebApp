package com.controller;
 
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.model.User;
 

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