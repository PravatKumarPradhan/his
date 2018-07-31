package com.param.global.org.dao;

import com.param.global.common.Response;
import com.param.global.org.dto.OrganizationMasterDto;

import in.param.exception.ApplicationException;

@SuppressWarnings("rawtypes")
public interface IGlobalOrganisationMasterDao {

	
	Response getGlobalOrganisationMasterForSyncById(Integer organisationId) throws ApplicationException;
	public Response saveOrganizationMaster(OrganizationMasterDto organizationMasterDto)throws ApplicationException;
	public Response updateOrganizationMaster(OrganizationMasterDto organizationMasterDto)throws ApplicationException;
	public Response getOrganizationByName(String organizationName) throws ApplicationException;
	public Response getOrganizationById(Integer organizationId)throws ApplicationException;
	public Response getOrganizationByNameAndNotById(OrganizationMasterDto organizationMasterDto) throws ApplicationException;
	public Response getAllOrganizationMasterList(OrganizationMasterDto organizationMasterDto)throws ApplicationException;
	public Response getOrganizationCount()throws ApplicationException;
	public Response updateOrganizationStatus(OrganizationMasterDto organizationMasterDto)throws ApplicationException;
	public Response getActiveOrganizationList()throws ApplicationException;

}
