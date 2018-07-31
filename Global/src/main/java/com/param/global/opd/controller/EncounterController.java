package com.param.global.opd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;
import com.param.global.dto.SlotMasterDto;
import com.param.global.opd.service.IEncounterMasterService;
import com.param.opd.encounter.dto.AppointmentStatusMasterDto;
import com.param.opd.encounter.dto.EncounterMasterDto;

@RestController
@RequestMapping("api/opd")
@SuppressWarnings({"rawtypes","unchecked"})
public class EncounterController implements ICommonConstants{

	@Autowired
	private IEncounterMasterService iEncounterMasterService;
	
	@RequestMapping(value="encounter", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public synchronized Response saveEncoutnerMaster(@RequestBody EncounterMasterDto encounterMasterDto) {
		try {
			return iEncounterMasterService.saveEncounterMaster(encounterMasterDto);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}
	
	@RequestMapping(value="encounter/{enconterId}", method=RequestMethod.GET)
	public Response getEncounterDetailsById(@PathVariable("enconterId") Integer enconterId) {
		try {
			return iEncounterMasterService.getEncounterDetailsById(enconterId);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}
	
	@RequestMapping(value="getSystemGeneratedVisitTypeId",  method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public Integer getSystemGeneratedVisitTypeId(@RequestBody SlotMasterDto encounterMasterDto) {
		try {
			return iEncounterMasterService.assignVisitTypeIdAccordingToConditions(encounterMasterDto);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	@RequestMapping(value="getEncounterDetails", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public Response getEncounterDetails(@RequestBody EncounterMasterDto encounterMasterDto) {
		try {
			return iEncounterMasterService.getEncounterDetails(encounterMasterDto);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/*	ADDED BY JYOTI
	 * 	DATE 03-05-2018
	 * */
	@RequestMapping(value="/getListOfEncounterMaster", method=RequestMethod.GET)
	public @ResponseBody Response listOfEncounterMaster(@RequestHeader(value="unit_id") int unitId,
			@RequestHeader(value="organization_id") int organizationId) {
		try
		{
			SlotMasterDto slotMasterDto= new SlotMasterDto();
			slotMasterDto.setUnitId(unitId);
			slotMasterDto.setOrganizationId(organizationId);
			return iEncounterMasterService.getListOfEncounterMasterDao(slotMasterDto);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}
	
	
	@RequestMapping(value="/getSearchedListEncounterMaster", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getSearchedListEncounterMaster(@RequestHeader(value="unit_id") int unitId,
			@RequestHeader(value="organization_id") int organizationId, @RequestBody SlotMasterDto slotMasterDto) {
		try
		{
			slotMasterDto.setUnitId(unitId);
			slotMasterDto.setOrganizationId(organizationId);
			return iEncounterMasterService.getSearchedListEncounterMaster(slotMasterDto);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}
	
	@RequestMapping(value="/getListOfAppointmentStatusMaster", method=RequestMethod.GET)
	public @ResponseBody Response getListOfAppointmentStatusMaster(@RequestHeader(value="unit_id") int unitId,
			@RequestHeader(value="organization_id") int organizationId) {
		try
		{
			AppointmentStatusMasterDto appointmentStatusMasterDto = new AppointmentStatusMasterDto();
			appointmentStatusMasterDto.setUnitId(unitId);
			appointmentStatusMasterDto.setOrganizationId(organizationId);
			return iEncounterMasterService.getListOfAppointmentStatus(appointmentStatusMasterDto);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}
	
	@RequestMapping(value="/encounters/orgId/{orgId}/unitId/{unitId}/patientId/{patientId}", method=RequestMethod.GET)
	public @ResponseBody Response getListOfEncountersByPatientId(@PathVariable("orgId") Integer organizationId,
																@PathVariable("unitId") Integer unitId,
																@PathVariable("patientId") Integer patientId) 
	{
		try
		{
			EncounterMasterDto encounterMasterDto = new EncounterMasterDto();
				encounterMasterDto.setOrganizationId(organizationId);
				encounterMasterDto.setUnitId(unitId);
				encounterMasterDto.setPatientId(patientId);
			return iEncounterMasterService.getListOfEncountersByPatientId(encounterMasterDto);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}
}
