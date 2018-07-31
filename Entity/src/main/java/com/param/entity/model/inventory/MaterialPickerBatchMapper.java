package com.param.entity.model.inventory;

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
import com.param.entity.model.master.UomType;
import com.param.entity.model.master.UomUnit;

@Entity(name = "MaterialPickerBatchMapper")
@Table(name = "t_material_picker_batch_mapper", schema = "inventory")
public class MaterialPickerBatchMapper extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "batch_id", nullable = false)
	private Batch batch;

	@Column(name = "picker_quantity")
	private Integer pickerQuantity;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "uom_type_id", nullable = false)
	private UomType uomType;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "uom_unit_id", nullable = false)
	private UomUnit uomUnit;	

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "material_picker_detail_id")
	private MaterialPickerDetail materialPickerDetail;

	public MaterialPickerBatchMapper() {
	}

	public MaterialPickerBatchMapper(Batch batch, Integer pickerQuantity, UomType uomType, UomUnit uomUnit) {
		super();
		this.batch = batch;
		this.pickerQuantity = pickerQuantity;
		this.uomType = uomType;
		this.uomUnit = uomUnit;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Batch getBatch() {
		return batch;
	}

	public void setBatch(Batch batch) {
		this.batch = batch;
	}

	public Integer getPickerQuantity() {
		return pickerQuantity;
	}

	public void setPickerQuantity(Integer pickerQuantity) {
		this.pickerQuantity = pickerQuantity;
	}

	public UomType getUomType() {
		return uomType;
	}

	public void setUomType(UomType uomType) {
		this.uomType = uomType;
	}

	public UomUnit getUomUnit() {
		return uomUnit;
	}

	public void setUomUnit(UomUnit uomUnit) {
		this.uomUnit = uomUnit;
	}

	public MaterialPickerDetail getMaterialPickerDetail() {
		return materialPickerDetail;
	}

	public void setMaterialPickerDetail(MaterialPickerDetail materialPickerDetail) {
		this.materialPickerDetail = materialPickerDetail;
	}

}
