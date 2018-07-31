package com.param.entity.model.procurement;

import java.io.Serializable;
import javax.persistence.*;

import com.param.entity.model.base.BaseEntity;
import com.param.entity.model.base.UomEntity;
import com.param.entity.model.master.UomType;
import com.param.entity.model.master.UomUnit;

import java.sql.Timestamp;

/**
 * The persistent class for the t_grn_bonus_uom_mapper database table.
 * 
 */
@Entity(name="com.param.entity.model.procurement.grnBonusUomMapper")
@Table(name="t_grn_bonus_uom_mapper",schema="procurement")
public class GrnBonusUomMapper extends UomEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	//bi-directional many-to-one association to TGoodReceiptNoteDetail
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="grn_detail_id")
	private GoodReceiptNoteDetail goodReceiptNoteDetail;

	public GrnBonusUomMapper() {
	  super();
	}
		

	public GrnBonusUomMapper(Integer conversions, Boolean ipUom, Boolean opUom, Boolean storeUom, UomType uomType,
			UomUnit uomUnit) {
		super(conversions, ipUom, opUom, storeUom, uomType, uomUnit);
	}
	
	public void updateGrnBonusUomMapper(UomType uomType, UomUnit uomUnit, Integer conversions, Boolean ipUom, Boolean opUom,
			Boolean storeUom) {
		this.uomType = uomType;
		this.uomUnit = uomUnit;
		this.conversions = conversions;
		this.ipUom = ipUom;
		this.opUom = opUom;
		this.storeUom = storeUom;
	}

	public GoodReceiptNoteDetail getGoodReceiptNoteDetail() {
		return goodReceiptNoteDetail;
	}

	public void setGoodReceiptNoteDetail(GoodReceiptNoteDetail goodReceiptNoteDetail) {
		this.goodReceiptNoteDetail = goodReceiptNoteDetail;
	}

	

}