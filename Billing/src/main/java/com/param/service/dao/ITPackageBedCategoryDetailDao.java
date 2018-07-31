package com.param.service.dao;

import com.param.billing.exception.ServiceException;
import com.param.global.common.Response;
import com.param.service.dto.TPackageBedCategoryDetailDto;
import com.param.service.global.model.TPackageBedCategoryDetail;

import in.param.common.dao.IGenericDao;

@SuppressWarnings("rawtypes")
public interface ITPackageBedCategoryDetailDao extends IGenericDao<TPackageBedCategoryDetail, Integer>{
	public Response saveTPackageBedCategoryDetail(TPackageBedCategoryDetailDto detailDto)throws ServiceException;
}
