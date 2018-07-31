package com.param.lis.unit.dto;

	public class HeaderMasterDto
	{
	
		private Integer headerId;
		private String code;
		private String desc;
		private Character status; 
		private Integer createdBy;
		private Long createdDate;
		private int updatedBy;
		private Long updatedDate;
		private Integer orgId;
		private Integer unitId;
		public Integer getHeaderId() {
			return headerId;
		}
		public void setHeaderId(Integer headerId) {
			this.headerId = headerId;
		}
		public String getCode() {
			return code;
		}
		public void setCode(String code) {
			this.code = code;
		}
		public String getDesc() {
			return desc;
		}
		public void setDesc(String desc) {
			this.desc = desc;
		}
		public Character getStatus() {
			return status;
		}
		public void setStatus(Character status) {
			this.status = status;
		}
		public Integer getCreatedBy() {
			return createdBy;
		}
		public void setCreatedBy(Integer createdBy) {
			this.createdBy = createdBy;
		}
		public Long getCreatedDate() {
			return createdDate;
		}
		public void setCreatedDate(Long createdDate) {
			this.createdDate = createdDate;
		}
		public int getUpdatedBy() {
			return updatedBy;
		}
		public void setUpdatedBy(int updatedBy) {
			this.updatedBy = updatedBy;
		}
		public Long getUpdatedDate() {
			return updatedDate;
		}
		public void setUpdatedDate(Long updatedDate) {
			this.updatedDate = updatedDate;
		}
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

		
		
	}
