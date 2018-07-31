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

@NamedQueries(
{
		// Prerequisites

		@NamedQuery(name = "GET_PREREQUISITES_BY_PREREQUISITES_ID", query = "SELECT prerequisitesMaster.prerequisitesId as id, "
				+ " prerequisitesMaster.code as code," + " prerequisitesMaster.desc as desc,"
				+ " prerequisitesMaster.status as status," + " prerequisitesMaster.orgId as orgId,"
				+ " prerequisitesMaster.createdBy as createdBy," + " prerequisitesMaster.createdDate as createdDate,"
				+ " prerequisitesMaster.updatedBy as updatedBy," + " prerequisitesMaster.updatedDate as updatedDate"
				+ " FROM PrerequisitesMaster prerequisitesMaster"
				+ " WHERE prerequisitesMaster.prerequisitesId = :prerequisitesId "
				+ " AND prerequisitesMaster.orgId= :orgId"),

		@NamedQuery(name = "GET_PREREQUISITES_BY_CODE", query = "SELECT prerequisitesMaster.prerequisitesId as id,"
				+ " prerequisitesMaster.code as code," + " prerequisitesMaster.desc as desc,"
				+ " prerequisitesMaster.status as status," + " prerequisitesMaster.createdBy as createdBy,"
				+ " prerequisitesMaster.createdDate as createdDate," + " prerequisitesMaster.updatedBy as updatedBy,"
				+ " prerequisitesMaster.updatedDate as updatedDate" + " FROM PrerequisitesMaster prerequisitesMaster"
				+ " WHERE prerequisitesMaster.orgId=:orgId " + " AND lower(prerequisitesMaster.code) = lower(:code)"),
				
				
				@NamedQuery(name = "UPDATE_GET_PREREQUISITES_BY_CODE", query = "SELECT prerequisitesMaster.prerequisitesId as id,"
						+ " prerequisitesMaster.code as code," + " prerequisitesMaster.desc as desc,"
						+ " prerequisitesMaster.status as status," + " prerequisitesMaster.createdBy as createdBy,"
						+ " prerequisitesMaster.createdDate as createdDate," + " prerequisitesMaster.updatedBy as updatedBy,"
						+ " prerequisitesMaster.updatedDate as updatedDate" + " FROM PrerequisitesMaster prerequisitesMaster"
						+ " WHERE prerequisitesMaster.orgId=:orgId " + " AND lower(prerequisitesMaster.code) = lower(:code)"
						+ " AND prerequisitesMaster.prerequisitesId <> :prerequisitesId"),


		@NamedQuery(name = "GET_PAGINATED_PREREQUISITES_MASTER_LIST", query = "SELECT prerequisitesMaster.prerequisitesId as id,"
				+ " prerequisitesMaster.code as code," + " prerequisitesMaster.desc as desc,"
				+ " prerequisitesMaster.status as status," + " prerequisitesMaster.createdBy as createdBy,"
				+ " prerequisitesMaster.createdDate as createdDate," + " prerequisitesMaster.updatedBy as updatedBy,"
				+ " prerequisitesMaster.updatedDate as updatedDate" + " FROM PrerequisitesMaster prerequisitesMaster"
				+ " WHERE prerequisitesMaster.orgId = :orgId" + " ORDER BY id DESC")

})

@NamedNativeQueries(
{ @NamedNativeQuery(name = "GET_TOTAL_PREREQUISITES_RECORD", query = "select count(*) from lab.m_prerequisites_master where "
		+ "org_id = :orgId ") })
@Entity
@Table(name = "m_prerequisites_master", schema = "lab")
@SequenceGenerator(name = "m_seq_prerequisites_master", sequenceName = "lab.m_seq_prerequisites_master", allocationSize = 1)
public class PrerequisitesMaster
{
	@Id
	@Column(name = "prerequisites_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "m_seq_prerequisites_master")
	private int prerequisitesId;

	@Column(name = "prerequisites_code")
	private String code;
	
	@Column(name = "prerequisites_name")
	private String desc;
	
	@Column(name = "prerequisites_status")
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
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "prerequisitesMaster")
	private List<TestMaster> listTestMaster;

	/**
	 * @return the prerequisitesId
	 */
	public int getPrerequisitesId()
	{
		return prerequisitesId;
	}

	/**
	 * @param prerequisitesId
	 *            the prerequisitesId to set
	 */
	public void setPrerequisitesId(int prerequisitesId)
	{
		this.prerequisitesId = prerequisitesId;
	}

	/**
	 * @return the code
	 */
	public String getCode()
	{
		return code;
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public void setCode(String code)
	{
		this.code = code;
	}

	/**
	 * @return the desc
	 */
	public String getDesc()
	{
		return desc;
	}

	/**
	 * @param desc
	 *            the desc to set
	 */
	public void setDesc(String desc)
	{
		this.desc = desc;
	}

	/**
	 * @return the status
	 */
	public Character getStatus()
	{
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(Character status)
	{
		this.status = status;
	}

	/**
	 * @return the createdBy
	 */
	public Integer getCreatedBy()
	{
		return createdBy;
	}

	/**
	 * @param createdBy
	 *            the createdBy to set
	 */
	public void setCreatedBy(Integer createdBy)
	{
		this.createdBy = createdBy;
	}

	/**
	 * @return the createdDate
	 */
	public Long getCreatedDate()
	{
		return createdDate;
	}

	/**
	 * @param createdDate
	 *            the createdDate to set
	 */
	public void setCreatedDate(Long createdDate)
	{
		this.createdDate = createdDate;
	}

	/**
	 * @return the updatedBy
	 */
	public Integer getUpdatedBy()
	{
		return updatedBy;
	}

	/**
	 * @param updatedBy
	 *            the updatedBy to set
	 */
	public void setUpdatedBy(Integer updatedBy)
	{
		this.updatedBy = updatedBy;
	}

	/**
	 * @return the updatedDate
	 */
	public Long getUpdatedDate()
	{
		return updatedDate;
	}

	/**
	 * @param updatedDate
	 *            the updatedDate to set
	 */
	public void setUpdatedDate(Long updatedDate)
	{
		this.updatedDate = updatedDate;
	}

	/**
	 * @return the orgId
	 */
	public Integer getOrgId()
	{
		return orgId;
	}

	/**
	 * @param orgId
	 *            the orgId to set
	 */
	public void setOrgId(Integer orgId)
	{
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
