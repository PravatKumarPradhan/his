package com.param.global.service;

import javax.transaction.Transactional;

import com.param.global.common.Response;
import com.param.global.dto.UnitApplicationConfigurationDto;

import in.param.exception.ApplicationException;

public interface IUnitApplicationBillingConfigurationService {

	@Transactional
	Response getUnitApplicationBillingConfigByOrgUnit(UnitApplicationConfigurationDto unitApplicationConfigurationDto)throws ApplicationException;

}
