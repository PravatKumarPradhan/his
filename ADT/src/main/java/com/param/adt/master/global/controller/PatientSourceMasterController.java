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
import com.param.adt.master.global.dto.PatientSourceMasterDto;
import com.param.adt.master.global.service.IPatientSourceMasterService;

@Controller
@RequestMapping("/adt")
@SuppressWarnings({"rawtypes","unchecked"})
public class PatientSourceMasterController implements ICommonConstants
{
	@Autowired
	private IPatientSourceMasterService iPatientSourceMasterService;


	@RequestMapping(value = "/savePatientSourceMaster", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response savePatientSourceMaster(@RequestBody PatientSourceMasterDto patientSourceMasterDto) {
		try {

			return iPatientSourceMasterService.addPatientSourceMaster(patientSourceMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@RequestMapping(value = "/getPatientSourceMasterList", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getPatientSourceMasterList(@RequestBody PatientSourceMasterDto patientSourceMasterDto) {
		try {
			return iPatientSourceMasterService.getPatientSourceMasterList(patientSourceMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@RequestMapping(value = "/updatePatientSourceMaster", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response updatePatientSourceMaster(@RequestBody PatientSourceMasterDto patientSourceMasterDto) {
		try {
			return iPatientSourceMasterService.updatePatientSourceMaster(patientSourceMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@RequestMapping(value = "/getPatientSourceById", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getPatientSourceById(@RequestBody PatientSourceMasterDto patientSourceMasterDto) {
		try {

			return iPatientSourceMasterService.getPatientSourceById(patientSourceMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@RequestMapping(value = "/updateStatusForPatientSource", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response updateStatusForPatientSource(@RequestBody PatientSourceMasterDto patientSourceMasterDto) {
		try {

			return iPatientSourceMasterService.updateStatusForPatientSource(patientSourceMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@RequestMapping(value = "/getActivePatientSourceList", method = RequestMethod.GET)
	public @ResponseBody Response getActivePatientSourceList() {
		try {
			return iPatientSourceMasterService.getActivePatientSourceList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}
	
	@RequestMapping(value = "/getPatientSourceCount", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getPatientSourceCount(@RequestBody PatientSourceMasterDto patientSourceMasterDto) {
		try {

			return iPatientSourceMasterService.getPatientSourceCount(patientSourceMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}
}
