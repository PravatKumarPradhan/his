package com.param.global.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;
import com.param.global.dto.AssociatedCompanyMasterDto;
import com.param.global.service.IAssociatedCompanyMasterService;


@RestController
@RequestMapping("api/global")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class AssociatedCompanyMasterController implements ICommonConstants{
	@Autowired
	private IAssociatedCompanyMasterService iAssociatedCompanyMasterService;
	
	@RequestMapping(value="/associatedCompanyMaster/companyId/{companyId}/unit/{unitId}/org/{orgId}", method=RequestMethod.GET)
	public Response getActiveCompanyMasterList(@PathVariable("companyId") Integer companyId, @PathVariable("unitId")Integer unitId, @PathVariable("orgId") Integer orgId) {
		try {
			AssociatedCompanyMasterDto associatedCompanyMasterDto = new AssociatedCompanyMasterDto();
			associatedCompanyMasterDto.setCompanyMasterId(companyId);
			associatedCompanyMasterDto.setUnitId(unitId);
			associatedCompanyMasterDto.setOrganizationId(orgId);
			return iAssociatedCompanyMasterService.getAssociatedCompanyMaster(associatedCompanyMasterDto);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR_CODE, null, null, null);
	}
}
