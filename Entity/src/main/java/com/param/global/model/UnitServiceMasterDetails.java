package com.param.global.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Convert;
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

import com.param.global.common.DateConverter;

@NamedNativeQueries({
		@NamedNativeQuery(name = "GET_PAGINATED_UNIT_SERVICE_MASTER_DETAILS_LIST", query = "SELECT details.unit_service_master_details_id as \"unitServiceMasterDetailsId\",  details.service_master_id as \"serviceMasterId\","
				+ " details.is_outsource as \"isOutsource\", details.is_package as \"isPackage\", details.is_rate_editable as \"isRateEditable\","
				+ " details.rate_editble_min_value as \"rateEditbleMinValue\", details.rate_editable_max_value as \"rateEditableMaxValue\","
				+ " details.is_tax_applicable as \"isTaxApplicable\", details.is_ref_doctor_share as \"isRefDoctorShare\","
				+ " details.is_discount as \"isDiscount\", details.gst_type_id as \"gstTypeId\", details.otc_gst_type_id as \"otcGstTypeId\","
				+ " service.service_standard_name as \"serviceStandardName\", service.service_standard_code as \"serviceStandardCode\",speciality.speciality_name as \"specialityName\","
				+ " subspe.sub_speciality_name as\"subSpecialityName\""
				+ " FROM service.t_unit_service_master_details details"
				+ " INNER JOIN service.m_service_master service ON details.service_master_id = service.service_master_id"
				+ " INNER JOIN  doctor.m_speciality_master speciality ON speciality.speciality_id = service.speciality_id"
				+ " INNER JOIN doctor.m_sub_speciality_master subspe ON subspe.sub_speciality_id = service.sub_speciality_id"
				+ " WHERE details.organization_id =:orgId AND details.unit_id =:unitId AND details.status = 'A'"
				+ " ORDER BY details.unit_service_master_details_id DESC"),

		@NamedNativeQuery(name = "GET_UNIT_SERVICE_MASTER_DETAILS_COUNT", query = "SELECT count(*) FROM service.t_unit_service_master_details details"
				+ " INNER JOIN service.m_service_master service ON details.service_master_id = service.service_master_id"
				+ " INNER JOIN  doctor.m_speciality_master speciality ON speciality.speciality_id = service.speciality_id"
				+ " INNER JOIN doctor.m_sub_speciality_master subspe ON subspe.sub_speciality_id = service.sub_speciality_id"
				+ " WHERE details.organization_id =:orgId AND details.unit_id =:unitId AND details.status = 'A'"),

		@NamedNativeQuery(name = "GET_SERVICE_MASTER_DETAILS_BY_ID", query = "SELECT details.unit_service_master_details_id as \"unitServiceMasterDetailsId\",  details.service_master_id as \"serviceMasterId\","
				+ " details.is_outsource as \"isOutsource\", details.is_package as \"isPackage\", details.is_rate_editable as \"isRateEditable\","
				+ " details.rate_editble_min_value as \"rateEditbleMinValue\", details.rate_editable_max_value as \"rateEditableMaxValue\","
				+ " details.is_tax_applicable as \"isTaxApplicable\", details.is_ref_doctor_share as \"isRefDoctorShare\","
				+ " details.is_auto_render as \"isAutoRender\",details.is_allow_multiple_quantity as \"isQuantityEditable\","
				+ " details.is_panel as \"isPanel\",details.is_discount as \"isDiscount\",details.is_procedure as \"isProcedure\",details.is_doc_req as \"isDocReq\","
				+ " details.is_bed_side as \"isBedSide\",details.is_poc as \"isPoc\",details.is_periodicity as \"isPeriodicity\","
				+ " details.discount_value as \"discountValue\" , details.gst_type_id as \"gstTypeId\", details.otc_gst_type_id as \"otcGstTypeId\","
				+ " details.organization_id as \"organizationId\", details.unit_id as \"unitId\", details.created_by as\"createdBy\","
				+  " details.status as \"status\", details.updated_by as \"updatedBy\","				
				+ "  service.service_standard_name as \"serviceStandardName\",service.service_standard_code as \"serviceStandardCode\", service.speciality_id as \"specialityId\", service.sub_speciality_id as \"subSpecialityId\","
				+ "  speciality.speciality_name as \"specialityName\",sub_speciality.sub_speciality_name as \"subSpecialityName\" "
				+ " FROM service.t_unit_service_master_details details"
				+ " INNER JOIN service.m_service_master service ON details.service_master_id = service.service_master_id"
				+ " INNER JOIN doctor.m_speciality_master speciality on service.speciality_id = speciality.speciality_id"
				+ " INNER JOIN doctor.m_sub_speciality_master sub_speciality on service.sub_speciality_id = sub_speciality.sub_speciality_id"
				+ " WHERE details.unit_service_master_details_id =:unitServiceMasterDetailsId ") })

		@NamedNativeQuery(name="GET_ALL_ACTIVE_TAX",
			query="SELECT tax.id as \"id\",tax.name as \"name\" "
				+ "FROM public.m_tax tax "
				+ "WHERE tax.is_active = true AND tax.is_deleted = false AND tax.unit_id = :unitId")
