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
import com.param.global.dto.UnitHolidayCalenderMapperDto;
import com.param.global.service.IUnitHolidayCalenerService;

@Controller
@RequestMapping("/adt")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class UnitHolidayCalenderController implements ICommonConstants {

	@Autowired
	IUnitHolidayCalenerService iUnitHolidayCalenerService;

	@RequestMapping(value = "/saveUnitHolidayCalener", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response saveUnitHolidayCalener(
			@RequestBody UnitHolidayCalenderMapperDto unitHolidayCalenderMapperDto) {
		try {

			return iUnitHolidayCalenerService.saveUnitHolidayCalener(unitHolidayCalenderMapperDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@RequestMapping(value = "/getUnitHolidayCalenerList", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getUnitHolidayCalenerList(
			@RequestBody UnitHolidayCalenderMapperDto unitHolidayCalenderMapperDto) {
		try {
			return iUnitHolidayCalenerService.getUnitHolidayCalenerList(unitHolidayCalenderMapperDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}
	
	@RequestMapping(value = "/getActiveUnitHolidayCalenerList", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getActiveUnitHolidayCalenerList(
			@RequestBody UnitHolidayCalenderMapperDto unitHolidayCalenderMapperDto) {
		try {
			return iUnitHolidayCalenerService.getActiveUnitHolidayCalenerList(unitHolidayCalenderMapperDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@RequestMapping(value = "/getUnitHolidayCalenerCount", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getUnitHolidayCalenerCount(
			@RequestBody UnitHolidayCalenderMapperDto unitHolidayCalenderMapperDto) {
		try {
			return iUnitHolidayCalenerService.getUnitHolidayCalenerCount(unitHolidayCalenderMapperDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@RequestMapping(value = "/updateStatusForUnitHolidayCalener", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response updateStatusForUnitHolidayCalener(
			@RequestBody UnitHolidayCalenderMapperDto unitHolidayCalenderMapperDto) {
		try {

			return iUnitHolidayCalenerService.updateStatusForUnitHolidayCalener(unitHolidayCalenderMapperDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}
}
