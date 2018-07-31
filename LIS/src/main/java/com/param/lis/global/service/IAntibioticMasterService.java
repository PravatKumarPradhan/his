package com.param.lis.global.service;

import in.param.exception.ApplicationException;

import com.param.lis.global.common.Response;
import com.param.lis.global.dto.AntibioticMasterDto;

@SuppressWarnings("rawtypes")
public interface IAntibioticMasterService {
	
	public Response addAntibioticMaster(AntibioticMasterDto antibioticMasterDto)throws ApplicationException;
	public Response updateAntibioticMaster(AntibioticMasterDto antibioticMasterDto) throws ApplicationException;
	public Response getListAntibioticMaster() throws ApplicationException;
	public Response ActivateInactivateAntibioticMaster(Integer orgId, Integer antibioticId, Character antibioticGroupStatus) throws ApplicationException;
	public Response listAntibioticMaster(Integer orgId, Integer offset, Integer recordPerPage) throws ApplicationException;
	public Response getToTalAntibioticMaster(Integer orgId) throws ApplicationException;
	public Response getAntibioticMasterById(Integer orgId, Integer antibioticId)
			throws ApplicationException;
}
