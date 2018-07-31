/**
 * 
 */
package com.param.global.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
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

import com.param.global.common.DateConverter;
import com.param.service.global.model.MCompanyContractMaster;


@NamedQueries({
	@NamedQuery(name = "GET_ASSOCIATED_COMPANY_MASTER_LIST_BY_COMPANY_ID",
				query = " SELECT associatedCompanyMaster.associatedCompanyMasterId as associatedCompanyMasterId, associatedCompanyMaster.associatedCompanyName as associatedCompanyName,"
					  + "        associatedCompanyMaster.status as status, associatedCompanyMaster.createdBy as createdBy,"
					  + "        associatedCompanyMaster.companyMasterId as companyMasterId "
					  + " FROM   AssociatedCompanyMaster associatedCompanyMaster "
					//  + " INNER JOIN associatedCompanyMaster.companyMaster companyMaster"
					  + " WHERE associatedCompanyMaster.companyMasterId=:companyId "
					  + " AND associatedCompanyMaster.status = 'A'"
					  + " AND associatedCompanyMaster.unitId=:unitId"
					  + " AND associatedCompanyMaster.organizationId=:orgId "),
					  
					  
})

@Entity
@Table(name = "t_associated_company_master" , schema = "public")
@SequenceGenerator(name="associated_company_master_seq", sequenceName="public.associated_company_master_seq", allocationSize=1)
public class AssociatedCompanyMaster {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "associated_company_master_seq")
	@Column(name = "associated_company_master_id")
	private int associatedCompanyMasterId;
	
	@Column(name = "associated_company_name")
	private String associatedCompanyName;
	
	@Column(name = "status")
	private char status;

	@Column(name = "created_by")
	private int createdBy;
	
	@Column(name = "created_date")
	@Convert(converter = DateConverter.class)
	private Long createdDate;
	
	@Column(name = "company_master_id")
	private Integer companyMasterId;
	
	@Column(name ="updated_by")
	private Integer updatedBy;
	
	@Column(name = "updated_date")
	@Convert(converter = DateConverter.class)
	private Long updatedDate;
	
	@Column(name = "unit_id")
	private Integer unitId;
	
	@Column(name="organization_id")
	private Integer organizationId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "company_master_id", insertable = false, updatable = false)
	private CompanyMaster companyMaster;
	
	@OneToMany(fetch = FetchType.LAZY , cascade = CascadeType.ALL , mappedBy = "associatedCompanyMaster")
	private List<MCompanyContractMaster> listMCompanyContractMaster;

	public List<MCompanyContractMaster> getListMCompanyContractMaster() {
		return listMCompanyContractMaster;
	}

	public void setListMCompanyContractMaster(List<MCompanyContractMaster> listMCompanyContractMaster) {
		this.listMCompanyContractMaster = listMCompanyContractMaster;
	}

	public int getAssociatedCompanyMasterId() {
		return associatedCompanyMasterId;
	}

	public void setAssociatedCompanyMasterId(int associatedCompanyMasterId) {
		this.associatedCompanyMasterId = associatedCompanyMasterId;
	}

	public String getAssociatedCompanyName() {
		return associatedCompanyName;
	}

	public void setAssociatedCompanyName(String associatedCompanyName) {
		this.associatedCompanyName = associatedCompanyName;
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

	public Long getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Long createdDate) {
		this.createdDate = createdDate;
	}

	public Integer getCompanyMasterId() {
		return companyMasterId;
	}

	public void setCompanyMasterId(Integer companyMasterId) {
		this.companyMasterId = companyMasterId;
	}

	public Integer getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Long getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Long updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Integer getUnitId() {
		return unitId;
	}

	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}

	public Integer getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}

	public CompanyMaster getCompanyMaster() {
		return companyMaster;
	}

	public void setCompanyMaster(CompanyMaster companyMaster) {
		this.companyMaster = companyMaster;
	}
}
