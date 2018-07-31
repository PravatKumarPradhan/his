package com.param.lis.global.service;

import in.param.exception.ApplicationException;

import com.param.lis.global.common.Response;
import com.param.lis.global.dto.ReagentMasterDto;

@SuppressWarnings("rawtypes")
public interface IReagentMasterService {
	public Response addReagentMaster(ReagentMasterDto reagentMasterDto)throws ApplicationException;
	public Response getReagentMasterById(Integer orgId, Integer reagentId) throws ApplicationException;

	public Response updateReagentMaster(ReagentMasterDto reagentMasterDto) throws ApplicationException;
	
	public Response ActivateInactivateReagentMaster(Integer orgId, Integer reagentId, Character reagentStatus) throws ApplicationException;

	public Response listReagentMaster(Integer orgId, Integer offset, Integer recordPerPage) throws ApplicationException;

	public Response getToTalReagentMasterRecord(Integer orgId) throws ApplicationException;
}
