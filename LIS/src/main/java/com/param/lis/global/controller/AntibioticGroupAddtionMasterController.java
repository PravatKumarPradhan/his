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
import com.param.lis.global.dto.AntibioticGroupAdditionMasterDto;
import com.param.lis.global.service.IAntibioticGroupAddtionMasterService;
@RestController
@SuppressWarnings({"rawtypes","unchecked"})
@RequestMapping("/LIS/global")
public class AntibioticGroupAddtionMasterController implements ICommonConstants, IError{

	@Autowired
	private IAntibioticGroupAddtionMasterService iAntibioticGroupAddtionMasterService;
	
	@RequestMapping(value = "/addAntibioticGroupAddtion" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public Response addAntibioticGroupAddtionMaster(@RequestBody AntibioticGroupAdditionMasterDto antibioticAdditionMasterDto){
		try{
			return iAntibioticGroupAddtionMasterService.addAntibioticGroupAddtionMaster(antibioticAdditionMasterDto);
		}catch(Exception e){
			e.printStackTrace();
			
		}
		return new Response<>(ERROR, ERR_201,ANTIBIOTIC_ADD_FAIL, null, null);
	}
	
	@RequestMapping(value = "/listAntibioticAntibioticGroupMaster/{orgId}/{offset}/{recordPerPage}", method = RequestMethod.GET)
	public @ResponseBody Response listAntibioticGroupAddtionMaster(@PathVariable(value = "orgId") Integer orgId,
			@PathVariable(value = "offset") Integer offset,
			@PathVariable(value = "recordPerPage") Integer recordPerPage)
	{
		Response<AntibioticGroupAdditionMasterDto, Integer> response = null;
		try
		{
			response = iAntibioticGroupAddtionMasterService.listAntibioticGroupAddtionMaster(orgId, offset, recordPerPage);
			return response;
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}
	
	@RequestMapping(value = "/getToTalAntibioticAntibioticGroupAddtion/{orgId}", method = RequestMethod.GET)
	public @ResponseBody Response getToTalAntibioticGroupAddtionMaster(@PathVariable(value= "orgId") Integer orgId)
		{
			Response<AntibioticGroupAdditionMasterDto, Integer> response = null;
			try
				{
					response = iAntibioticGroupAddtionMasterService.getToTalAntibioticGroupAddtionMaster(orgId);
					return response;
				}
			catch (Exception e)
				{
					e.printStackTrace();
					return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
				}
		}
	
	@RequestMapping(value = "/activeInactiveAntibioticGroupAddtion/{orgId}/{antiboiticGroupId}/{status}", method = RequestMethod.GET)
	public @ResponseBody Response activateInactivateAntibioticGroupAddtionMaster(@PathVariable(value = "orgId") Integer orgId,
			@PathVariable(value = "antiboiticGroupId") Integer antiboiticClassId,
			@PathVariable(value = "status") Character status)
	{
		Response<AntibioticGroupAdditionMasterDto, Integer> response = null;
		try
		{
			response = iAntibioticGroupAddtionMasterService.activateInactivateAntibioticGroupAddtionMaster(orgId, antiboiticClassId, status);
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
		return response;
	}
	
	@RequestMapping(value = "/updateAntibioticGroupAddtion", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response updateAntibioticGroupAddtionMaster(@RequestBody AntibioticGroupAdditionMasterDto antibioticAdditionMasterDto)
	{
		Response<AntibioticGroupAdditionMasterDto, Integer> response = null;
		try
		{
			response = iAntibioticGroupAddtionMasterService.updateAntibioticGroupAddtionMaster(antibioticAdditionMasterDto);
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
		return response;
	}
	@RequestMapping(value = "/getAntibioticAddtionGroupMasterById/{orgId}/{antiboiticGroupId}", method = RequestMethod.GET)
	public @ResponseBody Response getAntibioticGroupAddtionMasterById(@PathVariable(value = "orgId") Integer orgId,
			@PathVariable(value = "antiboiticGroupId") Integer antiboiticGroupId)
	{
		Response<AntibioticGroupAdditionMasterDto, Integer> response = null;
		try
		{
			response = iAntibioticGroupAddtionMasterService.getAntibioticGroupAddtionMasterById(orgId, antiboiticGroupId);
			return response;
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}	
	}
	
	@RequestMapping(value = "/getAntibioticByAddtionGroupId/{antiboiticGroupId}", method = RequestMethod.GET)
	public @ResponseBody Response getAntibioticGroupMasterClassById(
			@PathVariable(value = "antiboiticGroupId") Integer antiboiticGroupId)
	{
		Response<AntibioticGroupAdditionMasterDto, Integer> response = null;
		try
		{
			response = iAntibioticGroupAddtionMasterService.getAntibioticGroupMasterClassById(antiboiticGroupId);
			return response;
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}	
	}

}