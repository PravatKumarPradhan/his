package com.param.lis.global.service;

import in.param.exception.ApplicationException;

import com.param.lis.global.common.Response;
import com.param.lis.global.dto.AntibioticOrganismMpprMasterDto;

@SuppressWarnings("rawtypes")
public interface IAntibioticOrganismMpprMasterService {
	
	public Response addAntibioticOrganismMpprMaster(AntibioticOrganismMpprMasterDto antibioticOrganismMpprMasterDto)throws ApplicationException;
	public Response listAntibioticOrganismMpprMaster(Integer orgId, Integer offset, Integer recordPerPage) throws ApplicationException;
	public Response getToTalAntibioticOrganismMpprMaster(Integer orgId) throws ApplicationException;
	public Response updateAntibioticOrganismMpprMaster(AntibioticOrganismMpprMasterDto antibioticOrganismMpprMasterDto) throws ApplicationException;
	public Response activateInactivateAntibioticOrganismMpprMaster(Integer orgId,
			Integer organnismId, Character status) throws ApplicationException;
	public Response getAntibioticOrganismMpprMasterById(Integer orgId, Integer antibioticOrganismMpprId) throws ApplicationException;

	public Response getAntibioticByOrganismId(Integer organnismId) throws ApplicationException;
	
}
