package com.param.adt.master.unit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.param.adt.common.ICommonConstants;
import com.param.adt.common.Response;
import com.param.adt.master.unit.dto.UnitSpecialtyMapperDto;
import com.param.adt.master.unit.dto.UnitSubSpecialityMapperDto;
import com.param.adt.master.unit.service.IUnitSubSpecialityService;

@Controller
@RequestMapping("/adt")
@SuppressWarnings({"rawtypes","unchecked"})
public class UnitSubSpecialityController implements ICommonConstants
{

	@Autowired
	IUnitSubSpecialityService iUnitSubSpecialityService;
	
	@RequestMapping(value="/saveUnitSubSpecialtiy" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response saveUnitSubSpecialtiy(@RequestBody UnitSubSpecialityMapperDto unitSubSpecialityMapperDto){
		try{
			
			return iUnitSubSpecialityService.saveUnitSubSpecialtiy(unitSubSpecialityMapperDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	
	
	@RequestMapping(value="/getUnitSubSpecialityList", method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getUnitSubSpecialityList(@RequestBody UnitSubSpecialityMapperDto unitSubSpecialityMapperDto){
		try{
			return iUnitSubSpecialityService.getUnitSubSpecialityList(unitSubSpecialityMapperDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	
	@RequestMapping(value="/getUnitSubSpecialityCount", method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getUnitSubSpecialityCount(@RequestBody UnitSubSpecialityMapperDto unitSubSpecialityMapperDto){
		try{
			return iUnitSubSpecialityService.getUnitSubSpecialityCount(unitSubSpecialityMapperDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	
	@RequestMapping(value="/updateStatusForUnitSubSpeciality", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response updateStatusForUnitSubSpeciality(@RequestBody UnitSubSpecialityMapperDto unitSubSpecialityMapperDto){
		try{
			
			return iUnitSubSpecialityService.updateStatusForUnitSubSpeciality(unitSubSpecialityMapperDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	
	@RequestMapping(value="/getSubSpecialityBySpecialityId", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getSubSpecialityBySpecialityId(@RequestBody UnitSubSpecialityMapperDto unitSubSpecialityMapperDto){
		try{
			
			return iUnitSubSpecialityService.getSubSpecialityBySpecialityId(unitSubSpecialityMapperDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	
	@RequestMapping(value="/getSubSpecialityBySpecialityArray", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getSubSpecialityBySpecialityArray(@RequestBody UnitSubSpecialityMapperDto unitSubSpecialityMapperDto){
		try{
			
			return iUnitSubSpecialityService.getSubSpecialityBySpecialityArray(unitSubSpecialityMapperDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	
	@RequestMapping(value="/getActiveUnitSubSpecialityList", method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getActiveUnitSubSpecialityList(@RequestBody UnitSubSpecialityMapperDto unitSpecialtyMapperDto){
		try{
			return iUnitSubSpecialityService.getActiveUnitSubSpecialityList(unitSpecialtyMapperDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	
	@RequestMapping(value="/getSubSpecialityBySpecialityArrayForTariff", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getSubSpecialityBySpecialityArrayForTariff(@RequestBody UnitSubSpecialityMapperDto unitSubSpecialityMapperDto){
		try{
			
			return iUnitSubSpecialityService.getSubSpecialityBySpecialityArrayForTariff(unitSubSpecialityMapperDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	
}
