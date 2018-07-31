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

@Entity(name = "BatchUomMapper")
@Table(name = "t_batch_uom_mapper", schema = "inventory")
public class BatchUomMapper extends UomEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "batch_id", nullable = false)
	private Batch batch;

	public BatchUomMapper() {
		super();
	}

	public BatchUomMapper(Integer conversions, Boolean ipUom, Boolean opUom, Boolean storeUom, UomType uomType,
			UomUnit uomUnit) {
		super(conversions, ipUom, opUom, storeUom, uomType, uomUnit);
	}

	public Batch getBatch() {
		return this.batch;
	}

	public void setBatch(Batch batch) {
		this.batch = batch;
	}
}