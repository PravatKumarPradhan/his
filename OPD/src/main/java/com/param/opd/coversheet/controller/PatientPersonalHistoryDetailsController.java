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
import com.param.opd.coversheet.dto.ComplaintAppointmentMapperDto;
import com.param.opd.coversheet.dto.PatientPersonalHistoryDetailsDto;
import com.param.opd.coversheet.service.IPatientPersonalHistoryDetailsService;


/*FUNCTIONALITY : PERSONAL HISTORY
DATE:05/04/2018
CREATED BY: JYOTI*/
@RestController
@RequestMapping("api/opd")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class PatientPersonalHistoryDetailsController implements ICommonConstants{

	@Autowired
	private IPatientPersonalHistoryDetailsService iPatientPersonalHistoryDetailsService;
	
	@RequestMapping(value="/getOldPatientPersonalHistoryDetails", method=RequestMethod.GET)
	public @ResponseBody Response listOfPatientPersonalHistoryDetails(@RequestParam(value="patientId") int patientId,@RequestHeader(value="unit_id") int unitId,
			@RequestHeader(value="organization_id") int organizationId, @RequestParam(value="roleId") int roleId,@RequestParam(value="encounterId") int encounterId) {
		try
		{
			PatientPersonalHistoryDetailsDto patientPersonalHistoryDetailsDto= new PatientPersonalHistoryDetailsDto();
			patientPersonalHistoryDetailsDto.setPatientId(patientId);
			patientPersonalHistoryDetailsDto.setUnitId(unitId);
			patientPersonalHistoryDetailsDto.setOrganizationId(organizationId);
			patientPersonalHistoryDetailsDto.setEncounterId(encounterId);
			patientPersonalHistoryDetailsDto.setRoleId(roleId);
			return iPatientPersonalHistoryDetailsService.getListOfOldPatientPersonalHistoryDetails(patientPersonalHistoryDetailsDto);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}
	
	
	@RequestMapping(value="/getCurrentPatientPersonalHistoryDetails", method=RequestMethod.GET)
	public @ResponseBody Response getCurrentPatientPersonalHistoryDetails(@RequestParam(value="patientId") int patientId,@RequestHeader(value="unit_id") int unitId,
			@RequestHeader(value="organization_id") int organizationId,@RequestParam(value="encounterId") int encounterId,@RequestParam(value="roleId") int roleId) {
		try
		{
			PatientPersonalHistoryDetailsDto patientPersonalHistoryDetailsDto= new PatientPersonalHistoryDetailsDto();
			patientPersonalHistoryDetailsDto.setPatientId(patientId);
			patientPersonalHistoryDetailsDto.setUnitId(unitId);
			patientPersonalHistoryDetailsDto.setOrganizationId(organizationId);
			patientPersonalHistoryDetailsDto.setEncounterId(encounterId);
			patientPersonalHistoryDetailsDto.setRoleId(roleId);
			
			return iPatientPersonalHistoryDetailsService.getListOfCurrentPatientPersonalHistoryDetails(patientPersonalHistoryDetailsDto);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}
	
	@RequestMapping(value="/savePatientPersonalHistoryDetails", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response savePatientPersonalHistoryDetails(@RequestHeader(value="unit_id") int unitId,
			@RequestHeader(value="organization_id") int organizationId,@RequestBody PatientPersonalHistoryDetailsDto patientPersonalHistoryDetailsDto) {
		try
		{
			patientPersonalHistoryDetailsDto.setUnitId(unitId);
			patientPersonalHistoryDetailsDto.setOrganizationId(organizationId);
			return iPatientPersonalHistoryDetailsService.savePatientPersonalHistoryDetails(patientPersonalHistoryDetailsDto);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}
	
	@RequestMapping(value="/updatePatientPersonalHistoryDetails", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response updatePatientPersonalHistoryDetails(@RequestHeader(value="unit_id") int unitId,
			@RequestHeader(value="organization_id") int organizationId,@RequestBody PatientPersonalHistoryDetailsDto patientPersonalHistoryDetailsDto) {
		try
		{
			patientPersonalHistoryDetailsDto.setUnitId(unitId);
			patientPersonalHistoryDetailsDto.setOrganizationId(organizationId);
			return iPatientPersonalHistoryDetailsService.updatePatientPersonalHistoryDetails(patientPersonalHistoryDetailsDto);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}
	
	@RequestMapping(value="/updateStatusEnterInError", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response updateStatusEnterInError(@RequestHeader(value="unit_id") int unitId,
			@RequestHeader(value="organization_id") int organizationId,@RequestBody PatientPersonalHistoryDetailsDto patientPersonalHistoryDetailsDto) {
		try
		{
			patientPersonalHistoryDetailsDto.setUnitId(unitId);
			patientPersonalHistoryDetailsDto.setOrganizationId(organizationId);
			return iPatientPersonalHistoryDetailsService.updateStatusEnterInError(patientPersonalHistoryDetailsDto);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}
}
