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
import com.param.opd.coversheet.dto.AllergyPatientMapperDto;
import com.param.opd.coversheet.service.IAllergyPatientMapperService;


/*FUNCTIONALITY : ALLERGY PATIENT MAPPER
DATE:12/04/2018
CREATED BY: JYOTI*/
@RestController
@RequestMapping("api/opd")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class AllergyPatientMapperController implements ICommonConstants{

	@Autowired
	private IAllergyPatientMapperService iAllergyPatientMapperService;
	
	@RequestMapping(value="/saveAllergyPatientMapper", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response saveAllergyPatientMapper(@RequestHeader(value="unit_id") int unitId,
			@RequestHeader(value="organization_id") int organizationId,@RequestBody AllergyPatientMapperDto allergyPatientMapperDto) {
		try
		{
			allergyPatientMapperDto.setUnitId(unitId);
			allergyPatientMapperDto.setOrganizationId(organizationId);
			return iAllergyPatientMapperService.saveAllergyPatientMapper(allergyPatientMapperDto);

		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}
	
	
	@RequestMapping(value="/updateAllergyPatientMapper", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response updateAllergyPatientMapper(@RequestHeader(value="unit_id") int unitId,
			@RequestHeader(value="organization_id") int organizationId,@RequestBody AllergyPatientMapperDto allergyPatientMapperDto) {
		try
		{
			allergyPatientMapperDto.setUnitId(unitId);
			allergyPatientMapperDto.setOrganizationId(organizationId);
			return iAllergyPatientMapperService.saveAllergyPatientMapper(allergyPatientMapperDto);

		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}
	
	@RequestMapping(value="/updateEnterErrorStatusAllergyPatientMapper", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response updateEnterErrorStatusAllergyPatientMapper(@RequestHeader(value="unit_id") int unitId,
			@RequestHeader(value="organization_id") int organizationId,@RequestBody AllergyPatientMapperDto allergyPatientMapperDto) {
		try
		{
			allergyPatientMapperDto.setUnitId(unitId);
			allergyPatientMapperDto.setOrganizationId(organizationId);
			return iAllergyPatientMapperService.updateStatusEnterInError(allergyPatientMapperDto);

		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}
	
	
	@RequestMapping(value="/getAllergyPatientMapper", method=RequestMethod.GET)
	public @ResponseBody Response listOfAllergyPatientMapper(@RequestParam(value="patientId") int patientId,@RequestHeader(value="unit_id") int unitId,
			@RequestHeader(value="organization_id") int organizationId) {
		try
		{
			AllergyPatientMapperDto allergyPatientMapperDto= new AllergyPatientMapperDto();
			allergyPatientMapperDto.setPatientId(patientId);
			allergyPatientMapperDto.setUnitId(unitId);
			allergyPatientMapperDto.setOrganizationId(organizationId);
			return iAllergyPatientMapperService.getListOfAllergyPatientMapper(allergyPatientMapperDto);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}
	
	
	@RequestMapping(value="/cancelAllergyPatientMapper", method=RequestMethod.GET)
	public @ResponseBody Response cancelAllergyPatientMapper(@RequestParam(value="allergy_id") int allergyId,@RequestParam(value="allergy_type_id") int allergyTypeId,
			@RequestParam(value="patient_id") int patientId) {
		try
		{
			AllergyPatientMapperDto allergyPatientMapperDto= new AllergyPatientMapperDto();
			allergyPatientMapperDto.setPatientId(patientId);
			allergyPatientMapperDto.setAllergyId(allergyId);
			allergyPatientMapperDto.setAllergyTypeId(allergyTypeId);
			return iAllergyPatientMapperService.cancelAllergyPatientMapper(allergyPatientMapperDto);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}
	
	
	@RequestMapping(value="/getOldAllergyPatientMapper", method=RequestMethod.GET)
	public @ResponseBody Response getOldAllergyPatientMapper(@RequestParam(value="patientId") int patientId,@RequestHeader(value="unit_id") int unitId,
			@RequestHeader(value="organization_id") int organizationId,@RequestParam(value="roleId") int roleId,@RequestParam(value="encounterId") int encounterId) {
		try
		{
			AllergyPatientMapperDto allergyPatientMapperDto= new AllergyPatientMapperDto();
			allergyPatientMapperDto.setPatientId(patientId);
			allergyPatientMapperDto.setUnitId(unitId);
			allergyPatientMapperDto.setOrganizationId(organizationId);
			allergyPatientMapperDto.setRoleId(roleId);
			allergyPatientMapperDto.setEncounterId(encounterId);
			return iAllergyPatientMapperService.getOldAllergyPatientMapper(allergyPatientMapperDto);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}
	
	@RequestMapping(value="/getCurrentAllergyPatientMapper", method=RequestMethod.GET)
	public @ResponseBody Response getCurrentAllergyPatientMapper(@RequestParam(value="patientId") int patientId,@RequestHeader(value="unit_id") int unitId,
			@RequestHeader(value="organization_id") int organizationId,@RequestParam(value="roleId") int roleId,@RequestParam(value="encounterId") int encounterId) {
		try
		{
			AllergyPatientMapperDto allergyPatientMapperDto= new AllergyPatientMapperDto();
			allergyPatientMapperDto.setPatientId(patientId);
			allergyPatientMapperDto.setUnitId(unitId);
			allergyPatientMapperDto.setOrganizationId(organizationId);
			allergyPatientMapperDto.setRoleId(roleId);
			allergyPatientMapperDto.setEncounterId(encounterId);
			return iAllergyPatientMapperService.getCurrentAllergyPatientMapper(allergyPatientMapperDto);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}
}
