package com.param.service.dao;

import com.param.billing.exception.ServiceException;
import com.param.global.common.Response;
import com.param.service.dto.TPackageCategoryWiseConsumableDetailsDto;
import com.param.service.global.model.TPackageCategoryWiseConsumableDetails;

import in.param.common.dao.IGenericDao;

@SuppressWarnings("rawtypes")
public interface ITPackageCategoryWiseConsumableDetailsDao extends IGenericDao<TPackageCategoryWiseConsumableDetails, Integer>{
	public Response saveTPackageCategoryWiseConsumableDetails(TPackageCategoryWiseConsumableDetailsDto detailsDto)throws ServiceException;
}
