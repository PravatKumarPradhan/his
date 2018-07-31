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
import com.param.global.dao.IAllergyTypeMasterDao;
import com.param.global.dto.AllergyTypeMasterDto;
import com.param.global.service.IAllergyTypeMasterService;


/*FUNCTIONALITY : ALLERGY TYPE MASTER
DATE:12/04/2018
CREATED BY: JYOTI*/
@RestController
@RequestMapping("api/global")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class AllergyTypeMasterController implements ICommonConstants{

	@Autowired
	private IAllergyTypeMasterService iAllergyTypeMasterService;
	
	@RequestMapping(value="/getAllergyTypeMaster", method=RequestMethod.GET)
	public @ResponseBody Response listOfAllergyTypeMaster(@RequestHeader(value="unit_id") int unitId,
			@RequestHeader(value="organization_id") int organizationId) {
		try
		{
			AllergyTypeMasterDto allergyTypeMasterDto= new AllergyTypeMasterDto();
			allergyTypeMasterDto.setUnitId(unitId);
			allergyTypeMasterDto.setOrganizationId(organizationId);
			return iAllergyTypeMasterService.getListOfAllergyTypeMaster(allergyTypeMasterDto);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}
}
