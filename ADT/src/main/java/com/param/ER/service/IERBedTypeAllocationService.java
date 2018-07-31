package com.param.ER.service;

import javax.transaction.Transactional;

import com.param.ER.dto.ERBedTypeAllocationDto;
import com.param.adt.common.Response;
import com.param.global.dto.AdmissionDto;
import com.param.global.dto.DoctorMasterDto;
import com.param.global.dto.UnknownPatientRegistrationDto;

import in.param.exception.ApplicationException;
@SuppressWarnings("rawtypes")
public interface IERBedTypeAllocationService {

	
	@Transactional
	Response getUnknownPatientsList(UnknownPatientRegistrationDto unknownPatientRegistrationDto) throws ApplicationException;

	@Transactional
	Response getERBedTypeAllocationList(ERBedTypeAllocationDto erBedTypeAllocationDto) throws ApplicationException;

	@Transactional
	Response getERBedTypeAllocationCount(ERBedTypeAllocationDto erBedTypeAllocationDto)throws ApplicationException;

	@Transactional
	Response getERBedList(ERBedTypeAllocationDto erBedTypeAllocationDto) throws ApplicationException;

	@Transactional
	Response getActiveDoctorList(DoctorMasterDto doctorMasterDto)throws ApplicationException;
	
	@Transactional
	Response updateERPatientAdmisson(AdmissionDto admissionDto);

	@Transactional
	Response updateERsavePatientDetails(AdmissionDto admissionDto)throws ApplicationException;

	@Transactional
	Response saveTransfer(AdmissionDto admissionDto);

}
