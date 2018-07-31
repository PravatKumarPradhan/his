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
import com.param.opd.coversheet.dto.GeneralExaminationAppoMapperDto;
import com.param.opd.coversheet.dto.PatientHabitDetailsDto;
import com.param.opd.coversheet.service.IGeneralExaminationAppoMapperService;

/*FUNCTIONALITY : GENERAL EXAMI
DATE:17/04/2018
CREATED BY: JYOTI*/
@RestController
@RequestMapping("api/opd")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class GeneralExaminationAppoMapperController implements ICommonConstants{

	@Autowired
	private IGeneralExaminationAppoMapperService iGeneralExaminationAppoMapperService;
	
	@RequestMapping(value="/saveGeneralExaminationAppoMapper", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response savePatientHabitDetails(@RequestHeader(value="unit_id") int unitId,
			@RequestHeader(value="organization_id") int organizationId,@RequestBody GeneralExaminationAppoMapperDto generalExaminationAppoMapperDto) {
		try
		{
			generalExaminationAppoMapperDto.setUnitId(unitId);
			generalExaminationAppoMapperDto.setOrganizationId(organizationId);
			return iGeneralExaminationAppoMapperService.saveGeneralExaminationMapper(generalExaminationAppoMapperDto);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}
	
	@RequestMapping(value="/getGeneralExaminationAppoMapper", method=RequestMethod.GET)
	public @ResponseBody Response listOfGeneralExaminationAppoMapper(@RequestParam(value="patientId") int patientId,@RequestHeader(value="unit_id") int unitId,
			@RequestHeader(value="organization_id") int organizationId,@RequestParam(value="typeId") int typeId) {
		try
		{
			GeneralExaminationAppoMapperDto generalExaminationAppoMapperDto= new GeneralExaminationAppoMapperDto();
			generalExaminationAppoMapperDto.setPatientId(patientId);
			generalExaminationAppoMapperDto.setUnitId(unitId);
			generalExaminationAppoMapperDto.setOrganizationId(organizationId);
			generalExaminationAppoMapperDto.setTypeId(typeId);
			return iGeneralExaminationAppoMapperService.getListOfGeneralExaminationMapper(generalExaminationAppoMapperDto);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}
	
	
	@RequestMapping(value="/getSavedGeneralExaminationAppoMapper", method=RequestMethod.GET)
	public @ResponseBody Response listOfSavedGeneralExaminationAppoMapper(@RequestParam(value="patientId") int patientId,@RequestHeader(value="unit_id") int unitId,
			@RequestHeader(value="organization_id") int organizationId,@RequestParam(value="typeId") int typeId,@RequestParam(value="genderId") int genderId) {
		try
		{
			GeneralExaminationAppoMapperDto generalExaminationAppoMapperDto= new GeneralExaminationAppoMapperDto();
			generalExaminationAppoMapperDto.setPatientId(patientId);
			generalExaminationAppoMapperDto.setUnitId(unitId);
			generalExaminationAppoMapperDto.setOrganizationId(organizationId);
			generalExaminationAppoMapperDto.setTypeId(typeId);
			generalExaminationAppoMapperDto.setGenderId(genderId);
			return iGeneralExaminationAppoMapperService.getListOfSavedGeneralExaminationMapper(generalExaminationAppoMapperDto);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}
	
}
