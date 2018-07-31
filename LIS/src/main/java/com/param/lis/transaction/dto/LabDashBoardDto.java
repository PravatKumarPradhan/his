package com.param.lis.transaction.dto;

import java.math.BigInteger;
import java.util.Date;
import com.param.lis.global.common.StringCommonMethods;

public class LabDashBoardDto {

  private BigInteger sampleNo;
  private String uhid;
  private String visitType;
  private String genderName;
  private Date dob;
  private String doctorDetails;
  private String patientDetails;
  private String deptName;
  private String testDesc;
  private String specimenType;
  private String outSourceLab;
  private String labSampleStatus;
  private Integer labSampleDtlsId;
  
	private String sampleType;
	private String testCode;
	private String panelCode;
	private String priority;

	private String subSpecialityName;
	private String specimanName;
	private String status;
	private Date orderDateTime;
	private Date sampleCollectionDatetime;

  
  
  public String getSampleNo() 
  {
      return StringCommonMethods.generateSampleNumber(sampleNo, "SM-", 7);
  }
  public void setSampleNo(BigInteger sampleNo) {
    this.sampleNo = sampleNo;
  }
  public String getUhid() {
    return uhid;
  }
  public void setUhid(String uhid) {
    this.uhid = uhid;
  }
  public String getGenderName() {
    return genderName;
  }
  public void setGenderName(String genderName) {
    this.genderName = genderName;
  }
  public Date getDob() {
    return dob;
  }
  public void setDob(Date dob) {
    this.dob = dob;
  }
  public String getDoctorDetails() {
    return doctorDetails;
  }
  public void setDoctorDetails(String doctorDetails) {
    this.doctorDetails = doctorDetails;
  }
  public String getDeptName() {
    return deptName;
  }
  public void setDeptName(String deptName) {
    this.deptName = deptName;
  }
  public String getTestDesc() {
    return testDesc;
  }
  public void setTestDesc(String testDesc) {
    this.testDesc = testDesc;
  }
  public String getSpecimenType() {
    return specimenType;
  }
  public void setSpecimenType(String specimenType) {
    this.specimenType = specimenType;
  }
  public String getOutSourceLab() {
    return outSourceLab;
  }
  public void setOutSourceLab(String outSourceLab) {
    this.outSourceLab = outSourceLab;
  }
  public String getLabSampleStatus() {
    return labSampleStatus;
  }
  public void setLabSampleStatus(String labSampleStatus) {
    this.labSampleStatus = labSampleStatus;
  }
  public String getPatientDetails() {
    return patientDetails;
  }
  public void setPatientDetails(String patientDetails) {
    this.patientDetails = patientDetails;
  }
public Integer getLabSampleDtlsId() {
	return labSampleDtlsId;
}
public void setLabSampleDtlsId(Integer labSampleDtlsId) {
	this.labSampleDtlsId = labSampleDtlsId;
}
public String getVisitType() {
	return visitType;
}
public void setVisitType(String visitType) {
	this.visitType = visitType;
}
public String getSampleType() {
	return sampleType;
}
public void setSampleType(String sampleType) {
	this.sampleType = sampleType;
}
public String getTestCode() {
	return testCode;
}
public void setTestCode(String testCode) {
	this.testCode = testCode;
}
public String getPanelCode() {
	return panelCode;
}
public void setPanelCode(String panelCode) {
	this.panelCode = panelCode;
}
public String getPriority() {
	return priority;
}
public void setPriority(String priority) {
	this.priority = priority;
}

public String getSubSpecialityName() {
	return subSpecialityName;
}
public void setSubSpecialityName(String subSpecialityName) {
	this.subSpecialityName = subSpecialityName;
}
public String getSpecimanName() {
	return specimanName;
}
public void setSpecimanName(String specimanName) {
	this.specimanName = specimanName;
}
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
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

  
  
}
