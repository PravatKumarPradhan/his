package com.param.billing.global.transaction.service;

import com.param.global.common.Response;
import com.param.billing.exception.BillingException;
import com.param.billing.global.transaction.dto.BillPayeeDetailsDto;

import in.param.exception.ApplicationException;
@SuppressWarnings("rawtypes")
public interface IBillPayeeDetailsService {
	public Response saveBillPayeeDetails(BillPayeeDetailsDto billPayeeDetailsDto)throws ApplicationException;
	public Response getBillPayeeDetailsByBillId(int billId)throws BillingException;
}

