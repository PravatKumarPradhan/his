package com.param.entity.lis.global;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import com.param.entity.lis.common.LocalTimeConverter;
@NamedQueries({
	@NamedQuery(name = "GET_SENSITIVITY_TYPE_RESULT_BY_SENSITIVITY_ID", query = "SELECT sensitivityMaster.sensitivityId as id,"
			+ " sensitivityMaster.code as code,"
			+ " sensitivityMaster.desc as desc,"
			+ " sensitivityMaster.status as status,"
			+ " sensitivityMaster.orgId as orgId,"
			+ " sensitivityMaster.createdBy as createdBy,"
			+ " sensitivityMaster.createdDate as createdDate,"
			+ " sensitivityMaster.updatedBy as updatedBy,"
			+ " sensitivityMaster.updatedDate as updatedDate"
			+ " FROM SensitivityMaster sensitivityMaster "
			+ " WHERE sensitivityMaster.sensitivityId = :sensitivityId "
			+ " AND sensitivityMaster.orgId= :orgId"),
			
		@NamedQuery(name = "GET_SENSITIVITY_TYPE_RESULT_BY_SENSITIVITY_CODE", query = "SELECT sensitivityMaster.sensitivityId as id,"
			+ " sensitivityMaster.code as code,"
			+ " sensitivityMaster.desc as desc,"
			+ " sensitivityMaster.status as status,"
			+ " sensitivityMaster.createdBy as createdBy,"
			+ " sensitivityMaster.createdDate as createdDate,"
			+ " sensitivityMaster.updatedBy as updatedBy,"
			+ " sensitivityMaster.updatedDate as updatedDate"
			+ " FROM SensitivityMaster sensitivityMaster"
			+ " WHERE sensitivityMaster.orgId=:orgId "
			+ " AND lower(sensitivityMaster.code) = lower(:code)"),
			
			@NamedQuery(name = "UPDATE_GET_SENSITIVITY_TYPE_RESULT_BY_SENSITIVITY_CODE", query = "SELECT sensitivityMaster.sensitivityId as id,"
					+ " sensitivityMaster.code as code,"
					+ " sensitivityMaster.desc as desc,"
					+ " sensitivityMaster.status as status,"
					+ " sensitivityMaster.createdBy as createdBy,"
					+ " sensitivityMaster.createdDate as createdDate,"
					+ " sensitivityMaster.updatedBy as updatedBy,"
					+ " sensitivityMaster.updatedDate as updatedDate"
					+ " FROM SensitivityMaster sensitivityMaster"
					+ " WHERE sensitivityMaster.orgId=:orgId "
					+ " AND lower(sensitivityMaster.code) = lower(:code)"
					+ " AND sensitivityMaster.sensitivityId <> :sensitivityId"),

	@NamedQuery(name = "GET_SENSITIVITY_TYPE_RESULT_MASTER_LIST", query = "SELECT sensitivityMaster.sensitivityId as id,"
			+ " sensitivityMaster.code as code,"
			+ " sensitivityMaster.desc as desc,"
			+ " sensitivityMaster.status as status,"
			+ " sensitivityMaster.createdBy as createdBy,"
			+ " sensitivityMaster.createdDate as createdDate,"
			+ " sensitivityMaster.updatedBy as updatedBy,"
			+ " sensitivityMaster.updatedDate as updatedDate"
			+ " FROM SensitivityMaster sensitivityMaster"
			+ " WHERE sensitivityMaster.orgId = :orgId"
			+ " ORDER BY id DESC")

})

@NamedNativeQueries(
{ @NamedNativeQuery(name = "GET_TOTAL_SENSITIVITY_TYPE_RESULT_RECORD", query = "select count(*) from lab.m_sensitivity_tst_result_mst where "
	+ "org_id = :orgId ")
})
@Entity
@Table(name = "m_sensitivity_tst_result_mst", schema = "lab")
@SequenceGenerator(name = "m_seq_sen_test_result", sequenceName = "lab.m_seq_sen_test_result", allocationSize = 1)
public class SensitivityMaster {
	@Id
	@Column(name = "sensitivity_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "m_seq_sen_test_result")
	private int sensitivityId;
	@Column(name = "sensitivity_code")
	private String code;
	
	@Column(name = "sensitivity_desc")
	private String desc;
	
	@Column(name = "sensitivity_status")
	private Character status;
	@Column(name = "created_by")
	private Integer createdBy;

	@Column(name = "created_date")
	@Convert(converter = LocalTimeConverter.class)
	private Long createdDate;

	@Column(name = "updated_by")
	private  Integer updatedBy;

	@Column(name = "updated_date")
	@Convert(converter = LocalTimeConverter.class)
	private Long updatedDate;
	
	@Column(name = "org_id")
	private Integer orgId;
	

	/**
	 * @return the sensitivityId
	 */
	public int getSensitivityId() {
		return sensitivityId;
	}

	/**
	 * @param sensitivityId the sensitivityId to set
	 */
	public void setSensitivityId(int sensitivityId) {
		this.sensitivityId = sensitivityId;
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
	public  Integer getUpdatedBy() {
		return updatedBy;
	}

	/**
	 * @param updatedBy the updatedBy to set
	 */
	public void setUpdatedBy( Integer updatedBy) {
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
