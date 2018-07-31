package com.param.billing.unit.service;

import javax.transaction.Transactional;
import com.param.global.common.Response;
import com.param.global.dto.UnitServiceTariffMasterDto;
import in.param.exception.ApplicationException;

@SuppressWarnings("rawtypes")
public interface IUnitServiceTariffMasterService {

	@Transactional
	Response getMasterServiceListByMultipleParameters(UnitServiceTariffMasterDto unitServiceTariffMasterDto)throws ApplicationException;

	@Transactional
	Response saveUnitServiceTariffMaster(UnitServiceTariffMasterDto unitServiceTariffMasterDto)throws ApplicationException;
	
	@Transactional
	UnitServiceTariffMasterDto getBasePriceByServiceTariffMaster(UnitServiceTariffMasterDto unitServiceTariffMasterDto)throws ApplicationException;

	@Transactional
	Response getBasePriceOfServicesFromTariffMasterByServiceList(UnitServiceTariffMasterDto unitServiceTariffMasterDto)throws ApplicationException;

}
