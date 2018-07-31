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
import com.param.entity.model.master.Status;

@Entity(name = "MaterialIssueDetail")
@Table(name = "t_material_issue_detail", schema = "inventory")
public class MaterialIssueDetail extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private Integer id;

	@Column(name = "issue_quantity")
	private Integer issueQuantity = 0;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "item_id")
	private Item item;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "store_indent_detail_id", nullable = true)
	private StoreIndentDetail storeIndentDetail;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "material_issue_id", nullable = true)
	private MaterialIssue materialIssue;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "material_picker_detail_id")
	private MaterialPickerDetail materialPickerDetail;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "materialIssueDetail", cascade = CascadeType.ALL)
	private List<MaterialIssueBatchMapper> materialIssueBatchMappers;

	public MaterialIssueDetail() {
		super();
	}

	public MaterialIssueDetail(Integer id) {
		super();
		this.id = id;
	}

	public MaterialIssueDetail(Item item, StoreIndentDetail storeIndentDetail,
			MaterialPickerDetail materialPickerDetail, Integer issueQuantity) {
		super();
		this.item = item;
		this.storeIndentDetail = storeIndentDetail;
		this.materialPickerDetail = materialPickerDetail;
		this.issueQuantity = issueQuantity;
	}
	
	public MaterialIssueDetail(Integer issueQuantity, MaterialPickerDetail materialPickerDetail, Status status) {
		super();
		this.issueQuantity = issueQuantity;
		this.materialPickerDetail = materialPickerDetail;
	}

	public MaterialIssueDetail(Integer issueQuantity, MaterialPickerDetail materialPickerDetail, Status status,
			Item item) {
		super();
		this.issueQuantity = issueQuantity;
		this.materialPickerDetail = materialPickerDetail;
		this.item = item;
	}

	public MaterialIssueDetail(Integer issueQuantity,Item item) {
		super();
		this.issueQuantity = issueQuantity;
		this.item = item;
	}

/*	public void saveMaterialIssueDetail(Integer acceptedQuantity, String rejectNote, Integer rejectQuantity,
			RejectReason rejectReason, Status status, Date updatedDate, User updatedUser) {
		this.acceptedQuantity = acceptedQuantity;
		this.rejectQuantity = rejectQuantity;
		this.rejectNote = rejectNote;
		this.rejectReason = rejectReason;
		this.acceptedBy = updatedUser;
		this.acceptedOn = updatedDate;
		this.updatedDate = updatedDate;
		this.updatedBy = updatedUser;
	}*/

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIssueQuantity() {
		return this.issueQuantity;
	}

	public void setIssueQuantity(Integer issueQuantity) {
		this.issueQuantity = issueQuantity;
	}

	public MaterialPickerDetail getMaterialPickerDetail() {
		return materialPickerDetail;
	}

	public void setMaterialPickerDetail(MaterialPickerDetail materialPickerDetail) {
		this.materialPickerDetail = materialPickerDetail;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public List<MaterialIssueBatchMapper> getMaterialIssueBatchMappers() {
		return materialIssueBatchMappers;
	}

	public void setMaterialIssueBatchMappers(List<MaterialIssueBatchMapper> materialIssueBatchMappers) {
		this.materialIssueBatchMappers = materialIssueBatchMappers;
	}

	public StoreIndentDetail getStoreIndentDetail() {
		return storeIndentDetail;
	}

	public void setStoreIndentDetail(StoreIndentDetail storeIndentDetail) {
		this.storeIndentDetail = storeIndentDetail;
	}

	public MaterialIssue getMaterialIssue() {
		return materialIssue;
	}

	public void setMaterialIssue(MaterialIssue materialIssue) {
		this.materialIssue = materialIssue;
	}

	public void addBatch(MaterialIssueBatchMapper batchMapper) {
		if (this.materialIssueBatchMappers == null)
			this.materialIssueBatchMappers = new ArrayList<MaterialIssueBatchMapper>();

		this.materialIssueBatchMappers.add(batchMapper);
		batchMapper.setMaterialIssueDetail(this);
	}

}
