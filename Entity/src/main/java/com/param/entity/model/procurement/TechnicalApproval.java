package com.param.entity.model.procurement;

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
import com.param.entity.model.master.AssetType;
import com.param.entity.model.master.Status;
import com.param.entity.model.master.Store;


/**
 * The persistent class for the t_technical_approval database table.
 * 
 */
@Entity
@Table(name="t_technical_approval", schema = "procurement")
public class TechnicalApproval extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "asset_type_id")
	private AssetType assetType;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "status_id")
	private Status status;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "store_id")
	private Store store;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "qr_id")
	private QuotationRequest quotationRequest;

	private String remark;

	//bi-directional many-to-one association to TTechnicalApprovalDetail
	@OneToMany(fetch = FetchType.LAZY, mappedBy="technicalApproval", cascade = CascadeType.ALL)
	private List<TechnicalApprovalDetail> technicalApprovalDetailsList;
	
	
	public TechnicalApproval() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TechnicalApproval(AssetType assetType, Status status, Store store, QuotationRequest quotationRequest,
			String remark) {
		super();
		this.assetType = assetType;
		this.status = status;
		this.store = store;
		this.quotationRequest = quotationRequest;
		this.remark = remark;
	}



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public AssetType getAssetType() {
		return assetType;
	}

	public void setAssetType(AssetType assetType) {
		this.assetType = assetType;
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

	public QuotationRequest getQuotationRequest() {
		return quotationRequest;
	}

	public void setQuotationRequest(QuotationRequest quotationRequest) {
		this.quotationRequest = quotationRequest;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public List<TechnicalApprovalDetail> getTechnicalApprovalDetailsList() {
		return technicalApprovalDetailsList;
	}

	public void setTechnicalApprovalDetailsList(List<TechnicalApprovalDetail> technicalApprovalDetailsList) {
		this.technicalApprovalDetailsList = technicalApprovalDetailsList;
	}
	
	public TechnicalApprovalDetail addTechnicalApprovalDetailList(TechnicalApprovalDetail technicalApprovalDetail) {
		if (getTechnicalApprovalDetailsList() == null)
			this.technicalApprovalDetailsList = new ArrayList<TechnicalApprovalDetail>();

		getTechnicalApprovalDetailsList().add(technicalApprovalDetail);
		technicalApprovalDetail.setTechnicalApproval(this);

		return technicalApprovalDetail;
	}

	
	
	

	
}