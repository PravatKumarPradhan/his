package com.param.billing.unit.dao;

import com.param.billing.exception.ServiceException;
import com.param.billing.global.model.TGlobalDocShareGroupWise;
import com.param.billing.unit.dto.TGlobalDocShareGroupWiseDto;
import com.param.global.common.Response;

import in.param.common.dao.IGenericDao;
import in.param.exception.ApplicationException;

@SuppressWarnings("rawtypes")
public interface IGlobalDoctorShareGroupWiseDao extends IGenericDao<TGlobalDocShareGroupWise, Integer>{
	public Response saveGlobalDoctorShareGroupWiseDao(TGlobalDocShareGroupWiseDto detailDto)throws ServiceException;
	
	public Response getDoctorGradeList(Integer orgId,Integer orgUnitId) throws ApplicationException;
}
