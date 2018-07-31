package com.param.global.billing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.param.global.billing.dao.ICurrencyDenominationMasterDao;
import com.param.global.billing.dto.CurrencyDenominationMasterDto;
import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;

import in.param.exception.ApplicationException;

@Service

@SuppressWarnings("rawtypes")
public class CurrencyDenominationMasterServiceImpl implements ICurrencyDenominationMasterService, ICommonConstants {

	@Autowired
	ICurrencyDenominationMasterDao iCurrencyDenominationMasterDao;

	@Override
	public Response saveCurrencyDenominationMaster(CurrencyDenominationMasterDto currencyDenominationMasterDto)
			throws ApplicationException {
		Response response = iCurrencyDenominationMasterDao
				.getCurrencyDenominationMasterByName(currencyDenominationMasterDto);
		try {
			if (response.getListObject().size() > 0 && response.getListObject() != null) {
				return new Response<>(ERROR, ALREADY_EXIST_CODE, ALREADY_EXIST, null, null);
			} else {
				return iCurrencyDenominationMasterDao.saveCurrencyDenominationMaster(currencyDenominationMasterDto);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response getCurrencyDenominationMasterById(int currencyId, int orgId) throws ApplicationException {
		try {
			return iCurrencyDenominationMasterDao.getCurrencyDenominationMasterById(currencyId, orgId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response getCurrencyDenominationMasterList(CurrencyDenominationMasterDto currencyDenominationMasterDto)
			throws ApplicationException {
		try {
			return iCurrencyDenominationMasterDao.getCurrencyDenominationMasterList(currencyDenominationMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response updateCurrencyDenominationMaster(CurrencyDenominationMasterDto currencyDenominationMasterDto)
			throws ApplicationException {
		Response response = iCurrencyDenominationMasterDao
				.getCurrencyDenominationMasterByNameNotById(currencyDenominationMasterDto);
		try {
			if (response.getListObject().size() > 0 && response.getListObject() != null) {
				return new Response<>(ERROR, ALREADY_EXIST_CODE, ALREADY_EXIST, null, null);
			} else {
				return iCurrencyDenominationMasterDao.updateCurrencyDenominationMaster(currencyDenominationMasterDto);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response updateCurrencyDenominationMasterStatus(CurrencyDenominationMasterDto currencyDenominationMasterDto)
			throws ApplicationException {
		try {
			return iCurrencyDenominationMasterDao.updateCurrencyDenominationMasterStatus(currencyDenominationMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response getCurrencyDenominationCount(CurrencyDenominationMasterDto currencyDenominationMasterDto)
			throws ApplicationException {
		try {
			return iCurrencyDenominationMasterDao.getCurrencyDenominationCount(currencyDenominationMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

}
