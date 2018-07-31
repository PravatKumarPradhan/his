package com.param.lis.unit.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.param.lis.global.common.ICommonConstants;
import com.param.lis.global.common.IError;
import com.param.lis.global.common.JsonCheck;
import com.param.lis.global.common.Response;
import com.param.lis.unit.dto.TestMasterDto;
import com.param.lis.unit.service.ISingleParamTestMasterService;

@RestController
@SuppressWarnings(
{ "rawtypes", "unchecked" })
@RequestMapping(value = "/LIS/unit")
public class SingleParamTestMasterController implements ICommonConstants, IError
{
	@Autowired
	private ISingleParamTestMasterService iTestMasterService;

	final static Logger logger = Logger.getLogger(SingleParamTestMasterController.class);

	@RequestMapping(value = "/addSingleParameterTest", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response addSingleParameterTest(@RequestBody TestMasterDto  testMasterDto)
	{
		Response<TestMasterDto, Integer> response = null;
		try
		{
			 
			/*System.out.println(
			    gson.fromJson(testMasterDto,
			    		TestMasterDto.class)+testMasterDto);*/
			// System.out.println(testMasterDto);
			response = iTestMasterService.addSingleParameterTest(testMasterDto);
			return response;
			// return null;
		} catch (Exception e)
		{
			logger.error("Exception", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@RequestMapping(value = "/getSingleParameterTest/{orgId}/{orgUnitId}/{testId}", method = RequestMethod.GET)
	public @ResponseBody Response getSingleParameterTest(@PathVariable(value = "orgId") Integer orgId,
			@PathVariable(value = "orgUnitId") Integer orgUnitId, @PathVariable(value = "testId") Integer testId)
	{
		Response<TestMasterDto, Integer> response = null;
		try
		{
			response = iTestMasterService.getSingleParameterTest(orgId, orgUnitId, testId);
			return response;
		} catch (Exception e)
		{
			logger.error("Exception", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@RequestMapping(value = "/updateSingleParameterTest", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response updateSingleParameterTest(@RequestBody TestMasterDto testMasterDto)
	{
		Response<TestMasterDto, Integer> response = null;
		try
		{
			response = iTestMasterService.updateSingleParameterTest(testMasterDto);
		} catch (Exception e)
		{
			logger.error("Exception", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
		return response;
	}

	@RequestMapping(value = "/activeInactiveTest/{orgId}/{orgUnitId}/{testId}/{parameterId}/{testStatus}", method = RequestMethod.GET)
	public @ResponseBody Response activeInactiveTest(@PathVariable(value = "orgId") Integer orgId,
			@PathVariable(value = "orgUnitId") Integer orgUnitId, @PathVariable(value = "testId") Integer testId
			,@PathVariable(value = "parameterId") Integer parameterId,
			@PathVariable(value = "testStatus") Character testStatus)
	{
		Response<TestMasterDto, Integer> response = null;
		try
		{
			response = iTestMasterService.activeInactiveTest(orgId, orgUnitId, testId,parameterId, testStatus);
		} catch (Exception e)
		{
			logger.error("Exception", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
		return response;
	}

	@RequestMapping(value = "/listTestMaster/{orgId}/{isHistoCyto}/{orgUnitId}/{offset}/{recordPerPage}", method = RequestMethod.GET)
	public @ResponseBody Response listTestMaster(@PathVariable(value = "orgId") Integer orgId,@PathVariable(value = "isHistoCyto") Character isHistoCyto,
			@PathVariable(value = "orgUnitId") Integer orgUnitId, @PathVariable(value = "offset") Integer offset,
			@PathVariable(value = "recordPerPage") Integer recordPerPage)
	{
		Response<TestMasterDto, Integer> response = null;
		try
		{
			response = iTestMasterService.listTestMaster(orgId, isHistoCyto, orgUnitId, offset, recordPerPage);
			return response;
		} catch (Exception e)
		{
			logger.error("Exception", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@RequestMapping(value = "/getTotalTestRecord/{orgId}/{isHistoCyto}/{orgUnitId}", method = RequestMethod.GET)
	public @ResponseBody Response getTotalTestRecord(@PathVariable(value = "orgId") Integer orgId, @PathVariable(value = "isHistoCyto") Character isHistoCyto,
			@PathVariable(value = "orgUnitId") Integer orgUnitId)
	{
		Response<TestMasterDto, Integer> response = null;
		try
		{
			response = iTestMasterService.getTotalTestRecord(orgId,isHistoCyto, orgUnitId);
			return response;
		} catch (Exception e)
		{
			logger.error("Exception", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}	
	
	@RequestMapping(value = "/checkServiceAvaiable", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE )
	public @ResponseBody Response checkServiceAvaiable(@RequestBody TestMasterDto  testMasterDto)
	{
		Response<TestMasterDto, Integer> response = null;
		try
		{
			response = iTestMasterService.checkServiceAvaiable(testMasterDto);
			return response;
		} catch (Exception e)
		{
			logger.error("Exception", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

}