@Entity
@Table(name = "t_unit_service_master_details", schema = "service")
@SequenceGenerator(name = "unit_service_master_details_seq", sequenceName = "service.unit_service_master_details_seq", allocationSize = 1)
public class UnitServiceMasterDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "unit_service_master_details_seq")
	@Column(name = "unit_service_master_details_id")
	private int unitServiceMasterDetailsId;

	@Column(name = "service_master_id")
	private Integer serviceMasterId;

	@Column(name = "is_outsource")
	private char isOutsource;

	@Column(name = "is_package")
	private char isPackage;

	@Column(name = "is_rate_editable")
	private char isRateEditable;

	@Column(name = "rate_editble_min_value")
	private BigDecimal rateEditbleMinValue;

	@Column(name = "rate_editable_max_value")
	private BigDecimal rateEditableMaxValue;

	@Column(name = "is_tax_applicable")
	private char isTaxApplicable;

	@Column(name = "is_ref_doctor_share")
	private char isRefDoctorShare;

	@Column(name = "is_discount")
	private char isDiscount;

	@Column(name = "discount_value")
	private BigDecimal discountValue;

	@Column(name = "gst_type_id")
	private Integer gstTypeId;

	@Column(name = "otc_gst_type_id")
	private Integer otcGstTypeId;

	@Column(name = "organization_id")
	private Integer organizationId;

	@Column(name = "unit_id")
	private Integer unitId;

	@Column(name = "created_by")
	private Integer createdBy;

	@Column(name = "created_date")
	@Convert(converter = DateConverter.class)
	private Long createdDate;

	@Column(name = "status")
	private char status;

	@Column(name = "updated_by")
	private Integer updatedBy;

	@Column(name = "is_panel")
	private Character isPanel;

	@Column(name = "is_allow_multiple_quantity")
	private Character isQuantityEditable;

	@Column(name = "updated_date")
	@Convert(converter = DateConverter.class)
	private Long updatedDate;

	@Column(name = "is_procedure")
	private Character isProcedure;

	@Column(name = "is_doc_req")
	private Character isDocReq;

	@Column(name = "is_bed_side")
	private Character isBedSide;

	@Column(name = "is_poc")
	private Character isPoc;

	@Column(name = "is_periodicity")
	private Character isPeriodicity;
	
	@Column(name = "is_auto_render")
	private Character isAutoRender;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "service_master_id", insertable = false, updatable = false, nullable = false)
	private ServiceMaster serviceMaster;

	public Character getIsQuantityEditable() {
		return isQuantityEditable;
	}

	public void setIsQuantityEditable(Character isQuantityEditable) {
		this.isQuantityEditable = (isQuantityEditable == '\u0000') ? 'I' : isQuantityEditable;
	}

	public int getUnitServiceMasterDetailsId() {
		return unitServiceMasterDetailsId;
	}

	public void setUnitServiceMasterDetailsId(int unitServiceMasterDetailsId) {
		this.unitServiceMasterDetailsId = unitServiceMasterDetailsId;
	}

	public Integer getServiceMasterId() {
		return serviceMasterId;
	}

	public void setServiceMasterId(Integer serviceMasterId) {
		this.serviceMasterId = serviceMasterId;
	}

	public char getIsOutsource() {
		return isOutsource;
	}

	public void setIsOutsource(char isOutsource) {
		this.isOutsource = (isOutsource == '\u0000') ? 'I' : isOutsource;
	}

	public char getIsPackage() {
		return isPackage;
	}

	public void setIsPackage(char isPackage) {
		this.isPackage = (isPackage == '\u0000') ? 'I' : isOutsource;
	}

	public char getIsRateEditable() {
		return isRateEditable;
	}

	public void setIsRateEditable(char isRateEditable) {
		this.isRateEditable = (isRateEditable == '\u0000') ? 'I' : isRateEditable;
	}

	public BigDecimal getRateEditbleMinValue() {
		return rateEditbleMinValue;
	}

	public void setRateEditbleMinValue(BigDecimal rateEditbleMinValue) {
		this.rateEditbleMinValue = rateEditbleMinValue;

	}

	public BigDecimal getRateEditableMaxValue() {
		return rateEditableMaxValue;
	}

	public void setRateEditableMaxValue(BigDecimal rateEditableMaxValue) {
		this.rateEditableMaxValue = rateEditableMaxValue;
	}

	public char getIsTaxApplicable() {
		return isTaxApplicable;
	}

	public void setIsTaxApplicable(char isTaxApplicable) {
		this.isTaxApplicable = (isTaxApplicable == '\u0000') ? 'I' : isTaxApplicable;
	}

	public char getIsRefDoctorShare() {
		return isRefDoctorShare;
	}

	public void setIsRefDoctorShare(char isRefDoctorShare) {
		this.isRefDoctorShare = (isRefDoctorShare == '\u0000') ? 'I' : isRefDoctorShare;
	}

	public char getIsDiscount() {
		return isDiscount;
	}

	public void setIsDiscount(char isDiscount) {
		this.isDiscount = (isDiscount == '\u0000') ? 'I' : isDiscount;
	}

	public Integer getGstTypeId() {
		return gstTypeId;
	}

	public void setGstTypeId(Integer gstTypeId) {
		this.gstTypeId = gstTypeId;
	}

	public Integer getOtcGstTypeId() {
		return otcGstTypeId;
	}

	public void setOtcGstTypeId(Integer otcGstTypeId) {
		this.otcGstTypeId = otcGstTypeId;
	}

	public Integer getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
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

	public Long getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Long createdDate) {
		this.createdDate = createdDate;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = (status == '\u0000') ? 'I' : status;
	}

	public Integer getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Long getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Long updatedDate) {
		this.updatedDate = updatedDate;
	}

	public BigDecimal getDiscountValue() {
		return discountValue;
	}

	public void setDiscountValue(BigDecimal discountValue) {
		this.discountValue = discountValue;
	}

	public Character getIsPanel() {
		return isPanel;
	}

	public void setIsPanel(Character isPanel) {
		this.isPanel = (isPanel == '\u0000') ? 'I' : isPanel;
	}

	public Character getIsProcedure() {
		return isProcedure;
	}

	public void setIsProcedure(Character isProcedure) {
		this.isProcedure = (isProcedure == '\u0000') ? 'I' : isProcedure;
	}

	public Character getIsDocReq() {
		return isDocReq;
	}

	public void setIsDocReq(Character isDocReq) {
		this.isDocReq = (isDocReq == '\u0000') ? 'I' : isDocReq;
	}

	public Character getIsBedSide() {
		return isBedSide;
	}

	public void setIsBedSide(Character isBedSide) {
		this.isBedSide = (isBedSide == '\u0000') ? 'I' : isBedSide;
	}

	public Character getIsPoc() {
		return isPoc;
	}

	public void setIsPoc(Character isPoc) {
		this.isPoc = (isPoc == '\u0000') ? 'I' : isPoc;
	}

	public Character getIsPeriodicity() {
		return isPeriodicity;
	}

	public void setIsPeriodicity(Character isPeriodicity) {
		this.isPeriodicity = (isPeriodicity == '\u0000') ? 'I' : isPeriodicity;
	}

	public ServiceMaster getServiceMaster() {
		return serviceMaster;
	}

	public void setServiceMaster(ServiceMaster serviceMaster) {
		this.serviceMaster = serviceMaster;
	}

	public Character getIsAutoRender() {
		return isAutoRender;
	}

	public void setIsAutoRender(Character isAutoRender) {
		this.isAutoRender = isAutoRender;
	}
	

}
