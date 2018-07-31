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

import com.param.adt.master.unit.model.UnitICUTypeMapper;
import com.param.adt.master.unit.model.WardMaster;

@NamedQueries({

		@NamedQuery(name = "GET_ICU_LIST", query = "SELECT iCUTypeMaster.ICUTypeMasterId as ICUTypeMasterId, "
				+ "iCUTypeMaster.ICUtypeCode as ICUtypeCode, " + "iCUTypeMaster.ICUType as ICUType, "
				+ "iCUTypeMaster.isCloseICU as isCloseICU, " + "iCUTypeMaster.isOpenICU as isOpenICU, "
				+ "iCUTypeMaster.status as status " + "FROM ICUTypeMaster as iCUTypeMaster "
						+ "WHERE iCUTypeMaster.organizationId=:orgId "
						+ "ORDER BY iCUTypeMaster.ICUTypeMasterId DESC"),

		@NamedQuery(name = "GET_ICU_LIST_BY_NAME", query = "SELECT iCUTypeMaster.ICUTypeMasterId as ICUTypeMasterId, "
				+ "iCUTypeMaster.ICUtypeCode as ICUtypeCode, " + "iCUTypeMaster.ICUType as ICUType, "
				+ "iCUTypeMaster.isCloseICU as isCloseICU, " + "iCUTypeMaster.isOpenICU as isOpenICU, "
				+ "iCUTypeMaster.status as status "
				+ "FROM ICUTypeMaster as iCUTypeMaster WHERE LOWER(iCUTypeMaster.ICUType)=:iCUName OR iCUTypeMaster.ICUType=:iCUName"),

		@NamedQuery(name = "GET_ICU_LIST_BY_ID", query = "SELECT iCUTypeMaster.ICUTypeMasterId as ICUTypeMasterId, "
				+ "iCUTypeMaster.ICUtypeCode as ICUtypeCode, " + "iCUTypeMaster.ICUType as ICUType, "
				+ "iCUTypeMaster.isCloseICU as isCloseICU, " + "iCUTypeMaster.isOpenICU as isOpenICU, "
				+ "iCUTypeMaster.status as status "
				+ "FROM ICUTypeMaster as iCUTypeMaster WHERE iCUTypeMaster.ICUTypeMasterId =:iCUID"),

		@NamedQuery(name = "GET_ICU_LIST_BY_NAME_NOT_ID", query = "SELECT iCUTypeMaster.ICUTypeMasterId as ICUTypeMasterId, "
				+ "iCUTypeMaster.ICUtypeCode as ICUtypeCode, " + "iCUTypeMaster.ICUType as ICUType, "
				+ "iCUTypeMaster.isCloseICU as isCloseICU, " + "iCUTypeMaster.isOpenICU as isOpenICU, "
				+ "iCUTypeMaster.status as status "
				+ "FROM ICUTypeMaster as iCUTypeMaster WHERE (LOWER(iCUTypeMaster.ICUType)=:iCUName OR iCUTypeMaster.ICUType=:iCUName) AND iCUTypeMaster.ICUTypeMasterId !=:iCUID ") })
@Entity
@Table(name = "m_icu_type_master", schema = "adt")
@SequenceGenerator(name = "icu_type_master_seq", sequenceName = "adt.icu_type_master_seq", allocationSize = 1)
public class ICUTypeMaster {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "icu_type_master_seq")
	@Column(name = "icu_type_id")
	private int ICUTypeMasterId;

	@Column(name = "icu_type_code")
	private String ICUtypeCode;

	@Column(name = "icu_type")
	private String ICUType;

	@Column(name = "is_close_icu")
	private char isCloseICU;

	@Column(name = "is_open_icu")
	private char isOpenICU;

	@Column(name = "created_by")
	private int createdBy;

	@Column(name = "created_date")
	private Date createdDate;

	@Column(name = "status")
	private char status;

	@Column(name = "organization_id")
	private Integer organizationId;

	@Column(name="updated_by")
	private int updatedBy;
	
	@Column(name="updated_date")
	private Date updatedDate;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "organization_id", insertable = false, updatable = false, nullable = false)
	private OrganizationMaster organizationMaster;
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "icuTypeMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<UnitICUTypeMapper> unitICUMappersList;
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "icuTypeMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<WardMaster> wardMastersList;

	public int getICUTypeMasterId() {
		return ICUTypeMasterId;
	}

	public void setICUTypeMasterId(int iCUTypeMasterId) {
		ICUTypeMasterId = iCUTypeMasterId;
	}

	public String getICUtypeCode() {
		return ICUtypeCode;
	}

	public void setICUtypeCode(String iCUtypeCode) {
		ICUtypeCode = iCUtypeCode;
	}

	public String getICUType() {
		return ICUType;
	}

	public void setICUType(String iCUType) {
		ICUType = iCUType;
	}

	public char getIsCloseICU() {
		return isCloseICU;
	}

	public void setIsCloseICU(char isCloseICU) {
		this.isCloseICU = isCloseICU;
	}

	public char getIsOpenICU() {
		return isOpenICU;
	}

	public void setIsOpenICU(char isOpenICU) {
		this.isOpenICU = isOpenICU;
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

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}

	public Integer getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}

	public void setOrganizationMaster(OrganizationMaster organizationMaster) {
		this.organizationMaster = organizationMaster;
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
	
	
	/*
	 * @OneToMany(fetch = FetchType.LAZY,mappedBy = "icuTypeMaster", cascade =
	 * CascadeType.ALL, orphanRemoval = true) private List<WardMaster>
	 * listWardMaster;
	 */

}
