package com.param.global.dao;

import in.param.common.dao.IGenericDao;
import in.param.exception.ApplicationException;

import com.param.global.common.Response;
import com.param.global.dto.SystemObservationMasterDto;
import com.param.global.model.SystemObervationPropertyMaster;

public interface ISystemPropertyMasterDao extends IGenericDao<SystemObervationPropertyMaster, Integer>{

	@SuppressWarnings("rawtypes")
	public Response saveSystemObservationProperty(SystemObervationPropertyMaster systemObervationPropertyMaster)throws ApplicationException;
}
