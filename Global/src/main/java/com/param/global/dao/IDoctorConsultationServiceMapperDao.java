package com.param.global.dao;

import com.param.global.common.Response;
import com.param.global.dto.DoctorConsultationServiceMapperDto;
import com.param.global.model.DoctorConsultationServiceMapper;

import in.param.common.dao.IGenericDao;
import in.param.exception.ApplicationException;

@SuppressWarnings("rawtypes")
public interface IDoctorConsultationServiceMapperDao extends IGenericDao<DoctorConsultationServiceMapper, Integer> {

	Response saveDoctorConsultationServiceMapperService(
			DoctorConsultationServiceMapperDto doctorConsultationServiceMapperDto) throws ApplicationException;

	Response getDoctorConsultationServiceMapperList(
			DoctorConsultationServiceMapperDto doctorConsultationServiceMapperDto) throws ApplicationException;

	Response updateDoctorConsultationServiceMapper(
			DoctorConsultationServiceMapperDto doctorConsultationServiceMapperDto) throws ApplicationException;

	Integer checkDoctorConsultationExistOrNot(DoctorConsultationServiceMapperDto doctorConsultationServiceMapperDto)
			throws ApplicationException;

	Response getDoctorConsultationServiceMapperById(
			DoctorConsultationServiceMapperDto doctorConsultationServiceMapperDto) throws ApplicationException;

	Response inactivePreviousEntry(Integer id) throws ApplicationException;

	Response getDoctorConsultationServiceBySpecialityAndDoctorId(
			DoctorConsultationServiceMapperDto doctorConsultationServiceMapperDto) throws ApplicationException;

}
