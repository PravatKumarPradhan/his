package com.param.global.service;

import com.param.global.common.Response;
import com.param.global.dto.ServiceMasterDto;

import in.param.exception.ApplicationException;
@SuppressWarnings("rawtypes")
public interface IServiceMasterService {
	public Response saveOrganizationServiceMaster(ServiceMasterDto xServiceMasterDto)throws ApplicationException;
	public Response changeOrganizationServiceMasterStatus(ServiceMasterDto xServiceMasterDto)throws ApplicationException;
	public Response listOrganizationServiceMasterMaster(Integer orgId, Integer offset, Integer recordPerPage) throws ApplicationException;
	public Response getOrganizationServiceMasterTotalCount(Integer orgId)throws ApplicationException;
	public Response getOrganizationServiceMasterById(ServiceMasterDto xServiceMasterDto)throws ApplicationException;
	public Response updateOrganizationServiceMaster(ServiceMasterDto xServiceMasterDto)throws ApplicationException;
	public Response getOrganizationServiceMasterBySpecialityAndSubSpeciality(ServiceMasterDto xServiceMasterDto)throws ApplicationException;
	public Response getServiceDetailsByServiceId(ServiceMasterDto serviceMasterDto)throws ApplicationException;
}
