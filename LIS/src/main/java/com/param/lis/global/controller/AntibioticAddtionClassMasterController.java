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
import com.param.lis.global.dto.AntibioticAdditionMasterDto;
import com.param.lis.global.dto.IncubationPeriodMasterDto;
import com.param.lis.global.service.IAntibioticAddtionClassMasterService;
@RestController
@SuppressWarnings({"rawtypes","unchecked"})
@RequestMapping("/LIS/global")
public class AntibioticAddtionClassMasterController implements ICommonConstants, IError{

	@Autowired
	private IAntibioticAddtionClassMasterService iAntibioticAddtionClassMasterService;
	
	@RequestMapping(value = "/addAntibioticAddtionClass" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public Response addAntibioticAddtionClassMaster(@RequestBody AntibioticAdditionMasterDto antibioticAdditionMasterDto){
		try{
			return iAntibioticAddtionClassMasterService.addAntibioticAddtionClassMaster(antibioticAdditionMasterDto);
		}catch(Exception e){
			e.printStackTrace();
			
		}
		return new Response<>(ERROR, ERR_201,ANTIBIOTIC_ADD_FAIL, null, null);
	}
	
	@RequestMapping(value = "/listAntibioticAddtionClassMaster/{orgId}/{offset}/{recordPerPage}", method = RequestMethod.GET)
	public @ResponseBody Response listAntibioticAddtionClassMaster(@PathVariable(value = "orgId") Integer orgId,
			@PathVariable(value = "offset") Integer offset,
			@PathVariable(value = "recordPerPage") Integer recordPerPage)
	{
		Response<AntibioticAdditionMasterDto, Integer> response = null;
		try
		{
			response = iAntibioticAddtionClassMasterService.listAntibioticAddtionClassMaster(orgId, offset, recordPerPage);
			return response;
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}
	
	@RequestMapping(value = "/getToTalAntibioticAddtionClass/{orgId}", method = RequestMethod.GET)
	public @ResponseBody Response getToTalAntibioticAddtionClassMaster(@PathVariable(value= "orgId") Integer orgId)
		{
			Response<AntibioticAdditionMasterDto, Integer> response = null;
			try
				{
					response = iAntibioticAddtionClassMasterService.getToTalAntibioticAddtionClassMaster(orgId);
					return response;
				}
			catch (Exception e)
				{
					e.printStackTrace();
					return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
				}
		}
	
	@RequestMapping(value = "/activeInactiveAntibioticAddtion/{orgId}/{antiboiticClassId}/{status}", method = RequestMethod.GET)
	public @ResponseBody Response activateInactivateAntibioticAddtionClassMaster(@PathVariable(value = "orgId") Integer orgId,
			@PathVariable(value = "antiboiticClassId") Integer antiboiticClassId,
			@PathVariable(value = "status") Character status)
	{
		Response<AntibioticAdditionMasterDto, Integer> response = null;
		try
		{
			response = iAntibioticAddtionClassMasterService.activateInactivateAntibioticAddtionClassMaster(orgId, antiboiticClassId, status);
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
		return response;
	}
	
	@RequestMapping(value = "/updateAntibioticAddtion", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response updateAntibioticAddtionClassMaster(@RequestBody AntibioticAdditionMasterDto antibioticAdditionMasterDto)
	{
		Response<AntibioticAdditionMasterDto, Integer> response = null;
		try
		{
			response = iAntibioticAddtionClassMasterService.updateAntibioticAddtionClassMaster(antibioticAdditionMasterDto);
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
		return response;
	}
	@RequestMapping(value = "/getAntibioticAddtionClassMasterById/{orgId}/{antiboiticClassId}", method = RequestMethod.GET)
	public @ResponseBody Response getAgeGroupMasterById(@PathVariable(value = "orgId") Integer orgId,
			@PathVariable(value = "antiboiticClassId") Integer antiboiticClassId)
	{
		Response<AntibioticAdditionMasterDto, Integer> response = null;
		try
		{
			response = iAntibioticAddtionClassMasterService.getAntibioticAddtionClassMasterById(orgId, antiboiticClassId);
			return response;
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}	
	}
	
	@RequestMapping(value = "/getAntibioticByAntibioticClassId/{antiboiticClassId}", method = RequestMethod.GET)
	public @ResponseBody Response getAntibioticByAntibioticClassId(
			@PathVariable(value = "antiboiticClassId") Integer antiboiticClassId)
	{
		Response<AntibioticAdditionMasterDto, Integer> response = null;
		try
		{
			response = iAntibioticAddtionClassMasterService.getAntibioticMasterClassById(antiboiticClassId);
			return response;
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}	
	}

}