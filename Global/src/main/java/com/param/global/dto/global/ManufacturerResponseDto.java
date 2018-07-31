package com.param.global.dto.global;

public class ManufacturerResponseDto {

	private Integer id;
	private String detail;
	private String manufacturerCode;
	private String manufacturerName;
	private String address;
	
	
	public ManufacturerResponseDto(Integer id, String detail, String manufacturerCode, String manufacturerName,
			String address) {
		super();
		this.id = id;
		this.detail = detail;
		this.manufacturerCode = manufacturerCode;
		this.manufacturerName = manufacturerName;
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


	public String getManufacturerCode() {
		return manufacturerCode;
	}


	public void setManufacturerCode(String manufacturerCode) {
		this.manufacturerCode = manufacturerCode;
	}


	public String getManufacturerName() {
		return manufacturerName;
	}


	public void setManufacturerName(String manufacturerName) {
		this.manufacturerName = manufacturerName;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}
	
	
	
	
}
