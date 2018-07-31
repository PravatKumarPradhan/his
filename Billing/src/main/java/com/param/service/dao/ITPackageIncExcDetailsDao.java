package com.param.service.dao;

import com.param.billing.exception.ServiceException;
import com.param.global.common.Response;
import com.param.service.dto.TPackageIncExcDetailsDto;
import com.param.service.global.model.TPackageIncExcDetails;

import in.param.common.dao.IGenericDao;

@SuppressWarnings("rawtypes")
public interface ITPackageIncExcDetailsDao extends IGenericDao<TPackageIncExcDetails, Integer>{
	public Response saveTPackageIncExcDetails(TPackageIncExcDetailsDto detailsDto)throws ServiceException;
}
