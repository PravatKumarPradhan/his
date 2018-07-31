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
import com.param.global.dto.StateMasterDto;
import com.param.global.service.IStateMasterService;

@Controller
@RequestMapping("/adt")
@SuppressWarnings({"rawtypes","unchecked"})
public class StateMasterController implements ICommonConstants
{
	@Autowired
	private IStateMasterService iStateMaster;
	
	@RequestMapping(value="/saveStateMaster" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response saveStateMaster(@RequestBody StateMasterDto stateMasterDto){
		try{
			
			return iStateMaster.addStateMaster(stateMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	
	@RequestMapping(value="/getStateMasterList", method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getStateMasterList(@RequestBody StateMasterDto stateMasterDto){
		try{
			return iStateMaster.getStateMasterList(stateMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	
	 
	@RequestMapping(value="/updateStateMaster" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response updateStateMaster(@RequestBody StateMasterDto stateMasterDto){
		try{
			return iStateMaster.updateStateMaster(stateMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	
	@RequestMapping(value="/getStateById" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getStateById(@RequestBody StateMasterDto stateMasterDto){
		try{
			
			return iStateMaster.getStateById(stateMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	
	@RequestMapping(value="/updateStatusForState" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response updateStatusForState(@RequestBody StateMasterDto stateMasterDto){
		try{
			
			return iStateMaster.updateStatusForState(stateMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	
	@RequestMapping(value="/getActiveStateList", method = RequestMethod.GET)
	public @ResponseBody Response getActiveStateList(){
		try{
			return iStateMaster.getActiveStateList();
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	
	@RequestMapping(value="/getStateCount", method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getStateCount(@RequestBody StateMasterDto stateMasterDto){
		try{
			return iStateMaster.getStateCount(stateMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	
	
	@RequestMapping(value="/getStateByCountryId/{countryId}" , method = RequestMethod.GET)
	public @ResponseBody Response getStateByCountryId(@PathVariable("countryId") int countryId){
		try{
			StateMasterDto stateMasterDto = new StateMasterDto();
			stateMasterDto.setCountryId(countryId);
			return iStateMaster.getStateByCountryId(stateMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	
}
