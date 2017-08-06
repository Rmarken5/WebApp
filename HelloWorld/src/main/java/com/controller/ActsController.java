package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ActsController {
	
	@RequestMapping(value="/acts")
	public String toActs(){
		return "acts";
	}
}
