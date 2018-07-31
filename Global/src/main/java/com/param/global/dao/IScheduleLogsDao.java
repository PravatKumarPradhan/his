package com.param.global.dao;

import in.param.common.dao.IGenericDao;
import in.param.exception.ApplicationException;

import com.param.global.common.Response;
import com.param.global.dto.ScheduleLogsDto;
import com.param.global.model.ScheduleLogs;

@SuppressWarnings("rawtypes")
public interface IScheduleLogsDao extends IGenericDao<ScheduleLogs, Integer>{

	
	public Response saveScheduleLogs(ScheduleLogsDto scheduleLogsDto)throws ApplicationException;
	public Response getPendingScheduleLogs()throws ApplicationException;
	public Response markScheduleLogsCompleted(Integer scheduleLogsId)throws ApplicationException;
	public Response markScheduleLogsError(Integer scheduleLogsId)throws ApplicationException;
}
