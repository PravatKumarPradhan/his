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

@Entity(name = "Gender")
@Table(name = "m_gender_master", schema = "public")
public class Gender implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "gender_id", unique = true, nullable = false)
	private Integer genderId;

	@Column(name = "created_by")
	private Integer createdBy;

	@Column(name = "created_date")
	private Timestamp createdDate;

	@Column(name = "gender_code", length = 50)
	private String genderCode;

	@Column(name = "gender_name", length = 100)
	private String genderName;

	@Column(name = "gender_prefix", length = 1)
	private String genderPrefix;

	@Column(length = 1)
	private String status;

	@Column(name = "updated_by")
	private Integer updatedBy;

	@Column(name = "updated_date")
	private Timestamp updatedDate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "organization_id")
	private Organization organization;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "gender")
	private List<Prefix> prefixList;

	public Gender() {
	}

	public Gender(Integer genderId) {
		super();
		this.genderId = genderId;
	}

	public Integer getGenderId() {
		return this.genderId;
	}

	public void setGenderId(Integer genderId) {
		this.genderId = genderId;
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

	public String getGenderCode() {
		return this.genderCode;
	}

	public void setGenderCode(String genderCode) {
		this.genderCode = genderCode;
	}

	public String getGenderName() {
		return this.genderName;
	}

	public void setGenderName(String genderName) {
		this.genderName = genderName;
	}

	public String getGenderPrefix() {
		return this.genderPrefix;
	}

	public void setGenderPrefix(String genderPrefix) {
		this.genderPrefix = genderPrefix;
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

	public Organization getOrganization() {
		return this.organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public List<Prefix> getPrefixList() {
		return this.prefixList;
	}

	public void setPrefixList(List<Prefix> prefixList) {
		this.prefixList = prefixList;
	}

	public Prefix addPrefixList(Prefix prefixList) {
		getPrefixList().add(prefixList);
		prefixList.setGender(this);

		return prefixList;
	}

	public Prefix removePrefixList(Prefix prefixList) {
		getPrefixList().remove(prefixList);
		prefixList.setGender(null);

		return prefixList;
	}
}