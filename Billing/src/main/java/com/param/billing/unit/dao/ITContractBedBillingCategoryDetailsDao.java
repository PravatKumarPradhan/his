package com.param.billing.unit.dao;

import com.param.global.common.Response;
import com.param.service.dto.TContractBedCategoryDetailDto;
import com.param.service.global.model.TContractBedCategoryDetail;

import in.param.common.dao.IGenericDao;
import in.param.exception.ApplicationException;
@SuppressWarnings("rawtypes")
public interface ITContractBedBillingCategoryDetailsDao extends IGenericDao<TContractBedCategoryDetail,Integer> {
	public  Response saveContractBedBillingCategoryDetails(TContractBedCategoryDetailDto tContractBedCategoryDetail)throws ApplicationException;

}
