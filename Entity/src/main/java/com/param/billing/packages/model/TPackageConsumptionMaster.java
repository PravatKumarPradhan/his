package com.param.billing.packages.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.param.billing.global.model.PackageTypeMaster;
import com.param.billing.global.transaction.model.TariffMaster;
import com.param.entity.model.adt.Admission;
import com.param.entity.model.master.OrderDetail;
import com.param.entity.model.opd.Encounter;
import com.param.entity.model.patient.PatientRegistration;
import com.param.global.model.CompanyMaster;
import com.param.service.global.model.MPackageMaster;

@Entity
@Table(name = "t_package_consumption_master" , schema = "billing")
@SequenceGenerator(name = "package_consumption_master_seq" , sequenceName = "billing.package_consumption_master_seq" , allocationSize = 1)
public class TPackageConsumptionMaster {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "package_consumption_master_seq")
	@Column(name = "package_consumption_master_id")
	private int packageConsumptionMasterId;
	
	@Column(name = "organisation_id")
	private Integer organisationId;
	
	@Column(name = "unit_id")
	private Integer unitId;
	
	@Column(name = "package_id")
	private Integer packageId;
	
	@Column(name = "patient_id")
	private Integer patientId;
	
	@Column(name = "visit_type")
	private Integer visitType;
	
	@Column(name = "encounter_id")
	private Integer encounterId;
	
	@Column(name = "admission_id")
	private Integer admissionId;
	
	@Column(name = "order_detail_id")
	private Integer orderDetailId;
	
	@Column(name = "package_type_id")
	private Integer packageTypeId;
	
	@Column(name = "rate")
	private Double rate;
	
	@Column(name = "validity_from")
	private Date validityFrom;
	
	@Column(name = "validity_to")
	private Date validityTo;
	
	@Column(name = "applicable_no_of_visit")
	private Integer applicableNoOfVisit;
	
	@Column(name = "tariff_id")
	private Integer tariffId;
	
	@Column(name = "total_deposite")
	private Double totalDeposite;
	
	@Column(name = "balance_deposite")
	private Double balanceDeposite;
	
	@Column(name = "payee_id")
	private Integer payeeId;
	
	@Column(name = "co_share_in_percentage")
	private Double coShareInPercentage;
	
	@Column(name = "status")
	private char status;
	
	@Column(name = "is_cancelled")
	private char isCancelled;
	
	@Column(name = "is_discountinued")
	private char isDiscountinued;
	
	@Column(name = "cancelled_date")
	private Date cancelledDate;
	
	@Column(name = "cancelled_by")
	private Integer cancelledBy;
	
	@Column(name = "cancelled_reason_id")
	private Integer cancelledReasonId;
	
	@Column(name = "cancelled_remark")
	private String cancelledRemark;
	
	@Column(name = "discountined_by")
	private Integer discountinedBy;
	
	@Column(name = "discountined_date")
	private Date discountinedDate;
	
	@Column(name = "discountinued_reason_id")
	private Integer discountinuedReasonId;
	
	@Column(name = "discountinued_remark")
	private String discountinuedRemark;
	
	@Column(name = "created_date")
	private Date createdDate;
	
	@Column(name = "created_by")
	private Integer createdBy;
	
	@Column(name = "updated_date")
	private Date updatedDate;
	
	@Column(name = "updated_by")
	private Integer updatedBy;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "admission_id" , insertable = false , updatable = false , nullable = false)
	private Admission admission;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "encounter_id" , insertable = false , updatable = false , nullable = false)
	private Encounter encounter;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "order_detail_id" , insertable = false , updatable = false , nullable = false)
	private OrderDetail orderDetail;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "package_id" , insertable = false , updatable = false , nullable = false)
	private MPackageMaster mPackageMaster;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "package_type_id" , insertable = false , updatable = false , nullable = false)
	private PackageTypeMaster packageTypeMaster;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "patient_id" , insertable = false , updatable = false , nullable = false)
	private PatientRegistration patientRegistration;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "payee_id" , insertable = false , updatable = false , nullable = false)
	private CompanyMaster companyMaster;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tariff_id" , insertable = false , updatable = false , nullable = false)
	private TariffMaster tariffMaster;
	
	/*@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "visit_type" , insertable = false , updatable = false , nullable = false)
	private VisitType visitTypeMaster;*/
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tPackageConsumptionMaster")
	private List<TPackageConsumptionEoDetails> listTPackageConsumptionEoDetails;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tPackageConsumptionMaster")
	private List<TPackageConsumptionCapDetails> listTPackageConsumptionCapDetails;

	public int getPackageConsumptionMasterId() {
		return packageConsumptionMasterId;
	}

	public void setPackageConsumptionMasterId(int packageConsumptionMasterId) {
		this.packageConsumptionMasterId = packageConsumptionMasterId;
	}

	public Integer getOrganisationId() {
		return organisationId;
	}

	public void setOrganisationId(Integer organisationId) {
		this.organisationId = organisationId;
	}

	public Integer getUnitId() {
		return unitId;
	}

	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}

	public Integer getPackageId() {
		return packageId;
	}

	public void setPackageId(Integer packageId) {
		this.packageId = packageId;
	}

	public Integer getPatientId() {
		return patientId;
	}

	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}

	public Integer getVisitType() {
		return visitType;
	}

	public void setVisitType(Integer visitType) {
		this.visitType = visitType;
	}

	public Integer getEncounterId() {
		return encounterId;
	}

	public void setEncounterId(Integer encounterId) {
		this.encounterId = encounterId;
	}

	public Integer getAdmissionId() {
		return admissionId;
	}

	public void setAdmissionId(Integer admissionId) {
		this.admissionId = admissionId;
	}

	public Integer getOrderDetailId() {
		return orderDetailId;
	}

	public void setOrderDetailId(Integer orderDetailId) {
		this.orderDetailId = orderDetailId;
	}

	public Integer getPackageTypeId() {
		return packageTypeId;
	}

	public void setPackageTypeId(Integer packageTypeId) {
		this.packageTypeId = packageTypeId;
	}

	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

	public Date getValidityFrom() {
		return validityFrom;
	}

	public void setValidityFrom(Date validityFrom) {
		this.validityFrom = validityFrom;
	}

	public Date getValidityTo() {
		return validityTo;
	}

	public void setValidityTo(Date validityTo) {
		this.validityTo = validityTo;
	}

	public Integer getApplicableNoOfVisit() {
		return applicableNoOfVisit;
	}

	public void setApplicableNoOfVisit(Integer applicableNoOfVisit) {
		this.applicableNoOfVisit = applicableNoOfVisit;
	}

	public Integer getTariffId() {
		return tariffId;
	}

	public void setTariffId(Integer tariffId) {
		this.tariffId = tariffId;
	}

	public Double getTotalDeposite() {
		return totalDeposite;
	}

	public void setTotalDeposite(Double totalDeposite) {
		this.totalDeposite = totalDeposite;
	}

	public Double getBalanceDeposite() {
		return balanceDeposite;
	}

	public void setBalanceDeposite(Double balanceDeposite) {
		this.balanceDeposite = balanceDeposite;
	}

	public Integer getPayeeId() {
		return payeeId;
	}

	public void setPayeeId(Integer payeeId) {
		this.payeeId = payeeId;
	}

	public Double getCoShareInPercentage() {
		return coShareInPercentage;
	}

	public void setCoShareInPercentage(Double coShareInPercentage) {
		this.coShareInPercentage = coShareInPercentage;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = (status != '\u0000' ? status : 'I');
	}

	public char getIsCancelled() {
		return isCancelled;
	}

	public void setIsCancelled(char isCancelled) {
		this.isCancelled = (isCancelled != '\u0000' ? isCancelled : 'N');
	}

	public char getIsDiscountinued() {
		return isDiscountinued;
	}

	public void setIsDiscountinued(char isDiscountinued) {
		this.isDiscountinued = (isDiscountinued != '\u0000' ? isDiscountinued : 'N');
	}

	public Date getCancelledDate() {
		return cancelledDate;
	}

	public void setCancelledDate(Date cancelledDate) {
		this.cancelledDate = cancelledDate;
	}

	public Integer getCancelledBy() {
		return cancelledBy;
	}

	public void setCancelledBy(Integer cancelledBy) {
		this.cancelledBy = cancelledBy;
	}

	public Integer getCancelledReasonId() {
		return cancelledReasonId;
	}

	public void setCancelledReasonId(Integer cancelledReasonId) {
		this.cancelledReasonId = cancelledReasonId;
	}

	public String getCancelledRemark() {
		return cancelledRemark;
	}

	public void setCancelledRemark(String cancelledRemark) {
		this.cancelledRemark = cancelledRemark;
	}

	public Integer getDiscountinedBy() {
		return discountinedBy;
	}

	public void setDiscountinedBy(Integer discountinedBy) {
		this.discountinedBy = discountinedBy;
	}

	public Date getDiscountinedDate() {
		return discountinedDate;
	}

	public void setDiscountinedDate(Date discountinedDate) {
		this.discountinedDate = discountinedDate;
	}

	public Integer getDiscountinuedReasonId() {
		return discountinuedReasonId;
	}

	public void setDiscountinuedReasonId(Integer discountinuedReasonId) {
		this.discountinuedReasonId = discountinuedReasonId;
	}

	public String getDiscountinuedRemark() {
		return discountinuedRemark;
	}

	public void setDiscountinuedRemark(String discountinuedRemark) {
		this.discountinuedRemark = discountinuedRemark;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Integer getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Admission getAdmission() {
		return admission;
	}

	public void setAdmission(Admission admission) {
		this.admission = admission;
	}

	public Encounter getEncounter() {
		return encounter;
	}

	public void setEncounter(Encounter encounter) {
		this.encounter = encounter;
	}

	public OrderDetail getOrderDetail() {
		return orderDetail;
	}

	public void setOrderDetail(OrderDetail orderDetail) {
		this.orderDetail = orderDetail;
	}

	public MPackageMaster getmPackageMaster() {
		return mPackageMaster;
	}

	public void setmPackageMaster(MPackageMaster mPackageMaster) {
		this.mPackageMaster = mPackageMaster;
	}

	public PackageTypeMaster getPackageTypeMaster() {
		return packageTypeMaster;
	}

	public void setPackageTypeMaster(PackageTypeMaster packageTypeMaster) {
		this.packageTypeMaster = packageTypeMaster;
	}

	public PatientRegistration getPatientRegistration() {
		return patientRegistration;
	}

	public void setPatientRegistration(PatientRegistration patientRegistration) {
		this.patientRegistration = patientRegistration;
	}

	public CompanyMaster getCompanyMaster() {
		return companyMaster;
	}

	public void setCompanyMaster(CompanyMaster companyMaster) {
		this.companyMaster = companyMaster;
	}

	public TariffMaster getTariffMaster() {
		return tariffMaster;
	}

	public void setTariffMaster(TariffMaster tariffMaster) {
		this.tariffMaster = tariffMaster;
	}

	/*public VisitType getVisitTypeMaster() {
		return visitTypeMaster;
	}

	public void setVisitTypeMaster(VisitType visitTypeMaster) {
		this.visitTypeMaster = visitTypeMaster;
	}*/

	public List<TPackageConsumptionEoDetails> getListTPackageConsumptionEoDetails() {
		return listTPackageConsumptionEoDetails;
	}

	public void setListTPackageConsumptionEoDetails(List<TPackageConsumptionEoDetails> listTPackageConsumptionEoDetails) {
		this.listTPackageConsumptionEoDetails = listTPackageConsumptionEoDetails;
	}

	public List<TPackageConsumptionCapDetails> getListTPackageConsumptionCapDetails() {
		return listTPackageConsumptionCapDetails;
	}

	public void setListTPackageConsumptionCapDetails(
			List<TPackageConsumptionCapDetails> listTPackageConsumptionCapDetails) {
		this.listTPackageConsumptionCapDetails = listTPackageConsumptionCapDetails;
	}
}
