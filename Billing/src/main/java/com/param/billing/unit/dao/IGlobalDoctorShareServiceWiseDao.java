package com.param.billing.unit.dao;

import com.param.billing.exception.ServiceException;
import com.param.billing.global.model.TGlobalDocShareServiceWise;
import com.param.billing.unit.dto.TGlobalDocShareServiceWiseDto;
import com.param.global.common.Response;

import in.param.common.dao.IGenericDao;
@SuppressWarnings("rawtypes")
public interface IGlobalDoctorShareServiceWiseDao extends IGenericDao<TGlobalDocShareServiceWise, Integer>{
	public Response saveGlobalDoctorShareServiceWiseDao(TGlobalDocShareServiceWiseDto detailDto)throws ServiceException;
}