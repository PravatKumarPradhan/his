package com.param.global.controller;

import in.param.exception.ApplicationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.param.global.common.ICommonConstants;
import com.param.global.common.IError;
import com.param.global.common.Response;
import com.param.global.dto.PatientCategoryMasterDto;
import com.param.global.service.IPatientCategoryMasterService;

@RestController
@RequestMapping(value = "/global")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class PatientCategoryController implements ICommonConstants, IError {

	@Autowired
	private IPatientCategoryMasterService iPatientCategoryMasterService;

	@RequestMapping(value = "/getPatientCategoryMaster", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Response getPatientCategoryMaster(PatientCategoryMasterDto patientCategoryMasterDto) {
		try {
			return iPatientCategoryMasterService.getPatientCategoryMaster(patientCategoryMasterDto);
		} catch (ApplicationException ae) {
			ae.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@RequestMapping(value = "/savePatientCategoryMaster", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response savePatientCategoryMaster(
			@RequestBody PatientCategoryMasterDto patientCategoryMasterDto) {
		try {
			return iPatientCategoryMasterService.savePatientCategoryMaster(patientCategoryMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@RequestMapping(value = "/getPatientCategoryMasterList", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getPatientCategoryMasterList(
			@RequestBody PatientCategoryMasterDto patientCategoryMasterDto) {
		try {
			return iPatientCategoryMasterService.getPatientCategoryMasterList(patientCategoryMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
			return new Response<>(ERROR, ERROR_CODE, COMMON_ERROR_MESSAGE, null, null, e.getMessage());

		}
	}

	@RequestMapping(value = "/getPatientCategoryMasterById/{patientCategoryId}/{orgId}", method = RequestMethod.GET)
	public @ResponseBody Response getPatientCategoryMasterById(@PathVariable("patientCategoryId") int patientCategoryId,
			@PathVariable("orgId") int orgId) {
		try {
			return iPatientCategoryMasterService.getPatientCategoryMasterById(patientCategoryId, orgId);
		} catch (Exception e) {
			e.printStackTrace();
			return new Response<>(ERROR, ERROR_CODE, COMMON_ERROR_MESSAGE, null, null, e.getMessage());

		}
	}

	@RequestMapping(value = "/updatePatientCategoryMaster", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response updatePatientCategoryMaster(
			@RequestBody PatientCategoryMasterDto patientCategoryMasterDto) {
		try {
			return iPatientCategoryMasterService.updatePatientCategoryMaster(patientCategoryMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@RequestMapping(value = "/updatePatientCategoryMasterStatus", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response updatePatientCategoryMasterStatus(
			@RequestBody PatientCategoryMasterDto patientCategoryMasterDto) {
		try {
			return iPatientCategoryMasterService.updatePatientCategoryMasterStatus(patientCategoryMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@RequestMapping(value = "/getPatientCategoryMasterCount", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getPatientCategoryMasterCount(
			@RequestBody PatientCategoryMasterDto patientCategoryMasterDto) {
		try {
			return iPatientCategoryMasterService.getPatientCategoryMasterCount(patientCategoryMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	
	@RequestMapping(value="/patientcategory/{orgId}", method=RequestMethod.GET)
	public Response getPatientCategoryByOrg(@PathVariable("orgId")int orgId){
		try{
			return iPatientCategoryMasterService.getPatientCategoryByOrg(orgId);
		}catch(ApplicationException ae){
			ae.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}
	
}
