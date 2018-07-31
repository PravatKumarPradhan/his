package com.param.billing.global.transaction.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@NamedQueries({
	@NamedQuery(name="GET_ACTIVE_PATIENT_TYPE_FACTOR_BY_TARRIF_ID",
			query="SELECT tarrifPatientCat.serviceTarrifPatientCategoryManagerId as serviceTarrifPatientCategoryManagerId, tarrifPatientCat.serviceTarrifMasterId as serviceTarrifMasterId,"
					+ " tarrifPatientCat.patientCategoryId as patientCategoryId, tarrifPatientCat.multiplicationFactor as multiplicationFactor"
					+ " FROM ServiceTarrifPatientCategoryManager tarrifPatientCat"
					+ " WHERE tarrifPatientCat.status='A' AND tarrifPatientCat.serviceTarrifMasterId =:serviceTarrifMasterId")
})

@Entity
@Table(name="t_service_tarrif_patient_category_manager",schema="service")
@SequenceGenerator(name = "service_tarrif_patient_category_manager_seq", sequenceName = "service.service_tarrif_patient_category_manager_seq", allocationSize = 1)
public class ServiceTarrifPatientCategoryManager {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "service_tarrif_patient_category_manager_seq")
	@Column(name="t_service_tarrif_patient_category_manager_id")
	private Integer	serviceTarrifPatientCategoryManagerId;
	
	@Column(name = "service_tarrif_master_id")
	private Integer serviceTarrifMasterId;
	  
	@Column(name = "patient_category_id")
	private Integer patientCategoryId;
	  
	@Column(name = "multiplication_factor")
	private double multiplicationFactor;
	  
	@Column(name = "unit_id")
	private Integer unitId;
	
	@Column(name = "organisation_id")
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
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "service_tarrif_id" , insertable = false , updatable = false , nullable = false)
	private ServiceTarrifMaster serviceTarrifMaster;
	
	public Integer getServiceTarrifPatientCategoryManagerId() {
		return serviceTarrifPatientCategoryManagerId;
	}

	public void setServiceTarrifPatientCategoryManagerId(Integer serviceTarrifPatientCategoryManagerId) {
		this.serviceTarrifPatientCategoryManagerId = serviceTarrifPatientCategoryManagerId;
	}

	public Integer getServiceTarrifMasterId() {
		return serviceTarrifMasterId;
	}

	public void setServiceTarrifMasterId(Integer serviceTarrifMasterId) {
		this.serviceTarrifMasterId = serviceTarrifMasterId;
	}

	public Integer getPatientCategoryId() {
		return patientCategoryId;
	}

	public void setPatientCategoryId(Integer patientCategoryId) {
		this.patientCategoryId = patientCategoryId;
	}

	public double getMultiplicationFactor() {
		return multiplicationFactor;
	}

	public void setMultiplicationFactor(double multiplicationFactor) {
		this.multiplicationFactor = multiplicationFactor;
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
		this.status = (status == '\u0000') ? 'A' : status;
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

	public ServiceTarrifMaster getServiceTarrifMaster() {
		return serviceTarrifMaster;
	}

	public void setServiceTarrifMaster(ServiceTarrifMaster serviceTarrifMaster) {
		this.serviceTarrifMaster = serviceTarrifMaster;
	}
	
}
