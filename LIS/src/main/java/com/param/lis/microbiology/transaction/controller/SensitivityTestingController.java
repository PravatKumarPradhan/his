package com.param.lis.microbiology.transaction.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.param.lis.global.common.ICommonConstants;
import com.param.lis.global.common.IError;
import com.param.lis.global.common.JsonCheck;
import com.param.lis.global.common.Response;
import com.param.lis.microbiology.transaction.dto.MicrobioParamDto;
import com.param.lis.microbiology.transaction.dto.MicrobioResultEntryMasterDto;
import com.param.lis.microbiology.transaction.dto.SensitivityTestResultMasterDto;
import com.param.lis.microbiology.transaction.service.ISensitivityTestingService;

@RestController
@SuppressWarnings(
{ "rawtypes", "unchecked" })
@RequestMapping(value = "/LIS/Microbiology")
public class SensitivityTestingController  implements ICommonConstants, IError {
	
	@Autowired
	private ISensitivityTestingService iSensitivityTestingService;

	final static Logger logger = Logger.getLogger(SensitivityTestingController.class);
	
	@RequestMapping(value = "/SensitivityTesting/observation/list", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getSensitivityTestingList(
			@RequestBody MicrobioParamDto microbioParamDto)
	{
		Response<MicrobioResultEntryMasterDto, Integer> response = null;
		try
		{
			response = iSensitivityTestingService.getSensitivityTestingList(microbioParamDto);
			return response;
		} catch (Exception e)
		{
			logger.error("Exception", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}
	@RequestMapping(value = "/SensitivityTesting/observation/Count", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getSensitivityTestingListCount(@RequestBody MicrobioParamDto microbioParamDto)
	{
		Response<MicrobioResultEntryMasterDto, Integer> response = null;
		try
		{
			response = iSensitivityTestingService.getSensitivityTestingListCount(microbioParamDto);
			return response;
		} catch (Exception e)
		{
			logger.error("Exception", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}
	
	
	@RequestMapping(value = "/SensitivityTesting/observation/details", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getSensitivityTestingDetails(@RequestBody MicrobioParamDto microbioParamDto)
	{
		Response<MicrobioResultEntryMasterDto, Integer> response = null;
		try
		{
			response = iSensitivityTestingService.getSensitivityTestingDetails(microbioParamDto);
			return response;
		} catch (Exception e)
		{
			logger.error("Exception", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}
	
	@RequestMapping(value = "/sensitivityTesting/observation/{labSampleStausId}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response saveSensitivityTestingDuringObservation(@RequestBody SensitivityTestResultMasterDto   listSensitivityTestResultMasterDto,@PathVariable(value = "labSampleStausId") Integer labSampleStausId)
	{
		Response<SensitivityTestResultMasterDto, Integer> response = null;
		try
		{
			/*
		   JsonCheck.mapJsonToObject(listSensitivityTestResultMasterDto, SensitivityTestResultMasterDto.class);
		  return null;*/
			response = iSensitivityTestingService.saveSensitivityTestingObservation(listSensitivityTestResultMasterDto,labSampleStausId);
			return response;
		} catch (Exception e)
		{
			logger.error("Exception", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}
	
	@RequestMapping(value = "/sensitivityTesting/getSensitivityDetails/{labSampleDtlsId}/{orgId}/{orgUnitId}", method = RequestMethod.GET)
	public @ResponseBody Response getSensitivityDetails(@PathVariable(value = "labSampleDtlsId") Integer labSampleDtlsId,
	    @PathVariable(value = "orgId") Integer orgId,
	    @PathVariable(value = "orgUnitId") Integer orgUnitId)
	{
		Response<SensitivityTestResultMasterDto, Integer> response = null;
		try
		{
			response = iSensitivityTestingService.getSensitivityDetails(labSampleDtlsId,orgId,orgUnitId);
			return response;
		} catch (Exception e)
		{
			logger.error("Exception", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

}
