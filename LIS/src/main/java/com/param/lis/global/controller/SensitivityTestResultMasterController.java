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
import com.param.lis.global.dto.SensitivityMasterDto;
import com.param.lis.global.service.ISensitivityTestResultMasterService;

@RestController
@SuppressWarnings("rawtypes")
@RequestMapping("/LIS/global")
public class SensitivityTestResultMasterController implements ICommonConstants, IError{

	@Autowired
	private ISensitivityTestResultMasterService iSensitivityTestResultMasterService;
	
	@RequestMapping(value = "/addSensitivityTestResult" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public Response addSensitivityTestResultMaster(@RequestBody SensitivityMasterDto sensitivityTestResultMasterDto){
		try{
			return iSensitivityTestResultMasterService.addSensitivityTestResultMaster(sensitivityTestResultMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERR_201,SENSITIVITY_ADD_FAIL, null, null);
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getaddSensitivityTestResultyId/{orgId}/{sensitivityTestResultId}", method = RequestMethod.GET)
	public @ResponseBody Response getSensitivityTestResultMasterById(@PathVariable(value = "orgId") Integer orgId,
			@PathVariable(value = "sensitivityTestResultId") Integer sensitivityTestResultId)
	{
		Response<GlobalCommonDto, Integer> response = null;
		try
		{
			response = iSensitivityTestResultMasterService.getSensitivityTestResultMasterById(orgId, sensitivityTestResultId);
			return response;
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}	
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/updateSensitivityTestResult", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response updateSensitivityTestResultMaster(@RequestBody SensitivityMasterDto sensitivityTestResultMasterDto)
	{
		Response<SensitivityMasterDto, Integer> response = null;
		try
		{
			response = iSensitivityTestResultMasterService.updateSensitivityTestResultMaster(sensitivityTestResultMasterDto);
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
		return response;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/activeInactiveSensitivityTestResult/{orgId}/{sensitivityTestResultId}/{sensitivityTestResultStatus}", method = RequestMethod.GET)
	public @ResponseBody Response ActivateInactivateSensitivityTestResultMaster(@PathVariable(value = "orgId") Integer orgId,
			@PathVariable(value = "sensitivityTestResultId") Integer sensitivityTestResultId,
			@PathVariable(value = "sensitivityTestResultStatus") Character sensitivityTestResultStatus)
	{
		Response<SensitivityMasterDto, Integer> response = null;
		try
		{
			response = iSensitivityTestResultMasterService.ActivateInactivateSensitivityTestResultMaster(orgId, sensitivityTestResultId, sensitivityTestResultStatus);
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
		return response;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/listSensitivityTestResult/{orgId}/{offset}/{recordPerPage}", method = RequestMethod.GET)
	public @ResponseBody Response listSensitivityTestResultMaster(@PathVariable(value = "orgId") Integer orgId,
			@PathVariable(value = "offset") Integer offset,
			@PathVariable(value = "recordPerPage") Integer recordPerPage)
	{
		Response<GlobalCommonDto, Integer> response = null;
		try
		{
			response = iSensitivityTestResultMasterService.listSensitivityTestResultMaster(orgId, offset, recordPerPage);
			return response;
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getToTallistlistSensitivityTestResult/{orgId}", method = RequestMethod.GET)
	public @ResponseBody Response getToTalSensitivityTestResultMaster(@PathVariable(value= "orgId") Integer orgId)
		{
			Response<SensitivityMasterDto, Integer> response = null;
			try
				{
					response = iSensitivityTestResultMasterService.getToTalSensitivityTestResultMaster(orgId);
					return response;
				}
			catch (Exception e)
				{
					e.printStackTrace();
					return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
				}
		}


}
