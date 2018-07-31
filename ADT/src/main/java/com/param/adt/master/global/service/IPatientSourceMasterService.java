package com.param.adt.master.global.service;

import javax.transaction.Transactional;

import com.param.adt.common.Response;
import com.param.adt.master.global.dto.PatientSourceMasterDto;

import in.param.exception.ApplicationException;
@SuppressWarnings("rawtypes")
public interface IPatientSourceMasterService 
{

	@Transactional
	Response addPatientSourceMaster(PatientSourceMasterDto patientSourceMasterDto) throws ApplicationException;

	@Transactional
	Response getPatientSourceMasterList(PatientSourceMasterDto patientSourceMasterDto) throws ApplicationException;

	@Transactional
	Response updatePatientSourceMaster(PatientSourceMasterDto patientSourceMasterDto) throws ApplicationException;

	@Transactional
	Response getPatientSourceById(PatientSourceMasterDto patientSourceMasterDto)throws ApplicationException;

	@Transactional
	Response updateStatusForPatientSource(PatientSourceMasterDto patientSourceMasterDto)throws ApplicationException;

	@Transactional
	Response getActivePatientSourceList() throws ApplicationException;

	@Transactional
	Response getPatientSourceCount(PatientSourceMasterDto patientSourceMasterDto);

}
