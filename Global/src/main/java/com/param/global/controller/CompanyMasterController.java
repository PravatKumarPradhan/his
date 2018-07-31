package com.param.global.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;
import com.param.global.dto.CompanyMasterDto;
import com.param.global.service.ICompanyMasterService;

@RestController
@RequestMapping("api/global")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class CompanyMasterController implements ICommonConstants{
	@Autowired
	private ICompanyMasterService iCompanyMasterService;
	
	@RequestMapping(value="/companyMaster/unit/{unitId}/org/{orgId}", method=RequestMethod.GET)
	public Response getActiveCompanyMasterList(@PathVariable("unitId")Integer unitId, @PathVariable("orgId") Integer orgId) {
		try {
			CompanyMasterDto companyMasterDto = new CompanyMasterDto();
			companyMasterDto.setUnitId(unitId);
			companyMasterDto.setOrganizationId(orgId);
			return iCompanyMasterService.getActiveCompanyMasterList(companyMasterDto);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR_CODE, null, null, null);
	}
	
}
