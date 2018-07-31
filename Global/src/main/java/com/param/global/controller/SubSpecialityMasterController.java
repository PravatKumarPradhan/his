package com.param.global.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;
import com.param.global.dto.SpecialityMasterDto;
import com.param.global.dto.SubSpecialityMasterDto;
import com.param.global.service.ISpecialityService;
import com.param.global.service.ISubSpecialityService;
@Controller
@SuppressWarnings({"unchecked","rawtypes"})
@RequestMapping("/adt")
public class SubSpecialityMasterController implements ICommonConstants
{
	@Autowired
    private ISubSpecialityService iSubSpecialityService;
	
	@Autowired
    private ISpecialityService iSpecialityService;
	
	@RequestMapping(value="/saveMaster" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response saveSpecialityMaster(@RequestBody SpecialityMasterDto specialityMasterDto){
		try{
			
			return iSpecialityService.addSpecialityMaster(specialityMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	@RequestMapping(value="/saveSubSpecialtiyMaster" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response saveSubSpecialityMaster(@RequestBody SubSpecialityMasterDto subSpecialityMasterDto){
		try{
			return iSubSpecialityService.addSubSpecialityMaster(subSpecialityMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	
	
	@RequestMapping(value="/getSubSpecialityList",  method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getSpecialityList(@RequestBody SubSpecialityMasterDto subSpecialityMasterDto){
		try{
			return iSubSpecialityService.getSubSpecialityMasterList(subSpecialityMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	
	@RequestMapping(value="/getSubSpecialityById" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getSubSpecialityById(@RequestBody SubSpecialityMasterDto subSpecialityMasterDto){
		try{
			
			return iSubSpecialityService.getSubSpecialityById(subSpecialityMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	
	@RequestMapping(value="/updateStatusForSubSpeciality" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response updateStatusForSubSpeciality(@RequestBody SubSpecialityMasterDto subSpecialityMasterDto){
		try{
			
			return iSubSpecialityService.updateStatusForSubSpeciality(subSpecialityMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	
	@RequestMapping(value="/updateSubSpecialityMaster" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response updateSubSpecialityMaster(@RequestBody SubSpecialityMasterDto subspecialityMasterDto){
		try{
			
			return iSubSpecialityService.updateSubSpecialityMaster(subspecialityMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	
	@RequestMapping(value="/getSubSpecialityCount" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getSubSpecialityCount(@RequestBody SubSpecialityMasterDto subspecialityMasterDto){
		try{
			
			return iSubSpecialityService.getSubSpecialityCount(subspecialityMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	
	@RequestMapping(value = "/subspeciality/{id}" , method = RequestMethod.GET)
	public @ResponseBody Response getSubSpecialityBySpecialityId(@PathVariable("id") int id){
		try{
			SubSpecialityMasterDto masterDto = new SubSpecialityMasterDto();
			masterDto.setSpecialityId(id);
			Response res = iSubSpecialityService.getSubSpecialityBySpecialityId(masterDto);
			masterDto = null;
			return res;
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	@RequestMapping(value = "/getSubspecialityNotInUnit/{id}" , method = RequestMethod.GET)
	public @ResponseBody Response getSubspecialityNotInUnit(@PathVariable("id") int id){
		try{
			
			Response res = iSubSpecialityService.getSubspecialityNotInUnit(id);
			return res;
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	
	
	/*@RequestMapping(value="/getSubSpecialityBySpecialityId" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getSubSpecialityBySpecialityId(@RequestBody SubSpecialityMasterDto subspecialityMasterDto){
		try{
			return iSubSpecialityService.getSubSpecialityBySpecialityId(subspecialityMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}*/
}
