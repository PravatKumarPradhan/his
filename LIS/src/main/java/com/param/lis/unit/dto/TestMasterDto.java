package com.param.lis.unit.dto;

import java.math.BigInteger;
import java.util.List;

public class TestMasterDto
{
	private Integer testId;
	private String testCode;
	private String testDesc;
	private String testAlies;
	private Integer deptId;
	private String deptName;
	private Integer sampleId;
	private String sampleName;
	private Integer noOfSampleReq;
	private Double sampleVolume;
	private String sampleUnit;
	private Integer containerId;
	private String containerName;
	private Integer reportTypeId;
	private String reportTypeName;
	private Integer techniqueId;
	private String techniqueName;
	private String identificationNo;
	private Integer prerequisitsId;
	private String prerequisitName;
	private Character proRelease;
	private Character testStatus;
	private Character isOutsourced;
	private String footNote;
	private Integer createdBy;
	private Long createdDate;
	private Integer updatedBy;
	private Long updatedDate;
	private Integer orgId;
	private Integer orgUnitId;
	private Integer normlTatTime;
	private String normlTime;
	private Integer normlTatId;
	private Integer urgentTatTime;
	private Integer urgentTatId;
	private String urgentTime;
	private Integer serviceId;
	private Integer testType;
	private Integer unitId;
	private BigInteger count;
	private Character isCentrifugationRequried;
	private Character isHistoCyto;
	private Integer specimanId;
	private String specimanName;
	
	private List<ParameterMasterDto> listParameterMasterDto;
	private List<TestParamMpprDto> listTestParamMppr;
	
	public Integer getUnitId()
	{
		return unitId;
	}

	public void setUnitId(Integer unitId)
	{
		this.unitId = unitId;
	}

	public BigInteger getCount() {
		return count;
	}

	public void setCount(BigInteger count) {
		this.count = count;
	}

	public Integer getTestId()
	{
		return testId;
	}

	public void setTestId(Integer testId)
	{
		this.testId = testId;
	}

	public String getTestCode()
	{
		return testCode;
	}

	public void setTestCode(String testCode)
	{
		this.testCode = testCode;
	}

	public String getTestDesc()
	{
		return testDesc;
	}

	public void setTestDesc(String testDesc)
	{
		this.testDesc = testDesc;
	}

	public String getTestAlies()
	{
		return testAlies;
	}

	public void setTestAlies(String testAlies)
	{
		this.testAlies = testAlies;
	}

	public Integer getDeptId()
	{
		return deptId;
	}

	public void setDeptId(Integer deptId)
	{
		this.deptId = deptId;
	}

	public Integer getSampleId()
	{
		return sampleId;
	}

	public void setSampleId(Integer sampleId)
	{
		this.sampleId = sampleId;
	}

	public Integer getNoOfSampleReq()
	{
		return noOfSampleReq;
	}

	public void setNoOfSampleReq(Integer noOfSampleReq)
	{
		this.noOfSampleReq = noOfSampleReq;
	}

	public Double getSampleVolume()
	{
		return sampleVolume;
	}

	public void setSampleVolume(Double sampleVolume)
	{
		this.sampleVolume = sampleVolume;
	}

	public Integer getContainerId()
	{
		return containerId;
	}

	public void setContainerId(Integer containerId)
	{
		this.containerId = containerId;
	}

	public Integer getReportTypeId()
	{
		return reportTypeId;
	}

	public void setReportTypeId(Integer reportTypeId)
	{
		this.reportTypeId = reportTypeId;
	}

	public Integer getTechniqueId()
	{
		return techniqueId;
	}

	public void setTechniqueId(Integer techniqueId)
	{
		this.techniqueId = techniqueId;
	}

	public String getIdentificationNo()
	{
		return identificationNo;
	}

	public void setIdentificationNo(String identificationNo)
	{
		this.identificationNo = identificationNo;
	}

	public Integer getPrerequisitsId()
	{
		return prerequisitsId;
	}

	public void setPrerequisitsId(Integer prerequisitsId)
	{
		this.prerequisitsId = prerequisitsId;
	}

	public Character getProRelease()
	{
		return proRelease;
	}

	public void setProRelease(Character proRelease)
	{
		this.proRelease = proRelease;
	}

