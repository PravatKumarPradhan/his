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
import com.param.adt.master.unit.dto.UnitICUTypeMapperDto;
import com.param.adt.master.unit.service.IUnitICUService;

@Controller
@RequestMapping("/adt")
@SuppressWarnings({"rawtypes","unchecked"})
public class UnitICUController implements ICommonConstants
{
	@Autowired
	IUnitICUService iUnitICUService;
	
	
	@RequestMapping(value="/saveUnitICUType" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response saveUnitICUType(@RequestBody UnitICUTypeMapperDto unitICUTypeMapperDto){
		try{
			
			return iUnitICUService.saveUnitSubSpecialtiy(unitICUTypeMapperDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	
	
	@RequestMapping(value="/getUnitICUTypeList", method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getUnitICUTypeList(@RequestBody UnitICUTypeMapperDto unitICUTypeMapperDto){
		try{
			return iUnitICUService.getUnitICUTypeList(unitICUTypeMapperDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	
	@RequestMapping(value="/getActiveUnitICUTypeList", method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getActiveUnitICUTypeList(@RequestBody UnitICUTypeMapperDto unitICUTypeMapperDto){
		try{
			return iUnitICUService.getActiveUnitICUTypeList(unitICUTypeMapperDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	
	@RequestMapping(value="/getUnitICUTypeCount", method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getUnitICUTypeCount(@RequestBody UnitICUTypeMapperDto unitICUTypeMapperDto){
		try{
			return iUnitICUService.getUnitICUTypeCount(unitICUTypeMapperDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	
	@RequestMapping(value="/updateStatusForUnitICUType", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response updateStatusForUnitICUType(@RequestBody UnitICUTypeMapperDto unitICUTypeMapperDto){
		try{
			
			return iUnitICUService.updateStatusForUnitICUType(unitICUTypeMapperDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
}
