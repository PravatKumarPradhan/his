package com.param.billing.unit.dao;

import com.param.billing.exception.ServiceException;
import com.param.billing.global.model.TGlobalDocShareServiceWise;
import com.param.billing.global.model.TIndividualDocShareServiceWise;
import com.param.billing.unit.dto.TGlobalDocShareServiceWiseDto;
import com.param.billing.unit.dto.TIndividualDocShareServiceWiseDto;
import com.param.global.common.Response;

import in.param.common.dao.IGenericDao;
@SuppressWarnings("rawtypes")
public interface IIndividualDoctorShareServiceWiseDao extends IGenericDao<TIndividualDocShareServiceWise, Integer>{
	public Response saveIndividualDocShareServiceWise(TIndividualDocShareServiceWiseDto detailDto)throws ServiceException;
}