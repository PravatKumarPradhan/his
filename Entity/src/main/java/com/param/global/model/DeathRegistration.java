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

@Entity
@Table(name="t_death_registration",schema="patient")
@SequenceGenerator(name = "deth_registration_seq", sequenceName = "patient.deth_registration_seq", allocationSize = 1)
public class DeathRegistration {

	@Id
	@Column( name="death_registration_id")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="deth_registration_seq")
	private int deathRegistrationId;
	
	@Column(name="organization_id")
	private Integer organizationId;
	
	@Column(name="unit_id")
	private Integer unitId;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="gender_id")
	private Integer genderId;
	
	@Column(name="mark_on_decease")
	private String markOnDecease;
	
	@Column(name="date_of_death")
	private Date dateOfDeath;
	
	@Column(name="is_medico_legal")
	private char isMedicoLegal;
	
	@Column(name="uhid_number")
	private String uhidNumber;
	
	@Column(name="kin_id")
	private Integer kinId;
	
	@Column(name = "status")
	private char status;

	@Column(name = "created_date")
	private Date createdDate;

	@Column(name = "created_by")
	private Integer createdBy;

	@Column(name = "updated_by")
	private Integer updatedBy;

	@Column(name = "updated_date")
	private Date updatedDate;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "gender_id", insertable = false, nullable = false, updatable = false)
	private GenderMaster genderMaster;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "kin_id", insertable = false, nullable = false, updatable = false)
	private KinDetails kinDetails;
	

	public int getDethRegistrationId() {
		return deathRegistrationId;
	}

	public void setDethRegistrationId(int dethRegistrationId) {
		this.deathRegistrationId = dethRegistrationId;
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

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Integer getGenderId() {
		return genderId;
	}

	public void setGenderId(Integer genderId) {
		this.genderId = genderId;
	}

	public String getMarkOnDecease() {
		return markOnDecease;
	}

	public void setMarkOnDecease(String markOnDecease) {
		this.markOnDecease = markOnDecease;
	}

	public Date getDateOfDeath() {
		return dateOfDeath;
	}

	public void setDateOfDeath(Date dateOfDeath) {
		this.dateOfDeath = dateOfDeath;
	}

	public char getIsMedicoLegal() {
		return isMedicoLegal;
	}

	public void setIsMedicoLegal(char isMedicoLegal) {
		this.isMedicoLegal = isMedicoLegal;
	}

	public String getUhidNumber() {
		return uhidNumber;
	}

	public void setUhidNumber(String uhidNumber) {
		this.uhidNumber = uhidNumber;
	}

	public Integer getKinId() {
		return kinId;
	}

	public void setKinId(Integer kinId) {
		this.kinId = kinId;
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

	public Integer getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	
}
/*CREATE TABLE patient.t_death_registration
(
  deth_registration_id integer NOT NULL,
  organization_id integer,
  unit_id integer,
  first_name character varying,
  last_name character varying,
  gender_id integer,
  mark_on_decease character varying,
  date_of_death timestamp without time zone,
  is_medico_legal character(1) DEFAULT 'N'::bpchar,
  uhid_number character varying,
  kin_id integer,
  created_by integer,
  created_date timestamp without time zone DEFAULT now(),
  updated_by integer,
  updated_date timestamp without time zone DEFAULT now(),
  status character(1) DEFAULT 'A'::bpchar,
  CONSTRAINT t_deth_registration_pkey PRIMARY KEY (deth_registration_id)
)*/