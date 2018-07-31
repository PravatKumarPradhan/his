package com.param.global.model;

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

import com.param.adt.admission.model.Admission;
import com.param.adt.master.global.model.ReferralMaster;
import com.param.adt.master.global.model.ReferralTypeMaster;
import com.param.opd.coversheet.model.EncounterMaster;

@Entity
@Table(name="t_referal_details",schema="public")
@SequenceGenerator(name="referal_details_seq",sequenceName="public.referal_details_seq",allocationSize=1)
public class ReferalDetails {
	
	@Id
	@Column(name="referal_details_id")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="referal_details_seq")
	private Integer referalDetailsId;
	
	@Column(name="organization_id")
	private Integer organizationId;
	
	@Column(name="unit_id")
	private Integer unitId;
	
	@Column(name="visit_type_id")
	private Integer visitTypeId;
	
	@Column(name="admission_id")
	private Integer admissionId;
	
	@Column(name="encounter_id")
	private Integer encounterId;
	
	@Column(name="referral_type_id")
	private Integer referralTypeId;
	
	@Column(name="referral_id")
	private Integer referralId;
	
	@Column(name="referral_remark")
	private String referralRemark;
	
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
	@JoinColumn(name = "visit_type_id", insertable = false, nullable = false, updatable = false)
	private VisitTypeMaster visitTypeMaster;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "referral_type_id", insertable = false, nullable = false, updatable = false)
	private ReferralTypeMaster referralTypeMaster;  
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "referral_id", insertable = false, nullable = false, updatable = false)
	private ReferralMaster referralMaster;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "encounter_id", insertable = false, nullable = false, updatable = false)
	private EncounterMaster encounterMaster;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "admission_id", insertable = false, nullable = false, updatable = false)
	private Admission admission;

	public Integer getVisitTypeId() {
		return visitTypeId;
	}

	public void setVisitTypeId(Integer visitTypeId) {
		this.visitTypeId = visitTypeId;
	}

	public Integer getReferalDetailsId() {
		return referalDetailsId;
	}

	public void setReferalDetailsId(Integer referalDetailsId) {
		this.referalDetailsId = referalDetailsId;
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

	public Integer getAdmissionId() {
		return admissionId;
	}

	public void setAdmissionId(Integer admissionId) {
		this.admissionId = admissionId;
	}

	public Integer getEncounterId() {
		return encounterId;
	}

	public void setEncounterId(Integer encounterId) {
		this.encounterId = encounterId;
	}

	public VisitTypeMaster getVisitTypeMaster() {
		return visitTypeMaster;
	}

	public void setVisitTypeMaster(VisitTypeMaster visitTypeMaster) {
		this.visitTypeMaster = visitTypeMaster;
	}

	public ReferralTypeMaster getReferralTypeMaster() {
		return referralTypeMaster;
	}

	public void setReferralTypeMaster(ReferralTypeMaster referralTypeMaster) {
		this.referralTypeMaster = referralTypeMaster;
	}

	public ReferralMaster getReferralMaster() {
		return referralMaster;
	}

	public void setReferralMaster(ReferralMaster referralMaster) {
		this.referralMaster = referralMaster;
	}

	public EncounterMaster getEncounterMaster() {
		return encounterMaster;
	}

	public void setEncounterMaster(EncounterMaster encounterMaster) {
		this.encounterMaster = encounterMaster;
	}

	public Admission getAdmission() {
		return admission;
	}

	public void setAdmission(Admission admission) {
		this.admission = admission;
	}

	public Integer getReferralTypeId() {
		return referralTypeId;
	}

	public void setReferralTypeId(Integer referralTypeId) {
		this.referralTypeId = referralTypeId;
	}

	public Integer getReferralId() {
		return referralId;
	}

	public void setReferralId(Integer referralId) {
		this.referralId = referralId;
	}

	public String getReferralRemark() {
		return referralRemark;
	}

	public void setReferralRemark(String referralRemark) {
		this.referralRemark = referralRemark;
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

/*DROP TABLE public.t_referal_details;

CREATE TABLE public.t_referal_details
(
  referal_details_id integer NOT NULL DEFAULT nextval('referal_details_seq'::regclass),
  organizatoin_id integer,
  unit_id integer,
  visit_type_id integer,
  visit_adm_id integer,
  referral_type_id integer,
  referral_id integer,
  referral_remark character varying(200),
  created_date timestamp without time zone DEFAULT now(),
  created_by integer,
  updated_date timestamp without time zone DEFAULT now(),
  updated_by integer,
  status character(1) DEFAULT 'A'::bpchar,
  CONSTRAINT pk_referal_details_remark_id PRIMARY KEY (referal_details_id),

  CONSTRAINT fk_referal_details_reference_type_id FOREIGN KEY (referral_type_id)
      REFERENCES adt.m_referral_type_master (referral_type_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_referal_master_id FOREIGN KEY (referral_id)
      REFERENCES adt.m_referral_master (referral_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);*/