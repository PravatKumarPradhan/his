package com.param.entity.model.master;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity(name = "VisitType")
@Table(name = "m_visit_type_master", schema = "public")
public class VisitType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "visit_type_id", unique = true, nullable = false)
	private Integer visitTypeId;

	@Column(name = "created_by")
	private Integer createdBy;

	@Column(name = "created_date")
	private Timestamp createdDate;

	@Column(length = 1)
	private String status;

	@Column(name = "updated_by")
	private Integer updatedBy;

	@Column(name = "updated_date")
	private Timestamp updatedDate;

	@Column(name = "visit_type_code", length = 50)
	private String visitTypeCode;

	@Column(name = "visit_type_name", length = 255)
	private String visitTypeName;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "visitType")
	private List<KinDetail> kinDetailsList;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "organization_id")
	private Organization organization;

	/*@OneToMany(fetch = FetchType.LAZY, mappedBy = "visitTypeMaster")
	private List<TPackageConsumptionMaster> listTPackageConsumptionMaster;
	
	public List<TPackageConsumptionMaster> getListTPackageConsumptionMaster() {
		return listTPackageConsumptionMaster;
	}

	public void setListTPackageConsumptionMaster(List<TPackageConsumptionMaster> listTPackageConsumptionMaster) {
		this.listTPackageConsumptionMaster = listTPackageConsumptionMaster;
	}*/

	public VisitType() {
	}

	public VisitType(Integer visitTypeId) {
		super();
		this.visitTypeId = visitTypeId;
	}

	public VisitType(Integer visitTypeId, String visitTypeName) {
		super();
		this.visitTypeId = visitTypeId;
		this.visitTypeName = visitTypeName;
	}

	public Integer getVisitTypeId() {
		return this.visitTypeId;
	}

	public void setVisitTypeId(Integer visitTypeId) {
		this.visitTypeId = visitTypeId;
	}

	public Integer getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Timestamp getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getUpdatedBy() {
		return this.updatedBy;
	}

	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Timestamp getUpdatedDate() {
		return this.updatedDate;
	}

	public void setUpdatedDate(Timestamp updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getVisitTypeCode() {
		return this.visitTypeCode;
	}

	public void setVisitTypeCode(String visitTypeCode) {
		this.visitTypeCode = visitTypeCode;
	}

	public String getVisitTypeName() {
		return this.visitTypeName;
	}

	public void setVisitTypeName(String visitTypeName) {
		this.visitTypeName = visitTypeName;
	}

	public List<KinDetail> getKinDetailsList() {
		return this.kinDetailsList;
	}

	public void setKinDetailsList(List<KinDetail> kinDetailsList) {
		this.kinDetailsList = kinDetailsList;
	}

	public KinDetail addKinDetailsList(KinDetail kinDetailsList) {
		getKinDetailsList().add(kinDetailsList);
		kinDetailsList.setVisitType(this);

		return kinDetailsList;
	}

	public KinDetail removeKinDetailsList(KinDetail kinDetailsList) {
		getKinDetailsList().remove(kinDetailsList);
		kinDetailsList.setVisitType(null);

		return kinDetailsList;
	}

	public Organization getOrganization() {
		return this.organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

}
