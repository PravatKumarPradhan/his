package com.param.global.org.controller;

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
import com.param.global.org.dto.GeneralLedgerMasterDto;
import com.param.global.org.service.IGeneralLedgerMasterService;
@RestController
@RequestMapping("/org")
@SuppressWarnings({ "rawtypes", "unchecked" })

public class GeneralLedgerMasterController implements ICommonConstants{

	@Autowired
	IGeneralLedgerMasterService iGeneralLedgerMasterService;

	@RequestMapping(value = "/saveGeneralLedgerMaster", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response saveGeneralLedgerMaster(@RequestBody GeneralLedgerMasterDto generalLedgerMasterDto) {
		try {
			return iGeneralLedgerMasterService.saveGeneralLedgerMaster(generalLedgerMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@RequestMapping(value = "/getGeneralLedgerMasterById/{lederId}/{orgId}", method = RequestMethod.GET)
	public @ResponseBody Response getLiceneceTypeMasterById(@PathVariable("lederId") int lederId,@PathVariable("orgId") int orgId) {
		try {
			return iGeneralLedgerMasterService.getGeneralLedgerMasterById(lederId,orgId);
		} catch (Exception e) {
			e.printStackTrace();
			return new Response<>(ERROR, ERROR_CODE, COMMON_ERROR_MESSAGE, null, null, e.getMessage());

		}
	}
	@RequestMapping(value = "/getGeneralLedgerMasterList/{orgId}", method = RequestMethod.GET)
	public @ResponseBody Response getLiceneceTypeMasterList(@PathVariable("orgId") int orgId) {
		try {
			return iGeneralLedgerMasterService.getGeneralLedgerMasterList(orgId);
		} catch (Exception e) {
			e.printStackTrace();
			return new Response<>(ERROR, ERROR_CODE, COMMON_ERROR_MESSAGE, null, null, e.getMessage());
		}
	}
	
	@RequestMapping(value = "/getGeneralLedgerMasterActiveList/{orgId}", method = RequestMethod.GET)
	public @ResponseBody Response getGeneralLedgerMasterActiveList(@PathVariable("orgId") int orgId) {
		try {
			return iGeneralLedgerMasterService.getGeneralLedgerMasterActiveList(orgId);
		} catch (Exception e) {
			e.printStackTrace();
			return new Response<>(ERROR, ERROR_CODE, COMMON_ERROR_MESSAGE, null, null, e.getMessage());
		}
	}
	@RequestMapping(value = "/updateGeneralLedgerMaster", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response updateGeneralLedgerMaster(@RequestBody GeneralLedgerMasterDto generalLedgerMasterDto) {
		try {
			return iGeneralLedgerMasterService.updateGeneralLedgerMaster(generalLedgerMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}
	@RequestMapping(value = "/updateGeneralLedgerMasterStatus", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response updateGeneralLedgerMasterStatus(@RequestBody GeneralLedgerMasterDto generalLedgerMasterDto) {
		try {
			return iGeneralLedgerMasterService.updateGeneralLedgerMasterStatus(generalLedgerMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}
}
