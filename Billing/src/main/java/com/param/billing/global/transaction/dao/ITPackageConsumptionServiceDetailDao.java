package com.param.billing.global.transaction.dao;

import com.param.billing.dto.TPackageConsumptionServiceDetailDto;
import com.param.billing.packages.model.TPackageConsumptionServiceDetail;
import com.param.global.common.Response;

import in.param.common.dao.IGenericDao;

@SuppressWarnings("rawtypes")
public interface ITPackageConsumptionServiceDetailDao extends IGenericDao<TPackageConsumptionServiceDetail, Integer>{
	public Response saveTPackageConsumptionServiceDetail(TPackageConsumptionServiceDetailDto dto)throws Exception;
}