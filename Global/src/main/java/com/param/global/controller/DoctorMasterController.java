package com.param.global.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;
import com.param.global.dto.DoctorMasterDto;
import com.param.global.service.IDoctorMasterService;

/*FUNCTIONALITY : DOCTOR MASTER
DATE:09/04/2018
CREATED BY: JYOTI*/
@RestController
@RequestMapping("api/global")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class DoctorMasterController implements ICommonConstants{

	@Autowired
	private IDoctorMasterService iDoctorMasterService;
	
	@RequestMapping(value="/getDoctorMasterListAutoFillSerach", method=RequestMethod.GET)
	public @ResponseBody Response listOfSurgeryMasterList(@RequestHeader(value="unit_id") int unitId, @RequestHeader(value="organization_id") int organizationId, 
			@RequestParam(value="doctor_name") String doctorName) {
		try
		{
			DoctorMasterDto doctorMasterDto= new DoctorMasterDto();
			doctorMasterDto.setUnitId(unitId);
			doctorMasterDto.setOrganizationId(organizationId);
			doctorMasterDto.setDoctorName(doctorName);
			return iDoctorMasterService.getDoctorMasterList(doctorMasterDto);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}
	
	@RequestMapping(value="/doctorRegistration", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public synchronized @ResponseBody Response saveDoctorRegistration(@RequestBody DoctorMasterDto doctorMasterDto) {
		try {
			return iDoctorMasterService.saveDoctorRegistration(doctorMasterDto);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR, COMMON_ERROR_MESSAGE, null, null);
	}
	
	@RequestMapping(value="/getDoctorDetails", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getDoctorDetails(@RequestBody DoctorMasterDto doctorMasterDto) {
		try {
			return iDoctorMasterService.getDoctorDetails(doctorMasterDto);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR, COMMON_ERROR_MESSAGE, null, null);
	}
	
	@RequestMapping(value="/updateDoctorDetails", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public synchronized @ResponseBody Response updateDoctorDetails(@RequestBody DoctorMasterDto doctorMasterDto) {
		try {
			return iDoctorMasterService.updateDoctorDetails(doctorMasterDto);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR, COMMON_ERROR_MESSAGE, null, null);
	}
	
	@RequestMapping(value="/updateDoctorStatus", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response updateDoctorStatus(@RequestBody DoctorMasterDto doctorMasterDto) {
		try {
			return iDoctorMasterService.updateDoctorStatus(doctorMasterDto);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR, COMMON_ERROR_MESSAGE, null, null);
	}
	
	
	@RequestMapping(value="/searchDoctorDetailsByCriteria", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response searchDoctorDetailsByCriteria(@RequestBody DoctorMasterDto doctorMasterDto) {
		try {
			return iDoctorMasterService.searchDoctorDetailsByCriteria(doctorMasterDto);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR, COMMON_ERROR_MESSAGE, null, null);
	}
	
}
