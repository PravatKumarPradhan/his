package com.param.lis.common.dto;

import java.math.BigInteger;

public class CommonAutoCompleteDto
{
	private Integer id;
	private String label;
	private String name;
	private String firstName;
	private String lastName;
	private String uhid;
	private String patientWithUhid;
	private BigInteger  sampleNo;
	public CommonAutoCompleteDto()
	{
		
	}

	public CommonAutoCompleteDto(Integer id, String label) {
		super();
		this.id = id;
		this.label = label;
	}

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}



	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUhid() {
		return uhid;
	}

	public void setUhid(String uhid) {
		this.uhid = uhid;
	}

	public String getPatientWithUhid() {
		return getFirstName()+" "+getLastName()+" | "+getUhid();
	}

	public void setPatientWithUhid(String patientWithUhid) {
		this.patientWithUhid = patientWithUhid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigInteger getSampleNo() {
		return sampleNo;
	}

	public void setSampleNo(BigInteger sampleNo) {
		this.sampleNo = sampleNo;
	}

}
