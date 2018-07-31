package com.param.billing.global.transaction.dao;

import com.param.billing.dto.TPackageConsumptionCapDetailsDto;
import com.param.billing.exception.BillingException;
import com.param.billing.packages.model.TPackageConsumptionCapDetails;
import com.param.global.common.Response;

import in.param.common.dao.IGenericDao;

@SuppressWarnings("rawtypes")
public interface ITPackageConsumptionCapDetailsDao extends IGenericDao<TPackageConsumptionCapDetails, Integer>{
	public Response saveTPackageConsumptionCapDetails(TPackageConsumptionCapDetailsDto dto)throws BillingException;
}
