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
import com.param.opd.coversheet.dto.AppointmentVitalMapperDto;
import com.param.opd.coversheet.dto.PatientFamilyHistoryDto;
import com.param.opd.coversheet.service.IAppointmentVitalMapperService;


/*FUNCTIONALITY : APPOINTMENT VITAL MAPPER
DATE:13/04/2018
CREATED BY: JYOTI*/
@RestController
@RequestMapping("api/opd")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class AppointmentVitalMapperController implements ICommonConstants{

	@Autowired
	private IAppointmentVitalMapperService iAppointmentVitalMapperService;
	
	@RequestMapping(value="/saveAppointmentVitalMapper", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response saveAppointmentVitalMapper(@RequestHeader(value="unit_id") int unitId,
			@RequestHeader(value="organization_id") int organizationId,@RequestBody AppointmentVitalMapperDto appointmentVitalMapperDto) {
		try
		{
			appointmentVitalMapperDto.setUnitId(unitId);
			appointmentVitalMapperDto.setOrganizationId(organizationId);
			return iAppointmentVitalMapperService.saveAppointmentVitalMapper(appointmentVitalMapperDto);

		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}
	
	
	@RequestMapping(value="/updateAppointmentVitalMapper", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response updateAppointmentVitalMapper(@RequestHeader(value="unit_id") int unitId,
			@RequestHeader(value="organization_id") int organizationId,@RequestBody AppointmentVitalMapperDto appointmentVitalMapperDto) {
		try
		{
			appointmentVitalMapperDto.setUnitId(unitId);
			appointmentVitalMapperDto.setOrganizationId(organizationId);
			return iAppointmentVitalMapperService.updateAppointmentVitalMapper(appointmentVitalMapperDto);

		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}
	
	
	@RequestMapping(value="/getAppointmentVitalMapperByTrand", method=RequestMethod.GET)
	public @ResponseBody Response listOfPatientPersonalHistoryDetails(@RequestHeader(value="unit_id") int unitId,
			@RequestHeader(value="organization_id") int organizationId,@RequestHeader(value="patient_id") int patientId,@RequestParam(value="FromDate") String fromDate, @RequestParam(value="ToDate") String toDate,
			@RequestParam(value="VitalId") int vitalId,@RequestParam(value="encounterId") int encounterId) {
		try
		{
			AppointmentVitalMapperDto appointmentVitalMapperDto= new AppointmentVitalMapperDto();
			appointmentVitalMapperDto.setPatientId(patientId);
			appointmentVitalMapperDto.setUnitId(unitId);
			appointmentVitalMapperDto.setOrganizationId(organizationId);
			appointmentVitalMapperDto.setFromDate(fromDate);
			appointmentVitalMapperDto.setToDate(toDate);
			appointmentVitalMapperDto.setVitalId(vitalId);
			appointmentVitalMapperDto.setEncounterId(encounterId);
			return iAppointmentVitalMapperService.getListOfAppointmentVitalMapper(appointmentVitalMapperDto);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}
	
	@RequestMapping(value="/getAppointmentVitalMapperByPatientIdAndAppointmentId", method=RequestMethod.GET)
	public @ResponseBody Response listOfAppointmentVitalMapperByPatientId(@RequestHeader(value="unit_id") int unitId,
			@RequestHeader(value="organization_id") int organizationId,@RequestParam(value="patientId") int patientId) {
		try
		{
			AppointmentVitalMapperDto appointmentVitalMapperDto= new AppointmentVitalMapperDto();
			appointmentVitalMapperDto.setUnitId(unitId);
			appointmentVitalMapperDto.setOrganizationId(organizationId);
			appointmentVitalMapperDto.setPatientId(patientId);
			return iAppointmentVitalMapperService.getListOfAppointmentVitalMapperByPatientId(appointmentVitalMapperDto);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}
	
	@RequestMapping(value="/getOldAppointmentVitalMapper", method=RequestMethod.GET)
	public @ResponseBody Response getOldAppointmentVitalMapper(@RequestHeader(value="unit_id") int unitId,
			@RequestHeader(value="organization_id") int organizationId,@RequestParam(value="patientId") int patientId,@RequestParam(value="roleId") int roleId,@RequestParam(value="encounterId") int encounterId) {
		try
		{
			AppointmentVitalMapperDto appointmentVitalMapperDto= new AppointmentVitalMapperDto();
			appointmentVitalMapperDto.setUnitId(unitId);
			appointmentVitalMapperDto.setOrganizationId(organizationId);
			appointmentVitalMapperDto.setPatientId(patientId);
			appointmentVitalMapperDto.setEncounterId(encounterId);
			appointmentVitalMapperDto.setRoleId(roleId);
			return iAppointmentVitalMapperService.getOldAppointmentVitalMapper(appointmentVitalMapperDto);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}
	
	@RequestMapping(value="/getCurrentAppointmentVitalMapper", method=RequestMethod.GET)
	public @ResponseBody Response getCurrentAppointmentVitalMapper(@RequestHeader(value="unit_id") int unitId,
			@RequestHeader(value="organization_id") int organizationId,@RequestParam(value="patientId") int patientId,@RequestParam(value="roleId") int roleId,@RequestParam(value="encounterId") int encounterId) {
		try
		{
			AppointmentVitalMapperDto appointmentVitalMapperDto= new AppointmentVitalMapperDto();
			appointmentVitalMapperDto.setUnitId(unitId);
			appointmentVitalMapperDto.setOrganizationId(organizationId);
			appointmentVitalMapperDto.setPatientId(patientId);
			appointmentVitalMapperDto.setEncounterId(encounterId);
			appointmentVitalMapperDto.setRoleId(roleId);
			return iAppointmentVitalMapperService.getCurrentAppointmentVitalMapper(appointmentVitalMapperDto);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}
}
