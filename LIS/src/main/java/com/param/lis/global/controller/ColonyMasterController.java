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
import com.param.lis.global.dto.ColonyMasterDto;
import com.param.lis.global.dto.GlobalCommonDto;
import com.param.lis.global.service.IColonyMasterService;

@RestController
@SuppressWarnings("rawtypes")
@RequestMapping("/LIS/global")
public class ColonyMasterController implements ICommonConstants, IError{

	@Autowired
	private IColonyMasterService iColonyMasterService;
	
	@RequestMapping(value = "/addColony" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public Response addColonyMaster(@RequestBody ColonyMasterDto colonyMasterDto){
		try{
			return iColonyMasterService.addColonyMaster(colonyMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERR_201,COLONY_ADD_SUCC, null, null);
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getaddColonyById/{orgId}/{colonyId}", method = RequestMethod.GET)
	public @ResponseBody Response getColonyMasterById(@PathVariable(value = "orgId") Integer orgId,
			@PathVariable(value = "colonyId") Integer colonyId)
	{
		Response<GlobalCommonDto, Integer> response = null;
		try
		{
			response = iColonyMasterService.getColonyMasterById(orgId, colonyId);
			return response;
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}	
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/updateColonyMaster", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response updateColonyMaster(@RequestBody ColonyMasterDto colonyMasterDto)
	{
		Response<ColonyMasterDto, Integer> response = null;
		try
		{
			response = iColonyMasterService.updateColonyMaster(colonyMasterDto);
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
		return response;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/activeInactiveColony/{orgId}/{colonyId}/{colonyStatus}", method = RequestMethod.GET)
	public @ResponseBody Response ActivateInactivateColonyMaster(@PathVariable(value = "orgId") Integer orgId,
			@PathVariable(value = "colonyId") Integer colonyId,
			@PathVariable(value = "colonyStatus") Character colonyStatus)
	{
		Response<ColonyMasterDto, Integer> response = null;
		try
		{
			response = iColonyMasterService.ActivateInactivateColonyMaster(orgId, colonyId, colonyStatus);
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
		return response;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/listColony/{orgId}/{offset}/{recordPerPage}", method = RequestMethod.GET)
	public @ResponseBody Response listColonyMaster(@PathVariable(value = "orgId") Integer orgId,
			@PathVariable(value = "offset") Integer offset,
			@PathVariable(value = "recordPerPage") Integer recordPerPage)
	{
		Response<GlobalCommonDto, Integer> response = null;
		try
		{
			response = iColonyMasterService.listColonyMaster(orgId, offset, recordPerPage);
			return response;
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getToTallistlistColony/{orgId}", method = RequestMethod.GET)
	public @ResponseBody Response getToTallistlistColonyMaster(@PathVariable(value= "orgId") Integer orgId)
		{
			Response<ColonyMasterDto, Integer> response = null;
			try
				{
					response = iColonyMasterService.getToTalColonyMaster(orgId);
					return response;
				}
			catch (Exception e)
				{
					e.printStackTrace();
					return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
				}
		}


}
