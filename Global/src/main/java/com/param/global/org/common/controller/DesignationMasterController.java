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
import com.param.global.org.common.dto.DesignationMasterDto;
import com.param.global.org.common.service.IDesignationMasterService;

@RestController
@RequestMapping("api/common")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class DesignationMasterController implements ICommonConstants {

	@Autowired
	private IDesignationMasterService iDesignationMasterService;

	@RequestMapping(value = "/saveDesignationMaster", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response saveDesignationMaster(@RequestBody DesignationMasterDto designationMasterDto) {
		try {
			return iDesignationMasterService.saveDesignationMaster(designationMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@RequestMapping(value = "/getDesignationMasterById/{designationId}/{orgId}", method = RequestMethod.GET)
	public @ResponseBody Response getDesignationMasterById(@PathVariable("designationId") int designationId,
			@PathVariable("orgId") int orgId) {
		try {
			return iDesignationMasterService.getDesignationMasterById(designationId, orgId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@RequestMapping(value = "/getDesignationMasterList", method = RequestMethod.POST)
	public @ResponseBody Response getDesignationMasterList(@RequestBody DesignationMasterDto designationMasterDto) {
		try {
			return iDesignationMasterService.getDesignationMasterList(designationMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@RequestMapping(value = "/updateDesignationMaster", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response updateDesignationMaster(@RequestBody DesignationMasterDto designationMasterDto) {
		try {
			return iDesignationMasterService.updateDesignationMaster(designationMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@RequestMapping(value = "/updateDesignationMasterStatus", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response updateDesignationMasterStatus(@RequestBody DesignationMasterDto designationMasterDto) {
		try {
			return iDesignationMasterService.updateDesignationMasterStatus(designationMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@RequestMapping(value = "/getDesignationMasterCount", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getDegreeMasterCount(@RequestBody DesignationMasterDto designationMasterDto) {
		try {
			return iDesignationMasterService.getDesignationMasterCount(designationMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}
}
