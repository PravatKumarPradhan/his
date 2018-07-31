package com.param.billing.global.transaction.dao;

import com.param.billing.dto.TPackageConsumptionEoDetailsDto;
import com.param.billing.exception.BillingException;
import com.param.billing.packages.model.TPackageConsumptionEoDetails;
import com.param.global.common.Response;

import in.param.common.dao.IGenericDao;

@SuppressWarnings("rawtypes")
public interface ITPackageConsumptionEoDetailsDao extends IGenericDao<TPackageConsumptionEoDetails, Integer>{
	public Response saveTPackageConsumptionEoDetails(TPackageConsumptionEoDetailsDto dto)throws BillingException;
}
