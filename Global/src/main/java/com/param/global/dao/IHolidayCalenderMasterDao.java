package com.param.global.dao;

import com.param.global.common.Response;
import com.param.global.dto.DayMasterDto;
import com.param.global.dto.HolidayCalenderMasterDto;
import com.param.global.model.HolidayCalenderMaster;

import in.param.common.dao.IGenericDao;
import in.param.exception.ApplicationException;
@SuppressWarnings("rawtypes")
public interface IHolidayCalenderMasterDao extends IGenericDao<HolidayCalenderMaster, Integer>{

	
	Response getHolidayCalenderByName(HolidayCalenderMasterDto holidayCalenderMasterDto)throws ApplicationException;

	Response addHolidayCalenderTypeMaster(HolidayCalenderMasterDto holidayCalenderMasterDto) throws ApplicationException;

	Response getHolidayCalenderMasterList(HolidayCalenderMasterDto holidayCalenderMasterDto)throws ApplicationException;

	Response getHolidayByNameNotId(HolidayCalenderMasterDto holidayCalenderMasterDto)throws ApplicationException;

	Response updateHolidayMaster(HolidayCalenderMasterDto holidayCalenderMasterDto)throws ApplicationException;

	Response getHolidayByID(HolidayCalenderMasterDto holidayCalenderMasterDto)throws ApplicationException;

	Response updateHolidayStatus(HolidayCalenderMasterDto holidayCalenderMasterDto)throws ApplicationException;

	Response getActiveHolidayMasterList() throws ApplicationException;

	Response getCount(HolidayCalenderMasterDto holidayCalenderMasterDto)throws ApplicationException;

	Response getActiveDayListById(DayMasterDto dayMasterDto)throws ApplicationException;

	Response getActiveHolidayDayList(DayMasterDto dayMasterDto) throws ApplicationException;

}
