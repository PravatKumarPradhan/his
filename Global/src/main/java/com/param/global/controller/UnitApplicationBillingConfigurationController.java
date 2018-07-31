package com.param.global.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;
import com.param.global.dto.UnitApplicationConfigurationDto;
import com.param.global.service.IUnitApplicationBillingConfigurationService;

@Controller
@RequestMapping("/api/unit/applicationConfiguration")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class UnitApplicationBillingConfigurationController implements ICommonConstants
{

	@Autowired
	private IUnitApplicationBillingConfigurationService unitApplicationBillingConfigurationService;
	
	@RequestMapping(value="/billing/orgId/{orgId}/unitId/{unitId}", method = RequestMethod.GET)
	public @ResponseBody Response getUnitApplicationBillingConfigByOrgUnit(@PathVariable("unitId") Integer unitId,
			@PathVariable("orgId") Integer orgId) {
		try {
			UnitApplicationConfigurationDto unitApplicationConfigurationDto = new UnitApplicationConfigurationDto();
			unitApplicationConfigurationDto.setUnitId(unitId);
			unitApplicationConfigurationDto.setOrganizationId(orgId);
			return unitApplicationBillingConfigurationService.getUnitApplicationBillingConfigByOrgUnit(unitApplicationConfigurationDto);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR, COMMON_ERROR_MESSAGE, null, null);
	}
}
