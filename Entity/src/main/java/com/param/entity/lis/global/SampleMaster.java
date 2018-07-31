
/**@author Ganesh Chaudhari */

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

		@NamedQuery(name = "GET_SAMPLE_BY_SAMPLE_ID", query = "SELECT sampleMaster.sampleId AS id,"
				+ " sampleMaster.code as code,"
				+ " sampleMaster.desc as desc,"
				+ " sampleMaster.status as status,"
				+ " sampleMaster.createdBy as createdBy,"
				+ " sampleMaster.createdDate as createdDate,"
				+ " sampleMaster.updatedBy as updatedBy,"
				+ " sampleMaster.updatedDate as updatedDate,"
				+ " sampleMaster.orgId as orgId"
				+ " FROM SampleMaster sampleMaster "
				+ " WHERE sampleMaster.sampleId = :sampleId "
				+ " AND sampleMaster.orgId= :orgId"),
		

		@NamedQuery(name = "GET_SAMPLE_BY_CODE", query = "SELECT sampleMaster.sampleId as id,"
				+ " sampleMaster.code as code,"
				+ " sampleMaster.desc as desc,"
				+ " sampleMaster.status as status,"
				+ " sampleMaster.createdBy as createdBy,"
				+ " sampleMaster.createdDate as createdDate,"
				+ " sampleMaster.updatedBy as updatedBy,"
				+ " sampleMaster.updatedDate as updatedDate"
				+ " FROM SampleMaster sampleMaster"
				+ " WHERE sampleMaster.orgId=:orgId "
				+ " AND lower(sampleMaster.code) = lower(:code)"),
				
				@NamedQuery(name = "UPDATE_GET_SAMPLE_BY_CODE", query = "SELECT sampleMaster.sampleId as id,"
						+ " sampleMaster.code as code,"
						+ " sampleMaster.desc as desc,"
						+ " sampleMaster.status as status,"
						+ " sampleMaster.createdBy as createdBy,"
						+ " sampleMaster.createdDate as createdDate,"
						+ " sampleMaster.updatedBy as updatedBy,"
						+ " sampleMaster.updatedDate as updatedDate"
						+ " FROM SampleMaster sampleMaster"
						+ " WHERE sampleMaster.orgId=:orgId "
						+ " AND lower(sampleMaster.code) = lower(:code)"
						+ " AND sampleMaster.sampleId <> :sampleId"),

		@NamedQuery(name = "GET_PAGINATED_SAMPLE_MASTER_LIST", query = "SELECT sampleMaster.sampleId as id,"
				+ " sampleMaster.code as code,"
				+ " sampleMaster.desc as desc,"
				+ " sampleMaster.status as status,"
				+ " sampleMaster.createdBy as createdBy,"
				+ " sampleMaster.createdDate as createdDate,"
				+ " sampleMaster.updatedBy as updatedBy,"
				+ " sampleMaster.updatedDate as updatedDate"
				+ " FROM SampleMaster sampleMaster"
				+ " WHERE sampleMaster.orgId = :orgId"
				+ " ORDER BY id DESC")

})
	
@NamedNativeQueries(
{ @NamedNativeQuery(name = "GET_TOTAL_SAMPLE_RECORD", query = "select count(*) from lab.m_sample_master where "
		+ "org_id = :orgId"),

@NamedNativeQuery(name = "GET_SAMPLE_STATUS_LIST", query = "SELECT "
		+"	samplestausmast.sample_status_id AS \"sampleStatusId\","
		+"	samplestausmast.status_name AS \"statusName\" "
		+"FROM "
		+"	lab.m_sample_status_master samplestausmast "
		+"WHERE "
		+"	samplestausmast.org_id = :orgId "
		+"	AND samplestausmast.status = 'A' ")
})

@Entity
@Table(name = "m_sample_master", schema = "lab")
@SequenceGenerator(name = "m_seq_sample_master", sequenceName = "lab.m_seq_sample_master", allocationSize = 1)
public class SampleMaster
{
	@Id
	@Column(name = "sample_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "m_seq_sample_master")
	private int sampleId;
	
	@Column(name = "sample_code")
	private String code;

	@Column(name = "sample_desc")
	private String desc;

	@Column(name = "sample_status")
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

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "sampleMaster")
	private List<TestMaster> listTestMaster;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "sampleMaster")
	private List<SampleTypeMediaMappingMaster> listSampleTypeMediaMappingMaster;
	
	public int getSampleId()
	{
		return sampleId;
	}

	public void setSampleId(int sampleId)
	{
		this.sampleId = sampleId;
	}

	public String getCode()
	{
		return code;
	}

	public void setCode(String code)
	{
		this.code = code;
	}

	public String getDesc()
	{
		return desc;
	}

	public void setDesc(String desc)
	{
		this.desc = desc;
	}

	public Character getStatus()
	{
		return status;
	}

	public void setStatus(Character status)
	{
		this.status = status;
	}

	public Integer getCreatedBy()
	{
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy)
	{
		this.createdBy = createdBy;
	}

	public Long getCreatedDate()
	{
		return createdDate;
	}

	public void setCreatedDate(Long createdDate)
	{
		this.createdDate = createdDate;
	}

	public Integer getUpdatedBy()
	{
		return updatedBy;
	}

	public void setUpdatedBy(Integer updatedBy)
	{
		this.updatedBy = updatedBy;
	}

	public Long getUpdatedDate()
	{
		return updatedDate;
	}

	public void setUpdatedDate(Long updatedDate)
	{
		this.updatedDate = updatedDate;
	}

	public Integer getOrgId()
	{
		return orgId;
	}

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

	
	/**
	 * @return the listSampleTypeMediaMappingMaster
	 */
	public List<SampleTypeMediaMappingMaster> getListSampleTypeMediaMappingMaster() {
		return listSampleTypeMediaMappingMaster;
	}

	/**
	 * @param listSampleTypeMediaMappingMaster the listSampleTypeMediaMappingMaster to set
	 */
	public void setListSampleTypeMediaMappingMaster(
			List<SampleTypeMediaMappingMaster> listSampleTypeMediaMappingMaster) {
		this.listSampleTypeMediaMappingMaster = listSampleTypeMediaMappingMaster;
	}

}
