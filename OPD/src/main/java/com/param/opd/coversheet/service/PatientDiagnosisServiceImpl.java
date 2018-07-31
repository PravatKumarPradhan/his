package com.param.opd.coversheet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;
import com.param.opd.coversheet.dao.IPatientDiagnosisDao;
import com.param.opd.coversheet.dto.DiagnosisPatientAppointmentMapperDto;
import in.param.exception.ApplicationException;

@Service
@SuppressWarnings({ "rawtypes", "unchecked" })
public class PatientDiagnosisServiceImpl implements IPatientDiagnosisService, ICommonConstants {

	@Autowired
	IPatientDiagnosisDao iPatientDiagnosisDao;

	
	@Override
	public Response savePatientDiagnosis(DiagnosisPatientAppointmentMapperDto diagnosisPatientAppointmentMapperDto)
			throws ApplicationException {
		try {
			return iPatientDiagnosisDao.savePatientDiagnosis(diagnosisPatientAppointmentMapperDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}


	@Override
	public Response getPatientDiagnosisByPatientId(
			DiagnosisPatientAppointmentMapperDto diagnosisPatientAppointmentMapperDto) throws ApplicationException {		
		try {
			return iPatientDiagnosisDao.getPatientDiagnosisByPatientId(diagnosisPatientAppointmentMapperDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

}
