package com.param.billing.unit.service;

import com.param.global.common.Response;
import com.param.billing.exception.BillingException;
import com.param.billing.global.dto.BillPaymentCollectionReqDto;

@SuppressWarnings({"rawtypes"})
public interface IBillingPaymentDetailsService {
	public Response collectPayment(BillPaymentCollectionReqDto reqDto)throws BillingException;
}
