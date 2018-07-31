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

import com.param.lis.global.common.ICommonConstants;
import com.param.lis.global.common.IError;
import com.param.lis.global.common.Response;
import com.param.lis.unit.dto.TestMasterDto;
import com.param.lis.unit.service.IMultiParamTestMasterService;


@RestController
@SuppressWarnings(
{ "rawtypes", "unchecked" })
@RequestMapping(value = "/LIS/unit")
public class MultiParamTestMasterController implements ICommonConstants, IError
{
	@Autowired
	private IMultiParamTestMasterService iMultiParamTestMasterService;

	final static Logger logger = Logger.getLogger(MultiParamTestMasterController.class);

	@RequestMapping(value = "/addMultiParameterTest", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response addMultiParameterTest(@RequestBody TestMasterDto testMasterDto)
	{
		Response<TestMasterDto, Integer> response = null;
		try
		{
			response = iMultiParamTestMasterService.addMultiParameterTest(testMasterDto);
			return response;
		} catch (Exception e)
		{
			logger.error("Exception", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@RequestMapping(value = "/getMultyParameterTest/{orgId}/{orgUnitId}/{testId}", method = RequestMethod.GET)
	public @ResponseBody Response getMultyParameterTest(@PathVariable(value = "orgId") Integer orgId,
			@PathVariable(value = "orgUnitId") Integer orgUnitId, @PathVariable(value = "testId") Integer testId)
	{
		Response<TestMasterDto, Integer> response = null;
		try
		{
			response = iMultiParamTestMasterService.getMultyParameterTest(orgId, orgUnitId, testId);
			return response;
		} catch (Exception e)
		{
			logger.error("Exception", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@RequestMapping(value = "/updateMultiParameterTest", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response updateMultiParameterTest(@RequestBody TestMasterDto testMasterDto)
	{
		Response<TestMasterDto, Integer> response = null;
		try
		{
			response = iMultiParamTestMasterService.updateMultiParameterTest(testMasterDto);
		} catch (Exception e)
		{
			logger.error("Exception", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
		return response;
	}

	@RequestMapping(value = "/activeInactiveMultiParamTest/{orgId}/{orgUnitId}/{testId}/{testStatus}", method = RequestMethod.GET)
	public @ResponseBody Response activeInactiveMultiParamTest(@PathVariable(value = "orgId") Integer orgId,
			@PathVariable(value = "orgUnitId") Integer orgUnitId, @PathVariable(value = "testId") Integer testId,
			@PathVariable(value = "testStatus") Character testStatus)
	{
		Response<TestMasterDto, Integer> response = null;
		try
		{
			response = iMultiParamTestMasterService.activeInactiveMultiParamTest(orgId, orgUnitId, testId, testStatus);
		} catch (Exception e)
		{
			logger.error("Exception", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
		return response;
	}

	@RequestMapping(value = "/listMultiParamTestMaster/{orgId}/{isHistoCyto}/{orgUnitId}/{offset}/{recordPerPage}", method = RequestMethod.GET)
	public @ResponseBody Response listMultiParamTestMaster(@PathVariable(value = "orgId") Integer orgId,@PathVariable(value = "isHistoCyto") Character isHistoCyto,
			@PathVariable(value = "orgUnitId") Integer orgUnitId, @PathVariable(value = "offset") Integer offset,
			@PathVariable(value = "recordPerPage") Integer recordPerPage)
	{
		Response<TestMasterDto, Integer> response = null;
		try
		{
			response = iMultiParamTestMasterService.listMultiParamTestMaster(orgId, isHistoCyto,orgUnitId, offset, recordPerPage);
			return response;
		} catch (Exception e)
		{
			logger.error("Exception", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@RequestMapping(value = "/getTotalMultiTestRecord/{orgId}/{isHistoCyto}/{orgUnitId}", method = RequestMethod.GET)
	public @ResponseBody Response getTotalMultiTestRecord(@PathVariable(value = "orgId") Integer orgId,@PathVariable(value = "isHistoCyto") Character isHistoCyto,
			@PathVariable(value = "orgUnitId") Integer orgUnitId)
	{
		Response<TestMasterDto, Integer> response = null;
		try
		{
			response = iMultiParamTestMasterService.getTotalMultiTestRecord(orgId, isHistoCyto,orgUnitId);
			return response;
		} catch (Exception e)
		{
			logger.error("Exception", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}

	}

}
