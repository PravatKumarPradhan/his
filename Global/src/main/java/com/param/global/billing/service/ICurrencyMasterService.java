package com.param.global.billing.service;

import javax.transaction.Transactional;

import com.param.global.billing.dto.CurrencyMasterDto;
import com.param.global.common.Response;

import in.param.exception.ApplicationException;

@SuppressWarnings("rawtypes")
@Transactional
public interface ICurrencyMasterService {
	
	Response saveCurrencyMaster(CurrencyMasterDto currencyMasterDto) throws ApplicationException;

	Response getCurrencyMasterById(int currencyId, int orgId) throws ApplicationException;

	Response getCurrencyMasterList(CurrencyMasterDto currencyMasterDto) throws ApplicationException;

	Response updateCurrencyMaster(CurrencyMasterDto currencyMasterDto) throws ApplicationException;

	Response updateCurrencyMasterStatus(CurrencyMasterDto currencyMasterDto) throws ApplicationException;

	Response getCurrencyCount(CurrencyMasterDto currencyMasterDto) throws ApplicationException;

}
