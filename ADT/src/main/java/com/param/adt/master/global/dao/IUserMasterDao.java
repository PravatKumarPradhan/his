package com.param.adt.master.global.dao;

import com.param.adt.common.Response;
import com.param.adt.master.global.dto.OrganizationMasterDto;
import com.param.adt.master.global.dto.OrganizationUnitUserMapperDto;
import com.param.adt.master.global.dto.UserMasterDto;

import in.param.exception.ApplicationException;


@SuppressWarnings("rawtypes")
public interface IUserMasterDao {
	Response userLogin(UserMasterDto userMasterDto) throws ApplicationException;
	Response getOrganizationList( )throws ApplicationException;
	Response getUnitFromOrganizationId(OrganizationMasterDto organizationMasterDto)throws ApplicationException;
	Response validateUniqueUserName(UserMasterDto userMasterDto) throws ApplicationException;
	Response getHospitalUnitUserForLogin(OrganizationUnitUserMapperDto organizationUnitUserMapperDto)throws ApplicationException;
}
