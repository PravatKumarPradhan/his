package com.param.lis.transaction.controller;


import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.param.entity.lis.transaction.LabSampleDetailsMaster;
import com.param.lis.global.common.ICommonConstants;
import com.param.lis.global.common.IError;
import com.param.lis.global.common.JsonCheck;
import com.param.lis.global.common.Response;
import com.param.lis.transaction.dto.LabCommonDto;
import com.param.lis.transaction.dto.PatientArrivalDto;
import com.param.lis.transaction.dto.PatientArrivalMapperMasterDto;
import com.param.lis.transaction.service.IPatientArrivalService;

@RestController
@SuppressWarnings(
{ "rawtypes", "unchecked" })
@RequestMapping(value = "/LIS/transaction")
public class LabPatientArrivalContoller implements ICommonConstants, IError
{
	@Autowired
	private IPatientArrivalService iIPatientArrivalService;

	final static Logger logger = Logger.getLogger(LabPatientArrivalContoller.class);
	@RequestMapping(value = "/patientArrival" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response patientArrival(@RequestBody LabCommonDto labCommonDto)
	{
		Response<PatientArrivalDto, Integer> response = null;
		try
		{
			response = iIPatientArrivalService.patientArrival(labCommonDto);
			return response;
		} catch (Exception e)
		{
			logger.error("Exception", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}
	
	
	@RequestMapping(value = "/patientArrivalCount" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response patientArrivalCount(@RequestBody LabCommonDto labCommonDto)
	{
		Response<PatientArrivalDto, Integer> response = null;
		try
		{
			response = iIPatientArrivalService.patientArrivalList(labCommonDto);
			return response;
		} catch (Exception e)
		{
			logger.error("Exception", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}
	
	@RequestMapping(value = "/patientArrivalData" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response patientArrival(@RequestBody List<PatientArrivalMapperMasterDto> patientArrivalMapperMasterDto)
	{
		Response<PatientArrivalMapperMasterDto, Integer> response = null;
		try
		{
		
			response = iIPatientArrivalService.patientArrivalMapperMaster(patientArrivalMapperMasterDto);
			return response;
		} catch (Exception e)
		{
			logger.error("Exception", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}
	
	@RequestMapping(value = "/patientNotArrivalData" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response patientNotArrivalData(@RequestBody LabSampleDetailsMaster labSampleDetailsMaster)
	{
		Response<PatientArrivalMapperMasterDto, Integer> response = null;
		try
		{
		
			response = iIPatientArrivalService.patientNotArrivalData(labSampleDetailsMaster);
			return response;
		} catch (Exception e)
		{
			logger.error("Exception", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}
	
	
}
