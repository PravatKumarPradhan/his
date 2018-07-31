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
import com.param.global.org.common.dto.PriorityMasterDto;
import com.param.global.org.common.service.IPriorityMasterService;

@RestController
@RequestMapping("api/common")
@SuppressWarnings({ "rawtypes", "unchecked" })

public class PriorityMasterController implements ICommonConstants {

	@Autowired
	private IPriorityMasterService iPriorityMasterService;

	@RequestMapping(value = "/savePriorityMasterMaster", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response savePriorityMasterMaster(@RequestBody PriorityMasterDto priorityMasterDto) {
		try {
			return iPriorityMasterService.savePriorityMasterMaster(priorityMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@RequestMapping(value = "/getPriorityMasterById/{priorityId}/{orgId}", method = RequestMethod.GET)
	public @ResponseBody Response getPriorityMasterById(@PathVariable("priorityId") int priorityId,
			@PathVariable("orgId") int orgId) {
		try {
			return iPriorityMasterService.getPriorityMasterById(priorityId, orgId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@RequestMapping(value = "/getPriorityMasterList", method = RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getPriorityMasterList(@RequestBody PriorityMasterDto priorityMasterDto) {
		try {
			return iPriorityMasterService.getPriorityMasterList(priorityMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@RequestMapping(value = "/updatePriorityMaster", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response updatePriorityMaster(@RequestBody PriorityMasterDto priorityMasterDto) {
		try {
			return iPriorityMasterService.updatePriorityMaster(priorityMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@RequestMapping(value = "/updatePriorityMasterStatus", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response updatePriorityMasterStatus(@RequestBody PriorityMasterDto priorityMasterDto) {
		try {
			return iPriorityMasterService.updatePriorityMasterStatus(priorityMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@RequestMapping(value = "/getPriorityMasterCount", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getPriorityMasterCount(@RequestBody PriorityMasterDto priorityMasterDto) {
		try {
			return iPriorityMasterService.getPriorityMasterCount(priorityMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}
}
