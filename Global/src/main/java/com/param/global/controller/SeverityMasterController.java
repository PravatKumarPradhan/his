package com.param.global.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;
import com.param.global.dto.AllergyMasterDto;
import com.param.global.dto.SeverityMasterDto;
import com.param.global.service.ISeverityMasterService;

/*FUNCTIONALITY : SERVERITY MASTER
DATE:12/04/2018
CREATED BY: JYOTI*/
@RestController
@RequestMapping("api/global")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class SeverityMasterController implements ICommonConstants{

	@Autowired
	private ISeverityMasterService iSeverityMasterService;
	
	@RequestMapping(value="/getSeverityMaster", method=RequestMethod.GET)
	public @ResponseBody Response listOfSeverityMaster(@RequestHeader(value="unit_id") int unitId,
			@RequestHeader(value="organization_id") int organizationId) {
		try
		{
			SeverityMasterDto severityMasterDto= new SeverityMasterDto();
			severityMasterDto.setUnitId(unitId);
			severityMasterDto.setOrganizationId(organizationId);
			return iSeverityMasterService.getListOfSeverityMaster(severityMasterDto);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}
}
