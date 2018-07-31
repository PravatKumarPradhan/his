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
import com.param.global.service.IAllergyMasterService;

/*FUNCTIONALITY : ALLERGY MASTER
DATE:12/04/2018
CREATED BY: JYOTI*/
@RestController
@RequestMapping("api/global")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class AllergyMasterController implements ICommonConstants{

	@Autowired
	private IAllergyMasterService iAllergyMasterService;
	
	
	@RequestMapping(value="/getAllergyMaster", method=RequestMethod.GET)
	public @ResponseBody Response listOfAllergyTypeMaster(@RequestHeader(value="unit_id") int unitId,
			@RequestHeader(value="organization_id") int organizationId,@RequestParam(value="allergy_type_id") int allergyTypeId) {
		try
		{
			AllergyMasterDto allergyMasterDto= new AllergyMasterDto();
			allergyMasterDto.setUnitId(unitId);
			allergyMasterDto.setOrganizationId(organizationId);
			allergyMasterDto.setAllergyTypeId(allergyTypeId);
			return iAllergyMasterService.getListOfAllergyMaster(allergyMasterDto);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}
}
