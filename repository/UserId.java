package com.universityproject.repository;

public class UserId {
   private String mail;


public static UserId userDetails;

private UserId() {
	
}

public static UserId getInstance() {
	if(userDetails==null) {
		userDetails=new UserId();
	}
	return userDetails;
}

public String getMail() {
	return mail;
}

public void setMail(String mail) {
	this.mail = mail;
}
   
}
