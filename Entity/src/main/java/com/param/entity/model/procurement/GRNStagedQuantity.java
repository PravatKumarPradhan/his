package com.param.entity.model.procurement;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.param.entity.model.base.BaseEntity;

@Entity(name = "com.param.entity.model.procurement.GrnStagedQuantity")
@Table(name = "t_grn_staged_qunatity", schema = "procurement")
public class GRNStagedQuantity extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "po_staged_id")
	private PurchaseOrderStaged purchaseOrderStaged;

	private Integer receivedQuantity;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "grn_detail_id")
	private GoodReceiptNoteDetail goodReceiptNoteDetail;

	public GRNStagedQuantity() {
		super();
	}

	public GRNStagedQuantity(Integer receivedQuantity, PurchaseOrderStaged purchaseOrderStaged) {
		super();
		this.receivedQuantity = receivedQuantity;
		this.purchaseOrderStaged = purchaseOrderStaged;
	}

	public void updateGRNStagedQuantity(Integer receivedQuantity) {
		this.receivedQuantity = receivedQuantity;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public PurchaseOrderStaged getPurchaseOrderStaged() {
		return purchaseOrderStaged;
	}

	public void setPurchaseOrderStaged(PurchaseOrderStaged purchaseOrderStaged) {
		this.purchaseOrderStaged = purchaseOrderStaged;
	}

	public Integer getReceivedQuantity() {
		return receivedQuantity;
	}

	public void setReceivedQuantity(Integer receivedQuantity) {
		this.receivedQuantity = receivedQuantity;
	}

	public GoodReceiptNoteDetail getGoodReceiptNoteDetail() {
		return goodReceiptNoteDetail;
	}

	public void setGoodReceiptNoteDetail(GoodReceiptNoteDetail goodReceiptNoteDetail) {
		this.goodReceiptNoteDetail = goodReceiptNoteDetail;
	}

}
