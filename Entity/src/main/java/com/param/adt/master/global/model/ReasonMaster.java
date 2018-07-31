package com.param.adt.master.global.model;

import java.util.Date;
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

import com.param.adt.admission.model.AdmissionNote;
import com.param.adt.admission.model.UnreservedPatient;
import com.param.adt.discharge.model.Discharge;
import com.param.adt.transfer.model.TransferRequest;
import com.param.global.org.common.model.ReasonTypeMaster;

@NamedQueries({
	
	@NamedQuery(name="GET_REASON_MASTER_BY_ID",
			query="SELECT rtm.reasonMasterId AS reasonMasterId,"
					+ "rtm.reasonTypeId AS reasonTypeId,"
					+ "rtm.reasonCode AS reasonCode, " 
					+ "rtm.reasonDesc AS reasonDesc "
					+ " FROM ReasonMaster rtm "
					+ "WHERE rtm.organizationId=:orgId "
					+ "AND rtm.reasonMasterId=:reasonId"),
	@NamedQuery(name = "GET_REASONMASTER_LIST", 
	query = "SELECT rtm.reasonMasterId as reasonMasterId, "
			+ "rtm.reasonDesc as reasonDesc, "
			+ "rtm.reasonCode as reasonCode, " 
			+ "rtm.status as status, " 
			+ "rm.reasonTypeId as reasonTypeId, "
			+ "rm.reasonTypeDesc as reasonTypeDesc "
			+ "FROM ReasonMaster as rtm  "
			+ " INNER JOIN rtm.reasonTypeMaster rm "
			+ " WHERE rtm.organizationId=:orgId "),
	@NamedQuery(name = "GET_REASON_MASTER_BY_NAME", 
	query = "SELECT rtm.reasonMasterId as reasonMasterId, "
			+ "rtm.reasonDesc as reasonDesc, " 
			+ "rtm.status as status "
			+ "FROM ReasonMaster as rtm  "
			+ " WHERE LOWER(rtm.reasonDesc)=:reasonDesc OR rtm.reasonDesc=:reasonDesc " 
			+ "AND rtm.organizationId=:orgId"),
	@NamedQuery(name = "GET_REASON_MASTER_BY_NAME_NOT_BY_ID",
	query = "SELECT rtm.reasonMasterId as reasonMasterId, "
			+ "rtm.reasonDesc as reasonDesc  " 
			+ "FROM ReasonMaster as rtm  "
			+ "WHERE LOWER(rtm.reasonDesc)=:reasonDesc OR rtm.reasonDesc=:reasonDesc "
			+ "AND rtm.reasonMasterId !=:reasonId")
})
@Entity
@Table(name = "m_reason_master", schema = "adt")
@SequenceGenerator(name = "reason_master_seq", sequenceName = "adt.reason_master_seq", allocationSize = 1)
public class ReasonMaster {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reason_master_seq")
	@Column(name = "reason_master_id")
	private int reasonMasterId;

	@Column(name = "reason_desc")
	private String reasonDesc;
	
	@Column(name = "reason_code")
	private String reasonCode;

	@Column(name = "status")
	private char status;

	@Column(name = "created_by")
	private int createdBy;

	@Column(name = "created_date")
	private Date createdDate;

	@Column(name = "updated_by")
	private int updatedBy;

	@Column(name = "updated_date")
	private Date updatedDate;

	@Column(name = "organization_id")
	private Integer organizationId;

	@Column(name = "reason_type_id")
	private Integer reasonTypeId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "reason_type_id", insertable = false, updatable = false)
	private ReasonTypeMaster reasonTypeMaster;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "organization_id", insertable = false, updatable = false)
	private OrganizationMaster organizationMaster;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "reasonMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<AdmissionNote> admissionNotesList;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "reasonMasterUnreserve", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<UnreservedPatient> unreservedPatientsList;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "reasonMasterCancelation", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<UnreservedPatient> unreservedPatientsList2;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "reasonMasterReservation", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<UnreservedPatient> unreservedPatientsList3;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "reasonMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<TransferRequest> transferRequestslist;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "reason", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Discharge> dischargesList;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "reason2", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Discharge> dischargesList2;

	public ReasonMaster() {
		super();
	}

	public ReasonMaster(int reasonMasterId) {
		super();
		this.reasonMasterId = reasonMasterId;
	}

	public OrganizationMaster getOrganizationMaster() {
		return organizationMaster;
	}

	public void setOrganizationMaster(OrganizationMaster organizationMaster) {
		this.organizationMaster = organizationMaster;
	}

	public int getReasonMasterId() {
		return reasonMasterId;
	}

	public void setReasonMasterId(int reasonMasterId) {
		this.reasonMasterId = reasonMasterId;
	}

	public String getReasonDesc() {
		return reasonDesc;
	}

	public void setReasonDesc(String reasonDesc) {
		this.reasonDesc = reasonDesc;
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

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public int getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(int updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Integer getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}

	public Integer getReasonTypeId() {
		return reasonTypeId;
	}

	public void setReasonTypeId(Integer reasonTypeId) {
		this.reasonTypeId = reasonTypeId;
	}

	public ReasonTypeMaster getReasonTypeMaster() {
		return reasonTypeMaster;
	}

	public void setReasonTypeMaster(ReasonTypeMaster reasonTypeMaster) {
		this.reasonTypeMaster = reasonTypeMaster;
	}

	public String getReasonCode() {
		return reasonCode;
	}

	public void setReasonCode(String reasonCode) {
		this.reasonCode = reasonCode;
	}
	

}
