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

	@NamedQuery(name = "GET_REPORT_TYPE_BY_ANTIBIOTIC_GROUP_ID", query = "SELECT antibioticGroupMaster.antibioticGroupId as id,"
			+ " antibioticGroupMaster.code as code,"
			+ " antibioticGroupMaster.desc as desc,"
			+ " antibioticGroupMaster.status as status,"
			+ " antibioticGroupMaster.orgId as orgId,"
			+ " antibioticGroupMaster.createdBy as createdBy,"
			+ " antibioticGroupMaster.createdDate as createdDate,"
			+ " antibioticGroupMaster.updatedBy as updatedBy,"
			+ " antibioticGroupMaster.updatedDate as updatedDate "
			+ " FROM AntibioticGroupMaster antibioticGroupMaster "
			+ " WHERE antibioticGroupMaster.antibioticGroupId = :antibioticGroupId "
			+ " AND antibioticGroupMaster.orgId= :orgId"),
			
		@NamedQuery(name = "GET_REPORT_TYPE_BY_ANTIBIOTIC_GROUP_CODE", query = "SELECT antibioticGroupMaster.antibioticGroupId as id,"
			+ " antibioticGroupMaster.code as code,"
			+ " antibioticGroupMaster.desc as desc,"
			+ " antibioticGroupMaster.status as status,"
			+ " antibioticGroupMaster.createdBy as createdBy,"
			+ " antibioticGroupMaster.createdDate as createdDate,"
			+ " antibioticGroupMaster.updatedBy as updatedBy,"
			+ " antibioticGroupMaster.updatedDate as updatedDate "
			+ " FROM AntibioticGroupMaster antibioticGroupMaster "
			+ " WHERE antibioticGroupMaster.orgId=:orgId "
			+ " AND lower(antibioticGroupMaster.code) = lower(:code)"),
			
			@NamedQuery(name = "UPDATE_GET_REPORT_TYPE_BY_ANTIBIOTIC_GROUP_CODE", query = "SELECT antibioticGroupMaster.antibioticGroupId as id,"
					+ " antibioticGroupMaster.code as code,"
					+ " antibioticGroupMaster.desc as desc,"
					+ " antibioticGroupMaster.status as status,"
					+ " antibioticGroupMaster.createdBy as createdBy,"
					+ " antibioticGroupMaster.createdDate as createdDate,"
					+ " antibioticGroupMaster.updatedBy as updatedBy,"
					+ " antibioticGroupMaster.updatedDate as updatedDate "
					+ " FROM AntibioticGroupMaster antibioticGroupMaster "
					+ " WHERE antibioticGroupMaster.orgId=:orgId "
					+ " AND lower(antibioticGroupMaster.code) = lower(:code)"
					+ " AND antibioticGroupMaster.antibioticGroupId <> :antibioticGroupId"),

	@NamedQuery(name = "GET_PAGINATED_ANTIBIOTIC_GROUP_MASTER_LIST", query = "SELECT antibioticGroupMaster.antibioticGroupId as id,"
			+ " antibioticGroupMaster.code as code,"
			+ " antibioticGroupMaster.desc as desc,"
			+ " antibioticGroupMaster.status as status,"
			+ " antibioticGroupMaster.createdBy as createdBy,"
			+ " antibioticGroupMaster.createdDate as createdDate,"
			+ " antibioticGroupMaster.updatedBy as updatedBy,"
			+ " antibioticGroupMaster.updatedDate as updatedDate "
			+ " FROM AntibioticGroupMaster antibioticGroupMaster "
			+ " WHERE antibioticGroupMaster.orgId = :orgId"
			+ " ORDER BY id DESC")

})

@NamedNativeQueries(
{ @NamedNativeQuery(name = "GET_TOTAL_ANTIBIOTIC_GROUP_RECORD", query = "select count(*) from lab.m_antibiotic_group_master where "
	+ "org_id = :orgId ")
})
@Entity
@Table(name = "m_antibiotic_group_master", schema = "lab")
@SequenceGenerator(name = "m_seq_antibiotic_group", sequenceName = "lab.m_seq_antibiotic_group", allocationSize = 1)
public class AntibioticGroupMaster {
	
	@Id
	@Column(name = "antibiotic_group_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "m_seq_antibiotic_group")
	private int antibioticGroupId;
	
	@Column(name = "antibiotic_group_code")
	private String code;
	
	@Column(name = "antibiotic_group_desc")
	private String desc;

	@Column(name = "created_by")
	private Integer createdBy;
	
	@Column(name = "crated_date")
	@Convert(converter = LocalTimeConverter.class)
	private Long createdDate;

	@Column(name = "updated_date")
	@Convert(converter = LocalTimeConverter.class)
	private Long updatedDate;
	
	@Column(name = "updated_by")
	private Integer updatedBy;
	
	@Column(name = "antibiotic_group_status")
	private Character status;
	
	@Column(name = "org_id")
	private Integer orgId;
	

	/**
	 * @return the antibioticGroupId
	 */
	public int getAntibioticGroupId() {
		return antibioticGroupId;
	}

	/**
	 * @param antibioticGroupId the antibioticGroupId to set
	 */
	public void setAntibioticGroupId(int antibioticGroupId) {
		this.antibioticGroupId = antibioticGroupId;
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
