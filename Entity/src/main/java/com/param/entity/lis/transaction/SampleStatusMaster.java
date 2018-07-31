/**@author Ganesh Chaudhari */
package com.param.entity.lis.transaction;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.param.entity.lis.common.LocalTimeConverter;

@Entity
@Table(name = "m_test_status_master", schema = "lab")
public class SampleStatusMaster
{ 
	@Id
	@Column(name = "test_status_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int testStatusId;
	
	@Column(name = "status_name")
	private String statusName;

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
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "sampleStatusMaster")
	private List<LabSampleDetailsMaster> listLabSampleDetailsMaster;

	public int getTestStatusId()
	{
		return testStatusId;
	}

	public void setTestStatusId(int testStatusId)
	{
		this.testStatusId = testStatusId;
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

	public List<LabSampleDetailsMaster> getListLabSampleDetailsMaster()
	{
		return listLabSampleDetailsMaster;
	}

	public void setListLabSampleDetailsMaster(List<LabSampleDetailsMaster> listLabSampleDetailsMaster)
	{
		this.listLabSampleDetailsMaster = listLabSampleDetailsMaster;
	}

  public String getStatusName() {
    return statusName;
  }

  public void setStatusName(String statusName) {
    this.statusName = statusName;
  }

	

}



