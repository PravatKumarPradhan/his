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
import com.param.lis.global.dto.AgeGroupMasterDto;
import com.param.lis.global.service.IAgeGroupMasterService;

@RestController
@SuppressWarnings("rawtypes")
@RequestMapping("/LIS/global")
public class AgeGroupMasterController implements ICommonConstants, IError{

	@Autowired
	private IAgeGroupMasterService iAgeGroupMasterService;
	
	@RequestMapping(value = "/addAgeGroupMaster" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public Response addAgeGroupMaster(@RequestBody AgeGroupMasterDto ageGroupMasterDto){
		try{
			return iAgeGroupMasterService.addAgeGroupMaster(ageGroupMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERR_201, AGE_GROUP_ADD_SUCC, null, null);
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getAgeGroupById/{orgId}/{ageGroupId}", method = RequestMethod.GET)
	public @ResponseBody Response getAgeGroupMasterById(@PathVariable(value = "orgId") Integer orgId,
			@PathVariable(value = "ageGroupId") Integer ageGroupId)
	{
		Response<AgeGroupMasterDto, Integer> response = null;
		try
		{
			response = iAgeGroupMasterService.getAgeGroupMasterById(orgId, ageGroupId);
			return response;
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}	
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/updateAgeGroup", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response updateAgeGroupMaster(@RequestBody AgeGroupMasterDto unitMasterDto)
	{
		Response<AgeGroupMasterDto, Integer> response = null;
		try
		{
			response = iAgeGroupMasterService.updateAgeGroupMaster(unitMasterDto);
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
		return response;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/activeInactiveAgeGroup/{orgId}/{ageGroupId}/{ageGroupStatus}", method = RequestMethod.GET)
	public @ResponseBody Response ActivateInactivateAgeGroupMaster(@PathVariable(value = "orgId") Integer orgId,
			@PathVariable(value = "ageGroupId") Integer ageGroupId,
			@PathVariable(value = "ageGroupStatus") Character ageGroupStatus)
	{
		Response<AgeGroupMasterDto, Integer> response = null;
		try
		{
			response = iAgeGroupMasterService.ActivateInactivateAgeGroupMaster(orgId, ageGroupId, ageGroupStatus);
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
		return response;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/listAgeGroupMaster/{orgId}/{offset}/{recordPerPage}", method = RequestMethod.GET)
	public @ResponseBody Response listAgeGroupMaster(@PathVariable(value = "orgId") Integer orgId,
			@PathVariable(value = "offset") Integer offset,
			@PathVariable(value = "recordPerPage") Integer recordPerPage)
	{
		Response<AgeGroupMasterDto, Integer> response = null;
		try
		{
			response = iAgeGroupMasterService.listAgeGroupMaster(orgId, offset, recordPerPage);
			return response;
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getToTalAgeGroupMasterRecord/{orgId}", method = RequestMethod.GET)
	public @ResponseBody Response getToTalAgeGroupMasterRecord(@PathVariable(value= "orgId") Integer orgId)
		{
			Response<AgeGroupMasterDto, Integer> response = null;
			try
				{
					response = iAgeGroupMasterService.getToTalAgeGroupMasterRecord(orgId);
					return response;
				}
			catch (Exception e)
				{
					e.printStackTrace();
					return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
				}
		}


}
