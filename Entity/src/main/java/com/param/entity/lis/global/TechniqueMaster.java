package com.param.entity.lis.global;

import java.util.List;

import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import com.param.entity.lis.unit.TestMaster;
import com.param.entity.lis.common.LocalTimeConverter;

@NamedQueries({
		@NamedQuery(name = "GET_TECHNIQUE_BY_SAMPLE_ID", query = "SELECT techniqueMaster.techniqueId as id, "
				+ " techniqueMaster.code as code,"
				+ " techniqueMaster.desc as desc,"
				+ " techniqueMaster.status as status,"
				+ " techniqueMaster.orgId as orgId,"
				+ " techniqueMaster.createdBy as createdBy,"
				+ " techniqueMaster.createdDate as createdDate,"
				+ " techniqueMaster.updatedBy as updatedBy,"
				+ " techniqueMaster.updatedDate as updatedDate"
				+ " FROM TechniqueMaster techniqueMaster"
				+ " WHERE techniqueMaster.techniqueId = :techniqueId "
				+ " AND techniqueMaster.orgId= :orgId"),
		
		@NamedQuery(name = "GET_TECHNIQUE_BY_CODE", query = "SELECT techniqueMaster.techniqueId as id,"
				+ " techniqueMaster.code as code,"
				+ " techniqueMaster.desc as desc,"
				+ " techniqueMaster.status as status,"
				+ " techniqueMaster.createdBy as createdBy,"
				+ " techniqueMaster.createdDate as createdDate,"
				+ " techniqueMaster.updatedBy as updatedBy,"
				+ " techniqueMaster.updatedDate as updatedDate"
				+ " FROM TechniqueMaster techniqueMaster"
				+ " WHERE techniqueMaster.orgId=:orgId "
				+ " AND lower(techniqueMaster.code) = lower(:code)"),
				
				@NamedQuery(name = "UPDATE_GET_TECHNIQUE_BY_CODE", query =  "SELECT techniqueMaster.techniqueId as id,"
						+ " techniqueMaster.code as code,"
						+ " techniqueMaster.desc as desc,"
						+ " techniqueMaster.status as status,"
						+ " techniqueMaster.createdBy as createdBy,"
						+ " techniqueMaster.createdDate as createdDate,"
						+ " techniqueMaster.updatedBy as updatedBy,"
						+ " techniqueMaster.updatedDate as updatedDate"
						+ " FROM TechniqueMaster techniqueMaster"
						+ " WHERE techniqueMaster.orgId=:orgId "
						+ " AND lower(techniqueMaster.code) = lower(:code)"
						+ " AND techniqueMaster.techniqueId <> :techniqueId"),


		@NamedQuery(name = "GET_PAGINATED_TECHNIQUE_MASTER_LIST", query = "SELECT techniqueMaster.techniqueId as id,"
				+ " techniqueMaster.code as code,"
				+ " techniqueMaster.desc as desc,"
				+ " techniqueMaster.status as status,"
				+ " techniqueMaster.createdBy as createdBy,"
				+ " techniqueMaster.createdDate as createdDate,"
				+ " techniqueMaster.updatedBy as updatedBy,"
				+ " techniqueMaster.updatedDate as updatedDate"
				+ " FROM TechniqueMaster techniqueMaster"
				+ " WHERE techniqueMaster.orgId = :orgId"
				+ " ORDER BY id DESC")

})
	
@NamedNativeQueries(
{ @NamedNativeQuery(name = "GET_TOTAL_TECHNIQUE_RECORD", query = "select count(*) from lab.m_technique_master where "
		+ "org_id = :orgId")
})
@Entity
@Table(name = "m_technique_master", schema = "lab")
@SequenceGenerator(name = "m_seq_technique_master", sequenceName = "lab.m_seq_technique_master", allocationSize = 1)
public class TechniqueMaster {
	@Id
	@Column(name = "technique_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "m_seq_technique_master")
	private int techniqueId;
	@Column(name = "technique_name")
	private String desc;
	
	@Column(name = "technique_code")
	private String code;
	
	@Column(name = "technique_status")
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

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "techniqueMaster")
	private List<TestMaster> listTestMaster;
	
	/**
	 * @return the techniqueId
	 */
	public int getTechniqueId() {
		return techniqueId;
	}

	/**
	 * @param techniqueId the techniqueId to set
	 */
	public void setTechniqueId(int techniqueId) {
		this.techniqueId = techniqueId;
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

	public List<TestMaster> getListTestMaster()
	{
		return listTestMaster;
	}

	public void setListTestMaster(List<TestMaster> listTestMaster)
	{
		this.listTestMaster = listTestMaster;
	}



}
