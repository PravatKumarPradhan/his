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
import com.param.lis.unit.dto.StorageLocationMasterDto;
import com.param.lis.unit.service.IStorageLocationMasterService;

@RestController
@SuppressWarnings(
{ "rawtypes", "unchecked" })
@RequestMapping(value = "/LIS/global")
public class StorageLocationMasterController implements ICommonConstants, IError
{
	@Autowired
	private IStorageLocationMasterService iStorageLocationMasterService;

	@RequestMapping(value = "/addStorageLocation", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response addDepartment(@RequestBody StorageLocationMasterDto storageLocationMasterDto)
	{
		Response<StorageLocationMasterDto, Integer> response = null;
		try
		{
			response = iStorageLocationMasterService.addStorageLocationMaster(storageLocationMasterDto);
			return response;
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}


	@RequestMapping(value = "/getStorageLocationbyId/{orgId}/{unitId}/{storageLocationId}", method = RequestMethod.GET)
	public @ResponseBody Response getDepartmentbyId(@PathVariable(value = "orgId") Integer orgId,
			@PathVariable(value = "unitId") Integer unitId,
			@PathVariable(value = "storageLocationId") Integer storageLocationId)
	{
		Response<GlobalCommonDto, Integer> response = null;
		try
		{
			response = iStorageLocationMasterService.getStorageLocationMasterById(orgId,unitId,storageLocationId);
			return response;
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}	
	}

	@RequestMapping(value = "/updateStorageLocation", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response updateSample(@RequestBody StorageLocationMasterDto storageLocationMasterDto)
	{
		Response<StorageLocationMasterDto, Integer> response = null;
		try
		{
			response = iStorageLocationMasterService.updateStorageLocationMaster(storageLocationMasterDto);
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
		return response;
	}

	@RequestMapping(value = "/activeInactiveStorageLocation/{orgId}/{storageLocationId}/{storageLocationStatus}", method = RequestMethod.GET)
	public @ResponseBody Response activeInactiveDepartment(@PathVariable(value = "orgId") Integer orgId,
			@PathVariable(value = "storageLocationId") Integer storageLocationId,
			@PathVariable(value = "storageLocationStatus") Character storageLocationStatus)
	{
		Response<StorageLocationMasterDto, Integer> response = null;
		try
		{
			response = iStorageLocationMasterService.ActivateInactivateStorageLocationMaster(orgId, storageLocationId, storageLocationStatus);
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
		return response;
	}

	@RequestMapping(value = "/listStorageLocationMaster/{orgId}/{offset}/{recordPerPage}", method = RequestMethod.GET)
	public @ResponseBody Response listStorageLocationMaster(@PathVariable(value = "orgId") Integer orgId,
			@PathVariable(value = "offset") Integer offset,
			@PathVariable(value = "recordPerPage") Integer recordPerPage)
	{
		Response<GlobalCommonDto, Integer> response = null;
		try
		{
			response = iStorageLocationMasterService.listStorageLocationMaster(orgId, offset, recordPerPage);
			return response;
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}
	
	@RequestMapping(value = "/getTotalStorageLocationMasterRecord/{orgId}", method = RequestMethod.GET)
	public @ResponseBody Response getTotalStorageLocationMasterRecord(@PathVariable(value= "orgId") Integer orgId)
		{
			Response<StorageLocationMasterDto, Integer> response = null;
			try
				{
					response = iStorageLocationMasterService.getToTalStorageLocationMasterRecord(orgId);
					return response;
				}
			catch (Exception e)
				{
					e.printStackTrace();
					return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
				}
		}


}
