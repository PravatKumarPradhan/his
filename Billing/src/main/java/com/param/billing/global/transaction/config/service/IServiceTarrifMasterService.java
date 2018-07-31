package com.param.billing.global.transaction.config.service;

import in.param.exception.ApplicationException;

import com.param.billing.common.Response;
import com.param.billing.global.transaction.config.dto.ServiceChargesReqDto;
import com.param.billing.global.transaction.config.dto.VariablePricingDto;

public interface IServiceTarrifMasterService {

	Response saveServiceTarrifMaster(VariablePricingDto variablePricingDto) throws ApplicationException;
	
	Response getServiceTarrifMaster(VariablePricingDto variablePricingDto) throws ApplicationException;
	
	/*METHOD TO GET SERVICE PRICES BY VISIT TYPE & VARIABLE FACTOR*/
	Response listServiceMasterWithBasePriceByVisitType(Integer orgId, Integer unitId, Integer visitTypeId, Integer offset, Integer recordPerPage) throws ApplicationException;
	
	/*GET PRICE DETAILS BY SERVICE ID's*/
	Response getServiceCharges(ServiceChargesReqDto serviceChargesReqDto) throws ApplicationException;
}
