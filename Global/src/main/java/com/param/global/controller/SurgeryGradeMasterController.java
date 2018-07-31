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
import com.param.global.dto.SurgeryGradeMasterDto;
import com.param.global.service.ISurgeryGradeMasterService;

/*FUNCTIONALITY : Surgery Grade MASTER
DATE:27/04/2018
CREATED BY: Vivek */
@RestController
@RequestMapping("api/global")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class SurgeryGradeMasterController implements ICommonConstants {
	
	@Autowired
	ISurgeryGradeMasterService iSurgeryGradeMasterService;
	
	@RequestMapping(value="/saveSurgeryGradeMaster" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response saveSurgeryGradeMaster(@RequestBody SurgeryGradeMasterDto surgeryGradeMasterDto){
		try{
			
			return iSurgeryGradeMasterService.addSurgeryGradeMaster(surgeryGradeMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	
	@RequestMapping(value="/getSurgeryGradeMasterList", method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getSurgeryGradeMasterList(@RequestBody SurgeryGradeMasterDto surgeryGradeMasterDto){
		try{
			return iSurgeryGradeMasterService.getSurgeryGradeMasterList(surgeryGradeMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	
	@RequestMapping(value="/updateSurgeryGradeMaster" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response updateSurgeryGradeMaster(@RequestBody SurgeryGradeMasterDto surgeryGradeMasterDto){
		try{
			return iSurgeryGradeMasterService.updateSurgeryGradeMaster(surgeryGradeMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	
	@RequestMapping(value="/getSurgeryGradeById" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getSurgeryGradeById(@RequestBody SurgeryGradeMasterDto surgeryGradeMasterDto){
		try{
			
			return iSurgeryGradeMasterService.getSurgeryGradeById(surgeryGradeMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	
	@RequestMapping(value="/updateStatusForSurgeryGrade" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response updateStatusForSurgeryGrade(@RequestBody SurgeryGradeMasterDto surgeryGradeMasterDto){
		try{
			
			return iSurgeryGradeMasterService.updateStatusForSurgeryGrade(surgeryGradeMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	
	@RequestMapping(value="/getActiveSurgeryGradeList", method = RequestMethod.GET)
	public @ResponseBody Response getActiveSurgeryGradeList(){
		try{
			return iSurgeryGradeMasterService.getActiveSurgeryGradeList();
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	

}
