package com.param.lis.global.common;

import java.util.List;




public class SearchDto {

	private Integer orgId;
	private Integer orgUnitId;
	private String fromDate;
	private String toDate;
	private Integer patientId;
	private Character pending;
	private Character outSource;
	private Character completed;
	private Integer sampleStatusId;
	private Integer labSampleDtlsId;
	private List<obj> visitTypes;
	private List<obj> specimanTypes;
	private List<obj> testTypes;
	private List<obj> subDepts;
	private Integer deptId;
	private Integer subDeptId;
	private Integer visitTypeId;
	
  public String getFromDate() {
    return fromDate;
  }

  public void setFromDate(String fromDate) {
    this.fromDate = fromDate;
  }

  public String getToDate() {
    return toDate;
  }

  public void setToDate(String toDate) {
    this.toDate = toDate;
  }

  public Integer getPatientId() {
    return patientId;
  }

  public void setPatientId(Integer patientId) {
    this.patientId = patientId;
  }

  public List<obj> getVisitTypes() {
    return visitTypes;
  }

  public void setVisitTypes(List<obj> visitTypes) {
    this.visitTypes = visitTypes;
  }

  public List<obj> getSpecimanTypes() {
    return specimanTypes;
  }

  public void setSpecimanTypes(List<obj> specimanTypes) {
    this.specimanTypes = specimanTypes;
  }

  public List<obj> getTestTypes() {
    return testTypes;
  }

  public void setTestTypes(List<obj> testTypes) {
    this.testTypes = testTypes;
  }

  public Integer getOrgId() {
    return orgId;
  }

  public void setOrgId(Integer orgId) {
    this.orgId = orgId;
  }

  public Integer getOrgUnitId() {
    return orgUnitId;
  }

  public void setOrgUnitId(Integer orgUnitId) {
    this.orgUnitId = orgUnitId;
  }

  public List<obj> getSubDepts() {
    return subDepts;
  }

  public void setSubDepts(List<obj> subDepts) {
    this.subDepts = subDepts;
  }

  public Character getPending() {
    return pending;
  }

  public void setPending(Character pending) {
    this.pending = pending;
  }

  public Character getOutSource() {
    return outSource;
  }

  public void setOutSource(Character outSource) {
    this.outSource = outSource;
  }

  public Character getCompleted() {
    return completed;
  }

  public void setCompleted(Character completed) {
    this.completed = completed;
  }

  public Integer getSampleStatusId() {
    return sampleStatusId;
  }

  public void setSampleStatusId(Integer sampleStatusId) {
    this.sampleStatusId = sampleStatusId;
  }

  public Integer getLabSampleDtlsId() {
    return labSampleDtlsId;
  }

  public void setLabSampleDtlsId(Integer labSampleDtlsId) {
    this.labSampleDtlsId = labSampleDtlsId;
  }

public Integer getDeptId() {
	return deptId;
}

public void setDeptId(Integer deptId) {
	this.deptId = deptId;
}

public Integer getVisitTypeId() {
	return visitTypeId;
}

public void setVisitTypeId(Integer visitTypeId) {
	this.visitTypeId = visitTypeId;
}

public Integer getSubDeptId() {
	return subDeptId;
}

public void setSubDeptId(Integer subDeptId) {
	this.subDeptId = subDeptId;
}
  
  
  
}
