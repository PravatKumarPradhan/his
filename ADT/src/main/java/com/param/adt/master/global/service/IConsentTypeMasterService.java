package com.param.adt.master.global.service;

import javax.transaction.Transactional;

import com.param.adt.common.Response;
import com.param.adt.master.global.dto.ConsentTypeMasterDto;

import in.param.exception.ApplicationException;
@SuppressWarnings("rawtypes")
public interface IConsentTypeMasterService {

	@Transactional
	Response addConsentTypeMaster(ConsentTypeMasterDto consentTypeMasterDto) throws ApplicationException;

	@Transactional
	Response getConsentTypeMasterList(ConsentTypeMasterDto consentTypeMasterDto) throws ApplicationException;

	@Transactional
	Response updateConsentTypeMaster(ConsentTypeMasterDto consentTypeMasterDto) throws ApplicationException;

	@Transactional
	Response getConsentTypeById(ConsentTypeMasterDto consentTypeMasterDto) throws ApplicationException;

	@Transactional
	Response updateStatusForConsentType(ConsentTypeMasterDto consentTypeMasterDto)throws ApplicationException;

	@Transactional
	Response getActiveConsentTypeList() throws ApplicationException;

	@Transactional
	Response getConsentTypeCount(ConsentTypeMasterDto consentTypeMasterDto) throws ApplicationException;

}
