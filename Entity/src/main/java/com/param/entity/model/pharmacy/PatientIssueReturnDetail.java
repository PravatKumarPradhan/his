package com.param.entity.model.pharmacy;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.param.entity.model.base.BaseEntity;
import com.param.entity.model.master.ReturnReason;

@Entity(name="PatientIssueReturnDetail")
@Table(name="t_patient_issue_return_detail",schema="pharmacy")
public class PatientIssueReturnDetail extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="patient_issue_return_id")
	private PatientIssueReturn patientIssueReturn;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="issue_batch_mapper_id")
	private IssueDetailBatchMapper issueDetailBatchMapper;
	
	@Column(name="return_qty")
	private Integer returnQuantity;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="return_reason_id")
	private ReturnReason returnReason;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public PatientIssueReturn getPatientIssueReturn() {
		return patientIssueReturn;
	}

	public void setPatientIssueReturn(PatientIssueReturn patientIssueReturn) {
		this.patientIssueReturn = patientIssueReturn;
	}

	public IssueDetailBatchMapper getIssueDetailBatchMapper() {
		return issueDetailBatchMapper;
	}

	public void setIssueDetailBatchMapper(IssueDetailBatchMapper issueDetailBatchMapper) {
		this.issueDetailBatchMapper = issueDetailBatchMapper;
	}

	public Integer getReturnQuantity() {
		return returnQuantity;
	}

	public void setReturnQuantity(Integer returnQuantity) {
		this.returnQuantity = returnQuantity;
	}

	public ReturnReason getReturnReason() {
		return returnReason;
	}

	public void setReturnReason(ReturnReason returnReason) {
		this.returnReason = returnReason;
	}

	public PatientIssueReturnDetail() {
		super();
	}

	public PatientIssueReturnDetail(Integer id) {
		super();
		this.id = id;
	}

	public PatientIssueReturnDetail(Integer id, PatientIssueReturn patientIssueReturn,
			IssueDetailBatchMapper issueDetailBatchMapper, Integer returnQuantity, ReturnReason returnReason) {
		super();
		this.id = id;
		this.patientIssueReturn = patientIssueReturn;
		this.issueDetailBatchMapper = issueDetailBatchMapper;
		this.returnQuantity = returnQuantity;
		this.returnReason = returnReason;
	}
	
	public PatientIssueReturnDetail(IssueDetailBatchMapper issueDetailBatchMapper, Integer returnQuantity, ReturnReason returnReason) {
		super();
		this.issueDetailBatchMapper = issueDetailBatchMapper;
		this.returnQuantity = returnQuantity;
		this.returnReason = returnReason;
	}
}
