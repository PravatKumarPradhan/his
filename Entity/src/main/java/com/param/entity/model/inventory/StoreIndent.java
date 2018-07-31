package com.param.entity.model.inventory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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

import com.param.entity.model.adt.Priority;
import com.param.entity.model.base.BaseEntity;
import com.param.entity.model.master.CancelReason;
import com.param.entity.model.master.Status;
import com.param.entity.model.master.Store;
import com.param.entity.model.master.User;

@Entity(name = "StoreIndent")
@Table(name = "t_store_indent", schema = "inventory")
public class StoreIndent extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "approval_status_id", nullable = false)
	private Status approvalStatus;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "approved_by")
	protected User approvedBy;

	@Column(name = "approved_date")
	private Date approvedDate;

	@Column(name = "cancel_note", length = 100)
	private String cancelNote;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cancel_reason_id")
	private CancelReason cancelReason;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "from_store_id", nullable = false)
	private Store fromStore;

	@Column(name = "indent_no", nullable = false, length = 50)
	private String indentNo;

	@Column(name = "is_consignment", nullable = false)
	private Boolean isConsignment;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "priority_id", nullable = false)
	private Priority priority;

	@Column(length = 500)
	private String remark;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "status_id", nullable = false)
	private Status status;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "to_store_id", nullable = false)
	private Store toStore;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "indent_type_id", nullable = false)
	private IndentType indentType;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "storeIndent", cascade = CascadeType.ALL)
	private List<StoreIndentDetail> storeIndentDetailList;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "storeIndent", cascade = CascadeType.ALL)
	private List<MaterialPicker> matrialPicker;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "storeIndent", cascade = CascadeType.ALL)
	private List<MaterialIssue> materialIssue;

	public StoreIndent() {
	}

	public StoreIndent(Integer id) {
		super();
		this.id = id;
	}

	public StoreIndent(Status approvalStatus, Store fromStore, String indentNo, Boolean isConsignment,
			Priority priority, String remark, Status status, Store toStore, IndentType indentType) {
		super();
		this.approvalStatus = approvalStatus;
		this.fromStore = fromStore;
		this.indentNo = indentNo;
		this.isConsignment = isConsignment;
		this.priority = priority;
		this.remark = remark;
		this.status = status;
		this.toStore = toStore;
		this.indentType = indentType;
	}

	public void updateStoreIndent(Priority priority, Boolean isConsignment, String remark) {
		this.priority = priority;
		this.isConsignment = isConsignment;
		this.remark = remark;
	}

	public StoreIndent(Status status) {
		super();
		this.status = status;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Status getApprovalStatus() {
		return approvalStatus;
	}

	public void setApprovalStatus(Status approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	public User getApprovedBy() {
		return approvedBy;
	}

	public void setApprovedBy(User approvedBy) {
		this.approvedBy = approvedBy;
	}

	public Date getApprovedDate() {
		return approvedDate;
	}

	public void setApprovedDate(Date approvedDate) {
		this.approvedDate = approvedDate;
	}

	public String getCancelNote() {
		return cancelNote;
	}

	public void setCancelNote(String cancelNote) {
		this.cancelNote = cancelNote;
	}

	public CancelReason getCancelReason() {
		return cancelReason;
	}

	public void setCancelReason(CancelReason cancelReason) {
		this.cancelReason = cancelReason;
	}

	public Store getFromStore() {
		return fromStore;
	}

	public void setFromStore(Store fromStore) {
		this.fromStore = fromStore;
	}

	public String getIndentNo() {
		return indentNo;
	}

	public void setIndentNo(String indentNo) {
		this.indentNo = indentNo;
	}

	public Boolean getIsConsignment() {
		return isConsignment;
	}

	public void setIsConsignment(Boolean isConsignment) {
		this.isConsignment = isConsignment;
	}

	public Priority getPriority() {
		return priority;
	}

	public void setPriority(Priority priority) {
		this.priority = priority;
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

	public Store getToStore() {
		return toStore;
	}

	public void setToStore(Store toStore) {
		this.toStore = toStore;
	}

	public IndentType getIndentType() {
		return indentType;
	}

	public void setIndentType(IndentType indentType) {
		this.indentType = indentType;
	}

	public List<StoreIndentDetail> getStoreIndentDetailList() {
		return storeIndentDetailList;
	}

	public void setStoreIndentDetailList(List<StoreIndentDetail> storeIndentDetailList) {
		this.storeIndentDetailList = storeIndentDetailList;
	}

	public List<MaterialPicker> getMatrialPicker() {
		return matrialPicker;
	}

	public void setMatrialPicker(List<MaterialPicker> matrialPicker) {
		this.matrialPicker = matrialPicker;
	}

	public List<MaterialIssue> getMaterialIssue() {
		return materialIssue;
	}

	public void setMaterialIssue(List<MaterialIssue> materialIssue) {
		this.materialIssue = materialIssue;
	}

	public StoreIndentDetail addStoreIndentDetailList(StoreIndentDetail storeIndentDetail) {
		if (getStoreIndentDetailList() == null)
			this.storeIndentDetailList = new ArrayList<StoreIndentDetail>();

		getStoreIndentDetailList().add(storeIndentDetail);
		storeIndentDetail.setStoreIndent(this);

		return storeIndentDetail;
	}

	public StoreIndentDetail removeStoreIndentDetailList(StoreIndentDetail storeIndentDetail) {
		getStoreIndentDetailList().remove(storeIndentDetail);
		storeIndentDetail.setStoreIndent(null);

		return storeIndentDetail;
	}
}