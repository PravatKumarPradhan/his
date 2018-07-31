package com.param.service.dto;

public class EhcPackageSearchResDto {

	private String packageCode;
	private String packageName;
	private String packageType;
	private String visitType;
	private Character status;
	private Integer packageId;
	private Integer packagetypeId;
	public String getVisitType() {
		return visitType;
	}
	public void setVisitType(String visitType) {
		this.visitType = visitType;
	}
	public String getPackageCode() {
		return packageCode;
	}
	public void setPackageCode(String packageCode) {
		this.packageCode = packageCode;
	}
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	public String getPackageType() {
		return packageType;
	}
	public void setPackageType(String packageType) {
		this.packageType = packageType;
	}
	public Character getStatus() {
		return status;
	}
	public void setStatus(Character status) {
		this.status = status;
	}
	public Integer getPackageId() {
		return packageId;
	}
	public void setPackageId(Integer packageId) {
		this.packageId = packageId;
	}
	public Integer getPackagetypeId() {
		return packagetypeId;
	}
	public void setPackagetypeId(Integer packagetypeId) {
		this.packagetypeId = packagetypeId;
	}
	
}
