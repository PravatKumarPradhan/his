package com.param.billing.global.transaction.config.dao;

import com.param.billing.common.Response;
import com.param.billing.global.transaction.config.dto.VariablePricingDto;
import com.param.billing.global.transaction.model.ServiceTarrifMaster;

import in.param.common.dao.IGenericDao;
import in.param.exception.ApplicationException;

public interface IServiceTarrifMasterDao extends IGenericDao<ServiceTarrifMaster, Integer>{

	Response saveServiceTarrifMaster(VariablePricingDto variablePricingDto) throws ApplicationException;
	
	Response updateServiceTarrifMaster(VariablePricingDto variablePricingDto) throws ApplicationException;
	
	Response getServiceTarrifMaster(VariablePricingDto variablePricingDto) throws ApplicationException;
	
	Response getVariableFactorDetailsByVisitType(VariablePricingDto variablePricingDto) throws ApplicationException;
}
