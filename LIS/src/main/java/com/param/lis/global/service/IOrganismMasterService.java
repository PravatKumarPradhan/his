package com.param.lis.global.service;

import in.param.exception.ApplicationException;

import com.param.lis.global.common.Response;
import com.param.lis.global.dto.OrganismMasterDto;

@SuppressWarnings("rawtypes")
public interface IOrganismMasterService {
	
	public Response addOrganismMaster(OrganismMasterDto organismMasterDto)throws ApplicationException;
	public Response updateOrganismMaster(OrganismMasterDto organismMasterDto) throws ApplicationException;
	public Response ActivateInactivateOrganismMaster(Integer orgId, Integer  organismId, Character  organismStatus) throws ApplicationException;
	public Response listOrganismMaster(Integer orgId, Integer offset, Integer recordPerPage) throws ApplicationException;
	public Response getToTalOrganismMaster(Integer orgId) throws ApplicationException;
	public Response getOrganismMasterMasterById(Integer orgId, Integer  organismpId)
			throws ApplicationException;
}