	public Character getTestStatus()
	{
		return testStatus;
	}

	public void setTestStatus(Character testStatus)
	{
		this.testStatus = testStatus;
	}

	public Character getIsOutsourced()
	{
		return isOutsourced;
	}

	public void setIsOutsourced(Character isOutsourced)
	{
		this.isOutsourced = isOutsourced;
	}

	public String getFootNote()
	{
		return footNote;
	}

	public void setFootNote(String footNote)
	{
		this.footNote = footNote;
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

	public List<TestParamMpprDto> getListTestParamMppr()
	{
		return listTestParamMppr;
	}

	public void setListTestParamMppr(List<TestParamMpprDto> listTestParamMppr)
	{
		this.listTestParamMppr = listTestParamMppr;
	}

	public Integer getNormlTatTime()
	{
		return normlTatTime;
	}

	public void setNormlTatTime(Integer normlTatTime)
	{
		this.normlTatTime = normlTatTime;
	}

	public Integer getNormlTatId()
	{
		return normlTatId;
	}

	public void setNormlTatId(Integer normlTatId)
	{
		this.normlTatId = normlTatId;
	}

	public Integer getUrgentTatTime()
	{
		return urgentTatTime;
	}

	public void setUrgentTatTime(Integer urgentTatTime)
	{
		this.urgentTatTime = urgentTatTime;
	}

	public Integer getUrgentTatId()
	{
		return urgentTatId;
	}

	public void setUrgentTatId(Integer urgentTatId)
	{
		this.urgentTatId = urgentTatId;
	}

	public Integer getServiceId()
	{
		return serviceId;
	}

	public void setServiceId(Integer serviceId)
	{
		this.serviceId = serviceId;
	}

	public List<ParameterMasterDto> getListParameterMasterDto()
	{
		return listParameterMasterDto;
	}

	public void setListParameterMasterDto(List<ParameterMasterDto> listParameterMasterDto)
	{
		this.listParameterMasterDto = listParameterMasterDto;
	}

	public Integer getTestType()
	{
		return testType;
	}

	public void setTestType(Integer testType)
	{
		this.testType = testType;
	}

	public String getSampleName()
	{
		return sampleName;
	}

	public void setSampleName(String sampleName)
	{
		this.sampleName = sampleName;
	}

	public String getSampleUnit()
	{
		return sampleUnit;
	}

	public void setSampleUnit(String sampleUnit)
	{
		this.sampleUnit = sampleUnit;
	}

	public String getTechniqueName()
	{
		return techniqueName;
	}

	public void setTechniqueName(String techniqueName)
	{
		this.techniqueName = techniqueName;
	}

	public String getPrerequisitName()
	{
		return prerequisitName;
	}

	public void setPrerequisitName(String prerequisitName)
	{
		this.prerequisitName = prerequisitName;
	}

	public String getNormlTime()
	{
		return normlTime;
	}

	public void setNormlTime(String normlTime)
	{
		this.normlTime = normlTime;
	}

	public String getUrgentTime()
	{
		return urgentTime;
	}

	public void setUrgentTime(String urgentTime)
	{
		this.urgentTime = urgentTime;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public Character getIsCentrifugationRequried() {
		return isCentrifugationRequried;
	}

	public void setIsCentrifugationRequried(Character isCentrifugationRequried) {
		this.isCentrifugationRequried = isCentrifugationRequried;
	}

	public Character getIsHistoCyto() {
		return isHistoCyto;
	}

	public void setIsHistoCyto(Character isHistoCyto) {
		this.isHistoCyto = isHistoCyto;
	}

	public Integer getSpecimanId() {
		return specimanId;
	}

	public void setSpecimanId(Integer specimanId) {
		this.specimanId = specimanId;
	}

	public String getContainerName() {
		return containerName;
	}

	public void setContainerName(String containerName) {
		this.containerName = containerName;
	}

	public String getReportTypeName() {
		return reportTypeName;
	}

	public void setReportTypeName(String reportTypeName) {
		this.reportTypeName = reportTypeName;
	}

	public String getSpecimanName() {
		return specimanName;
	}

	public void setSpecimanName(String specimanName) {
		this.specimanName = specimanName;
	}
}
