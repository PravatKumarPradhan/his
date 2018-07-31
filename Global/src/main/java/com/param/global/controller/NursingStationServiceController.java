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
import com.param.global.dto.NursingStationMasterDto;
import com.param.global.service.INursingStationService;

/*FUNCTIONALITY : NURSING STATION
DATE:08/05/2018
CREATED BY: JYOTI*/
@RestController
@RequestMapping("api/global")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class NursingStationServiceController implements ICommonConstants{

	@Autowired
	private INursingStationService iNursingStationService;
	
	@RequestMapping(value="/getListOfNursingStation", method=RequestMethod.GET)
	public @ResponseBody Response getListOfNursingStation(@RequestHeader(value="unit_id") int unitId,
			@RequestHeader(value="organization_id") int organizationId) {
		try
		{
			NursingStationMasterDto nursingStationMasterDto= new NursingStationMasterDto();
			nursingStationMasterDto.setUnitId(unitId);
			nursingStationMasterDto.setOrganizationId(organizationId);
			return iNursingStationService.getListOfNursingStationMaster(nursingStationMasterDto);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}
	
	@RequestMapping(value="/getListOfNursingStationByClinicId", method=RequestMethod.GET)
	public @ResponseBody Response getListOfNursingStationByClinicId(@RequestHeader(value="unit_id") int unitId,
			@RequestHeader(value="organization_id") int organizationId, @RequestParam(value="clinic_id") int clinicId) {
		try
		{
			NursingStationMasterDto nursingStationMasterDto= new NursingStationMasterDto();
			nursingStationMasterDto.setUnitId(unitId);
			nursingStationMasterDto.setOrganizationId(organizationId);
			nursingStationMasterDto.setClinicId(clinicId);
			return iNursingStationService.getListOfNursingStationMasterByClinicId(nursingStationMasterDto);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}
}
