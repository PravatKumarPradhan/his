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
import com.param.entity.model.master.AssetType;
import com.param.entity.model.master.CancelReason;
import com.param.entity.model.master.Status;
import com.param.entity.model.master.Store;
import com.param.entity.model.master.User;

@Entity(name = "com.param.entity.model.inventory.ScrapRequest")
@Table(name = "t_scrap_request", schema = "inventory")
public class ScrapRequest extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "approval_status_id", nullable = false)
	private Status approvalStatus;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "approved_by")
	protected User approvedBy;

	@Column(name = "approved_on")
	private Date approvedOn;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "asset_type_id")
	private AssetType assetType;

	@Column(name = "near_by_expiry")
	private Boolean nearByExpiry;

	@Column(length = 500)
	private String remark;

	@Column(name = "request_no")
	private String requestNo;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "status_id", nullable = false)
	private Status status;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "store_id", nullable = false)
	private Store store;

	@Column(name = "cancel_note", length = 100)
	private String cancelNote;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cancel_reason_id")
	private CancelReason cancelReason;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "scrapRequest", cascade = CascadeType.ALL)
	private List<ScrapRequestDetail> scrapRequestDetails;

	public ScrapRequest() {
	}

	public ScrapRequest(Integer id) {
		super();
		this.id = id;
	}

	public ScrapRequest(Status approvalStatus, AssetType assetType, Boolean nearByExpiry, String remark,
			String requestNo, Status status, Store store) {
		super();
		this.approvalStatus = approvalStatus;
		this.assetType = assetType;
		this.nearByExpiry = nearByExpiry;
		this.remark = remark;
		this.requestNo = requestNo;
		this.status = status;
		this.store = store;
	}

	public void updateScrapRequest(String remark) {
		this.remark = remark;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Status getApprovalStatus() {
		return approvalStatus;
	}

	public void setApprovalStatus(Status approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	public User getApprovedBy() {
		return approvedBy;
	}

	public void setApprovedBy(User approvedBy) {
		this.approvedBy = approvedBy;
	}

	public Date getApprovedOn() {
		return approvedOn;
	}

	public void setApprovedOn(Date approvedOn) {
		this.approvedOn = approvedOn;
	}

	public AssetType getAssetType() {
		return assetType;
	}

	public void setAssetType(AssetType assetType) {
		this.assetType = assetType;
	}

	public Boolean getNearByExpiry() {
		return nearByExpiry;
	}

	public void setNearByExpiry(Boolean nearByExpiry) {
		this.nearByExpiry = nearByExpiry;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getRequestNo() {
		return requestNo;
	}

	public void setRequestNo(String requestNo) {
		this.requestNo = requestNo;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	public String getCancelNote() {
		return cancelNote;
	}

	public void setCancelNote(String cancelNote) {
		this.cancelNote = cancelNote;
	}

	public CancelReason getCancelReason() {
		return cancelReason;
	}

	public void setCancelReason(CancelReason cancelReason) {
		this.cancelReason = cancelReason;
	}

	public List<ScrapRequestDetail> getScrapRequestDetails() {
		return scrapRequestDetails;
	}

	public void setScrapRequestDetails(List<ScrapRequestDetail> scrapRequestDetails) {
		this.scrapRequestDetails = scrapRequestDetails;
	}

	public ScrapRequestDetail addScrapRequestDetail(ScrapRequestDetail scrapRequestDetail) {
		if (getScrapRequestDetails() == null)
			this.scrapRequestDetails = new ArrayList<ScrapRequestDetail>();

		getScrapRequestDetails().add(scrapRequestDetail);
		scrapRequestDetail.setScrapRequest(this);

		return scrapRequestDetail;
	}

	public ScrapRequestDetail removeScrapRequestDetail(ScrapRequestDetail scrapRequestDetail) {
		getScrapRequestDetails().remove(scrapRequestDetail);
		scrapRequestDetail.setScrapRequest(null);

		return scrapRequestDetail;
	}

}