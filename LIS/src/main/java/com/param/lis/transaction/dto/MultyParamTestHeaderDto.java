package com.param.lis.transaction.dto;

import java.util.List;

public class MultyParamTestHeaderDto {

	private Integer headerId;
	private String headerCode;
	private String headerDesc;
	
	private List<LabResultDetailsViewDto> listLabResultDetailsViewDto;
	
	
	public Integer getHeaderId() {
		return headerId;
	}

	public void setHeaderId(Integer headerId) {
		this.headerId = headerId;
	}

	public String getHeaderCode() {
		return headerCode;
	}

	public void setHeaderCode(String headerCode) {
		this.headerCode = headerCode;
	}

	public String getHeaderDesc() {
		return headerDesc;
	}

	public void setHeaderDesc(String headerDesc) {
		this.headerDesc = headerDesc;
	}

	public List<LabResultDetailsViewDto> getListLabResultDetailsViewDto() {
		return listLabResultDetailsViewDto;
	}

	public void setListLabResultDetailsViewDto(List<LabResultDetailsViewDto> listLabResultDetailsViewDto) {
		this.listLabResultDetailsViewDto = listLabResultDetailsViewDto;
	}

	

}
