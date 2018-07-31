package com.param.adt.master.global.service;

import javax.transaction.Transactional;

import com.param.adt.common.Response;
import com.param.adt.master.global.dto.ConsentMasterDto;

import in.param.exception.ApplicationException;
@SuppressWarnings("rawtypes")
public interface IConsentMasterService {

	@Transactional
	Response addConsentMaster(ConsentMasterDto consentMasterDto) throws ApplicationException;

	@Transactional
	Response updateConsentMaster(ConsentMasterDto consentMasterDto) throws ApplicationException;

	@Transactional
	Response updateStatusForConsent(ConsentMasterDto consentMasterDto)throws ApplicationException;

	@Transactional
	Response getConsentMasterList(ConsentMasterDto consentMasterDto) throws ApplicationException;

	@Transactional
	Response getConsentById(ConsentMasterDto consentMasterDto) throws ApplicationException;

	@Transactional
	Response getActiveConsentList() throws ApplicationException;

	@Transactional
	Response getConsentCount(ConsentMasterDto consentMasterDto) throws ApplicationException;
	
	

}
