package com.param.global.opd.dao;

import com.param.global.common.Response;
import com.param.global.dto.SlotMasterDto;

import in.param.exception.ApplicationException;

@SuppressWarnings("rawtypes")
public interface IAppointmentSchedulingDao {

	Response scheduleAppointment(SlotMasterDto slotMasterDto)throws ApplicationException;

	Response scheduledAppointmentList(SlotMasterDto slotMasterDto)throws ApplicationException;

	Response cancelScheduledAppointment(SlotMasterDto slotMasterDto)throws ApplicationException;

	Response rescheduleAppointment(SlotMasterDto slotMasterDto)throws ApplicationException;

	Response searchAppointmentsByCriteria(SlotMasterDto slotMasterDto)throws ApplicationException;
	
	Response updateAppointmentStatus(SlotMasterDto slotMasterDto)throws ApplicationException;

	Response patientAutofillSearch(SlotMasterDto slotMasterDto)throws ApplicationException;

	Response blockedScheduleAppointmentList(SlotMasterDto slotMasterDto)throws ApplicationException;

	Response isAppointmentValid(SlotMasterDto slotMasterDto)throws ApplicationException;

}
