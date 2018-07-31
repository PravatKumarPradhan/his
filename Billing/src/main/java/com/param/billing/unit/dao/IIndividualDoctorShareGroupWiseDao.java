package com.param.billing.unit.dao;

import com.param.billing.exception.ServiceException;
import com.param.billing.global.model.TIndividualDocShareGroupWise;
import com.param.billing.unit.dto.TIndividualDocShareGroupWiseDto;
import com.param.global.common.Response;

import in.param.common.dao.IGenericDao;

@SuppressWarnings("rawtypes")
public interface IIndividualDoctorShareGroupWiseDao extends IGenericDao<TIndividualDocShareGroupWise, Integer>{
	public Response saveIndividualDoctorShareGroupWise(TIndividualDocShareGroupWiseDto detailDto)throws ServiceException;
}
