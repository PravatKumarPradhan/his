package com.param.global.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;
import com.param.global.dto.CityMasterDto;
import com.param.global.dto.DistrictMasterDto;
import com.param.global.service.ICityMasterService;

@Controller
@RequestMapping("/adt")
@SuppressWarnings({"rawtypes","unchecked"})
public class CityMasterController implements ICommonConstants
{
	
	@Autowired
	private ICityMasterService iCityMaser;
	
	@RequestMapping(value="/saveCityMaster" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response saveCityMaster(@RequestBody CityMasterDto cityMasterDto){
		try{
			
			return iCityMaser.addCityMaster(cityMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	
	@RequestMapping(value="/getCityMasterList", method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getCityMasterList(@RequestBody CityMasterDto cityMasterDto){
		try{
			return iCityMaser.getCityMasterList(cityMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	
	 
	@RequestMapping(value="/updateCityMaster" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response updateCityMaster(@RequestBody CityMasterDto citytMasterDto){
		try{
			return iCityMaser.updateCityMaster(citytMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	
	@RequestMapping(value="/getCityById" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getCityById(@RequestBody CityMasterDto cityMasterDto){
		try{
			
			return iCityMaser.getCityById(cityMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	
	@RequestMapping(value="/updateStatusForCity" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response updateStatusForCity(@RequestBody CityMasterDto cityMasterDto){
		try{
			
			return iCityMaser.updateStatusForDistrict(cityMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	
	@RequestMapping(value="/getActiveCityList", method = RequestMethod.GET)
	public @ResponseBody Response getActiveCityList(){
		try{
			return iCityMaser.getActiveCityList();
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}

	@RequestMapping(value="/getActiveDistrictListByStateId" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getActiveDistrictListByStateId(@RequestBody DistrictMasterDto districtMasterDto){
		try{
			
			return iCityMaser.getActiveDistrictListByStateId(districtMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}

	@RequestMapping(value="/getCityCount" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getCityCount(@RequestBody CityMasterDto cityMasterDto){
		try{
			
			return iCityMaser.getCityCount(cityMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	
	@RequestMapping(value="/getAllCityMasterList/{stateId}", method = RequestMethod.GET)
	public @ResponseBody Response getCityMasterList(@PathVariable("stateId") int stateId){
		try{
			CityMasterDto  cityMasterDto= new CityMasterDto();
			cityMasterDto.setStateId(stateId);
			return iCityMaser.getCityByStateIs(cityMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
}
