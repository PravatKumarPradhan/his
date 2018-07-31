package com.param.service.global.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.param.global.model.ServiceMaster;

@Entity
@Table(name = "t_contract_service_details" , schema = "service")
@SequenceGenerator(name = "t_contract_service_details_seq" , sequenceName = "service.t_contract_service_details_seq" , allocationSize = 1)
public class TContractServiceDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "t_contract_service_details_seq")
	@Column(name = "contract_service_detail_id")
	private int contractServiceDetailId;
	
	@Column(name = "contract_id")
	private Integer contractId;
	
	@Column(name = "contract_group_detail_id")
	private Integer contractGroupDetailId;
	
	@Column(name = "service_id")
	private Integer serviceId;
	
	@Column(name="service_price")
	private Double servicePrice;
	
	@Column(name="final_price")
	private Double finalPrice;
	
	@Column(name="apportioned_price")
	private Double apportionedPrice;
	
	@Column(name = "created_date")
	private Date createdDate;
	
	@Column(name = "created_by")
	private Integer createdBy;
	
	@Column(name = "updated_date")
	private Date updatedDate;
	
	@Column(name = "updated_by")
	private Integer updatedBy;
	
	@Column(name = "org_id")
	private Integer orgId;
	
	@Column(name = "unit_id")
	private Integer unitId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name  = "contract_id" , insertable = false , updatable = false , nullable = false)
	private MCompanyContractMaster mCompanyContractMaster;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name  = "service_id" , insertable = false , updatable = false , nullable = false)
	private ServiceMaster serviceMaster;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name  = "contract_group_detail_id" , insertable = false , updatable = false , nullable = false)
	private TContractGroupDetails tContractGroupDetails;

	public int getContractServiceDetailId() {
		return contractServiceDetailId;
	}

	public void setContractServiceDetailId(int contractServiceDetailId) {
		this.contractServiceDetailId = contractServiceDetailId;
	}

	public Integer getContractId() {
		return contractId;
	}

	public void setContractId(Integer contractId) {
		this.contractId = contractId;
	}

	public Integer getContractGroupDetailId() {
		return contractGroupDetailId;
	}

	public void setContractGroupDetailId(Integer contractGroupDetailId) {
		this.contractGroupDetailId = contractGroupDetailId;
	}

	public Integer getServiceId() {
		return serviceId;
	}

	public void setServiceId(Integer serviceId) {
		this.serviceId = serviceId;
	}

	public Double getServicePrice() {
		return servicePrice;
	}

	public void setServicePrice(Double servicePrice) {
		this.servicePrice = servicePrice;
	}

	public Double getFinalPrice() {
		return finalPrice;
	}

	public void setFinalPrice(Double finalPrice) {
		this.finalPrice = finalPrice;
	}

	public Double getApportionedPrice() {
		return apportionedPrice;
	}

	public void setApportionedPrice(Double apportionedPrice) {
		this.apportionedPrice = apportionedPrice;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Integer getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public Integer getUnitId() {
		return unitId;
	}

	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}

	public MCompanyContractMaster getmCompanyContractMaster() {
		return mCompanyContractMaster;
	}

	public void setmCompanyContractMaster(MCompanyContractMaster mCompanyContractMaster) {
		this.mCompanyContractMaster = mCompanyContractMaster;
	}

	public ServiceMaster getServiceMaster() {
		return serviceMaster;
	}

	public void setServiceMaster(ServiceMaster serviceMaster) {
		this.serviceMaster = serviceMaster;
	}

	public TContractGroupDetails gettContractGroupDetails() {
		return tContractGroupDetails;
	}

	public void settContractGroupDetails(TContractGroupDetails tContractGroupDetails) {
		this.tContractGroupDetails = tContractGroupDetails;
	}
}