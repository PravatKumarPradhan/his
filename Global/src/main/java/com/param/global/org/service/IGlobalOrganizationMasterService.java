package com.param.global.org.service;

import in.param.exception.ApplicationException;

import com.param.global.common.Response;
import com.param.global.org.dto.OrganizationMasterDto;
import com.param.global.org.dto.OrganizationUnitLicenceDetailsDto;

@SuppressWarnings("rawtypes")
public interface IGlobalOrganizationMasterService {
	
	public Response saveOrganizationMaster(OrganizationMasterDto organizationMasterDto)throws ApplicationException;
	public Response updateOrganizationMaster(OrganizationMasterDto organizationMasterDto)throws ApplicationException;
	public Response getOrganizationById(int organizationId)throws ApplicationException;
	public Response getAllOrganizationMasterList(OrganizationMasterDto organizationMasterDto)throws ApplicationException;
	public Response getOrganizationCount()throws ApplicationException;
	public Response updateOrganizationStatus(OrganizationMasterDto organizationMasterDto)throws ApplicationException;
	public Response getLicenceDetailsByOrgUnitId(OrganizationUnitLicenceDetailsDto organizationUnitLicenceDetailsDto)throws ApplicationException;
	public Response getActiveOrganizationList() throws ApplicationException;
}
