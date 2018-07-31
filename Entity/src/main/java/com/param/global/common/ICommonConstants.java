package com.param.global.common;

public interface ICommonConstants {
	String SUCCESS = "success";
	String SUCCESS_CODE = "200";
	String SUCCESS_MESSAGE = "Added Successfully.";
	String ERROR = "error";
	
	public static final String ERROR_CODE = "500";
	
	
	public static final String SERVER_ERROR = "Server error";
	public static final String SERVER_ERROR_CODE = "500";
	
	boolean TRUE = true;
	boolean FALSE = false;
	
	char ACTIVE = 'A';
	char INACTIVE = 'I';
	
	char YES = 'Y';
	char NO = 'N';
	
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
	String ALREADY_EXIST_CODE = "409";
	String NOT_FOUND_CODE = "404";
	
	//---------Global Service Master----------//
		String SERVICE_NAME_ALREADY_EXIST = "Service Name Already Exist.";
	
	//billing codes and messages
	String BILLING_ERROR_CODE = "600";
	String SERVICE_ERROR_CODE = "700";
	String DEPOSIT_ADDED_MSG = "Amount deposited successfully your deposit no is ";
	
	String PAYMENT_COLLECTED = "Payment collected successfully";
	String EHC_PACKAGE_CREATED_MSG = "Ehc Package Created";
	String EITHER_OR_PACKAGE_CREATED = "Either Or Package Created";
	String PACKAGE_WITH_CAP_CREATED = "Package With Cap Created";
	String MULTIENCOUNTER_PACKAGE_CREATED = "Multiencounter Package Created";
	String ALL_EXCLUSIVE_PACKAGE_CREATED = "All Exclusive Package Created";
	
	String COMPANY_CONTRACT_CREATED = "Company Contarct Created";
	String PACKAGE_NOT_FOUND = "Package Not Found";
	String DOCTOR_SHARE = "Doctor share successfully";
}
