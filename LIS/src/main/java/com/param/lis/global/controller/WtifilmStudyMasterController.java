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
import com.param.lis.global.dto.WtfilmStudyMasterDto;
import com.param.lis.global.service.IWtfilmStudyMasterService;

@RestController
@SuppressWarnings("rawtypes")
@RequestMapping("/LIS/global")
public class WtifilmStudyMasterController implements ICommonConstants, IError{

	@Autowired
	private IWtfilmStudyMasterService iWtfilmStudyMasterService;
	
	@RequestMapping(value = "/addWtfilmStudy" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public Response addWtfilmStudyMaster(@RequestBody WtfilmStudyMasterDto wtfilmStudyMasterDto){
		try{
			return iWtfilmStudyMasterService.addWtfilmStudyMaster(wtfilmStudyMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERR_201,WTIFILMSTUDY_ADD_SUCC, null, null);
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getaddWtfilmStudyById/{orgId}/{wtfilmStudyId}", method = RequestMethod.GET)
	public @ResponseBody Response getWtfilmStudyMasterById(@PathVariable(value = "orgId") Integer orgId,
			@PathVariable(value = "wtfilmStudyId") Integer wtfilmStudyId)
	{
		Response<GlobalCommonDto, Integer> response = null;
		try
		{
			response = iWtfilmStudyMasterService.getWtfilmStudyMasterById(orgId, wtfilmStudyId);
			return response;
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}	
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/updateWtifilmStudyMaster", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response updateWtfilmStudyMaster(@RequestBody WtfilmStudyMasterDto WtfilmStudyMasterDto)
	{
		Response<WtfilmStudyMasterDto, Integer> response = null;
		try
		{
			response = iWtfilmStudyMasterService.updateWtfilmStudyMaster(WtfilmStudyMasterDto);
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
		return response;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/activeInactiveWtfilmStudy/{orgId}/{wtfilmStudyId}/{wtfilmStudyStatus}", method = RequestMethod.GET)
	public @ResponseBody Response ActivateInactivateWtfilmStudyMaster(@PathVariable(value = "orgId") Integer orgId,
			@PathVariable(value = "wtfilmStudyId") Integer wtfilmStudyId,
			@PathVariable(value = "wtfilmStudyStatus") Character wtfilmStudyStatus)
	{
		Response<WtfilmStudyMasterDto, Integer> response = null;
		try
		{
			response = iWtfilmStudyMasterService.ActivateInactivateWtfilmStudyMaster(orgId, wtfilmStudyId, wtfilmStudyStatus);
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
		return response;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/listWtfilmStudy/{orgId}/{offset}/{recordPerPage}", method = RequestMethod.GET)
	public @ResponseBody Response listWtfilmStudyMaster(@PathVariable(value = "orgId") Integer orgId,
			@PathVariable(value = "offset") Integer offset,
			@PathVariable(value = "recordPerPage") Integer recordPerPage)
	{
		Response<GlobalCommonDto, Integer> response = null;
		try
		{
			response = iWtfilmStudyMasterService.listWtfilmStudyMaster(orgId, offset, recordPerPage);
			return response;
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getToTallistlistWtfilmStudy/{orgId}", method = RequestMethod.GET)
	public @ResponseBody Response getToTalWtfilmStudyMaster(@PathVariable(value= "orgId") Integer orgId)
		{
			Response<WtfilmStudyMasterDto, Integer> response = null;
			try
				{
					response = iWtfilmStudyMasterService.getToTalWtfilmStudyMaster(orgId);
					return response;
				}
			catch (Exception e)
				{
					e.printStackTrace();
					return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
				}
		}


}
