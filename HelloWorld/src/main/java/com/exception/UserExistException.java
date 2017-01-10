package com.exception;
/**
 * Thrown if user exist in the database
 * @author Ryan
 *
 */
public class UserExistException extends Throwable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2012489170189893526L;

	public UserExistException(){
		super();
	}
	public UserExistException(String message){
		super(message);
	}
}
