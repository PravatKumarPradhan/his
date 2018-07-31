package com.param.adt.common;

public interface IMailConstants {

	//----------Mail constact---------------//
			String SMTP_HOST = "smtpout.secureserver.net";//"smtp.gmail.com";
			String SMTP_PORT = "465";//"465";
			String SMTP_USER_ID = "info@clinivantage.com";//"katkar.yogesh7350@gmail.com";
			String SMTP_USER_PWD = "phs12345";//"Cool1234";
			
			String MAIL_SUBJECT = "Password Reset";
			String MESSAGE = "You recently requested to reset your password for your Clinivantage account. "
							+ "\nBased on request we have successfully changed your password,  "
							+ "\nand your new password is :  ";
			String NOTE = "\n\nNote : Please don't forget to change your password once you logged in to your Clinivantage system from my profile section. "
						+ "\n\nRegards, "
						+ "\nClinivantage Team ";
			
			
			public String SALUTATION = "Hi ";
			
			public String PATIENT_REG_CONTENT = "\nYour Registration as Patient is successful with Clinivantage.\nFurther are the login credentials for Application ";
			public String DOCTOR_REG_CONTENT = "\nYour Registration as Doctor is successful with Clinivantage.\nFurther are the login credentials for Application ";
			public String EMPLOYEE_REG_CONTENT = "\nYour Registration is successful with Clinivantage.\nFurther are the login credentials for Application ";
			
			public String CHANGE_PASSWORD_NOTE = "Note:  Please don't forget to change your password once you logged in to your Clinivantage system from my profile section."
												+ "\n\nRegards, "
												+ "\nClinivantage Team ";
			public String USERNAME = "\nUsername: ";
			public String PASSWORD = "\nPassword: ";
			
			public String REG_SUBJECT = "Confirm Registration";
			
			String PASSWORD_PROTECTED_DOCUMENT_SUBJECT = "Patient Documents";
			public String PASSWORD_PROTECTED_DOCUMENT_MESSAGE = "\n follwing are the list of documents: \n";
			
}
