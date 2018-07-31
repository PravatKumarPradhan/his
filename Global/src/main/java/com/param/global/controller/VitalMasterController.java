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
import com.param.global.dto.SurgeryMasterDto;
import com.param.global.dto.VitalMasterDto;
import com.param.global.service.IVitalMasterService;

/*FUNCTIONALITY : VITAL MASTER
DATE:16/04/2018
CREATED BY: JYOTI*/

@RestController
@RequestMapping("api/global")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class VitalMasterController implements ICommonConstants{

	@Autowired
	private IVitalMasterService iVitalMasterService;
	
	
	@RequestMapping(value="/getVitalMasterList", method=RequestMethod.GET)
	public @ResponseBody Response listOfVitalMasterList(@RequestHeader(value="unit_id") int unitId, @RequestHeader(value="organization_id") int organizationId) {
		try
		{
			VitalMasterDto vitalMasterDto= new VitalMasterDto();
			vitalMasterDto.setUnitId(unitId);
			vitalMasterDto.setOrganizationId(organizationId);
			return iVitalMasterService.getVitalMasterList(vitalMasterDto);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}
}
