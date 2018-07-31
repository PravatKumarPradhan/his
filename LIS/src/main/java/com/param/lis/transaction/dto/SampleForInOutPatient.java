package com.param.lis.transaction.dto;

import java.util.Date;

public class SampleForInOutPatient
{
	private Integer labSampleDtlsId;
	private String labSampleNo;
	private String visitType;
	private String uhid;
	private String patientDetails;
	private String doctorDetails;
	private String testDesc;
	private Character isAlliquoteReq;
	private String wardCode;
	private String bedNumber;
	private String priorityName;
	private String colorCode;
	private Character isAcceptOrReject;
	private Character sendToDept;
	private String sampleBarcode;
	private Integer sampleRejectReasonId;
	private String sampleRejectReason;
	private Integer sampWrkStatusId;
	private Date createdDate;
	private Integer samplePendingCount;
	private Character isCentrifugationReq;
	private String panelCode;
    private Date orderDateTime;
    private Date sampleSendtocrDatetime;
    private String isSelected;
    private Date sampleCollectionDatetime;
    private String sampleType;
    private String testCode;

	
	public Integer getSamplePendingCount() {
		return samplePendingCount;
	}

	public void setSamplePendingCount(Integer samplePendingCount) {
		this.samplePendingCount = samplePendingCount;
	}

	public String getWardCode()
	{
		return wardCode;
	}

	public void setWardCode(String wardCode)
	{
		this.wardCode = wardCode;
	}

	public String getBedNumber()
	{
		return bedNumber;
	}

	public void setBedNumber(String bedNumber)
	{
		this.bedNumber = bedNumber;
	}

	private Integer sampleStatusId;

	public Integer getLabSampleDtlsId()
	{
		return labSampleDtlsId;
	}

	public void setLabSampleDtlsId(Integer labSampleDtlsId)
	{
		this.labSampleDtlsId = labSampleDtlsId;
	}

	public String getUhid()
	{
		return uhid;
	}

	public void setUhid(String uhid)
	{
		this.uhid = uhid;
	}

	public String getPatientDetails()
	{
		return patientDetails;
	}

	public void setPatientDetails(String patientDetails)
	{
		this.patientDetails = patientDetails;
	}

	public String getTestDesc()
	{
		return testDesc;
	}

	public void setTestDesc(String testDesc)
	{
		this.testDesc = testDesc;
	}

	public Character getIsAlliquoteReq()
	{
		return isAlliquoteReq;
	}

	public void setIsAlliquoteReq(Character isAlliquoteReq)
	{
		this.isAlliquoteReq = isAlliquoteReq;
	}

	public String getPriorityName()
	{
		return priorityName;
	}

	public void setPriorityName(String priorityName)
	{
		this.priorityName = priorityName;
	}

	public String getColorCode()
	{
		return colorCode;
	}

	public void setColorCode(String colorCode)
	{
		this.colorCode = colorCode;
	}

	public Character getIsAcceptOrReject()
	{
		return isAcceptOrReject;
		
	}

	public void setIsAcceptOrReject(Character isAcceptOrReject)
	{
		this.isAcceptOrReject = isAcceptOrReject;
	}

	public Integer getSampleStatusId()
	{
		return sampleStatusId;
	}

	public void setSampleStatusId(Integer sampleStatusId)
	{
		this.sampleStatusId = sampleStatusId;
	}

	public String getDoctorDetails()
	{
		return doctorDetails;
	}

	public void setDoctorDetails(String doctorDetails)
	{
		this.doctorDetails = doctorDetails;
	}

	public String getVisitType()
	{
		return visitType;
	}

	public void setVisitType(String visitType)
	{
		this.visitType = visitType;
	}

	public Character getSendToDept()
	{
		return sendToDept;
	}

	public void setSendToDept(Character sendToDept)
	{
		this.sendToDept = sendToDept;
	}

	public String getSampleBarcode()
	{
		return sampleBarcode;
	}

	public void setSampleBarcode(String sampleBarcode)
	{
		this.sampleBarcode = sampleBarcode;
	}

	public Integer getSampleRejectReasonId()
	{
		return sampleRejectReasonId;
	}

	public void setSampleRejectReasonId(Integer sampleRejectReasonId)
	{
		this.sampleRejectReasonId = sampleRejectReasonId;
	}

	public String getSampleRejectReason()
	{
		return sampleRejectReason;
	}

	public void setSampleRejectReason(String sampleRejectReason)
	{
		this.sampleRejectReason = sampleRejectReason;
	}

	public String getLabSampleNo()
	{
		return labSampleNo;
	}

	public void setLabSampleNo(String labSampleNo)
	{
		this.labSampleNo = labSampleNo;
	}

	public String getSampWrkStatusId()
	{
		return "1"; //Dummy Code
	}

	public void setSampWrkStatusId(Integer sampWrkStatusId)
	{
		this.sampWrkStatusId = sampWrkStatusId;
	}

	public Date getCreatedDate()
	{
		return createdDate;
	}

	public void setCreatedDate(Date createdDate)
	{
		this.createdDate = createdDate;
	}

	public Character getIsCentrifugationReq() {
		return isCentrifugationReq;
	}

	public void setIsCentrifugationReq(Character isCentrifugationReq) {
		this.isCentrifugationReq = isCentrifugationReq;
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

  public Date getSampleSendtocrDatetime() {
    return sampleSendtocrDatetime;
  }

  public void setSampleSendtocrDatetime(Date sampleSendtocrDatetime) {
    this.sampleSendtocrDatetime = sampleSendtocrDatetime;
  }

  public String getIsSelected() {
    return isSelected;
  }

  public void setIsSelected(String isSelected) {
    this.isSelected = isSelected;
  }

  public Date getSampleCollectionDatetime() {
    return sampleCollectionDatetime;
  }

  public void setSampleCollectionDatetime(Date sampleCollectionDatetime) {
    this.sampleCollectionDatetime = sampleCollectionDatetime;
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
	
  
}
