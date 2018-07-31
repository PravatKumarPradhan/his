package com.param.lis.unit.dto;

public class HelpValueMasterDto
{
	private Integer helpValueId;

	private Integer parameterId;

	private String helpValue;

	private Character status;

	private Integer createdBy;

	private Long createdDate;

	private Integer updatedBy;

	private Long updatedDate;
	
	private Integer orgId;

	private Integer orgUnitId;
	
	private Character isDeleted;

	public Integer getHelpValueId()
	{
		return helpValueId;
	}

	public void setHelpValueId(Integer helpValueId)
	{
		this.helpValueId = helpValueId;
	}

	public Integer getParameterId()
	{
		return parameterId;
	}

	public void setParameterId(Integer parameterId)
	{
		this.parameterId = parameterId;
	}

	public String getHelpValue()
	{
		return helpValue;
	}

	public void setHelpValue(String helpValue)
	{
		this.helpValue = helpValue;
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

	public Integer getOrgUnitId()
	{
		return orgUnitId;
	}

	public void setOrgUnitId(Integer orgUnitId)
	{
		this.orgUnitId = orgUnitId;
	}

	public Character getIsDeleted()
	{
		return isDeleted;
	}

	public void setIsDeleted(Character isDeleted)
	{
		this.isDeleted = isDeleted;
	}

}
