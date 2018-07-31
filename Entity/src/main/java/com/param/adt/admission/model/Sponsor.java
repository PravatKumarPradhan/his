package com.param.adt.admission.model;

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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.param.adt.master.global.model.OrganizationMaster;
import com.param.adt.master.global.model.UnitMaster;
import com.param.adt.master.unit.model.MealPreferenceMaster;
import com.param.global.model.BedCategoryMaster;
import com.param.global.model.PatientCategoryMaster;
import com.param.global.model.PaymentEntitlementMaster;
import com.param.global.model.VisitTypeMaster;

@Entity
@Table(name = "t_sponsor", schema = "adt")
@SequenceGenerator(name = "sponsor_seq", sequenceName = "adt.sponsor_seq", allocationSize = 1)
public class Sponsor {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sponsor_seq")
	@Column(name = "sponsor_id")
	private int sponsorId;

	@Column(name = "unit_id")
	private Integer unitId;

	@Column(name = "admission_id")
	private Integer admissionId;

	@Column(name = "is_op_ip")
	private Integer isOpIp;

	@Column(name = "patient_category_id")
	private Integer patientCategoryId;

	@Column(name = "company_type_id")
	private Integer companyTypeId;

	@Column(name = "company_id")
	private Integer companyId;

	@Column(name = "associate_company_id")
	private Integer associateCompanyId;

	@Column(name = "payment_entitlement_id")
	private Integer paymentEntitlementId;

	@Column(name = "billing_bed_category_id")
	private Integer billingBedCategoryId;

	@Column(name = "bed_category_id")
	private Integer bedCategoryId;

	@Column(name = "priority_id")
	private Integer priorityId;

	@Column(name = "contract_id")
	private Integer contractId;

	@Column(name = "meal_preference_id")
	private Integer mealPreferenceId;

	@Column(name = "attended_meal_preference_id")
	private Integer attendedMealPreferenceId;

	@Column(name = "third_party_administrator_id")
	private Integer thirdPartyaAdministratorId;

	@Column(name = "policy_id")
	private Integer policyId;

	@Column(name = "validity_period")
	private String validityPeriod;

	@Column(name = "amt_insured")
	private float amtInsured;

	@Column(name = "co_payment_percentage")
	private float coPaymentPercentage;

	@Column(name = "created_date")
	private Date createdDate;

	@Column(name = "created_by")
	private int createdBy;

	@Column(name = "updated_by")
	private int updatedBy;

	@Column(name = "updated_date")
	private Date updatedDate;

	@Column(name = "status")
	private char status;

