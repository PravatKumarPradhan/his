package com.param.global.service;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;
import com.param.global.dao.IScheduleLogsDao;
import com.param.global.dto.ScheduleLogsDto;

@Component
@SuppressWarnings("rawtypes")
public class ScheduleLogsListener  implements HttpSessionBindingListener{

	@Autowired
	IScheduleLogsDao iScheduleLogsDao;
	ScheduleLogsDto scheduleLogsDto;
	
	
	public ScheduleLogsDto  getScheduleLogsDto(){
		return scheduleLogsDto;
	}
	
	public void setScheduleLogsDto(ScheduleLogsDto scheduleLogsDto){
		this.scheduleLogsDto = scheduleLogsDto;
	}
	
	
	@Override
	public void valueBound(HttpSessionBindingEvent event) {
		try{
			System.out.println("-------------- Schedule log added to queue for insertion ------------");
			System.out.println(event.getValue().getClass().getName());
			ScheduleLogsListener listener = (ScheduleLogsListener)event.getValue();
			scheduleLogsDto = listener.getScheduleLogsDto();
			Response schRes= iScheduleLogsDao.saveScheduleLogs(scheduleLogsDto);
			if(schRes.getStatus().equalsIgnoreCase(ICommonConstants.SUCCESS)){
				System.out.println(scheduleLogsDto);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

	@Override
	public void valueUnbound(HttpSessionBindingEvent event) {
		System.out.println(event.getValue() != null ? ((ScheduleLogsListener)event.getValue()).getScheduleLogsDto() : "Object Not Found to detach");
		System.out.println("-------------- Schedule log detached from session ------------");
		
	}

}
