package com.param.adt.master.global.dao;

import com.param.adt.common.Response;
import com.param.adt.master.global.dto.MaritalStatusDto;

import in.param.exception.ApplicationException;
@SuppressWarnings("rawtypes")
public interface IMaritalStatusMasterDao {

	
	Response getMaritalStatusByName(MaritalStatusDto maritalStatusDto)  throws ApplicationException;

	Response addMaritalStatusMaster(MaritalStatusDto maritalStatusDto) throws ApplicationException;


	Response getMaritalStatusByNameNotId(MaritalStatusDto maritalStatusDto) throws ApplicationException;


	Response updateMaritalStatus(MaritalStatusDto maritalStatusDto) throws ApplicationException;

	Response getMaritalStatusByID(MaritalStatusDto maritalStatusDto) throws ApplicationException;

	Response getActiveMaritalStatusList() throws ApplicationException;

	Response getMaritalStatusMasterList(MaritalStatusDto maritalStatusDto) throws ApplicationException;

	Response updateMaritalStatusMaster(MaritalStatusDto maritalStatusDto) throws ApplicationException;

	Response getCount(MaritalStatusDto maritalStatusDto) throws ApplicationException;

}
