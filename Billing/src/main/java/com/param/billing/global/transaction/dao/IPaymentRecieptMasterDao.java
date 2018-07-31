package com.param.billing.global.transaction.dao;

import com.param.billing.common.Response;
import com.param.billing.global.transaction.dto.PaymentRecieptMasterDto;
import com.param.billing.global.transaction.model.PaymentRecieptMaster;

import in.param.common.dao.IGenericDao;
import in.param.exception.ApplicationException;
@SuppressWarnings("rawtypes")
public interface IPaymentRecieptMasterDao extends IGenericDao<PaymentRecieptMaster, Integer>{
	public Response savePaymentRecieptMaster(PaymentRecieptMasterDto paymentRecieptMasterDto)throws ApplicationException;
}

