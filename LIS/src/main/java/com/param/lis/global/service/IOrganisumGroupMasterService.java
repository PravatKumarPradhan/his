package com.param.lis.global.service;

import in.param.exception.ApplicationException;

import com.param.lis.global.common.Response;
import com.param.lis.global.dto.OrganisumGroupMasterDto;

@SuppressWarnings("rawtypes")
public interface IOrganisumGroupMasterService {
	
	public Response addOrganisumGroupMaster(OrganisumGroupMasterDto organisumGroupMasterDto)throws ApplicationException;
	public Response updateOrganisumGroupMaster(OrganisumGroupMasterDto organisumGroupMasterDto) throws ApplicationException;
	public Response ActivateInactivateOrganisumGroupMaster(Integer orgId, Integer  organisumGroupId, Character  organisumGroupStatus) throws ApplicationException;
	public Response listOrganisumGroupMaster(Integer orgId, Integer offset, Integer recordPerPage) throws ApplicationException;
	public Response getToTalOrganisumGroupMaster(Integer orgId) throws ApplicationException;
	public Response getOrganisumGroupMasterById(Integer orgId, Integer  organisumGroupId)
			throws ApplicationException;
}
