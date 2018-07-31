package com.param.entity.model.procurement;

import java.io.Serializable;
import javax.persistence.*;

import com.param.entity.model.base.BaseEntity;
import com.param.entity.model.master.AssetType;
import com.param.entity.model.master.Status;
import com.param.entity.model.master.Store;
import com.param.entity.model.master.Vendor;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


/**
 * The persistent class for the t_vendor_negociation database table.
 * 
 */
@Entity
@Table(name="t_vendor_negociation", schema = "procurement")
public class VendorNegociation extends BaseEntity implements Serializable  {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "approved_status_id")
	private Status approvalStatus;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "asset_type_id")
	private AssetType assetType;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "qr_id")
	private QuotationRequest quotationRequest;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "qv_id")
	private QuotationVendor quotationVendor;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "status_id")
	private Status status;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "store_id")
	private Store store;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "vendor_id")
	private Vendor vendor;
	
	//bi-directional many-to-one association to TVendorNegociationDetail
	@OneToMany(fetch = FetchType.LAZY, mappedBy="vendorNegociation", cascade = CascadeType.ALL)
	private List<VendorNegociationDetail> vendorNegociationDetailsList;

	public VendorNegociation() {
		super();
		// TODO Auto-generated constructor stub
	}

	public VendorNegociation(Status approvalStatus, AssetType assetType, QuotationRequest quotationRequest,
			QuotationVendor quotationVendor, Status status, Store store, Vendor vendor) {
		super();
		this.approvalStatus = approvalStatus;
		this.assetType = assetType;
		this.quotationRequest = quotationRequest;
		this.quotationVendor = quotationVendor;
		this.status = status;
		this.store = store;
		this.vendor = vendor;
	}

	public Integer getId() {
		return id;
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

	public AssetType getAssetType() {
		return assetType;
	}

	public void setAssetType(AssetType assetType) {
		this.assetType = assetType;
	}

	public QuotationRequest getQuotationRequest() {
		return quotationRequest;
	}

	public void setQuotationRequest(QuotationRequest quotationRequest) {
		this.quotationRequest = quotationRequest;
	}

	public QuotationVendor getQuotationVendor() {
		return quotationVendor;
	}

	public void setQuotationVendor(QuotationVendor quotationVendor) {
		this.quotationVendor = quotationVendor;
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

	public Vendor getVendor() {
		return vendor;
	}

	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}

	public List<VendorNegociationDetail> getVendorNegociationDetailsList() {
		return vendorNegociationDetailsList;
	}

	public void setVendorNegociationDetailsList(List<VendorNegociationDetail> vendorNegociationDetailsList) {
		this.vendorNegociationDetailsList = vendorNegociationDetailsList;
	}
	
	public VendorNegociationDetail addVendorNegociationDetailList(VendorNegociationDetail vendorNegociationDetail) {
		if (getVendorNegociationDetailsList() == null)
			this.vendorNegociationDetailsList = new ArrayList<VendorNegociationDetail>();

		getVendorNegociationDetailsList().add(vendorNegociationDetail);
		vendorNegociationDetail.setVendorNegociation(this);
		return vendorNegociationDetail;
	}
	

	
}