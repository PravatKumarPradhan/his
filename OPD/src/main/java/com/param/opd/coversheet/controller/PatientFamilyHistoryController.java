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
import com.param.opd.coversheet.dto.PatientFamilyHistoryDto;
import com.param.opd.coversheet.service.IpatientFamilyHistoryService;

/*FUNCTIONALITY : FAMILY HISTORY
DATE:06/04/2018
CREATED BY: JYOTI*/
@RestController
@RequestMapping("api/opd")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class PatientFamilyHistoryController implements ICommonConstants{

	@Autowired
	private IpatientFamilyHistoryService iPatientFamilyHistoryService;
	
	@RequestMapping(value="/getOldPatientFamilyHistory", method=RequestMethod.GET)
	public @ResponseBody Response getOldPatientFamilyHistory(@RequestParam(value="patientId") int patientId,@RequestHeader(value="unit_id") int unitId,
			@RequestHeader(value="organization_id") int organizationId,@RequestParam(value="roleId") int roleId,@RequestParam(value="encounterId") int encounterId) {
		try
		{
			PatientFamilyHistoryDto patientFamilyHistoryDto= new PatientFamilyHistoryDto();
			patientFamilyHistoryDto.setPatientId(patientId);
			patientFamilyHistoryDto.setUnitId(unitId);
			patientFamilyHistoryDto.setOrganizationId(organizationId);
			patientFamilyHistoryDto.setRoleId(roleId);
			patientFamilyHistoryDto.setEncounterId(encounterId);
			return iPatientFamilyHistoryService.getOldPatientFamilyHistory(patientFamilyHistoryDto);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}
	
	@RequestMapping(value="/getCurrentPatientFamilyHistory", method=RequestMethod.GET)
	public @ResponseBody Response getCurrentPatientFamilyHistory(@RequestParam(value="patientId") int patientId,@RequestHeader(value="unit_id") int unitId,
			@RequestHeader(value="organization_id") int organizationId,@RequestParam(value="roleId") int roleId,@RequestParam(value="encounterId") int encounterId) {
		try
		{
			PatientFamilyHistoryDto patientFamilyHistoryDto= new PatientFamilyHistoryDto();
			patientFamilyHistoryDto.setPatientId(patientId);
			patientFamilyHistoryDto.setUnitId(unitId);
			patientFamilyHistoryDto.setOrganizationId(organizationId);
			patientFamilyHistoryDto.setRoleId(roleId);
			patientFamilyHistoryDto.setEncounterId(encounterId);
			return iPatientFamilyHistoryService.getCurrentPatientFamilyHistory(patientFamilyHistoryDto);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}
	
	@RequestMapping(value="/savePatientFamilyHistory", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response savePatientPersonalHistoryDetails(@RequestHeader(value="unit_id") int unitId,
			@RequestHeader(value="organization_id") int organizationId,@RequestBody PatientFamilyHistoryDto patientFamilyHistoryDto) {
		try
		{
			patientFamilyHistoryDto.setUnitId(unitId);
			patientFamilyHistoryDto.setOrganizationId(organizationId);
			return iPatientFamilyHistoryService.savePatientFamilyHistory(patientFamilyHistoryDto);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}
	
	@RequestMapping(value="/updatePatientFamilyHistory", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response updatePatientFamilyHistory(@RequestHeader(value="unit_id") int unitId,
			@RequestHeader(value="organization_id") int organizationId,@RequestBody PatientFamilyHistoryDto patientFamilyHistoryDto) {
		try
		{
			patientFamilyHistoryDto.setUnitId(unitId);
			patientFamilyHistoryDto.setOrganizationId(organizationId);
			return iPatientFamilyHistoryService.updatePatientFamilyHistory(patientFamilyHistoryDto);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}
	
	@RequestMapping(value="/updateFamilyStatusEnterInError", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response updateFamilyStatusEnterInError(@RequestHeader(value="unit_id") int unitId,
			@RequestHeader(value="organization_id") int organizationId,@RequestBody PatientFamilyHistoryDto patientFamilyHistoryDto) {
		try
		{
			patientFamilyHistoryDto.setUnitId(unitId);
			patientFamilyHistoryDto.setOrganizationId(organizationId);
			return iPatientFamilyHistoryService.updateStatusEnterInError(patientFamilyHistoryDto);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}
}
