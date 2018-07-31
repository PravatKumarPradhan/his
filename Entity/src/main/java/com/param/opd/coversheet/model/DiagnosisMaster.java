package com.param.opd.coversheet.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/*@NamedQueries({
		@NamedQuery(name="DIAGNOSIS_AUTO_FILL_SEARCH",
				query=" SELECT 	diagMaster.diagnosisId as diagnosisId, diagMaster.code as code, diagMaster.diagnosisName as diagnosisName, diagMaster.status as status, "
					+ "			doctorDiagMappList.doctorDiagnosisId.doctorId as doctorId "
					+ " FROM 	DiagnosisMaster diagMaster "
					+ " INNER 	JOIN diagMaster.DoctorDiagnosisMapperList doctorDiagMappList "
					+ " WHERE 	LOWER(diagMaster.diagnosisName) LIKE :diagnosisName "
					+ " AND 	doctorDiagMappList.doctorDiagnosisId.doctorId=:doctorId "
					+ " AND		diagMaster.unitId=:unitId "
					+ " AND		diagMaster.organizationId=:organizationId "
					+ " AND 	diagMaster.status = 'A' "
				)
})*/
@NamedQueries({
		@NamedQuery(name = "DIAGNOSIS_AUTO_FILL_SEARCH", query = " SELECT 	diagMaster.diagnosisId as diagnosisId, diagMaster.code as code, diagMaster.diagnosisName as diagnosisName, diagMaster.status as status "
				+ " FROM 	DiagnosisMaster diagMaster "
				+ " WHERE 	LOWER(diagMaster.diagnosisName) LIKE :diagnosisName "
				+ " AND		diagMaster.unitId=:unitId " + " AND		diagMaster.organizationId=:organizationId "
				+ " AND 	diagMaster.status = 'A' ") })

@Entity
@Table(name = "m_diagnosis_master", schema = "emr")
@SequenceGenerator(name = "m_diagnosis_master_seq", sequenceName = "emr.m_diagnosis_master_seq", allocationSize = 1)
public class DiagnosisMaster {

	@Id
	@Column(name = "diagnosis_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "m_diagnosis_master_seq")
	private int diagnosisId;

	@Column(name = "diagnosis_desc")
	private String diagnosisDesc;

	@Column(name = "status")
	private char status;

	@Column(name = "code")
	private String code;

	@Column(name = "diagnosis_name")
	private String diagnosisName;

	@Column(name = "is_notifiable")
	private char isNotifiable;

	@Column(name = "short_desc")
	private String shortDesc;

	@Column(name = "marathi_diagnosis_desc")
	private String marathiDiagnosisDesc;

	@Column(name = "created_by")
	private Integer created_by;

	@Column(name = "cerated_date")
	private Date cerated_date;

	@Column(name = "updated_by")
	private Integer updated_by;

	@Column(name = "updated_date")
	private Date updated_date;

	@Column(name = "unit_id")
	private Integer unitId;

	@Column(name = "organization_id")
	private Integer organizationId;

	@Column(name = "is_comorbidity")
	private char isComorbidity;

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "diagnosisMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	private ComplaintAppointmentMapper ComplaintAppointmentMapper;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "diagnosisMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<DoctorDiagnosisMapper> DoctorDiagnosisMapperList;

	/*
	 * @OneToOne(fetch = FetchType.LAZY, mappedBy = "diagnosisMaster", cascade =
	 * CascadeType.ALL, orphanRemoval = true) private
	 * DiagnosisPatientAppointmentMapper diagnosisPatientAppointmentMapper;
	 */

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

	public int getDiagnosisId() {
		return diagnosisId;
	}

	public void setDiagnosisId(int diagnosisId) {
		this.diagnosisId = diagnosisId;
	}

	public String getDiagnosisDesc() {
		return diagnosisDesc;
	}

	public void setDiagnosisDesc(String diagnosisDesc) {
		this.diagnosisDesc = diagnosisDesc;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
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

	public char getIsNotifiable() {
		return isNotifiable;
	}

	public void setIsNotifiable(char isNotifiable) {
		this.isNotifiable = isNotifiable;
	}

	public String getShortDesc() {
		return shortDesc;
	}

	public void setShortDesc(String shortDesc) {
		this.shortDesc = shortDesc;
	}

	public String getMarathiDiagnosisDesc() {
		return marathiDiagnosisDesc;
	}

	public void setMarathiDiagnosisDesc(String marathiDiagnosisDesc) {
		this.marathiDiagnosisDesc = marathiDiagnosisDesc;
	}

	public Integer getCreated_by() {
		return created_by;
	}

	public void setCreated_by(Integer created_by) {
		this.created_by = created_by;
	}

	public Date getCerated_date() {
		return cerated_date;
	}

	public void setCerated_date(Date cerated_date) {
		this.cerated_date = cerated_date;
	}

	public Integer getUpdated_by() {
		return updated_by;
	}

	public void setUpdated_by(Integer updated_by) {
		this.updated_by = updated_by;
	}

	public Date getUpdated_date() {
		return updated_date;
	}

	public void setUpdated_date(Date updated_date) {
		this.updated_date = updated_date;
	}

	public ComplaintAppointmentMapper getComplaintAppointmentMapper() {
		return ComplaintAppointmentMapper;
	}

	public void setComplaintAppointmentMapper(ComplaintAppointmentMapper complaintAppointmentMapper) {
		ComplaintAppointmentMapper = complaintAppointmentMapper;
	}

	public List<DoctorDiagnosisMapper> getDoctorDiagnosisMapperList() {
		return DoctorDiagnosisMapperList;
	}

	public void setDoctorDiagnosisMapperList(List<DoctorDiagnosisMapper> doctorDiagnosisMapperList) {
		DoctorDiagnosisMapperList = doctorDiagnosisMapperList;
	}

	public char getIsComorbidity() {
		return isComorbidity;
	}

	public void setIsComorbidity(char isComorbidity) {
		this.isComorbidity = isComorbidity;
	}

}
