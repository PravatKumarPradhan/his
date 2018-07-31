package com.param.ER.dao;

import com.param.adt.common.Response;
import com.param.global.dto.AdmissionDto;
import com.param.global.dto.PatientSearchDto;
import com.param.global.model.UnknownPatientRegistration;

import in.param.common.dao.IGenericDao;
import in.param.exception.ApplicationException;
@SuppressWarnings("rawtypes")
public interface IUnknownPatientRegistrationDao extends IGenericDao<UnknownPatientRegistration, Integer>
{

	
	Response saveUnknownPatient(AdmissionDto unAdmissionDto) throws ApplicationException;

	Response getPriorityMasterList() throws ApplicationException;

	Response saveERPatientAdmission(AdmissionDto unAdmissionDto)throws ApplicationException;

	Response saveERPatientDetails(AdmissionDto unAdmissionDto)throws ApplicationException;

	Response patientSearchByMultipleCriteria(PatientSearchDto patientSearchDto)throws ApplicationException;

	Response saveMedicoLegalDetails(AdmissionDto unAdmissionDto)throws ApplicationException;

}
