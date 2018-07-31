package com.param.entity.lis.unit;


import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import com.param.entity.lis.common.LocalTimeConverter;

@NamedQueries({
	@NamedQuery(name = "GET_DEPARTMENT_BY_DEPT_ID", query = "SELECT departmentMaster.departmentId as id, "
			+ " departmentMaster.code as code,"
			+ " departmentMaster.desc as desc,"
			+ " departmentMaster.status as status,"
			+ " departmentMaster.orgId as orgId,"
			+ " departmentMaster.createdBy as createdBy,"
			+ " departmentMaster.createdDate as createdDate,"
			+ " departmentMaster.updatedBy as updatedBy,"
			+ " departmentMaster.unitId as unitId,"
			+ " departmentMaster.updatedDate as updatedDate"
			+ " FROM DepartmentMaster departmentMaster"
			+ " WHERE departmentMaster.departmentId = :departmentId "
			+ " AND departmentMaster.orgId= :orgId"
			+ " AND departmentMaster.unitId= :unitId"),
	
	@NamedQuery(name = "GET_DEPARTMENT_BY_CODE", query = "SELECT departmentMaster.departmentId as id,"
			+ " departmentMaster.code as code,"
			+ " departmentMaster.desc as desc,"
			+ " departmentMaster.status as status,"
			+ " departmentMaster.createdBy as createdBy,"
			+ " departmentMaster.createdDate as createdDate,"
			+ " departmentMaster.updatedBy as updatedBy,"
			+ " departmentMaster.unitId as unitId,"
			+ " departmentMaster.updatedDate as updatedDate"
			+ " FROM DepartmentMaster departmentMaster"
			+ " WHERE departmentMaster.orgId=:orgId "
			+"  AND departmentMaster.unitId=:unitId "
			+ " AND lower(departmentMaster.code) = lower(:code)"),
			
			@NamedQuery(name = "UPDATE_GET_DEPARTMENT_BY_CODE", query = "SELECT departmentMaster.departmentId as id,"
					+ " departmentMaster.code as code,"
					+ " departmentMaster.desc as desc,"
					+ " departmentMaster.status as status,"
					+ " departmentMaster.createdBy as createdBy,"
					+ " departmentMaster.createdDate as createdDate,"
					+ " departmentMaster.updatedBy as updatedBy,"
					+ " departmentMaster.unitId as unitId,"
					+ " departmentMaster.updatedDate as updatedDate"
					+ " FROM DepartmentMaster departmentMaster"
					+ " WHERE departmentMaster.orgId=:orgId "
					+"  AND departmentMaster.unitId=:unitId "
					+ " AND lower(departmentMaster.code) = lower(:code)"
					+ " AND departmentMaster.departmentId <> :departmentId"),

	@NamedQuery(name = "GET_PAGINATED_DEPARTMENT_MASTER_LIST", query = "SELECT departmentMaster.departmentId as id,"
			+ " departmentMaster.code as code,"
			+ " departmentMaster.desc as desc,"
			+ " departmentMaster.status as status,"
			+ " departmentMaster.createdBy as createdBy,"
			+ " departmentMaster.createdDate as createdDate,"
			+ " departmentMaster.updatedBy as updatedBy,"
			+ " departmentMaster.updatedDate as updatedDate"
			+ " FROM DepartmentMaster departmentMaster"
			+ " WHERE departmentMaster.orgId = :orgId"
			+ " ORDER BY id DESC")

})

@NamedNativeQueries(
{ @NamedNativeQuery(name = "GET_TOTAL_DEPARTMENT_RECORD", query = "select count(*) from lab.m_department_master where "
	+ "org_id = :orgId")
})
@Entity
@Table(name = "m_department_master", schema = "lab")
@SequenceGenerator(name = "m_seq_department_master", sequenceName = "lab.m_seq_department_master", allocationSize = 1)
public class DepartmentMaster
{
	@Id
	@Column(name = "department_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "m_seq_department_master")
	private int departmentId;
	@Column(name="department_code")
	private String code;

	@Column(name="department_name")
	private String desc;

	@Column(name="department_status")
	private char status; 

	@Column(name="created_by")
	private int createdBy;

	@Column(name="created_date")
	@Convert(converter = LocalTimeConverter.class)
	private Long createdDate;
	
	@Column(name="updated_by")
	private int updatedBy;
	
	@Column(name="updated_date")
	@Convert(converter = LocalTimeConverter.class)
	private Long updatedDate;
	
	@Column(name = "org_id")
	private Integer orgId;
	
	@Column(name = "unit_id")
	private Integer unitId;

	
	public int getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}

	public String getcode() {
		return code;
	}

	public void setcode(String code) {
		this.code = code;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
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

	public int getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(int updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Long getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Long updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public Integer getUnitId() {
		return unitId;
	}

	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}



}
