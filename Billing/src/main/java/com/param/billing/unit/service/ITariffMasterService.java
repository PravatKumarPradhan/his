package com.param.billing.unit.service;

import javax.transaction.Transactional;

import com.param.global.common.Response;
import com.param.global.dto.TariffMasterDto;

import in.param.exception.ApplicationException;

@SuppressWarnings("rawtypes")

public interface ITariffMasterService {

	@Transactional
	Response saveTariffMaster(TariffMasterDto tariffMasterDto)throws ApplicationException;

	@Transactional
	Response getTariffMasterList(TariffMasterDto tariffMasterDto)throws ApplicationException;

	@Transactional
	Response getPaymentEntitlementListByTariffid(TariffMasterDto tariffMasterDto)throws ApplicationException;

	@Transactional
	Response getTariffListById(TariffMasterDto tariffMasterDto)throws ApplicationException;

	@Transactional
	Response updateTariffMaster(TariffMasterDto tariffMasterDto)throws ApplicationException;

	@Transactional
	Response updateStatusForTariff(TariffMasterDto tariffMasterDto)throws ApplicationException;

	@Transactional
	Response updateStatusForTariffPaymentMapper(TariffMasterDto tariffMasterDto)throws ApplicationException;

	@Transactional
	Response getActiveTariffMasterList(TariffMasterDto tariffMasterDto)throws ApplicationException;

	@Transactional
	Response getPaymentEntitlementListByTariffIdList(TariffMasterDto tariffMasterDto)throws ApplicationException;

}
