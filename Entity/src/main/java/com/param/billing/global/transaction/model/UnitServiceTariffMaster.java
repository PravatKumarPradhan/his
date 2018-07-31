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
import com.param.global.model.BedCategoryMaster;
import com.param.global.model.PatientCategoryMaster;
import com.param.global.model.ServiceMaster;
import com.param.global.model.VisitTypeMaster;

@NamedQueries({
	
	@NamedQuery(name="GET_BASE_PRICE_OF_SERVICE_FROM_TARIFF",query="SELECT ustm.unitServiceTriffId as unitServiceTriffId, "
			+ "ustm.rate as rate " 
			+ "FROM UnitServiceTariffMaster ustm "
			+ "WHERE ustm.serviceId=:serviceId "
			+ "AND ustm.tariffId=:tariffId "
			+ "AND ustm.paymentEntitlementId=:paymentEntitlementId "
			+ "AND ustm.visitTypeId=:visitTypeId "
			+ "AND ustm.patientTypeId=:patientTypeId "
			+ "AND ustm.billingBedCategoryId=:billingBedCategoryId "
			+ "AND ustm.organizationId=:organizationId "
			+ "AND ustm.unitId=:unitId "
			+ "AND ustm.effectiveDate<:orderDate ")
	
})


@Entity
@Table(name="m_unit_service_tariff_master",schema="service")
@SequenceGenerator(name="unit_service_tariff_master_seq",sequenceName="service.unit_service_tariff_master_seq",allocationSize=1)
public class UnitServiceTariffMaster {

	@Id
	@Column(name="unit_service_tariff_id")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="unit_service_tariff_master_seq")
	private Integer unitServiceTriffId;
	
	@Column(name="organization_id")
	private Integer organizationId;
	
	@Column(name="unit_id")
	private Integer unitId;
	
	@Column(name="service_id")
	private Integer serviceId;
	
	@Column(name="tariff_id")
	private Integer tariffId;
	
	@Column(name="payment_entitlement_id")
	private Integer paymentEntitlementId;
	
	@Column(name="visit_type_id")
	private Integer visitTypeId;
	
	@Column(name="patient_type_id")
	private Integer patientTypeId;
	
	@Column(name="billing_bed_category_id")
	private Integer billingBedCategoryId;
	
	@Column(name="rate")
	private Double rate;
	
	@Column(name="stat_per")
	private Double statPer;
	
	@Column(name="effective_date")
	private Date effectiveDate;
	
	@Column(name="created_date")
	private Date createdDate;
	
	@Column(name="created_by")
	private Integer createdBy;
	
	@Column(name="updated_date")
	private Date updatedDate;
	
	@Column(name="updated_by")
	private Integer updatedBy;
	
	@Column(name="status")
	private Character status;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "service_id", insertable = false, nullable = false, updatable = false)
	private ServiceMaster serviceMaster;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tariff_id", insertable = false, nullable = false, updatable = false)
	private TariffMaster tariffMaster;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "visit_type_id", insertable = false, nullable = false, updatable = false)
	private VisitTypeMaster visitTypeMaster;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "patient_type_id", insertable = false, nullable = false, updatable = false)
	private PatientCategoryMaster patientCategoryMaster;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "billing_bed_category_id", insertable = false, nullable = false, updatable = false)
	private BedCategoryMaster bedCategoryMaster;
	

	public Integer getUnitServiceTriffId() {
		return unitServiceTriffId;
	}

	public void setUnitServiceTriffId(Integer unitServiceTriffId) {
		this.unitServiceTriffId = unitServiceTriffId;
	}

	public Integer getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}

	public Integer getUnitId() {
		return unitId;
	}

	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}

	public Integer getServiceId() {
		return serviceId;
	}

	public void setServiceId(Integer serviceId) {
		this.serviceId = serviceId;
	}

	public Integer getTariffId() {
		return tariffId;
	}

	public void setTariffId(Integer tariffId) {
		this.tariffId = tariffId;
	}

	public Integer getPaymentEntitlementId() {
		return paymentEntitlementId;
	}

	public void setPaymentEntitlementId(Integer paymentEntitlementId) {
		this.paymentEntitlementId = paymentEntitlementId;
	}

	public Integer getVisitTypeId() {
		return visitTypeId;
	}

	public void setVisitTypeId(Integer visitTypeId) {
		this.visitTypeId = visitTypeId;
	}

	public Integer getPatientTypeId() {
		return patientTypeId;
	}

	public void setPatientTypeId(Integer patientTypeId) {
		this.patientTypeId = patientTypeId;
	}

	public Integer getBillingBedCategoryId() {
		return billingBedCategoryId;
	}

	public void setBillingBedCategoryId(Integer billingBedCategoryId) {
		this.billingBedCategoryId = billingBedCategoryId;
	}

	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

	public Double getStatPer() {
		return statPer;
	}

	public void setStatPer(Double statPer) {
		this.statPer = statPer;
	}

	public Date getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
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

	public Character getStatus() {
		return status;
	}

	public void setStatus(Character status) {
		this.status = status;
	}
	
}


/*CREATE TABLE service.m_unit_service_tariff_master
(
  unit_service_tariff_id integer NOT NULL DEFAULT nextval('service.unit_service_tariff_master_seq'::regclass),
  organization_id integer,
  unit_id integer,
  service_id integer,
  tariff_id integer ,
  payment_entitlement_id integer, 	
  visit_type_id integer,
  patient_type_id integer,
  billing_bed_category_id integer,
  rate double precision,
  stat_per double precision,
  effective_date timestamp without time zone,
  created_by integer,
  created_date timestamp without time zone DEFAULT now(),
  status character(1) DEFAULT 'A'::"char",
  updated_by integer,
  updated_date timestamp without time zone DEFAULT now(),
  CONSTRAINT pk_unit_service_tariff_id PRIMARY KEY (unit_service_tariff_id)
)*/