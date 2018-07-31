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
import com.param.lis.global.common.JsonCheck;
import com.param.lis.global.common.Response;
import com.param.lis.global.dto.GlobalCommonDto;
import com.param.lis.global.dto.SampleMasterDto;
import com.param.lis.global.service.ISampleMasterService;

import ch.epfl.lamp.fjbg.Main;
import io.swagger.annotations.Api;

@RestController
@Api(value = "LIS_Global_Sample", tags = "Sample Master")
@SuppressWarnings({ "rawtypes", "unchecked" })
@RequestMapping(value = "/LIS/global")
public class SampleMasterController implements ICommonConstants, IError {
	
	@Autowired
	private ISampleMasterService iSampleMasterService;

	@RequestMapping(value = "/addSample", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response addSample(@RequestBody SampleMasterDto sampleMasterDto) {
		Response<SampleMasterDto, Integer> response = null;
		try {
			response = iSampleMasterService.addSampleMaster(sampleMasterDto);
			return response;
		} catch (Exception e) {
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@RequestMapping(value = "/getSamplebyId/{orgId}/{sampleId}", method = RequestMethod.GET)
	public @ResponseBody Response getSampleById(@PathVariable(value = "orgId") Integer orgId,
			@PathVariable(value = "sampleId") Integer sampleId) {
		Response<GlobalCommonDto, Integer> response = null;
		try {
			response = iSampleMasterService.getSampleMasterById(orgId, sampleId);
			return response;
		} catch (Exception e) {
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@RequestMapping(value = "/updateSample", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response updateSample(@RequestBody SampleMasterDto sampleMasterDto) {
		Response<SampleMasterDto, Integer> response = null;
		try {
			response = iSampleMasterService.updateSampleMaster(sampleMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
		return response;
	}

	@RequestMapping(value = "/activeInactiveSample/{orgId}/{sampleId}/{sampleStatus}", method = RequestMethod.GET)
	public @ResponseBody Response updateAccountingLedger(@PathVariable(value = "orgId") Integer orgId,
			@PathVariable(value = "sampleId") Integer sampleId,
			@PathVariable(value = "sampleStatus") Character sampleStatus) {
		Response<SampleMasterDto, Integer> response = null;
		try {
			response = iSampleMasterService.ActivateInactivateSampleMaster(orgId, sampleId, sampleStatus);
		} catch (Exception e) {
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
		return response;
	}

	@RequestMapping(value = "/listSampleMaster/{orgId}/{offset}/{recordPerPage}", method = RequestMethod.GET)
	public @ResponseBody Response listSampleMaster(@PathVariable(value = "orgId") Integer orgId,
			@PathVariable(value = "offset") Integer offset,
			@PathVariable(value = "recordPerPage") Integer recordPerPage) {
		Response<GlobalCommonDto, Integer> response = null;
		try {
			response = iSampleMasterService.listSampleMaster(orgId, offset, recordPerPage);
			return response;
		} catch (Exception e) {
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@RequestMapping(value = "/getTotalSampleMasterRecord/{orgId}", method = RequestMethod.GET)
	public @ResponseBody Response getTotalSampleMasterRecord(@PathVariable(value = "orgId") Integer orgId) {
		Response<SampleMasterDto, Integer> response = null;
		try {
			response = iSampleMasterService.getToTalSampleMasterRecord(orgId);
			return response;
		} catch (Exception e) {
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}
	
}
