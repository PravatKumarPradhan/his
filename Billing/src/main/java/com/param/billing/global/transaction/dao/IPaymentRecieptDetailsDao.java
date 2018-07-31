package com.param.billing.global.transaction.dao;

import com.param.billing.common.Response;
import com.param.billing.global.transaction.dto.PaymentReceiptDetailsDto;
import com.param.billing.global.transaction.model.PaymentReceiptDetails;

import in.param.common.dao.IGenericDao;
import in.param.exception.ApplicationException;
@SuppressWarnings("rawtypes")
public interface IPaymentRecieptDetailsDao extends IGenericDao<PaymentReceiptDetails, Integer>{
	public Response savePaymentRecieptDetails(PaymentReceiptDetailsDto paymentReceiptDetailsDto)throws ApplicationException;
}
