package com.param.global.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;
import com.param.global.dao.IUnitApplicationBillingConfigurationDao;
import com.param.global.dto.UnitApplicationConfigurationDto;

import in.param.exception.ApplicationException;

@Service
public class UnitApplicationBillingConfigurationServiceImpl implements IUnitApplicationBillingConfigurationService,ICommonConstants{

	@Autowired
	IUnitApplicationBillingConfigurationDao iUnitApplicationBillingConfigurationDao;
	
	@Override
	public Response getUnitApplicationBillingConfigByOrgUnit(
			UnitApplicationConfigurationDto unitApplicationConfigurationDto) throws ApplicationException {
		try {
			return iUnitApplicationBillingConfigurationDao.getUnitApplicationBillingConfigByOrgUnit(unitApplicationConfigurationDto);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

}
