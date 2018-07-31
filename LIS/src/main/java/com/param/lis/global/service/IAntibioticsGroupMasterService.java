package com.param.lis.global.service;

import in.param.exception.ApplicationException;

import com.param.lis.global.common.Response;
import com.param.lis.global.dto.AntibioticGroupMasterDto;

@SuppressWarnings("rawtypes")
public interface IAntibioticsGroupMasterService {
	
	public Response addAntibioticsGroupMaster(AntibioticGroupMasterDto antibioticGroupMasterDto)throws ApplicationException;
	public Response updateAntibioticsGroupMaster(AntibioticGroupMasterDto antibioticGroupMasterDto) throws ApplicationException;
	public Response ActivateInactivateAntibioticsGroupMaster(Integer orgId, Integer antibioticGroupId, Character antibioticGroupStatus) throws ApplicationException;
	public Response listAntibioticsGroupMaster(Integer orgId, Integer offset, Integer recordPerPage) throws ApplicationException;
	public Response getToTalAntibioticsGroupMaster(Integer orgId) throws ApplicationException;
	public Response getAntibioticsGroupMasterById(Integer orgId, Integer antibioticGroupId)
			throws ApplicationException;
}
