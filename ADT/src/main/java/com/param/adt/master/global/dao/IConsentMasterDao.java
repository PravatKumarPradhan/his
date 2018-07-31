package com.param.adt.master.global.dao;

import com.param.adt.common.Response;
import com.param.adt.master.global.dto.ConsentMasterDto;
import com.param.adt.master.global.model.ConsentMaster;

import in.param.common.dao.IGenericDao;
import in.param.exception.ApplicationException;
@SuppressWarnings("rawtypes")
public interface IConsentMasterDao extends IGenericDao<ConsentMaster, Integer>
{

	
	Response addConsentMaster(ConsentMasterDto consentMasterDto) throws ApplicationException;

	Response updateConsentMaster(ConsentMasterDto consentMasterDto);
	
	Response getConsentByName(ConsentMasterDto consentMasterDto)throws ApplicationException;

	Response getConsentByNameNotId(ConsentMasterDto consentMasterDto) throws ApplicationException;

	Response getConsentById(ConsentMasterDto consentMasterDto) throws ApplicationException;

	Response updateConsentStatus(ConsentMasterDto consentMasterDto) throws ApplicationException;

	Response getActiveConsentList() throws ApplicationException;

	Response getConsentMasterList(ConsentMasterDto consentMasterDto) throws ApplicationException;

	Response getCount(ConsentMasterDto consentMasterDto) throws ApplicationException;

	
}
