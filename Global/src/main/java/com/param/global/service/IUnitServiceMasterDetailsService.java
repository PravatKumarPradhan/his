package com.param.global.service;

import com.param.global.common.Response;
import com.param.global.dto.UnitServiceMasterDetailsDto;

import in.param.exception.ApplicationException;
@SuppressWarnings("rawtypes")
public interface IUnitServiceMasterDetailsService {
	public Response saveUnitServiceMasterDetails(UnitServiceMasterDetailsDto unitServiceMasterDetailsDto)throws ApplicationException;
	public Response getPaginatedUnitServiceMasterDetailsList(UnitServiceMasterDetailsDto unitServiceMasterDetailsDto)throws ApplicationException;
	public Response getUnitServiceMasterDetailsCount(UnitServiceMasterDetailsDto unitServiceMasterDetailsDto)throws ApplicationException;
	public Response getUnitServiceMasterDetailsById(UnitServiceMasterDetailsDto unitServiceMasterDetailsDto)throws ApplicationException;
	public Response updateUnitServiceMasterDetails(UnitServiceMasterDetailsDto unitServiceMasterDetailsDto)throws ApplicationException;
	public Response getAllActiveTax(Integer unitId) throws ApplicationException;
}
