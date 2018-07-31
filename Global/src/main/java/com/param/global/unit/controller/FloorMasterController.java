package com.param.global.unit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;
import com.param.global.unit.dto.FloorMasterDto;
import com.param.global.unit.service.IFloorMasterService;

@Controller
@RequestMapping("/adt")
@SuppressWarnings({"rawtypes","unchecked"})
public class FloorMasterController implements ICommonConstants{
	
	@Autowired
	IFloorMasterService iFloorService;

	@RequestMapping(value="/saveFloorMaster" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response saveFloorMaster(@RequestBody FloorMasterDto floorMasterDto){
		try{
			
			return iFloorService.addFloorMaster(floorMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	
	@RequestMapping(value="/getFloorMasterList", method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getFloorMasterList(@RequestBody FloorMasterDto floorMasterDto){
		try{
			return iFloorService.getFloorMasterList(floorMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	
	 
	@RequestMapping(value="/updateFloorMaster" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response updateFloorMaster(@RequestBody FloorMasterDto floorMasterDto){
		try{
			return iFloorService.updateFloorMaster(floorMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	
	@RequestMapping(value="/getFloorById" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getFloorById(@RequestBody FloorMasterDto floorMasterDto){
		try{
			
			return iFloorService.getFloorById(floorMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	
	@RequestMapping(value="/updateStatusForFloor" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response updateStatusForFloor(@RequestBody FloorMasterDto floorMasterDto){
		try{
			
			return iFloorService.updateStatusForFloor(floorMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	
	@RequestMapping(value="/getActiveFloorList", method = RequestMethod.GET)
	public @ResponseBody Response getActiveFloorList(){
		try{
			return iFloorService.getActiveFloorList();
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}

	@RequestMapping(value="/getFloorCount" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getFloorCount(@RequestBody FloorMasterDto floorMasterDto){
		try{
			
			return iFloorService.getFloorCount(floorMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
}
