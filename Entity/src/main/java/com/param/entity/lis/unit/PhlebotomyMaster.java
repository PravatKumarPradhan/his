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
	@NamedQuery(name = "GET_PHLEBOTOMY_BY_PHLEBOTOMY_ID", query = "SELECT phlebotomyMaster.phlebotomyId as id, "
			+ " phlebotomyMaster.code as code,"
			+ " phlebotomyMaster.desc as desc,"
			+ " phlebotomyMaster.status as status,"
			+ " phlebotomyMaster.orgId as orgId,"
			+ " phlebotomyMaster.createdBy as createdBy,"
			+ " phlebotomyMaster.createdDate as createdDate,"
			+ " phlebotomyMaster.updatedBy as updatedBy,"
			+ " phlebotomyMaster.updatedDate as updatedDate,"
			+ " phlebotomyMaster.unitId as unitId "
			+ " FROM PhlebotomyMaster phlebotomyMaster"
			+ " WHERE phlebotomyMaster.phlebotomyId = :phlebotomyId "
			+"  AND phlebotomyMaster.unitId=:unitId "
			+ " AND phlebotomyMaster.orgId= :orgId"),
	
	@NamedQuery(name = "GET_PHLEBOTOMY_BY_CODE", query = "SELECT phlebotomyMaster.phlebotomyId as id,"
			+ " phlebotomyMaster.code as code,"
			+ " phlebotomyMaster.desc as desc,"
			+ " phlebotomyMaster.orgId as orgId,"
			+ " phlebotomyMaster.status as status,"
			+ " phlebotomyMaster.createdBy as createdBy,"
			+ " phlebotomyMaster.createdDate as createdDate,"
			+ " phlebotomyMaster.updatedBy as updatedBy,"
			+ " phlebotomyMaster.updatedDate as updatedDate,"
			+ " phlebotomyMaster.unitId as unitId "
			+ " FROM PhlebotomyMaster phlebotomyMaster"
			+ " WHERE phlebotomyMaster.orgId=:orgId "
			+"  AND phlebotomyMaster.unitId=:unitId "
			+ " AND lower(phlebotomyMaster.code) = lower(:code)"),
			
			@NamedQuery(name = "UPDATE_GET_PHLEBOTOMY_BY_CODE", query = "SELECT phlebotomyMaster.phlebotomyId as id,"
					+ " phlebotomyMaster.code as code,"
					+ " phlebotomyMaster.desc as desc,"
					+ " phlebotomyMaster.orgId as orgId,"
					+ " phlebotomyMaster.status as status,"
					+ " phlebotomyMaster.createdBy as createdBy,"
					+ " phlebotomyMaster.createdDate as createdDate,"
					+ " phlebotomyMaster.updatedBy as updatedBy,"
					+ " phlebotomyMaster.updatedDate as updatedDate,"
					+ " phlebotomyMaster.unitId as unitId "
					+ " FROM PhlebotomyMaster phlebotomyMaster"
					+ " WHERE phlebotomyMaster.orgId=:orgId "
					+"  AND phlebotomyMaster.unitId=:unitId "
					+ " AND lower(phlebotomyMaster.code) = lower(:code)"
					+ " AND phlebotomyMaster.phlebotomyId <> :phlebotomyId"),

	@NamedQuery(name = "GET_PAGINATED_PHLEBOTOMY_MASTER_LIST", query = "SELECT phlebotomyMaster.phlebotomyId as id,"
			+ " phlebotomyMaster.code as code,"
			+ " phlebotomyMaster.desc as desc,"
			+ " phlebotomyMaster.orgId as orgId,"
			+ " phlebotomyMaster.status as status,"
			+ " phlebotomyMaster.createdBy as createdBy,"
			+ " phlebotomyMaster.createdDate as createdDate,"
			+ " phlebotomyMaster.updatedBy as updatedBy,"
			+ " phlebotomyMaster.updatedDate as updatedDate"
			+ " FROM PhlebotomyMaster phlebotomyMaster"
			+ " WHERE phlebotomyMaster.orgId = :orgId"
			+ " ORDER BY id DESC")

})

@NamedNativeQueries(
{ @NamedNativeQuery(name = "GET_TOTAL_PHLEBOTOMY_MASTER_RECORD", query = "select count(*) from lab.m_phlebotomy_master where "
	+ "org_id = :orgId")
})
@Entity
@Table(name = "m_phlebotomy_master", schema = "lab")
@SequenceGenerator(name = "m_seq_phlebotomy_master", sequenceName = "lab.m_seq_phlebotomy_master", allocationSize = 1)
public class PhlebotomyMaster
{
	@Id
	@Column(name = "phlebotomy_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "m_seq_phlebotomy_master")
	private Integer phlebotomyId;
	@Column(name="phlebotomy_code")
	private String code;

	@Column(name="phlebotomy_name")
	private String desc;

	@Column(name="phlebotomy_status")
	private char status; 

	@Column(name="created_by")
	private Integer createdBy;

	@Column(name="created_date")
	@Convert(converter = LocalTimeConverter.class)
	private Long createdDate;
	
	@Column(name="updated_by")
	private Integer updatedBy;
	
	@Column(name="updated_date")
	@Convert(converter = LocalTimeConverter.class)
	private Long updatedDate;
	
	@Column(name = "org_id")
	private Integer orgId;
	
	@Column(name = "dept_id")
	private Integer deptId;

	@Column(name = "unit_id")
	private Integer unitId;

	public Integer getPhlebotomyId() {
		return phlebotomyId;
	}

	public void setPhlebotomyId(Integer phlebotomyId) {
		this.phlebotomyId = phlebotomyId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
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

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
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

	public void setUpdatedBy(Integer updatedBy) {
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

	public Integer getDeptId() {
		return deptId;
	}

	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}
	
	

}
