package com.param.global.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;
import com.param.global.dto.OPConsultationConfigurationMasterDto;
import com.param.global.service.IOPConsultationConfigurationMasterService;

@Controller
@RequestMapping("api/opd/unit")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class OPConsultationConfigurationMasterController implements ICommonConstants {

	@Autowired
	IOPConsultationConfigurationMasterService iopConsultationConfigurationMasterService;
	
	
	@RequestMapping(value="/OPConsultationConfiguration" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response OPConsultationConfiguration(@RequestBody OPConsultationConfigurationMasterDto opConsultationConfigurationMasterDto){
		try{
			return iopConsultationConfigurationMasterService.OPConsultationConfiguration(opConsultationConfigurationMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	
	@RequestMapping(value="/getOPConsultationConfigurationList" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getOPConsultationConfigurationList(@RequestBody OPConsultationConfigurationMasterDto opConsultationConfigurationMasterDto){
		try{
			return iopConsultationConfigurationMasterService.getOPConsultationConfigurationList(opConsultationConfigurationMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	
	@RequestMapping(value="/getOPConsultationConfigurationListById" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getOPConsultationConfigurationListById(@RequestBody OPConsultationConfigurationMasterDto opConsultationConfigurationMasterDto){
		try{
			return iopConsultationConfigurationMasterService.getOPConsultationConfigurationListById(opConsultationConfigurationMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	
	@RequestMapping(value="/updateOPConsultationConfigurationMaster" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response updateOPConsultationConfigurationMaster(@RequestBody OPConsultationConfigurationMasterDto opConsultationConfigurationMasterDto){
		try{
			return iopConsultationConfigurationMasterService.updateOPConsultationConfigurationMaster(opConsultationConfigurationMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	
	@RequestMapping(value="/getOPConsultationConfigurationBySpecialityId" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getOPConsultationConfigurationBySpecialityId(@RequestBody OPConsultationConfigurationMasterDto opConsultationConfigurationMasterDto){
		try{
			return iopConsultationConfigurationMasterService.getOPConsultationConfigurationBySpecialityId(opConsultationConfigurationMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	
	
	
}
