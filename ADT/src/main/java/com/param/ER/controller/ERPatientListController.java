package com.param.ER.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.param.ER.service.IERPatientListService;
import com.param.adt.common.ICommonConstants;
import com.param.adt.common.Response;
import com.param.global.dto.AdmissionDto;
import com.param.global.dto.UnknownPatientRegistrationDto;

@Controller
@RequestMapping("/adt")
@SuppressWarnings({"rawtypes","unchecked"})
public class ERPatientListController implements ICommonConstants
{

		@Autowired
		IERPatientListService ierPatientListService;
		
		@RequestMapping(value = "/getERAdmissionList", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
		public @ResponseBody Response getERAdmissionList(@RequestBody AdmissionDto erAdmissionDto) {
			try {
				return ierPatientListService.getERAdmissionList(erAdmissionDto);

			} catch (Exception e) {
				e.printStackTrace();
			}
			return new Response(ERROR, null, null, null, null);
		}
		
		@RequestMapping(value = "/getERAdmissionCount", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
		public @ResponseBody Response getERAdmissionCount(@RequestBody AdmissionDto erAdmissionDto) {
			try {
				return ierPatientListService.getERAdmissionCount(erAdmissionDto);

			} catch (Exception e) {
				e.printStackTrace();
			}
			return new Response(ERROR, null, null, null, null); 
		}
		
		@RequestMapping(value = "/getERPatient", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
		public @ResponseBody Response getERPatient(@RequestBody UnknownPatientRegistrationDto unknownPatientRegistrationDto) {
			try {
				return ierPatientListService.getERPatient(unknownPatientRegistrationDto);

			} catch (Exception e) {
				e.printStackTrace();
			}
			return new Response(ERROR, null, null, null, null); 
		}
		
		@RequestMapping(value = "/searchERPatient", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
		public @ResponseBody Response searchERPatient(@RequestBody AdmissionDto admissionDto) {
			try {
				return ierPatientListService.searchERPatient(admissionDto);

			} catch (Exception e) {
				e.printStackTrace();
			}
			return new Response(ERROR, null, null, null, null); 
		}
		
		@RequestMapping(value = "/getDataForMapOfBed", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
		public @ResponseBody Response getDataForMapOfBed(@RequestBody AdmissionDto erAdmissionDto) {
			try {
				return ierPatientListService.getDataForMapOfBed(erAdmissionDto);

			} catch (Exception e) {
				e.printStackTrace();
			}
			return new Response(ERROR, null, null, null, null); 
		}
		
		@RequestMapping(value = "/searchPatientForMapOfBed", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
		public @ResponseBody Response searchPatientForMapOfBed(@RequestBody AdmissionDto erAdmissionDto) {
			try {
				return ierPatientListService.searchPatientForMapOfBed(erAdmissionDto);

			} catch (Exception e) {
				e.printStackTrace();
			}
			return new Response(ERROR, null, null, null, null); 
		}
}
