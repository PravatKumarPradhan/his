package com.param.lis.transaction.dto;

import java.util.Date;

public class CollectedSampleDto {
  private Integer labSampleDtlsId;

  private String uhid;

  private String patientDetails;

  private String priorityName;

  private String colorCode;

  private String deptName;

  private String containerName;

  private Integer sampleStatusId;

  private String visitType;

  private String panelCode;

  private Date orderDateTime;

  private Date sampleCollectionDatetime;
  
  private String testCode;

  private String testDesc;

  private String labSampleNo;
  
  private String sampleType;


  public Integer getLabSampleDtlsId() {
    return labSampleDtlsId;
  }

  public void setLabSampleDtlsId(Integer labSampleDtlsId) {
    this.labSampleDtlsId = labSampleDtlsId;
  }

  public String getUhid() {
    return uhid;
  }

  public void setUhid(String uhid) {
    this.uhid = uhid;
  }

  public String getPatientDetails() {
    return patientDetails;
  }

  public void setPatientDetails(String patientDetails) {
    this.patientDetails = patientDetails;
  }

  public String getPriorityName() {
    return priorityName;
  }

  public void setPriorityName(String priorityName) {
    this.priorityName = priorityName;
  }

  public String getColorCode() {
    return colorCode;
  }

  public void setColorCode(String colorCode) {
    this.colorCode = colorCode;
  }

  public String getDeptName() {
    return deptName;
  }

  public void setDeptName(String deptName) {
    this.deptName = deptName;
  }

  public Integer getSampleStatusId() {
    return sampleStatusId;
  }

  public void setSampleStatusId(Integer sampleStatusId) {
    this.sampleStatusId = sampleStatusId;
  }

  public String getContainerName() {
    return containerName;
  }

  public void setContainerName(String containerName) {
    this.containerName = containerName;
  }

  public String getVisitType() {
    return visitType;
  }

  public void setVisitType(String visitType) {
    this.visitType = visitType;
  }

  public String getPanelCode() {
    return panelCode;
  }

  public void setPanelCode(String panelCode) {
    this.panelCode = panelCode;
  }

  public Date getOrderDateTime() {
    return orderDateTime;
  }

  public void setOrderDateTime(Date orderDateTime) {
    this.orderDateTime = orderDateTime;
  }

  public Date getSampleCollectionDatetime() {
    return sampleCollectionDatetime;
  }

  public void setSampleCollectionDatetime(Date sampleCollectionDatetime) {
    this.sampleCollectionDatetime = sampleCollectionDatetime;
  }

public String getTestCode() {
	return testCode;
}

public void setTestCode(String testCode) {
	this.testCode = testCode;
}

public String getTestDesc() {
	return testDesc;
}

public void setTestDesc(String testDesc) {
	this.testDesc = testDesc;
}

public String getLabSampleNo() {
	return labSampleNo;
}

public void setLabSampleNo(String labSampleNo) {
	this.labSampleNo = labSampleNo;
}

public String getSampleType() {
	return sampleType;
}

public void setSampleType(String sampleType) {
	this.sampleType = sampleType;
}
  
  

}
