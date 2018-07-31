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
import com.param.adt.discharge.service.IDueForDischargeService;
import com.param.global.dto.AdmissionDto;

@Controller
@RequestMapping("/adt")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class DueForDischargeController implements ICommonConstants
{
	@Autowired
	IDueForDischargeService iDueForDischargeService;
	
	@RequestMapping(value = "/saveDischargeRequest", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response saveDischargeRequest(@RequestBody DischargeDto dischargeDto) {
		try {
			
			return iDueForDischargeService.saveDischargeRequest(dischargeDto);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}
	
	
	@RequestMapping(value = "/getDischagePatientList", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getDischagePatientList(@RequestBody AdmissionDto admissionDto) {
		try {
			
			return iDueForDischargeService.getDischagePatientList(admissionDto);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}
	
	@RequestMapping(value = "/updateDischargePdd", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response updatePDD(@RequestBody DischargeDto dischargeDto) {
		try {
			
			return iDueForDischargeService.updatePDD(dischargeDto);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}
	
	
}
