package com.param.global.dto.global;

public class PaymentMode {
	
	protected Integer id;

	protected String code;
	
	

	public PaymentMode() {
		super();
	}
	
	public PaymentMode(Integer id, String code) {
		super();
		this.id = id;
		this.code = code;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	

}
