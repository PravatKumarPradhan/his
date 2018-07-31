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

@Entity(name = "Prefix")
@Table(name = "m_prefix_master", schema = "public")
public class Prefix implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "prefix_id", unique = true, nullable = false)
	private Integer prefixId;

	@Column(name = "created_by")
	private Integer createdBy;

	@Column(name = "created_date")
	private Timestamp createdDate;

	@Column(name = "prefix_code", length = 2147483647)
	private String prefixCode;

	@Column(name = "prefix_desc", length = 2147483647)
	private String prefixDesc;

	@Column(length = 1)
	private String status;

	@Column(name = "updated_by")
	private Integer updatedBy;

	@Column(name = "updated_date")
	private Timestamp updatedDate;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "prefix")
	private List<KinDetail> kinDetailsList;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "gender_id")
	private Gender gender;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "organization_id")
	private Organization organization;

	public Prefix() {
	}

	public Prefix(Integer prefixId) {
		super();
		this.prefixId = prefixId;
	}

	public Integer getPrefixId() {
		return this.prefixId;
	}

	public void setPrefixId(Integer prefixId) {
		this.prefixId = prefixId;
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

	public String getPrefixCode() {
		return this.prefixCode;
	}

	public void setPrefixCode(String prefixCode) {
		this.prefixCode = prefixCode;
	}

	public String getPrefixDesc() {
		return this.prefixDesc;
	}

	public void setPrefixDesc(String prefixDesc) {
		this.prefixDesc = prefixDesc;
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

	public List<KinDetail> getKinDetailsList() {
		return this.kinDetailsList;
	}

	public void setKinDetailsList(List<KinDetail> kinDetailsList) {
		this.kinDetailsList = kinDetailsList;
	}

	public KinDetail addKinDetailsList(KinDetail kinDetailsList) {
		getKinDetailsList().add(kinDetailsList);
		kinDetailsList.setPrefix(this);

		return kinDetailsList;
	}

	public KinDetail removeKinDetailsList(KinDetail kinDetailsList) {
		getKinDetailsList().remove(kinDetailsList);
		kinDetailsList.setPrefix(null);

		return kinDetailsList;
	}

	public Gender getGender() {
		return this.gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Organization getOrganization() {
		return this.organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

}
