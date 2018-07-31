package com.param.global.opd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;
import com.param.global.dto.SlotMasterDto;
import com.param.global.opd.service.IAppointmentSchedulingService;

@RestController
@RequestMapping("api/opd")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class AppointmentSchedulingController implements ICommonConstants {
	
	@Autowired
	IAppointmentSchedulingService iAppointmentSchedulingService;
	
	@RequestMapping(value="/scheduleAppointment", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response scheduleAppointment(@RequestBody SlotMasterDto slotMasterDto) {
		try {
			
			return iAppointmentSchedulingService.scheduleAppointment(slotMasterDto);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}
	
	@RequestMapping(value="/scheduleAppointmentList", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response scheduledAppointmentList(@RequestBody SlotMasterDto slotMasterDto) {
		try {
				return iAppointmentSchedulingService.scheduledAppointmentList(slotMasterDto);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}
	
	@RequestMapping(value="/blockedScheduleAppointmentList", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response blockedScheduleAppointmentList(@RequestBody SlotMasterDto slotMasterDto) {
		try {
				return iAppointmentSchedulingService.blockedScheduleAppointmentList(slotMasterDto);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}
	
	@RequestMapping(value="/searchAppointmentsByCriteria", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response searchAppointmentsByCriteria(@RequestBody SlotMasterDto slotMasterDto) {
		try {
				return iAppointmentSchedulingService.searchAppointmentsByCriteria(slotMasterDto);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}
	
	@RequestMapping(value="/cancelScheduledAppointment", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response cancelScheduledAppointment(@RequestBody SlotMasterDto slotMasterDto) {
		try {
				return iAppointmentSchedulingService.cancelScheduledAppointment(slotMasterDto);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}
	
	@RequestMapping(value="/rescheduleAppointment", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response rescheduleAppointment(@RequestBody SlotMasterDto slotMasterDto) {
		try {
				return iAppointmentSchedulingService.rescheduleAppointment(slotMasterDto);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}
	
	@RequestMapping(value="/patientAutofillSearch", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response patientAutofillSearch(@RequestBody SlotMasterDto slotMasterDto) {
		try {
				return iAppointmentSchedulingService.patientAutofillSearch(slotMasterDto);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}
	
	@RequestMapping(value="/updateAppointmentStatus", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response updateAppointmentStatus(@RequestBody SlotMasterDto slotMasterDto) {
		try {
				return iAppointmentSchedulingService.updateAppointmentStatus(slotMasterDto);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}
	
}
