package com.param.ER.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.param.ER.model.ERBedTypeAllocation;
import com.param.adt.common.ICommonConstants;
import com.param.adt.common.Response;
import com.param.global.dto.AdmissionDto;
import com.param.global.dto.UnknownPatientRegistrationDto;

import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;

@SuppressWarnings({ "rawtypes", "unchecked" })
@Repository
public class ERPatientListDaoImpl extends GenericDao<ERBedTypeAllocation, Integer>
		implements IERPatientListDao, ICommonConstants {

	public ERPatientListDaoImpl() {
		super(ERBedTypeAllocation.class);
	}

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public Response getERAdmissionList(AdmissionDto erAdmissionDto) {
		try {
			List<AdmissionDto> erBedTypeAllocationDtosList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_ER_ADMISSION_LIST")
					.setFirstResult(erAdmissionDto.getOffset() != null ? erAdmissionDto.getOffset() : 0)
					.setMaxResults(erAdmissionDto.getNoOfRecordsPerPage() != null
							? erAdmissionDto.getNoOfRecordsPerPage() : 10)
					.setInteger("orgId", erAdmissionDto.getOrganizationId())
					.setInteger("unitId", erAdmissionDto.getUnitId())
					.setResultTransformer(Transformers.aliasToBean(AdmissionDto.class)).list();
			if (erBedTypeAllocationDtosList != null) {
				return new Response(SUCCESS, null, null, erBedTypeAllocationDtosList, null);
			}
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getERAdmissionCount(AdmissionDto erAdmissionDto) throws ApplicationException {
		try {

			List<AdmissionDto> erBedTypeAllocationDtosList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_ER_ADMISSION_LIST").setInteger("orgId", erAdmissionDto.getOrganizationId())
					.setInteger("unitId", erAdmissionDto.getUnitId())
					.setResultTransformer(Transformers.aliasToBean(AdmissionDto.class)).list();
			if (erBedTypeAllocationDtosList != null) {

				Integer count = erBedTypeAllocationDtosList.size();
				return new Response(SUCCESS, null, null, null, count);
			}
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getDataForMapOfBed(AdmissionDto erAdmissionDto) throws ApplicationException {
		try {

			List<AdmissionDto> erBedTypeAllocationDtosList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_DATA_FOR_MAP_OF_BED").setInteger("orgId", erAdmissionDto.getOrganizationId())
					.setInteger("unitId", erAdmissionDto.getUnitId())
					.setResultTransformer(Transformers.aliasToBean(AdmissionDto.class)).list();
			if (erBedTypeAllocationDtosList != null) {
				return new Response(SUCCESS, null, null, erBedTypeAllocationDtosList, null);
			}
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getERPatient(UnknownPatientRegistrationDto unknownPatientRegistrationDto)
			throws ApplicationException {
		try {

			List<AdmissionDto> erBedTypeAllocationDtosList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_ER_PATIENT_LIST")
					.setParameter("firstName", "%" + unknownPatientRegistrationDto.getPatientName().toLowerCase() + "%")
					.setInteger("orgId", unknownPatientRegistrationDto.getOrganizationId())
					.setInteger("unitId", unknownPatientRegistrationDto.getUnitId())
					.setResultTransformer(Transformers.aliasToBean(AdmissionDto.class)).list();
			if (erBedTypeAllocationDtosList != null) {

				return new Response(SUCCESS, null, null, erBedTypeAllocationDtosList, null);
			}
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response searchERPatient(AdmissionDto admissionDto) throws ApplicationException {
		try {
			Session session = sessionFactory.openSession();
			SQLQuery actualQuery = session.createSQLQuery(generateQuery(admissionDto).toString());
			List<AdmissionDto> admissionDtosList = actualQuery.setInteger("orgId", admissionDto.getOrganizationId())
					.setInteger("unitId", admissionDto.getUnitId())
					.setResultTransformer(Transformers.aliasToBean(AdmissionDto.class)).list();
			return new Response(SUCCESS, null, null, admissionDtosList, null);
		} catch (HibernateException he) {
			he.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		} 
		return new Response(ERROR, null, null, null, null);
	}

	private StringBuilder generateQuery(AdmissionDto admissionDto) {

		StringBuilder condition = new StringBuilder();

		if (admissionDto.getErBedTypeAllocation() > 0 ) 
			condition.append(" AND admissionDetails.er_bed_type_allocation=" + admissionDto.getErBedTypeAllocation());
		
		if (admissionDto.getAdmissionId() > 0) 
			condition.append(" AND admission.admission_id=" + admissionDto.getAdmissionId());

		StringBuilder query = new StringBuilder("select admission.admission_id as \"admissionId\", "
				+ "admissionDetails.admission_details_id as \"admissionDetailsId\", "
				+ "admission.patient_id as \"patientId\", " 
				+ "admission.t_patient_id as \"tPatientId\", "
				+ "admission.admission_number as \"admissionNumber\", " 
				+ "admission.doctor_id as \"doctorId\", "
				+ "concat(doctor.first_name,' ',doctor.middle_name,' ',doctor.last_name) as \"dFirstName\", "
				+ "to_char(admissionDetails.pdd,'dd/mm/yyyy HH:mm:ss')  as \"pdd\", "
				+ "bedMaster.bed_number as \"bedNumber\", " 
				+ "patient.uhid_number as \"UHIDNumber\", "
				+ "concat(patient.first_name,' ',patient.middle_name,' ',patient.last_name) as \"patientName\", "
				+ "to_char(patient.dob,'dd/MM/yyyy') as \"dob\", " 
				+ "gender.gender_code as \"genderName\", "
				+ "bedMaster.bed_id as \"bedId\" " 
				+ "FROM adt.t_admission admission "
				+ "inner join patient.t_patient_registration patient on admission.patient_id=patient.patient_id "
				+ "inner join public.m_gender_master gender on patient.gender_id=gender.gender_id "
				+ "inner join doctor.m_doctor_master doctor on admission.doctor_id=doctor.doctor_id "
				+ "inner join adt.t_admission_details admissionDetails on admission.admission_id=admissionDetails.admission_id "
				+ "left join adt.m_bed_master bedMaster on admissionDetails.bed_id=bedMaster.bed_id "
				+ "where admission.status='A' " 
				+ "AND admission.visit_type_id=4 " 
				+ "AND admission.patient_id > 0 "
				+ "AND admission.organization_id=:orgId " 
				+ "AND admission.unit_id=:unitId " 
				+ condition 
				+ " union "
				+ "select admission.admission_id as \"admissionId\", "
				+ "admissionDetails.admission_details_id as \"admissionDetailsId\", "
				+ "admission.patient_id as \"patientId\", " 
				+ "admission.t_patient_id as \"tPatientId\", "
				+ "admission.admission_number as \"admissionNumber\", " 
				+ "admission.doctor_id as \"doctorId\", "
				+ "concat(doctor.first_name,' ',doctor.middle_name,' ',doctor.last_name) as \"dFirstName\", "
				+ "to_char(admissionDetails.pdd,'dd/mm/yyyy HH:mm:ss') as \"pdd\", "
				+ "bedMaster.bed_number as \"bedNumber\"," 
				+ "unknownPatient.t_uhid as \"UHIDNumber\", "
				+ "unknownPatient.patient_name as \"patientName\","
				+ "concat(to_char(unknownPatient.age,'999'),' ', unknownPatient.age_format) as \"dob\", "
				+ "gender.gender_code as \"genderName\", " 
				+ "bedMaster.bed_id as \"bedId\" "
				+ "FROM adt.t_admission admission "
				+ "inner join patient.m_unknown_patient_registration unknownPatient on admission.t_patient_id=unknownPatient.unknown_patient_id "
				+ "inner join public.m_gender_master gender on unknownPatient.gender_id=gender.gender_id "
				+ "inner join doctor.m_doctor_master doctor on admission.doctor_id=doctor.doctor_id "
				+ "inner join adt.t_admission_details admissionDetails on admission.admission_id=admissionDetails.admission_id "
				+ "left join adt.m_bed_master bedMaster on admissionDetails.bed_id=bedMaster.bed_id "
				+ "where admission.status='A' " 
				+ "and admission.visit_type_id=4 " 
				+ "and admission.t_patient_id > 0 "
				+ "AND admission.organization_id=:orgId " 
				+ "AND admission.unit_id=:unitId " 
				+ condition);
		return query;

	}

	@Override
	public Response searchPatientForMapOfBed(AdmissionDto erAdmissionDto) throws ApplicationException {
		try {

			Session session = sessionFactory.openSession();
			SQLQuery actualQuery = session.createSQLQuery(createQuery(erAdmissionDto).toString());

			List<AdmissionDto> erBedTypeAllocationDtosList = actualQuery
					.setInteger("orgId", erAdmissionDto.getOrganizationId())
					.setInteger("unitId", erAdmissionDto.getUnitId())
					.setResultTransformer(Transformers.aliasToBean(AdmissionDto.class)).list();
			if (erBedTypeAllocationDtosList != null) {

				// Integer count= erBedTypeAllocationDtosList.size();
				return new Response(SUCCESS, null, null, erBedTypeAllocationDtosList, null);
			}
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}
	private StringBuilder createQuery(AdmissionDto erAdmissionDto) {
		StringBuilder condition = new StringBuilder();

		if (erAdmissionDto.getErBedTypeAllocation() > 0)
			condition.append(" AND admissionDetails.er_bed_type_allocation=" + erAdmissionDto.getErBedTypeAllocation());

		if (erAdmissionDto.getPatientId() > 0)
			condition.append(" AND admisson.patient_id=" + erAdmissionDto.getPatientId());

		if (erAdmissionDto.gettPatientId() > 0)
			condition.append(" AND admisson.t_patient_id=" + erAdmissionDto.gettPatientId());

		StringBuilder query = new StringBuilder("select admisson.admission_id as \"admissionId\", "
				+ "admissionDetails.admission_details_id as \"admissionDetailsId\", "
				+ "admisson.patient_id as \"patientId\", " + "admisson.t_patient_id as \"tPatientId\", "
				+ "admisson.admission_number as \"admissionNumber\", " 
				+ "admisson.doctor_id as \"doctorId\", "
				+ "admisson.visit_type_id as \"visitTypeId\", "
				+ "concat(doctor.first_name,' ',doctor.middle_name,' ',doctor.last_name) as \"dFirstName\", "
				+ "to_char(admissionDetails.pdd,'dd/MM/yyyy HH:mm:ss')  as \"pdd\", "
				+ "to_char(admissionDetails.doa,'dd/MM/yyyy HH:mm:ss')  as \"doa\", "
				+ "bedMaster.bed_number as \"bedNumber\", " 
				+ "patient.uhid_number as \"UHIDNumber\", "
				+ "patient.gender_id as \"genderId\", " 
				+ "patient.prefix_id as \"prefixId\", "
				+ "patient.patient_category_id as \"patientCategoryId\", "
				+ "concat(patient.first_name,' ',patient.middle_name,' ',patient.last_name) as \"patientName\", "
				+ "to_char(patient.dob,'dd/MM/yyyy') as \"dob\", " 
				+ "gender.gender_code as \"genderName\", "
				+ "bedMaster.bed_id as \"bedId\", " 
				+ "admissionDetails.floor_id as \"floorId\" "
				+ " FROM adt.t_admission admisson "
				+ "inner join patient.t_patient_registration patient on admisson.patient_id=patient.patient_id "
				+ "inner join public.m_gender_master gender on patient.gender_id=gender.gender_id "
				+ "inner join doctor.m_doctor_master doctor on admisson.doctor_id=doctor.doctor_id "
				+ "inner join adt.t_admission_details admissionDetails on admisson.admission_id=admissionDetails.admission_id "
				+ "inner join adt.m_bed_master bedMaster on admissionDetails.bed_id=bedMaster.bed_id "
				+ "where admisson.status='A' " 
				+ "and admisson.visit_type_id=4 " 
				+ "and admisson.patient_id > 0 "
				+ "AND admisson.organization_id=:orgId " 
				+ "AND admisson.unit_id=:unitId " 
				+ condition 
				+ " union "
				+ "select admisson.admission_id as \"admissionId\", "
				+ "admissionDetails.admission_details_id as \"admissionDetailsId\", "
				+ "admisson.patient_id as \"patientId\", " 
				+ "admisson.t_patient_id as \"tPatientId\", "
				+ "admisson.admission_number as \"admissionNumber\", " 
				+ "admisson.doctor_id as \"doctorId\", "
				+ "admisson.visit_type_id as \"visitTypeId\", "
				+ "concat(doctor.first_name,' ',doctor.middle_name,' ',doctor.last_name) as \"dFirstName\", "
				+ "to_char(admissionDetails.pdd,'dd/MM/yyyy HH:mm:ss') as \"pdd\", "
				+ "to_char(admissionDetails.doa,'dd/MM/yyyy HH:mm:ss') as \"doa\", "
				+ "bedMaster.bed_number as \"bedNumber\", " 
				+ "unknownPatient.t_uhid as \"UHIDNumber\", "
				+ "unknownPatient.gender_id as \"genderId\", " 
				+ "unknownPatient.prefix_id as \"prefixId\", "
				+ "unknownPatient.prefix_id as \"patientCategoryId\", "
				+ "unknownPatient.patient_name as \"patientName\","
				+ "concat(to_char(unknownPatient.age,'999'),' ', unknownPatient.age_format) as \"dob\", "
				+ "gender.gender_code as \"genderName\", " 
				+ "bedMaster.bed_id as \"bedId\", "
				+ "admissionDetails.floor_id as \"floorId\" " 
				+ " FROM adt.t_admission admisson "
				+ "inner join patient.m_unknown_patient_registration unknownPatient on admisson.t_patient_id=unknownPatient.unknown_patient_id "
				+ "inner join public.m_gender_master gender on unknownPatient.gender_id=gender.gender_id "
				+ "inner join doctor.m_doctor_master doctor on admisson.doctor_id=doctor.doctor_id "
				+ "inner join adt.t_admission_details admissionDetails on admisson.admission_id=admissionDetails.admission_id "
				+ "inner join adt.m_bed_master bedMaster on admissionDetails.bed_id=bedMaster.bed_id "
				+ "where admisson.status='A' " 
				+ "and admisson.visit_type_id=4 " 
				+ "and admisson.t_patient_id > 0 "
				+ "AND admisson.organization_id=:orgId " 
				+ "AND admisson.unit_id=:unitId " 
				+ condition);

		return query;
	}


}
