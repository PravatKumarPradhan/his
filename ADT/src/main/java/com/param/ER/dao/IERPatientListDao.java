package com.param.ER.dao;

import com.param.ER.model.ERBedTypeAllocation;
import com.param.adt.common.Response;
import com.param.global.dto.AdmissionDto;
import com.param.global.dto.UnknownPatientRegistrationDto;

import in.param.common.dao.IGenericDao;
import in.param.exception.ApplicationException;
@SuppressWarnings("rawtypes")
public interface IERPatientListDao extends IGenericDao<ERBedTypeAllocation, Integer>{

	Response getERAdmissionList(AdmissionDto erAdmissionDto) throws ApplicationException;

	Response getERAdmissionCount(AdmissionDto erAdmissionDto)throws ApplicationException;

	Response getDataForMapOfBed(AdmissionDto erAdmissionDto)throws ApplicationException;

	Response getERPatient(UnknownPatientRegistrationDto unknownPatientRegistrationDto)throws ApplicationException;

	Response searchERPatient(AdmissionDto admissionDto)throws ApplicationException;

	Response searchPatientForMapOfBed(AdmissionDto erAdmissionDto)throws ApplicationException;

}
