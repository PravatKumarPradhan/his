package com.param.adt.admission.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.param.adt.admission.dto.BedAvailibailityDto;
import com.param.adt.admission.service.IBedAvailibilityService;
import com.param.adt.common.ICommonConstants;
import com.param.adt.common.Response;
@Controller
@RequestMapping("/adt")
@SuppressWarnings({"rawtypes","unchecked"})
public class BedAvailibilityController implements ICommonConstants
{

	@Autowired
	IBedAvailibilityService iBedAvailibilityService;
	
	@RequestMapping(value="/bedAvailiblityList" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response bedAvailiblityList(@RequestBody BedAvailibailityDto bedAvailibailityDto ){
		try{		
			return iBedAvailibilityService.bedAvailiblityList(bedAvailibailityDto);
		
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null,null);
	}
}
