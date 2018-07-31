package com.param.lis.unit.dto;


public class TPanelDetailsMasterDto {
  

	  private Integer panelDetailsId;
	  private Integer panelId;
	  private Integer serviceId;
	  private Integer printOrder;
	  private Integer testId;

	  private Integer headerId;

	  private Integer orgId;

	  private Integer orgUnitId;
	  
	  private Character status;
	  
	  private Character isDeleted;
	  
	  private Integer createdBy;

	  private Long createdDate;

	  private Integer updatedBy;

	  private Long updatedDate;
	 

	  public Integer getServiceId() {
	    return serviceId;
	  }

	  public void setServiceId(Integer serviceId) {
	    this.serviceId = serviceId;
	  }

	  public Integer getTestId() {
	    return testId;
	  }

	  public void setTestId(Integer testId) {
	    this.testId = testId;
	  }

	  public Integer getHeaderId() {
	    return headerId;
	  }

	  public void setHeaderId(Integer headerId) {
	    this.headerId = headerId;
	  }

	  public Integer getOrgId() {
	    return orgId;
	  }

	  public void setOrgId(Integer orgId) {
	    this.orgId = orgId;
	  }

	  public Integer getOrgUnitId() {
	    return orgUnitId;
	  }

	  public void setOrgUnitId(Integer orgUnitId) {
	    this.orgUnitId = orgUnitId;
	  }

	  public Character getStatus() {
	    return status;
	  }

	  public void setStatus(Character status) {
	    this.status = (status == '\u0000') ? 'I' : status;
	  }

	  public Character getIsDeleted() {
	    return isDeleted;
	  }

	  public void setIsDeleted(Character isDeleted) {
	    this.isDeleted =  (isDeleted == '\u0000') ? 'N' : isDeleted;
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

	  public Integer getUpdatedBy() {
	    return updatedBy;
	  }

	  public void setUpdatedBy(Integer updatedBy) {
	    this.updatedBy = updatedBy;
	  }

	  public Long getUpdatedDate() {
	    return updatedDate;
	  }

	  public void setUpdatedDate(Long updatedDate) {
	    this.updatedDate = updatedDate;
	  }

	public Integer getPanelDetailsId() {
		return panelDetailsId;
	}

	public void setPanelDetailsId(Integer panelDetailsId) {
		this.panelDetailsId = panelDetailsId;
	}

	public Integer getPanelId() {
		return panelId;
	}

	public void setPanelId(Integer panelId) {
		this.panelId = panelId;
	}

	public Integer getPrintOrder() {
		return printOrder;
	}

	public void setPrintOrder(Integer printOrder) {
		this.printOrder = printOrder;
	}


}
