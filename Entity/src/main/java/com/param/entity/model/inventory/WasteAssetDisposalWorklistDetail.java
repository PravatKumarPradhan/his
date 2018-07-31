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
import com.param.entity.model.master.Status;
import com.param.entity.model.master.User;

@Entity(name = "com.param.entity.model.inventory.WasteAssetDisposalWorklistDetail")
@Table(name = "t_waste_asset_disposal_worklist_detail", schema = "inventory")
public class WasteAssetDisposalWorklistDetail extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private Integer id;

	@Column(name = "approved_disposed_qty")
	private Integer approvedDisposedQty;

	@Column(name = "disposed_qty")
	private Integer disposedQty;

	@Column(name = "reject_note")
	private String rejectNote;

	@Column(name = "reject_quantity")
	private Integer rejectQuantity;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "reject_reason_id")
	private RejectReason rejectReason;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "status_id", nullable = false)
	private Status status;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="asset_disposal_worklist_request_detail_id")
	private AssetDisposalWorklistRequestDetail assetDisposalWorklistRequestDetail;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="waste_asset_disposal_worklist_id")
	private WasteAssetDisposalWorklist wasteAssetDisposalWorklist;

	public WasteAssetDisposalWorklistDetail() {
	}

	public WasteAssetDisposalWorklistDetail(Integer disposedQty, Status status,
			AssetDisposalWorklistRequestDetail assetDisposalWorklistRequestDetail) {
		super();
		this.disposedQty = disposedQty;
		this.status = status;
		this.assetDisposalWorklistRequestDetail = assetDisposalWorklistRequestDetail;
	}
	
	public void updateWasteAssetDisposalWorklistDetail(Integer approvedDisposedQty, String rejectNote, Integer rejectQuantity,
			RejectReason rejectReason, Status status, Date updatedDate, User updatedUser) {
		this.approvedDisposedQty = approvedDisposedQty;
		this.rejectNote = rejectNote;
		this.rejectQuantity = rejectQuantity;
		this.rejectReason = rejectReason;
		this.status = status;
		this.updatedDate = updatedDate;
		this.updatedBy = updatedUser;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getApprovedDisposedQty() {
		return this.approvedDisposedQty;
	}

	public void setApprovedDisposedQty(Integer approvedDisposedQty) {
		this.approvedDisposedQty = approvedDisposedQty;
	}

	public Integer getDisposedQty() {
		return this.disposedQty;
	}

	public void setDisposedQty(Integer disposedQty) {
		this.disposedQty = disposedQty;
	}

	public String getRejectNote() {
		return this.rejectNote;
	}

	public void setRejectNote(String rejectNote) {
		this.rejectNote = rejectNote;
	}

	public Integer getRejectQuantity() {
		return this.rejectQuantity;
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

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public AssetDisposalWorklistRequestDetail getAssetDisposalWorklistRequestDetail() {
		return this.assetDisposalWorklistRequestDetail;
	}

	public void setAssetDisposalWorklistRequestDetail(
			AssetDisposalWorklistRequestDetail assetDisposalWorklistRequestDetail) {
		this.assetDisposalWorklistRequestDetail = assetDisposalWorklistRequestDetail;
	}

	public WasteAssetDisposalWorklist getWasteAssetDisposalWorklist() {
		return this.wasteAssetDisposalWorklist;
	}

	public void setWasteAssetDisposalWorklist(WasteAssetDisposalWorklist wasteAssetDisposalWorklist) {
		this.wasteAssetDisposalWorklist = wasteAssetDisposalWorklist;
	}

}