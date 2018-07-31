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
import com.param.entity.model.master.Store;
import com.param.entity.model.master.User;

@Entity(name = "MaterialIssue")
@Table(name = "t_material_issue", schema = "inventory")
public class MaterialIssue extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "picker_id")
	private User picker;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "carrier_id")
	private User carrierId;

	private String remark;

	@Column(name = "is_consignment", nullable = false)
	private Boolean isConsignment = false;

	@Column(name = "issue_no", length = 50)
	private String issueNo;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "status_id", nullable = false)
	private Status status;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "store_indent_id", nullable = true)
	private StoreIndent storeIndent;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "to_store_id")
	private Store toStore;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "from_store_id")
	private Store fromStore;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "material_picker_id")
	private MaterialPicker materialPicker;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "materialIssue", cascade = CascadeType.ALL)
	private List<MaterialIssueDetail> materialIssueDetails;

	public MaterialIssue() {
		super();
	}

	public MaterialIssue(Integer id) {
		super();
		this.id = id;
	}

	public MaterialIssue(User picker, String issueNo, Status status, StoreIndent storeIndent,
			MaterialPicker materialPicker, Store toStore, Store fromStore) {
		super();
		this.picker = picker;
		this.issueNo = issueNo;
		this.status = status;
		this.storeIndent = storeIndent;
		this.materialPicker = materialPicker;
		this.toStore = toStore;
		this.fromStore = fromStore;
	}

	public MaterialIssue(Status approvalStatus, User carrierId, MaterialPicker materialPicker, Status status) {
		super();
		this.carrierId = carrierId;
		this.materialPicker = materialPicker;
		this.status = status;
	}

	public MaterialIssue(Status approvalStatus, User carrierId, String issueNo, Status status, Store toStore,
			Store fromStore, MaterialPicker materialPicker) {
		super();
		this.carrierId = carrierId;
		this.issueNo = issueNo;
		this.status = status;
		this.toStore = toStore;
		this.fromStore = fromStore;
		this.materialPicker = materialPicker;
	}

	public MaterialIssue(User carrierId, String remark, Status status, Store toStore, Store fromStore, String issueNo,
			Boolean isConsignment) {
		super();
		this.carrierId = carrierId;
		this.remark = remark;
		this.status = status;
		this.toStore = toStore;
		this.fromStore = fromStore;
		this.issueNo = issueNo;
		this.isConsignment = isConsignment;
	}

	public void updateMaterialIssue(User carrierId, String remark, Status status, Store fromStore, Store toStore) {
		this.carrierId = carrierId;
		this.remark = remark;
		this.status = status;
		this.fromStore = fromStore;
		this.toStore = toStore;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getPicker() {
		return picker;
	}

	public void setPicker(User picker) {
		this.picker = picker;
	}

	public User getCarrierId() {
		return carrierId;
	}

	public void setCarrierId(User carrierId) {
		this.carrierId = carrierId;
	}

	public MaterialPicker getMaterialPicker() {
		return materialPicker;
	}

	public void setMaterialPicker(MaterialPicker materialPicker) {
		this.materialPicker = materialPicker;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getIssueNo() {
		return issueNo;
	}

	public void setIssueNo(String issueNo) {
		this.issueNo = issueNo;
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

	public Store getToStore() {
		return toStore;
	}

	public void setToStore(Store toStore) {
		this.toStore = toStore;
	}

	public Store getFromStore() {
		return fromStore;
	}

	public void setFromStore(Store fromStore) {
		this.fromStore = fromStore;
	}

	public List<MaterialIssueDetail> getMaterialIssueDetails() {
		return this.materialIssueDetails;
	}

	public void setMaterialIssueDetails(List<MaterialIssueDetail> materialIssueDetails) {
		this.materialIssueDetails = materialIssueDetails;
	}

	public Boolean getIsConsignment() {
		return isConsignment;
	}

	public void setIsConsignment(Boolean isConsignment) {
		this.isConsignment = isConsignment;
	}

	public MaterialIssueDetail addMaterialIssueDetail(MaterialIssueDetail MaterialIssueDetail) {
		if (getMaterialIssueDetails() == null)
			this.materialIssueDetails = new ArrayList<MaterialIssueDetail>();

		getMaterialIssueDetails().add(MaterialIssueDetail);
		MaterialIssueDetail.setMaterialIssue(this);

		return MaterialIssueDetail;
	}

	public MaterialIssueDetail removeTMaterialIssueDetail(MaterialIssueDetail MaterialIssueDetail) {
		getMaterialIssueDetails().remove(MaterialIssueDetail);
		MaterialIssueDetail.setMaterialIssue(null);

		return MaterialIssueDetail;
	}

	public MaterialIssueDetail adMaterialIssueDetail(MaterialIssueDetail materialIssueDetail) {
		if (getMaterialIssueDetails() == null)
			this.materialIssueDetails = new ArrayList<MaterialIssueDetail>();

		getMaterialIssueDetails().add(materialIssueDetail);
		materialIssueDetail.setMaterialIssue(this);

		return materialIssueDetail;
	}

}
