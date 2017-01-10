package com.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.business.UserBO;
import com.model.User;

@Controller
@Scope("session")
@SessionAttributes("user")
public class LoginController {
	private final String LOGIN_MESSAGE = "Welcome to my site, please login to continue!";
	
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public ModelAndView submitLoginForm(){
		ModelAndView mv = new ModelAndView("login");
		mv.addObject("user" ,new User());
		mv.addObject("message",LOGIN_MESSAGE);
		return mv;
	}
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public ModelAndView validateLogin(@RequestParam String userName, 
			@RequestParam String password, @RequestParam String action){
		System.out.println(action);
		UserBO usrBo = null;
		String view = null;
		ModelAndView mv = null;
		try{
		if("Login!".equalsIgnoreCase(action)){
			usrBo = new UserBO();
			System.out.println(userName + "  " +  password);
			mv = usrBo.validateLogin(new User(userName, password));
		}else if("Create Account".equalsIgnoreCase(action)){
			mv = new ModelAndView("redirect:/create");
		}
		return mv;
		}catch(Exception e){
			mv = new ModelAndView("unsuccessful");
			mv.addObject("message", e.getMessage());
			return mv;
		}
		}
}
