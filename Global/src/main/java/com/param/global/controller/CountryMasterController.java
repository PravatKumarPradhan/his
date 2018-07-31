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
import com.param.global.dto.CountryMasterDto;
import com.param.global.service.ICountryMasterService;


@Controller
@RequestMapping("/adt")
@SuppressWarnings({"rawtypes","unchecked"})
public class CountryMasterController implements ICommonConstants
{

  @Autowired
  private ICountryMasterService iCountryService;
	
	
	@RequestMapping(value="/saveCountryMaster" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response saveCountryMaster(@RequestBody CountryMasterDto countryMasterDto){
		try{
			
			return iCountryService.addCountryMaster(countryMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	
	@RequestMapping(value="/getCountryMasterList", method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getCountryMasterList(@RequestBody CountryMasterDto countryMasterDto){
		try{
			return iCountryService.getCountryMasterList(countryMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	
	 
	@RequestMapping(value="/updateCountryMaster" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response updateCountryMaster(@RequestBody CountryMasterDto countryMasterDto){
		try{
			return iCountryService.updateCountryMaster(countryMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	
	@RequestMapping(value="/getCountryById" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getCountryById(@RequestBody CountryMasterDto countryMasterDto){
		try{
			
			return iCountryService.getCountryById(countryMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	
	@RequestMapping(value="/updateStatusForCountry" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response updateStatusForCountry(@RequestBody CountryMasterDto countryMasterDto){
		try{
			
			return iCountryService.updateStatusForCountry(countryMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	
	@RequestMapping(value="/getActiveCountryList", method = RequestMethod.GET)
	public @ResponseBody Response getActiveCountryList(){
		try{
			return iCountryService.getActiveCountryList();
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}

	@RequestMapping(value="/getCountryCount" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getCountryCount(@RequestBody CountryMasterDto countryMasterDto){
		try{
			
			return iCountryService.getCountryCount(countryMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	
	
	@RequestMapping(value="/getAllCountryMasterList", method = RequestMethod.GET)
	public @ResponseBody Response getAllCountryList(){
		try{
			return iCountryService.getCountryMasterList();
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
}
