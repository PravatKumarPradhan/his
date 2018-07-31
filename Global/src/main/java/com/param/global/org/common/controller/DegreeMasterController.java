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
import com.param.global.org.common.dto.DegreeMasterDto;
import com.param.global.org.common.service.IDegreeMasterService;

@RestController
@RequestMapping("api/common")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class DegreeMasterController implements ICommonConstants {

	@Autowired
	private IDegreeMasterService iDegreeMasterService;

	@RequestMapping(value = "/saveDegreeMaster", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response saveDegreeMaster(@RequestBody DegreeMasterDto degreeMasterDto) {
		try {
			return iDegreeMasterService.saveDegreeMaster(degreeMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@RequestMapping(value = "/getDegreeMasterById/{degreeId}/{orgId}", method = RequestMethod.GET)
	public @ResponseBody Response getDegreeMasterById(@PathVariable("degreeId") int degreeId,
			@PathVariable("orgId") int orgId) {
		try {
			return iDegreeMasterService.getDegreeMasterById(degreeId, orgId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@RequestMapping(value = "/getDegreeMasterList", method = RequestMethod.POST)
	public @ResponseBody Response getDegreeMasterList(@RequestBody DegreeMasterDto degreeMasterDto) {
		try {
			return iDegreeMasterService.getDegreeMasterList(degreeMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@RequestMapping(value = "/updateDegreeMaster", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response updateDegreeMaster(@RequestBody DegreeMasterDto degreeMasterDto) {
		try {
			return iDegreeMasterService.updateDegreeMaster(degreeMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@RequestMapping(value = "/updateDegreeMasterStatus", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response updateDegreeMasterStatus(@RequestBody DegreeMasterDto degreeMasterDto) {
		try {
			return iDegreeMasterService.updateDegreeMasterStatus(degreeMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@RequestMapping(value = "/getDegreeMasterCount", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getDegreeMasterCount(@RequestBody DegreeMasterDto degreeMasterDto) {
		try {
			return iDegreeMasterService.getDegreeMasterCount(degreeMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}
}
