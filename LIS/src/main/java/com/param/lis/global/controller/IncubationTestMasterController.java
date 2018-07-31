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
import com.param.lis.global.dto.IncubationTestMasterDto;
import com.param.lis.global.service.IIncubationTestMasterService;

@RestController
@SuppressWarnings("rawtypes")
@RequestMapping("/LIS/global")
public class IncubationTestMasterController implements ICommonConstants, IError{

	@Autowired
	private IIncubationTestMasterService iIncubationTestMasterService;
	
	@RequestMapping(value = "/addIncubationTest" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public Response addIncubationTest(@RequestBody IncubationTestMasterDto incubationTestMasterDto){
		try{
			return iIncubationTestMasterService.addIncubationTest(incubationTestMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERR_201,INCUBATION_TEST_MASTER_ADD_SUCC, null, null);
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getIncubationTestById/{orgId}/{incubationTestId}", method = RequestMethod.GET)
	public @ResponseBody Response getaddIncubationTestById(@PathVariable(value = "orgId") Integer orgId,
			@PathVariable(value = "incubationTestId") Integer incubationTestId)
	{
		Response<GlobalCommonDto, Integer> response = null;
		try
		{
			response = iIncubationTestMasterService.getIncubationTestById(orgId, incubationTestId);
			return response;
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}	
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/updateIncubationTestMaster", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response updateIncubationTestMaster(@RequestBody IncubationTestMasterDto incubationTestMasterDto)
	{
		Response<IncubationTestMasterDto, Integer> response = null;
		try
		{
			response = iIncubationTestMasterService.updateIncubationTest(incubationTestMasterDto);
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
		return response;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/activeInactiveIncubationTest/{orgId}/{incubationTestId}/{incubationTestStatus}", method = RequestMethod.GET)
	public @ResponseBody Response ActivateInactivateIncubationTest(@PathVariable(value = "orgId") Integer orgId,
			@PathVariable(value = "incubationTestId") Integer incubationTestId,
			@PathVariable(value = "incubationTestStatus") Character incubationTestStatus)
	{
		Response<IncubationTestMasterDto, Integer> response = null;
		try
		{
			response = iIncubationTestMasterService.ActivateInactivateIncubationTest(orgId, incubationTestId, incubationTestStatus);
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
		return response;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/listIncubationTest/{orgId}/{offset}/{recordPerPage}", method = RequestMethod.GET)
	public @ResponseBody Response listIncubationTest(@PathVariable(value = "orgId") Integer orgId,
			@PathVariable(value = "offset") Integer offset,
			@PathVariable(value = "recordPerPage") Integer recordPerPage)
	{
		Response<GlobalCommonDto, Integer> response = null;
		try
		{
			response = iIncubationTestMasterService.listIncubationTest(orgId, offset, recordPerPage);
			return response;
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getToTalIncubationTest/{orgId}", method = RequestMethod.GET)
	public @ResponseBody Response getToTalIncubationTest(@PathVariable(value= "orgId") Integer orgId)
		{
			Response<IncubationTestMasterDto, Integer> response = null;
			try
				{
					response = iIncubationTestMasterService.getToTalIncubationTest(orgId);
					return response;
				}
			catch (Exception e)
				{
					e.printStackTrace();
					return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
				}
		}


}
