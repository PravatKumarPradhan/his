package com.param.billing.global.transaction.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.param.billing.common.ICommonConstants;
import com.param.billing.common.Response;
import com.param.billing.global.transaction.dto.BillingDetailsDto;
import com.param.billing.global.transaction.service.IBillingDetailsService;

@RestController
@RequestMapping(value="api/billing")
@SuppressWarnings("rawtypes")
public class BillingDetailsController implements ICommonConstants{
	
	@Autowired
	private IBillingDetailsService iBillingDetailsService;
	
	@RequestMapping(value="/billingDetails", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public Response saveBillingDetails(@RequestBody BillingDetailsDto billingDetailsDto) {
		try {
			return iBillingDetailsService.saveBillingDetails(billingDetailsDto);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}
}
