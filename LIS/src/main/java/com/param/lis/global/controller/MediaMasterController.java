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
import com.param.lis.global.dto.GlobalCommonDto;
import com.param.lis.global.dto.MediaMasterDto;
import com.param.lis.global.service.IMediaMasterService;

@RestController
@SuppressWarnings("rawtypes")
@RequestMapping("/LIS/global")
public class MediaMasterController implements ICommonConstants, IError{

	@Autowired
	private IMediaMasterService iMediaMasterService;
	
	@RequestMapping(value = "/saveMediaMaster" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public Response saveMediaMaster(@RequestBody MediaMasterDto mediaMasterDto){
		try{
			return iMediaMasterService.saveMediaMaster(mediaMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERR_201, MEDIA_ADD_FAIL, null, null);
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getMediabyId/{orgId}/{mediaId}", method = RequestMethod.GET)
	public @ResponseBody Response getMediabyId(@PathVariable(value = "orgId") Integer orgId,
			@PathVariable(value = "mediaId") Integer mediaId)
	{
		Response<GlobalCommonDto, Integer> response = null;
		try
		{
			response = iMediaMasterService.getMediaMasterById(orgId, mediaId);
			return response;
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}	
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/updateMedia", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response updateMedia(@RequestBody MediaMasterDto mediaMasterDto)
	{
		Response<MediaMasterDto, Integer> response = null;
		try
		{
			response = iMediaMasterService.updateMediaMaster(mediaMasterDto);
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
		return response;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/activeInactiveMedia/{orgId}/{mediaId}/{mediaStatus}", method = RequestMethod.GET)
	public @ResponseBody Response updateActiveInactiveMedia(@PathVariable(value = "orgId") Integer orgId,
			@PathVariable(value = "mediaId") Integer mediaId,
			@PathVariable(value = "mediaStatus") Character mediaStatus)
	{
		Response<MediaMasterDto, Integer> response = null;
		try
		{
			response = iMediaMasterService.ActivateInactivateMediaMaster(orgId, mediaId, mediaStatus);
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
		return response;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/listMediaMaster/{orgId}/{offset}/{recordPerPage}", method = RequestMethod.GET)
	public @ResponseBody Response listMediaMaster(@PathVariable(value = "orgId") Integer orgId,
			@PathVariable(value = "offset") Integer offset,
			@PathVariable(value = "recordPerPage") Integer recordPerPage)
	{
		Response<GlobalCommonDto, Integer> response = null;
		try
		{
			response = iMediaMasterService.listMediaMaster(orgId, offset, recordPerPage);
			return response;
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getTotalMediaMasterRecord/{orgId}", method = RequestMethod.GET)
	public @ResponseBody Response getTotalMediaMasterRecord(@PathVariable(value= "orgId") Integer orgId)
		{
			Response<MediaMasterDto, Integer> response = null;
			try
				{
					response = iMediaMasterService.getToTalMediaMasterRecord(orgId);
					return response;
				}
			catch (Exception e)
				{
					e.printStackTrace();
					return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
				}
		}


}
