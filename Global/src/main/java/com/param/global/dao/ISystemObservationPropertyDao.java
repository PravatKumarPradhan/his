package com.param.global.dao;

import in.param.common.dao.IGenericDao;
import in.param.exception.ApplicationException;

import com.param.global.common.Response;
import com.param.global.dto.SystemObservationMasterDto;
import com.param.global.model.SystemObervationMaster;

public interface ISystemObservationPropertyDao extends IGenericDao<SystemObervationMaster, Integer>{

/*CRUD FOR SYSTEM OBSERVATION PROPERTY MASTER*/	
	
	@SuppressWarnings("rawtypes")
	public Response saveSystemObservationProperty(SystemObservationMasterDto systemObservationPropertyMasterDto)throws ApplicationException;
	@SuppressWarnings("rawtypes")
	public Response getSystemObservationProperty(SystemObservationMasterDto systemObservationPropertyMasterDto)throws ApplicationException;
	@SuppressWarnings("rawtypes")
	public Response updateSystemObservationPropertyStatus(SystemObservationMasterDto systemObservationPropertyMasterDto)throws ApplicationException;
	public Response updateSystemObservationSinglePropertyStatus(
			SystemObservationMasterDto systemObservationPropertyMasterDto)throws ApplicationException;
}
