	package com.param.entity.lis.global;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import com.param.entity.lis.common.LocalTimeConverter;


@Entity
@Table(name = "t_report_history_master", schema = "lab")
@SequenceGenerator(name = "seq_report_history_master", sequenceName = "lab.seq_report_history_master", allocationSize = 1)
public class ReportHistoryMaster {
	

	@Id
	@Column(name = "history_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_report_history_master")
	private int historyId;
	
	@Column(name = "patient_id")
	private Integer patientId;
	
	@Column(name = "test_id")
	private Integer testId;
	
	@Column(name = "is_report_release")
	private Character isReportRelease;
	
	@Column(name = "created_by")
	private Integer createdBy;
	
	@Column(name = "created_date")
	@Convert(converter = LocalTimeConverter.class)
	private Long createdDate;

	@Column(name = "updated_date")
	@Convert(converter = LocalTimeConverter.class)
	private Long updatedDate;
	
	@Column(name = "updated_by")
	private Integer updatedBy;
	
	@Column(name = "status")
	private Character status;
	
	@Column(name = "result_id")
	private Integer resultId;
	
	@Column(name = "org_unit_id")
	private Integer orgUnitId;
	
	@Column(name = "org_id")
	private Integer orgId;
	
	@Column(name = "dept_id")
	private Integer deptId;

	@Column(name = "sub_dept_id")
	private Integer subdeptId;
	
	@Column(name = "lab_sample_dtls_id")
	private Integer labSampleDtlsId;

	public int getHistoryId() {
		return historyId;
	}

	public void setHistoryId(int historyId) {
		this.historyId = historyId;
	}

	public Integer getPatientId() {
		return patientId;
	}

	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}

	public Integer getTestId() {
		return testId;
	}

	public void setTestId(Integer testId) {
		this.testId = testId;
	}

	public Character getIsReportRelease() {
		return isReportRelease;
	}

	public void setIsReportRelease(Character isReportRelease) {
		this.isReportRelease = isReportRelease;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Long getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Long createdDate) {
		this.createdDate = createdDate;
	}

	public Long getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Long updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Integer getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Character getStatus() {
		return status;
	}

	public void setStatus(Character status) {
		this.status = status;
	}

	public Integer getResultId() {
		return resultId;
	}

	public void setResultId(Integer resultId) {
		this.resultId = resultId;
	}

	public Integer getOrgUnitId() {
		return orgUnitId;
	}

	public void setOrgUnitId(Integer orgUnitId) {
		this.orgUnitId = orgUnitId;
	}

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public Integer getDeptId() {
		return deptId;
	}

	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}

	public Integer getSubdeptId() {
		return subdeptId;
	}

	public void setSubdeptId(Integer subdeptId) {
		this.subdeptId = subdeptId;
	}

	public Integer getLabSampleDtlsId() {
		return labSampleDtlsId;
	}

	public void setLabSampleDtlsId(Integer labSampleDtlsId) {
		this.labSampleDtlsId = labSampleDtlsId;
	}
	

	
}
