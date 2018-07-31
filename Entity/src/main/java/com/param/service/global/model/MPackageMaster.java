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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.param.adt.master.unit.model.BillingBedCategoryMaster;
import com.param.billing.global.model.PackageTypeMaster;
import com.param.billing.global.transaction.model.TariffMaster;
import com.param.global.model.GenderMaster;
import com.param.global.model.PatientCategoryMaster;
import com.param.global.model.PaymentEntitlementMaster;
import com.param.global.model.ServiceMaster;
import com.param.global.model.VisitTypeMaster;



@NamedNativeQueries({
@NamedNativeQuery(name = "GET_PACKAGE_TYPE_MASTER_LIST", query = "SELECT "
		+" packagetypemast.package_type_id AS \"id\","
		+" packagetypemast.package_type AS \"name\" "
		+" FROM "
		+"	service.m_package_type_master packagetypemast "
		+"WHERE "
		+"	packagetypemast.organisation_id = :orgId "
		+"	AND packagetypemast.unit_id = :orgUnitId "
		+"	AND packagetypemast.staus = 'A' "),

@NamedNativeQuery(name = "SEARCH_EHC_PACKAGES",
query = "SELECT serv.service_standard_code AS \"packageCode\", serv.service_standard_name AS \"packageName\", "
		+ " packType.package_type as \"packageType\",vistmst.visit_type_name as \"visitType\", pack.status as \"status\", pack.package_master_id as \"packageId\" "
		+ " FROM service.m_package_master pack "
		+ " inner join service.m_package_type_master packType "
		+ " on packType.package_type_id = pack.package_type_id "
		+ " inner join service.m_service_master serv  "
		+ " on serv.service_master_id = pack.service_id "
		+ "inner join public.m_visit_type_master  vistmst "
		+ "on vistmst.visit_type_id = pack.visit_type_id " 
		+ " WHERE pack.org_id =:orgId and pack.unit_id =:unitId "),
//'OP' as \"visitType\",

@NamedNativeQuery(name = "SEARCH_PACKAGE_SERVICES_FOR_BILLING",
				 query = "select service.service_master_id as \"serviceMasterId\", pack.package_master_id as \"packageMasterId\",  service.service_standard_name as \"serviceStandardName\" , service.service_standard_code as \"serviceStandardCode\",  "
						 + " CAST ('0' AS DOUBLE PRECISION) as \"concession\", speciality.speciality_id as \"specialityId\", speciality.speciality_name as \"specialityName\", "
						 + " sub_speciality.sub_speciality_id as \"subSpecialityId\", sub_speciality.sub_speciality_name as \"subSpecialityName\", details.is_rate_editable as \"isRateEditable\", "
						 + " details.rate_editble_min_value as \"minRateEditable\", details.rate_editble_min_value as \"maxRateEditable\", details.is_tax_applicable as \"isTaxApplicable\", "
						 + " details.is_discount as \"isDiscountApplicable\", details.discount_value as \"discount\", details.gst_type_id as \"taxId\", tax.name as \"taxName\",  "
						 + " tax.tax_percentage as \"taxPercentage\", details.is_allow_multiple_quantity as \"isQuantityEditable\", cast(pack.package_price as numeric) as \"basePrice\", "
						 + " cast(1 as integer) as \"quantity\", packType.package_type as \"packageType\",pack.package_type_id as \"packageTypeId\" "
						 + " FROM service.t_unit_service_mapper mapper   "
						 + " inner join service.m_service_master service on service.service_master_id  = mapper.service_id "
						 + " inner join service.t_unit_service_master_details details on details.service_master_id = service.service_master_id "
						 + " inner join doctor.m_speciality_master speciality on service.speciality_id = speciality.speciality_id "
						 + " inner join doctor.m_sub_speciality_master sub_speciality on service.sub_speciality_id = sub_speciality.sub_speciality_id  "
						 + " inner join service.m_package_master pack on pack.service_id = service.service_master_id "
						 + " inner join service.m_package_type_master packType on packType.package_type_id = pack.package_type_id "
						 + " left join public.m_tax tax on tax.id=details.gst_type_id   "
						 + " where mapper.unit_id =:unitId AND mapper.orgnisation_id=:orgId AND service.status='A' and pack.package_type_id = :packageTypeId "),

@NamedNativeQuery(name = "GET_PACKAGE_BY_ID",
				 query = "select pack.package_master_id as \"packageMasterId\", pack.patient_type_id as \"patientTypeId\", pack.package_type_id as \"packageTypeId\",  "
						 + " pack.billing_bed_category_id as \"billingBedCategoryId\", pack.payment_entitlement_type_id as \"paymentEntitlementTypeId\", pack.sex_id as \"sexId\", "
						 + " to_char(pack.validity_from_date,'DD-MM-YYYY') as \"validityFromDate\", to_char(pack.validity_to_date,'DD-MM-YYYY') as \"validityToDate\",  "
						 + " pack.status as \"status\", pack.package_cost as \"packageCost\", pack.markup_down_in_percentage as \"markupDownInPercentage\", pack.package_price as \"packagePrice\", "
						 + " pack.is_manual_rounding_is_allow as \"isManualRoundingIsAllow\", pack.manual_roundoff_amount as \"manualRoundoffAmount\",  "
						 + " pack.validity_period_indays as \"validityPeriodIndays\", pack.min_age as \"minAge\", pack.max_age as \"maxAge\", pack.service_id as \"serviceId\", "
						 + " pack.tariff_id as \"tariffId\", pack.is_markup_down as \"isMarkupDown\", pack.round_off_posit_neg as \"roundOffPositNeg\", pack.no_of_encounters as \"noOfEncounters\", "
						 + " srv.service_standard_name as \"packageName\" "
						 + " from service.m_package_master pack "
						 + " inner join service.m_service_master srv  "
						 + " on srv.service_master_id = pack.service_id "
						 + " where pack.status = 'A' and pack.package_master_id = :packageId ")
})

