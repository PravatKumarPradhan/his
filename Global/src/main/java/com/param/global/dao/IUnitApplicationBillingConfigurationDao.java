package com.param.global.dao;

import com.param.global.common.Response;
import com.param.global.dto.UnitApplicationConfigurationDto;

public interface IUnitApplicationBillingConfigurationDao {

	Response getUnitApplicationBillingConfigByOrgUnit(UnitApplicationConfigurationDto unitApplicationConfigurationDto);

}
