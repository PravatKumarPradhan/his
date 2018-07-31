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
import com.param.global.dto.WeekendMasterDto;
import com.param.global.service.IWeekendMasterService;


@Controller
@RequestMapping("/api/global/unit")
@SuppressWarnings({"rawtypes","unchecked"})
public class WeekendMasterController implements ICommonConstants {

	@Autowired
	IWeekendMasterService iWeekendMasterService; 
	
	@RequestMapping(value="/saveWeekendMaster", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response saveWeekendMaster(@RequestBody WeekendMasterDto weekendMasterDto) {
		try {
				return iWeekendMasterService.saveWeekendMaster(weekendMasterDto);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}
	
	@RequestMapping(value="/getWeekendMasterList", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getWeekendMasterList(@RequestBody WeekendMasterDto weekendMasterDto) {
		try {
				return iWeekendMasterService.getWeekendMasterList(weekendMasterDto);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}
	
}
