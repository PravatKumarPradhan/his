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
import com.param.global.dto.SpecialityMasterDto;
import com.param.global.service.ISpecialityService;

@Controller
@SuppressWarnings({"rawtypes","unchecked"})
@RequestMapping("/adt")
public class SpecialityMasterController implements ICommonConstants
{ 
	
	@Autowired
    private ISpecialityService iSpecialityService;
	
	
	@RequestMapping(value="/saveSpecialtiyMaster" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response saveSpecialityMaster(@RequestBody SpecialityMasterDto specialityMasterDto){
		try{
			
			return iSpecialityService.addSpecialityMaster(specialityMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	
	
	@RequestMapping(value="/getSpecialityList", method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getSpecialityList(@RequestBody SpecialityMasterDto specialityMasterDto){
		try{
			return iSpecialityService.getSpecialityMasterList(specialityMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	
	
	@RequestMapping(value="/getSpecialityListForSubSpeciality", method = RequestMethod.GET)
	public @ResponseBody Response getSpecialityListForSubSpeciality(){
		try{
			return iSpecialityService.getSpecialityListForSubSpeciality();
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	
	
	@RequestMapping(value="/updateSpecialityMaster" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response updateSpecialityMaster(@RequestBody SpecialityMasterDto specialityMasterDto){
		try{
			return iSpecialityService.updateSpecialityMaster(specialityMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	
	@RequestMapping(value="/getSpecialityById" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getSpecialityById(@RequestBody SpecialityMasterDto specialityMasterDto){
		try{
			
			return iSpecialityService.getSpecialityById(specialityMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	
	@RequestMapping(value="/updateStatus" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response updateStatus(@RequestBody SpecialityMasterDto specialityMasterDto){
		try{
			
			return iSpecialityService.updateStatus(specialityMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	
	@RequestMapping(value="/getActiveSpecialityList", method = RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getActiveSpecialityList(@RequestBody SpecialityMasterDto specialityMasterDto){
		try{
			return iSpecialityService.getActiveSpecialityMasterList(specialityMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	
	@RequestMapping(value="/getSpecialityCount" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getSpecialityCount(@RequestBody SpecialityMasterDto specialityMasterDto){
		try{
			return iSpecialityService.getSpecialityCount(specialityMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	@RequestMapping(value="/getOrgActiveSpecialityList", method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getOrgActiveSpecialityList(@RequestBody SpecialityMasterDto specialityMasterDto){
		try{
			return iSpecialityService.getOrgActiveSpecialityList(specialityMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}

	
}
