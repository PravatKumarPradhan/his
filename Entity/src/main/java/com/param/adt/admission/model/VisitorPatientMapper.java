package com.param.adt.admission.model;

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

import com.param.adt.master.global.model.OrganizationMaster;
import com.param.adt.master.global.model.UnitMaster;

@Entity
@Table(name = "t_visitor_patient_mapper", schema = "adt")
@SequenceGenerator(name = "visitor_patient_mapper_seq", sequenceName = "adt.visitor_patient_mapper_seq", allocationSize = 1)
public class VisitorPatientMapper {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="visitor_patient_mapper_seq")
	@Column(name="visitor_patient_mapper_id")
	private Integer visitorPatientMapperId;
	
	@Column(name="visitor_patient_id")
	private int visitorPatientId;

	@Column(name="organization_id")
	private Integer organizationId;

	@Column(name="unit_id")
	private Integer unitId;

	@Column(name="expiry_date")
	private Date expiryDate;

	@Column(name="visitor_pass_status")
	private char visitorPassStatus;

	@Column(name="visitor_pass_type_id")
	private Integer visitorPassTypeId;

	@Column(name="status")
	private char status;

	@Column(name="created_by")
	private int createdBy;

	@Column(name="updated_by")
	private int updatedBy;

	@Column(name="created_date")
	private Date createdDate;

	@Column(name="updated_date")
	private Date updatedDate;
	
	@Column(name="lost_pass_reason")
	private String txtReason;
	
	public String getTxtReason() {
		return txtReason;
	}

	public void setTxtReason(String txtReason) {
		this.txtReason = txtReason;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "organization_id", insertable = false, updatable = false)
	private OrganizationMaster organizationMaster;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "unit_id", insertable = false, updatable = false)
	private UnitMaster unitMaster;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "visitor_patient_id", insertable = false, updatable = false)
	private VisitorAgainstPatient visitorAgainstPatient;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "visitor_pass_type_id", insertable = false, updatable = false)
	private VisitorPassTypeMaster visitorPassTypeMaster;

	public Integer getVisitorPatientMapperId() {
		return visitorPatientMapperId;
	}

	public void setVisitorPatientMapperId(Integer visitorPatientMapperId) {
		this.visitorPatientMapperId = visitorPatientMapperId;
	}

	public int getVisitorPatientId() {
		return visitorPatientId;
	}

	public void setVisitorPatientId(int visitorPatientId) {
		this.visitorPatientId = visitorPatientId;
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

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public char getVisitorPassStatus() {
		return visitorPassStatus;
	}

	public void setVisitorPassStatus(char visitorPassStatus) {
		this.visitorPassStatus = visitorPassStatus;
	}

	public Integer getVisitorPassTypeId() {
		return visitorPassTypeId;
	}

	public void setVisitorPassTypeId(Integer visitorPassTypeId) {
		this.visitorPassTypeId = visitorPassTypeId;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}

	public int getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	public int getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(int updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public OrganizationMaster getOrganizationMaster() {
		return organizationMaster;
	}

	public void setOrganizationMaster(OrganizationMaster organizationMaster) {
		this.organizationMaster = organizationMaster;
	}

	public UnitMaster getUnitMaster() {
		return unitMaster;
	}

	public void setUnitMaster(UnitMaster unitMaster) {
		this.unitMaster = unitMaster;
	}

	public VisitorAgainstPatient getVisitorAgainstPatient() {
		return visitorAgainstPatient;
	}

	public void setVisitorAgainstPatient(VisitorAgainstPatient visitorAgainstPatient) {
		this.visitorAgainstPatient = visitorAgainstPatient;
	}

	public VisitorPassTypeMaster getVisitorPassTypeMaster() {
		return visitorPassTypeMaster;
	}

	public void setVisitorPassTypeMaster(VisitorPassTypeMaster visitorPassTypeMaster) {
		this.visitorPassTypeMaster = visitorPassTypeMaster;
	}

	
	
}
