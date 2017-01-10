package com.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.springframework.stereotype.Repository;

import com.exception.UserExistException;
import com.misc.QueryStrings;
import com.model.User;
@Repository
@Transactional
public class UserDAOImpl implements UserDAO {
	
	Configuration cg = new Configuration().configure();
	ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
            cg.getProperties()).build();;
	SessionFactory factory = cg.buildSessionFactory(serviceRegistry);
	Session ssn = factory.openSession();
	Transaction tn = ssn.beginTransaction();
	
	/**
	 * Inserts a user into the database on creation
	 * of a new user
	 * @throws Exception 
	 */
	public void insertUser(User user) throws Exception,UserExistException {
		try{
			if(StringUtils.isNotBlank(user.getUserName())){
				if(null == getUserByUserName(user.getUserName()) && !getUsersByEmail(user)){
					user.setDeleteSW('N');
					ssn.save(user);
					tn.commit();
				}else{
					tn.rollback();
					throw new UserExistException("User already exists");
				}
			}
		}catch(Exception e){
			tn.rollback();
			e.printStackTrace();
			throw new Exception("Error in inserting new user "  + e.getMessage());
		}
	}
	/**
	 * Deletes the user
	 */
	public void deleteUser(User user) {
		ssn.delete(user);
		tn.commit();
	}
	/**
	 * This method fetches a user based on user name 
	 * to check for creating a user
	 * param - userName
	 * user name to get from the database.
	 * @throws Exception 
	 */
	public User getUserByUserName(String userName) throws Exception {
		User user = null;
		StringBuilder stringQuery = new StringBuilder(QueryStrings.GET_USER_BY_USER_NAME);
		try{
			if(StringUtils.isNotBlank(userName)){
				userName = userName.toLowerCase();
				user = (User)ssn.createQuery(stringQuery.toString())
						.setParameter("userName", userName)
						.uniqueResult();
			}
			return user;
		}catch(Exception e){
			tn.rollback();
			e.printStackTrace();
			throw new Exception("Exception caught in getUser() method " +  e.getMessage());
		}finally{
			ssn.clear();
		}
		
	}
	/**
	 * Checks for user with username and password
	 */
	public User validateLogin(String userName, String password) throws Exception{
		StringBuilder queryString = null;
		User user = null;
		try{
			queryString = new StringBuilder(QueryStrings.GET_USER_LOGIN);
			if(StringUtils.isNotBlank(userName)){
				userName = userName.toLowerCase();
			}
			user  =(User)ssn.createQuery(queryString.toString())
					.setParameter("userName", userName)
					.setParameter("password", password).uniqueResult();
			return user;
		}catch(Exception e){
			e.printStackTrace();
			tn.rollback();
			throw new Exception(e);
		}finally{
			ssn.close();
		}
		
	}
	/**
	 * updates a user
	 */
	public void updateUser(User user) {
		try{
			ssn.update(user);
			tn.commit();
		}catch(Exception e){
			e.printStackTrace();
			tn.rollback();
		}finally{
			ssn.close();
		}
	}
	/**
	 * Method is used to check if email is in use
	 * @param user - user object meant to be persisted
	 * @return true if email is in use; false if email is not in use
	 * @throws Exception
	 */
	private boolean getUsersByEmail(User user) throws Exception{
		String email = null;
		User retrievedUser = null;
		String retrivedEmail = null;
		StringBuilder queryString = new StringBuilder(QueryStrings.GET_USER_BY_EMAIL);
		try{
			if(user != null && StringUtils.isNotBlank(user.getEmail())){
				email = user.getEmail().toLowerCase();
				retrievedUser = (User)ssn.createQuery(queryString.toString())
						.setParameter("email", email).uniqueResult();
				if(retrievedUser != null){
					retrivedEmail = retrievedUser.getEmail().toLowerCase();
					return retrivedEmail.equalsIgnoreCase(email);
				}else{
					return false;
				}
			}
		}catch(Exception e){
			throw new Exception("Exception in getUsersByEmail" + e.getMessage());
		}
		
		return false;
		
	}
	
}
