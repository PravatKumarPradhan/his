package com.param.lis.unit.controller;

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
import com.param.lis.unit.dto.RackMasterDto;
import com.param.lis.unit.service.IRackMasterService;

@RestController
@SuppressWarnings(
{ "rawtypes", "unchecked" })
@RequestMapping(value = "/LIS/unit")
public class RackMasterController implements ICommonConstants, IError
{
	@Autowired
	private IRackMasterService iRackMasterService;

	@RequestMapping(value = "/addRack", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response addRack(@RequestBody RackMasterDto rackMasterDto)
	{
		Response<RackMasterDto, Integer> response = null;
		try
		{
			response = iRackMasterService.addRackMaster(rackMasterDto);
			return response;
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}


	@RequestMapping(value = "/getRackbyId/{orgId}/{orgUnitId}/{rackId}", method = RequestMethod.GET)
	public @ResponseBody Response getRackbyId(@PathVariable(value = "orgId") Integer orgId,
			@PathVariable(value = "orgUnitId") Integer orgUnitId,
			@PathVariable(value = "rackId") Integer rackId)
	{
		Response<GlobalCommonDto, Integer> response = null;
		try
		{
			response = iRackMasterService.getRackMasterById(orgId,orgUnitId,rackId);
			return response;
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}	
	}

	@RequestMapping(value = "/updateRackMaster", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response updateSample(@RequestBody RackMasterDto rackMasterDto)
	{
		Response<RackMasterDto, Integer> response = null;
		try
		{
			response = iRackMasterService.updateRackMaster(rackMasterDto);
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
		return response;
	}

	@RequestMapping(value = "/activeInactiveRack/{orgId}/{rackId}/{rackStatus}", method = RequestMethod.GET)
	public @ResponseBody Response activeInactiveRack(@PathVariable(value = "orgId") Integer orgId,
			@PathVariable(value = "rackId") Integer rackId,
			@PathVariable(value = "rackStatus") Character rackStatus)
	{
		Response<RackMasterDto, Integer> response = null;
		try
		{
			response = iRackMasterService.ActivateInactivateRackMaster(orgId, rackId, rackStatus);
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
		return response;
	}

	@RequestMapping(value = "/listRackMaster/{orgId}/{orgUnitId}/{offset}/{recordPerPage}", method = RequestMethod.GET)
	public @ResponseBody Response listRackMaster(@PathVariable(value = "orgId") Integer orgId,
			@PathVariable(value= "orgUnitId") Integer orgUnitId,
			@PathVariable(value = "offset") Integer offset,
			@PathVariable(value = "recordPerPage") Integer recordPerPage)
	{
		Response<GlobalCommonDto, Integer> response = null;
		try
		{
			response = iRackMasterService.listRackMaster(orgId,orgUnitId, offset, recordPerPage);
			return response;
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}
	
	@RequestMapping(value = "/getTotalRackMasterRecord/{orgId}/{orgUnitId}", method = RequestMethod.GET)
	public @ResponseBody Response getTotalRackMasterRecord(@PathVariable(value= "orgId") Integer orgId,@PathVariable(value= "orgUnitId") Integer orgUnitId)
		{
			Response<RackMasterDto, Integer> response = null;
			try
				{
					response = iRackMasterService.getToTalRackMasterRecord(orgId,orgUnitId);
					return response;
				}
			catch (Exception e)
				{
					e.printStackTrace();
					return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
				}
		}


}
