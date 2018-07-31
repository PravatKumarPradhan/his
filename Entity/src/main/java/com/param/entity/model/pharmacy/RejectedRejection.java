package com.param.entity.model.pharmacy;

import java.io.Serializable;
import java.util.ArrayList;
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
import com.param.entity.model.adt.WardMasters;
import com.param.entity.model.master.Store;

@Entity(name="RejectedRejection")
@Table(name="t_rejected_rejection",schema="pharmacy")
public class RejectedRejection extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer id;
	
	@Column(name = "reject_no")
	private String rejectNo;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="issue_reject_id", nullable=false)
	private Reject reject;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ward_id")
	private WardMasters ward;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="store_id", nullable=false)
	private Store store;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="issue_id", nullable=false)
	private Issue issue;
	
	@OneToMany(mappedBy="rejectedRejection",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<RejectedRejectionDetail> rejectedRejectionDetail;
	
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

	public Reject getReject() {
		return reject;
	}

	public void setReject(Reject reject) {
		this.reject = reject;
	}

	public WardMasters getWard() {
		return ward;
	}

	public void setWard(WardMasters ward) {
		this.ward = ward;
	}

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	public Issue getIssue() {
		return issue;
	}

	public void setIssue(Issue issue) {
		this.issue = issue;
	}

	public List<RejectedRejectionDetail> getRejectedRejectionDetail() {
		return rejectedRejectionDetail;
	}

	public void setRejectedRejectionDetail(List<RejectedRejectionDetail> rejectedRejectionDetail) {
		this.rejectedRejectionDetail = rejectedRejectionDetail;
	}

	public RejectedRejection() {
		super();
	}

	public RejectedRejectionDetail addRejectedRejectionDetail(RejectedRejectionDetail rejectedRejectionDetail) {
		if (getRejectedRejectionDetail() == null)
			this.rejectedRejectionDetail = new ArrayList<RejectedRejectionDetail>();
		
		getRejectedRejectionDetail().add(rejectedRejectionDetail);
		rejectedRejectionDetail.setRejectedRejection(this);

		return rejectedRejectionDetail;
	}
	
	public RejectedRejection(Integer id, String rejectNo, Reject reject, WardMasters ward, Store store) {
		super();
		this.id = id;
		this.rejectNo = rejectNo;
		this.reject = reject;
		this.ward = ward;
		this.store = store;
	}
	
	public RejectedRejection(String rejectNo, Reject reject, Store store, Issue issue, WardMasters ward) {
		super();
		this.rejectNo = rejectNo;
		this.reject = reject;
		this.store = store;
		this.issue = issue;
		this.ward = ward;
	}
	
}