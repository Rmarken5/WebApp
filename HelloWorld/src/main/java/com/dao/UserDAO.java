package com.dao;

import com.exception.UserExistException;
import com.model.User;

public interface UserDAO {
	public void insertUser(User user) throws Exception, UserExistException;
	public void deleteUser(User user);
	public User getUserByUserName(String userName) throws Exception;
	public User validateLogin(String userName, String password) throws Exception;
	public void updateUser(User user);
}
