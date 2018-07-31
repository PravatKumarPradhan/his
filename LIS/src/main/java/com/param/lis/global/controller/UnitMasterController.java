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
import com.param.lis.global.dto.UnitMasterDto;
import com.param.lis.global.service.IUnitMasterService;

@RestController
@SuppressWarnings("rawtypes")
@RequestMapping("/LIS/global")
public class UnitMasterController implements ICommonConstants, IError{

	@Autowired
	private IUnitMasterService iUnitMasterService;
	
	@RequestMapping(value = "/addUnitMaster" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public Response addUnitMaster(@RequestBody UnitMasterDto unitMasterDto){
		try{
			return iUnitMasterService.addUnitMaster(unitMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERR_201, UNIT_ADD_SUCC, null, null);
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getUnitById/{orgId}/{unitId}", method = RequestMethod.GET)
	public @ResponseBody Response getUnitMasterById(@PathVariable(value = "orgId") Integer orgId,
			@PathVariable(value = "unitId") Integer uitId)
	{
		Response<GlobalCommonDto, Integer> response = null;
		try
		{
			response = iUnitMasterService.getUnitMasterById(orgId, uitId);
			return response;
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}	
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/updateUnit", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response updateUnitMaster(@RequestBody UnitMasterDto unitMasterDto)
	{
		Response<UnitMasterDto, Integer> response = null;
		try
		{
			response = iUnitMasterService.updateUnitMaster(unitMasterDto);
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
		return response;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/activeInactiveUnit/{orgId}/{unitId}/{unitStatus}", method = RequestMethod.GET)
	public @ResponseBody Response ActivateInactivateUnitMaster(@PathVariable(value = "orgId") Integer orgId,
			@PathVariable(value = "unitId") Integer unitId,
			@PathVariable(value = "unitStatus") Character unitStatus)
	{
		Response<UnitMasterDto, Integer> response = null;
		try
		{
			response = iUnitMasterService.ActivateInactivateUnitMaster(orgId, unitId, unitStatus);
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
		return response;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/listUnitMaster/{orgId}/{offset}/{recordPerPage}", method = RequestMethod.GET)
	public @ResponseBody Response listUnitMaster(@PathVariable(value = "orgId") Integer orgId,
			@PathVariable(value = "offset") Integer offset,
			@PathVariable(value = "recordPerPage") Integer recordPerPage)
	{
		Response<GlobalCommonDto, Integer> response = null;
		try
		{
			response = iUnitMasterService.listUnitMaster(orgId, offset, recordPerPage);
			return response;
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getToTalUnitMasterRecord/{orgId}", method = RequestMethod.GET)
	public @ResponseBody Response getToTalUnitMasterRecord(@PathVariable(value= "orgId") Integer orgId)
		{
			Response<UnitMasterDto, Integer> response = null;
			try
				{
					response = iUnitMasterService.getToTalUnitMasterRecord(orgId);
					return response;
				}
			catch (Exception e)
				{
					e.printStackTrace();
					return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
				}
		}


}