	@Column(name = "organization_id")
	private Integer organizationId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "organization_id", insertable = false, nullable = false, updatable = false)
	private OrganizationMaster organizationMaster;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "unit_id", insertable = false, nullable = false, updatable = false)
	private UnitMaster unitMaster;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "visit_type_id", insertable = false, nullable = false, updatable = false)
	private VisitTypeMaster visitTypeMaster;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "patient_category_id", insertable = false, nullable = false, updatable = false)
	private PatientCategoryMaster patientCategoryMaster;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "payment_entitlement_id", insertable = false, nullable = false, updatable = false)
	private PaymentEntitlementMaster paymentEntitlementMaster;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "bed_category_id", insertable = false, nullable = false, updatable = false)
	private BedCategoryMaster bedCategoryMaster;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "meal_preference_id", insertable = false, nullable = false, updatable = false)
	private MealPreferenceMaster mealPreferenceMaster;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "attended_meal_preference_id", insertable = false, nullable = false, updatable = false)
	private MealPreferenceMaster attendedMealPreferenceMaster;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "sponser", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<SponsorDetails> sponserDetailsList;

	public int getSponsorId() {
		return sponsorId;
	}

	public void setSponsorId(int sponsorId) {
		this.sponsorId = sponsorId;
	}

	public Integer getUnitId() {
		return unitId;
	}

	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}

	public Integer getAdmissionId() {
		return admissionId;
	}

	public void setAdmissionId(Integer admissionId) {
		this.admissionId = admissionId;
	}

	public Integer getIsOpIp() {
		return isOpIp;
	}

	public void setIsOpIp(Integer isOpIp) {
		this.isOpIp = isOpIp;
	}


	public List<SponsorDetails> getSponserDetailsList() {
		return sponserDetailsList;
	}

	public void setSponserDetailsList(List<SponsorDetails> sponserDetailsList) {
		this.sponserDetailsList = sponserDetailsList;
	}

	public Integer getPatientCategoryId() {
		return patientCategoryId;
	}

	public void setPatientCategoryId(Integer patientCategoryId) {
		this.patientCategoryId = patientCategoryId;
	}

	public Integer getCompanyTypeId() {
		return companyTypeId;
	}

	public void setCompanyTypeId(Integer companyTypeId) {
		this.companyTypeId = companyTypeId;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public Integer getAssociateCompanyId() {
		return associateCompanyId;
	}

	public void setAssociateCompanyId(Integer associateCompanyId) {
		this.associateCompanyId = associateCompanyId;
	}

	public Integer getPaymentEntitlementId() {
		return paymentEntitlementId;
	}

	public void setPaymentEntitlementId(Integer paymentEntitlementId) {
		this.paymentEntitlementId = paymentEntitlementId;
	}

	public Integer getBillingBedCategoryId() {
		return billingBedCategoryId;
	}

	public void setBillingBedCategoryId(Integer billingBedCategoryId) {
		this.billingBedCategoryId = billingBedCategoryId;
	}

	public Integer getBedCategoryId() {
		return bedCategoryId;
	}

	public void setBedCategoryId(Integer bedCategoryId) {
		this.bedCategoryId = bedCategoryId;
	}

	public Integer getPriorityId() {
		return priorityId;
	}

	public void setPriorityId(Integer priorityId) {
		this.priorityId = priorityId;
	}

	public Integer getContractId() {
		return contractId;
	}

	public void setContractId(Integer contractId) {
		this.contractId = contractId;
	}

	public Integer getMealPreferenceId() {
		return mealPreferenceId;
	}

	public void setMealPreferenceId(Integer mealPreferenceId) {
		this.mealPreferenceId = mealPreferenceId;
	}

	public Integer getAttendedMealPreferenceId() {
		return attendedMealPreferenceId;
	}

	public void setAttendedMealPreferenceId(Integer attendedMealPreferenceId) {
		this.attendedMealPreferenceId = attendedMealPreferenceId;
	}

	public Integer getThirdPartyaAdministratorId() {
		return thirdPartyaAdministratorId;
	}

	public void setThirdPartyaAdministratorId(Integer thirdPartyaAdministratorId) {
		this.thirdPartyaAdministratorId = thirdPartyaAdministratorId;
	}

	public Integer getPolicyId() {
		return policyId;
	}

	public void setPolicyId(Integer policyId) {
		this.policyId = policyId;
	}

	public String getValidityPeriod() {
		return validityPeriod;
	}

	public void setValidityPeriod(String validityPeriod) {
		this.validityPeriod = validityPeriod;
	}

	public float getAmtInsured() {
		return amtInsured;
	}

	public void setAmtInsured(float amtInsured) {
		this.amtInsured = amtInsured;
	}

	public float getCoPaymentPercentage() {
		return coPaymentPercentage;
	}

	public void setCoPaymentPercentage(float coPaymentPercentage) {
		this.coPaymentPercentage = coPaymentPercentage;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public int getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	public int getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(int updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}

	public Integer getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}

	public OrganizationMaster getOrganizationMaster() {
		return organizationMaster;
	}

	public void setOrganizationMaster(OrganizationMaster organizationMaster) {
		this.organizationMaster = organizationMaster;
	}

	public UnitMaster getUnitMaster() {
		return unitMaster;
	}

	public void setUnitMaster(UnitMaster unitMaster) {
		this.unitMaster = unitMaster;
	}


/*	public PatientCategoryMaster getPatientCategoryMaster() {
		return patientCategoryMaster;
	}

	public void setPatientCategoryMaster(PatientCategoryMaster patientCategoryMaster) {
		this.patientCategoryMaster = patientCategoryMaster;
	}
*/


	public MealPreferenceMaster getMealPreferenceMaster() {
		return mealPreferenceMaster;
	}

	public void setMealPreferenceMaster(MealPreferenceMaster mealPreferenceMaster) {
		this.mealPreferenceMaster = mealPreferenceMaster;
	}

	public MealPreferenceMaster getAttendedMealPreferenceMaster() {
		return attendedMealPreferenceMaster;
	}

	public void setAttendedMealPreferenceMaster(MealPreferenceMaster attendedMealPreferenceMaster) {
		this.attendedMealPreferenceMaster = attendedMealPreferenceMaster;
	}

	/*
	 * @ManyToOne
	 * 
	 * @JoinColumn(name = "third_party_administrator_id", insertable = false,
	 * nullable = false, updatable = false) private ThirdPartyAdministrator
	 * thirdPartyAdministrator;
	 */

	/*
	 * @ManyToOne
	 * 
	 * @JoinColumn(name = "policy_id", insertable = false, nullable = false,
	 * updatable = false) private PolicyMaster policyMaster;
	 */

}
