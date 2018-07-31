package com.param.adt.master.global.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.param.adt.common.ICommonConstants;
import com.param.adt.common.Response;
import com.param.adt.master.global.dto.ConsentMasterDto;
import com.param.adt.master.global.service.IConsentMasterService;

@Controller
@RequestMapping("/adt")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ConsentMasterController implements ICommonConstants
{
	@Autowired
	private IConsentMasterService iConsentMasterService;
	
	@RequestMapping(value = "/saveConsentMaster", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response saveConsentMaster(@RequestBody ConsentMasterDto consentMasterDto) {
		try {

			return iConsentMasterService.addConsentMaster(consentMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@RequestMapping(value = "/getConsentMasterList", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getConsentMasterList(@RequestBody ConsentMasterDto consentMasterDto) {
		try {
			return iConsentMasterService.getConsentMasterList(consentMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@RequestMapping(value = "/updateConsentMaster", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response updateConsentMaster(@RequestBody ConsentMasterDto consentMasterDto) {
		try {
			return iConsentMasterService.updateConsentMaster(consentMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@RequestMapping(value = "/getConsentById", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getConsentById(@RequestBody ConsentMasterDto consentMasterDto) {
		try {

			return iConsentMasterService.getConsentById(consentMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@RequestMapping(value = "/updateStatusForConsent", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response updateStatusForConsent(@RequestBody ConsentMasterDto consentMasterDto) {
		try {

			return iConsentMasterService.updateStatusForConsent(consentMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@RequestMapping(value = "/getActiveConsentList", method = RequestMethod.GET)
	public @ResponseBody Response getActiveConsentList() {
		try {
			return iConsentMasterService.getActiveConsentList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}
	
	@RequestMapping(value = "/getConsentCount", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getConsentCount(@RequestBody ConsentMasterDto consentMasterDto) {
		try {

			return iConsentMasterService.getConsentCount(consentMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}
}
