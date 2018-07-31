package com.param.entity.model.procurement;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.param.entity.model.base.UomEntity;
import com.param.entity.model.master.UomType;
import com.param.entity.model.master.UomUnit;

@Entity(name = "com.param.entity.model.procurement.grnuommapper")
@Table(name = "t_grn_uom_mapper", schema = "procurement")
public class GrnUomMapper extends UomEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "grn_detail_id")
	private GoodReceiptNoteDetail goodReceiptNoteDetail;

	public GrnUomMapper() {
		super();
	}

	public GrnUomMapper(Integer conversions, Boolean ipUom, Boolean opUom, Boolean storeUom, UomType uomType,
			UomUnit uomUnit) {
		super(conversions, ipUom, opUom, storeUom, uomType, uomUnit);
	}
	
	public void updateGrnUomMapper(UomType uomType, UomUnit uomUnit, Integer conversions, Boolean ipUom, Boolean opUom,
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