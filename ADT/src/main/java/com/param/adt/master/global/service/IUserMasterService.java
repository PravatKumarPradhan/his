package com.param.adt.master.global.service;

import com.param.adt.common.Response;
import com.param.adt.master.global.dto.OrganizationMasterDto;
import com.param.adt.master.global.dto.UserMasterDto;

import in.param.exception.ApplicationException;
@SuppressWarnings("rawtypes")
public interface IUserMasterService 
{
	
	public Response userLogin(UserMasterDto userMasterDto)throws ApplicationException;
	public Response getOrganizationList();
	public Response getUnitListByOrganization(OrganizationMasterDto organizationMasterDto) throws ApplicationException;
	public Response validateUniqueUserName(UserMasterDto userMasterDto);
	
}
