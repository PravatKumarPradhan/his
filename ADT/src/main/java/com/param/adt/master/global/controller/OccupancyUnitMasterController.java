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
import com.param.adt.master.global.dto.OccupancyUnitMasterDto;
import com.param.adt.master.global.service.IOccupancyUnitMasterService;

@Controller
@RequestMapping("/adt")
@SuppressWarnings({"rawtypes","unchecked"})
public class OccupancyUnitMasterController implements ICommonConstants
{
	@Autowired
	IOccupancyUnitMasterService iOccupancyUnitMaster;
	
	@RequestMapping(value="/saveOccupancyUnitMaster" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response saveOccupancyUnitMaster(@RequestBody OccupancyUnitMasterDto occupancyUnitMasterDto){
		try{
			
			return iOccupancyUnitMaster.addOccupancyUnitMaster(occupancyUnitMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	
	@RequestMapping(value="/getOccupancyUnitMasterList", method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getOccupancyUnitMasterList(@RequestBody OccupancyUnitMasterDto occupancyUnitMasterDto){
		try{
			return iOccupancyUnitMaster.getOccupancyUnitMasterList(occupancyUnitMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	
	 
	@RequestMapping(value="/updateOccupancyUnitMaster" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response updateOccupancyUnitMaster(@RequestBody OccupancyUnitMasterDto occupancyUnitMasterDto){
		try{
			return iOccupancyUnitMaster.updateOccupancyUnitMaster(occupancyUnitMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	
	@RequestMapping(value="/getOccupancyUnitById" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getOccupancyUnitById(@RequestBody OccupancyUnitMasterDto occupancyUnitMasterDto){
		try{
			
			return iOccupancyUnitMaster.getOccupancyUnitById(occupancyUnitMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	
	@RequestMapping(value="/updateStatusForOccupancyUnit" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response updateStatusForOccupancyUnit(@RequestBody OccupancyUnitMasterDto occupancyUnitMasterDto){
		try{
			
			return iOccupancyUnitMaster.updateStatusForOccupancyUnit(occupancyUnitMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	
	@RequestMapping(value="/getActiveOccupancyUnitList", method = RequestMethod.GET)
	public @ResponseBody Response getActiveCityList(){
		try{
			return iOccupancyUnitMaster.getActiveOccupancyUnitList();
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	
	@RequestMapping(value="/getOccupancyUnitCount", method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getOccupancyUnitCount(@RequestBody OccupancyUnitMasterDto occupancyUnitMasterDto){
		try{
			return iOccupancyUnitMaster.getOccupancyUnitCount(occupancyUnitMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
}
