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
	@NamedQuery(name = "GET_STORAGE_LOCATION_MASTER_BY_DEPT_ID", query = "SELECT storageLocationMaster.storageLocationId as id, "
			+ " storageLocationMaster.code as code,"
			+ " storageLocationMaster.desc as desc,"
			+ " storageLocationMaster.status as status,"
			+ " storageLocationMaster.orgId as orgId,"
			+ " storageLocationMaster.createdBy as createdBy,"
			+ " storageLocationMaster.createdDate as createdDate,"
			+ " storageLocationMaster.updatedBy as updatedBy,"
			+ " storageLocationMaster.unitId as unitId,"
			+ " storageLocationMaster.updatedDate as updatedDate"
			+ " FROM StorageLocationMaster storageLocationMaster"
			+ " WHERE storageLocationMaster.storageLocationId = :storageLocationId "
			+ " AND storageLocationMaster.orgId= :orgId"
			+ " AND storageLocationMaster.unitId= :unitId"),
	
	@NamedQuery(name = "GET_STORAGE_LOCATION_MASTER_BY_CODE", query = "SELECT storageLocationMaster.storageLocationId as id,"
			+ " storageLocationMaster.code as code,"
			+ " storageLocationMaster.desc as desc,"
			+ " storageLocationMaster.status as status,"
			+ " storageLocationMaster.createdBy as createdBy,"
			+ " storageLocationMaster.createdDate as createdDate,"
			+ " storageLocationMaster.updatedBy as updatedBy,"
			+ " storageLocationMaster.unitId as unitId,"
			+ " storageLocationMaster.updatedDate as updatedDate"
			+ " FROM StorageLocationMaster storageLocationMaster"
			+ " WHERE storageLocationMaster.orgId=:orgId "
			+"  AND storageLocationMaster.unitId=:unitId "
			+ " AND lower(storageLocationMaster.code) = lower(:code)"),
			
			@NamedQuery(name = "UPDATE_GET_STORAGE_LOCATION_MASTER_BY_CODE", query = "SELECT storageLocationMaster.storageLocationId as id,"
					+ " storageLocationMaster.code as code,"
					+ " storageLocationMaster.desc as desc,"
					+ " storageLocationMaster.status as status,"
					+ " storageLocationMaster.createdBy as createdBy,"
					+ " storageLocationMaster.createdDate as createdDate,"
					+ " storageLocationMaster.updatedBy as updatedBy,"
					+ " storageLocationMaster.unitId as unitId,"
					+ " storageLocationMaster.updatedDate as updatedDate"
					+ " FROM StorageLocationMaster storageLocationMaster"
					+ " WHERE storageLocationMaster.orgId=:orgId "
					+"  AND storageLocationMaster.unitId=:unitId "
					+ " AND lower(storageLocationMaster.code) = lower(:code)"
					+ " AND storageLocationMaster.storageLocationId <> :storageLocationId"),

	@NamedQuery(name = "GET_PAGINATED_STORAGE_LOCATION_MASTER_MASTER_LIST", query = "SELECT storageLocationMaster.storageLocationId as id,"
			+ " storageLocationMaster.code as code,"
			+ " storageLocationMaster.desc as desc,"
			+ " storageLocationMaster.status as status,"
			+ " storageLocationMaster.createdBy as createdBy,"
			+ " storageLocationMaster.createdDate as createdDate,"
			+ " storageLocationMaster.updatedBy as updatedBy,"
			+ " storageLocationMaster.updatedDate as updatedDate"
			+ " FROM StorageLocationMaster storageLocationMaster"
			+ " WHERE storageLocationMaster.orgId = :orgId"
			+ " ORDER BY id DESC")

})

@NamedNativeQueries(
{ @NamedNativeQuery(name = "GET_TOTAL_STORAGE_LOCATION_MASTER_RECORD", query = "select count(*) from lab.m_storage_location_master where "
	+ "org_id = :orgId")
})
@Entity
@Table(name = "m_storage_location_master", schema = "lab")
@SequenceGenerator(name = "m_seq_storage_location_master", sequenceName = "lab.m_seq_storage_location_master", allocationSize = 1)
public class StorageLocationMaster
{
	@Id
	@Column(name = "storage_location_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "m_seq_storage_location_master")
	private int storageLocationId;
	@Column(name="storage_location_code")
	private String code;

	@Column(name="storage_location_name")
	private String desc;

	@Column(name="storage_location_status")
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

	public int getStorageLocationId() {
		return storageLocationId;
	}

	public void setStorageLocationId(int storageLocationId) {
		this.storageLocationId = storageLocationId;
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
