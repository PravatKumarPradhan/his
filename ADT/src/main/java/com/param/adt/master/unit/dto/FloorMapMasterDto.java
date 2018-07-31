package com.param.adt.master.unit.dto;

public class FloorMapMasterDto 
{
	
	private int floorMapId;
	
	private Integer floorId;
	
	private Integer wingId;
	
	private Integer specialityId;
	
	private Integer subSpecialityId;

	private char status;
	
	private int createdBy;
	
	private String createdDate;
	
	private int updatedBy;
	
	private String updatedDate;

	public int getFloorMapId() {
		return floorMapId;
	}

	public void setFloorMapId(int floorMapId) {
		this.floorMapId = floorMapId;
	}

	public Integer getFloorId() {
		return floorId;
	}

	public void setFloorId(Integer floorId) {
		this.floorId = floorId;
	}

	public Integer getWingId() {
		return wingId;
	}

	public void setWingId(Integer wingId) {
		this.wingId = wingId;
	}

	public Integer getSpecialityId() {
		return specialityId;
	}

	public void setSpecialityId(Integer specialityId) {
		this.specialityId = specialityId;
	}

	public Integer getSubSpecialityId() {
		return subSpecialityId;
	}

	public void setSubSpecialityId(Integer subSpecialityId) {
		this.subSpecialityId = subSpecialityId;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}

	public int getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public int getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(int updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}
	
	
}
