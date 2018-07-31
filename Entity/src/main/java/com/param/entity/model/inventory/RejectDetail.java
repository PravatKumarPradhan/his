package com.param.entity.model.inventory;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.param.entity.model.base.BaseEntity;
import com.param.entity.model.master.RejectReason;
import com.param.entity.model.master.User;

@Entity(name="com.param.entity.model.inventory.rejectdetail")
@Table(name = "t_reject_detail", schema = "inventory")
public class RejectDetail extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "accepted_by")
	private User acceptedBy;

	@Column(name = "accepted_on")
	private Date acceptedOn;

	@Column(name = "accepted_quantity")
	private Integer acceptedQuantity;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "reject_reason_id")
	private RejectReason rejectReason;

	@Column(name = "rejected_note", length = 100)
	private String rejectedNote;

	@Column(name = "rejected_quantity")
	private Integer rejectedQuantity;

	@ManyToOne
	@JoinColumn(name = "reject_id")
	private Reject reject;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "issue_batch_mapper_id")
	private MaterialIssueBatchMapper materialIssueBatchMapper;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "return_detail_id")
	private ReturnDetail returnDetail;
	
	public RejectDetail() {
	}

	public RejectDetail(RejectReason rejectReason, String rejectedNote, Integer rejectedQuantity, MaterialIssueBatchMapper materialIssueBatchMapper) {
		super();
		this.rejectReason = rejectReason;
		this.rejectedNote = rejectedNote;
		this.rejectedQuantity = rejectedQuantity;
		this.materialIssueBatchMapper = materialIssueBatchMapper;
	}
	
	public RejectDetail(RejectReason rejectReason, String rejectedNote,
			Integer rejectedQuantity, ReturnDetail returnDetail) {
		super();
		this.rejectReason = rejectReason;
		this.rejectedNote = rejectedNote;
		this.rejectedQuantity = rejectedQuantity;
		this.returnDetail = returnDetail;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getAcceptedBy() {
		return acceptedBy;
	}

	public void setAcceptedBy(User acceptedBy) {
		this.acceptedBy = acceptedBy;
	}

	public Date getAcceptedOn() {
		return this.acceptedOn;
	}

	public void setAcceptedOn(Date acceptedOn) {
		this.acceptedOn = acceptedOn;
	}

	public Integer getAcceptedQuantity() {
		return this.acceptedQuantity;
	}

	public void setAcceptedQuantity(Integer acceptedQuantity) {
		this.acceptedQuantity = acceptedQuantity;
	}

	public RejectReason getRejectReason() {
		return rejectReason;
	}

	public void setRejectReason(RejectReason rejectReason) {
		this.rejectReason = rejectReason;
	}

	public String getRejectedNote() {
		return this.rejectedNote;
	}

	public void setRejectedNote(String rejectedNote) {
		this.rejectedNote = rejectedNote;
	}

	public Integer getRejectedQuantity() {
		return this.rejectedQuantity;
	}

	public void setRejectedQuantity(Integer rejectedQuantity) {
		this.rejectedQuantity = rejectedQuantity;
	}

	public Reject getReject() {
		return this.reject;
	}

	public void setReject(Reject reject) {
		this.reject = reject;
	}
	
	public MaterialIssueBatchMapper getMaterialIssueBatchMapper() {
		return materialIssueBatchMapper;
	}

	public void setMaterialIssueBatchMapper(MaterialIssueBatchMapper materialIssueBatchMapper) {
		this.materialIssueBatchMapper = materialIssueBatchMapper;
	}

	public ReturnDetail getReturnDetail() {
		return returnDetail;
	}

	public void setReturnDetail(ReturnDetail returnDetail) {
		this.returnDetail = returnDetail;
	}

}
