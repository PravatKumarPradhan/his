package com.param.billing.unit.dto;

import java.util.List;


public class DoctorShareReqDto {

	private Integer orgId;
	private Integer unitId;
	
	
	private List<TGlobalDocShareGroupWiseDto> listTGlobalDocShareGroupWiseDto;
	
	private List<TGlobalDocShareServiceWiseDto> listTGlobalDocShareServiceWiseDto;

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public Integer getUnitId() {
		return unitId;
	}

	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}

	public List<TGlobalDocShareGroupWiseDto> getListTGlobalDocShareGroupWiseDto() {
		return listTGlobalDocShareGroupWiseDto;
	}

	public void setListTGlobalDocShareGroupWiseDto(List<TGlobalDocShareGroupWiseDto> listTGlobalDocShareGroupWiseDto) {
		this.listTGlobalDocShareGroupWiseDto = listTGlobalDocShareGroupWiseDto;
	}

	public List<TGlobalDocShareServiceWiseDto> getListTGlobalDocShareServiceWiseDto() {
		return listTGlobalDocShareServiceWiseDto;
	}

	public void setListTGlobalDocShareServiceWiseDto(
			List<TGlobalDocShareServiceWiseDto> listTGlobalDocShareServiceWiseDto) {
		this.listTGlobalDocShareServiceWiseDto = listTGlobalDocShareServiceWiseDto;
	}
	
	
	

}
