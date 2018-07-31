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
import com.param.global.dto.DistrictMasterDto;
import com.param.global.dto.StateMasterDto;
import com.param.global.service.IDistrictMasterService;

@Controller
@RequestMapping("/adt")
@SuppressWarnings({"rawtypes","unchecked"})
public class DistrictMasterController implements ICommonConstants
{
	@Autowired
	private IDistrictMasterService iDistrictSercive;
	
	@RequestMapping(value="/saveDistrictMaster" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response saveDistrictMaster(@RequestBody DistrictMasterDto districtMasterDto){
		try{
			
			return iDistrictSercive.addDistrictMaster(districtMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	
	@RequestMapping(value="/getDistrictMasterList", method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getDistrictMasterList(@RequestBody DistrictMasterDto districtMasterDto){
		try{
			return iDistrictSercive.getDistrictMasterList(districtMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	
	 
	@RequestMapping(value="/updateDistrictMaster" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response updateDistrictMaster(@RequestBody DistrictMasterDto districtMasterDto){
		try{
			return iDistrictSercive.updateDistrictMaster(districtMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	
	@RequestMapping(value="/getDistrictById" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getDistrictById(@RequestBody DistrictMasterDto districtMasterDto){
		try{
			
			return iDistrictSercive.getDistrictById(districtMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	
	@RequestMapping(value="/updateStatusForDistrict" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response updateStatusForDistrict(@RequestBody DistrictMasterDto districtMasterDto){
		try{
			
			return iDistrictSercive.updateStatusForDistrict(districtMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	
	@RequestMapping(value="/getActiveDistrictList", method = RequestMethod.GET)
	public @ResponseBody Response getActiveDistrictList(){
		try{
			return iDistrictSercive.getActiveDistrictList();
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}

	@RequestMapping(value="/getActiveStateListByCountryId" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getActiveStateListByCountryId(@RequestBody StateMasterDto stateMasterDto){
		try{
			
			return iDistrictSercive.getActiveStateListByCountryId(stateMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	
	@RequestMapping(value="/getDistrictCount" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getDistrictCount(@RequestBody DistrictMasterDto districtMasterDto){
		try{
			
			return iDistrictSercive.getDistrictCount(districtMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	
}
