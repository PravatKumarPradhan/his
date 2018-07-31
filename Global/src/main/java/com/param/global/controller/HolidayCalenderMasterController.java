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
import com.param.global.dto.DayMasterDto;
import com.param.global.dto.HolidayCalenderMasterDto;
import com.param.global.service.IHolidayCalenderMasterService;

@Controller
@RequestMapping("/adt")
@SuppressWarnings({"rawtypes","unchecked"})
public class HolidayCalenderMasterController implements ICommonConstants
{
	@Autowired
	private IHolidayCalenderMasterService iHolidayMasterService;
	
	@RequestMapping(value="/saveHolidayCalenderMaster" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response saveHolidayCalenderMaster(@RequestBody HolidayCalenderMasterDto holidayCalenderMasterDto){
		try{
			
			return iHolidayMasterService.addHolidayCalenderMaster(holidayCalenderMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	
	@RequestMapping(value="/getHolidayCalenderMasterList",method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getHolidayCalenderMasterList(@RequestBody HolidayCalenderMasterDto holidayCalenderMasterDto){
		try{
			return iHolidayMasterService.getHolidayCalenderMasterList(holidayCalenderMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	
	 
	@RequestMapping(value="/updateHolidayCalenderMaster" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response updateHolidayCalenderMaster(@RequestBody HolidayCalenderMasterDto holidayCalenderMasterDto){
		try{
			return iHolidayMasterService.updateHolidayCalenderMaster(holidayCalenderMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	
	@RequestMapping(value="/getHolidayCalenderById" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getHolidayCalenderById(@RequestBody HolidayCalenderMasterDto holidayCalenderMasterDto){
		try{
			
			return iHolidayMasterService.getHolidayCalenderById(holidayCalenderMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	
	@RequestMapping(value="/updateStatusForHolidayCalender" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response updateStatusForHolidayCalender(@RequestBody HolidayCalenderMasterDto holidayCalenderMasterDto){
		try{
			
			return iHolidayMasterService.updateStatusForHolidayCalender(holidayCalenderMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	
	@RequestMapping(value="/getActiveDayListById", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getActiveDayListById(@RequestBody DayMasterDto dayMasterDto){
		try{
			return iHolidayMasterService.getActiveDayListById(dayMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	
	@RequestMapping(value="/getActiveHolidayDayList", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getActiveHolidayDayList(@RequestBody DayMasterDto dayMasterDto){
		try{
			return iHolidayMasterService.getActiveHolidayDayList(dayMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	
	@RequestMapping(value="/getHolidayCount", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getHolidayCount(@RequestBody HolidayCalenderMasterDto holidayCalenderMasterDto){
		try{
			return iHolidayMasterService.getHolidayCount(holidayCalenderMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
}
