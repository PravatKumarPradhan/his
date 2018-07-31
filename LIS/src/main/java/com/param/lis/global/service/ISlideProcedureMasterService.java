package com.param.lis.global.service;

import in.param.exception.ApplicationException;

import com.param.lis.global.common.Response;
import com.param.lis.global.dto.AntibioticClassMasterDto;
import com.param.lis.global.dto.BacteriaMasterDto;
import com.param.lis.global.dto.SlideProcedureMasterDto;

@SuppressWarnings("rawtypes")
public interface ISlideProcedureMasterService {
	public Response addSlideProcedureMaster(SlideProcedureMasterDto slideProcedureMasterDto)throws ApplicationException;
	public Response updateSlideProcedureMaster(SlideProcedureMasterDto slideProcedureMasterDto) throws ApplicationException;
	public Response ActivateInactivateSlideProcedureMaster(Integer orgId, Integer antibioticId, Character antibioticStatus) throws ApplicationException;
	public Response listSlideProcedureMaster(Integer orgId, Integer offset, Integer recordPerPage) throws ApplicationException;
	public Response getToTalSlideProcedureMaster(Integer orgId) throws ApplicationException;
	public Response getSlideProcedureMasterById(Integer orgId, Integer reagentId)
			throws ApplicationException;
	
	
}
