package com.model;

import com.jcraft.jsch.UserInfo;

public class MyUserInfo implements UserInfo {

	String passwd;
	public String getPassword() {
		return passwd;
	}

	public boolean promptYesNo(String str) {
		str = "Yes";
		return true;
	}


	public String getPassphrase() {
		return null;
	}

	public boolean promptPassphrase(String message) {
		return true;
	}

	public boolean promptPassword(String message) {
		passwd = "Zeppelin32!"; // enter the password for the machine you want
								// to connect.
		return true;
	}

	public void showMessage(String message) {

	}

}