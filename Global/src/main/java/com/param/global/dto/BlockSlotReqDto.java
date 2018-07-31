package com.param.global.dto;

import java.util.List;

public class BlockSlotReqDto {

	private List<Integer> listSlotId;
	//private Integer doctorId;
	private Integer reasonId;
	private String remark;
	private Integer createdBy;
	private String createdDate;
	private Integer isBlockUnblock;
	private Integer organisationId;
	private Integer unitId;

	
	public List<Integer> getListSlotId() {
		return listSlotId;
	}
	public void setListSlotId(List<Integer> listSlotId) {
		this.listSlotId = listSlotId;
	}
	/*public Integer getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(Integer doctorId) {
		this.doctorId = doctorId;
	}*/
	public Integer getReasonId() {
		return reasonId;
	}
	public void setReasonId(Integer reasonId) {
		this.reasonId = reasonId;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public Integer getIsBlockUnblock() {
		return isBlockUnblock;
	}
	public void setIsBlockUnblock(Integer isBlockUnblock) {
		this.isBlockUnblock = isBlockUnblock;
	}
	public Integer getOrganisationId() {
		return organisationId;
	}
	public void setOrganisationId(Integer organisationId) {
		this.organisationId = organisationId;
	}
	public Integer getUnitId() {
		return unitId;
	}
	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}
	
	
}
