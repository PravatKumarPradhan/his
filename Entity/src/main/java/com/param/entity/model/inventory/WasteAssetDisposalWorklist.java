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

@Entity(name = "com.param.entity.model.inventory.WasteAssetDisposalWorklist")
@Table(name = "t_waste_asset_disposal_worklist", schema = "inventory")
public class WasteAssetDisposalWorklist extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "status_id", nullable = false)
	private Status status;

	@ManyToOne
	@JoinColumn(name = "asset_disposal_worklist_request_id")
	private AssetDisposalWorklistRequest assetDisposalWorklistRequest;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "wasteAssetDisposalWorklist", cascade = CascadeType.ALL)
	private List<WasteAssetDisposalWorklistDetail> wasteAssetDisposalWorklistDetails;

	public WasteAssetDisposalWorklist() {
		
	}

	/*public WasteAssetDisposalWorklist(Status status) {
		super();
		this.status = status;
	}*/

	public WasteAssetDisposalWorklist(Status status, AssetDisposalWorklistRequest assetDisposalWorklistRequest) {
		super();
		this.status = status;
		this.assetDisposalWorklistRequest = assetDisposalWorklistRequest;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public AssetDisposalWorklistRequest getAssetDisposalWorklistRequest() {
		return assetDisposalWorklistRequest;
	}

	public AssetDisposalWorklistRequest getTAssetDisposalWorklistRequest() {
		return this.assetDisposalWorklistRequest;
	}

	public void setAssetDisposalWorklistRequest(AssetDisposalWorklistRequest assetDisposalWorklistRequest) {
		this.assetDisposalWorklistRequest = assetDisposalWorklistRequest;
	}

	public List<WasteAssetDisposalWorklistDetail> getWasteAssetDisposalWorklistDetails() {
		return this.wasteAssetDisposalWorklistDetails;
	}

	public void setWasteAssetDisposalWorklistDetails(
			List<WasteAssetDisposalWorklistDetail> wasteAssetDisposalWorklistDetails) {
		this.wasteAssetDisposalWorklistDetails = wasteAssetDisposalWorklistDetails;
	}

	public WasteAssetDisposalWorklistDetail addWasteAssetDisposalWorklistDetail(
			WasteAssetDisposalWorklistDetail wasteAssetDisposalWorklistDetail) {
		if (getWasteAssetDisposalWorklistDetails() == null)
			this.wasteAssetDisposalWorklistDetails = new ArrayList<WasteAssetDisposalWorklistDetail>();

		getWasteAssetDisposalWorklistDetails().add(wasteAssetDisposalWorklistDetail);
		wasteAssetDisposalWorklistDetail.setWasteAssetDisposalWorklist(this);

		return wasteAssetDisposalWorklistDetail;
	}

	public WasteAssetDisposalWorklistDetail removeTWasteAssetDisposalWorklistDetail(
			WasteAssetDisposalWorklistDetail wasteAssetDisposalWorklistDetail) {
		getWasteAssetDisposalWorklistDetails().remove(wasteAssetDisposalWorklistDetail);
		wasteAssetDisposalWorklistDetail.setWasteAssetDisposalWorklist(null);

		return wasteAssetDisposalWorklistDetail;
	}

}
