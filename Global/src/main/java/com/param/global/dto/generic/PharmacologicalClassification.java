package com.param.global.dto.generic;

public class PharmacologicalClassification {

	protected Integer id;

	protected String classification;

	public PharmacologicalClassification(Integer id, String classification) {
		super();
		this.id = id;
		this.classification = classification;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getClassification() {
		return classification;
	}

	public void setClassification(String classification) {
		this.classification = classification;
	}

}
