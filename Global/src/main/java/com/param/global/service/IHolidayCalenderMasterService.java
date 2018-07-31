package com.param.global.service;

import javax.transaction.Transactional;

import com.param.global.common.Response;
import com.param.global.dto.DayMasterDto;
import com.param.global.dto.HolidayCalenderMasterDto;

import in.param.exception.ApplicationException;
@SuppressWarnings("rawtypes")
public interface IHolidayCalenderMasterService {

	@Transactional
	Response addHolidayCalenderMaster(HolidayCalenderMasterDto holidayCalenderMasterDto) throws ApplicationException;

	@Transactional
	Response getHolidayCalenderMasterList(HolidayCalenderMasterDto holidayCalenderMasterDto) throws ApplicationException;

	@Transactional
	Response updateHolidayCalenderMaster(HolidayCalenderMasterDto holidayCalenderMasterDto) throws ApplicationException;

	@Transactional
	Response getHolidayCalenderById(HolidayCalenderMasterDto holidayCalenderMasterDto)throws ApplicationException;

	@Transactional
	Response updateStatusForHolidayCalender(HolidayCalenderMasterDto holidayCalenderMasterDto)throws ApplicationException;

	@Transactional
	Response getActiveHolidayCalenderList()throws ApplicationException;

	@Transactional
	Response getHolidayCount(HolidayCalenderMasterDto holidayCalenderMasterDto) throws ApplicationException;
	
	@Transactional
	Response getActiveDayListById(DayMasterDto dayMasterDto)throws ApplicationException;

	@Transactional
	Response getActiveHolidayDayList(DayMasterDto dayMasterDto) throws ApplicationException;
	
	

}
