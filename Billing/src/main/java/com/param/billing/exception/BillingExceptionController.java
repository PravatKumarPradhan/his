package com.param.billing.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;

@ControllerAdvice
@SuppressWarnings("rawtypes")
public class BillingExceptionController {

	@ExceptionHandler(value = BillingException.class)
	public @ResponseBody Response handleBillEx(BillingException be){
		return new Response<>(ICommonConstants.ERROR, ICommonConstants.BILLING_ERROR_CODE, ICommonConstants.COMMON_ERROR_MESSAGE , null, null,be.getMessage());
	}
	
	@ExceptionHandler(value = ServiceException.class)
	public @ResponseBody Response handleServEx(ServiceException se){
		return new Response<>(ICommonConstants.ERROR, ICommonConstants.SERVICE_ERROR_CODE, ICommonConstants.COMMON_ERROR_MESSAGE , null, null,se.getMessage());
	}
}
