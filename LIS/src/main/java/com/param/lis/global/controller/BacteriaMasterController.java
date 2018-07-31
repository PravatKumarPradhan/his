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
import com.param.lis.global.dto.BacteriaMasterDto;
import com.param.lis.global.dto.GlobalCommonDto;
import com.param.lis.global.service.IBacteriaMasterService;

@RestController
@SuppressWarnings("rawtypes")
@RequestMapping("/LIS/global")
public class BacteriaMasterController implements ICommonConstants, IError{

	@Autowired
	private IBacteriaMasterService iBacteriaMasterService;
	
	@RequestMapping(value = "/addBacteria" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public Response addBacteriaMaster(@RequestBody BacteriaMasterDto bacteriaMasterDto){
		try{
			return iBacteriaMasterService.addBacteriaMaster(bacteriaMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERR_201,REPORT_TYPR_ADD_SUCC, null, null);
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getaddBacteriaById/{orgId}/{bacteriaId}", method = RequestMethod.GET)
	public @ResponseBody Response getaddBacteriaMasterById(@PathVariable(value = "orgId") Integer orgId,
			@PathVariable(value = "bacteriaId") Integer bacteriaId)
	{
		Response<GlobalCommonDto, Integer> response = null;
		try
		{
			response = iBacteriaMasterService.getBacteriaMasterById(orgId, bacteriaId);
			return response;
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}	
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/updateBacteriaMaster", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response updateBacteriaMaster(@RequestBody BacteriaMasterDto bacteriaMasterDto)
	{
		Response<BacteriaMasterDto, Integer> response = null;
		try
		{
			response = iBacteriaMasterService.updateBacteriaMaster(bacteriaMasterDto);
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
		return response;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/activeInactiveBacteria/{orgId}/{bacteriaId}/{bacteriaStatus}", method = RequestMethod.GET)
	public @ResponseBody Response ActivateInactivateBacteriaMaster(@PathVariable(value = "orgId") Integer orgId,
			@PathVariable(value = "bacteriaId") Integer bacteriaId,
			@PathVariable(value = "bacteriaStatus") Character bacteriaStatus)
	{
		Response<BacteriaMasterDto, Integer> response = null;
		try
		{
			response = iBacteriaMasterService.ActivateInactivateBacteriaMaster(orgId, bacteriaId, bacteriaStatus);
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
		return response;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/listBacteria/{orgId}/{offset}/{recordPerPage}", method = RequestMethod.GET)
	public @ResponseBody Response listBacteriaMaster(@PathVariable(value = "orgId") Integer orgId,
			@PathVariable(value = "offset") Integer offset,
			@PathVariable(value = "recordPerPage") Integer recordPerPage)
	{
		Response<GlobalCommonDto, Integer> response = null;
		try
		{
			response = iBacteriaMasterService.listBacteriaMaster(orgId, offset, recordPerPage);
			return response;
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getToTallistBacteria/{orgId}", method = RequestMethod.GET)
	public @ResponseBody Response getToTallistBacteriaMaster(@PathVariable(value= "orgId") Integer orgId)
		{
			Response<BacteriaMasterDto, Integer> response = null;
			try
				{
					response = iBacteriaMasterService.getToTalBacteriaMaster(orgId);
					return response;
				}
			catch (Exception e)
				{
					e.printStackTrace();
					return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
				}
		}


}
