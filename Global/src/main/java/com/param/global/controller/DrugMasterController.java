package com.param.global.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;
import com.param.global.dto.DrugMasterDto;
import com.param.global.service.IDrugMasterService;


/*FUNCTIONALITY : DRUG MASTER
DATE:09/04/2018
CREATED BY: JYOTI*/
@RestController
@RequestMapping("api/global")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class DrugMasterController implements ICommonConstants{

	@Autowired
	private IDrugMasterService iDrugMasterService;
	
	@RequestMapping(value="/getDrugMasterList", method=RequestMethod.GET)
	public @ResponseBody Response listOfDrugMasterList(@RequestHeader(value="unit_id") int unitId, @RequestHeader(value="organization_id") int organizationId) {
		try
		{
			DrugMasterDto drugMasterDto= new DrugMasterDto();
			drugMasterDto.setUnitId(unitId);
			drugMasterDto.setOrganizationId(organizationId);
			return iDrugMasterService.getDrugMasterList(drugMasterDto);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}
}
