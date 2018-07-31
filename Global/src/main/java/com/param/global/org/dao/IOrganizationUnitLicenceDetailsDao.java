package com.param.global.org.dao;

import in.param.common.dao.IGenericDao;
import in.param.exception.ApplicationException;

import com.param.entity.org.model.OrganizationUnitLicenceDetails;
import com.param.global.common.Response;
import com.param.global.org.dto.OrganizationUnitLicenceDetailsDto;

@SuppressWarnings("rawtypes")
public interface IOrganizationUnitLicenceDetailsDao extends IGenericDao<OrganizationUnitLicenceDetails, Integer>{

	public Response saveOrganizationUnitLicenceDetails(OrganizationUnitLicenceDetailsDto organizationUnitLicenceDetailsDto)throws ApplicationException;
	public Response getLicenceDetailsByOrgUnitId(OrganizationUnitLicenceDetailsDto organizationUnitLicenceDetailsDto)throws ApplicationException;
}
