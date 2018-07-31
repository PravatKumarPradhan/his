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
import com.param.adt.master.global.dto.BedTypeMasterDto;
import com.param.adt.master.global.service.IBedTypeMasterService;


@Controller
@RequestMapping("/adt")
@SuppressWarnings({"rawtypes","unchecked"})
public class BedTypeMasterController  implements ICommonConstants{

	@Autowired
	IBedTypeMasterService iBedTypeMasterService;
	
	@RequestMapping(value="/saveBedTypeMaster" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response saveBedTypeMaster(@RequestBody BedTypeMasterDto bedTypeMasterDto){
		try{
			
			return iBedTypeMasterService.addBedTypeMaster(bedTypeMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	
	@RequestMapping(value="/getBedTypeMasterList", method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getBedTypeMasterList(@RequestBody BedTypeMasterDto bedTypeMasterDto){
		try{
			return iBedTypeMasterService.getBedTypeMasterList(bedTypeMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	
	 
	@RequestMapping(value="/updateBedTypeMaster" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response updateBedTypeMaster(@RequestBody BedTypeMasterDto bedTypeMasterDto){
		try{
			return iBedTypeMasterService.updateBedTypeMaster(bedTypeMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	
	@RequestMapping(value="/getBedTypeById" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getBedTypeById(@RequestBody BedTypeMasterDto bedTypeMasterDto){
		try{
			
			return iBedTypeMasterService.getBedTypeById(bedTypeMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	
	@RequestMapping(value="/updateStatusForBedType" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response updateStatusForBedType(@RequestBody BedTypeMasterDto bedTypeMasterDto){
		try{
			
			return iBedTypeMasterService.updateStatusForBedType(bedTypeMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	
	@RequestMapping(value="/getActiveBedTypeList", method = RequestMethod.GET)
	public @ResponseBody Response getActiveBedTypeList(){
		try{
			return iBedTypeMasterService.getActiveBedTypeList();
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	
	@RequestMapping(value="/getBedTypeCount" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getBedTypeCount(@RequestBody BedTypeMasterDto bedTypeMasterDto){
		try{
			
			return iBedTypeMasterService.getBedTypeCount(bedTypeMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
}
