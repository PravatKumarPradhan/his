package com.param.entity.lis.global;

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

	@NamedQuery(name = "GET_COLONY_TYPE_BY_BACTERIA_ID", query = "SELECT colonyMaster.colonyId as id,"
			+ " colonyMaster.code as code,"
			+ " colonyMaster.desc as desc,"
			+ " colonyMaster.status as status,"
			+ " colonyMaster.orgId as orgId,"
			+ " colonyMaster.createdBy as createdBy,"
			+ " colonyMaster.createdDate as createdDate,"
			+ " colonyMaster.updatedBy as updatedBy,"
			+ " colonyMaster.updatedDate as updatedDate"
			+ " FROM ColonyMaster colonyMaster "
			+ " WHERE colonyMaster.colonyId = :colonyId "
			+ " AND colonyMaster.orgId= :orgId"),
			
		@NamedQuery(name = "GET_COLONY_TYPE_BY_BACTERIA_CODE", query = "SELECT colonyMaster.colonyId as id,"
			+ " colonyMaster.code as code,"
			+ " colonyMaster.desc as desc,"
			+ " colonyMaster.status as status,"
			+ " colonyMaster.createdBy as createdBy,"
			+ " colonyMaster.createdDate as createdDate,"
			+ " colonyMaster.updatedBy as updatedBy,"
			+ " colonyMaster.updatedDate as updatedDate"
			+ " FROM ColonyMaster colonyMaster"
			+ " WHERE colonyMaster.orgId=:orgId "
			+ " AND lower(colonyMaster.code) = lower(:code)"),
			
			
			@NamedQuery(name = "UPDATE_GET_COLONY_TYPE_BY_BACTERIA_CODE", query = "SELECT colonyMaster.colonyId as id,"
					+ " colonyMaster.code as code,"
					+ " colonyMaster.desc as desc,"
					+ " colonyMaster.status as status,"
					+ " colonyMaster.createdBy as createdBy,"
					+ " colonyMaster.createdDate as createdDate,"
					+ " colonyMaster.updatedBy as updatedBy,"
					+ " colonyMaster.updatedDate as updatedDate"
					+ " FROM ColonyMaster colonyMaster"
					+ " WHERE colonyMaster.orgId=:orgId "
					+ " AND lower(colonyMaster.code) = lower(:code)"
					+ " AND colonyMaster.colonyId <> :colonyId"),
	

	@NamedQuery(name = "GET_PAGINATED_COLONY_MASTER_LIST", query = "SELECT colonyMaster.colonyId as id,"
			+ " colonyMaster.code as code,"
			+ " colonyMaster.desc as desc,"
			+ " colonyMaster.status as status,"
			+ " colonyMaster.createdBy as createdBy,"
			+ " colonyMaster.createdDate as createdDate,"
			+ " colonyMaster.updatedBy as updatedBy,"
			+ " colonyMaster.updatedDate as updatedDate"
			+ " FROM ColonyMaster colonyMaster"
			+ " WHERE colonyMaster.orgId = :orgId"
			+ " ORDER BY id DESC")

})

@NamedNativeQueries(
{ @NamedNativeQuery(name = "GET_TOTAL_COLONY_RECORD", query = "select count(*) from lab.m_colony_master where "
	+ "org_id = :orgId ")
})
@Entity
@Table(name = "m_colony_master", schema = "lab")
@SequenceGenerator(name = "m_seq_colony_master", sequenceName = "lab.m_seq_colony_master", allocationSize = 1)
public class ColonyMaster {
	@Id
	@Column(name = "colony_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "m_seq_colony_master")
	private int colonyId;
	
	@Column(name = "colony_code")
	private String code;
	
	@Column(name = "colony_name")
	private String desc;
	
	@Column(name = "colony_status")
	private Character status;
	@Column(name = "created_by")
	private Integer createdBy;

	@Column(name = "created_date")
	@Convert(converter = LocalTimeConverter.class)
	private Long createdDate;

	@Column(name = "updated_by")
	private Integer updatedBy;

	@Column(name = "updated_date")
	@Convert(converter = LocalTimeConverter.class)
	private Long updatedDate;
	
	@Column(name = "org_id")
	private Integer orgId;
	

	/**
	 * @return the colonyId
	 */
	public int getColonyId() {
		return colonyId;
	}

	/**
	 * @param colonyId the colonyId to set
	 */
	public void setColonyId(int colonyId) {
		this.colonyId = colonyId;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the desc
	 */
	public String getDesc() {
		return desc;
	}

	/**
	 * @param desc the desc to set
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}

	/**
	 * @return the status
	 */
	public Character getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(Character status) {
		this.status = status;
	}

	/**
	 * @return the createdBy
	 */
	public Integer getCreatedBy() {
		return createdBy;
	}

	/**
	 * @param createdBy the createdBy to set
	 */
	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * @return the createdDate
	 */
	public Long getCreatedDate() {
		return createdDate;
	}

	/**
	 * @param createdDate the createdDate to set
	 */
	public void setCreatedDate(Long createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * @return the updatedBy
	 */
	public Integer getUpdatedBy() {
		return updatedBy;
	}

	/**
	 * @param updatedBy the updatedBy to set
	 */
	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}

	/**
	 * @return the updatedDate
	 */
	public Long getUpdatedDate() {
		return updatedDate;
	}

	/**
	 * @param updatedDate the updatedDate to set
	 */
	public void setUpdatedDate(Long updatedDate) {
		this.updatedDate = updatedDate;
	}

	/**
	 * @return the orgId
	 */
	public Integer getOrgId() {
		return orgId;
	}

	/**
	 * @param orgId the orgId to set
	 */
	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	

	
}
