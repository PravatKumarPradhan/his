package com.param.billing.packages.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="t_corporate_contract_master", schema="billing")
@SequenceGenerator(name = "corporate_contract_master_seq", sequenceName = "billing.corporate_contract_master_seq", allocationSize = 1)
public class CorporateContractMaster {
	@Id  
	@Column(name = "corporate_contract_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "corporate_contract_master_seq")
	private int corporateContractId;
	  
	@Column(name = "corporate_id")
	private Integer corporateId;
	  
	@Column(name = "contract_type_id")
	private  Integer contractTypeId;
	  
	@Column(name = "visit_type_id")
	private Integer visitTypeId;
	  
	@Column(name = "bed_category_id")
	private Integer bedCategoryId;
	  
	@Column(name = "patient_type_id")
	private Integer patientTypeId;
	  
	@Column(name = "discount_in_percentage")
	private double  discountInPercentage;
	  
	@Column(name = "contract_price")
	private double contractPrice;
	  
	@Column(name = "contract_cost")
	private double contractCost;
	  
	@Column(name = "validity_date_from")
	private Date validityDateFrom;
	  
	@Column(name = "validity_date_to")
	private Date validityDateTo;

	@Column(name = "unit_id")
	private Integer unitId;

	@Column(name = "orgnisation_id")
	private Integer orgnisationId;

	@Column(name = "status")
	private char status;

	@Column(name = "created_date")
	private Date createdDate;

	@Column(name = "created_by")
	private Integer createdBy;

	@Column(name = "updated_date")
	private Date updatedDate;

	@Column(name = "updated_by")
	private Integer updatedBy;

	public int getCorporateContractId() {
		return corporateContractId;
	}

	public void setCorporateContractId(int corporateContractId) {
		this.corporateContractId = corporateContractId;
	}

	public Integer getCorporateId() {
		return corporateId;
	}

	public void setCorporateId(Integer corporateId) {
		this.corporateId = corporateId;
	}

	public Integer getContractTypeId() {
		return contractTypeId;
	}

	public void setContractTypeId(Integer contractTypeId) {
		this.contractTypeId = contractTypeId;
	}

	public Integer getVisitTypeId() {
		return visitTypeId;
	}

	public void setVisitTypeId(Integer visitTypeId) {
		this.visitTypeId = visitTypeId;
	}

	public Integer getBedCategoryId() {
		return bedCategoryId;
	}

	public void setBedCategoryId(Integer bedCategoryId) {
		this.bedCategoryId = bedCategoryId;
	}

	public Integer getPatientTypeId() {
		return patientTypeId;
	}

	public void setPatientTypeId(Integer patientTypeId) {
		this.patientTypeId = patientTypeId;
	}

	public double getDiscountInPercentage() {
		return discountInPercentage;
	}

	public void setDiscountInPercentage(double discountInPercentage) {
		this.discountInPercentage = discountInPercentage;
	}

	public double getContractPrice() {
		return contractPrice;
	}

	public void setContractPrice(double contractPrice) {
		this.contractPrice = contractPrice;
	}

	public double getContractCost() {
		return contractCost;
	}

	public void setContractCost(double contractCost) {
		this.contractCost = contractCost;
	}

	public Date getValidityDateFrom() {
		return validityDateFrom;
	}

	public void setValidityDateFrom(Date validityDateFrom) {
		this.validityDateFrom = validityDateFrom;
	}

	public Date getValidityDateTo() {
		return validityDateTo;
	}

	public void setValidityDateTo(Date validityDateTo) {
		this.validityDateTo = validityDateTo;
	}

	public Integer getUnitId() {
		return unitId;
	}

	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}

	public Integer getOrgnisationId() {
		return orgnisationId;
	}

	public void setOrgnisationId(Integer orgnisationId) {
		this.orgnisationId = orgnisationId;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
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

}
