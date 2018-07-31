package com.param.entity.model.procurement;

import java.io.Serializable;
import javax.persistence.*;

import com.param.entity.model.base.BaseEntity;

import java.util.Date;
import java.sql.Timestamp;


/**
 * The persistent class for the t_po_staged database table.
 * 
 */
@Entity(name="PurchaseOrderStaged")
@Table(name="t_po_staged", schema = "procurement")
public class PurchaseOrderStaged extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private Integer id;

	
	private Integer quantity;

	@Temporal(TemporalType.DATE)
	@Column(name="staged_date")
	private Date stagedDate;

	//bi-directional many-to-one association to TPurchaseOrderDetail
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="po_detail_id")
	private PurchaseOrderDetail purchaseOrderDetail;

	public PurchaseOrderStaged() {
		super();
	}
	
	public PurchaseOrderStaged(Integer id) {
		this.id = id ;
	}
	
	public PurchaseOrderStaged(Integer quantity, Date stagedDate) {
		super();
		this.quantity = quantity;
		this.stagedDate = stagedDate;
	}
	
	public void updatePurchaseOrderStaged(Integer quantity, Date stagedDate) {
		this.quantity = quantity;
		this.stagedDate = stagedDate;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Date getStagedDate() {
		return stagedDate;
	}

	public void setStagedDate(Date stagedDate) {
		this.stagedDate = stagedDate;
	}

	public PurchaseOrderDetail getPurchaseOrderDetail() {
		return purchaseOrderDetail;
	}

	public void setPurchaseOrderDetail(PurchaseOrderDetail purchaseOrderDetail) {
		this.purchaseOrderDetail = purchaseOrderDetail;
	}

	

}