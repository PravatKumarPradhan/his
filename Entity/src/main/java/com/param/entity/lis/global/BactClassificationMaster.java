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

	@NamedQuery(name = "GET_REPORT_TYPE_BY_BACTERIA_CLASSIFICATION_ID", query = "SELECT bactClassificationMaster.bactClassificationId as id,"
			+ " bactClassificationMaster.code as code,"
			+ " bactClassificationMaster.desc as desc,"
			+ " bactClassificationMaster.status as status,"
			+ " bactClassificationMaster.orgId as orgId,"
			+ " bactClassificationMaster.createdBy as createdBy,"
			+ " bactClassificationMaster.createdDate as createdDate,"
			+ " bactClassificationMaster.updatedBy as updatedBy,"
			+ " bactClassificationMaster.updatedDate as updatedDate"
			+ " FROM BactClassificationMaster bactClassificationMaster "
			+ " WHERE bactClassificationMaster.bactClassificationId = :bactClassificationId"
			+ " AND bactClassificationMaster.orgId= :orgId"),
			
		@NamedQuery(name = "GET_REPORT_TYPE_BY_BACTERIA_CLASSIFICATION_CODE", query = "SELECT bactClassificationMaster.bactClassificationId as id,"
			+ " bactClassificationMaster.code as code,"
			+ " bactClassificationMaster.desc as desc,"
			+ " bactClassificationMaster.status as status,"
			+ " bactClassificationMaster.createdBy as createdBy,"
			+ " bactClassificationMaster.createdDate as createdDate,"
			+ " bactClassificationMaster.updatedBy as updatedBy,"
			+ " bactClassificationMaster.updatedDate as updatedDate"
			+ " FROM BactClassificationMaster bactClassificationMaster "
			+ " WHERE bactClassificationMaster.orgId=:orgId "
			+ " AND lower(bactClassificationMaster.code) = lower(:code)"),
			
			@NamedQuery(name = "UPDATE_GET_REPORT_TYPE_BY_BACTERIA_CLASSIFICATION_CODE", query = "SELECT bactClassificationMaster.bactClassificationId as id,"
					+ " bactClassificationMaster.code as code,"
					+ " bactClassificationMaster.desc as desc,"
					+ " bactClassificationMaster.status as status,"
					+ " bactClassificationMaster.createdBy as createdBy,"
					+ " bactClassificationMaster.createdDate as createdDate,"
					+ " bactClassificationMaster.updatedBy as updatedBy,"
					+ " bactClassificationMaster.updatedDate as updatedDate"
					+ " FROM BactClassificationMaster bactClassificationMaster "
					+ " WHERE bactClassificationMaster.orgId=:orgId "
					+ " AND lower(bactClassificationMaster.code) = lower(:code)"
					+ " AND bactClassificationMaster.bactClassificationId <> :bactClassificationId"),
	
	

	@NamedQuery(name = "GET_PAGINATED_BACTERIA_CLASSIFICATION_MASTER_LIST", query = "SELECT bactClassificationMaster.bactClassificationId as id,"
			+ " bactClassificationMaster.code as code,"
			+ " bactClassificationMaster.desc as desc,"
			+ " bactClassificationMaster.status as status,"
			+ " bactClassificationMaster.createdBy as createdBy,"
			+ " bactClassificationMaster.createdDate as createdDate,"
			+ " bactClassificationMaster.updatedBy as updatedBy,"
			+ " bactClassificationMaster.updatedDate as updatedDate"
			+ " FROM BactClassificationMaster bactClassificationMaster "
			+ " WHERE bactClassificationMaster.orgId = :orgId"
			+ " ORDER BY id DESC")

})

@NamedNativeQueries(
{ @NamedNativeQuery(name = "GET_TOTAL_BACTERIA_CLASSIFICATION_RECORD", query = "select count(*) from lab.m_bact_classification_mst where "
	+ "org_id = :orgId ")
})
@Entity
@Table(name = "m_bact_classification_mst", schema = "lab")
@SequenceGenerator(name = "m_seq_bact_classification_mst", sequenceName = "lab.m_seq_bact_classification_mst", allocationSize = 1)
public class BactClassificationMaster {
	@Id
	@Column(name = "bact_classification_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "m_seq_bact_classification_mst")
	private int bactClassificationId;
	
	@Column(name = "bact_classification_code")
	private String code;
	
	@Column(name = "bact_classification_name")
	private String desc;

	@Column(name = "bact_classification_status")
	private Character status;
	@Column(name = "created_by")
	private Integer createdBy;

	@Column(name = "craeted_date")
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
	 * @return the bactClassificationId
	 */
	public int getBactClassificationId() {
		return bactClassificationId;
	}

	/**
	 * @param bactClassificationId the bactClassificationId to set
	 */
	public void setBactClassificationId(int bactClassificationId) {
		this.bactClassificationId = bactClassificationId;
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
