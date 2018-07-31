package com.param.adt.admission.dao;

import java.util.List;

import org.dozer.DozerBeanMapper;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.param.adt.admission.dto.AdmissionNoteDto;
import com.param.adt.admission.model.Admission;
import com.param.adt.common.ICommonConstants;
import com.param.adt.common.Response;
import com.param.adt.master.unit.dto.WardMasterDto;
import com.param.global.dto.AdmissionDto;
import com.param.global.dto.KinDetailsDto;

import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;

@Repository
@SuppressWarnings({ "rawtypes", "unchecked" })
public class AdmittedPatientListDaoImpl extends GenericDao<Admission, Integer>
implements IAdmittedPatientListDao, ICommonConstants {

	public AdmittedPatientListDaoImpl() {
		super(Admission.class);
		// TODO Auto-generated constructor stub
	}

	@Autowired
	SessionFactory sessionFactory;

	@Autowired
	DozerBeanMapper mapper;

	@Override
	public Response getAdmittedPatientList(AdmissionDto admissionDto) throws ApplicationException {
		try {
			List<AdmissionDto> admissionDtosList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_ADMITTED_PATIENT_LIST")
					.setInteger("organizationId", admissionDto.getOrganizationId())
					.setInteger("unitId", admissionDto.getUnitId())
					.setResultTransformer(Transformers.aliasToBean(AdmissionDto.class)).list();
			return new Response(SUCCESS, null, null, admissionDtosList, null);

		} catch (HibernateException he) {
			he.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		} 
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response getActiveWardListByFloorId(WardMasterDto wardMasterDto) throws ApplicationException {
		try {
			List<AdmissionDto> admissionDtosList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_WARD_BY_FLOOR_ID")
					.setInteger("organizationId", wardMasterDto.getOrganizationId())
					.setInteger("unitId", wardMasterDto.getUnitId()).setInteger("floorId", wardMasterDto.getFloorId())
					.setResultTransformer(Transformers.aliasToBean(WardMasterDto.class)).list();
			return new Response(SUCCESS, null, null, admissionDtosList, null);

		} catch (HibernateException he) {
			he.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		} 
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response getKinDetailsByAdmissionId(AdmissionDto admissionDto) throws ApplicationException {
		try {
			Session session=sessionFactory.openSession();
			SQLQuery query=session.createSQLQuery("SELECT kin.kin_id as \"kinDetailsId\", "
					+ "kin.visit_type_id as \"visitTypeId\", "
					+ "kin.prefix_id as \"prefixId\", "
					+ "prefix.prefix_code as \"prefixCode\", "
					+ "kin.kin_name as \"kinName\", "
					+ "kin.relation_id as \"relationId\", "
					+ "relation.relation_name as \"relationDesc\", "
					+ "kin.mobile_no as \"mobileNo\", "
					+ "kin.phone_no as \"phoneNo\", "
					+ "kin.identification_id as \"identificationId\", "
					+ "id.identification_name as \"identificationName\", "
					+ "kin.identification_no as \"identificationNo\", "
					+ "kin.address as \"address\" "
					+ "FROM public.m_kin_details kin "
					+ "INNER JOIN  public.m_prefix_master prefix ON  prefix.prefix_id = kin.prefix_id "
					+ "INNER JOIN  public.m_relation_master relation ON  relation.relation_id = kin.relation_id "
					+ "INNER JOIN  adt.m_identification_master id ON id.identification_id = kin.identification_id "
					+ "WHERE kin.organization_id=:organizationId "
					+ "AND kin.unit_id=:unitId "
					+ "AND kin.status='A' "
					+ "AND kin.admission_id=:admissionId ");
			List<AdmissionDto> admissionDtosList = query
					.setInteger("organizationId", admissionDto.getOrganizationId())
					.setInteger("unitId", admissionDto.getUnitId())
					.setInteger("admissionId", admissionDto.getAdmissionId())
					.setResultTransformer(Transformers.aliasToBean(KinDetailsDto.class)).list();
			return new Response(SUCCESS, null, null, admissionDtosList, null);

		} catch (HibernateException he) {
			he.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		} 
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response getAdmittedPatientByFloorWard(AdmissionDto admissionDto) throws ApplicationException {
		try {

			Session session = sessionFactory.openSession();
			Query query = session.getNamedQuery("GET_ADMITTED_PATIENT_LIST");
			StringBuilder stringQuery = new StringBuilder(query.getQueryString());
			if (admissionDto.getFloorId() > 0 && admissionDto.getWardId() == 0) {
				stringQuery.append("AND bm.floorId=" + admissionDto.getFloorId());
				System.out.println(stringQuery);
			} else if (admissionDto.getFloorId() != 0 && admissionDto.getWardId() != 0) {
				stringQuery.append(
						"AND bm.floorId=" + admissionDto.getFloorId() + " AND bm.wardId=" + admissionDto.getWardId());
			} else {
				stringQuery.append("");
			}

			Query newQuery = session.createQuery(stringQuery.toString());

			List<AdmissionNoteDto> admissionNoteDtosList = newQuery.setInteger("unitId", admissionDto.getUnitId())
					.setInteger("organizationId", admissionDto.getOrganizationId())
					.setResultTransformer(Transformers.aliasToBean(AdmissionDto.class)).list();

			return new Response(SUCCESS, null, COMMON_ERROR_MESSAGE, admissionNoteDtosList, null);

		} catch (HibernateException he) {
			he.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		} 
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response serachPatientForMapOfBed(AdmissionDto admissionDto) throws ApplicationException {
		try {

			Session session = sessionFactory.openSession();
			SQLQuery finalQuery =session.createSQLQuery(patientSearch(admissionDto).toString());

			List<AdmissionNoteDto> admissionNoteDtosList = finalQuery.setInteger("unitId", admissionDto.getUnitId())
					.setInteger("organizationId", admissionDto.getOrganizationId())
					.setResultTransformer(Transformers.aliasToBean(AdmissionDto.class)).list();

			return new Response(SUCCESS, null, null, admissionNoteDtosList, null);

		} catch (HibernateException he) {
			he.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		} 
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
	}
	
	private StringBuilder patientSearch(AdmissionDto admissionDto)
	{
		
		StringBuilder condition=new StringBuilder();
		
		if(admissionDto.getFloorId()>0)
		{
			condition.append(" AND bm.floor_id="+admissionDto.getFloorId()+" ");
		}
		if(admissionDto.getWardId()>0)
		{
			condition.append(" AND bm.ward_id="+admissionDto.getWardId()+" ");
		}
		if(admissionDto.getNursingStationId()>0)
		{
			condition.append(" AND nurWard.nursing_station_id="+admissionDto.getNursingStationId()+" ");
		}
		if(admissionDto.getBedCategoryId()>0)
		{
			condition.append(" AND apml.bed_category_id="+admissionDto.getBedCategoryId()+" ");
		}
		if(admissionDto.getPatientId()>0)
		{
			condition.append(" AND admission.patient_id="+admissionDto.getPatientId()+" ");
		}
			
		StringBuilder query= new StringBuilder( "SELECT admission.admission_id as \"admissionId\", "
				+ "admission.patient_id as \"patientId\", "
				+ "admission.t_patient_id as \"tPatientId\", "
				+ "admission.admission_number as \"admissionNumber\", "
				+ "admission.visit_type_id as \"visitTypeId\", " 
				+ "admission.doctor_id as \"doctorId\", "
				+ "admission.speciality_id as \"specialityId\", " 
				+ "sp.speciality_name as \"specialityName\", "
				+ "pr.uhid_number as \"UHIDNumber\", " 
				+ "concat(pr.first_name,' ',pr.middle_name,' ',pr.last_name) as \"pFirstName\" , " 
				+ "pr.gender_id as \"genderId\", " 
				+ "gm.gender_code as \"genderCode\", "
				+ "to_char(pr.dob,'MM/dd/yyyy') as \"dob\", " 
				+ "concat(doc.first_name,' ', doc.middle_name,' ',doc.last_name) as \"dFirstName\", "
				+ "to_char(adl.doa,'dd/MM/yyyy') as \"doa\", " 
				+ "to_char(adl.pdd,'dd/MM/yyyy') as \"pdd\", "
				+ "apml.payment_entitlement_id as \"paymentEntitlementId\", "
				+ "pe.payment_entitlement_desc as \"paymentEntitlementDesc\", "
				+ "bedCatMst.bed_category_desc as \"bedCategoryDesc\", " 
				+ "bm.floor_id as \"floorId\", "
				+ "bm.bed_number as \"bedNumber\" "
				+ "FROM adt.t_admission admission " 
				+ "INNER JOIN patient.t_patient_registration pr on admission.patient_id=pr.patient_id "
				+ "INNER JOIN public.m_gender_master gm on pr.gender_id=gm.gender_id " 
				+ "INNER JOIN doctor.m_doctor_master doc on admission.doctor_id=doc.doctor_id "
				+ "INNER JOIN doctor.m_speciality_master sp on admission.speciality_id=sp.speciality_id " 
				+ "INNER JOIN adt.t_admission_details adl on adl.admission_id=admission.admission_id "
				+ "INNER JOIN adt.m_bed_master bm on adl.bed_id=bm.bed_id "
				+ "INNER JOIN adt.t_admission_note anl on admission.admission_id=anl.admission_id "
				+ "INNER JOIN adt.t_admission_patient_mapping apml on anl.admission_note_id = apml.admission_note_id " 
				+ "INNER JOIN adt.m_bed_category_master bedCatMst on apml.bed_category_id = bedCatMst.bed_category_id "
				+ "INNER JOIN public.m_payment_entitlement_master pe on apml.payment_entitlement_id = pe.payment_entitlement_id " 
				+ "INNER JOIN adt.m_ward_master ward on bm.ward_id=ward.ward_id "
				+ "LEFT JOIN adt.t_nursing_station_ward_mapper nurWard on ward.ward_id=nurWard.ward_id "
				+ "LEFT JOIN adt.m_nursing_station_master nur on nurWard.nursing_station_id=nur.nursing_station_id "
				+ "WHERE admission.status='A' "
				+ "AND apml.active_status='B' " 
				+ "AND apml.status='A' " 
				+ "AND anl.status='A' "
				+ "AND admission.organization_id=:organizationId " 
				+ "AND admission.unit_id=:unitId "
				+ condition
				);
				
				
				if(admissionDto.gettPatientId()>0)
				{
					condition.append(" AND admission.t_patient_id="+admissionDto.gettPatientId()+" ");
				
					query.append(
				 "UNION "
				+ "SELECT admission.admission_id as \"admissionId\", "
				+ "admission.patient_id as \"patientId\", "
				+ "admission.t_patient_id as \"tPatientId\", "
				+ "admission.admission_number as \"admissionNumber\", "
				+ "admission.visit_type_id as \"visitTypeId\", " 
				+ "admission.doctor_id as \"doctorId\", "
				+ "admission.speciality_id as \"specialityId\", " 
				+ "sp.speciality_name as \"specialityName\", "
				+ "pr.t_uhid as \"UHIDNumber\", " 
				+ "pr.patient_name as \"pFirstName\" , " 
				+ "pr.gender_id as \"genderId\", " 
				+ "gm.gender_code as \"genderCode\", "
				+ "concat(to_char(pr.age,'999'),' ', pr.age_format) as \"dob\", "
				+ "  concat(doc.first_name,' ', doc.middle_name,' ',doc.last_name) as \"dFirstName\", "
				+ "to_char(adl.doa,'dd/MM/yyyy') as \"doa\", " 
				+ " to_char(adl.pdd,'dd/MM/yyyy') as \"pdd\", "
				+ " apml.payment_entitlement_id as \"paymentEntitlementId\", "
				+ " pe.payment_entitlement_desc as \"paymentEntitlementDesc\", "
				+ " bedCatMst.bed_category_desc as \"bedCategoryDesc\", "
				+ "bm.floor_id as \"floorId\", "
				+ "bm.bed_number as \"bedNumber\" "
				+ " FROM adt.t_admission admission " 
				+ " INNER JOIN patient.m_unknown_patient_registration pr on admission.t_patient_id=pr.unknown_patient_id " 
				+ " INNER JOIN public.m_gender_master gm on pr.gender_id=gm.gender_id " 
				+ " INNER JOIN doctor.m_doctor_master doc on admission.doctor_id=doc.doctor_id "
				+ " INNER JOIN doctor.m_speciality_master sp on admission.speciality_id=sp.speciality_id "
				+ " INNER JOIN adt.t_admission_details adl on adl.admission_id=admission.admission_id "
				+ " INNER JOIN adt.m_bed_master bm on adl.bed_id=bm.bed_id " 
				+ "INNER JOIN adt.m_ward_master ward on bm.ward_id=ward.ward_id "
				+ "LEFT JOIN adt.t_nursing_station_ward_mapper nurWard on ward.ward_id=nurWard.ward_id "
				+ "LEFT JOIN adt.m_nursing_station_master nur on nurWard.nursing_station_id=nur.nursing_station_id "
				+ " INNER JOIN adt.t_admission_note anl on admission.admission_id=anl.admission_id "
				+ " INNER JOIN adt.t_admission_patient_mapping apml on anl.admission_note_id = apml.admission_note_id "  
				+ " INNER JOIN adt.m_bed_category_master bedCatMst on apml.bed_category_id = bedCatMst.bed_category_id "
				+ " INNER JOIN public.m_payment_entitlement_master pe on apml.payment_entitlement_id = pe.payment_entitlement_id " 
				+ " WHERE admission.status='A' "
				+ " AND apml.active_status='B' " 
				+ " AND apml.status='A' " 
				+ " AND anl.status='A' "
				+ " AND admission.organization_id=:organizationId "
				+ " AND admission.unit_id=:unitId"
				+ condition);
		
				}
		
		return query;
	}

	
}
