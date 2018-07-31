package com.param.global.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;
import com.param.global.dto.MasterOfSytemDto;
import com.param.global.dto.SystemObservationMasterDto;
import com.param.global.service.ISystemObservationPropertyService;

/*FUNCTIONALITY : SYSTEM OBSERVATION PROPERTY MASTER
DATE:20/04/2018
CREATED BY: JYOTI*/
@RestController
@RequestMapping("api/global")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class SystemObservationPropertyMasterController implements ICommonConstants{

	@Autowired
	private ISystemObservationPropertyService iSystemObservationPropertyService;
	
	@RequestMapping(value="/saveSystemObservationProperty", method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response saveSystemObservationProperty(@RequestHeader(value="unit_id") int unitId,
			@RequestHeader(value="organization_id") int organizationId, @RequestBody SystemObservationMasterDto systemObservationPropertyMasterDto) {
		try
		{
			systemObservationPropertyMasterDto.setUnitId(unitId);
			systemObservationPropertyMasterDto.setOrganizationId(organizationId);
			return iSystemObservationPropertyService.saveSystemObservationProperty(systemObservationPropertyMasterDto);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}
	
	@RequestMapping(value="/getListOfSystemObservationProperty", method=RequestMethod.GET)
	public @ResponseBody Response getListOfSystemObservationProperty(@RequestHeader(value="unit_id") int unitId, @RequestHeader(value="organization_id") int organizationId) {
		try
		{
			SystemObservationMasterDto systemObservationPropertyMasterDto = new SystemObservationMasterDto();
			systemObservationPropertyMasterDto.setUnitId(unitId);
			systemObservationPropertyMasterDto.setOrganizationId(organizationId);
			return iSystemObservationPropertyService.getSystemObservationProperty(systemObservationPropertyMasterDto);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}
	
	
	@RequestMapping(value="/updateSystemObservationPropertyStatus",  method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response updateSystemObservationPropertyStatus(@RequestBody SystemObservationMasterDto systemObservationPropertyMasterDto) {
		try
		{
			return iSystemObservationPropertyService.updateSystemObservationPropertyStatus(systemObservationPropertyMasterDto);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}
	
	@RequestMapping(value="/updateSystemObservationSinglePropertyStatus",  method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response updateSystemObservationSinglePropertyStatus(@RequestBody SystemObservationMasterDto systemObservationPropertyMasterDto) {
		try
		{
			return iSystemObservationPropertyService.updateSystemObservationSinglePropertyStatus(systemObservationPropertyMasterDto);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}
}
