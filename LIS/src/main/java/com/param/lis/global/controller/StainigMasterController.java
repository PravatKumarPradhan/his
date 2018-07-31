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
import com.param.lis.global.dto.StainigMasterDto;
import com.param.lis.global.service.IStainigMasterService;

@RestController
@SuppressWarnings("rawtypes")
@RequestMapping("/LIS/global")
public class StainigMasterController implements ICommonConstants, IError{

	@Autowired
	private IStainigMasterService iStainigMasterService;
	
	@RequestMapping(value = "/addStainigMaster" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public Response addStainigMaster(@RequestBody StainigMasterDto stainigMasterDto){
		try{
			return iStainigMasterService.addStainigMaster(stainigMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERR_201,STAINIG_ADD_SUCC, null, null);
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getaddStainigMasterById/{orgId}/{StainigId}", method = RequestMethod.GET)
	public @ResponseBody Response getStainigMasterById(@PathVariable(value = "orgId") Integer orgId,
			@PathVariable(value = "StainigId") Integer StainigId)
	{
		Response<GlobalCommonDto, Integer> response = null;
		try
		{
			response = iStainigMasterService.getStainigMasterMasterById(orgId, StainigId);
			return response;
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}	
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/updateStainigMaster", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response updateStainigMaster(@RequestBody StainigMasterDto stainigMasterDto)
	{
		Response<StainigMasterDto, Integer> response = null;
		try
		{
			response = iStainigMasterService.updateStainigMaster(stainigMasterDto);
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
		return response;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/activeInactiveStainigMaster/{orgId}/{StainigId}/{organismStatus}", method = RequestMethod.GET)
	public @ResponseBody Response ActivateInactivateStainigMaster(@PathVariable(value = "orgId") Integer orgId,
			@PathVariable(value = "StainigId") Integer StainigId,
			@PathVariable(value = "organismStatus") Character organismStatus)
	{
		Response<StainigMasterDto, Integer> response = null;
		try
		{
			response = iStainigMasterService.ActivateInactivateStainigMaster(orgId, StainigId, organismStatus);
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
		return response;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/listStainigMaster/{orgId}/{offset}/{recordPerPage}", method = RequestMethod.GET)
	public @ResponseBody Response listStainigMaster(@PathVariable(value = "orgId") Integer orgId,
			@PathVariable(value = "offset") Integer offset,
			@PathVariable(value = "recordPerPage") Integer recordPerPage)
	{
		Response<GlobalCommonDto, Integer> response = null;
		try
		{
			response = iStainigMasterService.listStainigMaster(orgId, offset, recordPerPage);
			return response;
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getToTallistStainigMaster/{orgId}", method = RequestMethod.GET)
	public @ResponseBody Response getToTalStainigMaster(@PathVariable(value= "orgId") Integer orgId)
		{
			Response<StainigMasterDto, Integer> response = null;
			try
				{
					response = iStainigMasterService.getToTalStainigMaster(orgId);
					return response;
				}
			catch (Exception e)
				{
					e.printStackTrace();
					return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
				}
		}


}
