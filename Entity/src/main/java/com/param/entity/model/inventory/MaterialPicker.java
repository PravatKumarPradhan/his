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
import com.param.entity.model.master.Status;

@Entity(name = "MaterialPicker")
@Table(name = "t_material_picker", schema = "inventory")
public class MaterialPicker extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private Integer id;

	private String remark;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "status_id", nullable = false)
	private Status status;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "store_indent_id", nullable = false)
	private StoreIndent storeIndent;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "materialPicker", cascade = CascadeType.ALL)
	private List<MaterialPickerDetail> materialPickerDetails;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "materialPicker", cascade = CascadeType.ALL)
	private List<MaterialIssue> materialIssue;

	public MaterialPicker() {
		super();
	}

	public MaterialPicker(Integer id) {
		super();
		this.id = id;
	}	

	public MaterialPicker(Status status, StoreIndent storeIndent) {
		super();
		this.status = status;
		this.storeIndent = storeIndent;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public StoreIndent getStoreIndent() {
		return storeIndent;
	}

	public void setStoreIndent(StoreIndent storeIndent) {
		this.storeIndent = storeIndent;
	}

	public List<MaterialPickerDetail> getMaterialPickerDetails() {
		return materialPickerDetails;
	}

	public void setMaterialPickerDetails(List<MaterialPickerDetail> materialPickerDetails) {
		this.materialPickerDetails = materialPickerDetails;
	}

	public MaterialPickerDetail addMaterialPickerDetail(MaterialPickerDetail materialPickerDetail) {
		if (getMaterialPickerDetails() == null)
			this.materialPickerDetails = new ArrayList<MaterialPickerDetail>();

		getMaterialPickerDetails().add(materialPickerDetail);
		materialPickerDetail.setMaterialPicker(this);

		return materialPickerDetail;
	}

	public MaterialPickerDetail removeMaterialPickerDetail(MaterialPickerDetail MaterialPickerDetail) {
		getMaterialPickerDetails().remove(MaterialPickerDetail);
		MaterialPickerDetail.setMaterialPicker(null);

		return MaterialPickerDetail;
	}

	public MaterialPickerDetail addMaterialPickerDetailList(MaterialPickerDetail materialPickerDetail) {
		if (getMaterialPickerDetails() == null)
			this.materialPickerDetails = new ArrayList<MaterialPickerDetail>();

		getMaterialPickerDetails().add(materialPickerDetail);
		materialPickerDetail.setMaterialPicker(this);

		return materialPickerDetail;
	}

}
