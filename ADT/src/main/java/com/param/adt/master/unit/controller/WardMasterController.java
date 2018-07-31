package com.param.adt.master.unit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.param.adt.master.unit.dto.WardMasterDto;
import com.param.adt.master.unit.service.IWardMasterService;
import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;

@RestController
@RequestMapping("api/adt")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class WardMasterController implements ICommonConstants {

	@Autowired
	private IWardMasterService iWardMasterService;

	@RequestMapping(value = "/saveWardMasterMaster", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response saveWardMasterMaster(@RequestBody WardMasterDto wardMasterDto) {
		try {
			return iWardMasterService.saveWardMasterMaster(wardMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@RequestMapping(value = "/getWardMasterById/{wardId}/{orgId}/{unitId}", method = RequestMethod.GET)
	public @ResponseBody Response getWardMasterById(@PathVariable("wardId") int wardId,
			@PathVariable("orgId") int orgId,@PathVariable("unitId") int unitId) {
		try {
			return iWardMasterService.getWardMasterById(wardId, orgId,unitId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@RequestMapping(value = "/getWardMasterList", method = RequestMethod.POST)
	public @ResponseBody Response getWardMasterList(@RequestBody WardMasterDto wardMasterDto) {
		try {
			return iWardMasterService.getWardMasterList(wardMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@RequestMapping(value = "/updateWardMaster", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response updateDesignationMaster(@RequestBody WardMasterDto wardMasterDto) {
		try {
			return iWardMasterService.updateWardMaster(wardMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@RequestMapping(value = "/updateWardMasterStatus", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response updateWardMasterStatus(@RequestBody WardMasterDto wardMasterDto) {
		try {
			return iWardMasterService.updateWardMasterStatus(wardMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@RequestMapping(value = "/getWardMasterCount", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getWardMasterCount(@RequestBody WardMasterDto wardMasterDto) {
		try {
			return iWardMasterService.getWardMasterCount(wardMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}
}
