package com.param.entity.model.pharmacy;

import java.io.Serializable;
import java.sql.Timestamp;
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

import com.param.entity.model.adt.Admission;
import com.param.entity.model.base.BaseEntity;
import com.param.entity.model.doctor.Doctor;
import com.param.entity.model.master.DiscountCategory;
import com.param.entity.model.master.DiscountType;
import com.param.entity.model.master.Order;
import com.param.entity.model.master.Status;
import com.param.entity.model.master.Store;
import com.param.entity.model.master.User;
import com.param.entity.model.master.VisitType;
import com.param.entity.model.opd.Encounter;
import com.param.entity.model.patient.PatientRegistration;

@Entity(name = "Sale")
@Table(name = "t_sale", schema = "pharmacy")
public class Sale extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "admission_id")
	private Admission admission;

	@Column(name = "bill_no")
	private String billNo;

	@Column(name = "counter_id")
	private Integer counterId;

	private Double discount;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "discount_category_id")
	private DiscountCategory discountCategory;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "discount_type_id")
	private DiscountType discountType;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "doctor_id")
	private Doctor doctor;

	@Column(name = "net_amount")
	private Double netAmount;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "order_id")
	private Order order;

	@Column(name = "outstanding_amount")
	private Double outstandingAmount;

	@Column(name = "paid_amount")
	private Double paidAmount;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "patient_id")
	private PatientRegistration patient;

	@Column(name = "round_off")
	private Double roundOff;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "status_id")
	private Status status;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "store_id")
	private Store store;

	@Column(name = "tax_amount")
	private Double taxAmount;

	@Column(name = "total_amount")
	private Double totalAmount;

	@Column(name = "total_discount_amount")
	private Double totalDiscountAmount;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "visit_type_id")
	private VisitType visitType;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sale_type_id")
	private SaleType saleType;
	
	@Column(name = "received_by")
	private String receivedBy;
	
	@Column(name = "received_on")
	private Timestamp receivedOn;
	
	@Column(name = "is_handover")
	private boolean isHandover;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "handover_by")
	private User handoverBy;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "temp_doctor_id")
	private TempDoctor tempDoctor;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "temp_patient_id")
	private TempPatientRegistration tempPatient;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "sale", cascade = CascadeType.ALL)
	private List<SaleDetail> saleDetailsList;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="encounter_id")
	private Encounter encounter;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="carrier_id")
	private User carrier;
	
	public Sale() {
	}
	
	public Sale(Long id) {
		super();
		this.id = id;
	}
	
	public Sale(Admission admission, String billNo, Integer counterId, Double discount,
			DiscountCategory discountCategory, DiscountType discountType, Doctor doctor, Double netAmount, Order order,
			Double outstandingAmount, Double paidAmount, PatientRegistration patient, Double roundOff, Status status,
			Store store, Double taxAmount, Double totalAmount, Double totalDiscountAmount, VisitType visitType,
			SaleType saleType, TempDoctor tempDoctor, TempPatientRegistration tempPatient) {
		super();
		this.admission = admission;
		this.billNo = billNo;
		this.counterId = counterId;
		this.discount = discount;
		this.discountCategory = discountCategory;
		this.discountType = discountType;
		this.doctor = doctor;
		this.netAmount = netAmount;
		this.order = order;
		this.outstandingAmount = outstandingAmount;
		this.paidAmount = paidAmount;
		this.patient = patient;
		this.roundOff = roundOff;
		this.status = status;
		this.store = store;
		this.taxAmount = taxAmount;
		this.totalAmount = totalAmount;
		this.totalDiscountAmount = totalDiscountAmount;
		this.visitType = visitType;
		this.saleType = saleType;
		this.tempDoctor = tempDoctor;
		this.tempPatient = tempPatient;
	}

	public Sale(Long id, String receivedBy) {
		super();
		this.id = id;
		this.receivedBy = receivedBy;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Admission getAdmission() {
		return admission;
	}

	public void setAdmission(Admission admission) {
		this.admission = admission;
	}

	public String getBillNo() {
		return billNo;
	}

	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}

	public Integer getCounterId() {
		return counterId;
	}

	public void setCounterId(Integer counterId) {
		this.counterId = counterId;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public DiscountCategory getDiscountCategory() {
		return discountCategory;
	}

	public void setDiscountCategory(DiscountCategory discountCategory) {
		this.discountCategory = discountCategory;
	}

	public DiscountType getDiscountType() {
		return discountType;
	}

	public void setDiscountType(DiscountType discountType) {
		this.discountType = discountType;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public Double getNetAmount() {
		return netAmount;
	}

	public void setNetAmount(Double netAmount) {
		this.netAmount = netAmount;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Double getOutstandingAmount() {
		return outstandingAmount;
	}

	public void setOutstandingAmount(Double outstandingAmount) {
		this.outstandingAmount = outstandingAmount;
	}

	public Double getPaidAmount() {
		return paidAmount;
	}

	public void setPaidAmount(Double paidAmount) {
		this.paidAmount = paidAmount;
	}

	public PatientRegistration getPatient() {
		return patient;
	}

	public void setPatient(PatientRegistration patient) {
		this.patient = patient;
	}

	public Double getRoundOff() {
		return roundOff;
	}

	public void setRoundOff(Double roundOff) {
		this.roundOff = roundOff;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	public Double getTaxAmount() {
		return taxAmount;
	}

	public void setTaxAmount(Double taxAmount) {
		this.taxAmount = taxAmount;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Double getTotalDiscountAmount() {
		return totalDiscountAmount;
	}

	public void setTotalDiscountAmount(Double totalDiscountAmount) {
		this.totalDiscountAmount = totalDiscountAmount;
	}

	public VisitType getVisitType() {
		return visitType;
	}

	public void setVisitType(VisitType visitType) {
		this.visitType = visitType;
	}

	public SaleType getSaleType() {
		return saleType;
	}

	public void setSaleType(SaleType saleType) {
		this.saleType = saleType;
	}

	public TempDoctor getTempDoctor() {
		return tempDoctor;
	}

	public void setTempDoctor(TempDoctor tempDoctor) {
		this.tempDoctor = tempDoctor;
	}

	public TempPatientRegistration getTempPatient() {
		return tempPatient;
	}

	public void setTempPatient(TempPatientRegistration tempPatient) {
		this.tempPatient = tempPatient;
	}

	public List<SaleDetail> getSaleDetailsList() {
		return saleDetailsList;
	}

	public void setSaleDetailsList(List<SaleDetail> saleDetailsList) {
		this.saleDetailsList = saleDetailsList;
	}

	public SaleDetail addSaleDetailsList(SaleDetail saleDetail) {
		if(getSaleDetailsList() == null)
			this.saleDetailsList = new ArrayList<SaleDetail>();
		
		getSaleDetailsList().add(saleDetail);
		saleDetail.setSale(this);

		return saleDetail;
	}

	public SaleDetail removeSaleDetailsList(SaleDetail saleDetail) {
		getSaleDetailsList().remove(saleDetail);
		saleDetail.setSale(null);

		return saleDetail;
	}

	public Encounter getEncounter() {
		return encounter;
	}

	public void setEncounter(Encounter encounter) {
		this.encounter = encounter;
	}

	public String getReceivedBy() {
		return receivedBy;
	}

	public void setReceivedBy(String receivedBy) {
		this.receivedBy = receivedBy;
	}

	public Timestamp getReceivedOn() {
		return receivedOn;
	}

	public void setReceivedOn(Timestamp receivedOn) {
		this.receivedOn = receivedOn;
	}

	public boolean isHandover() {
		return isHandover;
	}

	public void setHandover(boolean isHandover) {
		this.isHandover = isHandover;
	}

	public User getHandoverBy() {
		return handoverBy;
	}

	public void setHandoverBy(User handoverBy) {
		this.handoverBy = handoverBy;
	}

	public User getCarrier() {
		return carrier;
	}

	public void setCarrier(User carrier) {
		this.carrier = carrier;
	}

	public Sale(String billNo, Order order,Double discount, DiscountCategory discountCategory, DiscountType discountType,
			Doctor doctor, Double netAmount, Double outstandingAmount, Double paidAmount, PatientRegistration patient,
			Double roundOff, Status status, Store store, Double taxAmount, Double totalAmount,
			Double totalDiscountAmount, VisitType visitType, SaleType saleType) {
		super();
		this.billNo = billNo;
		this.order = order;
		this.discount = discount;
		this.discountCategory = discountCategory;
		this.discountType = discountType;
		this.doctor = doctor;
		this.netAmount = netAmount;
		this.outstandingAmount = outstandingAmount;
		this.paidAmount = paidAmount;
		this.patient = patient;
		this.roundOff = roundOff;
		this.status = status;
		this.store = store;
		this.taxAmount = taxAmount;
		this.totalAmount = totalAmount;
		this.totalDiscountAmount = totalDiscountAmount;
		this.visitType = visitType;
		this.saleType = saleType;
	}
	
}