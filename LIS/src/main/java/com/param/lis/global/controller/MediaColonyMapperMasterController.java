package com.param.lis.global.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.param.lis.common.dto.CheckTranSactionExistsDto;
import com.param.lis.common.dto.CommonDto;
import com.param.lis.global.common.ICommonConstants;
import com.param.lis.global.common.IError;
import com.param.lis.global.common.Response;
import com.param.lis.global.dto.MediaColonyMapperMasterDto;
import com.param.lis.global.dto.SampleMasterDto;
import com.param.lis.global.service.IMediaColonyMapperMasterService;
@RestController
@SuppressWarnings({"rawtypes","unchecked"})
@RequestMapping("/LIS/global")
public class MediaColonyMapperMasterController implements ICommonConstants, IError{

	@Autowired
	private IMediaColonyMapperMasterService iMediaColonyMapperMasterService;
	
	@RequestMapping(value = "/addMediaColonyMapper" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public Response addMediaColonyMapperMaster(@RequestBody MediaColonyMapperMasterDto mediaColonyMapperMasterDto){
		try{
			return iMediaColonyMapperMasterService.addMediaColonyMapperMaster(mediaColonyMapperMasterDto);
		}catch(Exception e){
			e.printStackTrace();
			
		}
		return new Response<>(ERROR, ERR_201,MEDIA_COLONY_MAPPER_ACTIVATE_FAIL, null, null);
	}
	
	@RequestMapping(value = "/listMediaColonyMapperMaster/{orgId}/{offset}/{recordPerPage}", method = RequestMethod.GET)
	public @ResponseBody Response listMediaColonyMapperMaster(@PathVariable(value = "orgId") Integer orgId,
			@PathVariable(value = "offset") Integer offset,
			@PathVariable(value = "recordPerPage") Integer recordPerPage)
	{
		Response<MediaColonyMapperMasterDto, Integer> response = null;
		try
		{
			response = iMediaColonyMapperMasterService.listMediaColonyMapperMaster(orgId, offset, recordPerPage);
			return response;
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}
	
	@RequestMapping(value = "/getToTalMediaColonyMapper/{orgId}", method = RequestMethod.GET)
	public @ResponseBody Response getToTalMediaColonyMapperMaster(@PathVariable(value= "orgId") Integer orgId)
		{
			Response<MediaColonyMapperMasterDto, Integer> response = null;
			try
				{
					response = iMediaColonyMapperMasterService.getToTalMediaColonyMapperMaster(orgId);
					return response;
				}
			catch (Exception e)
				{
					e.printStackTrace();
					return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
				}
		}
	
	@RequestMapping(value = "/activeInactiveMediaColonyMapperMaster/{orgId}/{mediaId}/{status}", method = RequestMethod.GET)
	public @ResponseBody Response activateInactivateMediaColonyMapperMaster(@PathVariable(value = "orgId") Integer orgId,
			@PathVariable(value = "mediaId") Integer mediaId,
			@PathVariable(value = "status") Character status)
	{
		Response<MediaColonyMapperMasterDto, Integer> response = null;
		try
		{
			response = iMediaColonyMapperMasterService.activateInactivateMediaColonyMapperMaster(orgId, mediaId, status);
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
		return response;
	}
	
	@RequestMapping(value = "/updateMediaColonyMapperMaster", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response updateMediaColonyMapperMaster(@RequestBody MediaColonyMapperMasterDto mediaColonyMapperMasterDto)
	{
		Response<MediaColonyMapperMasterDto, Integer> response = null;
		try
		{
			response = iMediaColonyMapperMasterService.updateMediaColonyMapperMaster(mediaColonyMapperMasterDto);
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
		return response;
	}
	@RequestMapping(value = "/getMediaColonyMapperMasterById/{orgId}/{mediaId}", method = RequestMethod.GET)
	public @ResponseBody Response getAgeGroupMasterById(@PathVariable(value = "orgId") Integer orgId,
			@PathVariable(value = "mediaId") Integer mediaId)
	{
		Response<MediaColonyMapperMasterDto, Integer> response = null;
		try
		{
			response = iMediaColonyMapperMasterService.getMediaColonyMapperMasterById(orgId, mediaId);
			return response;
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}	
	}
	
	@RequestMapping(value = "/getMediaByMediaId/{mediaId}", method = RequestMethod.GET)
	public @ResponseBody Response getAntibioticByAntibioticClassId(
			@PathVariable(value = "mediaId") Integer mediaId)
	{
		Response<MediaColonyMapperMasterDto, Integer> response = null;
		try
		{
			response = iMediaColonyMapperMasterService.getMediaMasterClassById(mediaId);
			return response;
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}	
	}
	
	@RequestMapping(value = "/checkTranSactionExists" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getTotalSampleMasterRecord(@RequestBody CheckTranSactionExistsDto checkTranSactionExistsDto) {
		Response<MediaColonyMapperMasterDto, Integer> response = null;
		try {
			response = iMediaColonyMapperMasterService.getToTalMediaRecordCount(checkTranSactionExistsDto);
			return response;
		} catch (Exception e) {
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

}