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
import com.param.opd.coversheet.dto.PatientMedicalHistoryDto;
import com.param.opd.coversheet.dto.PatientPersonalHistoryDetailsDto;
import com.param.opd.coversheet.service.IPatientMedicalHistoryService;
import com.param.opd.coversheet.service.IPatientPersonalHistoryDetailsService;

/*FUNCTIONALITY : MEDICAL HISTORY
DATE:05/04/2018
CREATED BY: JYOTI*/

@RestController
@RequestMapping("api/opd")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class PatientMedicalHistoryController implements ICommonConstants{

	
	@Autowired
	private IPatientMedicalHistoryService iPatientMedicalHistoryService;
	
	@RequestMapping(value="/getOldPatientMedicalHistoryDetails", method=RequestMethod.GET)
	public @ResponseBody Response listOfPatientMedicalHistoryDetails(@RequestParam(value="patientId") int patientId,@RequestHeader(value="unit_id") int unitId,
			@RequestHeader(value="organization_id") int organizationId,@RequestParam(value="roleId") int roleId,@RequestParam(value="encounterId") int encounterId) {
		try
		{
			PatientMedicalHistoryDto patientMedicalHistoryDto= new PatientMedicalHistoryDto();
			patientMedicalHistoryDto.setPatientId(patientId);
			patientMedicalHistoryDto.setUnitId(unitId); 
			patientMedicalHistoryDto.setOrganizationId(organizationId);
			patientMedicalHistoryDto.setRoleId(roleId);
			patientMedicalHistoryDto.setEncounterId(encounterId);
			return iPatientMedicalHistoryService.getOldPatientMedicalHistory(patientMedicalHistoryDto);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}
	
	@RequestMapping(value="/getCurrentPatientMedicalHistoryDetails", method=RequestMethod.GET)
	public @ResponseBody Response getCurrentPatientMedicalHistoryDetails(@RequestParam(value="patientId") int patientId,@RequestHeader(value="unit_id") int unitId,
			@RequestHeader(value="organization_id") int organizationId,@RequestParam(value="roleId") int roleId,@RequestParam(value="encounterId") int encounterId) {
		try
		{
			PatientMedicalHistoryDto patientMedicalHistoryDto= new PatientMedicalHistoryDto();
			patientMedicalHistoryDto.setPatientId(patientId);
			patientMedicalHistoryDto.setUnitId(unitId); 
			patientMedicalHistoryDto.setOrganizationId(organizationId);
			patientMedicalHistoryDto.setRoleId(roleId);
			patientMedicalHistoryDto.setEncounterId(encounterId);
			return iPatientMedicalHistoryService.getCurrentPatientMedicalHistory(patientMedicalHistoryDto);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}
	
	@RequestMapping(value="/savePatientMedicalHistoryDetails", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response savePatientMedicalHistoryDetails(@RequestHeader(value="unit_id") int unitId,
			@RequestHeader(value="organization_id") int organizationId,@RequestBody PatientMedicalHistoryDto patientMedicalHistoryDto) {
		try
		{
			patientMedicalHistoryDto.setUnitId(unitId);
			patientMedicalHistoryDto.setOrganizationId(organizationId);
			return iPatientMedicalHistoryService.savePatientMedicalHistory(patientMedicalHistoryDto);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}
	
	@RequestMapping(value="/updatePatientMedicalHistoryDetails", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response updatePatientMedicalHistoryDetails(@RequestHeader(value="unit_id") int unitId,
			@RequestHeader(value="organization_id") int organizationId,@RequestBody PatientMedicalHistoryDto patientMedicalHistoryDto) {
		try
		{
			patientMedicalHistoryDto.setUnitId(unitId);
			patientMedicalHistoryDto.setOrganizationId(organizationId);
			return iPatientMedicalHistoryService.updatePatientMedicalHistory(patientMedicalHistoryDto);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}
	
	@RequestMapping(value="/updateEnterErrorStatusPatientMedicalHistoryDetails", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response updateEnterErrorStatusPatientMedicalHistoryDetails(@RequestHeader(value="unit_id") int unitId,
			@RequestHeader(value="organization_id") int organizationId,@RequestBody PatientMedicalHistoryDto patientMedicalHistoryDto) {
		try
		{
			patientMedicalHistoryDto.setUnitId(unitId);
			patientMedicalHistoryDto.setOrganizationId(organizationId);
			return iPatientMedicalHistoryService.updateStatusEnterInError(patientMedicalHistoryDto);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}
}
