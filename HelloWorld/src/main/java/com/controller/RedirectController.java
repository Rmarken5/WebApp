package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RedirectController {

	@RequestMapping(value = "direct", method = RequestMethod.POST)
	public ModelAndView bannerDirect(@RequestParam String action){
		System.out.println(action);
		ModelAndView mv = new ModelAndView("redirect:/" + action.toLowerCase());
		return mv;
	}
}
