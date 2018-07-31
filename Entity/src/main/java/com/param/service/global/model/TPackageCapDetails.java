package com.param.service.global.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.param.global.model.SpecialityMaster;
import com.param.global.model.SubSpecialityMaster;

@NamedNativeQueries({
	@NamedNativeQuery(name = "GET_CAP_DETAILS_BY_PACKAGE_ID_FOR_BILLING",
					 query = "select pcap.package_cap_details_id as \"packageCapDetailsId\", pcap.package_id as \"packageId\", pcap.department_id as \"departmentId\",  "
							 + " pcap.sub_department_id as \"subDepartmentId\", pcap.department_cap_amount as \"departmentCapAmount\", pcap.sub_department_cap_amount as \"subDepartmentCapAmount\", "
							 + " pcap.is_service_item as \"isServiceItem\", pcap.product_cateroy_id as \"productCateroyId\", pcap.product_cateroy_cap_amount as \"productCateroyCapAmount\", "
							 + " sp.speciality_code as \"groupCode\", sp.speciality_name as \"groupName\",coalesce(sub.sub_speciality_name,'-') as \"subGroupName\",  coalesce(prod.category,'-') as \"productCategory\" "
							 + " from service.t_package_cap_details pcap  "
							 + " left join doctor.m_speciality_master sp "
							 + " on sp.speciality_id = pcap.department_id "
							 + " left join doctor.m_sub_speciality_master sub " 
							 + " on sub.sub_speciality_id = pcap.sub_department_id "
							 + " left join public.m_product_category prod "
							 + " on prod.id = pcap.product_cateroy_id "
							 + " where pcap.package_id = :packageId "
							 + " order by pcap.package_cap_details_id "),
	
	@NamedNativeQuery(name = "GET_CAP_DETAILS_BY_PACKAGE_ID",
					  query = "select pcap.package_cap_details_id as \"packageCapDetailsId\", pcap.package_id as \"packageId\", pcap.department_id as \"departmentId\",   "
							  + " pcap.sub_department_id as \"subDepartmentId\", pcap.department_cap_amount as \"departmentCapAmount\", pcap.sub_department_cap_amount as \"subDepartmentCapAmount\",  "
							  + " pcap.is_service_item as \"isServiceItem\", pcap.product_cateroy_id as \"productCateroyId\", pcap.product_cateroy_cap_amount as \"productCateroyCapAmount\" "
							  + " from service.t_package_cap_details pcap   "
							  + " where pcap.package_id = :packageId ")
	
})
@Entity
@Table(name="t_package_cap_details",schema="service")
@SequenceGenerator(name="t_seq_package_cap_details",sequenceName="service.t_seq_package_cap_details",allocationSize=1)
public class TPackageCapDetails {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="t_seq_package_cap_details")
	@Column(name="package_cap_details_id")
	private Integer packageCapDetailsId;
	
	@Column(name="package_id")
	private Integer packageId;
	
	@Column(name="department_id")
	private Integer departmentId;
	
	@Column(name="sub_department_id")
	private Integer subDepartmentId;
	
	@Column(name="department_cap_amount")
	private Double departmentCapAmount;
	
	@Column(name="sub_department_cap_amount")
	private Double subDepartmentCapAmount;
	
	@Column(name="organisation_id")
	private Integer organisationId;
	
	@Column(name="unit_id")
	private Integer unitId;
	
	@Column(name="created_by")
	private Integer createdBy;
	
	@Column(name="created_date")
	private Date createdDate;
	
	@Column(name="is_service_item")
	private Integer isServiceItem;
	
	@Column(name="product_cateroy_id")
	private Integer productCateroyId;
	
	@Column(name="status")
	private char status;

	@Column(name="product_cateroy_cap_amount")
	private Double productCateroyCapAmount;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "package_id" , insertable = false , updatable = false , nullable = false)
	private MPackageMaster mPackageMaster;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "department_id" , insertable = false , updatable = false , nullable = false)
	private SpecialityMaster specialityMaster;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sub_department_id" , insertable = false , updatable = false , nullable = false)
	private SubSpecialityMaster subSpecialityMaster;
	
	public MPackageMaster getmPackageMaster() {
		return mPackageMaster;
	}

	public void setmPackageMaster(MPackageMaster mPackageMaster) {
		this.mPackageMaster = mPackageMaster;
	}

	public SpecialityMaster getSpecialityMaster() {
		return specialityMaster;
	}

	public void setSpecialityMaster(SpecialityMaster specialityMaster) {
		this.specialityMaster = specialityMaster;
	}

	public SubSpecialityMaster getSubSpecialityMaster() {
		return subSpecialityMaster;
	}

	public void setSubSpecialityMaster(SubSpecialityMaster subSpecialityMaster) {
		this.subSpecialityMaster = subSpecialityMaster;
	}

	public Integer getPackageCapDetailsId() {
		return packageCapDetailsId;
	}

	public void setPackageCapDetailsId(Integer packageCapDetailsId) {
		this.packageCapDetailsId = packageCapDetailsId;
	}

	public Integer getPackageId() {
		return packageId;
	}

	public void setPackageId(Integer packageId) {
		this.packageId = packageId;
	}

	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	public Integer getSubDepartmentId() {
		return subDepartmentId;
	}

	public void setSubDepartmentId(Integer subDepartmentId) {
		this.subDepartmentId = subDepartmentId;
	}

	public Double getDepartmentCapAmount() {
		return departmentCapAmount;
	}

	public void setDepartmentCapAmount(Double departmentCapAmount) {
		this.departmentCapAmount = departmentCapAmount;
	}

	public Double getSubDepartmentCapAmount() {
		return subDepartmentCapAmount;
	}

	public void setSubDepartmentCapAmount(Double subDepartmentCapAmount) {
		this.subDepartmentCapAmount = subDepartmentCapAmount;
	}

	public Integer getOrganisationId() {
		return organisationId;
	}

	public void setOrganisationId(Integer organisationId) {
		this.organisationId = organisationId;
	}

	public Integer getUnitId() {
		return unitId;
	}

	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
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

	public Integer getIsServiceItem() {
		return isServiceItem;
	}

	public void setIsServiceItem(Integer isServiceItem) {
		this.isServiceItem = isServiceItem;
	}

	public Integer getProductCateroyId() {
		return productCateroyId;
	}

	public void setProductCateroyId(Integer productCateroyId) {
		this.productCateroyId = productCateroyId;
	}

	public Double getProductCateroyCapAmount() {
		return productCateroyCapAmount;
	}

	public void setProductCateroyCapAmount(Double productCateroyCapAmount) {
		this.productCateroyCapAmount = productCateroyCapAmount;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}
	
	
	

}
