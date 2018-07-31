package com.param.global.dto.global;

import java.util.Date;

public class PurchaseOrderStaged {
	
    private Integer id;
	
	private Integer stagedQuantity;
	
	private Date stagedDate;
	

	public PurchaseOrderStaged() {
		super();
	}


	public PurchaseOrderStaged(Integer id, Integer stagedQuantity, Date stagedDate) {
		super();
		this.id = id;
		this.stagedQuantity = stagedQuantity;
		this.stagedDate = stagedDate;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Integer getStagedQuantity() {
		return stagedQuantity;
	}


	public void setStagedQuantity(Integer stagedQuantity) {
		this.stagedQuantity = stagedQuantity;
	}


	public Date getStagedDate() {
		return stagedDate;
	}


	public void setStagedDate(Date stagedDate) {
		this.stagedDate = stagedDate;
	}
	
	

}
