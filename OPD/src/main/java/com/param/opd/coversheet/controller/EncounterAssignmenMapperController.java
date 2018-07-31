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
import com.param.opd.coversheet.dto.EncounterAssignmenMapperDto;
import com.param.opd.coversheet.dto.PatientFamilyHistoryDto;
import com.param.opd.coversheet.service.IEncounterAssignmenMapperService;

/*FUNCTIONALITY : OLD ENCOUNTER FUNCTIONALITY
DATE:18/05/2018
CREATED BY: JYOTI*/

@RestController
@RequestMapping("api/opd")
@SuppressWarnings("rawtypes")
public class EncounterAssignmenMapperController implements ICommonConstants{

	@Autowired
	private IEncounterAssignmenMapperService iEncounterAssignmenMapperService;
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/getOldEncounterList", method=RequestMethod.GET)
	public @ResponseBody Response getOldEncounterList(@RequestHeader(value="unit_id") int unitId,
			@RequestHeader(value="organization_id") int organizationId, @RequestParam("patientId") int patientId) {
		try
		{
			EncounterAssignmenMapperDto encounterAssignmenMapperDto= new EncounterAssignmenMapperDto();
			encounterAssignmenMapperDto.setUnitId(unitId);
			encounterAssignmenMapperDto.setOrganizationId(organizationId);
			encounterAssignmenMapperDto.setPatientId(patientId);
			return iEncounterAssignmenMapperService.getOldListEncounterAssignment(encounterAssignmenMapperDto);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}
	
	@RequestMapping(value="/encounterDetails", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response savePatientPersonalHistoryDetails(@RequestHeader(value="unit_id") int unitId,
			@RequestHeader(value="organization_id") int organizationId,@RequestBody EncounterAssignmenMapperDto encounterAssignmenMapperDto) {
		try
		{
			encounterAssignmenMapperDto.setUnitId(unitId);
			encounterAssignmenMapperDto.setOrganizationId(organizationId);
			return iEncounterAssignmenMapperService.saveEncounterAssignment(encounterAssignmenMapperDto);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}
	
}
