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

	@NamedQuery(name = "GET_REPORT_TYPE_BY_BACTERIA_ID", query = "SELECT bacteriaMaster.bacteriaId as id,"
			+ " bacteriaMaster.code as code,"
			+ " bacteriaMaster.desc as desc,"
			+ " bacteriaMaster.status as status,"
			+ " bacteriaMaster.orgId as orgId,"
			+ " bacteriaMaster.createdBy as createdBy,"
			+ " bacteriaMaster.createdDate as createdDate,"
			+ " bacteriaMaster.updatedBy as updatedBy,"
			+ " bacteriaMaster.updatedDate as updatedDate "
			+ " FROM BacteriaMaster bacteriaMaster "
			+ " WHERE bacteriaMaster.bacteriaId = :bacteriaId "
			+ " AND bacteriaMaster.orgId= :orgId"),
			
		@NamedQuery(name = "GET_REPORT_TYPE_BY_BACTERIA_CODE", query = "SELECT bacteriaMaster.bacteriaId as id,"
			+ " bacteriaMaster.code as code,"
			+ " bacteriaMaster.desc as desc,"
			+ " bacteriaMaster.status as status,"
			+ " bacteriaMaster.createdBy as createdBy,"
			+ " bacteriaMaster.createdDate as createdDate,"
			+ " bacteriaMaster.updatedBy as updatedBy,"
			+ " bacteriaMaster.updatedDate as updatedDate "
			+ " FROM BacteriaMaster bacteriaMaster "
			+ " WHERE bacteriaMaster.orgId=:orgId "
			+ " AND lower(bacteriaMaster.code) = lower(:code)"),
	
			@NamedQuery(name = "UPDATE_GET_REPORT_TYPE_BY_BACTERIA_CODE", query ="SELECT bacteriaMaster.bacteriaId as id,"
					+ " bacteriaMaster.code as code,"
					+ " bacteriaMaster.desc as desc,"
					+ " bacteriaMaster.status as status,"
					+ " bacteriaMaster.createdBy as createdBy,"
					+ " bacteriaMaster.createdDate as createdDate,"
					+ " bacteriaMaster.updatedBy as updatedBy,"
					+ " bacteriaMaster.updatedDate as updatedDate "
					+ " FROM BacteriaMaster bacteriaMaster "
					+ " WHERE bacteriaMaster.orgId=:orgId "
					+ " AND lower(bacteriaMaster.code) = lower(:code)"
					+ " AND bacteriaMaster.bacteriaId <> :bacteriaId"),
					
	@NamedQuery(name = "GET_PAGINATED_BACTERIA_MASTER_LIST", query = "SELECT bacteriaMaster.bacteriaId as id,"
			+ " bacteriaMaster.code as code,"
			+ " bacteriaMaster.desc as desc,"
			+ " bacteriaMaster.status as status,"
			+ " bacteriaMaster.createdBy as createdBy,"
			+ " bacteriaMaster.createdDate as createdDate,"
			+ " bacteriaMaster.updatedBy as updatedBy,"
			+ " bacteriaMaster.updatedDate as updatedDate "
			+ " FROM BacteriaMaster bacteriaMaster "
			+ " WHERE bacteriaMaster.orgId = :orgId"
			+ " ORDER BY id DESC")

})

@NamedNativeQueries(
{ @NamedNativeQuery(name = "GET_TOTAL_BACTERIA_RECORD", query = "select count(*) from lab.m_bacteria_master where "
	+ "org_id = :orgId ")
})
@Entity
@Table(name = "m_bacteria_master", schema = "lab")
@SequenceGenerator(name = "m_seq_bacteria_master", sequenceName = "lab.m_seq_bacteria_master", allocationSize = 1)
public class BacteriaMaster {
	

	@Id
	@Column(name = "bacteria_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "m_seq_bacteria_master")
	private int bacteriaId;
	
	@Column(name = "bacteria_code")
	private String code;
	
	@Column(name = "bacteria_name")
	private String desc;

	
	@Column(name = "bacteria_status")
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
	 * @return the bacteriaId
	 */
	public int getBacteriaId() {
		return bacteriaId;
	}

	/**
	 * @param bacteriaId the bacteriaId to set
	 */
	public void setBacteriaId(int bacteriaId) {
		this.bacteriaId = bacteriaId;
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
