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
import com.param.opd.coversheet.dto.ImmunizationHistoryMapperDto;
import com.param.opd.coversheet.dto.PatientHabitDetailsDto;
import com.param.opd.coversheet.service.IPatientHabitDetailsMapperService;

/*FUNCTIONALITY : PATIENT HABIT DETAILS MAPPER
DATE:09/04/2018
CREATED BY: JYOTI*/
@RestController
@RequestMapping("api/opd")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class PatientHabitDetailsMapperController implements ICommonConstants{

	@Autowired
	private IPatientHabitDetailsMapperService iPatientHabitDetailsMapperService;
	
	@RequestMapping(value="/savePatientHabitDetails", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response savePatientHabitDetails(@RequestHeader(value="unit_id") int unitId,
			@RequestHeader(value="organization_id") int organizationId,@RequestBody PatientHabitDetailsDto patientHabitDetailsDto) {
		try
		{
			patientHabitDetailsDto.setUnitId(unitId);
			patientHabitDetailsDto.setOrganizationId(organizationId);
			return iPatientHabitDetailsMapperService.savePatientHabitDetailsMapper(patientHabitDetailsDto);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}
	
	@RequestMapping(value="/updatePatientHabitDetails", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response updatePatientHabitDetails(@RequestHeader(value="unit_id") int unitId,
			@RequestHeader(value="organization_id") int organizationId,@RequestBody PatientHabitDetailsDto patientHabitDetailsDto) {
		try
		{
			patientHabitDetailsDto.setUnitId(unitId);
			patientHabitDetailsDto.setOrganizationId(organizationId);
			return iPatientHabitDetailsMapperService.updatePatientHabitDetails(patientHabitDetailsDto);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}
	
	@RequestMapping(value="/updateEnterErrorStatusPatientHabitDetails", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response updateEnterErrorStatusPatientHabitDetails(@RequestHeader(value="unit_id") int unitId,
			@RequestHeader(value="organization_id") int organizationId,@RequestBody PatientHabitDetailsDto patientHabitDetailsDto) {
		try
		{
			patientHabitDetailsDto.setUnitId(unitId);
			patientHabitDetailsDto.setOrganizationId(organizationId);
			return iPatientHabitDetailsMapperService.updateStatusEnterInError(patientHabitDetailsDto);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}
	
	@RequestMapping(value="/getPatientHabitDetails", method=RequestMethod.GET)
	public @ResponseBody Response listOfPatientHabitDetails(@RequestParam(value="patientId") int patientId,@RequestHeader(value="unit_id") int unitId,
			@RequestHeader(value="organization_id") int organizationId) {
		try
		{
			PatientHabitDetailsDto patientHabitDetailsDto= new PatientHabitDetailsDto();
			patientHabitDetailsDto.setPatientId(patientId);
			patientHabitDetailsDto.setUnitId(unitId);
			patientHabitDetailsDto.setOrganizationId(organizationId);
			return iPatientHabitDetailsMapperService.getListOfPatientHabitDetailsMapper(patientHabitDetailsDto);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}
	
	@RequestMapping(value="/getOldPatientHabitDetails", method=RequestMethod.GET)
	public @ResponseBody Response getOldPatientHabitDetails(@RequestParam(value="patientId") int patientId,@RequestHeader(value="unit_id") int unitId,
			@RequestHeader(value="organization_id") int organizationId,@RequestParam(value="roleId") int roleId,@RequestParam(value="encounterId") int encounterId) {
		try
		{
			PatientHabitDetailsDto patientHabitDetailsDto= new PatientHabitDetailsDto();
			patientHabitDetailsDto.setPatientId(patientId);
			patientHabitDetailsDto.setUnitId(unitId);
			patientHabitDetailsDto.setOrganizationId(organizationId);
			patientHabitDetailsDto.setRoleId(roleId);
			patientHabitDetailsDto.setEncounterId(encounterId);
			return iPatientHabitDetailsMapperService.getOldPatientHabitDetails(patientHabitDetailsDto);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}
	
	@RequestMapping(value="/getCurrentPatientHabitDetails", method=RequestMethod.GET)
	public @ResponseBody Response getCurrentPatientHabitDetails(@RequestParam(value="patientId") int patientId,@RequestHeader(value="unit_id") int unitId,
			@RequestHeader(value="organization_id") int organizationId,@RequestParam(value="roleId") int roleId,@RequestParam(value="encounterId") int encounterId) {
		try
		{
			PatientHabitDetailsDto patientHabitDetailsDto= new PatientHabitDetailsDto();
			patientHabitDetailsDto.setPatientId(patientId);
			patientHabitDetailsDto.setUnitId(unitId);
			patientHabitDetailsDto.setOrganizationId(organizationId);
			patientHabitDetailsDto.setRoleId(roleId);
			patientHabitDetailsDto.setEncounterId(encounterId);
			return iPatientHabitDetailsMapperService.getCurrentPatientHabitDetails(patientHabitDetailsDto);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}
}
