package com.param.global.service;

import javax.transaction.Transactional;

import com.param.global.common.Response;
import com.param.global.dto.WeekendMasterDto;

import in.param.exception.ApplicationException;
@Transactional
public interface IWeekendMasterService {

	Response saveWeekendMaster(WeekendMasterDto weekendMasterDto)throws ApplicationException;

	Response getWeekendMasterList(WeekendMasterDto weekendMasterDto)throws ApplicationException;

}
