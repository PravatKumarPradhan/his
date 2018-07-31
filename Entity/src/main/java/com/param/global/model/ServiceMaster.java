package com.param.global.model;

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

import com.param.entity.lis.unit.TestMaster;
import com.param.entity.org.model.GeneralLedgerMaster;
import com.param.service.global.model.MPackageMaster;
import com.param.service.global.model.TContractGroupPharmacyExclusionDetails;
import com.param.service.global.model.TContractServiceDetails;
import com.param.service.global.model.TPackageIncExcDetails;
import com.param.service.global.model.TPackageServicesDetails;

@NamedQueries({

	
	@NamedQuery(name="GET_RENDERED_SERVICES_BY_ENCOUNTER_ID",
				query="SELECT orderDetails.orderDetailsId as orderDetailsId, "
					+ "orderDetails.orgId as orgId, "
					+ "ord.orderNo as orderNo, "
					+ "orderDetails.orgUnitId as orgUnitId, "
					+ "orderDetails.orderId as orderId, "
					+ "orderDetails.orderQty as quantity, "
					+ "orderDetails.serviceId as serviceMasterId, "
					+ "service.serviceStandardName as serviceStandardName, "
					+ "service.serviceStandardCode as serviceStandardCode, "
					+ "orderDetails.priorityId as priorityId, "
					+ "orderDetails.isOutsourced as isOutsourced,"
					+ "to_char(orderDetails.orderDate,'yyyy-mm-dd') as orderDateString, "
					+ "to_char(orderDetails.orderSchDate,'yyyy-mm-dd') as orderSchDateString, "
					//+ "orderDetails.orderSchDate as orderSchDate, "
					//+ "orderDetails.orderDate as orderDate, "
					+ "orderDetails.serviceAmt as basePrice, "
					+ "orderDetails.ordConcession as concession, "
					+ "orderDetails.ordDiscount as discount, "
					+ "orderDetails.netAmount as netPay, "
					+ "orderDetails.netAmount as oldNetPay, "
					+ "orderDetails.ordDocSplId as specialityId, "
					+ "service.specialityId as serviceSpecialityId, "
					+ "service.subSpecialityId as serviceSubSpecialityId, "
					+ "orderDetails.taxId as taxId, "
					+ "tax.name as taxName, "
					+ "orderDetails.taxPer as taxPercentage, "
					+ "orderDetails.taxAmount as taxAmt, "
					+ "orderDetails.selfPayable as selfPayable,"
					+ "orderDetails.creditPayable as creditPayable, "
					+ "orderDetails.payeeId as payeeId, "
					+ "orderDetails.packageConsumptionAmt as packageConsumptionAmt, "
					+ "orderDetails.orderSetId as orderSetId, "
					+ "orderDetails.contractId as contractId, "
					+ "orderDetails.bedCategoryId as bedCategoryId, "
					+ "orderDetails.coPayPer as coPayPer, "
					+ "orderDetails.docSharePer as docSharePer, "
					+ "orderDetails.ordTotalAmt as totalAmt, "
					+ "orderDetails.ordCancelBy as ordCancelBy, "
					+ "orderDetails.ordCancelRemark as ordCancelRemark, "
					+ "orderDetails.tariffId as tariffId, "
					+ "orderDetails.billingClassId as billingClassId, "
					+ "orderDetails.bedId as bedId, "
					+ "orderDetails.roomId as roomId, "
					+ "orderDetails.wardId as wardId, "
					+ "orderDetails.orderEmergencyFlag as orderEmergencyFlag, "
					+ "orderDetails.packageId as packageId, "
					+ "orderDetails.ordDocId as doctorId, "
					+ "orderDetails.drugId as drugId, "
					+ "orderDetails.batchId as batchId, "
					+ "orderDetails.oldOrdDtlId as oldOrdDtlId, "
					+ "orderDetails.serviceRendered as serviceRendered,"
					+ "orderDetails.serviceChargeable as serviceChargeable, "
					+ "orderDetails.serviceRenderingDeptId as serviceRenderingDeptId, "
					+ "orderDetails.ordRenderDatetime as ordRenderDatetime, "
					+ "orderDetails.serviceIsBilled as serviceIsBilled, "
					+ "orderDetails.serviceBillId as serviceBillId, "
					+ "orderDetails.ordRemarks as ordRemarks, "
					+ "orderDetails.ordCancelled as ordCancelled, "
					+ "orderDetails.ordCancelReasonId as ordCancelReasonId, "
					+ "orderDetails.ordCancelDatetime as ordCancelDatetime, "
					+ "orderDetails.isDrug as isDrug, "
					+ "orderDetails.status as status, "
					+ "orderDetails.updatedBy as updatedBy, "
					+ "orderDetails.updatedDate as updatedDate, "
					+ "orderDetails.createdDate as createdDate, "
					+ "orderDetails.createdBy as createdBy, "
					+ "unitService.isRateEditable as isRateEditable, "
					+ "unitService.rateEditbleMinValue as minRateEditable, "
					+ "unitService.rateEditableMaxValue as maxRateEditable, "
					+ "unitService.isTaxApplicable as isTaxApplicable, "
					+ "unitService.isRefDoctorShare as isRefDoctorShare, "
					+ "unitService.isDiscount as isDiscountApplicable, "
					+ "unitService.discountValue as discountValue, "
					+ "unitService.gstTypeId as gstTypeId, "
					+ "unitService.otcGstTypeId as otcGstTypeId, "
					+ "unitService.isQuantityEditable as isQuantityEditable, "
					+ "speciality.specialityName as specialityName,"
					+ "concat('Dr. ',coalesce(doc.firstName),' ',coalesce(doc.middleName),' ',coalesce(doc.lastName)) as doctorName "
					+ "FROM OrderDetailsMaster orderDetails "
					+ "INNER JOIN orderDetails.orderMaster ord "
					+ "LEFT JOIN orderDetails.serviceMaster service "
					+ "LEFT JOIN orderDetails.specialityMaster speciality "
					+ "INNER JOIN service.unitServiceMasterDetailsList unitService "
					+ "LEFT JOIN orderDetails.doctorMaster doc "
					+ "LEFT JOIN orderDetails.tax tax "
					+ "WHERE ord.encounterId=:encounterId "
					+ "AND orderDetails.status='A' "
					+ "AND orderDetails.serviceIsBilled=0 "
					),


		@NamedQuery(name = "CHANGE_GLOBAL_SERVICE_MASTER_STATUS", query = " UPDATE ServiceMaster SET status=:status "
				+ " WHERE serviceMasterId =:serviceMasterId AND organizationId =:organizationId") 
	})


