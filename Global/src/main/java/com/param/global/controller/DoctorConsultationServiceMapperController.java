package com.param.global.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;
import com.param.global.dto.DoctorConsultationServiceMapperDto;
import com.param.global.service.IDoctorConsultationServiceMapperService;

/**
 * 
 * @author Kaustubh
 * Controller for DoctorConsultationServiceMapper
 */
@RestController
@RequestMapping(value="api/billing/unit")
@SuppressWarnings({"rawtypes","unchecked"})
public class DoctorConsultationServiceMapperController implements ICommonConstants {

	@Autowired
	IDoctorConsultationServiceMapperService iDoctorConsultationServiceMapperService;
	
	@RequestMapping(value="/doctorConsultationServiceMapper" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response saveDoctorConsultationServiceMapperService(@RequestBody DoctorConsultationServiceMapperDto doctorConsultationServiceMapperDto){
		try{
			return iDoctorConsultationServiceMapperService.saveDoctorConsultationServiceMapperService(doctorConsultationServiceMapperDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	
	@RequestMapping(value="/getDoctorConsultationServiceMapperList" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getDoctorConsultationServiceMapperList(@RequestBody DoctorConsultationServiceMapperDto doctorConsultationServiceMapperDto){
		try{
			return iDoctorConsultationServiceMapperService.getDoctorConsultationServiceMapperList(doctorConsultationServiceMapperDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	
	@RequestMapping(value="/updateDoctorConsultationServiceMapper" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response updateDoctorConsultationServiceMapper(@RequestBody DoctorConsultationServiceMapperDto doctorConsultationServiceMapperDto){
		try{
			return iDoctorConsultationServiceMapperService.updateDoctorConsultationServiceMapper(doctorConsultationServiceMapperDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	
	@RequestMapping(value="/getDoctorConsultationServiceMapperById" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getDoctorConsultationServiceMapperById(@RequestBody DoctorConsultationServiceMapperDto doctorConsultationServiceMapperDto){
		try{
			return iDoctorConsultationServiceMapperService.getDoctorConsultationServiceMapperById(doctorConsultationServiceMapperDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	
	@RequestMapping(value="/getDoctorConsultationServiceBySpecialityAndDoctorId" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getDoctorConsultationServiceBySpecialityAndDoctorId(@RequestBody DoctorConsultationServiceMapperDto doctorConsultationServiceMapperDto){
		try{
			return iDoctorConsultationServiceMapperService.getDoctorConsultationServiceBySpecialityAndDoctorId(doctorConsultationServiceMapperDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	
}
