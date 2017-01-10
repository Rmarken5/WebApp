package com.service;

import org.springframework.stereotype.Service;

import com.dao.UserDAO;
import com.dao.UserDAOImpl;
import com.exception.UserExistException;
import com.model.User;
@Service
public class UserServiceImpl implements UserService{
	UserDAO userDAO = new UserDAOImpl();
	public void insertUser(User user) throws Exception,UserExistException {
		userDAO.insertUser(user);
	}

	public void deleteUser(User user) {
		userDAO.deleteUser(user);
		
	}

	public User getUserByUserName(String userName) throws Exception {
		return userDAO.getUserByUserName(userName);
	}

	public void updateUser(User user) {
		userDAO.updateUser(user);
	}

	public User validateLogin(String userName, String password) throws Exception {
		return userDAO.validateLogin(userName, password);
	}

}
