package com.param.global.org.service;

import in.param.exception.ApplicationException;

import com.param.global.common.Response;
import com.param.global.org.dto.UnitMasterDto;

@SuppressWarnings("rawtypes")
public interface IGlobalUnitMasterService {

	
	public Response saveUnitMaster(UnitMasterDto unitMasterDto)throws ApplicationException;
	public Response updateUnitMaster(UnitMasterDto unitMasterDto)throws ApplicationException;
	public Response getUnitById(Integer unitId)throws ApplicationException;
	public Response updateUnitStatusById(UnitMasterDto unitMasterDto)throws ApplicationException;
	public Response getAllUnitList(UnitMasterDto unitMasterDto)throws ApplicationException;
	public Response getUnitTotalCount()throws ApplicationException;
	
}
