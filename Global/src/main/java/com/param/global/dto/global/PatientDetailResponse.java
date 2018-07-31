package com.param.global.dto.global;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class PatientDetailResponse
{
	private Integer visitTypeId;
	
	private String visitType;
	
	private Integer patientId;
	
	private String patientName;
	
	private String patientDetail;
	
	private String uhid;
	
	private Integer wardId;
	
	private String wardName;
	
	private Integer bedId;
	
	private String bedNo;
	
	private List<VisitDetails> visitDetails;
	
	public Integer getVisitTypeId() {
		return visitTypeId;
	}

	public void setVisitTypeId(Integer visitTypeId) {
		this.visitTypeId = visitTypeId;
	}

	public String getVisitType() {
		return visitType;
	}

	public void setVisitType(String visitType) {
		this.visitType = visitType;
	}

	public Integer getPatientId() {
		return patientId;
	}

	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getPatientDetail() {
		return patientDetail;
	}

	public void setPatientDetail(String patientDetail) {
		this.patientDetail = patientDetail;
	}

	public String getUhid() {
		return uhid;
	}

	public void setUhid(String uhid) {
		this.uhid = uhid;
	}

	public Integer getWardId() {
		return wardId;
	}

	public void setWardId(Integer wardId) {
		this.wardId = wardId;
	}

	public String getWardName() {
		return wardName;
	}

	public void setWardName(String wardName) {
		this.wardName = wardName;
	}

	public Integer getBedId() {
		return bedId;
	}

	public void setBedId(Integer bedId) {
		this.bedId = bedId;
	}

	public String getBedNo() {
		return bedNo;
	}

	public void setBedNo(String bedNo) {
		this.bedNo = bedNo;
	}

	public List<VisitDetails> getVisitDetails() {
		return visitDetails;
	}

	public void setVisitDetails(List<VisitDetails> visitDetails) {
		this.visitDetails = visitDetails;
	}

	public PatientDetailResponse()
	{
		super();
	}

	public PatientDetailResponse(Integer patientId, String patientName, String patientDetail, String uhid) {
		super();
		this.patientId = patientId;
		this.patientName = patientName;
		this.patientDetail = patientDetail;
		this.uhid = uhid;
	}
	
}