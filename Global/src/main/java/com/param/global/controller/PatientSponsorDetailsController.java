package com.param.global.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;
import com.param.global.dto.PatientSponsorDetailsDto;
import com.param.global.service.IPatientSponsorDetailsService;

@Controller
@RequestMapping("api/opd")
@SuppressWarnings("rawtypes")
public class PatientSponsorDetailsController implements ICommonConstants{
	
	@Autowired
	IPatientSponsorDetailsService iPatientSponsorDetailsService;
	
	@RequestMapping(value="savePatientSponsorDetails", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public Response savePatientSponsorDetails(@RequestBody PatientSponsorDetailsDto patientSponsorDetailsDto) {
		try {
			return iPatientSponsorDetailsService.savePatientSponsorDetails(patientSponsorDetailsDto);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}
	
}
