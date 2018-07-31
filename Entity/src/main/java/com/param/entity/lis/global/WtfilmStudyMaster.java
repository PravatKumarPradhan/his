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
	@NamedQuery(name = "GET_WTFILMSTUDY_TYPE_BY_WTFILMSTUDY_ID", query = "SELECT wtfilmStudyMaster.wetfilmStudyId as id,"
			+ " wtfilmStudyMaster.code as code,"
			+ " wtfilmStudyMaster.desc as desc,"
			+ " wtfilmStudyMaster.status as status,"
			+ " wtfilmStudyMaster.orgId as orgId,"
			+ " wtfilmStudyMaster.createdBy as createdBy,"
			+ " wtfilmStudyMaster.createdDate as createdDate,"
			+ " wtfilmStudyMaster.updatedBy as updatedBy,"
			+ " wtfilmStudyMaster.updatedDate as updatedDate"
			+ " FROM WtfilmStudyMaster wtfilmStudyMaster "
			+ " WHERE wtfilmStudyMaster.wetfilmStudyId = :wetfilmStudyId "
			+ " AND wtfilmStudyMaster.orgId= :orgId"),
			
		@NamedQuery(name = "GET_WTFILMSTUDY_TYPE_BY_WTFILMSTUDY_CODE", query = "SELECT wtfilmStudyMaster.wetfilmStudyId as id,"
			+ " wtfilmStudyMaster.code as code,"
			+ " wtfilmStudyMaster.desc as desc,"
			+ " wtfilmStudyMaster.status as status,"
			+ " wtfilmStudyMaster.orgId as orgId,"
			+ " wtfilmStudyMaster.createdBy as createdBy,"
			+ " wtfilmStudyMaster.createdDate as createdDate,"
			+ " wtfilmStudyMaster.updatedBy as updatedBy,"
			+ " wtfilmStudyMaster.updatedDate as updatedDate"
			+ " FROM WtfilmStudyMaster wtfilmStudyMaster"
			+ " WHERE wtfilmStudyMaster.orgId=:orgId "
			+ " AND lower(wtfilmStudyMaster.code) = lower(:code)"),
			
			@NamedQuery(name = "UPDATE_GET_WTFILMSTUDY_TYPE_BY_WTFILMSTUDY_CODE", query = "SELECT wtfilmStudyMaster.wetfilmStudyId as id,"
					+ " wtfilmStudyMaster.code as code,"
					+ " wtfilmStudyMaster.desc as desc,"
					+ " wtfilmStudyMaster.status as status,"
					+ " wtfilmStudyMaster.orgId as orgId,"
					+ " wtfilmStudyMaster.createdBy as createdBy,"
					+ " wtfilmStudyMaster.createdDate as createdDate,"
					+ " wtfilmStudyMaster.updatedBy as updatedBy,"
					+ " wtfilmStudyMaster.updatedDate as updatedDate"
					+ " FROM WtfilmStudyMaster wtfilmStudyMaster"
					+ " WHERE wtfilmStudyMaster.orgId=:orgId "
					+ " AND lower(wtfilmStudyMaster.code) = lower(:code)"
					+ " AND wtfilmStudyMaster.wetfilmStudyId <> :wetfilmStudyId"),
			
	

	@NamedQuery(name = "GET_PAGINATED_WTFILMSTUDY_MASTER_LIST", query = "SELECT wtfilmStudyMaster.wetfilmStudyId as id,"
			+ " wtfilmStudyMaster.code as code,"
			+ " wtfilmStudyMaster.desc as desc,"
			+ " wtfilmStudyMaster.status as status,"
			+ " wtfilmStudyMaster.orgId as orgId,"
			+ " wtfilmStudyMaster.createdBy as createdBy,"
			+ " wtfilmStudyMaster.createdDate as createdDate,"
			+ " wtfilmStudyMaster.updatedBy as updatedBy,"
			+ " wtfilmStudyMaster.updatedDate as updatedDate"
			+ " FROM WtfilmStudyMaster wtfilmStudyMaster"
			+ " WHERE wtfilmStudyMaster.orgId = :orgId" 
			+ " ORDER BY id DESC")

})

@NamedNativeQueries(
{ @NamedNativeQuery(name = "GET_TOTAL_WTFILMSTUDY_RECORD", query = "select count(*) from lab.m_wetfilm_study_master where "
	+ "org_id = :orgId ")
})
@Entity
@Table(name = "m_wetfilm_study_master", schema = "lab")
@SequenceGenerator(name = "m_seq_wetfilm_study_master", sequenceName = "lab.m_seq_wetfilm_study_master", allocationSize = 1)
public class WtfilmStudyMaster {
	@Id
	@Column(name = "wetfilm_study_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "m_seq_wetfilm_study_master")
	private int wetfilmStudyId;
	@Column(name = "wetfilm_study_code")
	private String code;
	
	@Column(name = "wetfilm_study_name")
	private String desc;
	
	@Column(name = "wetfilm_study_status")
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
	 * @return the wetfilmStudyId
	 */
	public int getWetfilmStudyId() {
		return wetfilmStudyId;
	}

	/**
	 * @param wetfilmStudyId the wetfilmStudyId to set
	 */
	public void setWetfilmStudyId(int wetfilmStudyId) {
		this.wetfilmStudyId = wetfilmStudyId;
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

