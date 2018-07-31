package com.param.opd.coversheet.service;

import javax.transaction.Transactional;

import com.param.global.common.Response;
import com.param.opd.coversheet.dto.DiagnosisPatientAppointmentMapperDto;

import in.param.exception.ApplicationException;

@SuppressWarnings("rawtypes")
public interface IPatientDiagnosisService {	
	
	@Transactional
	public Response savePatientDiagnosis(DiagnosisPatientAppointmentMapperDto diagnosisPatientAppointmentMapperDto) throws ApplicationException;

	@Transactional
	public Response getPatientDiagnosisByPatientId(DiagnosisPatientAppointmentMapperDto diagnosisPatientAppointmentMapperDto) throws ApplicationException;

}
