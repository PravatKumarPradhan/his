package com.param.global.dao;

import com.param.global.common.Response;
import com.param.global.dto.WeekendMasterDto;
import com.param.global.model.WeekendMaster;

import in.param.common.dao.IGenericDao;
import in.param.exception.ApplicationException;
@SuppressWarnings("rawtypes")
public interface IWeekendMasterDao extends IGenericDao<WeekendMaster, Integer>{

	Response saveWeekendMaster(WeekendMasterDto weekendMasterDto)throws ApplicationException;

	Response getWeekendMasterList(WeekendMasterDto weekendMasterDto)throws ApplicationException;

}
