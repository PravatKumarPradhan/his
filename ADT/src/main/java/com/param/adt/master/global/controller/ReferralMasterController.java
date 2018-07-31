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
import com.param.adt.master.global.dto.ReferralMasterDto;
import com.param.adt.master.global.service.IReferralMasterService;
import com.param.global.dto.AreaMasterDto;

@Controller
@RequestMapping("/adt")
@SuppressWarnings({"rawtypes","unchecked"})
public class ReferralMasterController implements ICommonConstants 
{
	@Autowired
	private IReferralMasterService iReferralMasterService;
	
	@RequestMapping(value = "/saveReferralMaster", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response saveReferralMaster(@RequestBody ReferralMasterDto referralMasterDto) {
		try {

			return iReferralMasterService.addReferralMaster(referralMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@RequestMapping(value = "/getReferralMasterList",method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getReferralMasterList(@RequestBody ReferralMasterDto referralMasterDto) {
		try {
			return iReferralMasterService.getReferralMasterList(referralMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@RequestMapping(value = "/updateReferralMaster", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response updateReferralMaster(@RequestBody ReferralMasterDto referralMasterDto) {
		try {
			return iReferralMasterService.updateReferralMaster(referralMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@RequestMapping(value = "/getReferralById", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getReferralById(@RequestBody ReferralMasterDto referralMasterDto) {
		try {

			return iReferralMasterService.getReferralById(referralMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@RequestMapping(value = "/updateStatusForReferral", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response updateStatusForReferral(@RequestBody ReferralMasterDto referralMasterDto) {
		try {

			return iReferralMasterService.updateStatusForReferral(referralMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@RequestMapping(value = "/getActiveReferralList", method = RequestMethod.GET)
	public @ResponseBody Response getActiveReferralList() {
		try {
			return iReferralMasterService.getActiveReferralList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}
	
	@RequestMapping(value = "/getAreaByCityId", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getAreaByCityId(@RequestBody AreaMasterDto areaMasterDto) {
		try {

			return iReferralMasterService.getAreaByCityId(areaMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}
	
	@RequestMapping(value = "/getReferralCount",method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getReferralCount(@RequestBody ReferralMasterDto referralMasterDto) {
		try {
			return iReferralMasterService.getReferralCount(referralMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@RequestMapping(value = "/getReferralListByReferralTypeId",method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getReferralListByReferralTypeId(@RequestBody ReferralMasterDto referralMasterDto) {
		try {
			return iReferralMasterService.getReferralListByReferralTypeId(referralMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}
	
	
}
