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
	@NamedQuery(name="GET_ACTIVE_BET_TYPE_FACTOR_BY_TARRIF_ID",
			query="SELECT tarrifBed.tarrifBedTypeManagerId as tarrifBedTypeManagerId, tarrifBed.serviceTarrifId as serviceTarrifId, tarrifBed.bedTypeId as bedTypeId, tarrifBed.multiplicationFactor as multiplicationFactor"
					+ " FROM ServiceTarrifBedtypeManager tarrifBed"
					+ " JOIN tarrifBed.serviceTarrifMaster servTarrifMst"
					+ " WHERE tarrifBed.status='A' AND tarrifBed.serviceTarrifId =:serviceTarrifId")
})

@Entity
@Table(name="t_service_tarrif_bed_type_manager",schema="service")
@SequenceGenerator(name = "service_tarrif_bed_type_manager_seq", sequenceName = "service.service_tarrif_bed_type_manager_seq", allocationSize = 1)
public class ServiceTarrifBedtypeManager {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "service_tarrif_bed_type_manager_seq")
	@Column(name="tarrif_bed_type_manager_id")
	private Integer tarrifBedTypeManagerId;
	
	@Column(name = "service_tarrif_id")
	private Integer serviceTarrifId;
	  
	@Column(name = "bed_type_id")
	private Integer bedTypeId;
	  
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

	/*@Column(name = "updated_by") 
	private Integer updatedBy;*/
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "service_tarrif_id" , insertable = false , updatable = false , nullable = false)
	private ServiceTarrifMaster serviceTarrifMaster;
	
	public Integer getTarrifBedTypeManagerId() {
		return tarrifBedTypeManagerId;
	}

	public void setTarrifBedTypeManagerId(Integer tarrifBedTypeManagerId) {
		this.tarrifBedTypeManagerId = tarrifBedTypeManagerId;
	}

	public Integer getServiceTarrifId() {
		return serviceTarrifId;
	}

	public void setServiceTarrifId(Integer serviceTarrifId) {
		this.serviceTarrifId = serviceTarrifId;
	}

	public Integer getBedTypeId() {
		return bedTypeId;
	}

	public void setBedTypeId(Integer bedTypeId) {
		this.bedTypeId = bedTypeId;
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

	/*public Integer getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}*/

	public ServiceTarrifMaster getServiceTarrifMaster() {
		return serviceTarrifMaster;
	}

	public void setServiceTarrifMaster(ServiceTarrifMaster serviceTarrifMaster) {
		this.serviceTarrifMaster = serviceTarrifMaster;
	}

	
	
}