@NamedQueries({
	
	@NamedQuery(name="GET_EHC_PACKAGES_LIST",query="SELECT pm.packageMasterId as packageMasterId, "
			+ "pm.serviceId as serviceId, "
			+ "service.serviceStandardName as serviceDescription "
			+ "FROM MPackageMaster pm "
			+ "INNER JOIN pm.serviceMaster service "
			+ "WHERE pm.sexId=:sexId "
			+ "AND :age > pm.minAge "
			+ "AND :age < pm.maxAge "
			+ "AND pm.orgId=:orgId "
			+ "AND pm.unitId=:unitId "
			+ "AND pm.packageTypeId=1 " //packageTypeId=1 --> For EHC package
			+ "AND service.status='A' "
			+ "AND pm.status='A' ")
})


@Entity
@Table(name = "m_package_master" , schema = "service")
@SequenceGenerator(name = "package_master_seq" , sequenceName = "service.package_master_seq" , allocationSize = 1)
public class MPackageMaster {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "package_master_seq")
	@Column(name = "package_master_id")
	private int packageMasterId;
	
	@Column(name = "visit_type_id")
	private Integer visitTypeId;
	
	@Column(name = "patient_type_id")
	private Integer patientTypeId;
	
	@Column(name = "package_type_id")
	private Integer packageTypeId;
	
	@Column(name = "billing_bed_category_id")
	private Integer billingBedCategoryId;
	
	@Column(name = "payment_entitlement_type_id")
	private Integer paymentEntitlementTypeId;
	
	@Column(name = "sex_id")
	private Integer sexId;
	
	@Column(name = "validity_from_date")
	private Date validityFromDate;
	
	@Column(name = "validity_to_date")
	private Date validityToDate;
	
	@Column(name = "status")
	private char status;
	
	@Column(name = "package_cost")
	private double packageCost;
	
	@Column(name = "markup_down_in_percentage")
	private double markupDownInPercentage;
	
	@Column(name = "package_price")
	private double packagePrice;
	
	@Column(name = "is_manual_rounding_is_allow")
	private char isManualRoundingIsAllow;
	
	@Column(name = "manual_roundoff_amount")
	private double manualRoundoffAmount;
	
	@Column(name = "created_date")
	private Date createdDate;
	
	@Column(name = "created_by")
	private Integer createdBy;
	
	@Column(name = "updated_date")
	private Date updatedDate;
	
	@Column(name = "updated_by")
	private Integer updatedBy;
	
	@Column(name = "validity_period_indays")
	private int validityPeriodIndays;
	
	@Column(name = "min_age")
	private Integer minAge;
	
	@Column(name = "max_age")
	private Integer maxAge;
	
	@Column(name = "service_id")
	private Integer serviceId;
	
	@Column(name = "org_id")
	private Integer orgId;
	
	@Column(name = "unit_id")
	private Integer unitId;
	
	@Column(name = "tariff_id")
	private Integer tariffId;
	
	@Column(name = "is_markup_down")
	private Integer isMarkupDown;
	
	@Column(name = "round_off_posit_neg")
	private Integer roundOffPositNeg;
	
	@Column(name = "no_of_encounters")
	private Integer noOfEncounters;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "billing_bed_category_id" , insertable = false , updatable = false , nullable = false)
	private BillingBedCategoryMaster billingBedCategoryMaster;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "payment_entitlement_type_id" , insertable = false , updatable = false , nullable = false)
	private PaymentEntitlementMaster paymentEntitlementMaster;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "service_id" , insertable = false , updatable = false , nullable = false)
	private ServiceMaster serviceMaster;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sex_id" , insertable = false , updatable = false , nullable = false)
	private GenderMaster genderMaster;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "package_type_id" , insertable = false , updatable = false , nullable = false)
	private PackageTypeMaster packageTypeMaster;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "patient_type_id" , insertable = false , updatable = false , nullable = false)
	private PatientCategoryMaster patientCategoryMaster;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "visit_type_id" , insertable = false , updatable = false , nullable = false)
	private VisitTypeMaster visitTypeMaster;
	
	@OneToMany(fetch = FetchType.LAZY , cascade = CascadeType.ALL , mappedBy = "mPackageMaster")
	private List<TPackageServicesDetails> listTPackageServicesDetails;
	
	@OneToMany(fetch = FetchType.LAZY , cascade = CascadeType.ALL , mappedBy = "mPackageMaster")
	private List<TPackageBedCategoryDetail> listTPackageBedCategoryDetail;
	
	@OneToMany(fetch = FetchType.LAZY , cascade = CascadeType.ALL , mappedBy = "mPackageMaster")
	private List<TPackageCapDetails> listTPackageCapDetails;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tariff_id" , insertable = false , updatable = false , nullable = false)
	private TariffMaster tariffMaster;

	public Integer getNoOfEncounters() {
		return noOfEncounters;
	}

	public void setNoOfEncounters(Integer noOfEncounters) {
		this.noOfEncounters = noOfEncounters;
	}

	public Integer getIsMarkupDown() {
		return isMarkupDown;
	}

	public void setIsMarkupDown(Integer isMarkupDown) {
		this.isMarkupDown = isMarkupDown;
	}

	public Integer getRoundOffPositNeg() {
		return roundOffPositNeg;
	}

	public void setRoundOffPositNeg(Integer roundOffPositNeg) {
		this.roundOffPositNeg = roundOffPositNeg;
	}

	public TariffMaster getTariffMaster() {
		return tariffMaster;
	}

	public void setTariffMaster(TariffMaster tariffMaster) {
		this.tariffMaster = tariffMaster;
	}

	public Integer getTariffId() {
		return tariffId;
	}

	public void setTariffId(Integer tariffId) {
		this.tariffId = tariffId;
	}

	public int getPackageMasterId() {
		return packageMasterId;
	}

	public void setPackageMasterId(int packageMasterId) {
		this.packageMasterId = packageMasterId;
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

	public Integer getPackageTypeId() {
		return packageTypeId;
	}

	public void setPackageTypeId(Integer packageTypeId) {
		this.packageTypeId = packageTypeId;
	}

	public Integer getBillingBedCategoryId() {
		return billingBedCategoryId;
	}

	public void setBillingBedCategoryId(Integer billingBedCategoryId) {
		this.billingBedCategoryId = billingBedCategoryId;
	}

	public Integer getPaymentEntitlementTypeId() {
		return paymentEntitlementTypeId;
	}

	public void setPaymentEntitlementTypeId(Integer paymentEntitlementTypeId) {
		this.paymentEntitlementTypeId = paymentEntitlementTypeId;
	}

	public Integer getSexId() {
		return sexId;
	}

	public void setSexId(Integer sexId) {
		this.sexId = sexId;
	}

	public Date getValidityFromDate() {
		return validityFromDate;
	}

	public void setValidityFromDate(Date validityFromDate) {
		this.validityFromDate = validityFromDate;
	}

	public Date getValidityToDate() {
		return validityToDate;
	}

	public void setValidityToDate(Date validityToDate) {
		this.validityToDate = validityToDate;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}

	public double getPackageCost() {
		return packageCost;
	}

	public void setPackageCost(double packageCost) {
		this.packageCost = packageCost;
	}

	public double getMarkupDownInPercentage() {
		return markupDownInPercentage;
	}

	public void setMarkupDownInPercentage(double markupDownInPercentage) {
		this.markupDownInPercentage = markupDownInPercentage;
	}

	public double getPackagePrice() {
		return packagePrice;
	}

	public void setPackagePrice(double packagePrice) {
		this.packagePrice = packagePrice;
	}

	public char getIsManualRoundingIsAllow() {
		return isManualRoundingIsAllow;
	}

	public void setIsManualRoundingIsAllow(char isManualRoundingIsAllow) {
		this.isManualRoundingIsAllow = isManualRoundingIsAllow;
	}

	public double getManualRoundoffAmount() {
		return manualRoundoffAmount;
	}

	public void setManualRoundoffAmount(double manualRoundoffAmount) {
		this.manualRoundoffAmount = manualRoundoffAmount;
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

	public int getValidityPeriodIndays() {
		return validityPeriodIndays;
	}

	public void setValidityPeriodIndays(int validityPeriodIndays) {
		this.validityPeriodIndays = validityPeriodIndays;
	}

	public Integer getMinAge() {
		return minAge;
	}

	public void setMinAge(Integer minAge) {
		this.minAge = minAge;
	}

	public Integer getMaxAge() {
		return maxAge;
	}

	public void setMaxAge(Integer maxAge) {
		this.maxAge = maxAge;
	}

	public Integer getServiceId() {
		return serviceId;
	}

	public void setServiceId(Integer serviceId) {
		this.serviceId = serviceId;
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

	public BillingBedCategoryMaster getBillingBedCategoryMaster() {
		return billingBedCategoryMaster;
	}

	public void setBillingBedCategoryMaster(BillingBedCategoryMaster billingBedCategoryMaster) {
		this.billingBedCategoryMaster = billingBedCategoryMaster;
	}

	public PaymentEntitlementMaster getPaymentEntitlementMaster() {
		return paymentEntitlementMaster;
	}

	public void setPaymentEntitlementMaster(PaymentEntitlementMaster paymentEntitlementMaster) {
		this.paymentEntitlementMaster = paymentEntitlementMaster;
	}

	public ServiceMaster getServiceMaster() {
		return serviceMaster;
	}

	public void setServiceMaster(ServiceMaster serviceMaster) {
		this.serviceMaster = serviceMaster;
	}

	public GenderMaster getGenderMaster() {
		return genderMaster;
	}

	public void setGenderMaster(GenderMaster genderMaster) {
		this.genderMaster = genderMaster;
	}

	public PackageTypeMaster getPackageTypeMaster() {
		return packageTypeMaster;
	}

	public void setPackageTypeMaster(PackageTypeMaster packageTypeMaster) {
		this.packageTypeMaster = packageTypeMaster;
	}

	public PatientCategoryMaster getPatientCategoryMaster() {
		return patientCategoryMaster;
	}

	public void setPatientCategoryMaster(PatientCategoryMaster patientCategoryMaster) {
		this.patientCategoryMaster = patientCategoryMaster;
	}

	public VisitTypeMaster getVisitTypeMaster() {
		return visitTypeMaster;
	}

	public void setVisitTypeMaster(VisitTypeMaster visitTypeMaster) {
		this.visitTypeMaster = visitTypeMaster;
	}

	public List<TPackageServicesDetails> getListTPackageServicesDetails() {
		return listTPackageServicesDetails;
	}

	public void setListTPackageServicesDetails(List<TPackageServicesDetails> listTPackageServicesDetails) {
		this.listTPackageServicesDetails = listTPackageServicesDetails;
	}

	public List<TPackageBedCategoryDetail> getListTPackageBedCategoryDetail() {
		return listTPackageBedCategoryDetail;
	}

	public void setListTPackageBedCategoryDetail(List<TPackageBedCategoryDetail> listTPackageBedCategoryDetail) {
		this.listTPackageBedCategoryDetail = listTPackageBedCategoryDetail;
	}

	public List<TPackageCapDetails> getListTPackageCapDetails() {
		return listTPackageCapDetails;
	}

	public void setListTPackageCapDetails(List<TPackageCapDetails> listTPackageCapDetails) {
		this.listTPackageCapDetails = listTPackageCapDetails;
	}
}