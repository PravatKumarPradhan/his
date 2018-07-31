package com.param.global.model;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;

import com.param.global.common.DateConverter;
@NamedNativeQueries({
		@NamedNativeQuery(name="SEARCH_SERVICE_MASTER_BY_UNIT_AND_ORG", 
				query=" SELECT service.service_master_id as \"serviceMasterId\", service.organization_id as \"organizationId\","
						+ " service.service_standard_name as \"serviceStandardName\", service.speciality_id as \"specialityId\","
						+ " service.sub_speciality_id as \"subSpecialityId\""
						+ " FROM service.m_service_master service "
						+ " INNER JOIN service.t_unit_service_mapper mapper ON service.service_master_id = mapper.service_id"
						+ " WHERE lower(service.service_standard_name) like :searchKeyword "
						+ " AND service.speciality_id =:specialityId"
						+ " AND service.sub_speciality_id =:subSpecialityId"
						+ " AND mapper.unit_id =:unitId"
						+ " AND mapper.orgnisation_id=:orgId"
						+ " AND mapper.status ='A'"),
		
		@NamedNativeQuery(name="SEARCH_SERVICE_MASTER_BY_UNIT_AND_ORG_SPECIALITY_SUBSPECILAITY_LIST", 
		query=" SELECT service.service_master_id as \"serviceId\", "
				+ " service.speciality_id as \"specialityId\", "
				+ " service.sub_speciality_id as \"subSpecialityId\", "
				+ " service.service_standard_name as \"serviceStandardName\", "
				+ " speciality.speciality_name as \"specialityName\", "
				+ " subSpciality.sub_speciality_name as \"subSpecialityName\" "
				+ " FROM service.m_service_master service "
				+ " INNER JOIN service.t_unit_service_mapper mapper ON service.service_master_id = mapper.service_id "
				+ " INNER JOIN doctor.m_speciality_master speciality ON service.speciality_id = speciality.speciality_id "
				+ " INNER JOIN doctor.m_sub_speciality_master subSpciality ON service.sub_speciality_id = subSpciality.sub_speciality_id "
				+ " WHERE mapper.unit_id =:unitId"
				+ " AND mapper.orgnisation_id=:orgId"
				+ " AND mapper.status ='A' "),
		
		@NamedNativeQuery(name="GET_MAPPED_SERVICES_BY_UNIT",
				query = "SELECT mapper.service_id as\"serviceId\", mapper.unit_id as\"unitId\", mapper.orgnisation_id as \"orgnisationId\", "
						+ " service.service_standard_name as \"serviceStandardName\", speciality.speciality_name as \"specialityName\","
						+ " subspe.sub_speciality_name as\"subSpecialityName\", mapper.status as \"status\""
						+ " FROM service.t_unit_service_mapper mapper"
						+ " INNER JOIN service.m_service_master service ON mapper.service_id = service.service_master_id"
						+ " INNER JOIN  doctor.m_speciality_master speciality ON speciality.speciality_id = service.speciality_id"
						+ " INNER JOIN doctor.m_sub_speciality_master subspe ON subspe.sub_speciality_id = service.sub_speciality_id"
						+ " WHERE mapper.unit_id =:unitId AND mapper.orgnisation_id =:orgId ORDER BY mapper.service_id DESC"),
		
		@NamedNativeQuery(name="UPDATE_UNIT_SERVICE_MAPPER_STATUS",
				query ="UPDATE service.t_unit_service_mapper SET status =:status"
						+ " WHERE service_id =:serviceId AND unit_id =:unitId AND orgnisation_id =:orgId"),
		
		@NamedNativeQuery(name = "GET_COUNT OF_MAPPED_SERVICES_BY_UNIT",
				query = "SELECT COUNT(*) FROM service.t_unit_service_mapper mapper"
						+ " INNER JOIN service.m_service_master service ON mapper.service_id = service.service_master_id"
						+ " INNER JOIN  doctor.m_speciality_master speciality ON speciality.speciality_id = service.speciality_id"
						+ " INNER JOIN doctor.m_sub_speciality_master subspe ON subspe.sub_speciality_id = service.sub_speciality_id"
						+ " WHERE mapper.unit_id =:unitId AND mapper.orgnisation_id =:orgId"),
		
		@NamedNativeQuery(name="SEARCH_SERVICES_BY_NAME_AND_CODE",
				query="select service.service_master_id as \"serviceMasterId\", "
						+ "service.service_standard_name as \"serviceStandardName\" ,"
						+ "service.service_standard_code as \"serviceStandardCode\", "
						+ "CAST ('0' AS DOUBLE PRECISION) as \"concession\", "
						+ " speciality.speciality_id as \"specialityId\", "
						+ " speciality.speciality_name as \"specialityName\","
						+ " sub_speciality.sub_speciality_id as \"subSpecialityId\", "
						+ " sub_speciality.sub_speciality_name as \"subSpecialityName\", "
						+ " details.is_rate_editable as \"isRateEditable\","
						+ " details.rate_editble_min_value as \"minRateEditable\", "
						+ " details.rate_editble_min_value as \"maxRateEditable\", "
						+ " details.is_tax_applicable as \"isTaxApplicable\","
						+ " details.is_discount as \"isDiscountApplicable\","
						+ " details.discount_value as \"discount\", "
						+ " details.gst_type_id as \"taxId\", "
						+ " tax.name as \"taxName\", "
						+ " tax.tax_percentage as \"taxPercentage\", "
						+ " details.is_allow_multiple_quantity as \"isQuantityEditable\" "
						+ " FROM service.t_unit_service_mapper mapper " 
						+ " inner join service.m_service_master service on service.service_master_id  = mapper.service_id"
						+ " inner join service.t_unit_service_master_details details on details.service_master_id = service.service_master_id"
						+ " inner join doctor.m_speciality_master speciality on service.speciality_id = speciality.speciality_id"
						+ " inner join doctor.m_sub_speciality_master sub_speciality on service.sub_speciality_id = sub_speciality.sub_speciality_id "
						+ " left join public.m_tax tax on tax.id=details.gst_type_id " 
						+ " where mapper.unit_id =:unitId AND mapper.orgnisation_id=:orgId AND service.status='A' AND(lower(service.service_standard_name )like :searchKeyword OR lower(service.service_standard_code) like :searchKeyword)"),
		
		@NamedNativeQuery(name="CHECK_SERVICE_AVAILABLE_IN_UNIT_SERVICE_MAPPER",
		query =" select unitSrvMpper.service_id as \"serviceId\" "
				+ " from service.t_unit_service_mapper unitSrvMpper "
				+ " WHERE service_id IN (:serviceId) ")
})
@Entity
@Table(name="t_unit_service_mapper", schema="service")
public class UnitServiceMapper {
	
	@EmbeddedId
	private UnitServiceMapperId unitServiceMapperId;
	
	@Column(name = "orgnisation_id")
	private Integer orgnisationId;

	@Column(name = "status")
	private char status;

	@Column(name = "created_date")
	@Convert(converter = DateConverter.class)
	private Long createdDate;

	@Column(name = "created_by")
	private Integer createdBy;

	@Column(name = "updated_date")
	@Convert(converter = DateConverter.class)
	private Long updatedDate;

	@Column(name = "updated_by")
	private Integer updatedBy;

	public UnitServiceMapperId getUnitServiceMapperId() {
		return unitServiceMapperId;
	}

	public void setUnitServiceMapperId(UnitServiceMapperId unitServiceMapperId) {
		this.unitServiceMapperId = unitServiceMapperId;
	}

	public Integer getOrgnisationId() {
		return orgnisationId;
	}

	public void setOrgnisationId(Integer orgnisationId) {
		this.orgnisationId = orgnisationId;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status =  status;
	}

	public Long getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Long createdDate) {
		this.createdDate = createdDate;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Long getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Long updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Integer getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}
	
}
