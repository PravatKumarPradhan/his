package com.param.billing.global.transaction.config.dao;

import com.param.billing.common.Response;
import com.param.billing.global.transaction.config.dto.TarrifPatientCategoryMpprDto;
import com.param.billing.global.transaction.model.ServiceTarrifPatientCategoryManager;

import in.param.common.dao.IGenericDao;
import in.param.exception.ApplicationException;

public interface ITarrifPatientCategoryDao extends IGenericDao<ServiceTarrifPatientCategoryManager, Integer>{

	Response savePatientCategoryMultiplicationFactor(TarrifPatientCategoryMpprDto patientCategoryMpprDto) throws ApplicationException;
	
	Response getPatientCategoryMultiplicationFactorByTarrifId(Integer tarrifMasterId) throws ApplicationException;
	
}
 