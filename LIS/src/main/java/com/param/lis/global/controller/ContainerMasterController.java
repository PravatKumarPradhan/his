package com.param.lis.global.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.param.lis.global.common.ICommonConstants;
import com.param.lis.global.common.IError;
import com.param.lis.global.common.Response;
import com.param.lis.global.dto.ContainerMasterDto;
import com.param.lis.global.dto.GlobalCommonDto;
import com.param.lis.global.dto.SampleMasterDto;
import com.param.lis.global.service.IContainerMasterService;

@RestController
@SuppressWarnings(
{ "rawtypes", "unchecked" })
@RequestMapping(value = "/LIS/global")
public class ContainerMasterController implements ICommonConstants, IError
{
	@Autowired
	private IContainerMasterService iContainerMasterService;

	@RequestMapping(value = "/addContainer", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Response addContainer(@RequestBody ContainerMasterDto containerMasterDto)
	{
		Response<ContainerMasterDto, Integer> response = null;
		try
		{
			response = iContainerMasterService.addContainerMaster(containerMasterDto);
			return response;
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}


	@RequestMapping(value = "/getContainerbyId/{orgId}/{containerId}", method = RequestMethod.GET)
	public  Response getSampleById(@PathVariable(value = "orgId") Integer orgId,
			@PathVariable(value = "containerId") Integer ContainerId)
	{
		Response<GlobalCommonDto, Integer> response = null;
		try
		{
			response = iContainerMasterService.getContainerMasterById(orgId, ContainerId);
			return response;
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@RequestMapping(value = "/updateContainer", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public  Response updateContainer(@RequestBody ContainerMasterDto containerMasterDto)
	{
		Response<ContainerMasterDto, Integer> response = null;
		try
		{
			response = iContainerMasterService.updateContainerMaster(containerMasterDto);
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
		return response;
	}

	@RequestMapping(value = "/activeInactiveContainer/{orgId}/{containerId}/{containerStatus}", method = RequestMethod.GET)
	public Response activeInactiveContainer(@PathVariable(value = "orgId") Integer orgId,
			@PathVariable(value = "containerId") Integer containerId,
			@PathVariable(value = "containerStatus") Character containerStatus)
	{
		Response<ContainerMasterDto, Integer> response = null;
		try
		{
			response = iContainerMasterService.ActivateInactivateContainerMaster(orgId, containerId, containerStatus);
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
		return response;
	}

	@RequestMapping(value = "/listContainerMaster/{orgId}/{offset}/{recordPerPage}", method = RequestMethod.GET)
	public Response listContainerMaster(@PathVariable(value = "orgId") Integer orgId,
			@PathVariable(value = "offset") Integer offset,
			@PathVariable(value = "recordPerPage") Integer recordPerPage)
	{
		Response<GlobalCommonDto, Integer> response = null;
		try
		{
			response = iContainerMasterService.listContainerMaster(orgId, offset, recordPerPage);
			return response;
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}
	
	@RequestMapping(value = "/getTotalContainerMasterRecord/{orgId}", method = RequestMethod.GET)
	public Response getTotalContainerMasterRecord(@PathVariable(value= "orgId") Integer orgId)
		{
			Response<SampleMasterDto, Integer> response = null;
			try
				{
					response = iContainerMasterService.getToTalContainerMasterRecord(orgId);
					return response;
				}
			catch (Exception e)
				{
					e.printStackTrace();
					return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
				}
		}


}
