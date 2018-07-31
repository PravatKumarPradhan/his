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
import com.param.entity.model.master.RejectReason;

@Entity(name="RejectDetail")
@Table(name="t_reject_detail",schema="pharmacy")
public class RejectDetail extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="reject_id")
	private Reject reject;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="issue_batch_mapper" )
	private IssueDetailBatchMapper issueDetailBatchMapper;
	
	@Column(name = "rejected_quantity")
	private Integer rejectedQuantity;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="reject_reason_id" )
	private RejectReason rejectReason;
	
	@Column(name = "rejected_note")
	private String rejectedNote;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="return_detail_id")
	private PatientIssueReturnDetail patientIssueReturnDetail;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Reject getReject() {
		return reject;
	}

	public void setReject(Reject reject) {
		this.reject = reject;
	}

	public IssueDetailBatchMapper getIssueDetailBatchMapper() {
		return issueDetailBatchMapper;
	}

	public void setIssueDetailBatchMapper(IssueDetailBatchMapper issueDetailBatchMapper) {
		this.issueDetailBatchMapper = issueDetailBatchMapper;
	}

	public Integer getRejectedQuantity() {
		return rejectedQuantity;
	}

	public void setRejectedQuantity(Integer rejectedQuantity) {
		this.rejectedQuantity = rejectedQuantity;
	}

	public RejectReason getRejectReason() {
		return rejectReason;
	}

	public void setRejectReason(RejectReason rejectReason) {
		this.rejectReason = rejectReason;
	}

	public String getRejectedNote() {
		return rejectedNote;
	}

	public void setRejectedNote(String rejectedNote) {
		this.rejectedNote = rejectedNote;
	}
	
	public PatientIssueReturnDetail getPatientIssueReturnDetail() {
		return patientIssueReturnDetail;
	}

	public void setPatientIssueReturnDetail(PatientIssueReturnDetail patientIssueReturnDetail) {
		this.patientIssueReturnDetail = patientIssueReturnDetail;
	}

	public RejectDetail() {
		super();
	}

	public RejectDetail(Integer id) {
		super();
		this.id = id;
	}

	public RejectDetail(Integer id, Reject reject, IssueDetailBatchMapper issueDetailBatchMapper, Integer rejectedQuantity,
			RejectReason rejectReason, String rejectedNote) {
		super();
		this.id = id;
		this.reject = reject;
		this.issueDetailBatchMapper = issueDetailBatchMapper;
		this.rejectedQuantity = rejectedQuantity;
		this.rejectReason = rejectReason;
		this.rejectedNote = rejectedNote;
	}
	
	public RejectDetail( Reject reject, IssueDetailBatchMapper issueDetailBatchMapper, Integer rejectedQuantity,
			RejectReason rejectReason, String rejectedNote) {
		super();
		this.reject = reject;
		this.issueDetailBatchMapper = issueDetailBatchMapper;
		this.rejectedQuantity = rejectedQuantity;
		this.rejectReason = rejectReason;
		this.rejectedNote = rejectedNote;
	}

	public RejectDetail(IssueDetailBatchMapper issueDetailBatchMapper, Integer rejectedQuantity, RejectReason rejectReason,
			String rejectedNote) {
		super();
		this.issueDetailBatchMapper = issueDetailBatchMapper;
		this.rejectedQuantity = rejectedQuantity;
		this.rejectReason = rejectReason;
		this.rejectedNote = rejectedNote;
	}
	
	public RejectDetail(IssueDetailBatchMapper issueDetailBatchMapper, Integer rejectedQuantity, RejectReason rejectReason,
			String rejectedNote, PatientIssueReturnDetail patientIssueReturnDetail) {
		super();
		this.issueDetailBatchMapper = issueDetailBatchMapper;
		this.rejectedQuantity = rejectedQuantity;
		this.rejectReason = rejectReason;
		this.rejectedNote = rejectedNote;
		this.patientIssueReturnDetail = patientIssueReturnDetail;
	}
	
}