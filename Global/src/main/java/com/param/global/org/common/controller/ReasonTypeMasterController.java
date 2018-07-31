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
import com.param.global.org.common.dto.ReasonTypeMasterDto;
import com.param.global.org.common.service.IReasonTypeMasterService;

@RestController
@RequestMapping("/api/common")

@SuppressWarnings({ "unchecked", "rawtypes" })
public class ReasonTypeMasterController implements ICommonConstants {

	@Autowired
	IReasonTypeMasterService iReasonTypeMasterService;


	@RequestMapping(value = "/saveReasonTypeMaster", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response saveReasonTypeMaster(@RequestBody ReasonTypeMasterDto reasonTypeMasterDto) {
		try {
			return iReasonTypeMasterService.saveReasonTypeMaster(reasonTypeMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@RequestMapping(value = "/getReasonTypeMasterById/{reasonTypeId}/{orgId}", method = RequestMethod.GET)
	public @ResponseBody Response getEmployeeCategoryMasterById(@PathVariable("reasonTypeId") int reasonTypeId,
			@PathVariable("orgId") int orgId) {
		try {
			return iReasonTypeMasterService.getReasonTypeMasterById(reasonTypeId, orgId);
		} catch (Exception e) {
			e.printStackTrace();
			return new Response<>(ERROR, ERROR_CODE, COMMON_ERROR_MESSAGE, null, null, e.getMessage());

		}
	}

	@RequestMapping(value = "/getReasonTypeMasterList", method = RequestMethod.POST)
	public @ResponseBody Response getReasonTypeMasterList(@RequestBody ReasonTypeMasterDto reasonTypeMasterDto) {
		try {
			return iReasonTypeMasterService.getReasonTypeMasterList(reasonTypeMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
			return new Response<>(ERROR, ERROR_CODE, COMMON_ERROR_MESSAGE, null, null, e.getMessage());
		}
	}

	@RequestMapping(value = "/updateReasonTypeMaster", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response updateReasonTypeMaster(@RequestBody ReasonTypeMasterDto reasonTypeMasterDto) {
		try {
			return iReasonTypeMasterService.updateReasonTypeMaster(reasonTypeMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@RequestMapping(value = "/updateReasonTypeMasterStatus", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response updateReasonTypeMasterStatus(@RequestBody ReasonTypeMasterDto reasonTypeMasterDto) {
		try {
			return iReasonTypeMasterService.updateReasonTypeMasterStatus(reasonTypeMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@RequestMapping(value = "/getReasonTypeMasterCount", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getReasonTypeMasterCount(@RequestBody ReasonTypeMasterDto reasonTypeMasterDto) {
		try {
			return iReasonTypeMasterService.getReasonTypeMasterCount(reasonTypeMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

}
