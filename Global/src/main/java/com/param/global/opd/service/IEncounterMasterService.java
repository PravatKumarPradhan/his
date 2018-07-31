package com.param.global.opd.service;

import com.param.global.common.Response;
import com.param.global.dto.SlotMasterDto;
import com.param.opd.encounter.dto.AppointmentStatusMasterDto;
import com.param.opd.encounter.dto.EncounterMasterDto;

import in.param.exception.ApplicationException;
@SuppressWarnings("rawtypes")
public interface IEncounterMasterService {
	public Response saveEncounterMaster(EncounterMasterDto encounterMasterDto)throws ApplicationException;
	
	public Response getEncounterDetailsById(Integer encounterId)throws ApplicationException;
	
	Integer assignVisitTypeIdAccordingToConditions(SlotMasterDto encounterMasterDto)throws ApplicationException;

	public Response getEncounterDetails(EncounterMasterDto encounterMasterDto);
	
	/*	ADDED BY JYOTI
	 * 	DATE 03-05-2018
	 */
	public Response getListOfEncounterMasterDao(SlotMasterDto slotMasterDto)throws ApplicationException;
	public Response getSearchedListEncounterMaster(SlotMasterDto slotMasterDto)throws ApplicationException;
	
	public Response getListOfAppointmentStatus(AppointmentStatusMasterDto appointmentStatusMasterDto)throws ApplicationException;

	//---
	public Response getListOfEncountersByPatientId(EncounterMasterDto encounterMasterDto) throws ApplicationException;
}
