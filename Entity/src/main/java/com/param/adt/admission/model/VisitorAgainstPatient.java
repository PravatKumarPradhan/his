package com.param.adt.admission.model;

import java.sql.Date;
import java.util.List;
import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.param.adt.master.global.model.OrganizationMaster;
import com.param.adt.master.global.model.UnitMaster;

@NamedQueries({ @NamedQuery(name = "GET_VISITORS_LIST", 
	query = "SELECT vap.visitorPatientId as visitorPatientId, "
		+ "vpml.visitorPatientMapperId as visitorPatientMapperId, " 
		+ "vap.passNumber as passNumber, "
		+ "vpml.visitorPassTypeId as visitorPassTypeId, "
		+ "to_char(vpml.expiryDate,'dd/MM/yyyy') as expiryDate, " 
		+ "to_char(vpml.expiryDate,'dd/MM/yyyy') as txtOldDate, "
		+ "vptm.visitorPassTypeDesc as visitorPassTypeDesc "
		+ "FROM VisitorAgainstPatient vap " 
		+ "INNER JOIN vap.visitorPatientMappersList vpml "
		+ "INNER JOIN vpml.visitorPassTypeMaster vptm " 
		+ "WHERE vap.status='A' " 
		+ "AND vpml.status='A' "
		+ "AND vap.organizationId=:organizationId " 
		+ "AND vap.unitId=:unitId " 
		+ "AND vap.admissionId=:admissionId") })

@Entity
@Table(name = "t_visitor_against_patient", schema = "adt")
@SequenceGenerator(name = "visitor_against_patient_seq", sequenceName = "adt.visitor_against_patient_seq", allocationSize = 1)
public class VisitorAgainstPatient {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "visitor_against_patient_seq")
	@Column(name = "visitor_patient_id")
	private int visitorPatientId;

	@Column(name = "organization_id")
	private Integer organizationId;

	@Column(name = "unit_id")
	private Integer unitId;

	@Column(name = "admission_id")
	private Integer admissionId;

	@Column(name = "pass_number")
	private String passNumber;

	@Column(name = "status")
	private char status;

	@Column(name = "created_by")
	private int createdBy;

	@Column(name = "updated_by")
	private int updatedBy;

	@Column(name = "created_date")
	private Date createdDate;

	@Column(name = "updated_date")
	private Date updatedDate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "organization_id", insertable = false, updatable = false)
	private OrganizationMaster organizationMaster;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "unit_id", insertable = false, updatable = false)
	private UnitMaster unitMaster;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "admission_id", insertable = false, updatable = false)
	private Admission admission;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "visitorAgainstPatient", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<VisitorPatientMapper> visitorPatientMappersList;

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

	public Integer getAdmissionId() {
		return admissionId;
	}

	public void setAdmissionId(Integer admissionId) {
		this.admissionId = admissionId;
	}

	public String getPassNumber() {
		return passNumber;
	}

	public void setPassNumber(String passNumber) {
		this.passNumber = passNumber;
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

	public Admission getAdmission() {
		return admission;
	}

	public void setAdmission(Admission admission) {
		this.admission = admission;
	}

	public List<VisitorPatientMapper> getVisitorPatientMappersList() {
		return visitorPatientMappersList;
	}

	public void setVisitorPatientMappersList(List<VisitorPatientMapper> visitorPatientMappersList) {
		this.visitorPatientMappersList = visitorPatientMappersList;
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

}
