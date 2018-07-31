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
import com.param.global.org.common.dto.RejectionMasterDto;
import com.param.global.org.common.service.IRejectionMasterService;

@RestController
@RequestMapping(value = "api/common")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class RejectionMasterController implements ICommonConstants {

	@Autowired
	IRejectionMasterService iRejectionMasterService;

	@RequestMapping(value = "/saveRejectionMaster", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response saveRejectionMaster(@RequestBody RejectionMasterDto rejectionMasterDto) {
		try {
			return iRejectionMasterService.saveRejectionMaster(rejectionMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@RequestMapping(value = "/getRejectionMasterById/{rejectId}/{orgId}", method = RequestMethod.GET)
	public @ResponseBody Response getRejectionMasterById(@PathVariable("rejectId") int rejectId,
			@PathVariable("orgId") int orgId) {
		try {
			return iRejectionMasterService.getRejectionMasterById(rejectId, orgId);
		} catch (Exception e) {
			e.printStackTrace();
			return new Response<>(ERROR, ERROR_CODE, COMMON_ERROR_MESSAGE, null, null, e.getMessage());

		}
	}

	@RequestMapping(value = "/getRejectionMasterList", method = RequestMethod.POST)
	public @ResponseBody Response getRejectionMasterList(@RequestBody RejectionMasterDto rejectionMasterDto) {
		try {
			return iRejectionMasterService.getRejectionMasterList(rejectionMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
			return new Response<>(ERROR, ERROR_CODE, COMMON_ERROR_MESSAGE, null, null, e.getMessage());
		}
	}

	@RequestMapping(value = "/updateRejectionMaster", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response updateRejectionMaster(@RequestBody RejectionMasterDto rejectionMasterDto) {
		try {
			return iRejectionMasterService.updateRejectionMaster(rejectionMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@RequestMapping(value = "/updateRejectionMasterStatus", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response updateRejectionMasterStatus(@RequestBody RejectionMasterDto rejectionMasterDto) {
		try {
			return iRejectionMasterService.updateRejectionMasterStatus(rejectionMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@RequestMapping(value = "/getRejectionMasterCount", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getRejectionMasterCount(@RequestBody RejectionMasterDto rejectionMasterDto) {
		try {
			return iRejectionMasterService.getRejectionMasterCount(rejectionMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}
}
