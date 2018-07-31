package com.param.service.global.model;

import java.util.Date;

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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.param.global.model.ServiceMaster;

@NamedNativeQueries({
	@NamedNativeQuery(name = "GET_SERVICE_DETAILS_BY_PACKAGE_ID_FOR_BILLING",
				query = "select ps.package_service_details_id as \"packageServiceDetailsId\", ps.package_id as \"packageId\", ps.service_id as \"serviceId\",  "
						+ " ps.number_to_be_use as \"numberToBeUse\", ps.service_price as \"servicePrice\", ps.apportioned_price as \"apportionedPrice\",  "
						+ " ps.package_either_or_group_id as \"packageEitherOrGroupId\", ps.is_service_item as \"isServiceItem\", ps.item_id as \"itemId\", ps.periodicity_id as \"periodicityId\", "
						+ " srv.service_standard_name as \"serviceName\", srv.service_standard_code as \"serviceCode\", srv.service_speciality_id as \"specialityId\", srv.service_sub_speciality_id as \"subSpecialityId\", "
						+ " spc.speciality_name as \"specialityName\", subspc.sub_speciality_name as \"subSpecialityName\" "
						+ " from service.t_package_services_details ps "
						+ " inner join service.m_service_master srv "
						+ " on srv.service_master_id = ps.service_id "
						+ " inner join doctor.m_speciality_master spc "
						+ " on spc.speciality_id = srv.speciality_id "
						+ " left join doctor.m_sub_speciality_master subspc "
						+ " on subspc.sub_speciality_id = srv.sub_speciality_id "
						+ " where ps.package_id = :packageId "),
	@NamedNativeQuery(
		name="GET_EXISITNG_PACKAGE_DETAILS_BY_ENCOUNTERID_VISITTYPE_PATIENTID",
		query="select pcm.package_consumption_master_id as \"packageConsumptionMasterId\", packMaster.package_master_id as \"packageId\", ordMaster.order_no as \"orderNo\", to_char(ordMaster.order_date, 'DD-MM-YYYY') as \"orderDate\", srv.service_standard_name as \"packageName\","
				+" srv.service_standard_code as \"packageCode\", packMaster.package_price as \"packagePrice\", packMaster.package_type_id as \"packageTypeId\", packMaster.service_id as \"packageServiceId\","
				+" pcm.order_detail_id as \"orderDetailId\", to_char(pcm.validity_from, 'DD-MM-YYYY') as \"validityFrom\",  to_char(pcm.validity_to, 'DD-MM-YYYY') as \"validityTo\", pcm.applicable_no_of_visit as \"applicableNoOfVisit\","
				+" pcm.total_deposite as \"totalDeposite\", pcm.balance_deposite as \"balanceDeposite\", pcm.is_cancelled as \"isCancelled\", pcm.is_discountinued as \"isDiscountinued\"," 
				+"  to_char(pcm.cancelled_date, 'DD-MM-YYYY') as \"cancelledDate\", pcm.cancelled_by as \"cancelledBy\", pcm.cancelled_reason_id as \"cancelledReasonId\", pcm.cancelled_remark as \"cancelledRemark\","
				+" pcm.discountined_by as \"discountinuedBy\", pcm.discountinued_reason_id as \"discountinuedReasonId\",  to_char(pcm.discountined_date, 'DD-MM-YYYY') as \"discountinuedDate\",  pcm.discountinued_remark as \"discountinuedRemark\" "
				
				+ " from billing.t_package_consumption_master pcm"
				+ "	inner join service.m_package_master packMaster on packMaster.package_master_id = pcm.package_id"
				+ " inner join service.m_service_master srv on packMaster.service_id = srv.service_master_id"
				+ " inner join public.t_order_details ordDetails on ordDetails.order_details_id = pcm.order_detail_id"
				+ " inner join public.t_order_master ordMaster on ordMaster.order_id = ordDetails.order_id"
				+ " where    pcm.patient_id =:patientId  and ((pcm.encounter_id =:encounterId and pcm.visit_type =:visitTypeId) or (pcm.admission_id =:encounterId and pcm.visit_type =:visitTypeId))"
				+ " and pcm.organisation_id =:organisationId and  pcm.unit_id=:unitId and pcm.status= 'A' and pcm.is_cancelled = 'N' and pcm.is_discountinued = 'N'"
	),
	@NamedNativeQuery(
			name="GET_PACAKAGE_CONSUMPTION_SERVICE_DETAILS_BY_PACKAGEID_PATIENTID",
			query=" SELECT srv.service_master_id as \"serviceId\", srv.service_standard_code as \"serviceCode\", srv.service_standard_name as \"serviceStandardName\","
			 	+ " pcsd.package_quantity as \"packageQuantity\", pcsd.balance_package_quantity as \"balanceQuantity\","
			 	+ " pcsd.is_service_item as \"isServiceItem\" "
			 	+ "  , srv.service_speciality_id as \"specialityId\", srv.service_sub_speciality_id as \"subSpecialityId\", sm.speciality_name as \"specialityName\", ssm.sub_speciality_name as \"subSpecialityName\" "
			 	+ " from billing.t_package_consumption_master pcm"
			 	+ " inner join billing.t_package_consumption_service_detail pcsd on pcsd.package_consumption_master_id = pcm.package_consumption_master_id"
			 	+ " inner join service.m_service_master srv on pcsd.service_id = srv.service_master_id"
			 	+ " left join doctor.m_speciality_master sm on sm.speciality_id = srv.service_speciality_id"
				+ " left join doctor.m_sub_speciality_master ssm on ssm.sub_speciality_id = srv.service_sub_speciality_id"
			 	+ " where package_id =:packageId and pcm.patient_id =:patientId ")
})
@Entity
@Table(name="t_package_services_details",schema="service")
@SequenceGenerator(name="package_service_details_seq",sequenceName="service.package_service_details_seq",allocationSize=1)
public class TPackageServicesDetails {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="package_service_details_seq")
	@Column(name="package_service_details_id")
	private int packageServiceDetailsId;
	
	@Column(name="package_id")
	private Integer packageId;
	
	@Column(name="service_id")
	private Integer serviceId;

	@Column(name="number_to_be_use")
	private Integer numberToBeUse;
	
	@Column(name="service_price")
	private Double servicePrice;
	
	@Column(name="apportioned_price")
	private Double apportionedPrice;
	
	@Column(name="orgnisation_id")
	private Integer orgnisationId;
	
	@Column(name="unit_id")
	private Integer unitId;
	
	@Column(name="created_by")
	private Integer createdBy;
	
	@Column(name="created_date")
	private Date createdDate;
	
	@Column(name="updated_by")
	private Integer updatedBy;
	
	@Column(name="updated_date")
	private Date updatedDate;
	
	@Column(name="status")
	private char status;
	
	@Column(name="package_either_or_group_id")
	private Integer packageEitherOrGroupId;
	
	@Column(name="is_service_item")
	private Integer isServiceItem;
	
	@Column(name="item_id")
	private Integer itemId;
	
	@Column(name="periodicity_id")
	private Integer periodicityId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "package_id" , insertable = false , updatable = false , nullable = false)
	private MPackageMaster mPackageMaster;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "service_id" , insertable = false , updatable = false , nullable = false)
	private ServiceMaster serviceMaster;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "package_either_or_group_id" , insertable = false , updatable = false , nullable = false)
	private TPackageEitherorGroupDetails tPackageEitherorGroupDetails;

	public MPackageMaster getmPackageMaster() {
		return mPackageMaster;
	}

	public void setmPackageMaster(MPackageMaster mPackageMaster) {
		this.mPackageMaster = mPackageMaster;
	}

	public ServiceMaster getServiceMaster() {
		return serviceMaster;
	}

	public void setServiceMaster(ServiceMaster serviceMaster) {
		this.serviceMaster = serviceMaster;
	}

	public TPackageEitherorGroupDetails gettPackageEitherorGroupDetails() {
		return tPackageEitherorGroupDetails;
	}

	public void settPackageEitherorGroupDetails(TPackageEitherorGroupDetails tPackageEitherorGroupDetails) {
		this.tPackageEitherorGroupDetails = tPackageEitherorGroupDetails;
	}

	public int getPackageServiceDetailsId() {
		return packageServiceDetailsId;
	}

	public void setPackageServiceDetailsId(int packageServiceDetailsId) {
		this.packageServiceDetailsId = packageServiceDetailsId;
	}

	public Integer getPackageId() {
		return packageId;
	}

	public void setPackageId(Integer packageId) {
		this.packageId = packageId;
	}

	public Integer getServiceId() {
		return serviceId;
	}

	public void setServiceId(Integer serviceId) {
		this.serviceId = serviceId;
	}

	public Integer getNumberToBeUse() {
		return numberToBeUse;
	}

	public void setNumberToBeUse(Integer numberToBeUse) {
		this.numberToBeUse = numberToBeUse;
	}

	public Double getServicePrice() {
		return servicePrice;
	}

	public void setServicePrice(Double servicePrice) {
		this.servicePrice = servicePrice;
	}

	public Double getApportionedPrice() {
		return apportionedPrice;
	}

	public void setApportionedPrice(Double apportionedPrice) {
		this.apportionedPrice = apportionedPrice;
	}

	public Integer getOrgnisationId() {
		return orgnisationId;
	}

	public void setOrgnisationId(Integer orgnisationId) {
		this.orgnisationId = orgnisationId;
	}

	public Integer getUnitId() {
		return unitId;
	}

	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
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

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}

	public Integer getPackageEitherOrGroupId() {
		return packageEitherOrGroupId;
	}

	public void setPackageEitherOrGroupId(Integer packageEitherOrGroupId) {
		this.packageEitherOrGroupId = packageEitherOrGroupId;
	}

	public Integer getIsServiceItem() {
		return isServiceItem;
	}

	public void setIsServiceItem(Integer isServiceItem) {
		this.isServiceItem = isServiceItem;
	}

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public Integer getPeriodicityId() {
		return periodicityId;
	}

	public void setPeriodicityId(Integer periodicityId) {
		this.periodicityId = periodicityId;
	}
	
	
}
