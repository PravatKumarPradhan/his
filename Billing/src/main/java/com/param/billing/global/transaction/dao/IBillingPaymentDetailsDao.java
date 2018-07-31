package com.param.billing.global.transaction.dao;

import com.param.billing.common.Response;
import com.param.billing.exception.BillingException;
import com.param.billing.global.dto.BillingPaymentDetailsReqDto;
import com.param.billing.global.transaction.dto.BillingPaymentDetailsDto;
import com.param.billing.global.transaction.model.BillingPaymentDetails;

import in.param.common.dao.IGenericDao;
import in.param.exception.ApplicationException;
@SuppressWarnings("rawtypes")
public interface IBillingPaymentDetailsDao extends IGenericDao<BillingPaymentDetails, Integer>{
	public Response saveBillingPaymentDetails(BillingPaymentDetailsDto billingPaymentDetailsDto)throws ApplicationException;
	public com.param.global.common.Response savePayment(BillingPaymentDetailsReqDto reqDto)throws BillingException;
}

