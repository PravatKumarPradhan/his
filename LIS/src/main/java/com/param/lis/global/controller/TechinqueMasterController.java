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
import com.param.lis.global.dto.SampleMasterDto;
import com.param.lis.global.dto.TechniqueMasterDto;
import com.param.lis.global.service.ISampleMasterService;
import com.param.lis.global.service.ITechinqueMasterService;

@RestController
@SuppressWarnings(
{ "rawtypes", "unchecked" })
@RequestMapping(value = "/LIS/global")
public class TechinqueMasterController implements ICommonConstants, IError
{
	@Autowired
	private ITechinqueMasterService iTechinqueMasterService;

	@RequestMapping(value = "/addTechinque", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response addTechinque(@RequestBody TechniqueMasterDto techniqueMasterDto)
	{
		Response<SampleMasterDto, Integer> response = null;
		try
		{
			response = iTechinqueMasterService.addTechinqueMaster(techniqueMasterDto);
			return response;
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}


	@RequestMapping(value = "/getTechinquebyId/{orgId}/{techniqueId}", method = RequestMethod.GET)
	public @ResponseBody Response getTechinquebyId(@PathVariable(value = "orgId") Integer orgId,
			@PathVariable(value = "techniqueId") Integer techniqueId)
	{
		Response<GlobalCommonDto, Integer> response = null;
		try
		{
			response = iTechinqueMasterService.getTechinqueMasterById(orgId, techniqueId);
			return response;
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}	
	}

	@RequestMapping(value = "/updateTechinque", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response updateSample(@RequestBody TechniqueMasterDto techniqueMasterDto)
	{
		Response<TechniqueMasterDto, Integer> response = null;
		try
		{
			response = iTechinqueMasterService.updateTechinqueMaster(techniqueMasterDto);
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
		return response;
	}

	@RequestMapping(value = "/activeInactiveTechinque/{orgId}/{techniqueId}/{techniqueStatus}", method = RequestMethod.GET)
	public @ResponseBody Response activeInactiveTechinque(@PathVariable(value = "orgId") Integer orgId,
			@PathVariable(value = "techniqueId") Integer techniqueId,
			@PathVariable(value = "techniqueStatus") Character techniqueStatus)
	{
		Response<TechniqueMasterDto, Integer> response = null;
		try
		{
			response = iTechinqueMasterService.ActivateInactivateTechinqueMaster(orgId, techniqueId, techniqueStatus);
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
		return response;
	}

	@RequestMapping(value = "/listTechinqueMaster/{orgId}/{offset}/{recordPerPage}", method = RequestMethod.GET)
	public @ResponseBody Response listTechinqueMaster(@PathVariable(value = "orgId") Integer orgId,
			@PathVariable(value = "offset") Integer offset,
			@PathVariable(value = "recordPerPage") Integer recordPerPage)
	{
		Response<GlobalCommonDto, Integer> response = null;
		try
		{
			response = iTechinqueMasterService.listTechinqueMaster(orgId, offset, recordPerPage);
			return response;
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}
	
	@RequestMapping(value = "/getTotalTechinqueMasterRecord/{orgId}", method = RequestMethod.GET)
	public @ResponseBody Response getTotalTechinqueMasterRecord(@PathVariable(value= "orgId") Integer orgId)
		{
			Response<TechniqueMasterDto, Integer> response = null;
			try
				{
					response = iTechinqueMasterService.getToTalTechinqueMasterRecord(orgId);
					return response;
				}
			catch (Exception e)
				{
					e.printStackTrace();
					return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
				}
		}


}
