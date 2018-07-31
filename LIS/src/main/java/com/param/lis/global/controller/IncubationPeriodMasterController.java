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
import com.param.lis.global.dto.IncubationPeriodMasterDto;
import com.param.lis.global.service.IIncubationPeriodMasterService;

@RestController
@SuppressWarnings("rawtypes")
@RequestMapping("/LIS/global")
public class IncubationPeriodMasterController implements ICommonConstants, IError{

	@Autowired
	private IIncubationPeriodMasterService iIncubationPeriodMasterService;
	
	@RequestMapping(value = "/addIncubationPeriod" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public Response addIncubationPeriodMaster(@RequestBody IncubationPeriodMasterDto incubationPeriodMasterDto){
		try{
			return iIncubationPeriodMasterService.addIncubationPeriodMaster(incubationPeriodMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERR_201,INCUBATION_TEST_MASTER_ADD_SUCC, null, null);
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getIncubationPeriodById/{orgId}/{incubationPeriodId}", method = RequestMethod.GET)
	public @ResponseBody Response getIncubationPeriodMasterById(@PathVariable(value = "orgId") Integer orgId,
			@PathVariable(value = "incubationPeriodId") Integer incubationPeriodId)
	{
		Response<IncubationPeriodMasterDto, Integer> response = null;
		try
		{
			response = iIncubationPeriodMasterService.getIncubationPeriodMasterById(orgId, incubationPeriodId);
			return response;
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}	
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/updateIncubationPeriodMaster", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response updateIncubationPeriodMaster(@RequestBody IncubationPeriodMasterDto incubationPeriodMasterDto)
	{
		Response<IncubationPeriodMasterDto, Integer> response = null;
		try
		{
			response = iIncubationPeriodMasterService.updateIncubationPeriodMaster(incubationPeriodMasterDto);
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
		return response;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/activeInactiveIncubationPeriod/{orgId}/{incubationPeriodId}/{incubationPeriodStatus}", method = RequestMethod.GET)
	public @ResponseBody Response ActivateInactivateIncubationPeriodMaster(@PathVariable(value = "orgId") Integer orgId,
			@PathVariable(value = "incubationPeriodId") Integer incubationPeriodId,
			@PathVariable(value = "incubationPeriodStatus") Character incubationPeriodStatus)
	{
		Response<IncubationPeriodMasterDto, Integer> response = null;
		try
		{
			response = iIncubationPeriodMasterService.ActivateInactivateIncubationPeriodMaster(orgId, incubationPeriodId,incubationPeriodStatus);
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
		return response;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/listIncubationPeriod/{orgId}/{offset}/{recordPerPage}", method = RequestMethod.GET)
	public @ResponseBody Response listIncubationPeriodMaster(@PathVariable(value = "orgId") Integer orgId,
			@PathVariable(value = "offset") Integer offset,
			@PathVariable(value = "recordPerPage") Integer recordPerPage)
	{
		Response<IncubationPeriodMasterDto, Integer> response = null;
		try
		{
			response = iIncubationPeriodMasterService.listIncubationPeriodMaster(orgId, offset, recordPerPage);
			return response;
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getToTalIncubationPeriod/{orgId}", method = RequestMethod.GET)
	public @ResponseBody Response getToTalIncubationPeriodMaster(@PathVariable(value= "orgId") Integer orgId)
		{
			Response<IncubationPeriodMasterDto, Integer> response = null;
			try
				{
					response = iIncubationPeriodMasterService.getToTalIncubationPeriodMaster(orgId);
					return response;
				}
			catch (Exception e)
				{
					e.printStackTrace();
					return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
				}
		}


}
