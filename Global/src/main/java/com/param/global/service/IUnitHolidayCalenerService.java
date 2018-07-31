package com.param.global.service;

import javax.transaction.Transactional;

import com.param.global.common.Response;
import com.param.global.dto.UnitHolidayCalenderMapperDto;

import in.param.exception.ApplicationException;
@SuppressWarnings("rawtypes")
public interface IUnitHolidayCalenerService {

	@Transactional
	Response saveUnitHolidayCalener(UnitHolidayCalenderMapperDto unitHolidayCalenderMapperDto) throws ApplicationException;

	@Transactional
	Response getUnitHolidayCalenerList(UnitHolidayCalenderMapperDto unitHolidayCalenderMapperDto) throws ApplicationException;

	@Transactional
	Response getUnitHolidayCalenerCount(UnitHolidayCalenderMapperDto unitHolidayCalenderMapperDto) throws ApplicationException;

	@Transactional
	Response updateStatusForUnitHolidayCalener(UnitHolidayCalenderMapperDto unitHolidayCalenderMapperDto)throws ApplicationException;

	@Transactional
	Response getActiveUnitHolidayCalenerList(UnitHolidayCalenderMapperDto unitHolidayCalenderMapperDto)throws ApplicationException;

	
}
