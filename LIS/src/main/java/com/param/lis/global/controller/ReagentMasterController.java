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
import com.param.lis.global.dto.ReagentMasterDto;
import com.param.lis.global.service.IReagentMasterService;

@RestController
@SuppressWarnings("rawtypes")
@RequestMapping("/LIS/global")
public class ReagentMasterController implements ICommonConstants, IError{

	@Autowired
	private IReagentMasterService iReagentMasterService;
	
	@RequestMapping(value = "/addReagentMaster" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public Response addReagentMaster(@RequestBody ReagentMasterDto reagentMasterDto){
		try{
			return iReagentMasterService.addReagentMaster(reagentMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERR_201,REAGENT_ADD_SUCC, null, null);
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getReagentById/{orgId}/{reagentId}", method = RequestMethod.GET)
	public @ResponseBody Response getUnitMasterById(@PathVariable(value = "orgId") Integer orgId,
			@PathVariable(value = "reagentId") Integer reagentId)
	{
		Response<GlobalCommonDto, Integer> response = null;
		try
		{
			response = iReagentMasterService.getReagentMasterById(orgId, reagentId);
			return response;
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}	
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/updateReagent", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response updateReagentMaster(@RequestBody ReagentMasterDto reagentMasterDto)
	{
		Response<ReagentMasterDto, Integer> response = null;
		try
		{
			response = iReagentMasterService.updateReagentMaster(reagentMasterDto);
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
		return response;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/activeInactiveReagent/{orgId}/{reagentId}/{reagentStatus}", method = RequestMethod.GET)
	public @ResponseBody Response ActivateInactivateReagentMaster(@PathVariable(value = "orgId") Integer orgId,
			@PathVariable(value = "reagentId") Integer reagentId,
			@PathVariable(value = "reagentStatus") Character reagentStatus)
	{
		Response<ReagentMasterDto, Integer> response = null;
		try
		{
			response = iReagentMasterService.ActivateInactivateReagentMaster(orgId, reagentId, reagentStatus);
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
		return response;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/listReagentMaster/{orgId}/{offset}/{recordPerPage}", method = RequestMethod.GET)
	public @ResponseBody Response listReagentMaster(@PathVariable(value = "orgId") Integer orgId,
			@PathVariable(value = "offset") Integer offset,
			@PathVariable(value = "recordPerPage") Integer recordPerPage)
	{
		Response<GlobalCommonDto, Integer> response = null;
		try
		{
			response = iReagentMasterService.listReagentMaster(orgId, offset, recordPerPage);
			return response;
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getToTalReagentMasterRecord/{orgId}", method = RequestMethod.GET)
	public @ResponseBody Response getToTalReagentMasterRecord(@PathVariable(value= "orgId") Integer orgId)
		{
			Response<ReagentMasterDto, Integer> response = null;
			try
				{
					response = iReagentMasterService.getToTalReagentMasterRecord(orgId);
					return response;
				}
			catch (Exception e)
				{
					e.printStackTrace();
					return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
				}
		}


}
