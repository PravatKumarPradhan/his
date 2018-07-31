package com.param.global.service;

import javax.transaction.Transactional;

import com.param.global.common.Response;
import com.param.global.dto.DoctorConsultationServiceMapperDto;

import in.param.exception.ApplicationException;
@SuppressWarnings("rawtypes")
public interface IDoctorConsultationServiceMapperService {

	@Transactional
	Response saveDoctorConsultationServiceMapperService(DoctorConsultationServiceMapperDto doctorConsultationServiceMapperDto)throws ApplicationException;

	@Transactional
	Response getDoctorConsultationServiceMapperList(DoctorConsultationServiceMapperDto doctorConsultationServiceMapperDto)throws ApplicationException;

	@Transactional
	Response updateDoctorConsultationServiceMapper(DoctorConsultationServiceMapperDto doctorConsultationServiceMapperDto)throws ApplicationException;

	@Transactional
	Response getDoctorConsultationServiceMapperById(DoctorConsultationServiceMapperDto doctorConsultationServiceMapperDto);

	@Transactional
	Response getDoctorConsultationServiceBySpecialityAndDoctorId(DoctorConsultationServiceMapperDto doctorConsultationServiceMapperDto);

}
