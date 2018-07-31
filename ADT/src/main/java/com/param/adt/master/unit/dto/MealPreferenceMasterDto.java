package com.param.adt.master.unit.dto;

public class MealPreferenceMasterDto 
{
	private int mealPreferenceId;
	
	private String mealPreferenceCode;
	
	private String mealPreferenceDesc;
	
	private String createdDate;

	private int createdBy;

	private String userName;
	
	private int updatedBy;

	private String updatedDate;
		
	private char status;
	
	private Integer organizationId;
	
	private Integer unitId;

	public int getMealPreferenceId() {
		return mealPreferenceId;
	}

	public void setMealPreferenceId(int mealPreferenceId) {
		this.mealPreferenceId = mealPreferenceId;
	}

	public String getMealPreferenceCode() {
		return mealPreferenceCode;
	}

	public void setMealPreferenceCode(String mealPreferenceCode) {
		this.mealPreferenceCode = mealPreferenceCode;
	}

	public String getMealPreferenceDesc() {
		return mealPreferenceDesc;
	}

	public void setMealPreferenceDesc(String mealPreferenceDesc) {
		this.mealPreferenceDesc = mealPreferenceDesc;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public int getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}

	public Integer getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}

	public Integer getUnitId() {
		return unitId;
	}

	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}
	
	
}
