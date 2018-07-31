package com.param.ER.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.param.ER.dto.ERBedTypeAllocationDto;
import com.param.ER.service.IERBedTypeAllocationService;
import com.param.adt.common.ICommonConstants;
import com.param.adt.common.Response;
import com.param.global.dto.AdmissionDto;
import com.param.global.dto.DoctorMasterDto;
import com.param.global.dto.UnknownPatientRegistrationDto;

@Controller
@RequestMapping("/adt")
@SuppressWarnings({"rawtypes","unchecked"})
public class ERBedTypeAllocationController implements ICommonConstants
{
	@Autowired
	IERBedTypeAllocationService iBedTypeAllocationService;
	
	
	
	
	@RequestMapping(value = "/getUnknownPatientsList", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getUnknownPatientsList(@RequestBody UnknownPatientRegistrationDto unknownPatientRegistrationDto) {
		try {
			return iBedTypeAllocationService.getUnknownPatientsList(unknownPatientRegistrationDto);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}
	
	@RequestMapping(value = "/getERBedTypeAllocationList", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getERBedTypeAllocationList(@RequestBody ERBedTypeAllocationDto erBedTypeAllocationDto) {
		try {
			return iBedTypeAllocationService.getERBedTypeAllocationList(erBedTypeAllocationDto);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}
	
	
	@RequestMapping(value = "/getERBedTypeAllocationCount", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getERBedTypeAllocationCount(@RequestBody ERBedTypeAllocationDto erBedTypeAllocationDto) {
		try {
			return iBedTypeAllocationService.getERBedTypeAllocationCount(erBedTypeAllocationDto);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}
	
	@RequestMapping(value = "/getERBedList", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getERBedList(@RequestBody ERBedTypeAllocationDto erBedTypeAllocationDto) {
		try {
			return iBedTypeAllocationService.getERBedList(erBedTypeAllocationDto);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}
	
	@RequestMapping(value = "/updateERPatientAdmission", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response updateERPatientAdmisson(@RequestBody AdmissionDto admissionDto) {
		try{
			Response res= iBedTypeAllocationService.updateERPatientAdmisson(admissionDto);
			//admissionDto.setAdmissionId(adm.getAdmissionId());
			admissionDto.setActiveStatus('B');	//B----> Booked
			admissionDto.setBedStatusId(5); // 5----> Booked
		
			if(res.getStatus().equals(SUCCESS))
			{
				Response res3= iBedTypeAllocationService.updateERsavePatientDetails(admissionDto);
				if(res3.getStatus().equals(SUCCESS)&&admissionDto.getErBedTypeAllocation()==1)
				{
					return iBedTypeAllocationService.saveTransfer(admissionDto);
				}
				{
					return new Response(SUCCESS, null, "No entry in transfer", null, null);
				}
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	
	@RequestMapping(value = "/getActiveDoctorList", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getActiveDoctorList(@RequestBody DoctorMasterDto doctorMasterDto) {
		try {
			return iBedTypeAllocationService.getActiveDoctorList(doctorMasterDto);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}
	
}
