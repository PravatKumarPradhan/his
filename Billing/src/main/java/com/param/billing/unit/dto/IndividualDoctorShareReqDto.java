package com.param.billing.unit.dto;

import java.util.List;


public class IndividualDoctorShareReqDto {

	private Integer orgId;
	private Integer unitId;
	
	
	private List<TIndividualDocShareGroupWiseDto> listTIndividualDocShareGroupWiseDto;
	
	private List<TIndividualDocShareServiceWiseDto> listTIndividualDocShareServiceWiseDto;

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

	public List<TIndividualDocShareGroupWiseDto> getListTIndividualDocShareGroupWiseDto() {
		return listTIndividualDocShareGroupWiseDto;
	}

	public void setListTIndividualDocShareGroupWiseDto(
			List<TIndividualDocShareGroupWiseDto> listTIndividualDocShareGroupWiseDto) {
		this.listTIndividualDocShareGroupWiseDto = listTIndividualDocShareGroupWiseDto;
	}

	public List<TIndividualDocShareServiceWiseDto> getListTIndividualDocShareServiceWiseDto() {
		return listTIndividualDocShareServiceWiseDto;
	}

	public void setListTIndividualDocShareServiceWiseDto(
			List<TIndividualDocShareServiceWiseDto> listTIndividualDocShareServiceWiseDto) {
		this.listTIndividualDocShareServiceWiseDto = listTIndividualDocShareServiceWiseDto;
	}

	

}
