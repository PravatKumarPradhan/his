
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
import com.param.adt.master.global.dto.DischargeTypeMasterDto;
import com.param.adt.master.global.service.IDischargeTypeMasterService;

@Controller
@RequestMapping("/adt")
@SuppressWarnings({"rawtypes","unchecked"})
public class DischargeTypeMasterController implements ICommonConstants{

	@Autowired
	IDischargeTypeMasterService iDischargeTypeMaster;
	
	@RequestMapping(value="/saveDischargeTypeMaster" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response saveDischargeTypeMaster(@RequestBody DischargeTypeMasterDto dischargeTypeMasterDto){
		try{
			
			return iDischargeTypeMaster.addDischargeTypeMaster(dischargeTypeMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	
	@RequestMapping(value="/getDischargeTypeMasterList", method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getDischargeTypeMasterList(@RequestBody DischargeTypeMasterDto dischargeTypeMasterDto){
		try{
			return iDischargeTypeMaster.getDischargeTypeMasterList(dischargeTypeMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	
	 
	@RequestMapping(value="/updateDischargeTypeMaster" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response updateDischargeTypeMaster(@RequestBody DischargeTypeMasterDto DischargeTypeMasterDto){
		try{
			return iDischargeTypeMaster.updateDischargeTypeMaster(DischargeTypeMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	
	@RequestMapping(value="/getDischargeTypeById" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getDischargeTypeById(@RequestBody DischargeTypeMasterDto DischargeTypeMasterDto){
		try{
			
			return iDischargeTypeMaster.getDischargeTypeById(DischargeTypeMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	
	@RequestMapping(value="/updateStatusForDischargeType" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response updateStatusForDischargeType(@RequestBody DischargeTypeMasterDto DischargeTypeMasterDto){
		try{
			
			return iDischargeTypeMaster.updateStatusForDischargeType(DischargeTypeMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	
	@RequestMapping(value="/getActiveDischargeTypeList", method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getActiveCityList(@RequestBody DischargeTypeMasterDto dischargeTypeMasterDto){
		try{
			return iDischargeTypeMaster.getActiveDischargeTypeList(dischargeTypeMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	
	@RequestMapping(value="/getDischargeTypeCount" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getDischargeTypeCount(@RequestBody DischargeTypeMasterDto DischargeTypeMasterDto){
		try{
			
			return iDischargeTypeMaster.getDischargeTypeCount(DischargeTypeMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
}
