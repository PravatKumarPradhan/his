package com.param.ER.service;

import javax.transaction.Transactional;

import com.param.adt.common.Response;
import com.param.global.dto.AdmissionDto;
import com.param.global.dto.PatientSearchDto;

import in.param.exception.ApplicationException;

@SuppressWarnings("rawtypes")
public interface IUnknownPatientRegistrationService {

	@Transactional
	Response getPriorityMasterList() throws ApplicationException;

	@Transactional
	Response saveUnknownPatient(AdmissionDto unAdmissionDto)throws ApplicationException;
			
	@Transactional
	Response saveERPatientAdmisson(AdmissionDto unAdmissionDto) throws ApplicationException;

	@Transactional
	Response saveERsavePatientDetails(AdmissionDto unAdmissionDto)throws ApplicationException;

	@Transactional
	Response patientSearchByMultipleCriteria(PatientSearchDto patientSearchDto)throws ApplicationException;

	@Transactional
	Response saveMedicoLegalDetails(AdmissionDto unAdmissionDto)throws ApplicationException;
 

}
