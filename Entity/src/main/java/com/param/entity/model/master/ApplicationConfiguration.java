package com.param.entity.model.master;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.param.entity.model.base.BaseEntity;

@Entity(name="ApplicationConfiguration")
@Table(name="m_application_configuration",schema="public")
public class ApplicationConfiguration extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private Integer id;
	
	@Column(nullable = false, length = 10)
	private Boolean itemName;

	@Column(nullable = false, length = 100)
	private Integer defaultTaxId;

	@Column(nullable = false, length = 10)
	private Boolean issueMaterialPickerRequired;
	
	public ApplicationConfiguration() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Boolean getItemName() {
		return itemName;
	}

	public void setItemName(Boolean itemName) {
		this.itemName = itemName;
	}

	
	public Integer getDefaultTaxId() {
		return defaultTaxId;
	}

	public void setDefaultTaxId(Integer defaultTaxId) {
		this.defaultTaxId = defaultTaxId;
	}

	public Boolean getIssueMaterialPickerRequired() {
		return issueMaterialPickerRequired;
	}

	public void setIssueMaterialPickerRequired(Boolean issueMaterialPickerRequired) {
		this.issueMaterialPickerRequired = issueMaterialPickerRequired;
	}

	public void setAddedDate(Timestamp addedDate) {
		this.addedDate = addedDate;
	}

	
	public Boolean getIsActive() {
		return this.isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Boolean getIsDeleted() {
		return this.isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public void setUpdatedDate(Timestamp updatedDate) {
		this.updatedDate = updatedDate;
	}


	public ApplicationConfiguration(Boolean itemName, Integer defaultTaxId, Boolean issueMaterialPickerRequired) {
		super();
		this.itemName = itemName;
		this.defaultTaxId = defaultTaxId;
		this.issueMaterialPickerRequired = issueMaterialPickerRequired;
	}

	public void updateApplicationConfiguration(Boolean itemName, Integer defaultTaxId, Boolean issueMaterialPickerRequired) {
		this.itemName = itemName;
		this.defaultTaxId = defaultTaxId;
		this.issueMaterialPickerRequired = issueMaterialPickerRequired;
	}
	
	public ApplicationConfiguration(Integer id) {
		super();
		this.id = id;
	}

}
