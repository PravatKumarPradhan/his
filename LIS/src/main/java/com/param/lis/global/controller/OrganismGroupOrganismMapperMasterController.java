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
import com.param.lis.global.dto.OrganismGroupOrganismMapperMasterDto;
import com.param.lis.global.service.IOrganismGroupOrganismMapperMasterService;
@RestController
@SuppressWarnings({"rawtypes","unchecked"})
@RequestMapping("/LIS/global")
public class OrganismGroupOrganismMapperMasterController implements ICommonConstants, IError{

	@Autowired
	private IOrganismGroupOrganismMapperMasterService iOrganismGroupOrganismMapperMasterService;
	
	@RequestMapping(value = "/addOrganismGroupOrganismMapper" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public Response addOrganismGroupOrganismMapperMaster(@RequestBody OrganismGroupOrganismMapperMasterDto mediaColonyMapperMasterDto){
		try{
			return iOrganismGroupOrganismMapperMasterService.addOrganismGroupOrganismMapperMaster(mediaColonyMapperMasterDto);
		}catch(Exception e){
			e.printStackTrace();
			
		}
		return new Response<>(ERROR, ERR_201,MEDIA_COLONY_MAPPER_ACTIVATE_FAIL, null, null);
	}
	
	@RequestMapping(value = "/listOrganismGroupOrganismMapperMaster/{orgId}/{offset}/{recordPerPage}", method = RequestMethod.GET)
	public @ResponseBody Response listOrganismGroupOrganismMapperMaster(@PathVariable(value = "orgId") Integer orgId,
			@PathVariable(value = "offset") Integer offset,
			@PathVariable(value = "recordPerPage") Integer recordPerPage)
	{
		Response<OrganismGroupOrganismMapperMasterDto, Integer> response = null;
		try
		{
			response = iOrganismGroupOrganismMapperMasterService.listOrganismGroupOrganismMapperMaster(orgId, offset, recordPerPage);
			return response;
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}
	
	@RequestMapping(value = "/getToTalOrganismGroupOrganismMapper/{orgId}", method = RequestMethod.GET)
	public @ResponseBody Response getToTalOrganismGroupOrganismMapperMaster(@PathVariable(value= "orgId") Integer orgId)
		{
			Response<OrganismGroupOrganismMapperMasterDto, Integer> response = null;
			try
				{
					response = iOrganismGroupOrganismMapperMasterService.getToTalOrganismGroupOrganismMapperMaster(orgId);
					return response;
				}
			catch (Exception e)
				{
					e.printStackTrace();
					return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
				}
		}
	
	@RequestMapping(value = "/activeInactiveOrganismGroupOrganismMapperMaster/{orgId}/{organismGroupId}/{status}", method = RequestMethod.GET)
	public @ResponseBody Response activateInactivateOrganismGroupOrganismMapperMaster(@PathVariable(value = "orgId") Integer orgId,
			@PathVariable(value = "organismGroupId") Integer organismGroupId,
			@PathVariable(value = "status") Character status)
	{
		Response<OrganismGroupOrganismMapperMasterDto, Integer> response = null;
		try
		{
			response = iOrganismGroupOrganismMapperMasterService.activateInactivateOrganismGroupOrganismMapperMaster(orgId, organismGroupId, status);
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
		return response;
	}
	
	@RequestMapping(value = "/updateOrganismGroupOrganismMapperMaster", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response updateOrganismGroupOrganismMapperMaster(@RequestBody OrganismGroupOrganismMapperMasterDto mediaColonyMapperMasterDto)
	{
		Response<OrganismGroupOrganismMapperMasterDto, Integer> response = null;
		try
		{
			response = iOrganismGroupOrganismMapperMasterService.updateOrganismGroupOrganismMapperMaster(mediaColonyMapperMasterDto);
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
		return response;
	}
	@RequestMapping(value = "/getOrganismGroupOrganismMapperMasterById/{orgId}/{organismGroupId}", method = RequestMethod.GET)
	public @ResponseBody Response getOrganismGroupOrganismMapperMasterById(@PathVariable(value = "orgId") Integer orgId,
			@PathVariable(value = "organismGroupId") Integer organismGroupId)
	{
		Response<OrganismGroupOrganismMapperMasterDto, Integer> response = null;
		try
		{
			response = iOrganismGroupOrganismMapperMasterService.getOrganismGroupOrganismMapperMasterById(orgId, organismGroupId);
			return response;
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}	
	}
	
	@RequestMapping(value = "/getOrganismGroupById/{organismGroupId}", method = RequestMethod.GET)
	public @ResponseBody Response getOrganismGroupOrganismClassById(
			@PathVariable(value = "organismGroupId") Integer organismGroupId)
	{
		Response<OrganismGroupOrganismMapperMasterDto, Integer> response = null;
		try
		{
			response = iOrganismGroupOrganismMapperMasterService.getOrganismGroupOrganismClassById(organismGroupId);
			return response;
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}	
	}

}