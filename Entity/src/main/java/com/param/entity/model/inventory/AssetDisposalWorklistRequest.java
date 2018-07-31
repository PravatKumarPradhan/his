package com.param.entity.model.inventory;

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
import com.param.entity.model.master.Status;
import com.param.entity.model.master.User;

@Entity(name = "com.param.entity.model.inventory.AssetDisposalWorklistRequest")
@Table(name="t_asset_disposal_worklist_request", schema = "inventory")
public class AssetDisposalWorklistRequest extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "scrap_request_id")
	private ScrapRequest scrapRequest;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "status_id", nullable = false)
	private Status status;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "witness_id", nullable = false)
	private User witness;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "assetDisposalWorklistRequest", cascade = CascadeType.ALL)
	private List<AssetDisposalWorklistRequestDetail> assetDisposalWorklistRequestDetails;

	//bi-directional many-to-one association to WasteAssetDisposalWorklist
	@OneToMany(fetch = FetchType.LAZY, mappedBy="assetDisposalWorklistRequest", cascade = CascadeType.ALL)
	private List<WasteAssetDisposalWorklist> wasteAssetDisposalWorklists;

	public AssetDisposalWorklistRequest() {
		
	}

	public AssetDisposalWorklistRequest(Integer id) {
		super();
		this.id = id;
	}

	public AssetDisposalWorklistRequest(ScrapRequest scrapRequest, Status status, User witness) {
		super();
		this.scrapRequest = scrapRequest;
		this.status = status;
		this.witness = witness;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ScrapRequest getScrapRequest() {
		return scrapRequest;
	}

	public void setScrapRequest(ScrapRequest scrapRequest) {
		this.scrapRequest = scrapRequest;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public User getWitness() {
		return witness;
	}

	public void setWitness(User witness) {
		this.witness = witness;
	}

	public List<AssetDisposalWorklistRequestDetail> getAssetDisposalWorklistRequestDetails() {
		return assetDisposalWorklistRequestDetails;
	}

	public void setAssetDisposalWorklistRequestDetails(
			List<AssetDisposalWorklistRequestDetail> assetDisposalWorklistRequestDetails) {
		this.assetDisposalWorklistRequestDetails = assetDisposalWorklistRequestDetails;
	}

	public List<AssetDisposalWorklistRequestDetail> getTAssetDisposalWorklistRequestDetails() {
		return this.assetDisposalWorklistRequestDetails;
	}

	public void setTAssetDisposalWorklistRequestDetails(List<AssetDisposalWorklistRequestDetail> assetDisposalWorklistRequestDetails) {
		this.assetDisposalWorklistRequestDetails = assetDisposalWorklistRequestDetails;
	}

	public AssetDisposalWorklistRequestDetail addAssetDisposalWorklistRequestDetail(AssetDisposalWorklistRequestDetail assetDisposalWorklistRequestDetail) {
		if (getAssetDisposalWorklistRequestDetails() == null)
			this.assetDisposalWorklistRequestDetails = new ArrayList<AssetDisposalWorklistRequestDetail>();
		
		getAssetDisposalWorklistRequestDetails().add(assetDisposalWorklistRequestDetail);
		assetDisposalWorklistRequestDetail.setAssetDisposalWorklistRequest(this);

		return assetDisposalWorklistRequestDetail;
	}

	public AssetDisposalWorklistRequestDetail removeTAssetDisposalWorklistRequestDetail(AssetDisposalWorklistRequestDetail assetDisposalWorklistRequestDetail) {
		getTAssetDisposalWorklistRequestDetails().remove(assetDisposalWorklistRequestDetail);
		assetDisposalWorklistRequestDetail.setAssetDisposalWorklistRequest(null);

		return assetDisposalWorklistRequestDetail;
	}

	public List<WasteAssetDisposalWorklist> getWasteAssetDisposalWorklists() {
		return this.wasteAssetDisposalWorklists;
	}

	public void setWasteAssetDisposalWorklists(List<WasteAssetDisposalWorklist> wasteAssetDisposalWorklists) {
		this.wasteAssetDisposalWorklists = wasteAssetDisposalWorklists;
	}

	public WasteAssetDisposalWorklist addTWasteAssetDisposalWorklist(WasteAssetDisposalWorklist wasteAssetDisposalWorklist) {
		getWasteAssetDisposalWorklists().add(wasteAssetDisposalWorklist);
		wasteAssetDisposalWorklist.setAssetDisposalWorklistRequest(this);

		return wasteAssetDisposalWorklist;
	}

	public WasteAssetDisposalWorklist removeTWasteAssetDisposalWorklist(WasteAssetDisposalWorklist wasteAssetDisposalWorklist) {
		getWasteAssetDisposalWorklists().remove(wasteAssetDisposalWorklist);
		wasteAssetDisposalWorklist.setAssetDisposalWorklistRequest(null);

		return wasteAssetDisposalWorklist;
	}

}