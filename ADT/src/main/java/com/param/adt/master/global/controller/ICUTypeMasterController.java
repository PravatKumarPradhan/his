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
import com.param.adt.master.global.dto.ICUTypeMasterDto;
import com.param.adt.master.global.service.IICUTypeService;


@Controller
@SuppressWarnings({"rawtypes","unchecked"})
@RequestMapping("/adt")
public class ICUTypeMasterController implements ICommonConstants
{
	@Autowired
    private IICUTypeService iicuTypeService;
	
	
	@RequestMapping(value="/saveICUTypeMaster" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response saveICUTypeMaster(@RequestBody ICUTypeMasterDto icuTypeMasterDto){
		try{
			
			return iicuTypeService.addICUTypeMaster(icuTypeMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	
	@RequestMapping(value="/getICUTypeList",  method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getSpecialityList(@RequestBody ICUTypeMasterDto icuTypeMasterDto){
		try{
			return iicuTypeService.getICUTypeMasterList(icuTypeMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	
	
	@RequestMapping(value="/updateICUtypeMaster" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response updateSpecialityMaster(@RequestBody ICUTypeMasterDto icuTypeMasterDto){
		try{
			return iicuTypeService.updateICUTypeMaster(icuTypeMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	
	@RequestMapping(value="/getICUById" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getSpecialityById(@RequestBody ICUTypeMasterDto icuTypeMasterDto){
		try{
			
			return iicuTypeService.getSpecialityById(icuTypeMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	
	@RequestMapping(value="/updateStatusForICU" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response updateStatusForICU(@RequestBody ICUTypeMasterDto icuTypeMasterDto){
		try{
			
			return iicuTypeService.updateStatusForICU(icuTypeMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	
	@RequestMapping(value="/getICUCount", method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getICUCount(@RequestBody ICUTypeMasterDto icuTypeMasterDto){
		try{
			return iicuTypeService.getICUCount(icuTypeMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
}
