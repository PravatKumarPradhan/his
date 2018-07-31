package com.param.billing.global.transaction.dao;

import com.param.billing.common.Response;
import com.param.billing.global.transaction.dto.BillingDetailsDto;
import com.param.billing.global.transaction.model.BillingDetails;

import in.param.common.dao.IGenericDao;
import in.param.exception.ApplicationException;
@SuppressWarnings("rawtypes")
public interface IBillingDetailsDao extends IGenericDao<BillingDetails, Integer>{
	public Response saveBillingDetails(BillingDetailsDto billingDetailsDto)throws ApplicationException;
}
