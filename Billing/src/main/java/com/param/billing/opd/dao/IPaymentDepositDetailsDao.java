package com.param.billing.opd.dao;

import com.param.billing.exception.BillingException;
import com.param.billing.global.dto.PaymentDepositDetailsDto;
import com.param.billing.global.model.PaymentDepositDetails;
import com.param.global.common.Response;

import in.param.common.dao.IGenericDao;

@SuppressWarnings("rawtypes")
public interface IPaymentDepositDetailsDao extends IGenericDao<PaymentDepositDetails, Integer>{
	public Response savePaymentDepositDetails(PaymentDepositDetailsDto dto)throws BillingException;
}
