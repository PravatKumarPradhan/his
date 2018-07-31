package com.param.entity.model.pharmacy;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.param.entity.model.base.BaseEntity;
import com.param.entity.model.inventory.StoreIndentDetail;
import com.param.entity.model.master.Store;

@Entity(name="Return")
@Table(name="t_return",schema="pharmacy")
public class Return extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer id;

	@Column(name="return_no", nullable=false, length=50)
	private String returnNo;

	@Column(name="total_return_amt", nullable=false)
	private Double totalReturnAmt;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="sale_id", nullable=false)
	private Sale sale;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="store_id", nullable=false)
	private Store store;

	public Return() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getReturnNo() {
		return this.returnNo;
	}

	public void setReturnNo(String returnNo) {
		this.returnNo = returnNo;
	}

	public Double getTotalReturnAmt() {
		return this.totalReturnAmt;
	}

	public void setTotalReturnAmt(Double totalReturnAmt) {
		this.totalReturnAmt = totalReturnAmt;
	}

	public Sale getSale() {
		return this.sale;
	}

	public void setSale(Sale sale) {
		this.sale = sale;
	}

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "returnId", cascade = CascadeType.ALL)
	private List<ReturnDetail> returnDetailList;
	
	public List<ReturnDetail> getReturnDetailList() {
		return returnDetailList;
	}

	public void setReturnDetailList(List<ReturnDetail> returnDetailList) {
		this.returnDetailList = returnDetailList;
	}

	public Return(Double totalReturnAmt, Sale sale, Store store, String returnNo) {
		super();
		this.totalReturnAmt = totalReturnAmt;
		this.sale = sale;
		this.store = store;
		this.returnNo = returnNo;
	}

	public ReturnDetail addReturnDetailList(ReturnDetail returnDetail) {
		if (getReturnDetailList() == null)
			this.returnDetailList = new ArrayList<ReturnDetail>();
		
		getReturnDetailList().add(returnDetail);
		returnDetail.setReturnId(this);

		return returnDetail;
	}
	
}