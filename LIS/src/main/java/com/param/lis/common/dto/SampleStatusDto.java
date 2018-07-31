package com.param.lis.common.dto;

public class SampleStatusDto
{

	private Integer sampleStatusId;
	private String statusName;
	private Character status;

	public Integer getSampleStatusId()
	{
		return sampleStatusId;
	}

	public void setSampleStatusId(Integer sampleStatusId)
	{
		this.sampleStatusId = sampleStatusId;
	}

	public String getStatusName()
	{
		return statusName;
	}

	public void setStatusName(String statusName)
	{
		this.statusName = statusName;
	}

	public Character getStatus()
	{
		return status;
	}

	public void setStatus(Character status)
	{
		this.status = status;
	}
}
