package com.param.adt.master.global.dao;

import com.param.adt.common.Response;
import com.param.adt.master.global.dto.ConsentTypeMasterDto;
import com.param.adt.master.global.model.ConsentTypeMaster;

import in.param.common.dao.IGenericDao;
import in.param.exception.ApplicationException;
@SuppressWarnings("rawtypes")
public interface IConsentTypeMasterDao extends IGenericDao<ConsentTypeMaster, Integer>{

	
	Response getConsentTypeByName(ConsentTypeMasterDto consentTypeMasterDto) throws ApplicationException;

	Response addConsentTypeMaster(ConsentTypeMasterDto consentTypeMasterDto) throws ApplicationException;

	Response getConsentTypeByNameNotId(ConsentTypeMasterDto consentTypeMasterDto) throws ApplicationException;

	Response updateConsentTypeMaster(ConsentTypeMasterDto consentTypeMasterDto) throws ApplicationException;

	Response getConsentTypeById(ConsentTypeMasterDto consentTypeMasterDto) throws ApplicationException;

	Response updateConsentTypeStatus(ConsentTypeMasterDto consentTypeMasterDto) throws ApplicationException;

	Response getConsentTypeMasterList(ConsentTypeMasterDto consentTypeMasterDto) throws ApplicationException;

	Response getActiveConsentTypeList() throws ApplicationException;

	Response getCount(ConsentTypeMasterDto consentTypeMasterDto) throws ApplicationException;


}
