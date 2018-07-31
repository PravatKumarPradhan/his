package com.param.adt.discharge.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.param.adt.common.ICommonConstants;
import com.param.adt.common.Response;
import com.param.adt.discharge.dto.DischargeDto;
import com.param.adt.discharge.service.IShortLeaveService;

@Controller
@RequestMapping("/adt")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ShortLeaveController implements ICommonConstants {

	@Autowired
	IShortLeaveService iShortLeaveService;

	@RequestMapping(value = "/saveShortLeaveRequest", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response saveShortLeaveRequest(@RequestBody DischargeDto dischargeDto) {
		try {
			return iShortLeaveService.saveShortLeaveRequest(dischargeDto);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}
	
	@RequestMapping(value = "/getShortLeaveRequestList", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getShortLeaveRequestList(@RequestBody DischargeDto dischargeDto) {
		try {
			return iShortLeaveService.getShortLeaveRequestList(dischargeDto);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}
	
	@RequestMapping(value = "/getShortLeaveRequestListForDoctor", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getShortLeaveRequestListForDoctor(@RequestBody DischargeDto dischargeDto) {
		try {
			return iShortLeaveService.getShortLeaveRequestListForDoctor(dischargeDto);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}
	
	@RequestMapping(value = "/acceptShotLeaveByDoctor", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response acceptShotLeaveByDoctor(@RequestBody DischargeDto dischargeDto) {
		try {
			return iShortLeaveService.acceptShotLeaveByDoctor(dischargeDto);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}
	
	@RequestMapping(value = "/rejectShotLeaveByDoctor", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response rejectShotLeaveByDoctor(@RequestBody DischargeDto dischargeDto) {
		try {
			return iShortLeaveService.rejectShotLeaveByDoctor(dischargeDto);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}
	
	@RequestMapping(value = "/acceptShotLeaveByBilling", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response acceptShotLeaveByBilling(@RequestBody DischargeDto dischargeDto) {
		try {
			return iShortLeaveService.acceptShotLeaveByBilling(dischargeDto);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}
	
	@RequestMapping(value = "/rejectShotLeaveByBilling", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response rejectShotLeaveByBilling(@RequestBody DischargeDto dischargeDto) {
		try {
			return iShortLeaveService.rejectShotLeaveByBilling(dischargeDto);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}
	
	@RequestMapping(value = "/releasePatientForShotLeave", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response releasePatientForShotLeave(@RequestBody DischargeDto dischargeDto) {
		try {
			return iShortLeaveService.releasePatientForShotLeave(dischargeDto);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}
}
	