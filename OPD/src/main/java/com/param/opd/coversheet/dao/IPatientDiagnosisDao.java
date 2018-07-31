package com.param.opd.coversheet.dao;

import com.param.global.common.Response;
import com.param.opd.coversheet.dto.DiagnosisPatientAppointmentMapperDto;
import in.param.exception.ApplicationException;

@SuppressWarnings("rawtypes")
public interface IPatientDiagnosisDao {

	Response savePatientDiagnosis(DiagnosisPatientAppointmentMapperDto diagnosisPatientAppointmentMapperDto)
			throws ApplicationException;

	Response getPatientDiagnosisByPatientId(DiagnosisPatientAppointmentMapperDto diagnosisPatientAppointmentMapperDto)
			throws ApplicationException;

}
