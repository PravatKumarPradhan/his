package com.param.ER.service;

import javax.transaction.Transactional;

import com.param.adt.common.Response;
import com.param.global.dto.AdmissionDto;
import com.param.global.dto.UnknownPatientRegistrationDto;

import in.param.exception.ApplicationException;
@SuppressWarnings("rawtypes")
public interface IERPatientListService {

	@Transactional
	Response getERAdmissionList(AdmissionDto erAdmissionDto)throws ApplicationException;

	@Transactional
	Response getERAdmissionCount(AdmissionDto erAdmissionDto)throws ApplicationException;

	@Transactional
	Response getDataForMapOfBed(AdmissionDto erAdmissionDto)throws ApplicationException;

	@Transactional
	Response getERPatient(UnknownPatientRegistrationDto unknownPatientRegistrationDto)throws ApplicationException;

	@Transactional
	Response searchERPatient(AdmissionDto admissionDto)throws ApplicationException;

	@Transactional
	Response searchPatientForMapOfBed(AdmissionDto erAdmissionDto)throws ApplicationException;

}
