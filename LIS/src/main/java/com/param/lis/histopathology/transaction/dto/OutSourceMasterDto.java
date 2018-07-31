package com.param.lis.histopathology.transaction.dto;

import java.math.BigInteger;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class OutSourceMasterDto {
	
	private Integer outSourcedId;
	private String uhid;
	private String patientDetails;
	private String doctorDetails;
	private String deptName;
	private String priorityName;
	private String colorCode;
	private String testDesc;
	private String containerName;
	private Integer patientId;
	private Integer doctorId;
	private Integer testId;
	private Integer visitTypeId;
	private String visitTypeCode;
	private String uploadDoc;
	private String notes;
	private Integer createdBy;
	private Long createdDate;
	private Integer updatedBy;
	private Long updatedDate;
	private Integer labSampleDtlsId;
	private Character status;
	private Integer orderId;
	private Integer orgId;
	private Integer orgUnitId;
	private Integer sampleStatusId;
	private String sampleNo;
	private BigInteger labSampleNo;
	private Integer offset;
	private Integer recordPerPage;
	private Integer sampleStatus;
	private Integer accPayableMstId;
	private String outSourceLabName;
	/*private MultipartFile inputFile;*/
	
	private List<OutSourceDetailMasterDto> listOutSourceDetailMasterDto;

	
	public Integer getOutSourcedId() {
		return outSourcedId;
	}

	public void setOutSourcedId(Integer outSourcedId) {
		this.outSourcedId = outSourcedId;
	}

	public Integer getPatientId() {
		return patientId;
	}

	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}

	public Integer getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(Integer doctorId) {
		this.doctorId = doctorId;
	}

	public Integer getTestId() {
		return testId;
	}

	public void setTestId(Integer testId) {
		this.testId = testId;
	}

	public Integer getVisitTypeId() {
		return visitTypeId;
	}

	public void setVisitTypeId(Integer visitTypeId) {
		this.visitTypeId = visitTypeId;
	}


	public String getUploadDoc() {
		return uploadDoc;
	}

	public void setUploadDoc(String uploadDoc) {
		this.uploadDoc = uploadDoc;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Long getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Long createdDate) {
		this.createdDate = createdDate;
	}

	public Integer getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Long getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Long updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Integer getLabSampleDtlsId() {
		return labSampleDtlsId;
	}

	public void setLabSampleDtlsId(Integer labSampleDtlsId) {
		this.labSampleDtlsId = labSampleDtlsId;
	}


	public Character getStatus() {
		return status;
	}

	public void setStatus(Character status) {
		this.status = status;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
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

	public BigInteger getLabSampleNo() {
		return labSampleNo;
	}

	public void setLabSampleNo(BigInteger labSampleNo) {
		this.labSampleNo = labSampleNo;
	}

	public Integer getOffset() {
		return offset;
	}

	public void setOffset(Integer offset) {
		this.offset = offset;
	}

	public Integer getRecordPerPage() {
		return recordPerPage;
	}

	public void setRecordPerPage(Integer recordPerPage) {
		this.recordPerPage = recordPerPage;
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

	public String getTestDesc() {
		return testDesc;
	}

	public void setTestDesc(String testDesc) {
		this.testDesc = testDesc;
	}

	public String getContainerName() {
		return containerName;
	}

	public void setContainerName(String containerName) {
		this.containerName = containerName;
	}

	public String getVisitTypeCode() {
		return visitTypeCode;
	}

	public void setVisitTypeCode(String visitTypeCode) {
		this.visitTypeCode = visitTypeCode;
	}

	public Integer getSampleStatusId() {
		return sampleStatusId;
	}

	public void setSampleStatusId(Integer sampleStatusId) {
		this.sampleStatusId = sampleStatusId;
	}

	public String getSampleNo() {
		return sampleNo;
	}

	public void setSampleNo(String sampleNo) {
		this.sampleNo = sampleNo;
	}

	public Integer getSampleStatus() {
		return sampleStatus;
	}

	public void setSampleStatus(Integer sampleStatus) {
		this.sampleStatus = sampleStatus;
	}

	public List<OutSourceDetailMasterDto> getListOutSourceDetailMasterDto() {
		return listOutSourceDetailMasterDto;
	}

	public void setListOutSourceDetailMasterDto(List<OutSourceDetailMasterDto> listOutSourceDetailMasterDto) {
		this.listOutSourceDetailMasterDto = listOutSourceDetailMasterDto;
	}

	public Integer getAccPayableMstId() {
		return accPayableMstId;
	}

	public void setAccPayableMstId(Integer accPayableMstId) {
		this.accPayableMstId = accPayableMstId;
	}

	public String getOutSourceLabName() {
		return outSourceLabName;
	}

	public void setOutSourceLabName(String outSourceLabName) {
		this.outSourceLabName = outSourceLabName;
	}

	/*public MultipartFile getInputFile() {
		return inputFile;
	}

	public void setInputFile(MultipartFile inputFile) {
		this.inputFile = inputFile;
	}
	*/

}
