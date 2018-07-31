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
import com.param.adt.discharge.service.IMarkedForDischargeService;
import com.param.global.dto.AdmissionDto;

@Controller
@RequestMapping("/adt")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class MarkedForDischargeController implements ICommonConstants
{
	@Autowired
	IMarkedForDischargeService iMarkedForDischargeService;
	
	
	@RequestMapping(value = "/getDischargePatientListForNursing", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getDischagePatientListForNursing(@RequestBody DischargeDto dischargeDto) {
		try {
			
			return iMarkedForDischargeService.getDischagePatientListForNursing(dischargeDto);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}
	
	@RequestMapping(value = "/getPatientServiceOrderList", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getPatientServiceOrderList(@RequestBody AdmissionDto admissionDto) {
		try {
			
			return iMarkedForDischargeService.getPatientServiceOrderList(admissionDto);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}
	
	@RequestMapping(value = "/updatePatientServiceOrder", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response updatePatientServiceOrder(@RequestBody AdmissionDto admissionDto) {
		try {
			
			return iMarkedForDischargeService.updatePatientServiceOrder(admissionDto);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}
	
	@RequestMapping(value = "/getPatientReadyForBillingList", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getPatientReadyForBillingList(@RequestBody DischargeDto dischargeDto) {
		try {
			
			return iMarkedForDischargeService.getPatientReadyForBillingList(dischargeDto);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}
	
	@RequestMapping(value = "/getPatientFinalDischargeList", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getPatientFinalDischargeList(@RequestBody DischargeDto dischargeDto) {
		try {
			
			return iMarkedForDischargeService.getPatientFinalDischargeList(dischargeDto);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}
	
	@RequestMapping(value = "/updatePatientReadyForBillingStatus", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response updatePatientReadyForBillingStatus(@RequestBody DischargeDto dischargeDto) {
		try {
			
			return iMarkedForDischargeService.updatePatientReadyForBillingStatus(dischargeDto);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}
	
	@RequestMapping(value = "/vacateBed", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response vacateBed(@RequestBody DischargeDto dischargeDto) {
		try {
			
			return iMarkedForDischargeService.vacateBed(dischargeDto);

		} catch (Exception e) {
			e.printStackTrace();
		
		}
		return new Response(ERROR, null, null, null, null);
	}
}
