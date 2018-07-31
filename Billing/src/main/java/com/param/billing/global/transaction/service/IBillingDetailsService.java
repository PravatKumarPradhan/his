package com.param.billing.global.transaction.service;

import com.param.billing.common.Response;
import com.param.billing.global.transaction.dto.BillingDetailsDto;

import in.param.exception.ApplicationException;
@SuppressWarnings("rawtypes")
public interface IBillingDetailsService {
	public Response saveBillingDetails(BillingDetailsDto billingDetailsDto)throws ApplicationException;
}
