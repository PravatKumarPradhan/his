package com.param.entity.model.inventory;

import java.io.Serializable;
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
import com.param.entity.model.master.Status;
import com.param.entity.model.master.Store;
import com.param.entity.model.master.User;

@Entity(name="com.param.entity.model.inventory.reject")
@Table(name = "t_reject", schema = "inventory")
public class Reject extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "to_store_id", nullable = false)
	private Store toStore;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "from_store_id", nullable = false)
	private Store fromStore;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "rejected_by")
	private User rejectedBy;

	@Column(name = "rejected_date")
	private Date rejectedDate;

	private String remark;

	@Column(name = "return_no")
	private String returnNo;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "status_id", nullable = false)
	private Status status;

	@ManyToOne
	@JoinColumn(name = "material_issue_id")
	private MaterialIssue materialIssue;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "reject", cascade = CascadeType.ALL)
	private List<RejectDetail> RejectDetails;

	public Reject() {
		super();
	}

	public Reject(Store toStore, Store fromStore, User rejectedBy, Date rejectedDate, String returnNo,
			MaterialIssue materialIssue, Status status) {
		super();
		this.toStore = toStore;
		this.fromStore = fromStore;
		this.rejectedBy = rejectedBy;
		this.rejectedDate = rejectedDate;
		this.returnNo = returnNo;
		this.materialIssue = materialIssue;
		this.status = status;
	}

	public Reject(Store toStore, Store fromStore, User rejectedBy, Date rejectedDate, String returnNo, Status status) {
		super();
		this.toStore = toStore;
		this.fromStore = fromStore;
		this.rejectedBy = rejectedBy;
		this.rejectedDate = rejectedDate;
		this.returnNo = returnNo;
		this.status = status;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Store getToStore() {
		return toStore;
	}

	public void setToStore(Store toStore) {
		this.toStore = toStore;
	}

	public Store getFromStore() {
		return fromStore;
	}

	public void setFromStore(Store fromStore) {
		this.fromStore = fromStore;
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

	public String getReturnNo() {
		return returnNo;
	}

	public void setReturnNo(String returnNo) {
		this.returnNo = returnNo;
	}

	public MaterialIssue getMaterialIssue() {
		return materialIssue;
	}

	public void setMaterialIssue(MaterialIssue materialIssue) {
		this.materialIssue = materialIssue;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public List<RejectDetail> getRejectDetails() {
		return RejectDetails;
	}

	public void setRejectDetails(List<RejectDetail> rejectDetails) {
		RejectDetails = rejectDetails;
	}

	public RejectDetail addRejectDetail(RejectDetail RejectDetail) {
		if (getRejectDetails() == null)
			this.RejectDetails = new ArrayList<RejectDetail>();

		getRejectDetails().add(RejectDetail);
		RejectDetail.setReject(this);

		return RejectDetail;
	}

	public RejectDetail removeRejectDetail(RejectDetail RejectDetail) {
		getRejectDetails().remove(RejectDetail);
		RejectDetail.setReject(null);

		return RejectDetail;
	}

	public RejectDetail addMaterialIssueDetail(RejectDetail RejectDetail) {
		if (getRejectDetails() == null)
			this.RejectDetails = new ArrayList<RejectDetail>();

		getRejectDetails().add(RejectDetail);
		RejectDetail.setReject(this);

		return RejectDetail;
	}

}