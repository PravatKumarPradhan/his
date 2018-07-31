package com.param.lis.unit.dao;

import in.param.exception.ApplicationException;

import com.param.lis.global.common.Response;
import com.param.lis.unit.dto.ParameterMasterDto;

@SuppressWarnings("rawtypes")
public interface IParameterMasterDao
{

	public Response addParameter(ParameterMasterDto parameterMasterDto) throws ApplicationException;

	public Response getParameterById(Integer orgId, Integer orgUnitId,Integer parameterId) throws ApplicationException;

	public Response updateParameter(ParameterMasterDto parameterMasterDto) throws ApplicationException;

	public Response activeInactiveParameter( Integer orgId,
			Integer parameterId,
			Character paramStatus)
			throws ApplicationException;

	public Response listParameterMaster(Integer orgId, Integer orgUnitId, Integer offset, Integer recordPerPage)
			throws ApplicationException;

	public Response getTotalParameterMasterRecord(Integer orgId, Integer orgUnitId) throws ApplicationException;
	
	public Response getHelpValueByParameter(Integer orgId,Integer orgUnitId, Integer parameterId) throws ApplicationException;
	
	public Response getRefReangeByParameter(Integer parameterId,Integer refrangeTypeId,Integer orgId,Integer orgUnitId) throws ApplicationException;
}
