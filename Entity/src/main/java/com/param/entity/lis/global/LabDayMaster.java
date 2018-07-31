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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.param.entity.lis.unit.ParameterMaster;
import com.param.entity.lis.common.LocalTimeConverter;

@Entity
@Table(name = "m_day_master", schema = "lab")
public class LabDayMaster
{
	@Id
	@Column(name = "day_master_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer day_master_id;
	
	@Column(name = "day")
	private String day;
	
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
	
	@Column(name="status")
	private Integer status;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "labDayMaster")
	private List<ParameterMaster> listParameterMaster;

	public Integer getDay_master_id()
	{
		return day_master_id;
	}

	public void setDay_master_id(Integer day_master_id)
	{
		this.day_master_id = day_master_id;
	}

	public String getDay()
	{
		return day;
	}

	public void setDay(String day)
	{
		this.day = day;
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

	public Integer getStatus()
	{
		return status;
	}

	public void setStatus(Integer status)
	{
		this.status = status;
	}

	public List<ParameterMaster> getListParameterMaster()
	{
		return listParameterMaster;
	}

	public void setListParameterMaster(List<ParameterMaster> listParameterMaster)
	{
		this.listParameterMaster = listParameterMaster;
	}
	
	
	

}
