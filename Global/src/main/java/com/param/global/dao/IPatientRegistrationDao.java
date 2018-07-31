package com.param.global.dao;

import com.param.global.common.Response;
import com.param.global.dto.PatientRegistrationDto;
import com.param.global.model.PatientRegistration;

import in.param.common.dao.IGenericDao;
import in.param.exception.ApplicationException;

@SuppressWarnings("rawtypes")
public interface IPatientRegistrationDao extends IGenericDao<PatientRegistration, Integer> {
	public Response getPatientDetailsById(Integer patientId) throws ApplicationException;

	public Response savePatientRegistration(PatientRegistrationDto patientRegistrationDto) throws ApplicationException;

	public Response savePatientDetails(PatientRegistrationDto patientRegistrationDto) throws ApplicationException;

	public Response getPatientDetais(PatientRegistrationDto patientRegistrationDto) throws ApplicationException;

	public Response checkUniquePatient(PatientRegistrationDto patientRegistrationDto) throws ApplicationException;

	public Long getNextValPatientId() throws ApplicationException;

	public Response patientSearchOTC(PatientRegistrationDto patientRegistrationDto) throws ApplicationException;

	public Response patientSearchER(PatientRegistrationDto patientRegistrationDto) throws ApplicationException;

	public Response patientSearchNormal(PatientRegistrationDto patientRegistrationDto) throws ApplicationException;

	public Response updatePatient(PatientRegistrationDto patientRegistrationDto) throws ApplicationException;

	public Response savePatientRegistrationLog(PatientRegistrationDto patientRegistrationDto);

	public Response patientSearchNormalByPatientId(PatientRegistrationDto patientRegistrationDto)
			throws ApplicationException;

	public Response patientDetailsByIdForBilling(int patientId) throws ApplicationException;

	// data sync
	public Response getPatientByIdForSync(Integer recordId) throws ApplicationException;

	public Response patientSearchPre(PatientRegistrationDto patientRegistrationDto)throws ApplicationException;

}
