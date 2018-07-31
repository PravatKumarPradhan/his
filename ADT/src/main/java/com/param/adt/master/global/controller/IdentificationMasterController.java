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
import com.param.adt.master.global.dto.IdentificationMasterDto;
import com.param.adt.master.global.service.IIdentificationMasterService;

@Controller
@RequestMapping("/adt")
@SuppressWarnings({"rawtypes","unchecked"})
public class IdentificationMasterController implements ICommonConstants
{
	@Autowired
	private IIdentificationMasterService iIdentificationMasterService;
	
	@RequestMapping(value="/saveIdentificationMaster" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response saveIdentificationMaster(@RequestBody IdentificationMasterDto identificationMasterDto){
		try{
			
			return iIdentificationMasterService.addIdentificationMaster(identificationMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	
	@RequestMapping(value="/getIdentificationMasterList", method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getIdentificationMasterList(@RequestBody IdentificationMasterDto identificationMasterDto){
		try{
			return iIdentificationMasterService.getIdentificationMasterList(identificationMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	
	 
	@RequestMapping(value="/updateIdentificationMaster" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response updateIdentificationMaster(@RequestBody IdentificationMasterDto identificationMasterDto){
		try{
			return iIdentificationMasterService.updateIdentificationMaster(identificationMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	
	@RequestMapping(value="/getIdentificationById" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getIdentificationById(@RequestBody IdentificationMasterDto identificationMasterDto){
		try{
			
			return iIdentificationMasterService.getIdentificationById(identificationMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	
	@RequestMapping(value="/updateStatusForIdentification" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response updateStatusForIdentification(@RequestBody IdentificationMasterDto identificationMasterDto){
		try{
			
			return iIdentificationMasterService.updateStatusForIdentification(identificationMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	
	@RequestMapping(value="/getActiveIdentificationList", method = RequestMethod.GET)
	public @ResponseBody Response getActiveIdentificationList(){
		try{
			return iIdentificationMasterService.getActiveIdentificationList();
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	
	@RequestMapping(value="/getIdentificationCount" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getIdentificationCount(@RequestBody IdentificationMasterDto identificationMasterDto){
		try{
			
			return iIdentificationMasterService.getIdentificationCount(identificationMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}

}
