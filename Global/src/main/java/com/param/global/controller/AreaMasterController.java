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
import com.param.global.dto.AreaMasterDto;
import com.param.global.dto.CityMasterDto;
import com.param.global.service.IAreaMasterService;
@Controller
@RequestMapping("/adt")
@SuppressWarnings({"rawtypes","unchecked"})
public class AreaMasterController implements ICommonConstants
{
	@Autowired
	private IAreaMasterService iAreaMaster;
	
	@RequestMapping(value="/saveAreaMaster" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response saveAreaMaster(@RequestBody AreaMasterDto areaMasterDto){
		try{
			
			return iAreaMaster.addAreaMaster(areaMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	
	@RequestMapping(value="/getAreaMasterList", method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getAreaMasterList(@RequestBody AreaMasterDto areaMasterDto){
		try{
			return iAreaMaster.getAreaMasterList(areaMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	
	 
	@RequestMapping(value="/updateAreaMaster" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response updateAreaMaster(@RequestBody AreaMasterDto areaMasterDto){
		try{
			return iAreaMaster.updateAreaMaster(areaMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	
	@RequestMapping(value="/getAreaById" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getAreaById(@RequestBody AreaMasterDto areaMasterDto){
		try{
			
			return iAreaMaster.getAreaById(areaMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	
	@RequestMapping(value="/updateStatusForArea" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response updateStatusForArea(@RequestBody AreaMasterDto areaMasterDto){
		try{
			
			return iAreaMaster.updateStatusForArea(areaMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	
	@RequestMapping(value="/getActiveAreaList", method = RequestMethod.GET)
	public @ResponseBody Response getActiveCityList(){
		try{
			return iAreaMaster.getActiveAreaList();
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}

	@RequestMapping(value="/getActiveCityListByDistrictId" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getActiveCityListByDistrictId(@RequestBody CityMasterDto cityMasterDto){
		try{
			
			return iAreaMaster.getActiveCityListByDistrictId(cityMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	
	@RequestMapping(value="/getAreaCount" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getAreaCount(@RequestBody AreaMasterDto areaMasterDto){
		try{
			
			return iAreaMaster.getAreaCount(areaMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}

}
