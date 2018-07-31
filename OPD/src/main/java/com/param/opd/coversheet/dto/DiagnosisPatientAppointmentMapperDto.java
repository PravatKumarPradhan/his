package com.param.opd.coversheet.dto;

import java.util.List;

public class DiagnosisPatientAppointmentMapperDto {

	private Integer appointmentId;

	private Integer diagnosisId;

	private Integer patientId;

	private Integer type;

	private String remark;

	private Integer unitId;

	private Integer organizationId;

	private char status;

	private Integer created_by;

	private String created_date;

	private Integer updated_by;

	private String updated_date;

	private List<DiagnosisPatientAppointmentDetailsDto> listDiagnosisPatientAppointmentDetailsDto;

	private Character isNotifiable;

	private Character isComorbidity;

	private String code;

	private String diagnosisName;
	
	private Integer encounterId;
	
	

	public Integer getEncounterId() {
		return encounterId;
	}

	public void setEncounterId(Integer encounterId) {
		this.encounterId = encounterId;
	}

	public Integer getAppointmentId() {
		return appointmentId;
	}

	public void setAppointmentId(Integer appointmentId) {
		this.appointmentId = appointmentId;
	}

	public Integer getDiagnosisId() {
		return diagnosisId;
	}

	public void setDiagnosisId(Integer diagnosisId) {
		this.diagnosisId = diagnosisId;
	}

	public Integer getPatientId() {
		return patientId;
	}

	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getUnitId() {
		return unitId;
	}

	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}

	public Integer getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}

	public Integer getCreated_by() {
		return created_by;
	}

	public void setCreated_by(Integer created_by) {
		this.created_by = created_by;
	}

	public String getCreated_date() {
		return created_date;
	}

	public void setCreated_date(String created_date) {
		this.created_date = created_date;
	}

	public Integer getUpdated_by() {
		return updated_by;
	}

	public void setUpdated_by(Integer updated_by) {
		this.updated_by = updated_by;
	}

	public String getUpdated_date() {
		return updated_date;
	}

	public void setUpdated_date(String updated_date) {
		this.updated_date = updated_date;
	}

	public List<DiagnosisPatientAppointmentDetailsDto> getListDiagnosisPatientAppointmentDetailsDto() {
		return listDiagnosisPatientAppointmentDetailsDto;
	}

	public void setListDiagnosisPatientAppointmentDetailsDto(
			List<DiagnosisPatientAppointmentDetailsDto> listDiagnosisPatientAppointmentDetailsDto) {
		this.listDiagnosisPatientAppointmentDetailsDto = listDiagnosisPatientAppointmentDetailsDto;
	}

	public Character getIsNotifiable() {
		return isNotifiable;
	}

	public void setIsNotifiable(Character isNotifiable) {
		this.isNotifiable = isNotifiable;
	}

	public Character getIsComorbidity() {
		return isComorbidity;
	}

	public void setIsComorbidity(Character isComorbidity) {
		this.isComorbidity = isComorbidity;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDiagnosisName() {
		return diagnosisName;
	}

	public void setDiagnosisName(String diagnosisName) {
		this.diagnosisName = diagnosisName;
	}

}
