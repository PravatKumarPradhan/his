package com.param.entity.model.inventory;

import java.io.Serializable;
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

@Entity
@Table(name="t_asset_disposal_worklist_request_detail", schema = "inventory")
public class AssetDisposalWorklistRequestDetail extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="scrap_request_detail_id")
	private ScrapRequestDetail scrapRequestDetail;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="asset_disposal_worklist_request_id")
	private AssetDisposalWorklistRequest assetDisposalWorklistRequest;
	
	//bi-directional many-to-one association to TWasteAssetDisposalWorklistDetail
	@OneToMany(fetch = FetchType.LAZY, mappedBy="assetDisposalWorklistRequestDetail", cascade = CascadeType.ALL)
	private List<WasteAssetDisposalWorklistDetail> wasteAssetDisposalWorklistDetails;
	

	public AssetDisposalWorklistRequestDetail() {
	}

	public AssetDisposalWorklistRequestDetail(Integer id) {
		super();
		this.id = id;
	}

	public AssetDisposalWorklistRequestDetail(ScrapRequestDetail scrapRequestDetail) {
		super();
		this.scrapRequestDetail = scrapRequestDetail;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ScrapRequestDetail getScrapRequestDetail() {
		return scrapRequestDetail;
	}

	public void setScrapRequestDetail(ScrapRequestDetail scrapRequestDetail) {
		this.scrapRequestDetail = scrapRequestDetail;
	}

	public AssetDisposalWorklistRequest getAssetDisposalWorklistRequest() {
		return this.assetDisposalWorklistRequest;
	}

	public void setAssetDisposalWorklistRequest(AssetDisposalWorklistRequest assetDisposalWorklistRequest) {
		this.assetDisposalWorklistRequest = assetDisposalWorklistRequest;
	}

	public List<WasteAssetDisposalWorklistDetail> getWasteAssetDisposalWorklistDetails() {
		return this.wasteAssetDisposalWorklistDetails;
	}

	public void setWasteAssetDisposalWorklistDetails(List<WasteAssetDisposalWorklistDetail> wasteAssetDisposalWorklistDetails) {
		this.wasteAssetDisposalWorklistDetails = wasteAssetDisposalWorklistDetails;
	}

	public WasteAssetDisposalWorklistDetail addWasteAssetDisposalWorklistDetail(WasteAssetDisposalWorklistDetail wasteAssetDisposalWorklistDetail) {
		getWasteAssetDisposalWorklistDetails().add(wasteAssetDisposalWorklistDetail);
		wasteAssetDisposalWorklistDetail.setAssetDisposalWorklistRequestDetail(this);

		return wasteAssetDisposalWorklistDetail;
	}

	public WasteAssetDisposalWorklistDetail removeWasteAssetDisposalWorklistDetail(WasteAssetDisposalWorklistDetail wasteAssetDisposalWorklistDetail) {
		getWasteAssetDisposalWorklistDetails().remove(wasteAssetDisposalWorklistDetail);
		wasteAssetDisposalWorklistDetail.setAssetDisposalWorklistRequestDetail(null);

		return wasteAssetDisposalWorklistDetail;
	}

}