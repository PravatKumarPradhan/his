package com.param.entity.model.procurement;

import java.io.Serializable;
import javax.persistence.*;

import com.param.entity.model.base.BaseEntity;
import com.param.entity.model.master.OtherCharge;

import java.sql.Timestamp;


/**
 * The persistent class for the t_other_charges_grn_mapper database table.
 * 
 */
@Entity(name="com.param.entity.model.procurement.grnOtherChargesMapper")
@Table(name="t_other_charges_grn_mapper",schema="procurement")
public class GrnOtherChargesMapper extends BaseEntity implements Serializable {
   
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "other_charges_id")
	private OtherCharge otherCharge;
	
	@Column(name = "amount")
	private Double amount;
	

	//bi-directional many-to-one association to GoodReceiptNoteDetail
    @ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="grn_detail_id")
	private GoodReceiptNoteDetail goodReceiptNoteDetail;

	public GrnOtherChargesMapper() {
		super();
	}
	
	public GrnOtherChargesMapper(OtherCharge otherCharge, Double amount) {
		super();
		this.otherCharge = otherCharge;
		this.amount = amount;
	}
	
	public void updateGrnOtherChargesMapper(OtherCharge otherCharge, Double amount) {
		this.otherCharge = otherCharge;
		this.amount = amount;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public OtherCharge getOtherCharge() {
		return otherCharge;
	}

	public void setOtherCharge(OtherCharge otherCharge) {
		this.otherCharge = otherCharge;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public GoodReceiptNoteDetail getGoodReceiptNoteDetail() {
		return goodReceiptNoteDetail;
	}

	public void setGoodReceiptNoteDetail(GoodReceiptNoteDetail goodReceiptNoteDetail) {
		this.goodReceiptNoteDetail = goodReceiptNoteDetail;
	}

	
	
	
}