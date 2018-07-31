package com.param.lis.global.service;

import in.param.exception.ApplicationException;

import com.param.lis.global.common.Response;
import com.param.lis.global.dto.OrganismGroupOrganismMapperMasterDto;

@SuppressWarnings("rawtypes")
public interface IOrganismGroupOrganismMapperMasterService {
	
	public Response addOrganismGroupOrganismMapperMaster(OrganismGroupOrganismMapperMasterDto organismGroupOrganismMapperMasterDto)throws ApplicationException;
	public Response listOrganismGroupOrganismMapperMaster(Integer orgId, Integer offset, Integer recordPerPage) throws ApplicationException;
	public Response getToTalOrganismGroupOrganismMapperMaster(Integer orgId) throws ApplicationException;
	public Response updateOrganismGroupOrganismMapperMaster(OrganismGroupOrganismMapperMasterDto organismGroupOrganismMapperMasterDto) throws ApplicationException;
	public Response activateInactivateOrganismGroupOrganismMapperMaster(Integer orgId,
			Integer organismGroupOrganismMpprId, Character status) throws ApplicationException;
	public Response getOrganismGroupOrganismMapperMasterById(Integer orgId, Integer organismGroupOrganismMpprId) throws ApplicationException;

	public Response getOrganismGroupOrganismClassById(Integer organismGroupId) throws ApplicationException;
	
}
