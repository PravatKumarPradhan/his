package com.param.entity.model.inventory;

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

import com.param.entity.model.base.BaseEntity;
import com.param.entity.model.master.Item;
import com.param.entity.model.master.RejectReason;
import com.param.entity.model.master.Status;
import com.param.entity.model.master.UomType;
import com.param.entity.model.master.UomUnit;
import com.param.entity.model.master.User;
import com.param.entity.model.procurement.PurchaseRequestDetail;

@Entity(name = "StoreIndentDetail")
@Table(name = "t_store_indent_detail", schema = "inventory")
public class StoreIndentDetail extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private Integer id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "authorized_by")
	private User authorizedBy;

	@Column(name = "authorized_date")
	private Date authorizedDate;

	@Column(name = "authorized_quantity")
	private Integer authorizedQuantity = 0;

	@Column(name = "indent_quantity")
	private Integer indentQuantity;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "item_id")
	private Item item;

	@Column(name = "pending_quantity")
	private Integer pendingQuantity;

	@Column(name = "in_transit_quantity")
	private Integer inTransitQuantity = 0;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "purchase_request_detail_id")
	private PurchaseRequestDetail purchaseRequestDetail;

	@Column(name = "reject_note", length = 100)
	private String rejectNote;

	@Column(name = "reject_quantity")
	private Integer rejectQuantity = 0;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "reject_reason_id")
	private RejectReason rejectReason;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "status_id")
	private Status status;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "uom_type_id")
	private UomType uomType;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "uom_unit_id")
	private UomUnit uomUnit;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "store_indent_id")
	private StoreIndent storeIndent;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "storeIndentDetail", cascade = CascadeType.PERSIST)
	private List<MaterialPickerDetail> materialPickerDetails;

	/*@OneToMany(fetch = FetchType.LAZY, mappedBy = "storeIndentDetail", cascade = CascadeType.PERSIST)
	private List<MaterialIssueDetail> materialIssueDetail;*/

	public StoreIndentDetail() {
	}

	public StoreIndentDetail(Integer id) {
		this.id = id;
	}

	public StoreIndentDetail(Status status) {
		super();
		this.status = status;
	}

	public StoreIndentDetail(Integer indentQuantity, Item item, Integer pendingQuantity, Status status, UomType uomType,
			UomUnit uomUnit) {

		super();
		this.indentQuantity = indentQuantity;
		this.item = item;
		this.pendingQuantity = pendingQuantity;
		this.status = status;
		this.uomType = uomType;
		this.uomUnit = uomUnit;
	}

	public void updateStoreIndentDetail(Item item, Integer indentQuantity) {
		this.item = item;
		this.indentQuantity = indentQuantity;
	}

	public void approveStoreIndentDetail(Integer authorizedQuantity, String rejectNote, Integer rejectQuantity,
			RejectReason rejectReason, Status status, Date updatedDate, User updatedUser) {
		this.authorizedQuantity = authorizedQuantity;
		this.pendingQuantity = authorizedQuantity;
		this.rejectQuantity = rejectQuantity;
		this.rejectNote = rejectNote;
		this.rejectReason = rejectReason;
		this.status = status;
		this.authorizedBy = updatedUser;
		this.authorizedDate = updatedDate;
		this.updatedDate = updatedDate;
		this.updatedBy = updatedUser;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getAuthorizedBy() {
		return authorizedBy;
	}

	public void setAuthorizedBy(User authorizedBy) {
		this.authorizedBy = authorizedBy;
	}

	public Date getAuthorizedDate() {
		return authorizedDate;
	}

	public void setAuthorizedDate(Date authorizedDate) {
		this.authorizedDate = authorizedDate;
	}

	public Integer getAuthorizedQuantity() {
		return authorizedQuantity;
	}

	public void setAuthorizedQuantity(Integer authorizedQuantity) {
		this.authorizedQuantity = authorizedQuantity;
	}

	public Integer getIndentQuantity() {
		return indentQuantity;
	}

	public void setIndentQuantity(Integer indentQuantity) {
		this.indentQuantity = indentQuantity;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public Integer getPendingQuantity() {
		return pendingQuantity;
	}

	public void setPendingQuantity(Integer pendingQuantity) {
		this.pendingQuantity = pendingQuantity;
	}

	public PurchaseRequestDetail getPurchaseRequestDetail() {
		return purchaseRequestDetail;
	}

	public void setPurchaseRequestDetail(PurchaseRequestDetail purchaseRequestDetail) {
		this.purchaseRequestDetail = purchaseRequestDetail;
	}

	public String getRejectNote() {
		return rejectNote;
	}

	public void setRejectNote(String rejectNote) {
		this.rejectNote = rejectNote;
	}

	public Integer getRejectQuantity() {
		return rejectQuantity;
	}

	public void setRejectQuantity(Integer rejectQuantity) {
		this.rejectQuantity = rejectQuantity;
	}

	public RejectReason getRejectReason() {
		return rejectReason;
	}

	public void setRejectReason(RejectReason rejectReason) {
		this.rejectReason = rejectReason;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
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

	public StoreIndent getStoreIndent() {
		return storeIndent;
	}

	public void setStoreIndent(StoreIndent storeIndent) {
		this.storeIndent = storeIndent;
	}

	public Integer getInTransitQuantity() {
		return inTransitQuantity;
	}

	public void setInTransitQuantity(Integer inTransitQuantity) {
		this.inTransitQuantity = inTransitQuantity;
	}

	public List<MaterialPickerDetail> getMaterialPickerDetails() {
		return materialPickerDetails;
	}

	public void setMaterialPickerDetails(List<MaterialPickerDetail> materialPickerDetails) {
		this.materialPickerDetails = materialPickerDetails;
	}

}