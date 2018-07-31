package com.param.global.billing.dao;

import com.param.global.billing.dto.CurrencyMasterDto;
import com.param.global.common.Response;
import in.param.exception.ApplicationException;

@SuppressWarnings("rawtypes")
public interface ICurrencyMasterDao {

	Response saveCurrencyMaster(CurrencyMasterDto currencyMasterDto) throws ApplicationException;

	Response getCurrencyMasterByName(CurrencyMasterDto currencyMasterDto) throws ApplicationException;

	Response getCurrencyMasterById(int currencyId, int orgId) throws ApplicationException;

	Response getCurrencyMasterList(CurrencyMasterDto currencyMasterDto) throws ApplicationException;

	Response updateCurrencyMaster(CurrencyMasterDto currencyMasterDto) throws ApplicationException;

	Response updateCurrencyMasterStatus(CurrencyMasterDto currencyMasterDto) throws ApplicationException;

	Response getCurrencyMasterByNameNotId(CurrencyMasterDto currencyMasterDto) throws ApplicationException;

	Response getCurrencyCount(CurrencyMasterDto currencyMasterDto) throws ApplicationException;
}
