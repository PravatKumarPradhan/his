package com.param.billing.unit.dao;

import org.springframework.context.ApplicationContextException;

import com.param.billing.global.transaction.model.UnitServiceTariffMaster;
import com.param.global.common.Response;
import com.param.global.dto.UnitServiceTariffMasterDto;

import in.param.common.dao.IGenericDao;

public interface IUnitServiceTariffMasterDao extends IGenericDao<UnitServiceTariffMaster, Integer>{

	Response saveUnitServiceTariffMaster(UnitServiceTariffMasterDto unitServiceTariffMasterDto)throws ApplicationContextException;

	Response getUnitServiceTariffsUniqueness(UnitServiceTariffMasterDto unitServiceTariffMasterDto)throws ApplicationContextException;

	UnitServiceTariffMasterDto getBasePriceByServiceTariffMaster(UnitServiceTariffMasterDto unitServiceTariffMasterDto)throws ApplicationContextException;

	Response getBasePriceOfServicesFromTariffMasterByServiceList(UnitServiceTariffMasterDto unitServiceTariffMasterDto)throws ApplicationContextException;

}
