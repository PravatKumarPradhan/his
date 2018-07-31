package com.param.adt.common;

public interface IUserLoginConstants {

	public String INVALID_USER_CODE = "101";
	public String INVALID_USER_MSG = "Invalid user name";
	
	public String VALID_USER_CODE = "100";
	public String VALID_USER_MSG = "";
	
	public String INVALID_PWD_CODE = "102";
	public String INVALID_PWD_MSG = "Invalid Password";
	
	public String VALID_PWD_CODE = "103";
	public String VALID_PWD_MSG = "Login Successful";
	
	public String INVALID_MAIL_ID_MSG = "This email-id is not registered in system \nplease check your email-id.";
	public String ERROR_UPDATION_PASSWORD = "Error occurred during updating password \nplease try after sometime.";
	public String PASSWORD_CHANGE_MAIL_SENT_SUCCESS = "Your password has been successfully changed \nplease check your mail inbox for new password.";
	public String DOCUMENT_MAIL_SENT_SUCCESS = "Document has been sent, \nplease check your mail.";
}
