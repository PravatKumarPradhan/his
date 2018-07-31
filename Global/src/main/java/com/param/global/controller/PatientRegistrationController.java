package com.param.global.controller;

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
import com.param.global.dto.PatientRegistrationDto;
import com.param.global.service.IPatientRegistrationService;

@RestController
@RequestMapping(value="api/global")
@SuppressWarnings({ "unchecked", "rawtypes"})
public class PatientRegistrationController implements ICommonConstants{
	@Autowired
	private IPatientRegistrationService iPatientRegistrationService;
	
	@RequestMapping(value="patient/{patientId}",method=RequestMethod.GET)
	public Response saveOrganizationServiceMaster(@PathVariable("patientId") Integer patientId) {
		try {
			return iPatientRegistrationService.getPatientDetailsById(patientId);
		}catch (Exception e) {
			e.printStackTrace();
			return new Response(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
		}
	}
	
	
 /*  1- Normal Registrations.
	 2- OTC Registrations.
	 3- OTC to Normal Conversion.
	 4- Temp to Normal Conversion.
	 5- Pre-Registration. 
	 6- Pre  to Normal Conversion*/
	
	@RequestMapping(value="/patientRegistration", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public synchronized @ResponseBody Response savePatientRegistration(@RequestBody PatientRegistrationDto patientRegistrationDto) {
		try {
			return iPatientRegistrationService.savePatientRegistration(patientRegistrationDto);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR, COMMON_ERROR_MESSAGE, null, null);
	}
	
	@RequestMapping(value="/convertPatient", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public synchronized @ResponseBody Response convertPatient(@RequestBody PatientRegistrationDto patientRegistrationDto) {
		try {
			return iPatientRegistrationService.convertPatient(patientRegistrationDto);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR, COMMON_ERROR_MESSAGE, null, null);
	}
	
	@RequestMapping(value="/updatePatient", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public synchronized @ResponseBody Response updatepatientRegistration(@RequestBody PatientRegistrationDto patientRegistrationDto) {
		try {
			return iPatientRegistrationService.updatepatientRegistration(patientRegistrationDto);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR, COMMON_ERROR_MESSAGE, null, null);
	}
	
	@RequestMapping(value="/getPatientDetails",method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getPatientDetais(@RequestBody PatientRegistrationDto patientRegistrationDto) {
		try {
			return iPatientRegistrationService.getPatientDetais(patientRegistrationDto);
		}catch (Exception e) {
			e.printStackTrace();
			return new Response(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
		}
	}
	
	@RequestMapping(value="/patientSearch",method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response patientSearch(@RequestBody PatientRegistrationDto patientRegistrationDto) {
		try {
			return iPatientRegistrationService.patientSearch(patientRegistrationDto);
		}catch (Exception e) {
			e.printStackTrace();
			return new Response(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
		}
	}
	
	@RequestMapping(value = "/patient/billing/{patientId}" , method = RequestMethod.GET)
	public @ResponseBody Response patientDetailsByIdForBilling(@PathVariable("patientId") int patientId){
		try{
			return iPatientRegistrationService.patientDetailsByIdForBilling(patientId);
		}catch(Exception e){
			e.printStackTrace();
			return new Response<>(ERROR, ERROR_CODE, COMMON_ERROR_MESSAGE, null, null,e.getMessage());
			
		}
	}
}
