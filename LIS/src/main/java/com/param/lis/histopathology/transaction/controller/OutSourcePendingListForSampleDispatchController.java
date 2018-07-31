package com.param.lis.histopathology.transaction.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.param.lis.global.common.ICommonConstants;
import com.param.lis.global.common.IError;
import com.param.lis.global.common.JsonCheck;
import com.param.lis.global.common.Response;
import com.param.lis.histopathology.transaction.dto.OutSourceDetailMasterDto;
import com.param.lis.histopathology.transaction.dto.OutSourceMasterDto;
import com.param.lis.histopathology.transaction.service.IOutSourceMasterService;
import com.param.lis.histopathology.transaction.service.IOutSourcePendingListForSampleDispatchService;

import io.swagger.annotations.Api;

@RestController
@Api(value = "Out Sourced Pending list Assignment", tags = "OutSourceMaster")
@SuppressWarnings({ "rawtypes", "unchecked" })
@RequestMapping(value = "/LIS/OutSource")
public class OutSourcePendingListForSampleDispatchController implements ICommonConstants, IError
{
	final static Logger logger = Logger.getLogger(OutSourcePendingListForSampleDispatchController.class);
	
	@Autowired 
	private IOutSourcePendingListForSampleDispatchService iOutSourcePendingListForSampleDispatchService;
	
	
	@RequestMapping(value = "/pendingListForSampleDispatch", method = RequestMethod.POST)
	public @ResponseBody Response getoutSourcePendingListForSampleDispatch(@RequestBody OutSourceMasterDto outSourceMasterDto) 
	{
		Response<OutSourceMasterDto, Integer> response = null;
		try {
			response = iOutSourcePendingListForSampleDispatchService.outSourcePendingListForSampleDispatch(outSourceMasterDto);
			return response;
		} catch (Exception e) 
		{
			logger.error("Exception", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@RequestMapping(value = "/PendingListForSampleDispatch/count", method = RequestMethod.POST)
	public @ResponseBody Response getoutSourcePendingListForSampleDispatchCount(@RequestBody OutSourceMasterDto outSourceMasterDto) {
		Response<OutSourceMasterDto, Integer> response = null;
		try {
			response = iOutSourcePendingListForSampleDispatchService.getoutSourcePendingListForSampleDispatchCount(outSourceMasterDto);
			return response;
		} catch (Exception e) {
			logger.error("Exception", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}
	
	@RequestMapping(value = "/addPendingListOutSourceDetails", method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response saveOutSourceDetails(@RequestBody List<OutSourceMasterDto> outSourceMasterDto ) {
		Response<OutSourceMasterDto, Integer> response = null;
		try {
			/*	JsonCheck.mapJsonToObject(outSourceMasterDto, OutSourceMasterDto.class);*/
	     	response = iOutSourcePendingListForSampleDispatchService.saveOutSourceDetails(outSourceMasterDto);
			return response;
		} catch (Exception e) {
			logger.error("Exception", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}
	
	@RequestMapping(value = "/getOutSourceDetailsByOutSourceId", method = RequestMethod.POST)
	public @ResponseBody Response getOutSourceDetailsByOutSourceId(@RequestBody OutSourceDetailMasterDto outSourceDetailMasterDto) 
	{
		Response<OutSourceDetailMasterDto, Integer> response = null;
		try {
			response = iOutSourcePendingListForSampleDispatchService.getOutSourceDetailsByOutSourceId(outSourceDetailMasterDto);
			return response;
		} catch (Exception e) 
		{
			logger.error("Exception", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}
	
	
	
	

	
}
