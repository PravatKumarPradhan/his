package com.param.global.org.common.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;
import com.param.global.org.common.dto.ReasonMasterDto;
import com.param.global.org.common.service.IReasonMasterService;

@RestController
@RequestMapping("/api/common")
@SuppressWarnings({ "unchecked", "rawtypes" })

public class ReasonMasterController implements ICommonConstants {

	@Autowired
	IReasonMasterService iReasonMasterService;

	@RequestMapping(value = "/saveReasonMaster", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response saveReasonMaster(@RequestBody ReasonMasterDto reasonMasterDto) {
		try {
			return iReasonMasterService.saveReasonMaster(reasonMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@RequestMapping(value = "/getReasonMasterById/{reasonId}/{orgId}", method = RequestMethod.GET)
	public @ResponseBody Response getEmployeeCategoryMasterById(@PathVariable("reasonId") int reasonId,
			@PathVariable("orgId") int orgId) {
		try {
			return iReasonMasterService.getReasonMasterById(reasonId, orgId);
		} catch (Exception e) {
			e.printStackTrace();
			return new Response<>(ERROR, ERROR_CODE, COMMON_ERROR_MESSAGE, null, null, e.getMessage());

		}
	}

	@RequestMapping(value = "/getReasonMasterList", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getReasonMasterList(@RequestBody ReasonMasterDto reasonMasterDto) {
		try {
			return iReasonMasterService.getReasonMasterList(reasonMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
			return new Response<>(ERROR, ERROR_CODE, COMMON_ERROR_MESSAGE, null, null, e.getMessage());
		}
	}

	@RequestMapping(value = "/updateReasonMaster", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response updateReasonTypeMaster(@RequestBody ReasonMasterDto reasonMasterDto) {
		try {
			return iReasonMasterService.updateReasonMaster(reasonMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@RequestMapping(value = "/updateReasonMasterStatus", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response updateReasonMasterStatus(@RequestBody ReasonMasterDto reasonMasterDto) {
		try {
			return iReasonMasterService.updateReasonMasterStatus(reasonMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@RequestMapping(value = "/getReasonMasterCount", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getReasonMasterCount(@RequestBody ReasonMasterDto reasonMasterDto) {
		try {
			return iReasonMasterService.getReasonMasterCount(reasonMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}
}
