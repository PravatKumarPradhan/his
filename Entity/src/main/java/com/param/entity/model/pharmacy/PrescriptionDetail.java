package com.param.entity.model.pharmacy;

import java.io.Serializable;
import java.sql.Timestamp;
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
import com.param.entity.model.master.Store;

@Entity(name="PrescriptionDetail")
@Table(name="t_prescription_detail",schema="pharmacy")
public class PrescriptionDetail extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false )
	private Long id;

	@Column(name="authorised_by")
	private Integer authorisedBy;

	@Column(name="authorised_date")
	private Timestamp authorisedDate;

	@Column(name="discontinue_by")
	private Integer discontinueBy;

	@Column(name="discontinue_on")
	private Timestamp discontinueOn;

	@Column(name="is_discontinued" )
	private Boolean isDiscontinued;

	@Column(name="is_intervention")
	private Boolean isIntervention;

	@Column(name="is_pre_stop")
	private Boolean isPreStop;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "item_id")
	private Item item;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "status_id")
	private Status status;

	@Column(name="order_qty" )
	private Integer orderQty;

	@Column(name="pending_qty" )
	private Integer pendingQty;

	@Column(name="prescription_date")
	private Timestamp prescriptionDate;

	@Column(name="refill_qty")
	private Integer refillQty;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name ="store_id" )
	private Store store;
	
	@Column(name="strength" )
	private Double strength;

	@Column(name="uom_unit_id" )
	private Integer uomUnitId;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="prescription_id" )
	private Prescription prescription;
	
	@Column(name="remark")
	private String remark;

	@OneToMany(fetch = FetchType.LAZY, mappedBy="prescriptionDetail",cascade = CascadeType.ALL)
	private List<PrescriptionFrequencyDetail> PrescriptionFrequencyDetails;

	public PrescriptionDetail() {
	}

	public PrescriptionDetail(Long id) {
		super();
		this.id = id;
	}
	
	public PrescriptionDetail(Long id, Integer authorisedBy, Timestamp authorisedDate, Integer discontinueBy,
			Timestamp discontinueOn, Boolean isDiscontinued, Boolean isIntervention, Boolean isPreStop, Item item,
			Integer orderQty, Integer pendingQty, Timestamp prescriptionDate, Integer refillQty, Store store,
			Double strength, Integer uomUnitId, Prescription prescription,
			List<PrescriptionFrequencyDetail> prescriptionFrequencyDetails) {
		super();
		this.id = id;
		this.authorisedBy = authorisedBy;
		this.authorisedDate = authorisedDate;
		this.discontinueBy = discontinueBy;
		this.discontinueOn = discontinueOn;
		this.isDiscontinued = isDiscontinued;
		this.isIntervention = isIntervention;
		this.isPreStop = isPreStop;
		this.item = item;
		this.orderQty = orderQty;
		this.pendingQty = pendingQty;
		this.prescriptionDate = prescriptionDate;
		this.refillQty = refillQty;
		this.store = store;
		this.strength = strength;
		this.uomUnitId = uomUnitId;
		this.prescription = prescription;
		PrescriptionFrequencyDetails = prescriptionFrequencyDetails;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getAuthorisedBy() {
		return this.authorisedBy;
	}

	public void setAuthorisedBy(Integer authorisedBy) {
		this.authorisedBy = authorisedBy;
	}

	public Timestamp getAuthorisedDate() {
		return this.authorisedDate;
	}

	public void setAuthorisedDate(Timestamp authorisedDate) {
		this.authorisedDate = authorisedDate;
	}

	public Integer getDiscontinueBy() {
		return this.discontinueBy;
	}

	public void setDiscontinueBy(Integer discontinueBy) {
		this.discontinueBy = discontinueBy;
	}

	public Timestamp getDiscontinueOn() {
		return this.discontinueOn;
	}

	public void setDiscontinueOn(Timestamp discontinueOn) {
		this.discontinueOn = discontinueOn;
	}

	public Boolean getIsDiscontinued() {
		return this.isDiscontinued;
	}

	public void setIsDiscontinued(Boolean isDiscontinued) {
		this.isDiscontinued = isDiscontinued;
	}

	public Boolean getIsIntervention() {
		return this.isIntervention;
	}

	public void setIsIntervention(Boolean isIntervention) {
		this.isIntervention = isIntervention;
	}

	public Boolean getIsPreStop() {
		return this.isPreStop;
	}

	public void setIsPreStop(Boolean isPreStop) {
		this.isPreStop = isPreStop;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public Integer getOrderQty() {
		return this.orderQty;
	}

	public void setOrderQty(Integer orderQty) {
		this.orderQty = orderQty;
	}

	public Integer getPendingQty() {
		return this.pendingQty;
	}

	public void setPendingQty(Integer pendingQty) {
		this.pendingQty = pendingQty;
	}

	public Timestamp getPrescriptionDate() {
		return this.prescriptionDate;
	}

	public void setPrescriptionDate(Timestamp prescriptionDate) {
		this.prescriptionDate = prescriptionDate;
	}

	public Integer getRefillQty() {
		return this.refillQty;
	}

	public void setRefillQty(Integer refillQty) {
		this.refillQty = refillQty;
	}

	public Store getStoreId() {
		return this.store;
	}

	public void setStoreId(Store store) {
		this.store = store;
	}

	public Double getStrength() {
		return this.strength;
	}

	public void setStrength(Double strength) {
		this.strength = strength;
	}

	public Integer getUomUnitId() {
		return this.uomUnitId;
	}

	public void setUomUnitId(Integer uomUnitId) {
		this.uomUnitId = uomUnitId;
	}

	public Prescription getPrescription() {
		return this.prescription;
	}

	public void setPrescription(Prescription prescription) {
		this.prescription = prescription;
	}
	

	public List<PrescriptionFrequencyDetail> getPrescriptionFrequencyDetails() {
		return this.PrescriptionFrequencyDetails;
	}

	public void setPrescriptionFrequencyDetails(List<PrescriptionFrequencyDetail> PrescriptionFrequencyDetails) {
		this.PrescriptionFrequencyDetails = PrescriptionFrequencyDetails;
	}

	public PrescriptionFrequencyDetail addPrescriptionFrequencyDetail(PrescriptionFrequencyDetail PrescriptionFrequencyDetail) {
		getPrescriptionFrequencyDetails().add(PrescriptionFrequencyDetail);
		PrescriptionFrequencyDetail.setTPrescriptionDetail(this);

		return PrescriptionFrequencyDetail;
	}

	public PrescriptionFrequencyDetail removePrescriptionFrequencyDetail(PrescriptionFrequencyDetail PrescriptionFrequencyDetail) {
		getPrescriptionFrequencyDetails().remove(PrescriptionFrequencyDetail);
		PrescriptionFrequencyDetail.setTPrescriptionDetail(null);

		return PrescriptionFrequencyDetail;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}