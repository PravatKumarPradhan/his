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

	@NamedQuery(name = "GET_ANTIBIOTICS_TYPE_BY_BACTERIA_ID", query = "SELECT antibioticClassMaster.antibioticClassId as id,"
			+ " antibioticClassMaster.code as code,"
			+ " antibioticClassMaster.desc as desc,"
			+ " antibioticClassMaster.status as status,"
			+ " antibioticClassMaster.orgId as orgId,"
			+ " antibioticClassMaster.createdBy as createdBy,"
			+ " antibioticClassMaster.createdDate as createdDate,"
			+ " antibioticClassMaster.updatedBy as updatedBy,"
			+ " antibioticClassMaster.updatedDate as updatedDate"
			+ " FROM AntibioticClassMaster antibioticClassMaster "
			+ " WHERE antibioticClassMaster.antibioticClassId = :antibioticClassId "
			+ " AND antibioticClassMaster.orgId= :orgId"),
			
		@NamedQuery(name = "GET_ANTIBIOTICS_TYPE_BY_BACTERIA_CODE", query = "SELECT antibioticClassMaster.antibioticClassId as id,"
			+ " antibioticClassMaster.code as code,"
			+ " antibioticClassMaster.desc as desc,"
			+ " antibioticClassMaster.status as status,"
			+ " antibioticClassMaster.orgId as orgId,"
			+ " antibioticClassMaster.createdBy as createdBy,"
			+ " antibioticClassMaster.createdDate as createdDate,"
			+ " antibioticClassMaster.updatedBy as updatedBy,"
			+ " antibioticClassMaster.updatedDate as updatedDate"
			+ " FROM AntibioticClassMaster antibioticClassMaster"
			+ " WHERE antibioticClassMaster.orgId=:orgId "
			+ " AND lower(antibioticClassMaster.code) = lower(:code)"),
			
			@NamedQuery(name = "UPDATE_GET_ANTIBIOTICS_TYPE_BY_BACTERIA_CODE", query = "SELECT antibioticClassMaster.antibioticClassId as id,"
					+ " antibioticClassMaster.code as code,"
					+ " antibioticClassMaster.desc as desc,"
					+ " antibioticClassMaster.status as status,"
					+ " antibioticClassMaster.orgId as orgId,"
					+ " antibioticClassMaster.createdBy as createdBy,"
					+ " antibioticClassMaster.createdDate as createdDate,"
					+ " antibioticClassMaster.updatedBy as updatedBy,"
					+ " antibioticClassMaster.updatedDate as updatedDate"
					+ " FROM AntibioticClassMaster antibioticClassMaster"
					+ " WHERE antibioticClassMaster.orgId=:orgId "
					+ " AND lower(antibioticClassMaster.code) = lower(:code)"
					+ " AND antibioticClassMaster.antibioticClassId <> :antibioticClassId"),
	
	

	@NamedQuery(name = "GET_PAGINATED_ANTIBIOTICS_MASTER_LIST", query = "SELECT antibioticClassMaster.antibioticClassId as id,"
			+ " antibioticClassMaster.code as code,"
			+ " antibioticClassMaster.desc as desc,"
			+ " antibioticClassMaster.status as status,"
			+ " antibioticClassMaster.createdBy as createdBy,"
			+ " antibioticClassMaster.createdDate as createdDate,"
			+ " antibioticClassMaster.updatedBy as updatedBy,"
			+ " antibioticClassMaster.updatedDate as updatedDate"
			+ " FROM AntibioticClassMaster antibioticClassMaster"
			+ " WHERE antibioticClassMaster.orgId = :orgId"
			+ " ORDER BY id DESC"),
			
			@NamedQuery(name = "GET_ANTIBIOTICS_CLASS_LIST_MASTER", 
			query = "SELECT antibioticClassMaster.antibioticClassId as antibioticClassId,"
			+ " antibioticClassMaster.code as code,"
			+ " antibioticClassMaster.desc as desc,"
			+ " antibioticClassMaster.status as status "
			+ " FROM AntibioticClassMaster antibioticClassMaster")
		
	
})

@NamedNativeQueries(
{ @NamedNativeQuery(name = "GET_TOTAL_ANTIBIOTICS_RECORD", query = "select count(*) from lab.m_antibiotic_class_master where "
	+ "org_id = :orgId ")
})
@Entity
@Table(name = "m_antibiotic_class_master", schema = "lab")
@SequenceGenerator(name = "m_seq_antibiotic_class", sequenceName = "lab.m_seq_antibiotic_class", allocationSize = 1)
public class AntibioticClassMaster {
	

	@Id
	@Column(name = "antibiotic_class_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "m_seq_antibiotic_class")
	private int antibioticClassId;
	
	@Column(name = "antibiotic_class_code")
	private String code;
	
	@Column(name = "antibiotic_class_desc")
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
	
	@Column(name = "antibiotic_class_status")
	private Character status;
	
	@Column(name = "org_id")
	private Integer orgId;
	


	/**
	 * @return the antibioticClassId
	 */
	public int getAntibioticClassId() {
		return antibioticClassId;
	}

	/**
	 * @param antibioticClassId the antibioticClassId to set
	 */
	public void setAntibioticClassId(int antibioticClassId) {
		this.antibioticClassId = antibioticClassId;
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
