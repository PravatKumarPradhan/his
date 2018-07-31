package com.param.service.dao;

import com.param.billing.exception.ServiceException;
import com.param.global.common.Response;
import com.param.service.dto.TPackageServicesDetailsDto;
import com.param.service.global.model.TPackageServicesDetails;

import in.param.common.dao.IGenericDao;

@SuppressWarnings("rawtypes")
public interface ITPackageServicesDetailsDao extends IGenericDao<TPackageServicesDetails, Integer>{
	public Response saveTPackageServicesDetails(TPackageServicesDetailsDto detailsDto)throws ServiceException;
	public Response getPackageServicesByPackageId(int packageId)throws ServiceException;
}
