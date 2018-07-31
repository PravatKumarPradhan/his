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
import com.param.opd.coversheet.dto.PatientPersonalHistoryDetailsDto;
import com.param.opd.coversheet.dto.PatientSurgicalHistoryDto;
import com.param.opd.coversheet.service.IPatientSurgicalHistoryService;

/*FUNCTIONALITY : SURGICAL HISTORY
DATE:06/04/2018
CREATED BY: JYOTI*/
@RestController
@RequestMapping("api/opd")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class PatientSurgicalHistoryController implements ICommonConstants{

	@Autowired
	private IPatientSurgicalHistoryService iPatientSurgicalHistoryService;
	
	@RequestMapping(value="/getOldPatientSurgicalHistory", method=RequestMethod.GET)
	public @ResponseBody Response getOldPatientSurgicalHistory(@RequestParam(value="patientId") int patientId,@RequestHeader(value="unit_id") int unitId,
			@RequestHeader(value="organization_id") int organizationId,@RequestParam(value="roleId") int roleId, @RequestParam(value="encounterId") int encounterId) {
		try
		{
			PatientSurgicalHistoryDto patientSurgicalHistoryDto= new PatientSurgicalHistoryDto();
			patientSurgicalHistoryDto.setPatientId(patientId);
			patientSurgicalHistoryDto.setUnitId(unitId);
			patientSurgicalHistoryDto.setOrganizationId(organizationId);
			patientSurgicalHistoryDto.setRoleId(roleId);
			patientSurgicalHistoryDto.setEncounterId(encounterId);
			return iPatientSurgicalHistoryService.getOldListOfPatientSurgicalHistory(patientSurgicalHistoryDto);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}
	
	@RequestMapping(value="/getCurrentPatientSurgicalHistory", method=RequestMethod.GET)
	public @ResponseBody Response getCurrentPatientSurgicalHistory(@RequestParam(value="patientId") int patientId,@RequestHeader(value="unit_id") int unitId,
			@RequestHeader(value="organization_id") int organizationId,@RequestParam(value="roleId") int roleId, @RequestParam(value="encounterId") int encounterId) {
		try
		{
			PatientSurgicalHistoryDto patientSurgicalHistoryDto= new PatientSurgicalHistoryDto();
			patientSurgicalHistoryDto.setPatientId(patientId);
			patientSurgicalHistoryDto.setUnitId(unitId);
			patientSurgicalHistoryDto.setOrganizationId(organizationId);
			patientSurgicalHistoryDto.setRoleId(roleId);
			patientSurgicalHistoryDto.setEncounterId(encounterId);
			return iPatientSurgicalHistoryService.getCurrentListOfPatientSurgicalHistory(patientSurgicalHistoryDto);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}
	
	@RequestMapping(value="/savePatientSurgicalHistory", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response savePatientSurgicalHistory(@RequestHeader(value="unit_id") int unitId,
			@RequestHeader(value="organization_id") int organizationId,@RequestBody PatientSurgicalHistoryDto patientSurgicalHistoryDto) {
		try
		{
			patientSurgicalHistoryDto.setUnitId(unitId);
			patientSurgicalHistoryDto.setOrganizationId(organizationId);
			return iPatientSurgicalHistoryService.savePatientSurgicalHistory(patientSurgicalHistoryDto);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}
	
	@RequestMapping(value="/updatePatientSurgicalHistory", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response updatePatientSurgicalHistory(@RequestHeader(value="unit_id") int unitId,
			@RequestHeader(value="organization_id") int organizationId,@RequestBody PatientSurgicalHistoryDto patientSurgicalHistoryDto) {
		try
		{
			patientSurgicalHistoryDto.setUnitId(unitId);
			patientSurgicalHistoryDto.setOrganizationId(organizationId);
			return iPatientSurgicalHistoryService.updatePatientSurgicalHistory(patientSurgicalHistoryDto);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}
	
	@RequestMapping(value="/updateEnterErrorStatusPatientSurgicalHistory", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response updateEnterErrorStatusPatientSurgicalHistory(@RequestHeader(value="unit_id") int unitId,
			@RequestHeader(value="organization_id") int organizationId,@RequestBody PatientSurgicalHistoryDto patientSurgicalHistoryDto) {
		try
		{
			patientSurgicalHistoryDto.setUnitId(unitId);
			patientSurgicalHistoryDto.setOrganizationId(organizationId);
			return iPatientSurgicalHistoryService.updateStatusEnterInError(patientSurgicalHistoryDto);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}
}
