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
import javax.persistence.Table;

import com.param.entity.model.base.BaseEntity;
import com.param.entity.model.master.RejectReason;
import com.param.entity.model.master.UomType;
import com.param.entity.model.master.UomUnit;
import com.param.entity.model.master.User;

@Entity
@Table(name = "t_material_issue_batch_mapper", schema = "inventory")
public class MaterialIssueBatchMapper extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "batch_id", nullable = false)
	private Batch batch;

	@Column(name = "issue_quantity")
	private Integer issueQuantity;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "accepted_by")
	private User acceptedBy;

	@Column(name = "accepted_on")
	private Date acceptedOn;

	@Column(name = "accepted_quantity")
	private Integer acceptedQuantity = 0;

	@Column(name = "rejected_note", length = 100)
	private String rejectNote;

	@Column(name = "rejected_quantity")
	private Integer rejectQuantity = 0;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "rejected_reason_id")
	private RejectReason rejectReason;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "uom_type_id", nullable = false)
	private UomType uomType;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "uom_unit_id", nullable = false)
	private UomUnit uomUnit;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "material_issue_detail_id")
	private MaterialIssueDetail materialIssueDetail;

	public MaterialIssueBatchMapper() {
	}

	public MaterialIssueBatchMapper(Integer id) {
		super();
		this.id = id;
	}



	public MaterialIssueBatchMapper(Batch batch, Integer issueQuantity, UomType uomType, UomUnit uomUnit) {
		super();
		this.batch = batch;
		this.issueQuantity = issueQuantity;
		this.uomType = uomType;
		this.uomUnit = uomUnit;
	}

	public void updateBatchMapper(Batch batch, Integer issueQuantity, UomType uomType, UomUnit uomUnit) {
		this.batch = batch;
		this.issueQuantity = issueQuantity;
		this.uomType = uomType;
		this.uomUnit = uomUnit;
	}
	
	public void updateMaterialIssueBatchMapper(Integer id, Integer acceptedQuantity, String rejectNote, Integer rejectQuantity,
			RejectReason rejectReason) {
		this.id = id;
		this.acceptedQuantity = acceptedQuantity;
		this.rejectNote = rejectNote;
		this.rejectQuantity = rejectQuantity;
		this.rejectReason = rejectReason;
	}

/*	public void updateMaterialIssueBatchMapper(Batch batch, Integer acceptedQuantity, String rejectNote, Integer rejectQuantity,
			RejectReason rejectReason, UomType uomType, UomUnit uomUnit) {
		this.batch = batch;
		this.acceptedQuantity = acceptedQuantity;
		this.rejectNote = rejectNote;
		this.rejectQuantity = rejectQuantity;
		this.rejectReason = rejectReason;
		this.uomType = uomType;
		this.uomUnit = uomUnit;
	}*/

	public MaterialIssueBatchMapper(Batch batch, Integer acceptedQuantity, String rejectNote, Integer rejectQuantity,
			RejectReason rejectReason, UomType uomType, UomUnit uomUnit, MaterialIssueDetail materialIssueDetail) {
		super();
		this.batch = batch;
		this.acceptedQuantity = acceptedQuantity;
		this.rejectNote = rejectNote;
		this.rejectQuantity = rejectQuantity;
		this.rejectReason = rejectReason;
		this.uomType = uomType;
		this.uomUnit = uomUnit;
		this.materialIssueDetail = materialIssueDetail;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIssueQuantity() {
		return this.issueQuantity;
	}

	public void setIssueQuantity(Integer issueQuantity) {
		this.issueQuantity = issueQuantity;
	}

	public Batch getBatch() {
		return batch;
	}

	public void setBatch(Batch batch) {
		this.batch = batch;
	}

	public UomType getUomType() {
		return uomType;
	}

	public void setUomType(UomType uomType) {
		this.uomType = uomType;
	}

	public UomUnit getUomUnit() {
		return uomUnit;
	}

	public void setUomUnit(UomUnit uomUnit) {
		this.uomUnit = uomUnit;
	}

	public MaterialIssueDetail getMaterialIssueDetail() {
		return materialIssueDetail;
	}

	public void setMaterialIssueDetail(MaterialIssueDetail materialIssueDetail) {
		this.materialIssueDetail = materialIssueDetail;
	}

	public User getAcceptedBy() {
		return acceptedBy;
	}

	public void setAcceptedBy(User acceptedBy) {
		this.acceptedBy = acceptedBy;
	}

	public Date getAcceptedOn() {
		return acceptedOn;
	}

	public void setAcceptedOn(Date acceptedOn) {
		this.acceptedOn = acceptedOn;
	}

	public Integer getAcceptedQuantity() {
		return acceptedQuantity;
	}

	public void setAcceptedQuantity(Integer acceptedQuantity) {
		this.acceptedQuantity = acceptedQuantity;
	}

	public String getRejectNote() {
		return rejectNote;
	}

	public void setRejectNote(String rejectNote) {
		this.rejectNote = rejectNote;
	}

	public Integer getRejectQuantity() {
		return rejectQuantity;
	}

	public void setRejectQuantity(Integer rejectQuantity) {
		this.rejectQuantity = rejectQuantity;
	}

	public RejectReason getRejectReason() {
		return rejectReason;
	}

	public void setRejectReason(RejectReason rejectReason) {
		this.rejectReason = rejectReason;
	}

}
