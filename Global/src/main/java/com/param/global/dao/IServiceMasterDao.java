package com.param.global.dao;

import java.util.List;

import com.param.global.common.Response;
import com.param.global.dto.ServiceMasterDto;
import com.param.global.model.ServiceMaster;

import in.param.common.dao.IGenericDao;
import in.param.exception.ApplicationException;
@SuppressWarnings("rawtypes")
public interface IServiceMasterDao extends IGenericDao<ServiceMaster, Integer>{
	public Response saveOrganizationServiceMaster(ServiceMasterDto xServiceMasterDto)throws ApplicationException;
	public Response changeOrganizationServiceMasterStatus(ServiceMasterDto xServiceMasterDto)throws ApplicationException;
	public Response listOrganizationServiceMasterMaster(Integer orgId, Integer offset, Integer recordPerPage) throws ApplicationException;
	public Response getOrganizationServiceMasterTotalCount(Integer orgId)throws ApplicationException;
	public Response getOrganizationServiceMasterById(ServiceMasterDto xServiceMasterDto)throws ApplicationException;
	public Response updateOrganizationServiceMaster(ServiceMasterDto xServiceMasterDto)throws ApplicationException;
	public Response getOrganizationServiceMasterBySpecialityAndSubSpeciality(ServiceMasterDto xServiceMasterDto)throws ApplicationException;

	public Response listServiceMasterWithBasePriceByVisitType(Integer orgId, Integer unitId, Integer visitTypeId, Integer offset, Integer recordPerPage) throws ApplicationException;
	public Response listServiceMasterByServiceId(Integer orgId, Integer unitId, List<Integer> listServiceId) throws ApplicationException;
	public Response getServiceDetailsByServiceId(ServiceMasterDto serviceMasterDto)throws ApplicationException;
}
