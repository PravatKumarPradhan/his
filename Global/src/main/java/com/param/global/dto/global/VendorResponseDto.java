package com.param.global.dto.global;

public class VendorResponseDto {
	
	private Integer id;
	private String detail;
	private String vendorCode;
	private String vendorName;
	private String address;
	
	public VendorResponseDto(Integer id, String detail, String vendorCode, String vendorName, String address) {
		super();
		this.id = id;
		this.detail = detail;
		this.vendorCode = vendorCode;
		this.vendorName = vendorName;
		this.address = address;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getVendorCode() {
		return vendorCode;
	}
	public void setVendorCode(String vendorCode) {
		this.vendorCode = vendorCode;
	}
	public String getVendorName() {
		return vendorName;
	}
	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	

}