@NamedNativeQueries({
		@NamedNativeQuery(name = "GET_PAGINATED_SERVICE_MASTER_LIST", 
				query = "SELECT service.service_master_id as \"serviceMasterId\", "
						+ "service.codification_service_name as \"codificationServiceName\", "
						+ "speciality.speciality_name as \"specialityName\", "
						+ "subspeciality.sub_speciality_name as \"subSpecialityName\", "
				+ " service.service_standard_name as\"serviceStandardName\","
				+ " service.service_standard_code as\"serviceStandardCode\","
				+ " to_char(service.valid_from,'dd/mm/yyyy') as\"validFrom\","
				+ " to_char(service.valid_till,'dd/mm/yyyy') as\"validTill\","
				+ " glm.general_ledger_id as\"generalLedgerId\","
				+ " glm.general_ledger_name as\"generalLedgerName\","
				+ "service.status as \"status\" "
				+ " from service.m_service_master service"
				+ " INNER JOIN doctor.m_speciality_master as speciality ON speciality.speciality_id = service.speciality_id"
				+ " INNER JOIN doctor.m_sub_speciality_master as subspeciality ON subspeciality.sub_speciality_id = service.sub_speciality_id"
				+ " INNER JOIN public.m_general_ledger_master AS glm ON glm.general_ledger_id=service.general_ledger_id"
				+ " WHERE service.organization_id=:orgId" 
				+ " ORDER BY service.service_master_id DESC"),

		@NamedNativeQuery(name = "GET_SERVICE_MASTER_COUNT", query = "select count(*) from service.m_service_master where "
				+ "organization_id = :orgId"),



	
	
	@NamedNativeQuery(name="GET_AUTO_RENDERED_SERVICES_BY_PATIENT",
				query = " select details.order_details_id as \"orderDetailsId\", "
						+ "orderm.order_id as \"orderId\", "
						+ "service.service_master_id as \"serviceMasterId\", "
						+ " service.service_standard_name as \"serviceStandardName\", "
						+ " service.service_standard_code as \"serviceStandardCode\","
						+ " to_char(orderm.order_date,'yyyy-mm-dd') as \"orderDateString\", "
						+ " details.order_date as \"orderDate\", "
						+ " CAST ('20' AS DOUBLE PRECISION) as \"concession\", "
						+ " CAST ('1' AS DOUBLE PRECISION) as \"quantity\" , "
						+ " serdtl.is_tax_applicable as \"isTaxApplicable\","
						+ " tax.id as \"taxId\", "
						+ " tax.tax_percentage as \"taxPercentage\", "
						+ " tax.name as \"taxName\" "
						+ " FROM public.t_order_details details "
						+ " INNER JOIN public.t_order_master orderm on details.order_id = orderm.order_id "
						+ " INNER JOIN service.m_service_master service  on service.service_master_id = details.service_id "
						+ " INNER JOIN service.t_unit_service_master_details serdtl on service.service_master_id = serdtl.service_master_id "
						+ " LEFT JOIN public.m_tax tax on tax.id=serdtl.gst_type_id "
						+ " WHERE orderm.patient_id=:patientId "
						+ " AND details.service_is_billed=0 "
						+ " AND service.is_autorendered ='A'"
						+ " AND orderm.org_unit_id=:unitId "
						+ " AND orderm.org_id=:orgId"),

		@NamedNativeQuery(name = "GET_SERVICE_MASTER_BY_ID",
		query = " SELECT service.service_master_id as \"serviceMasterId\","
				+ " service.organization_id as \"organizationId\","
				+ " service.service_standard_name as \"serviceStandardName\", "
				+ "service.speciality_id as \"specialityId\","
				+ "service.codification_service_name as \"codificationServiceName\", "	
				+ " service.service_standard_code as\"serviceStandardCode\","
				+ " to_char(service.valid_from,'yyyy/mm/dd') as\"validFrom\","
				+ " to_char(service.valid_till,'yyyy/mm/dd') as\"validTill\","
				+ " glm.general_ledger_id as\"generalLedgerId\","
				+ " glm.general_ledger_name as\"generalLedgerName\","
				+ " subspeciality.sub_speciality_name as \"subSpecialityName\", "
				+ " service.sub_speciality_id as \"subSpecialityId\"" 
				+ " FROM service.m_service_master service "
				+ " INNER JOIN doctor.m_sub_speciality_master as subspeciality ON subspeciality.sub_speciality_id = service.sub_speciality_id "
				+ " INNER JOIN public.m_general_ledger_master AS glm ON glm.general_ledger_id=service.general_ledger_id "
				+ " WHERE service.service_master_id =:serviceMasterId AND service.organization_id=:orgId"),

		@NamedNativeQuery(name = "GET_SERVICE_MASTER_BY_SPECIALITY_AND_SUB_SPECIALITY",
			query = "SELECT service.service_master_id as \"serviceMasterId\", speciality.speciality_name as \"specialityName\", subspeciality.sub_speciality_name as \"subSpecialityName\", "
				+ " service.service_standard_name as\"serviceStandardName\",service.status as \"status\"  from service.m_service_master service"
				+ " INNER JOIN doctor.m_speciality_master as speciality ON speciality.speciality_id = service.speciality_id"
				+ " INNER JOIN doctor.m_sub_speciality_master as subspeciality ON subspeciality.sub_speciality_id = service.sub_speciality_id"
				+ " WHERE service.organization_id=:orgId" + "   AND service.speciality_id =:specialityId"
				+ "   AND service.sub_speciality_id =:subSpecialityId" + "   AND service.status ='A'"
				+ " ORDER BY service.service_master_id ASC"),

		@NamedNativeQuery(name = "GET_PAGINATED_SERVICE_MASTER_LIST_WITH_BASEPRICE_BYVISITTYPE", query = "SELECT service.service_master_id as \"serviceMasterId\", speciality.speciality_name as \"specialityName\", subspeciality.sub_speciality_name as \"subSpecialityName\","
				+ " service.service_standard_name as \"serviceStandardName\",service.status as \"status\", basePrice.base_price as \"basePrice\"  from service.m_service_master as service"
				+ " INNER JOIN doctor.m_speciality_master as speciality ON speciality.speciality_id = service.speciality_id"
				+ " INNER JOIN doctor.m_sub_speciality_master as subspeciality ON subspeciality.sub_speciality_id = service.sub_speciality_id"
				+ " INNER JOIN service.m_service_base_price_master as basePrice on basePrice.service_master_id = service.service_master_id"
				+ " WHERE service.organization_id= :orgId AND basePrice.visit_type_id= :visitTypeId AND  basePrice.from_date <= now() AND basePrice.to_date >= now()"
				+ " ORDER BY service.service_master_id DESC"),

		@NamedNativeQuery(name = "GET_SERVICE_DETAILS_WITH_BASEPRICE_BYSERVICELIST", query = "SELECT service.service_master_id as \"serviceMasterId\", "
				+ " service.speciality_id as \"specialityId\", " + " service.sub_speciality_id as \"subSpecialityId\", "
				+ " speciality.speciality_name as \"specialityName\", "
				+ " subspeciality.sub_speciality_name as \"subSpecialityName\","
				+ " service.service_standard_name as \"serviceStandardName\"," + " service.status as \"status\", "
				+ " basePrice.base_price as \"basePrice\"" + " FROM service.m_service_master as service "
				+ " INNER JOIN doctor.m_speciality_master as speciality ON speciality.speciality_id = service.speciality_id"
				+ " INNER JOIN doctor.m_sub_speciality_master as subspeciality ON subspeciality.sub_speciality_id = service.sub_speciality_id "
				+ " LEFT JOIN service.m_service_base_price_master as basePrice on basePrice.service_master_id = service.service_master_id "
				+ " WHERE service.organization_id= :orgId AND service.service_master_id IN (:listServiceId) ORDER BY service.service_master_id "),

		@NamedNativeQuery(name = "GET_SERVICE_DETAILS_BY_ID", query = " select service.service_standard_name as \"serviceStandardName\" ,"
				+ "service.service_standard_code as \"serviceStandardCode\","
				+ " speciality.speciality_id as \"specialityId\", "
				+ "speciality.speciality_name as \"specialityName\","
				+ " sub_speciality.sub_speciality_id as \"subSpecialityId\", "
				+ "sub_speciality.sub_speciality_name as \"subSpecialityName\" "
				+ " from service.t_unit_service_mapper mapper"
				+ " inner join service.m_service_master service on service.service_master_id  = mapper.service_id"
				+ " inner join service.t_unit_service_master_details details on details.service_master_id = service.service_master_id"
				+ " inner join doctor.m_speciality_master speciality on service.speciality_id = speciality.speciality_id"
				+ " inner join doctor.m_sub_speciality_master sub_speciality on service.sub_speciality_id = sub_speciality.sub_speciality_id"
				+ " where mapper.unit_id =:unitId " + "AND mapper.orgnisation_id=:orgId " + "AND service.status='A' "
				+ "AND service.service_master_id =:serviceId"),

		@NamedNativeQuery(name = "SEARCH_SERVICE_FOR_PACKAGES", query = "select serv.service_master_id as \"serviceMasterId\", sp.speciality_id as \"specialityId\",  sp.speciality_name as \"groupDesc\",   "
				+ " subsp.sub_speciality_id as \"subSpecialityMasterId\", subsp.sub_speciality_name as \"subGroup\", serv.service_standard_code as \"serviceCode\",   "
				+ " serv.service_standard_name as \"serviceDescription\" , coalesce(srvtrf.rate,null) as \"rate\" "
				+ " from service.m_service_master serv  " + " inner join doctor.m_speciality_master sp   "
				+ " on sp.speciality_id = serv.speciality_id  " + " inner join doctor.m_sub_speciality_master subsp   "
				+ " on subsp.sub_speciality_id = serv.sub_speciality_id   "
				+ " inner join service.m_service_tarrif_master srvtrf "
				+ " on srvtrf.service_master_id = serv.service_master_id "
				+ " where serv.status = 'A' and serv.organization_id = :orgId "),
		
		
		@NamedNativeQuery(name = "GET_SERVICE_MASTER_BY_SPECIALITY_AND_SUB_SPECIALITY_NOT_MAPPED",
		query = "SELECT service.service_master_id as \"serviceMasterId\", speciality.speciality_name as \"specialityName\", subspeciality.sub_speciality_name as \"subSpecialityName\", "
			+ " service.service_standard_name as\"serviceStandardName\","
		    + " service.service_standard_code as \"serviceStandardCode\",service.status as \"status\" "
			+ " from service.m_service_master service"
			+ " INNER JOIN doctor.m_speciality_master as speciality ON speciality.speciality_id = service.speciality_id"
			+ " INNER JOIN doctor.m_sub_speciality_master as subspeciality ON subspeciality.sub_speciality_id = service.sub_speciality_id"
			+ " WHERE service.organization_id=:orgId" + "   AND service.speciality_id =:specialityId"
			+ "   AND service.sub_speciality_id =:subSpecialityId" + "   AND service.status ='A' "),


})
@Entity
@Table(name = "m_service_master", schema = "service")
@SequenceGenerator(name = "service_master_seq", sequenceName = "service.service_master_seq", allocationSize = 1)
public class ServiceMaster {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "service_master_seq")
	@Column(name = "service_master_id")
	private int serviceMasterId;

	@Column(name = "service_code_type_id")
	private Integer serviceCodeTypeId;	

	@Column(name = "service_alias_name")
	private String serviceAliasName;	

	@Column(name = "service_short_description")
	private String serviceShortDescription;

	@Column(name = "service_long_description")
	private String serviceLongDescripton;

	@Column(name = "is_available_for_package")
	private char isAvailableForPackage;

	@Column(name = "is_outsource_service")
	private char isOutsourceService;

	@Column(name = "is_ot_procedure")
	private char isOtProcedure;

	@Column(name = "is_order_set")
	private char isOrderSet;

	@Column(name = "is_rate_editable")
	private char isRateEditable;

	@Column(name = "min_rate_editable")
	private float minRateEditable;

	@Column(name = "max_rate_editable")
	private float maxRateEditable;

	@Column(name = "is_discount_applicable")
	private char isDiscountApplicable;

	@Column(name = "is_allow_multiple_quantity")
	private char isAllowMultipleQuantity;

	@Column(name = "is_doctor_required")
	private char isDoctorRequired;

	@Column(name = "is_tax_applicable")
	private char isTaxApplicable;

	@Column(name = "rendering_department_id")
	private Integer renderingDepartmentId;

	@Column(name = "is_autorendered")
	private char isAutorendered;
	
	@Column(name = "service_standard_name")
	private String serviceStandardName;

	@Column(name = "service_standard_code")
	private String serviceStandardCode;

	@Column(name = "speciality_id")
	private Integer specialityId;

	@Column(name = "sub_speciality_id")
	private Integer subSpecialityId;
	
	@Column(name = "organization_id")
	private Integer organizationId;

	@Column(name = "created_by")
	private int createdBy;

	@Column(name = "created_date")
	private Date createdDate;

	@Column(name = "status")
	private char status;

	@Column(name = "updated_by")
	private int updatedBy;

	@Column(name = "updated_date")
	private Date updatedDate;

	@Column(name = "codification_service_name")
	private String codificationServiceName;

	@Column(name = "valid_from")
	private Date validFrom;

	@Column(name = "valid_till")
	private Date validTill;
	
	@Column(name = "general_ledger_id")
	private Integer generalLedgerId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "general_ledger_id", insertable = false, updatable = false, nullable = false)
	private GeneralLedgerMaster generalLedgerMaster;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "speciality_id", insertable = false, updatable = false, nullable = false)
	private SpecialityMaster specialityMaster;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sub_speciality_id", insertable = false, updatable = false, nullable = false)
	private SubSpecialityMaster subSpecialityMaster;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "serviceMaster")
	private List<TestMaster> listTestMaster;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "serviceMaster", fetch = FetchType.LAZY)
	private List<MPackageMaster> listMPackageMaster;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "serviceMaster", fetch = FetchType.LAZY)
	private List<TPackageServicesDetails> listTPackageServicesDetails;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "serviceMaster", fetch = FetchType.LAZY)
	private List<TPackageIncExcDetails> listTPackageIncExcDetails;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "serviceMaster")
	private List<TContractGroupPharmacyExclusionDetails> listTContractGroupPharmacyExclusionDetails;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "serviceMaster")
	private List<TContractServiceDetails> listTContractServiceDetails;
	
	@OneToMany(fetch = FetchType.LAZY , cascade = CascadeType.ALL , mappedBy = "serviceMaster")
	private List<UnitServiceMasterDetails> unitServiceMasterDetailsList;

	public List<TContractServiceDetails> getListTContractServiceDetails() {
		return listTContractServiceDetails;
	}

	public void setListTContractServiceDetails(List<TContractServiceDetails> listTContractServiceDetails) {
		this.listTContractServiceDetails = listTContractServiceDetails;
	}

	public List<TContractGroupPharmacyExclusionDetails> getListTContractGroupPharmacyExclusionDetails() {
		return listTContractGroupPharmacyExclusionDetails;
	}

	public void setListTContractGroupPharmacyExclusionDetails(
			List<TContractGroupPharmacyExclusionDetails> listTContractGroupPharmacyExclusionDetails) {
		this.listTContractGroupPharmacyExclusionDetails = listTContractGroupPharmacyExclusionDetails;
	}

	public List<TPackageIncExcDetails> getListTPackageIncExcDetails() {
		return listTPackageIncExcDetails;
	}

	public void setListTPackageIncExcDetails(List<TPackageIncExcDetails> listTPackageIncExcDetails) {
		this.listTPackageIncExcDetails = listTPackageIncExcDetails;
	}

	public List<TPackageServicesDetails> getListTPackageServicesDetails() {
		return listTPackageServicesDetails;
	}

	public void setListTPackageServicesDetails(List<TPackageServicesDetails> listTPackageServicesDetails) {
		this.listTPackageServicesDetails = listTPackageServicesDetails;
	}

	public List<MPackageMaster> getListMPackageMaster() {
		return listMPackageMaster;
	}

	public void setListMPackageMaster(List<MPackageMaster> listMPackageMaster) {
		this.listMPackageMaster = listMPackageMaster;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public int getServiceMasterId() {
		return serviceMasterId;
	}

	public void setServiceMasterId(int serviceMasterId) {
		this.serviceMasterId = serviceMasterId;
	}

	public int getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(int updatedBy) {
		this.updatedBy = updatedBy;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Integer getServiceCodeTypeId() {
		return serviceCodeTypeId;
	}

	public void setServiceCodeTypeId(Integer serviceCodeTypeId) {
		this.serviceCodeTypeId = serviceCodeTypeId;
	}

	public Integer getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}

	public String getServiceAliasName() {
		return serviceAliasName;
	}

	public void setServiceAliasName(String serviceAliasName) {
		this.serviceAliasName = serviceAliasName;
	}

	public String getServiceStandardName() {
		return serviceStandardName;
	}

	public void setServiceStandardName(String serviceStandardName) {
		this.serviceStandardName = serviceStandardName;
	}

	public Integer getSpecialityId() {
		return specialityId;
	}

	public void setSpecialityId(Integer specialityId) {
		this.specialityId = specialityId;
	}

	public Integer getSubSpecialityId() {
		return subSpecialityId;
	}

	public void setSubSpecialityId(Integer subSpecialityId) {
		this.subSpecialityId = subSpecialityId;
	}

	public String getServiceShortDescription() {
		return serviceShortDescription;
	}

	public void setServiceShortDescription(String serviceShortDescription) {
		this.serviceShortDescription = serviceShortDescription;
	}

	public String getServiceLongDescripton() {
		return serviceLongDescripton;
	}

	public void setServiceLongDescripton(String serviceLongDescripton) {
		this.serviceLongDescripton = serviceLongDescripton;
	}

	public char getIsAvailableForPackage() {
		return isAvailableForPackage;
	}

	public void setIsAvailableForPackage(char isAvailableForPackage) {
		this.isAvailableForPackage = (isAvailableForPackage == '\u0000') ? 'I' : isAvailableForPackage;
	}

	public char getIsOutsourceService() {
		return isOutsourceService;
	}

	public void setIsOutsourceService(char isOutsourceService) {
		this.isOutsourceService = (isOutsourceService == '\u0000') ? 'I' : isOutsourceService;
	}

	public char getIsOtProcedure() {
		return isOtProcedure;
	}

	public void setIsOtProcedure(char isOtProcedure) {
		this.isOtProcedure = (isOtProcedure == '\u0000') ? 'I' : isOtProcedure;
	}

	public char getIsOrderSet() {
		return isOrderSet;
	}

	public void setIsOrderSet(char isOrderSet) {
		this.isOrderSet = (isOrderSet == '\u0000') ? 'I' : isOrderSet;
	}

	public char getIsRateEditable() {
		return isRateEditable;
	}

	public void setIsRateEditable(char isRateEditable) {
		this.isRateEditable = (isRateEditable == '\u0000') ? 'I' : isRateEditable;
	}

	public float getMinRateEditable() {
		return minRateEditable;
	}

	public void setMinRateEditable(float minRateEditable) {
		this.minRateEditable = minRateEditable;
	}

	public float getMaxRateEditable() {
		return maxRateEditable;
	}

	public void setMaxRateEditable(float maxRateEditable) {
		this.maxRateEditable = maxRateEditable;
	}

	public char getIsDiscountApplicable() {
		return isDiscountApplicable;
	}

	public void setIsDiscountApplicable(char isDiscountApplicable) {
		this.isDiscountApplicable = (isDiscountApplicable == '\u0000') ? 'I' : isDiscountApplicable;
	}

	public char getIsAllowMultipleQuantity() {
		return isAllowMultipleQuantity;
	}

	public void setIsAllowMultipleQuantity(char isAllowMultipleQuantity) {
		this.isAllowMultipleQuantity = (isAllowMultipleQuantity == '\u0000') ? 'I' : isAllowMultipleQuantity;
	}

	public char getIsDoctorRequired() {
		return isDoctorRequired;
	}

	public void setIsDoctorRequired(char isDoctorRequired) {
		this.isDoctorRequired = (isDoctorRequired == '\u0000') ? 'I' : isDoctorRequired;
	}

	public char getIsTaxApplicable() {
		return isTaxApplicable;
	}

	public void setIsTaxApplicable(char isTaxApplicable) {
		this.isTaxApplicable = (isTaxApplicable == '\u0000') ? 'I' : isTaxApplicable;
	}

	public Integer getRenderingDepartmentId() {
		return renderingDepartmentId;
	}

	public void setRenderingDepartmentId(Integer renderingDepartmentId) {
		this.renderingDepartmentId = renderingDepartmentId;
	}

	public char getIsAutorendered() {
		return isAutorendered;
	}

	public void setIsAutorendered(char isAutorendered) {
		this.isAutorendered = (isAutorendered == '\u0000') ? 'I' : isAutorendered;
	}

	public int getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public SpecialityMaster getSpecialityMaster() {
		return specialityMaster;
	}

	public void setSpecialityMaster(SpecialityMaster specialityMaster) {
		this.specialityMaster = specialityMaster;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}

	public String getServiceStandardCode() {
		return serviceStandardCode;
	}

	public void setServiceStandardCode(String serviceStandardCode) {
		this.serviceStandardCode = serviceStandardCode;
	}

	public SubSpecialityMaster getSubSpecialityMaster() {
		return subSpecialityMaster;
	}

	public void setSubSpecialityMaster(SubSpecialityMaster subSpecialityMaster) {
		this.subSpecialityMaster = subSpecialityMaster;
	}

	public List<TestMaster> getListTestMaster() {
		return listTestMaster;
	}

	public void setListTestMaster(List<TestMaster> listTestMaster) {
		this.listTestMaster = listTestMaster;
	}

	public String getCodificationServiceName() {
		return codificationServiceName;
	}

	public void setCodificationServiceName(String codificationServiceName) {
		this.codificationServiceName = codificationServiceName;
	}

	public Date getValidFrom() {
		return validFrom;
	}

	public void setValidFrom(Date validFrom) {
		this.validFrom = validFrom;
	}

	public Date getValidTill() {
		return validTill;
	}

	public void setValidTill(Date validTill) {
		this.validTill = validTill;
	}

	public Integer getGeneralLedgerId() {
		return generalLedgerId;
	}

	public void setGeneralLedgerId(Integer generalLedgerId) {
		this.generalLedgerId = generalLedgerId;
	}

}
