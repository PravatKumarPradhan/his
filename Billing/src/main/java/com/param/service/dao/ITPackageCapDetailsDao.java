package com.param.service.dao;

import com.param.billing.exception.ServiceException;
import com.param.global.common.Response;
import com.param.service.dto.TPackageCapDetailsDto;
import com.param.service.global.model.TPackageCapDetails;

import in.param.common.dao.IGenericDao;

@SuppressWarnings("rawtypes")
public interface ITPackageCapDetailsDao extends IGenericDao<TPackageCapDetails, Integer>{
	public Response saveTPackageCapDetails(TPackageCapDetailsDto detailsDto)throws ServiceException;
	public Response getPackageCapDetailsByPackageIdForBilling(int packageId)throws ServiceException;
	public Response getPackageCapDetailsByPackageId(int packageId)throws ServiceException;
}
