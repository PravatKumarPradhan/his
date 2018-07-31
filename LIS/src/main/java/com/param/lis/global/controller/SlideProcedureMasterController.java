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
import com.param.lis.global.dto.SlideProcedureMasterDto;
import com.param.lis.global.service.ISlideProcedureMasterService;

@RestController
@SuppressWarnings("rawtypes")
@RequestMapping("/LIS/global")
public class SlideProcedureMasterController implements ICommonConstants, IError{

	@Autowired
	private ISlideProcedureMasterService iSlideProcedureMasterService;
	
	@RequestMapping(value = "/addSlideProcedure" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public Response addSlideProcedureMaster(@RequestBody SlideProcedureMasterDto slideProcedureMasterDto){
		try{
			return iSlideProcedureMasterService.addSlideProcedureMaster(slideProcedureMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERR_201,SLIDEPROCEDURE_ADD_SUCC, null, null);
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getslideProcedureById/{orgId}/{slideProcedureId}", method = RequestMethod.GET)
	public @ResponseBody Response getSlideProcedureMasterById(@PathVariable(value = "orgId") Integer orgId,
			@PathVariable(value = "slideProcedureId") Integer slideProcedureId)
	{
		Response<GlobalCommonDto, Integer> response = null;
		try
		{
			response = iSlideProcedureMasterService.getSlideProcedureMasterById(orgId, slideProcedureId);
			return response;
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}	
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/updateSlideProcedureMaster", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response updateSlideProcedureMaster(@RequestBody SlideProcedureMasterDto slideProcedureMasterDto)
	{
		Response<SlideProcedureMasterDto, Integer> response = null;
		try
		{
			response = iSlideProcedureMasterService.updateSlideProcedureMaster(slideProcedureMasterDto);
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
		return response;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/activeInactiveSlideProcedure/{orgId}/{slideProcedureId}/{slideProcedureStatus}", method = RequestMethod.GET)
	public @ResponseBody Response ActivateInactivateSlideProcedureMaster(@PathVariable(value = "orgId") Integer orgId,
			@PathVariable(value = "slideProcedureId") Integer slideProcedureId,
			@PathVariable(value = "slideProcedureStatus") Character slideProcedureStatus)
	{
		Response<SlideProcedureMasterDto, Integer> response = null;
		try
		{
			response = iSlideProcedureMasterService.ActivateInactivateSlideProcedureMaster(orgId, slideProcedureId, slideProcedureStatus);
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
		return response;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/listSlideProcedure/{orgId}/{offset}/{recordPerPage}", method = RequestMethod.GET)
	public @ResponseBody Response listSlideProcedureMaster(@PathVariable(value = "orgId") Integer orgId,
			@PathVariable(value = "offset") Integer offset,
			@PathVariable(value = "recordPerPage") Integer recordPerPage)
	{
		Response<GlobalCommonDto, Integer> response = null;
		try
		{
			response = iSlideProcedureMasterService.listSlideProcedureMaster(orgId, offset, recordPerPage);
			return response;
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getToTallistlistSlideProcedure/{orgId}", method = RequestMethod.GET)
	public @ResponseBody Response getToTallistlistSlideProcedureMaster(@PathVariable(value= "orgId") Integer orgId)
		{
			Response<SlideProcedureMasterDto, Integer> response = null;
			try
				{
					response = iSlideProcedureMasterService.getToTalSlideProcedureMaster(orgId);
					return response;
				}
			catch (Exception e)
				{
					e.printStackTrace();
					return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
				}
		}


}
