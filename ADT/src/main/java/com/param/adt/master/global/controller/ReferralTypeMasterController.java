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
import com.param.adt.master.global.dto.ReferralTypeMasterDto;
import com.param.adt.master.global.service.IReferralTypeMasterService;

@Controller
@RequestMapping("/adt")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ReferralTypeMasterController implements ICommonConstants {
	@Autowired
	IReferralTypeMasterService iReferralTypeMasterService;

	@RequestMapping(value = "/saveReferralTypeMaster", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response saveReferralTypeMaster(@RequestBody ReferralTypeMasterDto referralTypeMasterDto) {
		try {

			return iReferralTypeMasterService.addReferralTypeMaster(referralTypeMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@RequestMapping(value = "/getReferralTypeMasterList", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getReferralTypeMasterList(@RequestBody ReferralTypeMasterDto referralTypeMasterDto) {
		try {
			return iReferralTypeMasterService.getReferralTypeMasterList(referralTypeMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@RequestMapping(value = "/updateReferralTypeMaster", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response updateReferralTypeMaster(@RequestBody ReferralTypeMasterDto referralTypeMasterDto) {
		try {
			return iReferralTypeMasterService.updateReferralTypeMaster(referralTypeMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@RequestMapping(value = "/getReferralTypeById", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getReferralTypeById(@RequestBody ReferralTypeMasterDto referralTypeMasterDto) {
		try {

			return iReferralTypeMasterService.getReferralTypeById(referralTypeMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@RequestMapping(value = "/updateStatusForReferralType", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response updateStatusForReferralType(
			@RequestBody ReferralTypeMasterDto referralTypeMasterDto) {
		try {

			return iReferralTypeMasterService.updateStatusForReferralType(referralTypeMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@RequestMapping(value = "/getActiveReferralTypeList", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getActiveReferralTypeList(@RequestBody ReferralTypeMasterDto referralTypeMasterDto) {
		try {
			return iReferralTypeMasterService.getActiveReferralTypeList(referralTypeMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}
	
	@RequestMapping(value = "/getReferralTypeCount", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getReferralTypeCount(	@RequestBody ReferralTypeMasterDto referralTypeMasterDto) {
		try {

			return iReferralTypeMasterService.getReferralTypeCount(referralTypeMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}
}
