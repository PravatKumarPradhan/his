package com.param.adt.master.global.dao;

import com.param.adt.common.Response;
import com.param.adt.master.global.dto.OrganizationMasterDto;
import com.param.adt.master.global.model.OrganizationMaster;

import in.param.common.dao.IGenericDao;
import in.param.exception.ApplicationException;
@SuppressWarnings("rawtypes")
public interface IOrganizationMasterDao extends IGenericDao<OrganizationMaster, Integer>
{
	public Response getOrganizationMasterList()throws ApplicationException;
	
	public Response saveOrganizationMaster(OrganizationMasterDto OrganizationMasterDto) throws ApplicationException;
	
	public Response updateOrganizationMaster(OrganizationMasterDto OrganizationMasterDto) throws ApplicationException;
	
	public Response getOrganizationByName(OrganizationMasterDto OrganizationMasterDto) throws ApplicationException; 
	
	public Response getOrganizationByNameAndId(OrganizationMasterDto OrganizationMasterDto) throws ApplicationException;
	
	public Response getOrganizationSuggestionByName(OrganizationMasterDto OrganizationMasterDto) throws ApplicationException;
}
