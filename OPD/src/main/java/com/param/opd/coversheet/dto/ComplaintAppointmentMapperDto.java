package com.param.opd.coversheet.dto;

import java.util.List;


public class ComplaintAppointmentMapperDto {

	private int complaintId;
	private Integer encounterId;
	private Integer unitId;
	private Integer organizationId;
	private char status;
	private Integer createdBy;
	private String ceratedDate;
	private Integer updatedBy;
	private String updatedDate;
	private Integer roleId;
	private Integer complaintAppoId;
	private char isReviewedFlag;
	private List<ComplaintAppointmentDetailsDto> listComplaintAppointmentDetailsDto;
	
	public int getComplaintId() {
		return complaintId;
	}
	public void setComplaintId(int complaintId) {
		this.complaintId = complaintId;
	}
	public Integer getEncounterId() {
		return encounterId;
	}
	public void setEncounterId(Integer encounterId) {
		this.encounterId = encounterId;
	}
	public Integer getUnitId() {
		return unitId;
	}
	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}
	public Integer getOrganizationId() {
		return organizationId;
	}
	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}
	public char getStatus() {
		return status;
	}
	public void setStatus(char status) {
		this.status = status;
	}
	public Integer getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}
	public String getCeratedDate() {
		return ceratedDate;
	}
	public void setCeratedDate(String ceratedDate) {
		this.ceratedDate = ceratedDate;
	}
	public Integer getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}
	public String getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public Integer getComplaintAppoId() {
		return complaintAppoId;
	}
	public void setComplaintAppoId(Integer complaintAppoId) {
		this.complaintAppoId = complaintAppoId;
	}
	public char getIsReviewedFlag() {
		return isReviewedFlag;
	}
	public void setIsReviewedFlag(char isReviewedFlag) {
		this.isReviewedFlag = isReviewedFlag;
	}
	public List<ComplaintAppointmentDetailsDto> getListComplaintAppointmentDetailsDto() {
		return listComplaintAppointmentDetailsDto;
	}
	public void setListComplaintAppointmentDetailsDto(
			List<ComplaintAppointmentDetailsDto> listComplaintAppointmentDetailsDto) {
		this.listComplaintAppointmentDetailsDto = listComplaintAppointmentDetailsDto;
	}
		
}
