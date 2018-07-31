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
import com.param.adt.master.global.dto.ConsentTypeMasterDto;
import com.param.adt.master.global.service.IConsentTypeMasterService;

@Controller
@RequestMapping("/adt")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ConsentTypeMasterController implements ICommonConstants 
{
	@Autowired
	IConsentTypeMasterService iConsentTypeMasterService;

	@RequestMapping(value = "/saveConsentTypeMaster", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response saveConsentTypeMaster(@RequestBody ConsentTypeMasterDto consentTypeMasterDto) {
		try {

			return iConsentTypeMasterService.addConsentTypeMaster(consentTypeMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@RequestMapping(value = "/getConsentTypeMasterList", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getConsentTypeMasterList(@RequestBody ConsentTypeMasterDto consentTypeMasterDto) {
		try {
			return iConsentTypeMasterService.getConsentTypeMasterList(consentTypeMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@RequestMapping(value = "/updateConsentTypeMaster", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response updateConsentTypeMaster(@RequestBody ConsentTypeMasterDto consentTypeMasterDto) {
		try {
			return iConsentTypeMasterService.updateConsentTypeMaster(consentTypeMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@RequestMapping(value = "/getConsentTypeById", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getConsentTypeById(@RequestBody ConsentTypeMasterDto consentTypeMasterDto) {
		try {

			return iConsentTypeMasterService.getConsentTypeById(consentTypeMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@RequestMapping(value = "/updateStatusForConsentType", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response updateStatusForConsentType(@RequestBody ConsentTypeMasterDto consentTypeMasterDto) {
		try {

			return iConsentTypeMasterService.updateStatusForConsentType(consentTypeMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@RequestMapping(value = "/getActiveConsentTypeList", method = RequestMethod.GET)
	public @ResponseBody Response getActiveConsentTypeList() {
		try {
			return iConsentTypeMasterService.getActiveConsentTypeList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}
	
	@RequestMapping(value = "/getConsentTypeCount", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getConsentTypeCount(@RequestBody ConsentTypeMasterDto consentTypeMasterDto) {
		try {

			return iConsentTypeMasterService.getConsentTypeCount(consentTypeMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}
}
