package com.param.adt.master.global.service;

import javax.transaction.Transactional;

import com.param.adt.common.Response;
import com.param.adt.master.global.dto.MaritalStatusDto;

import in.param.exception.ApplicationException;
@SuppressWarnings("rawtypes")
public interface IMaritalStatusMasterService {

	
	@Transactional
	Response addMaritalStatusMaster(MaritalStatusDto maritalStatusDto) throws ApplicationException;

	@Transactional
	Response getMaritalById(MaritalStatusDto maritalStatusDto) throws ApplicationException;

	@Transactional
	Response updateMaritalStatusMaster(MaritalStatusDto maritalStatusDto) throws ApplicationException;

	@Transactional
	Response updateStatusForMarital(MaritalStatusDto maritalStatusDto) throws ApplicationException;

	@Transactional
	Response getActiveMaritalStatusList() throws ApplicationException;

	@Transactional
	Response getMaritalStatusMasterList(MaritalStatusDto maritalStatusDto) throws ApplicationException;

	@Transactional
	Response getMaritalStatusCount(MaritalStatusDto maritalStatusDto)throws ApplicationException;

}
