package com.param.opd.coversheet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;
import com.param.opd.coversheet.dto.ComplaintAppointmentMapperDto;
import com.param.opd.coversheet.dto.DiagnosisPatientAppointmentMapperDto;
import com.param.opd.coversheet.service.IPatientDiagnosisService;

@RestController
@SuppressWarnings({ "rawtypes", "unchecked" })
@RequestMapping("/api/opd")
public class PatientDiagnosisController implements ICommonConstants {

	@Autowired
	IPatientDiagnosisService iPatientDiagnosisService;

	@RequestMapping(value = "/savePatientDiagnosis", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response savePatientDiagnosis(@RequestHeader(value = "unit_id") int unitId,
			@RequestHeader(value = "organization_id") int organizationId,
			@RequestHeader(value = "patient_id") int patientId,
			@RequestHeader(value = "encounterId") int encounterId,
			@RequestBody DiagnosisPatientAppointmentMapperDto diagnosisPatientAppointmentMapperDto) {
		try {

			diagnosisPatientAppointmentMapperDto.setUnitId(unitId);
			diagnosisPatientAppointmentMapperDto.setEncounterId(encounterId);
			diagnosisPatientAppointmentMapperDto.setPatientId(patientId);
			diagnosisPatientAppointmentMapperDto.setOrganizationId(organizationId);
			return iPatientDiagnosisService.savePatientDiagnosis(diagnosisPatientAppointmentMapperDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@RequestMapping(value = "/getPatientDiagnosisByPatientId", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getPatientDiagnosisByPatientId(@RequestHeader(value = "unit_id") int unitId,
			@RequestHeader(value = "organization_id") int organizationId,
			@RequestHeader(value = "patient_id") int patientId) {
		try {
			DiagnosisPatientAppointmentMapperDto diagnosisPatientAppointmentMapperDto = new DiagnosisPatientAppointmentMapperDto();
			diagnosisPatientAppointmentMapperDto.setUnitId(unitId);
			diagnosisPatientAppointmentMapperDto.setPatientId(patientId);
			diagnosisPatientAppointmentMapperDto.setOrganizationId(organizationId);
			return iPatientDiagnosisService.getPatientDiagnosisByPatientId(diagnosisPatientAppointmentMapperDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}
}
