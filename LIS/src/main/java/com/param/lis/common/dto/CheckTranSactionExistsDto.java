package com.param.lis.common.dto;

import java.util.List;

public class CheckTranSactionExistsDto {
	private List<Integer> listCommonId;
	private Integer orgId;
	private Integer mediaId;
	
	public List<Integer> getListCommonId() {
		return listCommonId;
	}
	public void setListCommonId(List<Integer> listCommonId) {
		this.listCommonId = listCommonId;
	}
	public Integer getOrgId() {
		return orgId;
	}
	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}
	public Integer getMediaId() {
		return mediaId;
	}
	public void setMediaId(Integer mediaId) {
		this.mediaId = mediaId;
	}
	
	

	
}
