package com.param.adt.master.global.service;

import javax.transaction.Transactional;

import com.param.adt.common.Response;
import com.param.adt.master.global.dto.IdentificationMasterDto;

import in.param.exception.ApplicationException;
@SuppressWarnings("rawtypes")
public interface IIdentificationMasterService 
{

	@Transactional
	Response addIdentificationMaster(IdentificationMasterDto identificationMasterDto) throws ApplicationException;

	@Transactional
	Response getIdentificationMasterList(IdentificationMasterDto countryMasterDto)throws ApplicationException;

	@Transactional
	Response updateIdentificationMaster(IdentificationMasterDto identificationMasterDto) throws ApplicationException;

	@Transactional
	Response getIdentificationById(IdentificationMasterDto identificationMasterDto) throws ApplicationException;

	@Transactional
	Response updateStatusForIdentification(IdentificationMasterDto identificationMasterDto) throws ApplicationException;

	@Transactional
	Response getActiveIdentificationList() throws ApplicationException;

	@Transactional
	Response getIdentificationCount(IdentificationMasterDto identificationMasterDto) throws ApplicationException;

}
