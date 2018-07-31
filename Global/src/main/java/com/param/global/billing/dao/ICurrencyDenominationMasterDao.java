package com.param.global.billing.dao;

import com.param.global.billing.dto.CurrencyDenominationMasterDto;
import com.param.global.common.Response;

import in.param.exception.ApplicationException;
@SuppressWarnings("rawtypes")
public interface ICurrencyDenominationMasterDao {
	
	
	Response saveCurrencyDenominationMaster(CurrencyDenominationMasterDto currencyDenominationMasterDto) throws ApplicationException;

	Response getCurrencyDenominationMasterById(int currencyId, int orgId) throws ApplicationException;

	Response getCurrencyDenominationMasterList(CurrencyDenominationMasterDto currencyDenominationMasterDto) throws ApplicationException;

	Response updateCurrencyDenominationMaster(CurrencyDenominationMasterDto currencyDenominationMasterDto) throws ApplicationException;

	Response updateCurrencyDenominationMasterStatus(CurrencyDenominationMasterDto currencyDenominationMasterDto) throws ApplicationException;

	Response getCurrencyDenominationCount(CurrencyDenominationMasterDto currencyDenominationMasterDto) throws ApplicationException;
	
	Response getCurrencyDenominationMasterByName(CurrencyDenominationMasterDto currencyDenominationMasterDto) throws ApplicationException;

	Response getCurrencyDenominationMasterByNameNotById(CurrencyDenominationMasterDto currencyDenominationMasterDto) throws ApplicationException;
}
