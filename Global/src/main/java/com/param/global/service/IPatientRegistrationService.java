package com.param.global.service;

import com.param.global.common.Response;
import com.param.global.dto.PatientRegistrationDto;

import in.param.exception.ApplicationException;
@SuppressWarnings("rawtypes")
public interface IPatientRegistrationService {
	public Response getPatientDetailsById(Integer patientId) throws ApplicationException;

	public Response savePatientRegistration(PatientRegistrationDto patientRegistrationDto)throws ApplicationException;

	public Response getPatientDetais(PatientRegistrationDto patientRegistrationDto)throws ApplicationException;

	public Response patientSearch(PatientRegistrationDto patientRegistrationDto)throws ApplicationException;

	public Response updatepatientRegistration(PatientRegistrationDto patientRegistrationDto)throws ApplicationException;
	
	public Response patientDetailsByIdForBilling(int patientId)throws ApplicationException;

	public Response convertPatient(PatientRegistrationDto patientRegistrationDto)throws ApplicationException;
}
