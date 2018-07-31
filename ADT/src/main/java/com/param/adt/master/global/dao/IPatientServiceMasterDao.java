package com.param.adt.master.global.dao;

import com.param.adt.common.Response;
import com.param.adt.master.global.dto.PatientSourceMasterDto;
import com.param.adt.master.global.model.PatientSourceMaster;

import in.param.common.dao.IGenericDao;
import in.param.exception.ApplicationException;
@SuppressWarnings("rawtypes")
public interface IPatientServiceMasterDao extends IGenericDao<PatientSourceMaster, Integer>{

	
	Response getPatientSourceByName(PatientSourceMasterDto patientSourceMasterDto) throws ApplicationException;

	Response addPatientSourceTypeMaster(PatientSourceMasterDto patientSourceMasterDto) throws ApplicationException;

	Response getPatientSourceMasterList(PatientSourceMasterDto patientSourceMasterDto) throws ApplicationException;

	Response getPatientSourceById(PatientSourceMasterDto patientSourceMasterDto)throws ApplicationException;

	Response getActivePatientSourceList() throws ApplicationException;

	Response getPatientSourceByNameNotId(PatientSourceMasterDto patientSourceMasterDto) throws ApplicationException;

	Response updatePatientSource(PatientSourceMasterDto patientSourceMasterDto)throws ApplicationException;

	Response updatePatientSourceStatus(PatientSourceMasterDto patientSourceMasterDto)throws ApplicationException;

	Response getCount(PatientSourceMasterDto patientSourceMasterDto) throws ApplicationException;

}
