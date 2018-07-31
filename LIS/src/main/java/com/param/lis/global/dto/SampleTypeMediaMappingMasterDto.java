package com.param.lis.global.dto;

import java.util.List;


public class SampleTypeMediaMappingMasterDto {

	private Integer sampleMediaMpprId;
	private Integer sampleId;
	private Integer mediaId;
	private Character sampleMediaStatus;
	private Integer createdBy;
	private Long createdDate;
	private Integer updatedBy;
	private Long updatedDate;
	private Integer orgId;
	private String sampleName;
	private String mediaName;
	private Character IsDeleted;
	private String mediaDescription;
	private String sampleDesc;
	private String sampleCode;
	
	private List<Integer> selectediaampleMediaMpprList;
	
	/*private BigInteger count;
	
	
	*//**
	 * @return the count
	 *//*
	public BigInteger getCount() {
		return count;
	}
	*//**
	 * @param count the count to set
	 *//*
	public void setCount(BigInteger count) {
		this.count = count;
	}*/
	/**
	 * @return the sampleMediaMpprId
	 */
	public Integer getSampleMediaMpprId() {
		return sampleMediaMpprId;
	}
	/**
	 * @param sampleMediaMpprId the sampleMediaMpprId to set
	 */
	public void setSampleMediaMpprId(Integer sampleMediaMpprId) {
		this.sampleMediaMpprId = sampleMediaMpprId;
	}
	/**
	 * @return the sampleId
	 */
	public Integer getSampleId() {
		return sampleId;
	}
	/**
	 * @param sampleId the sampleId to set
	 */
	public void setSampleId(Integer sampleId) {
		this.sampleId = sampleId;
	}
	/**
	 * @return the mediaId
	 */
	public Integer getMediaId() {
		return mediaId;
	}
	/**
	 * @param mediaId the mediaId to set
	 */
	public void setMediaId(Integer mediaId) {
		this.mediaId = mediaId;
	}
	/**
	 * @return the sampleMediaStatus
	 */
	public Character getSampleMediaStatus() {
		return sampleMediaStatus;
	}
	/**
	 * @param sampleMediaStatus the sampleMediaStatus to set
	 */
	public void setSampleMediaStatus(Character sampleMediaStatus) {
		this.sampleMediaStatus = sampleMediaStatus;
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
	public String getSampleName() {
		return sampleName;
	}
	public void setSampleName(String sampleName) {
		this.sampleName = sampleName;
	}
	public String getMediaName() {
		return mediaName;
	}
	public void setMediaName(String mediaName) {
		this.mediaName = mediaName;
	}
	public List<Integer> getSelectediaampleMediaMpprList() {
		return selectediaampleMediaMpprList;
	}
	public void setSelectediaampleMediaMpprList(
			List<Integer> selectediaampleMediaMpprList) {
		this.selectediaampleMediaMpprList = selectediaampleMediaMpprList;
	}
	public Character getIsDeleted() {
		return IsDeleted;
	}
	public void setIsDeleted(Character IsDeleted) {
		this.IsDeleted = IsDeleted;
	}
	public String getMediaDescription() {
		return mediaDescription;
	}
	public void setMediaDescription(String mediaDescription) {
		this.mediaDescription = mediaDescription;
	}
	public String getSampleDesc() {
		return sampleDesc;
	}
	public void setSampleDesc(String sampleDesc) {
		this.sampleDesc = sampleDesc;
	}
	public String getSampleCode() {
		return sampleCode;
	}
	public void setSampleCode(String sampleCode) {
		this.sampleCode = sampleCode;
	}
	
	
	
	
}
