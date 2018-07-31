package com.param.adt.admission.service;

import javax.transaction.Transactional;

import com.param.adt.admission.dto.AdmissionNoteDto;
import com.param.adt.common.Response;
import com.param.adt.master.global.dto.DoctorSpecialityMapperDto;
import com.param.adt.master.global.dto.VisitMasterDto;
import com.param.global.dto.PatientRegistrationDto;

import in.param.exception.ApplicationException;
@SuppressWarnings("rawtypes")
public interface IAdmissionRequestService 
{
	
	@Transactional
	Response saveAdmissionRequest(AdmissionNoteDto admissionNoteDto) throws ApplicationException;

	@Transactional
	Response getPatientListLike(PatientRegistrationDto patientRegistrationDto) throws ApplicationException;
	
	@Transactional
	Response getPatientVisitDetailsById(VisitMasterDto visitMasterDto) throws ApplicationException;

	@Transactional
	Response getDoctorListAganistSpeciality(DoctorSpecialityMapperDto doctorSpecialityMapper) throws ApplicationException;
	
	@Transactional
	Response getReasonMasterList() throws ApplicationException;

	@Transactional
	Response saveAdmissionPatientMapper(AdmissionNoteDto admissionNoteDto)throws ApplicationException;

	@Transactional
	Response saveWaitingListNumber(AdmissionNoteDto admissionNoteDto)throws ApplicationException;

	@Transactional
	Response getDoctorsListAganistSpecialitys(DoctorSpecialityMapperDto doctorSpecialityMapper)throws ApplicationException;

	
}
