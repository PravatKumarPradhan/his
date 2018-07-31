package com.param.adt.admission.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.param.adt.admission.service.IAdmittedPatientListService;
import com.param.adt.common.ICommonConstants;
import com.param.adt.common.Response;
import com.param.adt.master.unit.dto.WardMasterDto;
import com.param.global.dto.AdmissionDto;

@Controller
@RequestMapping("/adt")
@SuppressWarnings({"rawtypes","unchecked"})
public class AdmittedPatientListController implements ICommonConstants
{

	@Autowired
	IAdmittedPatientListService iAdmittedPatientListService;
	
	
	@RequestMapping(value="/getAdmittedPatientList" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getAdmittedPatientList(@RequestBody AdmissionDto admissionDto){
		try{			
			return iAdmittedPatientListService.getAdmittedPatientList(admissionDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null,null);
	}
	
	@RequestMapping(value="/getActiveWardListByFloorId" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getActiveWardListByFloorId(@RequestBody WardMasterDto wardMasterDto){
		try{			
			return iAdmittedPatientListService.getActiveWardListByFloorId(wardMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null,null);
	}
	
	@RequestMapping(value="/getKinDetailsByAdmissionId" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getKinDetailsByAdmissionId(@RequestBody AdmissionDto admissionDto){
		try{			
			return iAdmittedPatientListService.getKinDetailsByAdmissionId(admissionDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null,null);
	}
	
	@RequestMapping(value="/getAdmittedPatientByFloorWard" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getAdmittedPatientByFloorWard(@RequestBody AdmissionDto admissionDto){
		try{			
			
			return iAdmittedPatientListService.getAdmittedPatientByFloorWard(admissionDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null,null);
	}
	
	@RequestMapping(value="/serachPatientForMapOfBed" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response serachPatientForMapOfBed(@RequestBody AdmissionDto admissionDto){
		try{			
			
			return iAdmittedPatientListService.serachPatientForMapOfBed(admissionDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null,null);
	}
	
	
}
