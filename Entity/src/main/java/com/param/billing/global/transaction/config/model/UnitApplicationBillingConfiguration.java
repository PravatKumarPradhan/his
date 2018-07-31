package com.param.billing.global.transaction.config.model;

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

import com.param.billing.global.transaction.model.TariffMaster;

@NamedQueries({
	
	@NamedQuery(name="GET_DEFAULT_SELF_TARIFF_FROM_APP_BILLING_CONF",query="SELECT uabc.unitApplicationBillingConfigurationId as unitApplicationBillingConfigurationId, "
			+ "uabc.organizationId as organizationId,"
			+ "uabc.unitId as unitId, "
			+ "uabc.defaultSelfTariffId as defaultSelfTariffId, "
			+ "uabc.isPreOrPostBilling as isPreOrPostBilling "
			+ "FROM UnitApplicationBillingConfiguration uabc "
			+ "WHERE uabc.organizationId=:organizationId "
			+ "AND uabc.unitId=:unitId "
			+ "AND uabc.status='A' ")
})



@Entity
@Table(name="t_unit_application_billing_configuration",schema="public")
@SequenceGenerator(name="unit_application_billing_configuration_seq",sequenceName="public.unit_application_billing_configuration_seq",allocationSize=1)
public class UnitApplicationBillingConfiguration {

	@Id
	@Column(name=" unit_application_billing_configuration_id")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="unit_application_billing_configuration_seq")
	private Integer  unitApplicationBillingConfigurationId;
	
	@Column(name="organization_id")
	private Integer organizationId;
	
	@Column(name="unit_id")
	private Integer unitId;
	
	@Column(name="default_self_tariff_id")
	private Integer defaultSelfTariffId;
	
	@Column(name="is_pre_or_post_billing")
	private Character isPreOrPostBilling;
	
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
	@JoinColumn(name = "default_self_tariff_id", insertable = false, nullable = false, updatable = false)
	private TariffMaster tariffMaster;
	

	public Integer getUnitApplicationBillingConfigurationId() {
		return unitApplicationBillingConfigurationId;
	}

	public void setUnitApplicationBillingConfigurationId(Integer unitApplicationBillingConfigurationId) {
		this.unitApplicationBillingConfigurationId = unitApplicationBillingConfigurationId;
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

	public Integer getDefaultSelfTariffId() {
		return defaultSelfTariffId;
	}

	public void setDefaultSelfTariffId(Integer defaultSelfTariffId) {
		this.defaultSelfTariffId = defaultSelfTariffId;
	}

	public Character getIsPreOrPostBilling() {
		return isPreOrPostBilling;
	}

	public void setIsPreOrPostBilling(Character isPreOrPostBilling) {
		this.isPreOrPostBilling = isPreOrPostBilling;
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
/*DROP TABLE public.t_unit_application_billing_configuration;

CREATE TABLE public.t_unit_application_billing_configuration
(
  unit_application_billing_configuration_id integer NOT NULL DEFAULT nextval('unit_application_billing_configuration_seq'::regclass),
  organization_id integer,
  unit_id integer,	
  default_self_tariff_id integer,
  is_pre_or_post_billing character(1) DEFAULT 'N'::bpchar,
  created_date timestamp without time zone DEFAULT now(),
  created_by integer,
  updated_date timestamp without time zone DEFAULT now(),
  updated_by integer,
  status character(1) DEFAULT 'A'::bpchar,
  CONSTRAINT t_unit_application_billing_configuration_pkey PRIMARY KEY (unit_application_billing_configuration_id),
  CONSTRAINT t_unit_application_billing_configur_default_self_tariff_id_fkey FOREIGN KEY (default_self_tariff_id)
      REFERENCES service.m_tariff_master (tariff_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)*/