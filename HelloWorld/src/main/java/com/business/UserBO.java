package com.business;

import org.springframework.web.servlet.ModelAndView;

import com.exception.UserExistException;
import com.model.User;
import com.service.UserService;
import com.service.UserServiceImpl;

public class UserBO {

	public String addUser(User user, String rePass){
		UserService service = new UserServiceImpl();
		try{
			if(rePass.equals(user.getPassword())){
				service.insertUser(user);
				return "redirect:/login";
			}else{
				return "create:/create";
			}
		}catch(UserExistException ue){
			ue.printStackTrace();
			return "redirect:/create";
		}catch(Exception e){
			e.printStackTrace();
			return "unsuccessful";
		}
	}
	/**
	 * This method validates login for user entered 
	 * on login
	 * @param user - user to validate login for
	 * @return action
	 * @throws Exception 
	 */
	public ModelAndView validateLogin(User user) throws Exception{
		UserService service = null ;
		User returnUser = null;
		ModelAndView mv = null;
		String errorMessage = null;
		try{
			service = new UserServiceImpl();
			if(null != user 
					&& null != user.getUserName()
					&& null != user.getPassword()){
				returnUser =  service.validateLogin(user.getUserName(), user.getPassword());
				if(null != returnUser){
					mv = new ModelAndView("home","user",returnUser);
					return mv;
				}else{
					errorMessage = "There is no user " + user.getUserName() +". Please sign up.";
					mv = new ModelAndView("login","errorMessage",errorMessage);
					mv.addObject("user", new User());
					return mv;
				}
			}else{
				mv = new ModelAndView("login");
				return mv;
			}
		}catch(Exception e){
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
	}
}
