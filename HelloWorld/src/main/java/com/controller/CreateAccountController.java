package com.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.business.UserBO;
import com.model.User;

@Controller
public class CreateAcccountController {
	private UserBO usrBO = new UserBO();
	private final String CREATE_USER_MESSAGE = "Please fill all the fields to create a new user";
	
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public ModelAndView createUser(){
		ModelAndView mv = new ModelAndView("createUser");
		mv.addObject("message",CREATE_USER_MESSAGE);
		mv.addObject("newUser", new User());
		mv.addObject("rePass", new String());
		return mv;
	}
	/**
	 * Post form to create a new user.
	 * @param newUser - user to be added
	 * @param rePass - confirmation on password
	 * @return
	 */
	@RequestMapping(value = "create", method = RequestMethod.POST)
	public String createUser(@ModelAttribute(value = "newUser") User newUser, @RequestParam String rePass){
		String action = usrBO.addUser(newUser, rePass);
		return action;
	}
}
