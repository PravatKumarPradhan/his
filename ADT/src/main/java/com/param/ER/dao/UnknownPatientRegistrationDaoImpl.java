package com.param.ER.dao;

import java.util.List;

import org.dozer.DozerBeanMapper;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.param.adt.admission.model.Admission;
import com.param.adt.admission.model.AdmissionDetails;
import com.param.adt.admission.model.MedicoLeagal;
import com.param.adt.common.ICommonConstants;
import com.param.adt.common.Response;
import com.param.global.dto.AdmissionDto;
import com.param.global.dto.PatientSearchDto;
import com.param.global.model.UnknownPatientRegistration;
import com.param.global.org.common.dto.PriorityMasterDto;

import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;

@SuppressWarnings({ "rawtypes", "unchecked" })
@Repository
public class UnknownPatientRegistrationDaoImpl extends GenericDao<UnknownPatientRegistration, Integer>
		implements ICommonConstants, IUnknownPatientRegistrationDao {

	@Autowired
	SessionFactory sessionFactory;

	@Autowired
	DozerBeanMapper mapper;

	public UnknownPatientRegistrationDaoImpl() {
		super(UnknownPatientRegistration.class);

	}

	@Override
	public Response saveUnknownPatient(AdmissionDto unAdmissionDto) throws ApplicationException {
		try {

			UnknownPatientRegistration unknownPatientRegistration = mapper.map(unAdmissionDto,
					UnknownPatientRegistration.class, "AdmissionDto_to_UnknownPatientRegistration");
			save(unknownPatientRegistration);

			return new Response(SUCCESS, null, null, null, unknownPatientRegistration);
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getPriorityMasterList() throws ApplicationException {
		try {
			List<PriorityMasterDto> priorityMasterDtosList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_PRIORITY_LIST")
					.setResultTransformer(Transformers.aliasToBean(PriorityMasterDto.class)).list();
			if (priorityMasterDtosList != null) {
				return new Response(SUCCESS, null, null, priorityMasterDtosList, null);
			}
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response saveERPatientAdmission(AdmissionDto unAdmissionDto) throws ApplicationException {
		try {

			Session session = sessionFactory.openSession();

			Admission admission = mapper.map(unAdmissionDto, Admission.class, "AdmissionDto_to_Admission");
			Transaction tr = session.beginTransaction();
			Integer id = (Integer) session.save(admission);
			tr.commit();
			session.close();
			return new Response(SUCCESS, null, id.toString(), null, null);
		} catch (HibernateException he) {
			he.printStackTrace();
			return new Response(ERROR, null, null, null, null);
		} catch (Exception e) {
			e.printStackTrace();
			return new Response(ERROR, null, null, null, null);
		} finally {
			 ;
		}
	}

	@Override
	public Response saveERPatientDetails(AdmissionDto unAdmissionDto) throws ApplicationException {
		try {
			Session session = sessionFactory.openSession();

			// <-----Adding data in patient details
			Transaction tr = session.beginTransaction();
			AdmissionDetails admissionDetails = mapper.map(unAdmissionDto, AdmissionDetails.class,
					"AdmissionDto_to_AdmissionDetails");
			session.save(admissionDetails);
			tr.commit();
			// ----->

			return new Response(SUCCESS, null, null, null, admissionDetails);

		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response patientSearchByMultipleCriteria(PatientSearchDto patientSearchDto) throws ApplicationException {
		try {
			Session session = sessionFactory.openSession();
			StringBuilder resQuery = this.searchPatient(patientSearchDto);
			SQLQuery actualQuery = session.createSQLQuery(resQuery.toString());
			List<PatientSearchDto> bedMasterDtosList = actualQuery
					.setInteger("organizationId", patientSearchDto.getOrganizationId())
					.setInteger("unitId", patientSearchDto.getUnitId())
					.setResultTransformer(Transformers.aliasToBean(PatientSearchDto.class)).list();
			return new Response(SUCCESS, null, null, bedMasterDtosList, null);

		} catch (HibernateException he) {
			he.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
	}

	private StringBuilder searchPatient(PatientSearchDto patientSearchDto) 
	{
		StringBuilder query=new StringBuilder();
		if(patientSearchDto.getPatientType()!=null)
		{
		String strQuery="SELECT pr.patient_id as \"patientId\", "
			+" pr.uhid_number as \"uhIdNumber\", "
			+" concat(coalesce(pr.first_name,'') ,' ', coalesce(pr.middle_name,'') ,' ', coalesce(pr.last_name,'')) as \"patientName\",  " 
			+" pr.gender_id as \"genderId\",   "
			+" gm.gender_code as \"genderCode\",  gm.gender_name as \"genderName\","
			+ "pr.mobile_number as \"mobileNo\", "
			+ "id.identification_name as \"identificationName\", "
			+ "pr.identification_number as \"identificationNo\", "
			+" to_char(pr.dob,'DD/MM/YYYY') as \"birthDate\","
			+ "concat(EXTRACT(year from AGE(CURRENT_TIMESTAMP, pr.dob)) , 'Y ' , EXTRACT(month from AGE(CURRENT_TIMESTAMP, pr.dob)),'M ') as \"age\", "
			+ "1 as \"patientType\","
			+ "pr.prefix_id as \"prefixId\", "
			+ "pr.patient_category_id as \"patientCategoryId\", "
			+ "adm.admission_id as \"admissionId\" "
			//+ "to_char(pr.dob,'DD-MM-YYYY') as \"birthDate\" "
			+ "FROM patient.t_patient_registration as pr "
			+ "INNER JOIN public.m_gender_master as gm	on gm.gender_id= pr.gender_id "
			+ "INNER JOIN adt.m_identification_master as id on id.identification_id = pr.identification_type_id	"
			+ "LEFT JOIN adt.t_admission as adm on adm.patient_id = pr.patient_id AND adm.status='A' "
			+ " WHERE pr.status='A' "
			+ " AND pr.organization_id=:organizationId "
			+ " AND pr.unit_id=:unitId ";
		query.append(strQuery);
		StringBuilder ANDIdentification=new StringBuilder("AND pr.identification_number='"+patientSearchDto.getIdentificationNo()+"' ");
		StringBuilder ANDDob=new StringBuilder("AND pr.dob='"+patientSearchDto.getBirthDate()+"' ");
		StringBuilder ANDMobile=new StringBuilder("AND pr.mobile_number='"+patientSearchDto.getMobileNo()+"' ");
		StringBuilder ANDGenderId=new StringBuilder("AND pr.gender_id="+patientSearchDto.getGenderId()+" ");
		StringBuilder ANDPatientName=new StringBuilder("AND lower(concat(coalesce(pr.first_name,''),coalesce(pr.last_name,''))) LIKE concat('%',lower('"+patientSearchDto.getPatientName()+"'),'%') ");
		StringBuilder ANDUhid=new StringBuilder("AND lower(pr.uhid_number) LIKE concat('%',LOWER('"+patientSearchDto.getUhIdNumber()+"'),'%') ");
		
		if(patientSearchDto.getUhIdNumber() != null && !patientSearchDto.getUhIdNumber().isEmpty())
			query.append(ANDUhid);
		if(patientSearchDto.getPatientName() != null && !patientSearchDto.getPatientName().isEmpty())
			query.append(ANDPatientName);
		if(patientSearchDto.getGenderId() != null)
			query.append(ANDGenderId);
		if(patientSearchDto.getMobileNo() != null && !patientSearchDto.getMobileNo().isEmpty())
			query.append(ANDMobile);
		if(patientSearchDto.getBirthDate() != null && !patientSearchDto.getBirthDate().isEmpty())
			query.append(ANDDob);
		if(patientSearchDto.getIdentificationNo() != null && !patientSearchDto.getIdentificationNo().isEmpty())
			query.append(ANDIdentification);
		
		if(patientSearchDto.getPatientType()==4)
		{
		String strQuery2=" union SELECT pr.unknown_patient_id as \"patientId\", "
				+" pr.t_uhid as \"uhIdNumber\", "
				+" pr.patient_name as \"patientName\",  " 
				+" pr.gender_id as \"genderId\",   "
				+" gm.gender_code as \"genderCode\",  gm.gender_name as \"genderName\","
				+ "pr.mobile as \"mobileNo\", "
				+ "'-' as \"identificationName\", "
				+ "'-' as \"identificationNo\", "
				+ "'-' as \"birthDate\","
				+" concat(to_char(pr.age,'999'),' ', pr.age_format) as \"age\",  "
				+ "4 as \"patientType\", "
				+ "0 as \"prefixId\", "
				+ "0 as \"patientCategoryId\", "
				+ "adm.admission_id as \"admissionId\" "
				+ "FROM patient.m_unknown_patient_registration as pr "
				+ "INNER JOIN public.m_gender_master as gm	on gm.gender_id= pr.gender_id "
				+ "LEFT JOIN adt.t_admission as adm on adm.patient_id = pr.unknown_patient_id AND adm.status='A' "
				//+ "LEFT JOIN adt.m_identification_master as id on id.identification_id = pr.identification_type_id	"
				+ " WHERE pr.status='A' "
				+ " AND pr.organization_id=:organizationId "
				+ " AND pr.unit_id=:unitId ";
			query.append(strQuery2);
			//StringBuilder ANDIdentification2=new StringBuilder("AND pr.identification_number='"+patientSearchDto.getIdentificationNo()+"'");
			//StringBuilder ANDDob2=new StringBuilder("AND pr.dob='"+patientSearchDto.getBirthDate()+"'");
			StringBuilder ANDMobile2=new StringBuilder("AND pr.mobile_number='"+patientSearchDto.getMobileNo()+"'");
			StringBuilder ANDGenderId2=new StringBuilder("AND pr.gender_id="+patientSearchDto.getGenderId());
			StringBuilder ANDPatientName2=new StringBuilder("AND lower(coalesce(pr.patient_name,'')) LIKE concat('%',lower('"+patientSearchDto.getPatientName()+"'),'%')");
			StringBuilder ANDUhid2=new StringBuilder("AND lower(pr.t_uhid) LIKE concat('%',LOWER('"+patientSearchDto.getUhIdNumber()+"'),'%')");
			
			if(patientSearchDto.getUhIdNumber() != null && !patientSearchDto.getUhIdNumber().isEmpty())
				query.append(ANDUhid2);
			if(patientSearchDto.getPatientName() != null && !patientSearchDto.getPatientName().isEmpty())
				query.append(ANDPatientName2);
			if(patientSearchDto.getGenderId() != null)
				query.append(ANDGenderId2);
			if(patientSearchDto.getMobileNo() != null && !patientSearchDto.getMobileNo().isEmpty())
				query.append(ANDMobile2);
			/*if(patientSearchDto.getBirthDate() != null && !patientSearchDto.getBirthDate().isEmpty())
				query.append(ANDDob);
			if(patientSearchDto.getIdentificationNo() != null && !patientSearchDto.getIdentificationNo().isEmpty())
				query.append(ANDIdentification);*/
		}
		}
		
		
		System.out.println(query);
		return query;
	}

	@Override
	public Response saveMedicoLegalDetails(AdmissionDto unAdmissionDto) throws ApplicationException {
		try {
			Session session = sessionFactory.openSession();

			// <-----Adding data in medicoLeagal if medicoLegal flag is on
			Transaction tr = session.beginTransaction();
			MedicoLeagal medicoLeagal = mapper.map(unAdmissionDto, MedicoLeagal.class,
					"AdmissionDto_to_MedicoLeagal");
			session.save(medicoLeagal);
			tr.commit();
			// ----->

			return new Response(SUCCESS, null, null, null, null);

		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	

}
