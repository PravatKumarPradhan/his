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
@Table(name="t_corporate_contract_service_details", schema="billing")
@SequenceGenerator(name = "corporate_contract_service_details_seq", sequenceName = "billing.corporate_contract_service_details_seq", allocationSize = 1)
public class CorporateContractServiceDetails {
	@Id
	@Column(name = "corporate_contract_details_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "corporate_contract_service_details_seq")
	private int corporateContractDetailsId;
	
	@Column(name = "corporate_contract_id")
	private Integer corporateContractId;
	  
	@Column(name = "service_id")
	private Integer serviceId;
	  
	@Column(name = "service_group_id")
	private Integer serviceGroupId;
	  
	@Column(name = "number_to_be_use")
	private int  numberToBeUse;
	  
	@Column(name = "service_price")
	private double servicePrice;
	  
	@Column(name = "is_cap_applicable")
	private char isCapApplicable;
	  
	@Column(name = "cap_amount")
	private double capAmount;
	  
	@Column(name = "apportioned_price")
	private double apportionedPrice;
	  
	@Column(name = "discount_in_percentage")
	private double discountInPercentage;
	  
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

	public int getCorporateContractDetailsId() {
		return corporateContractDetailsId;
	}

	public void setCorporateContractDetailsId(int corporateContractDetailsId) {
		this.corporateContractDetailsId = corporateContractDetailsId;
	}

	public Integer getCorporateContractId() {
		return corporateContractId;
	}

	public void setCorporateContractId(Integer corporateContractId) {
		this.corporateContractId = corporateContractId;
	}

	public Integer getServiceId() {
		return serviceId;
	}

	public void setServiceId(Integer serviceId) {
		this.serviceId = serviceId;
	}

	public Integer getServiceGroupId() {
		return serviceGroupId;
	}

	public void setServiceGroupId(Integer serviceGroupId) {
		this.serviceGroupId = serviceGroupId;
	}

	public int getNumberToBeUse() {
		return numberToBeUse;
	}

	public void setNumberToBeUse(int numberToBeUse) {
		this.numberToBeUse = numberToBeUse;
	}

	public double getServicePrice() {
		return servicePrice;
	}

	public void setServicePrice(double servicePrice) {
		this.servicePrice = servicePrice;
	}

	public char getIsCapApplicable() {
		return isCapApplicable;
	}

	public void setIsCapApplicable(char isCapApplicable) {
		this.isCapApplicable = isCapApplicable;
	}

	public double getCapAmount() {
		return capAmount;
	}

	public void setCapAmount(double capAmount) {
		this.capAmount = capAmount;
	}

	public double getApportionedPrice() {
		return apportionedPrice;
	}

	public void setApportionedPrice(double apportionedPrice) {
		this.apportionedPrice = apportionedPrice;
	}

	public double getDiscountInPercentage() {
		return discountInPercentage;
	}

	public void setDiscountInPercentage(double discountInPercentage) {
		this.discountInPercentage = discountInPercentage;
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
