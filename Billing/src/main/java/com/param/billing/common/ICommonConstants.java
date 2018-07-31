package com.param.billing.common;

public interface ICommonConstants {
	String SUCCESS = "success";
	String SUCCESS_CODE = "200";
	String ALREADY_EXIST_CODE = "409";
	String SUCCESS_MESSAGE = "Added Successfully.";
	String ERROR = "error";
	
	boolean TRUE = true;
	boolean FALSE = false;
	
	char ACTIVE = 'A';
	char INACTIVE = 'I';
	
	Object NULL = null;
	
	public Integer TYPE_INSURANCE = 2;
	public Integer TYPE_CREDIT_COMPANY = 3;
	
	/*MESSAGE ON JSP*/
	String COMMON_SUCCESS="Added Successfully.";
	String COMMON_DELETE="Deleted Successfully.";
	String COMMON_UPDATE="Updated Successfully.";
	String ALREADY_EXIST="Already Exists.";
	String COMMON_APPROVED="Approved Successfully";
	String ALREADY_APPROVED="Already Approved";
	String COMMON_ERROR="Fail";
	String COMMON_ERROR_CODE="501";
	String COMMON_ERROR_MESSAGE="Something went wrong!!";
	
	String PACKAGE_TYPE_ERROR = "Package Type List Not Found";
}
