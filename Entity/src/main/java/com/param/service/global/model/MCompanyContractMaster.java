package com.param.service.global.model;

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
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.param.adt.master.unit.model.BillingBedCategoryMaster;
import com.param.billing.global.transaction.model.TariffMaster;
import com.param.global.model.AssociatedCompanyMaster;
import com.param.global.model.CompanyMaster;
import com.param.global.model.PaymentEntitlementMaster;
import com.param.global.model.VisitTypeMaster;

@NamedNativeQueries({
@NamedNativeQuery(name = "SEARCH_FOR_COMPANY_CONTRACT",
query = "SELECT "
		+ "   contractmst.contract_id AS \"contractId\", "
		+ "   assocompany.company_desc as \"assCompanyName\", "
		+ "   tariffmst.tariff_desc as \"tariffName\", "
		+ "   paymst.payment_entitlement_desc AS \"paymentEntitlementName\", "
		+ "   contractmst.contract_name AS \"contractName\", "
		+ "   contractmst.validity_from AS \"validityFrom\", "
		+ "   contractmst.validity_to AS \"validityTo\", "
		+ "   insurance.insurance_desc as \"insuranceName\" "
		+ " FROM "
		+ "   service.m_company_contract_master contractmst "
		+ "   INNER JOIN public.m_payment_entitlement_master paymst on paymst.payment_entitlement_id = contractmst.payment_entitlement_id "
		+ "   INNER JOIN  adt.t_insurance insurance on insurance.insurance_id = contractmst.company_id "
		+ "   INNER JOIN  t_company_master assocompany on assocompany.company_id = contractmst.associate_company_id "
		+ "   INNER JOIN  service.m_tariff_master tariffmst on tariffmst.tariff_id = contractmst.tariff_id "
		+ " WHERE "
		+ "   contractmst.org_id =:orgId "
		+ "   AND contractmst.unit_id =:orgUnitId "),

})
@Entity
@Table(name = "m_company_contract_master" , schema = "service")
@SequenceGenerator(name = "m_company_contract_master_seq" , sequenceName = "service.m_company_contract_master_seq" , allocationSize = 1)
public class MCompanyContractMaster {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "m_company_contract_master_seq")
	@Column(name = "contract_id")
	private int contractId;
	
	@Column(name = "org_id")
	private Integer orgId;
	
	@Column(name = "unit_id")
	private Integer unitId;
	
	@Column(name = "payment_entitlement_id")
	private Integer paymentEntitlementId;
	
	@Column(name = "associate_company_id")
	private Integer associateCompanyId;
	
	@Column(name = "contract_name")
	private String contractName;
	
	@Column(name = "validity_from")
	private Date validityFrom;
	
	@Column(name = "validity_to")
	private Date validityTo;
	
	@Column(name = "grade_id")
	private Integer gradeId;
	
	@Column(name = "co_share_percentage")
	private double coSharePercentage;
	
	@Column(name = "tariff_id")
	private Integer tariffId;
	
	@Column(name = "is_tariff_rate_applicable")
	private char isTariffRateApplicable;
	
	@Column(name = "billing_bed_category_id")
	private Integer billingBedCategoryId;
	
	@Column(name = "visit_type_id")
	private Integer visitTypeId;
	
	@Column(name = "patient_type_id")
	private Integer patientTypeId;
	
	@Column(name = "company_id")
	private Integer companyId;
	
	@Column(name = "created_by")
	private Integer createdBy;
	
	@Column(name = "updated_by")
	private Integer updatedBy;
	
	@Column(name = "updated_date")
	private Date updatedDate;
	
	@Column(name = "created_date")
	private Date createdDate;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name  = "associate_company_id" , insertable = false , updatable = false , nullable = false)
	private AssociatedCompanyMaster associatedCompanyMaster;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name  = "billing_bed_category_id" , insertable = false , updatable = false , nullable = false)
	private BillingBedCategoryMaster billingBedCategoryMaster;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name  = "company_id" , insertable = false , updatable = false , nullable = false)
	private CompanyMaster companyMaster;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name  = "grade_id" , insertable = false , updatable = false , nullable = false)
	private ContractGradeMaster contractGradeMaster;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name  = "payment_entitlement_id" , insertable = false , updatable = false , nullable = false)
	private PaymentEntitlementMaster paymentEntitlementMaster;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name  = "tariff_id" , insertable = false , updatable = false , nullable = false)
	private TariffMaster tariffMaster;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name  = "visit_type_id" , insertable = false , updatable = false , nullable = false)
	private VisitTypeMaster visitTypeMaster;
	
	@OneToMany(fetch = FetchType.LAZY , cascade = CascadeType.ALL , mappedBy = "mCompanyContractMaster")
	private List<TContractGroupPharmacyExclusionDetails> listTContractGroupPharmacyExclusionDetails;
	
	@OneToMany(fetch = FetchType.LAZY , cascade = CascadeType.ALL , mappedBy = "mCompanyContractMaster")
	private List<TContractCapDetails> listTContractCapDetails;
	
	@OneToMany(fetch = FetchType.LAZY , cascade = CascadeType.ALL , mappedBy = "mCompanyContractMaster")
	private List<TContractGroupDetails> listTContractGroupDetails;
	
	@OneToMany(fetch = FetchType.LAZY , cascade = CascadeType.ALL , mappedBy = "mCompanyContractMaster")
	private List<TContractServiceDetails> listTContractServiceDetails;

	public List<TContractServiceDetails> getListTContractServiceDetails() {
		return listTContractServiceDetails;
	}

	public void setListTContractServiceDetails(List<TContractServiceDetails> listTContractServiceDetails) {
		this.listTContractServiceDetails = listTContractServiceDetails;
	}

	public List<TContractGroupDetails> getListTContractGroupDetails() {
		return listTContractGroupDetails;
	}

	public void setListTContractGroupDetails(List<TContractGroupDetails> listTContractGroupDetails) {
		this.listTContractGroupDetails = listTContractGroupDetails;
	}

	public List<TContractCapDetails> getListTContractCapDetails() {
		return listTContractCapDetails;
	}

	public void setListTContractCapDetails(List<TContractCapDetails> listTContractCapDetails) {
		this.listTContractCapDetails = listTContractCapDetails;
	}

	public List<TContractGroupPharmacyExclusionDetails> getListTContractGroupPharmacyExclusionDetails() {
		return listTContractGroupPharmacyExclusionDetails;
	}

	public void setListTContractGroupPharmacyExclusionDetails(List<TContractGroupPharmacyExclusionDetails> listTContractGroupPharmacyExclusionDetails) {
		this.listTContractGroupPharmacyExclusionDetails = listTContractGroupPharmacyExclusionDetails;
	}

	public int getContractId() {
		return contractId;
	}

	public void setContractId(int contractId) {
		this.contractId = contractId;
	}

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public Integer getUnitId() {
		return unitId;
	}

	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}

	public Integer getPaymentEntitlementId() {
		return paymentEntitlementId;
	}

	public void setPaymentEntitlementId(Integer paymentEntitlementId) {
		this.paymentEntitlementId = paymentEntitlementId;
	}

	public Integer getAssociateCompanyId() {
		return associateCompanyId;
	}

	public void setAssociateCompanyId(Integer associateCompanyId) {
		this.associateCompanyId = associateCompanyId;
	}

	public String getContractName() {
		return contractName;
	}

	public void setContractName(String contractName) {
		this.contractName = contractName;
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

	public Integer getGradeId() {
		return gradeId;
	}

	public void setGradeId(Integer gradeId) {
		this.gradeId = gradeId;
	}

	public double getCoSharePercentage() {
		return coSharePercentage;
	}

	public void setCoSharePercentage(double coSharePercentage) {
		this.coSharePercentage = coSharePercentage;
	}

	public Integer getTariffId() {
		return tariffId;
	}

	public void setTariffId(Integer tariffId) {
		this.tariffId = tariffId;
	}

	public char getIsTariffRateApplicable() {
		return isTariffRateApplicable;
	}

	public void setIsTariffRateApplicable(char isTariffRateApplicable) {
		this.isTariffRateApplicable = isTariffRateApplicable;
	}

	public Integer getBillingBedCategoryId() {
		return billingBedCategoryId;
	}

	public void setBillingBedCategoryId(Integer billingBedCategoryId) {
		this.billingBedCategoryId = billingBedCategoryId;
	}

	public Integer getVisitTypeId() {
		return visitTypeId;
	}

	public void setVisitTypeId(Integer visitTypeId) {
		this.visitTypeId = visitTypeId;
	}

	public Integer getPatientTypeId() {
		return patientTypeId;
	}

	public void setPatientTypeId(Integer patientTypeId) {
		this.patientTypeId = patientTypeId;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Integer getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public AssociatedCompanyMaster getAssociatedCompanyMaster() {
		return associatedCompanyMaster;
	}

	public void setAssociatedCompanyMaster(AssociatedCompanyMaster associatedCompanyMaster) {
		this.associatedCompanyMaster = associatedCompanyMaster;
	}

	public BillingBedCategoryMaster getBillingBedCategoryMaster() {
		return billingBedCategoryMaster;
	}

	public void setBillingBedCategoryMaster(BillingBedCategoryMaster billingBedCategoryMaster) {
		this.billingBedCategoryMaster = billingBedCategoryMaster;
	}

	public CompanyMaster getCompanyMaster() {
		return companyMaster;
	}

	public void setCompanyMaster(CompanyMaster companyMaster) {
		this.companyMaster = companyMaster;
	}

	public ContractGradeMaster getContractGradeMaster() {
		return contractGradeMaster;
	}

	public void setContractGradeMaster(ContractGradeMaster contractGradeMaster) {
		this.contractGradeMaster = contractGradeMaster;
	}

	public PaymentEntitlementMaster getPaymentEntitlementMaster() {
		return paymentEntitlementMaster;
	}

	public void setPaymentEntitlementMaster(PaymentEntitlementMaster paymentEntitlementMaster) {
		this.paymentEntitlementMaster = paymentEntitlementMaster;
	}

	public TariffMaster getTariffMaster() {
		return tariffMaster;
	}

	public void setTariffMaster(TariffMaster tariffMaster) {
		this.tariffMaster = tariffMaster;
	}

	public VisitTypeMaster getVisitTypeMaster() {
		return visitTypeMaster;
	}

	public void setVisitTypeMaster(VisitTypeMaster visitTypeMaster) {
		this.visitTypeMaster = visitTypeMaster;
	}
}
