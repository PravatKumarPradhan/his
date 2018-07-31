package com.param.global.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;
import com.param.global.service.IVisitTypeMasterService;
@RestController
@RequestMapping(value="api/global")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class VisitTypeMasterController implements ICommonConstants {
	@Autowired
	private IVisitTypeMasterService iVisitTypeMasterService;
	
	@RequestMapping(value="/visitTypeMaster/unit/{unitId}/org/{orgId}" ,method=RequestMethod.GET)
	public Response getActiveVisitTypeMaster(@PathVariable("unitId")Integer unitId, @PathVariable("orgId")Integer orgId) {
		try {
			return iVisitTypeMasterService.getActiveVisitTypeList(unitId, orgId);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}
	
	
}
