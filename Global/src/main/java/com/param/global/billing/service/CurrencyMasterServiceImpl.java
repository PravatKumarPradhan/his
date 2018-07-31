package com.param.global.billing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.param.global.billing.dao.ICurrencyMasterDao;
import com.param.global.billing.dto.CurrencyMasterDto;
import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;
import in.param.exception.ApplicationException;

@Service
@SuppressWarnings({ "rawtypes", "unchecked" })
public class CurrencyMasterServiceImpl implements ICurrencyMasterService, ICommonConstants {

	@Autowired
	ICurrencyMasterDao iCurrencyMasterDao;

	@Override
	public Response saveCurrencyMaster(CurrencyMasterDto currencyMasterDto) throws ApplicationException {
		Response response = iCurrencyMasterDao.getCurrencyMasterByName(currencyMasterDto);

		try {
			if (response.getListObject().size() > 0 && response.getListObject() != null) {
				return new Response(ERROR, null, ALREADY_EXIST, null, null);
			} else {
				return iCurrencyMasterDao.saveCurrencyMaster(currencyMasterDto);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return new Response<>(ERROR, ERROR_CODE, null, null, null);
	}

	@Override
	public Response getCurrencyMasterById(int currencyId, int orgId) throws ApplicationException {
		try {
			return iCurrencyMasterDao.getCurrencyMasterById(currencyId, orgId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, null, null, null);
	}

	@Override
	public Response getCurrencyMasterList(CurrencyMasterDto currencyMasterDto) throws ApplicationException {
		try {
			return iCurrencyMasterDao.getCurrencyMasterList(currencyMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, null, null, null);
	}

	@Override
	public Response updateCurrencyMaster(CurrencyMasterDto currencyMasterDto) throws ApplicationException {
		Response deptResponse = iCurrencyMasterDao.getCurrencyMasterByNameNotId(currencyMasterDto);

		try {
			if (deptResponse.getListObject() != null && deptResponse.getListObject().size() > 0) {
				return new Response(ERROR, null, ALREADY_EXIST, null, null);
			} else {
				return iCurrencyMasterDao.updateCurrencyMaster(currencyMasterDto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, null, null, null);
	}

	@Override
	public Response updateCurrencyMasterStatus(CurrencyMasterDto currencyMasterDto) throws ApplicationException {
		try {
			return iCurrencyMasterDao.updateCurrencyMasterStatus(currencyMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, null, null, null);
	}

	@Override
	public Response getCurrencyCount(CurrencyMasterDto currencyMasterDto) throws ApplicationException {
		try {
			return iCurrencyMasterDao.getCurrencyCount(currencyMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, null, null, null);
	}

}
