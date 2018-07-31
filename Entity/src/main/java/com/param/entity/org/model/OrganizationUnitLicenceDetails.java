package com.param.entity.org.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.param.adt.master.global.model.OrganizationMaster;
import com.param.adt.master.global.model.UnitMaster;

@NamedNativeQueries({
	
	@NamedNativeQuery(name="GET_LICENCE_DETAILS",
				query="SELECT 	licence.licence_details_id as \"licenceDetailsId\" , licence.licence_type_id as \"licenceTypeId\" ,"
						+ "		licence.organization_id as \"organizationId\" , licence.unit_id as \"unitId\" , "
						+ "		licence.licence_number as \"licenceNumber\" , to_char(licence.expiry_date,'DD/MM/YYYY') as \"expiryDate\" ,"
						+ "		licence.is_unit as \"isUnit\" , licenceType.licence_type as \"licenceType\" "
						+ "FROM public.t_organization_unit_licence_details licence "
						+ "inner join public.m_licence_type_master licenceType "
						+ "on licenceType.licence_type_id = licence.licence_type_id ")
})

@Entity
@Table(name="t_organization_unit_licence_details" , schema= "public")
@SequenceGenerator(name="org_licence_details_seq" , sequenceName="public.org_licence_details_seq", allocationSize=1)
public class OrganizationUnitLicenceDetails {

	@Id
	@GeneratedValue(generator = "org_licence_details_seq" , strategy=GenerationType.SEQUENCE)
	@Column(name="licence_details_id")
	private int licenceDetailsId;
	
	@Column(name="licence_type_id")
	private Integer licenceTypeId;
	
	@Column(name="organization_id")
	private Integer organizationId;
	
	@Column(name="unit_id")
	private Integer unitId;
	
	@Column(name="licence_number")
	private String licenceNumber;
	
	@Column(name="expiry_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date expiryDate;
	
	@Column(name="created_by")
	private Integer createdBy;
	
	@Column(name="created_date")
	private Date createdDate;
	
	@Column(name="is_unit")
	private char isUnit;
	
	
	@ManyToOne
	@JoinColumn(name="organization_id" , insertable = false , updatable = false , nullable= false)
	private OrganizationMaster organizationMaster;
	
	@ManyToOne
	@JoinColumn(name="unit_id" , insertable = false , updatable = false , nullable= false)
	private UnitMaster unitMaster;

	public int getLicenceDetailsId() {
		return licenceDetailsId;
	}

	public void setLicenceDetailsId(int licenceDetailsId) {
		this.licenceDetailsId = licenceDetailsId;
	}

	public Integer getLicenceTypeId() {
		return licenceTypeId;
	}

	public void setLicenceTypeId(Integer licenceTypeId) {
		this.licenceTypeId = licenceTypeId;
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

	public String getLicenceNumber() {
		return licenceNumber;
	}

	public void setLicenceNumber(String licenceNumber) {
		this.licenceNumber = licenceNumber;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public char getIsUnit() {
		return isUnit;
	}

	public void setIsUnit(char isUnit) {
		this.isUnit = isUnit;
	}
	
}
