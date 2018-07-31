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
import com.param.lis.global.dto.OrganismMasterDto;
import com.param.lis.global.service.IOrganismMasterService;

@RestController
@SuppressWarnings("rawtypes")
@RequestMapping("/LIS/global")
public class OrganismMasterController implements ICommonConstants, IError{

	@Autowired
	private IOrganismMasterService iOrganismMasterService;
	
	@RequestMapping(value = "/addOrganismMaster" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public Response addOrganismMaster(@RequestBody OrganismMasterDto organisumMasterDto){
		try{
			return iOrganismMasterService.addOrganismMaster(organisumMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERR_201,ORGANISUM_ADD_SUCC, null, null);
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getaddOrganismMasterById/{orgId}/{organismId}", method = RequestMethod.GET)
	public @ResponseBody Response getOrganismMasterById(@PathVariable(value = "orgId") Integer orgId,
			@PathVariable(value = "organismId") Integer organismId)
	{
		Response<GlobalCommonDto, Integer> response = null;
		try
		{
			response = iOrganismMasterService.getOrganismMasterMasterById(orgId, organismId);
			return response;
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}	
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/updateOrganismMaster", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response updateOrganismMaster(@RequestBody OrganismMasterDto organisumMasterDto)
	{
		Response<OrganismMasterDto, Integer> response = null;
		try
		{
			response = iOrganismMasterService.updateOrganismMaster(organisumMasterDto);
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
		return response;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/activeInactiveOrganismMaster/{orgId}/{organismId}/{organismStatus}", method = RequestMethod.GET)
	public @ResponseBody Response ActivateInactivateOrganismMaster(@PathVariable(value = "orgId") Integer orgId,
			@PathVariable(value = "organismId") Integer organismId,
			@PathVariable(value = "organismStatus") Character organismStatus)
	{
		Response<OrganismMasterDto, Integer> response = null;
		try
		{
			response = iOrganismMasterService.ActivateInactivateOrganismMaster(orgId, organismId, organismStatus);
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
		return response;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/listOrganismMaster/{orgId}/{offset}/{recordPerPage}", method = RequestMethod.GET)
	public @ResponseBody Response listOrganismMaster(@PathVariable(value = "orgId") Integer orgId,
			@PathVariable(value = "offset") Integer offset,
			@PathVariable(value = "recordPerPage") Integer recordPerPage)
	{
		Response<GlobalCommonDto, Integer> response = null;
		try
		{
			response = iOrganismMasterService.listOrganismMaster(orgId, offset, recordPerPage);
			return response;
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getToTallistlistOrganismMaster/{orgId}", method = RequestMethod.GET)
	public @ResponseBody Response getToTalOrganismMaster(@PathVariable(value= "orgId") Integer orgId)
		{
			Response<OrganismMasterDto, Integer> response = null;
			try
				{
					response = iOrganismMasterService.getToTalOrganismMaster(orgId);
					return response;
				}
			catch (Exception e)
				{
					e.printStackTrace();
					return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
				}
		}


}
