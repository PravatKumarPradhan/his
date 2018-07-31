package com.param.global.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.param.billing.global.model.DepositMaster;
import com.param.service.global.model.MCompanyContractMaster;
import com.param.service.global.model.MPackageMaster;
@NamedQueries({
	@NamedQuery(name="GET_ACTIVE_VISIT_TYPE_LIST",
				query =" SELECT visitTypeMaster.visitTypeId as visitTypeId, visitTypeMaster.visitTypeName as visitTypeName,"
						+ " visitTypeMaster.visitTypeCode as visitTypeCode "
						+ " FROM VisitTypeMaster visitTypeMaster"
						+ " WHERE visitTypeMaster.organizationId =:orgId AND visitTypeMaster.status = 'A'")
})

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@Table(name = "m_visit_type_master", schema = "public")
@SequenceGenerator(name="visit_type_master_seq" , sequenceName ="public.visit_type_master_seq", allocationSize = 1)
public class VisitTypeMaster 
{
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="visit_type_master_seq")
	@Column(name="visit_type_id")
	private int visitTypeId;
	
	@Column(name="visit_type_name")
	private String visitTypeName;
	
	@Column(name="visit_type_code")
	private String visitTypeCode;
	
	@Column(name="created_by")
	private int createdBy;
	
	@Column(name="created_date")
	private String createdDate;
	
	@Column(name="status")
	private char status;
	
	@Column(name="updated_by")
	private int updatedBy;
	
	@Column(name="updated_date")
	private Date updatedDate;
	
	@Column(name="organization_id")
	private Integer organizationId;
	
	@OneToMany(fetch = FetchType.LAZY , cascade = CascadeType.ALL , mappedBy = "visitTypeMaster")
	private List<DepositMaster> listDepositMaster;
	
	@OneToMany(fetch = FetchType.LAZY , cascade = CascadeType.ALL , mappedBy = "visitTypeMaster")
	private List<MPackageMaster> listMPackageMaster;
	
	@OneToMany(fetch = FetchType.LAZY , cascade = CascadeType.ALL , mappedBy = "visitTypeMaster")
	private List<MCompanyContractMaster> listMCompanyContractMaster;

	public List<MCompanyContractMaster> getListMCompanyContractMaster() {
		return listMCompanyContractMaster;
	}

	public void setListMCompanyContractMaster(List<MCompanyContractMaster> listMCompanyContractMaster) {
		this.listMCompanyContractMaster = listMCompanyContractMaster;
	}
	
	public List<MPackageMaster> getListMPackageMaster() {
		return listMPackageMaster;
	}

	public void setListMPackageMaster(List<MPackageMaster> listMPackageMaster) {
		this.listMPackageMaster = listMPackageMaster;
	}

	public List<DepositMaster> getListDepositMaster() {
		return listDepositMaster;
	}

	public void setListDepositMaster(List<DepositMaster> listDepositMaster) {
		this.listDepositMaster = listDepositMaster;
	}

	public Integer getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
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

	public int getVisitTypeId() {
		return visitTypeId;
	}

	public void setVisitTypeId(int visitTypeId) {
		this.visitTypeId = visitTypeId;
	}

	public String getVisitTypeName() {
		return visitTypeName;
	}

	public void setVisitTypeName(String visitTypeName) {
		this.visitTypeName = visitTypeName;
	}

	public String getVisitTypeCode() {
		return visitTypeCode;
	}

	public void setVisitTypeCode(String visitTypeCode) {
		this.visitTypeCode = visitTypeCode;
	}

	public int getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}
	
	
}
