package com.param.global.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import in.param.exception.ApplicationException;

import com.param.global.common.ICommonConstants;
import com.param.global.common.IError;
import com.param.global.common.Response;
import com.param.global.dto.PaymentEntitlementMasterDto;
import com.param.global.service.IPaymentEntitlementMasterService;

@RestController
@RequestMapping(value="/global")
public class PaymentEntitlementController implements ICommonConstants, IError{

	@Autowired
	IPaymentEntitlementMasterService iPaymentEntitlementMasterService;
	
	@RequestMapping(value="/getPaymentEntitlement", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public Response getPaymentEntitlement(PaymentEntitlementMasterDto paymentEntitlementMasterDto){
		try{
			return iPaymentEntitlementMasterService.getPaymentEntitlementMaster(paymentEntitlementMasterDto);
		}catch(ApplicationException ae){
			ae.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}
	
	@RequestMapping(value="/paymententitlement/{orgId}", method=RequestMethod.GET)
	public Response getPaymentEntitlementByOrg(@PathVariable("orgId")int orgId){
		try{
			return iPaymentEntitlementMasterService.getPaymentEntitlementListByOrgId(orgId);
		}catch(ApplicationException ae){
			ae.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}
	
	
}
