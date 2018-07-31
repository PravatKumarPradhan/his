package com.param.entity.model.inventory;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.param.entity.model.base.UomEntity;
import com.param.entity.model.master.UomType;
import com.param.entity.model.master.UomUnit;

@Entity(name = "OpbUomMapper")
@Table(name = "t_opb_uom_mapper", schema = "inventory")
public class OpbUomMapper extends UomEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "opb_detail_id", nullable = false)
	private OpeningBalanceDetail openingBalanceDetail;

	public OpbUomMapper() {
		super();
	}

	public OpbUomMapper(Integer conversions, Boolean ipUom, Boolean opUom, Boolean storeUom, UomType uomType,
			UomUnit uomUnit) {
		super(conversions, ipUom, opUom, storeUom, uomType, uomUnit);
	}

	public OpeningBalanceDetail getOpeningBalanceDetail() {
		return this.openingBalanceDetail;
	}

	public void setOpeningBalanceDetail(OpeningBalanceDetail openingBalanceDetail) {
		this.openingBalanceDetail = openingBalanceDetail;
	}
}