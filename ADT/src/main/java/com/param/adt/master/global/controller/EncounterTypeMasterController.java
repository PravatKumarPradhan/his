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
import com.param.adt.master.global.dto.EncounterTypeMasterDto;
import com.param.adt.master.global.service.IEncounterTypeMasterService;

@Controller
@RequestMapping("/adt")
@SuppressWarnings({"rawtypes","unchecked"})
public class EncounterTypeMasterController implements ICommonConstants
{
	@Autowired
	private IEncounterTypeMasterService iEncounterTypeService; 
	
	
	@RequestMapping(value="/saveEncounterTypeMaster" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response saveEncounterTypeMaster(@RequestBody EncounterTypeMasterDto encounterTypeMasterDto){
		try{
			
			return iEncounterTypeService.addEncounterTypeMaster(encounterTypeMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	
	@RequestMapping(value="/getEncounterTypeMasterList", method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getEncounterTypeMasterList(@RequestBody EncounterTypeMasterDto encounterTypeMasterDto){
		try{
			return iEncounterTypeService.getEncounterTypeMasterList(encounterTypeMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	
	 
	@RequestMapping(value="/updateEncounterTypeMaster" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response updateEncounterTypeMaster(@RequestBody EncounterTypeMasterDto encounterTypeMasterDto){
		try{
			return iEncounterTypeService.updateEncounterTypeMaster(encounterTypeMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	
	@RequestMapping(value="/getEncounterTypeById" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getEncounterTypeById(@RequestBody EncounterTypeMasterDto encounterTypeMasterDto){
		try{
			
			return iEncounterTypeService.getEncounterTypeById(encounterTypeMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	
	@RequestMapping(value="/updateStatusForEncounterType" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response updateStatusForEncounterType(@RequestBody EncounterTypeMasterDto encounterTypeMasterDto){
		try{
			
			return iEncounterTypeService.updateStatusForEncounterType(encounterTypeMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	
	@RequestMapping(value="/getActiveEncounterTypeList", method = RequestMethod.GET)
	public @ResponseBody Response getActiveEncounterTypeList(){
		try{
			return iEncounterTypeService.getActiveEncounterTypeList();
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	
	@RequestMapping(value="/getEncounterTypeCount" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getEncounterCount(@RequestBody EncounterTypeMasterDto encounterTypeMasterDto){
		try{
			
			return iEncounterTypeService.getEncounterCount(encounterTypeMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	

}
