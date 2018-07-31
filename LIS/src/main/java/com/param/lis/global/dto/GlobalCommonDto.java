package com.param.lis.global.dto;

public class GlobalCommonDto
{


	private Integer id;
	private String code;
	private String desc;
	private Character status;
	private Integer createdBy;
	private Long createdDate;
	private Integer updatedBy;
	private Long updatedDate;
	private Integer orgId;
	private Integer unitId;
	private Character isBlockFrozensec;
	
	public Integer getId()
	{
		return id;
	}
	
	public void setId(Integer id) 
	{
		this.id = id;
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
	

	public Integer getUnitId() {
		return unitId;
	}

	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}

	/**
	 * @return the isBlockFrozensec
	 */
	public Character getIsBlockFrozensec() {
		return isBlockFrozensec;
	}

	/**
	 * @param isBlockFrozensec the isBlockFrozensec to set
	 */
	public void setIsBlockFrozensec(Character isBlockFrozensec) {
		this.isBlockFrozensec = isBlockFrozensec;
	}
	
	
	
}
