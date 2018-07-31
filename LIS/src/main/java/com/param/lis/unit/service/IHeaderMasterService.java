package com.param.lis.unit.service;

import com.param.lis.global.common.Response;
import com.param.lis.unit.dto.HeaderMasterDto;

import in.param.exception.ApplicationException;

@SuppressWarnings("rawtypes")
public interface IHeaderMasterService
{
	public Response addHeaderMaster(HeaderMasterDto headerMasterDto) throws ApplicationException;

	public Response getHeaderMasterById(Integer orgId,Integer orgUnitId, Integer headerId) throws ApplicationException;

	public Response updateHeaderMaster(HeaderMasterDto headerMasterDto) throws ApplicationException;
	
	public Response ActivateInactivateHeaderMaster(Integer orgId, Integer headerId, Character headerStatus) throws ApplicationException;

	public Response listHeaderMaster(Integer orgId, Integer offset, Integer recordPerPage) throws ApplicationException;

	public Response getToTalHeaderMasterRecord(Integer orgId) throws ApplicationException;

}
