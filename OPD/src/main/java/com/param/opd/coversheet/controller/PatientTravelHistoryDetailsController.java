package com.param.opd.coversheet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;
import com.param.opd.coversheet.dto.PatientTravelHistoryDetailsDto;
import com.param.opd.coversheet.service.IPatientTravelHistoryDetailsService;


/*FUNCTIONALITY : Travel HISTORY
DATE:27/04/2018
CREATED BY: Vivek*/
@RestController
@RequestMapping("api/opd")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class PatientTravelHistoryDetailsController implements ICommonConstants{
	
	@Autowired
	private IPatientTravelHistoryDetailsService iPatientTravelHistoryDetailsService;
	
	
	@RequestMapping(value="/getOldPatientTravelHistoryDetails", method=RequestMethod.GET)
	public @ResponseBody Response getOldPatientTravelHistoryDetails(@RequestParam(value="patientId") int patientId,@RequestHeader(value="unit_id") int unitId,
			@RequestHeader(value="organization_id") int organizationId,@RequestParam(value="roleId") int roleId,@RequestParam(value="encounterId") int encounterId) {
		try
		{
			PatientTravelHistoryDetailsDto patientTravelHistoryDetailsDto= new PatientTravelHistoryDetailsDto();
			patientTravelHistoryDetailsDto.setPatientId(patientId);
			patientTravelHistoryDetailsDto.setUnitId(unitId);
			patientTravelHistoryDetailsDto.setOrganizationId(organizationId);
			patientTravelHistoryDetailsDto.setEncounterId(encounterId);
			patientTravelHistoryDetailsDto.setRoleId(roleId);
			return iPatientTravelHistoryDetailsService.getOldPatientTravelHistory(patientTravelHistoryDetailsDto);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}
	
	@RequestMapping(value="/getCurrentPatientTravelHistoryDetails", method=RequestMethod.GET)
	public @ResponseBody Response getCurrentPatientTravelHistoryDetails(@RequestParam(value="patientId") int patientId,@RequestHeader(value="unit_id") int unitId,
			@RequestHeader(value="organization_id") int organizationId,@RequestParam(value="roleId") int roleId,@RequestParam(value="encounterId") int encounterId) {
		try
		{
			PatientTravelHistoryDetailsDto patientTravelHistoryDetailsDto= new PatientTravelHistoryDetailsDto();
			patientTravelHistoryDetailsDto.setPatientId(patientId);
			patientTravelHistoryDetailsDto.setUnitId(unitId);
			patientTravelHistoryDetailsDto.setOrganizationId(organizationId);
			patientTravelHistoryDetailsDto.setEncounterId(encounterId);
			patientTravelHistoryDetailsDto.setRoleId(roleId);
			return iPatientTravelHistoryDetailsService.getCurrentPatientTravelHistory(patientTravelHistoryDetailsDto);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}
	
	@RequestMapping(value="/savePatientTravelHistoryDetails", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response savePatientTravelHistoryDetails(@RequestHeader(value="unit_id") int unitId,
			@RequestHeader(value="organization_id") int organizationId,@RequestBody PatientTravelHistoryDetailsDto patientTravelHistoryDetailsDto) {
		try
		{
			patientTravelHistoryDetailsDto.setUnitId(unitId);
			patientTravelHistoryDetailsDto.setOrganizationId(organizationId);
			return iPatientTravelHistoryDetailsService.savePatientTravelHistoryDetails(patientTravelHistoryDetailsDto);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}
	
	@RequestMapping(value="/updatePatientTravelHistoryDetails", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response updatePatientTravelHistoryDetails(@RequestHeader(value="unit_id") int unitId,
			@RequestHeader(value="organization_id") int organizationId,@RequestBody PatientTravelHistoryDetailsDto patientTravelHistoryDetailsDto) {
		try
		{
			patientTravelHistoryDetailsDto.setUnitId(unitId);
			patientTravelHistoryDetailsDto.setOrganizationId(organizationId);
			return iPatientTravelHistoryDetailsService.updatePatientTravelHistory(patientTravelHistoryDetailsDto);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}
	
	@RequestMapping(value="/updateEnterErrorStatusPatientTravelHistoryDetails", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response updateEnterErrorStatusPatientTravelHistoryDetails(@RequestHeader(value="unit_id") int unitId,
			@RequestHeader(value="organization_id") int organizationId,@RequestBody PatientTravelHistoryDetailsDto patientTravelHistoryDetailsDto) {
		try
		{
			patientTravelHistoryDetailsDto.setUnitId(unitId);
			patientTravelHistoryDetailsDto.setOrganizationId(organizationId);
			return iPatientTravelHistoryDetailsService.updateStatusEnterInError(patientTravelHistoryDetailsDto);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}
	

}
