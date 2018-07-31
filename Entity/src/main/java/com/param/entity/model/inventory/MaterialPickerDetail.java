package com.param.entity.model.inventory;

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
import com.param.entity.model.master.Item;

@Entity(name = "MaterialPickerDetail")
@Table(name = "t_material_picker_detail", schema = "inventory")
public class MaterialPickerDetail extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private Integer id;
	
	@Column(name = "picker_quantity")
	private Integer pickerQuantity = 0;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "item_id")
	private Item item;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "material_picker_id")
	private MaterialPicker materialPicker;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "store_indent_detail_id")
	private StoreIndentDetail storeIndentDetail;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "materialPickerDetail", cascade = CascadeType.ALL)
	private List<MaterialPickerBatchMapper> batchList;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "materialPickerDetail", cascade = CascadeType.PERSIST)
	private List<MaterialIssueDetail> materialIssueDetail;
	
	public MaterialPickerDetail() {
		super();
	}

	public MaterialPickerDetail(Integer id) {
		super();
		this.id = id;
	}

	public MaterialPickerDetail(StoreIndentDetail storeIndentDetail, Integer pickerQuantity) {
		super();
		this.storeIndentDetail = storeIndentDetail;
		this.item = storeIndentDetail.getItem();
		this.pickerQuantity = pickerQuantity;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPickerQuantity() {
		return pickerQuantity;
	}

	public void setPickerQuantity(Integer pickerQuantity) {
		this.pickerQuantity = pickerQuantity;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public MaterialPicker getMaterialPicker() {
		return materialPicker;
	}

	public void setMaterialPicker(MaterialPicker materialPicker) {
		this.materialPicker = materialPicker;
	}

	public StoreIndentDetail getStoreIndentDetail() {
		return storeIndentDetail;
	}

	public void setStoreIndentDetail(StoreIndentDetail storeIndentDetail) {
		this.storeIndentDetail = storeIndentDetail;
	}

	public List<MaterialPickerBatchMapper> getBatchList() {
		return batchList;
	}

	public void setBatchList(List<MaterialPickerBatchMapper> batchList) {
		this.batchList = batchList;
	}

	public List<MaterialIssueDetail> getMaterialIssueDetail() {
		return materialIssueDetail;
	}

	public void setMaterialIssueDetail(List<MaterialIssueDetail> materialIssueDetail) {
		this.materialIssueDetail = materialIssueDetail;
	}

	public void addBatch(MaterialPickerBatchMapper batchMapper) {
		if (this.batchList == null)
			this.batchList = new ArrayList<MaterialPickerBatchMapper>();
		
		this.batchList.add(batchMapper);
		batchMapper.setMaterialPickerDetail(this);

	}
	
}
