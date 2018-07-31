package com.param.lis.global.controller;

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
import com.param.lis.global.common.Response;
import com.param.lis.global.dto.MediaColonyMapperMasterDto;
import com.param.lis.global.dto.SampleTypeMediaMappingMasterDto;
import com.param.lis.global.service.ISampleTypeMediaMappingMasterService;

@RestController
@SuppressWarnings("rawtypes")
@RequestMapping("/LIS/global")
public class SampleTypeMediaMappingMasterController implements ICommonConstants, IError{

	@Autowired
	private ISampleTypeMediaMappingMasterService iSampleTypeMediaMappingMasterService;
	
	@RequestMapping(value = "/addSampleTypeMedia" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public Response addSampleTypeMediaMappingMaster(@RequestBody SampleTypeMediaMappingMasterDto sampleTypeMediaMappingMasterDto){
		try{
			return iSampleTypeMediaMappingMasterService.addSampleTypeMediaMappingMaster(sampleTypeMediaMappingMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERR_201,REPORT_TYPR_ADD_SUCC, null, null);
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getSampleTypeMediaById/{orgId}/{sampleTypeId}", method = RequestMethod.GET)
	public @ResponseBody Response getaddSampleTypeMediaMappingMasterById(@PathVariable(value = "orgId") Integer orgId,
			@PathVariable(value = "sampleTypeId") Integer sampleTypeId)
	{
		Response<SampleTypeMediaMappingMasterDto, Integer> response = null;
		try
		{
			response = iSampleTypeMediaMappingMasterService.getSampleTypeMediaMappingMasterById(orgId, sampleTypeId);
			return response;
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}	
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/updateSampleTypeMedia", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response updateSampleTypeMediaMappingMaster(@RequestBody SampleTypeMediaMappingMasterDto SampleTypeMediaMappingMasterDto)
	{
		Response<SampleTypeMediaMappingMasterDto, Integer> response = null;
		try
		{
			response = iSampleTypeMediaMappingMasterService.updateSampleTypeMediaMappingMaster(SampleTypeMediaMappingMasterDto);
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
		return response;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/activeInactiveSampleTypeMedia/{orgId}/{sampleTypeMediaId}/{sampleTypeMediaStatus}", method = RequestMethod.GET)
	public @ResponseBody Response ActivateInactivateSampleTypeMediaMappingMaster(@PathVariable(value = "orgId") Integer orgId,
			@PathVariable(value = "sampleTypeMediaId") Integer sampleTypeMediaId,
			@PathVariable(value = "sampleTypeMediaStatus") Character sampleTypeMediaStatus)
	{
		Response<SampleTypeMediaMappingMasterDto, Integer> response = null;
		try
		{
			response = iSampleTypeMediaMappingMasterService.ActivateInactivateSampleTypeMediaMappingMaster(orgId, sampleTypeMediaId, sampleTypeMediaStatus);
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
		return response;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/listSampleTypeMedia/{orgId}/{offset}/{recordPerPage}", method = RequestMethod.GET)
	public @ResponseBody Response listSampleTypeMediaMappingMaster(@PathVariable(value = "orgId") Integer orgId,
			@PathVariable(value = "offset") Integer offset,
			@PathVariable(value = "recordPerPage") Integer recordPerPage)
	{
		Response<SampleTypeMediaMappingMasterDto, Integer> response = null;
		try
		{
			response = iSampleTypeMediaMappingMasterService.listSampleTypeMediaMappingMaster(orgId, offset, recordPerPage);
			return response;
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}
	
	
	

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getSampleTypeAddtionClassMasterById/{orgId}/{sampleId}", method = RequestMethod.GET)
	public @ResponseBody Response getSampleTypeAddtionClassMasterById(@PathVariable(value = "orgId") Integer orgId,
			@PathVariable(value = "sampleId") Integer sampleId)
	{
		Response<MediaColonyMapperMasterDto, Integer> response = null;
		try
		{
			response = iSampleTypeMediaMappingMasterService.getSampleTypeAddtionClassMasterById(orgId, sampleId);
			return response;
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}	
	}
	
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getToTallistSampleTypeMedia/{orgId}", method = RequestMethod.GET)
	public @ResponseBody Response getToTallistSampleTypeMediaMappingMaster(@PathVariable(value= "orgId") Integer orgId)
		{
			Response<SampleTypeMediaMappingMasterDto, Integer> response = null;
			try
				{
					response = iSampleTypeMediaMappingMasterService.getToTalSampleTypeMediaMappingMaster(orgId);
					return response;
				}
			catch (Exception e)
				{
					e.printStackTrace();
					return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
				}
		}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getMediaMasterClassById/{orgId}", method = RequestMethod.GET)
	public @ResponseBody Response getMediaMasterClassById(@PathVariable(value= "orgId") Integer orgId)
		{
			Response<SampleTypeMediaMappingMasterDto, Integer> response = null;
			try
				{
					response = iSampleTypeMediaMappingMasterService.getMediaMasterClassById(orgId);
					return response;
				}
			catch (Exception e)
				{
					e.printStackTrace();
					return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
				}
		}
	
	
	
	


}
