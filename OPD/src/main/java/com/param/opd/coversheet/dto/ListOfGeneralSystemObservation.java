package com.param.opd.coversheet.dto;

import java.util.List;

public class ListOfGeneralSystemObservation {

	private Integer systemObervationId;
	private char isNADValue;
	private String remark;
	
	private List<ListOfGeneralSystemProperty> listOfGeneralSystemProperty;
	
	public List<ListOfGeneralSystemProperty> getListOfGeneralSystemProperty() {
		return listOfGeneralSystemProperty;
	}
	public void setListOfGeneralSystemProperty(
			List<ListOfGeneralSystemProperty> listOfGeneralSystemProperty) {
		this.listOfGeneralSystemProperty = listOfGeneralSystemProperty;
	}
	public Integer getSystemObervationId() {
		return systemObervationId;
	}
	public void setSystemObervationId(Integer systemObervationId) {
		this.systemObervationId = systemObervationId;
	}
	public char getIsNADValue() {
		return isNADValue;
	}
	public void setIsNADValue(char isNADValue) {
		this.isNADValue = isNADValue;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
}
