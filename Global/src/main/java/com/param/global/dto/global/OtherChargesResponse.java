package com.param.global.dto.global;

public class OtherChargesResponse {
	
    private Integer id;
	
    private String otherCharges;
    
   

	public OtherChargesResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OtherChargesResponse(Integer id, String otherCharges) {
		super();
		this.id = id;
		this.otherCharges = otherCharges;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOtherCharges() {
		return otherCharges;
	}

	public void setOtherCharges(String otherCharges) {
		this.otherCharges = otherCharges;
	}

	
	

}
