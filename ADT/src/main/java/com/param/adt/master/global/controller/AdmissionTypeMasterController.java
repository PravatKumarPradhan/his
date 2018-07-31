package com.param.adt.master.global.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.param.adt.common.ICommonConstants;
import com.param.adt.common.Response;
import com.param.adt.master.global.dto.AdmissionTypeMasterDto;
import com.param.adt.master.global.service.IAdmissionTypeMasterService;


@Controller
@RequestMapping("/adt")
@SuppressWarnings({"rawtypes","unchecked"})
public class AdmissionTypeMasterController  implements ICommonConstants{
	
	@Autowired
	IAdmissionTypeMasterService iAdmissionTypeMasterService;
	
	@RequestMapping(value="/saveAdmissionTypeMaster" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response saveAdmissionTypeMaster(@RequestBody AdmissionTypeMasterDto admissionTypeMasterDto){
		try{
			
			return iAdmissionTypeMasterService.addAdmissionTypeMaster(admissionTypeMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	
	@RequestMapping(value="/getAdmissionTypeMasterList", method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getAdmissionTypeMasterList(@RequestBody AdmissionTypeMasterDto admissionTypeMasterDto){
		try{
			return iAdmissionTypeMasterService.getAdmissionTypeMasterList(admissionTypeMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	
	 
	@RequestMapping(value="/updateAdmissionTypeMaster" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response updateAdmissionTypeMaster(@RequestBody AdmissionTypeMasterDto admissionTypeMasterDto){
		try{
			return iAdmissionTypeMasterService.updateAdmissionTypeMaster(admissionTypeMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	
	@RequestMapping(value="/getAdmissionTypeById" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getAdmissionTypeById(@RequestBody AdmissionTypeMasterDto admissionTypeMasterDto){
		try{
			
			return iAdmissionTypeMasterService.getAdmissionTypeById(admissionTypeMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	
	@RequestMapping(value="/updateStatusForAdmissionType" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response updateStatusForAdmissionType(@RequestBody AdmissionTypeMasterDto admissionTypeMasterDto){
		try{
			
			return iAdmissionTypeMasterService.updateStatusForAdmissionType(admissionTypeMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	
	@RequestMapping(value="/getActiveAdmissionTypeList", method = RequestMethod.GET)
	public @ResponseBody Response getActiveAdmissionTypeList(){
		try{
			return iAdmissionTypeMasterService.getActiveAdmissionTypeList();
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	
	@RequestMapping(value="/getAdmissionTypeCount", method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getAdmissionTypeCount(@RequestBody AdmissionTypeMasterDto admissionTypeMasterDto){
		try{
			return iAdmissionTypeMasterService.getAdmissionTypeCount(admissionTypeMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}

}
