package com.param.lis.global.dao;

import in.param.exception.ApplicationException;

import java.util.List;

import com.param.lis.global.common.Response;
import com.param.lis.global.dto.OrganismGroupOrganismMapperMasterDto;

@SuppressWarnings("rawtypes")
public interface IOrganismGroupOrganismMapperMasterDao {
	
	public Response addOrganismGroupOrganismMapperMaster(OrganismGroupOrganismMapperMasterDto organismGroupOrganismMapperMasterDto)throws ApplicationException;
	public Response listOrganismGroupOrganismMapperMaster(Integer orgId, Integer offset, Integer recordPerPage) throws ApplicationException;
	public Response getToTalOrganismGroupOrganismMapperMaster(Integer orgId) throws ApplicationException;
	public Response updateOrganismGroupOrganismMapperMaster(List<OrganismGroupOrganismMapperMasterDto> listOrganismGroupOrganismMapperMasterDto) throws ApplicationException;
	public Response activateInactivateOrganismGroupOrganismMapperMaster(Integer orgId,
			Integer organismGroupId, Character status) throws ApplicationException;
	public Response checkOrganismGroupMapperAvaiable(OrganismGroupOrganismMapperMasterDto organismGroupOrganismMapperMasterDto) throws ApplicationException;
	
	public Response getOrganismGroupOrganismMapperMasterById(Integer orgId, Integer OrganismGroupOrganismMapperId) throws ApplicationException;
	public Response getOrganismGroupMasterClassById(Integer  organismGroupId) throws ApplicationException;
	
}
