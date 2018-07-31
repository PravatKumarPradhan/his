package com.param.adt.admission.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.param.adt.admission.dto.UnreservedPatientDto;
import com.param.adt.admission.service.IBedReservationListService;
import com.param.adt.common.ICommonConstants;
import com.param.adt.common.Response;
import com.param.global.dto.AdmissionDto;

@Controller
@RequestMapping("/adt")
@SuppressWarnings({"rawtypes","unchecked"})
public class BedReservationListController implements ICommonConstants
{

	@Autowired
	IBedReservationListService iBedReservationListService;

	@RequestMapping(value="/getBedReservationList" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getBedReservationList(@RequestBody AdmissionDto admissionDto){
		try{			
			return iBedReservationListService.getBedReservationList(admissionDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null,null);
	}
	
	@RequestMapping(value="/getBedReservationCount" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getBedReservationCount(@RequestBody AdmissionDto admissionDto){
		try{			
			return iBedReservationListService.getBedReservationCount(admissionDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null,null);
	}
	
	
	@RequestMapping(value="/unreservePatientBooking" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response unreservePatrientBooking(@RequestBody UnreservedPatientDto unreservedPatientDto){
		try{			
			return iBedReservationListService.unreservePatrientBooking(unreservedPatientDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null,null);
	}
	
}
