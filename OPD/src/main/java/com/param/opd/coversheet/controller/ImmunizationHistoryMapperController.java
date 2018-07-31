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
import com.param.opd.coversheet.dto.ImmunizationHistoryMapperDto;
import com.param.opd.coversheet.dto.PatientFamilyHistoryDto;
import com.param.opd.coversheet.service.IImmunizationHistoryMapperService;

/*FUNCTIONALITY : IMMUNIZATION APPOINTMENT
DATE:09/04/2018
CREATED BY: JYOTI*/
@RestController
@RequestMapping("api/opd")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ImmunizationHistoryMapperController implements ICommonConstants{

	@Autowired
	private IImmunizationHistoryMapperService iImmunizationHistoryMapperService;
	
	@RequestMapping(value="/saveImmunizationHistoryMapper", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response saveComplaintAppointment(@RequestHeader(value="unit_id") int unitId,
			@RequestHeader(value="organization_id") int organizationId,@RequestBody ImmunizationHistoryMapperDto immunizationHistoryMapperDto) {
		try
		{
			immunizationHistoryMapperDto.setUnitId(unitId);
			immunizationHistoryMapperDto.setOrganizationId(organizationId);
			return iImmunizationHistoryMapperService.saveImmunizationHistoryMapper(immunizationHistoryMapperDto);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}
	
	@RequestMapping(value="/updateImmunizationHistoryMapper", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response updateImmunizationHistoryMapper(@RequestHeader(value="unit_id") int unitId,
			@RequestHeader(value="organization_id") int organizationId,@RequestBody ImmunizationHistoryMapperDto immunizationHistoryMapperDto) {
		try
		{
			immunizationHistoryMapperDto.setUnitId(unitId);
			immunizationHistoryMapperDto.setOrganizationId(organizationId);
			return iImmunizationHistoryMapperService.updateImmunizationHistoryMapper(immunizationHistoryMapperDto);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}
	
	
	@RequestMapping(value="/getImmunizationHistoryMapper", method=RequestMethod.GET)
	public @ResponseBody Response listOfImmunizationHistoryMapper(@RequestParam(value="patientId") int patientId,@RequestHeader(value="unit_id") int unitId,
			@RequestHeader(value="organization_id") int organizationId) {
		try
		{
			ImmunizationHistoryMapperDto immunizationHistoryMapperDto= new ImmunizationHistoryMapperDto();
			immunizationHistoryMapperDto.setPatientId(patientId);
			immunizationHistoryMapperDto.setUnitId(unitId);
			immunizationHistoryMapperDto.setOrganizationId(organizationId);
			return iImmunizationHistoryMapperService.getListOfImmunizationHistoryMapper(immunizationHistoryMapperDto);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}
	
	
	@RequestMapping(value="/getOldImmunizationHistoryMapper", method=RequestMethod.GET)
	public @ResponseBody Response getOldImmunizationHistoryMapper(@RequestParam(value="patientId") int patientId,@RequestHeader(value="unit_id") int unitId,
			@RequestHeader(value="organization_id") int organizationId,@RequestParam(value="roleId") int roleId,@RequestParam(value="encounterId") int encounterId) {
		try
		{
			ImmunizationHistoryMapperDto immunizationHistoryMapperDto= new ImmunizationHistoryMapperDto();
			immunizationHistoryMapperDto.setPatientId(patientId);
			immunizationHistoryMapperDto.setUnitId(unitId);
			immunizationHistoryMapperDto.setOrganizationId(organizationId);
			immunizationHistoryMapperDto.setEncounterId(encounterId);
			immunizationHistoryMapperDto.setRoleId(roleId);
			return iImmunizationHistoryMapperService.getOldImmunizationHistoryMapper(immunizationHistoryMapperDto);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}
	
	@RequestMapping(value="/getCurrentImmunizationHistoryMapper", method=RequestMethod.GET)
	public @ResponseBody Response getCurrentImmunizationHistoryMapper(@RequestParam(value="patientId") int patientId,@RequestHeader(value="unit_id") int unitId,
			@RequestHeader(value="organization_id") int organizationId,@RequestParam(value="roleId") int roleId,@RequestParam(value="encounterId") int encounterId) {
		try
		{
			ImmunizationHistoryMapperDto immunizationHistoryMapperDto= new ImmunizationHistoryMapperDto();
			immunizationHistoryMapperDto.setPatientId(patientId);
			immunizationHistoryMapperDto.setUnitId(unitId);
			immunizationHistoryMapperDto.setOrganizationId(organizationId);
			immunizationHistoryMapperDto.setEncounterId(encounterId);
			immunizationHistoryMapperDto.setRoleId(roleId);
			return iImmunizationHistoryMapperService.getCurrentImmunizationHistoryMapper(immunizationHistoryMapperDto);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}
}
