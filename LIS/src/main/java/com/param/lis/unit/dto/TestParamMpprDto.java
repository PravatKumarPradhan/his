package com.param.lis.unit.dto;

public class TestParamMpprDto
{

	private Integer testPerMpprId;
	private Integer testId;
	private Integer parameterId;
	private String parameterDesc;
	private Integer paraSequence;
	private Integer headerId;
	private String headerDesc;
	private String headerCode;
	private Character testParaStatus;
	private Integer createdBy;
	private Long createdDate;
	private Integer updatedBy;
	private Long updatedDate;
	private Integer orgId;
	private Integer orgUnitId;
	private Character isDeleted;

	/**
	 * @return the testPerMpprId
	 */
	public Integer getTestPerMpprId()
	{
		return testPerMpprId;
	}

	/**
	 * @param testPerMpprId
	 *            the testPerMpprId to set
	 */
	public void setTestPerMpprId(Integer testPerMpprId)
	{
		this.testPerMpprId = testPerMpprId;
	}

	/**
	 * @return the testId
	 */
	public Integer getTestId()
	{
		return testId;
	}

	/**
	 * @param testId
	 *            the testId to set
	 */
	public void setTestId(Integer testId)
	{
		this.testId = testId;
	}

	/**
	 * @return the parameterId
	 */
	public Integer getParameterId()
	{
		return parameterId;
	}

	/**
	 * @param parameterId
	 *            the parameterId to set
	 */
	public void setParameterId(Integer parameterId)
	{
		this.parameterId = parameterId;
	}

	/**
	 * @return the paraSequence
	 */
	public Integer getParaSequence()
	{
		return paraSequence;
	}

	/**
	 * @param paraSequence
	 *            the paraSequence to set
	 */
	public void setParaSequence(Integer paraSequence)
	{
		this.paraSequence = paraSequence;
	}

	/**
	 * @return the headerId
	 */
	public Integer getHeaderId()
	{
		return headerId;
	}

	/**
	 * @param headerId
	 *            the headerId to set
	 */
	public void setHeaderId(Integer headerId)
	{
		this.headerId = headerId;
	}

	/**
	 * @return the testParaStatus
	 */
	public Character getTestParaStatus()
	{
		return testParaStatus;
	}

	/**
	 * @param testParaStatus
	 *            the testParaStatus to set
	 */
	public void setTestParaStatus(Character testParaStatus)
	{
		this.testParaStatus = testParaStatus;
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

	/**
	 * @return the orgUnitId
	 */
	public Integer getOrgUnitId()
	{
		return orgUnitId;
	}

	/**
	 * @param orgUnitId
	 *            the orgUnitId to set
	 */
	public void setOrgUnitId(Integer orgUnitId)
	{
		this.orgUnitId = orgUnitId;
	}
	
	public void setIsDeleted(Character isDeleted)
	{
		this.isDeleted = isDeleted;
	}
	
	public Character getIsDeleted()
	{
		return isDeleted;
	}


	public String getParameterDesc()
	{
		return parameterDesc;
	}

	public void setParameterDesc(String parameterDesc)
	{
		this.parameterDesc = parameterDesc;
	}

	public String getHeaderDesc()
	{
		return headerDesc;
	}

	public void setHeaderDesc(String headerDesc)
	{
		this.headerDesc = headerDesc;
	}

	public String getHeaderCode() {
		return headerCode;
	}

	public void setHeaderCode(String headerCode) {
		this.headerCode = headerCode;
	}
	
	

}
