package com.param.entity.model.pharmacy;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.param.entity.model.base.BaseEntity;
import com.param.entity.model.master.User;

@Entity(name="Reject")
@Table(name="t_reject",schema="pharmacy")
public class Reject extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer id;
	
	@Column(name = "reject_no")
	private String rejectNo;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="issue_id", nullable=false)
	private Issue issue;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="rejected_by", nullable=false)
	private User rejectedBy;
	
	@Column(name = "rejected_date")
	private Date rejectedDate;
	
	@Column(name = "remark")
	private String remark;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="return_id")
	private PatientIssueReturn patientIssueReturn;
	
	@OneToMany(mappedBy="reject",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<RejectDetail> rejectDetail;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRejectNo() {
		return rejectNo;
	}

	public void setRejectNo(String rejectNo) {
		this.rejectNo = rejectNo;
	}

	public Issue getIssue() {
		return issue;
	}

	public void setIssue(Issue issue) {
		this.issue = issue;
	}

	public User getRejectedBy() {
		return rejectedBy;
	}

	public void setRejectedBy(User rejectedBy) {
		this.rejectedBy = rejectedBy;
	}

	public Date getRejectedDate() {
		return rejectedDate;
	}

	public void setRejectedDate(Date rejectedDate) {
		this.rejectedDate = rejectedDate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public List<RejectDetail> getRejectDetail() {
		return rejectDetail;
	}

	public void setRejectDetail(List<RejectDetail> rejectDetail) {
		this.rejectDetail = rejectDetail;
	}
	
	public PatientIssueReturn getPatientIssueReturn() {
		return patientIssueReturn;
	}

	public void setPatientIssueReturn(PatientIssueReturn patientIssueReturn) {
		this.patientIssueReturn = patientIssueReturn;
	}

	public RejectDetail addRejectDetail(RejectDetail rejectDetail) {
		if (getRejectDetail() == null)
			this.rejectDetail = new ArrayList<RejectDetail>();
		
		getRejectDetail().add(rejectDetail);
		rejectDetail.setReject(this);

		return rejectDetail;
	}
	
	
	public Reject() {
		super();
	}

	public Reject(Integer id, String rejectNo, Issue issue, User rejectedBy, Date rejectedDate, String remark) {
		super();
		this.id = id;
		this.rejectNo = rejectNo;
		this.issue = issue;
		this.rejectedBy = rejectedBy;
		this.rejectedDate = rejectedDate;
		this.remark = remark;
	}

	public Reject(String rejectNo, Issue issue, User rejectedBy, Timestamp rejectedDate) {
		super();
		this.rejectNo = rejectNo;
		this.issue = issue;
		this.rejectedBy = rejectedBy;
		this.rejectedDate = rejectedDate;
	}
	
	public Reject(String rejectNo, Issue issue, User rejectedBy, Timestamp rejectedDate, PatientIssueReturn patientIssueReturn) {
		super();
		this.rejectNo = rejectNo;
		this.issue = issue;
		this.rejectedBy = rejectedBy;
		this.rejectedDate = rejectedDate;
		this.patientIssueReturn = patientIssueReturn;
	}

	public Reject(Integer id) {
		super();
		this.id = id;
	}
	
	
	
}