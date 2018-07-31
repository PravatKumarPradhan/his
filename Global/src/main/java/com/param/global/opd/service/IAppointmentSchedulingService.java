package com.param.global.opd.service;

import javax.transaction.Transactional;

import com.param.global.common.Response;
import com.param.global.dto.SlotMasterDto;

import in.param.exception.ApplicationException;
@SuppressWarnings("rawtypes")
public interface IAppointmentSchedulingService {

	@Transactional
	Response scheduleAppointment(SlotMasterDto slotMasterDto)throws ApplicationException;

	@Transactional
	Response scheduledAppointmentList(SlotMasterDto slotMasterDto)throws ApplicationException;

	@Transactional
	Response cancelScheduledAppointment(SlotMasterDto slotMasterDto)throws ApplicationException;

	@Transactional
	Response rescheduleAppointment(SlotMasterDto slotMasterDto)throws ApplicationException;

	@Transactional
	Response searchAppointmentsByCriteria(SlotMasterDto slotMasterDto)throws ApplicationException;

	@Transactional
	Response patientAutofillSearch(SlotMasterDto slotMasterDto)throws ApplicationException;

	@Transactional
	Response updateAppointmentStatus(SlotMasterDto slotMasterDto) throws ApplicationException;

	@Transactional
	Response blockedScheduleAppointmentList(SlotMasterDto slotMasterDto)throws ApplicationException;

}
