package com.param.global.dao;

import com.param.global.common.Response;
import com.param.global.dto.UnitHolidayCalenderMapperDto;
import com.param.global.model.UnitHolidayCalenderMapper;

import in.param.common.dao.IGenericDao;
import in.param.exception.ApplicationException;
@SuppressWarnings("rawtypes")
public interface IUnitHolidayCalenderDao extends IGenericDao<UnitHolidayCalenderMapper, Integer>{

	Response truncateUnitHolidayCalener(UnitHolidayCalenderMapperDto obj) throws ApplicationException;
	
	Response saveUnitHolidayCalener(UnitHolidayCalenderMapperDto unitHolidayCalenderMapperDto) throws ApplicationException;

	Response getUnitHolidayCalenerList(UnitHolidayCalenderMapperDto unitHolidayCalenderMapperDto) throws ApplicationException;

	Response getUnitHolidayCalenerById(UnitHolidayCalenderMapperDto unitHolidayCalenderMapperDto) throws ApplicationException;

	Response updateStatusForUnitHolidayCalener(UnitHolidayCalenderMapperDto unitHolidayCalenderMapperDto);

	Response getCount(UnitHolidayCalenderMapperDto unitHolidayCalenderMapperDto) throws ApplicationException;

	Response getActiveUnitHolidayCalenerList(UnitHolidayCalenderMapperDto unitHolidayCalenderMapperDto)throws ApplicationException;

}
