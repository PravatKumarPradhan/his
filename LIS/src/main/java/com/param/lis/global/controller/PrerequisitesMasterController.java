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
import com.param.lis.global.dto.PrerequisitesMasterDto;
import com.param.lis.global.service.IPrerequisitesService;

@RestController
@SuppressWarnings(
{ "rawtypes", "unchecked" })
@RequestMapping(value = "/LIS/global")
public class PrerequisitesMasterController implements ICommonConstants, IError
{
	@Autowired
	private IPrerequisitesService iPrerequisitesService;

	@RequestMapping(value = "/addPrerequisites", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response addPrerequisites(@RequestBody PrerequisitesMasterDto prerequisitesMasterDto)
	{
		Response<PrerequisitesMasterDto, Integer> response = null;
		try
		{
			response = iPrerequisitesService.addPrerequisitesMaster(prerequisitesMasterDto);
			return response;
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}


	@RequestMapping(value = "/getPrerequisitesbyId/{orgId}/{prerequisitesId}", method = RequestMethod.GET)
	public @ResponseBody Response getPrerequisitesById(@PathVariable(value = "orgId") Integer orgId,
			@PathVariable(value = "prerequisitesId") Integer prerequisitesId)
	{
		Response<GlobalCommonDto, Integer> response = null;
		try
		{
			response = iPrerequisitesService.getPrerequisitesMasterById(orgId, prerequisitesId);
			return response;
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}	
	}

	@RequestMapping(value = "/updatePrerequisites", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response updatePrerequisites(@RequestBody PrerequisitesMasterDto prerequisitesMasterDto)
	{
		Response<PrerequisitesMasterDto, Integer> response = null;
		try
		{
			response = iPrerequisitesService.updatePrerequisitesMaster(prerequisitesMasterDto);
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
		return response;
	}

	@RequestMapping(value = "/activeInactivePrerequisites/{orgId}/{prerequisitesId}/{prerequisitesStatus}", method = RequestMethod.GET)
	public @ResponseBody Response updateAccountingLedger(@PathVariable(value = "orgId") Integer orgId,
			@PathVariable(value = "prerequisitesId") Integer prerequisitesId,
			@PathVariable(value = "prerequisitesStatus") Character prerequisitesStatus)
	{
		Response<PrerequisitesMasterDto, Integer> response = null;
		try
		{
			response = iPrerequisitesService.ActivateInactivatePrerequisitesMaster(orgId, prerequisitesId, prerequisitesStatus);
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
		return response;
	}

	@RequestMapping(value = "/listPrerequisitesMaster/{orgId}/{offset}/{recordPerPage}", method = RequestMethod.GET)
	public @ResponseBody Response listPrerequisitesMaster(@PathVariable(value = "orgId") Integer orgId,
			@PathVariable(value = "offset") Integer offset,
			@PathVariable(value = "recordPerPage") Integer recordPerPage)
	{
		Response<GlobalCommonDto, Integer> response = null;
		try
		{
			response = iPrerequisitesService.listPrerequisitesMaster(orgId, offset, recordPerPage);
			return response;
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}
	
	@RequestMapping(value = "/getTotalPrerequisitesMasterRecord/{orgId}", method = RequestMethod.GET)
	public @ResponseBody Response getTotalPrerequisitesMasterRecord(@PathVariable(value= "orgId") Integer orgId)
		{
			Response<PrerequisitesMasterDto, Integer> response = null;
			try
				{
					response = iPrerequisitesService.getToTalPrerequisitesMasterRecord(orgId);
					return response;
				}
			catch (Exception e)
				{
					e.printStackTrace();
					return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
				}
		}


}
