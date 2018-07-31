package com.param.global.dao;

import java.math.BigInteger;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.param.global.common.GlobalCommonDateUtils;
import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;
import com.param.global.dto.PatientDetailsDto;
import com.param.global.dto.PatientRegistrationDto;
import com.param.global.dto.PatientSearchBillRes;
import com.param.global.dto.UnknownPatientRegistrationDto;
import com.param.global.dto.sync.PatientMasterDto;
import com.param.global.model.PatientDetails;
import com.param.global.model.PatientRegistration;
import com.param.global.model.PatientRegistrationLog;

import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;

@Repository
@SuppressWarnings({ "unchecked", "rawtypes" })
public class PatientRegistrationDaoImpl extends GenericDao<PatientRegistration, Integer> implements IPatientRegistrationDao, ICommonConstants{
	
	public PatientRegistrationDaoImpl() {
		super(PatientRegistration.class);
	}

	@Autowired
	DozerBeanMapper mapper;
	
	@Override
	public Response savePatientRegistration(PatientRegistrationDto patientRegistrationDto) throws ApplicationException {
	try{	
		Session session=sessionFactory.openSession();
			Transaction tr=session.beginTransaction();
				//<-----RegistrationTypeId==>4 and IsUnknownReg==>'Y' means its a conversion from Unknown patient to Normal patient 	
				if(patientRegistrationDto.getRegistrationTypeId()==4 && patientRegistrationDto.getIsUnknownReg()=='Y')
				{
				Query query=session.createQuery("update UnknownPatientRegistration upr set upr.status='I',"
							+ "upr.updatedBy=:updatedBy,"
							+ "upr.updatedDate=:updatedDate "
							+ "WHERE "
							+ "upr.unknownPatientId=:prevId "
							+ "AND upr.organizationId=:orgId "
							+ "AND upr.unitId=:unitId ");
					query.setInteger("updatedBy", patientRegistrationDto.getUpdatedBy());
					query.setDate("updatedDate",GlobalCommonDateUtils.getDate(patientRegistrationDto.getUpdatedDate(),"dd-M-yyyy HH:mm:ss"));
					query.setInteger("prevId", patientRegistrationDto.getPreviousId());
					query.setInteger("orgId", patientRegistrationDto.getOrganizationId());
					query.setInteger("unitId", patientRegistrationDto.getUnitId());
				query.executeUpdate();
				}
				//----->
				//<-----RegistrationTypeId==>3 and IsUnknownReg==>'Y' means its a conversion from OTC patient to Normal patient
				if(patientRegistrationDto.getRegistrationTypeId()==3 && patientRegistrationDto.getIsOtcReg()=='Y')
				{
					Query query=session.createQuery("update PatientRegistration pr set pr.status='I',"
							+ "pr.updatedBy=:updatedBy,"
							+ "pr.updatedDate=:updatedDate "
							+ "WHERE "
							+ "pr.patientId=:prevId "
							+ "AND pr.organizationId=:orgId "
							+ "AND pr.unitId=:unitId ");
					query.setInteger("updatedBy", patientRegistrationDto.getUpdatedBy());
					query.setDate("updatedDate",GlobalCommonDateUtils.getDate(patientRegistrationDto.getUpdatedDate(),"dd-M-yyyy HH:mm:ss"));
					query.setInteger("prevId", patientRegistrationDto.getPreviousId());
					query.setInteger("orgId", patientRegistrationDto.getOrganizationId());
					query.setInteger("unitId", patientRegistrationDto.getUnitId());
				query.executeUpdate();
				}
				//----->
				//<----- Inserting data into patient_registration table 
				PatientRegistration patientRegistration = mapper.map(patientRegistrationDto,PatientRegistration.class,
							"PatientRegistrationDto_to_PatientRegistration");
				patientRegistrationDto.setPatientId((Integer) session.save(patientRegistration));
//				patientRegistration=(PatientRegistration) session.save(patientRegistration);
//				patientRegistrationDto.setPatientId(patientRegistration.getPatientId());
				//----->
			tr.commit();
		return new Response(SUCCESS, null, null, null, patientRegistrationDto);
	}catch (HibernateException he) {
		he.printStackTrace();
	} catch (Exception e) {
		e.printStackTrace();
	}
	return new Response(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response savePatientDetails(PatientRegistrationDto patientRegistrationDto) throws ApplicationException {
		try{	
			Session session=sessionFactory.openSession();
			Transaction tr=session.beginTransaction();
				PatientDetails patientDetails = mapper.map(patientRegistrationDto,PatientDetails.class,
						"PatientRegistrationDto_to_PatientDetails");
			session.save(patientDetails);
			tr.commit();
			return new Response(SUCCESS, SUCCESS_CODE, COMMON_SUCCESS, null, null);
		}catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}
	
	@Override
	public Response getPatientDetailsById(Integer patientId) throws ApplicationException {
		try {
			PatientDetailsDto patientDetailsDto = (PatientDetailsDto) sessionFactory.getCurrentSession()
					.getNamedQuery("GET_PATIENT_DETAILS_BY_ID")
					.setInteger("patientId", patientId)
					.setResultTransformer(Transformers.aliasToBean(PatientDetailsDto.class))
					.uniqueResult();
			return new Response<>(SUCCESS, SUCCESS_CODE, null, null, patientDetailsDto);
		}catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response getPatientDetais(PatientRegistrationDto patientRegistrationDto) throws ApplicationException {
		try {
			List<PatientRegistrationDto> getPatientDetaisList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_PATIENT_DETAILS")
					.setInteger("orgId", patientRegistrationDto.getOrganizationId())
					.setInteger("unitId", patientRegistrationDto.getUnitId())
					.setResultTransformer(Transformers.aliasToBean(PatientRegistrationDto.class)).list();
			return new Response<>(SUCCESS, SUCCESS_CODE, null, getPatientDetaisList, null);
		}catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response checkUniquePatient(PatientRegistrationDto patientRegistrationDto)
			throws ApplicationException {
		try {
			List<PatientRegistrationDto> getPatientDetaisList;
			if(patientRegistrationDto.getRegistrationTypeId()==5)
			{
				getPatientDetaisList = sessionFactory.getCurrentSession()
						.getNamedQuery("CHECK_UNIQUE_PATIENT")
						.setInteger("orgId", patientRegistrationDto.getOrganizationId())
						.setInteger("unitId", patientRegistrationDto.getUnitId())
						.setInteger("genderId", patientRegistrationDto.getGenderId())
						.setDate("dob", GlobalCommonDateUtils.getDate(patientRegistrationDto.getDob(),"dd-M-yyyy"))
						.setString("mobileNumber", patientRegistrationDto.getMobileNumber())
						.setResultTransformer(Transformers.aliasToBean(PatientDetailsDto.class)).list();
				return new Response<>(SUCCESS, SUCCESS_CODE, null, getPatientDetaisList, null);
			}
			
			//<-----If the patient is converting from OTC to normal the condition for checking unique patient will be slight different 
			if(patientRegistrationDto.getRegistrationTypeId()==3)
			{
				
				StringBuilder finalQuery=new StringBuilder(sessionFactory.getCurrentSession()
						.getNamedQuery("CHECK_UNIQUE_PATIENT").getQueryString());
				finalQuery.append(" AND pt.patientId!="+patientRegistrationDto.getPreviousId());
				
				Query query=sessionFactory.openSession().createQuery(finalQuery.toString());
				getPatientDetaisList = query
						.setInteger("orgId", patientRegistrationDto.getOrganizationId())
						.setInteger("unitId", patientRegistrationDto.getUnitId())
						.setInteger("genderId", patientRegistrationDto.getGenderId())
						.setDate("dob", GlobalCommonDateUtils.getDate(patientRegistrationDto.getDob(),"dd-M-yyyy"))
						.setString("mobileNumber", patientRegistrationDto.getMobileNumber())
						.setResultTransformer(Transformers.aliasToBean(PatientDetailsDto.class)).list();
			}
			//----->
			//<----- For updating the patient record
			else if(patientRegistrationDto.getPatientId()>0)
			{
				Session session=sessionFactory.openSession();
					StringBuilder finalQuery=new StringBuilder(session
						.getNamedQuery("CHECK_UNIQUE_PATIENT").getQueryString());
				
					finalQuery.append(" AND pt.patientId!="+patientRegistrationDto.getPatientId());
				
				Query query=sessionFactory.openSession().createQuery(finalQuery.toString());
				getPatientDetaisList = query
						.setInteger("orgId", patientRegistrationDto.getOrganizationId())
						.setInteger("unitId", patientRegistrationDto.getUnitId())
						.setInteger("genderId", patientRegistrationDto.getGenderId())
						.setDate("dob", GlobalCommonDateUtils.getDate(patientRegistrationDto.getDob(),"dd-M-yyyy"))
						.setString("mobileNumber", patientRegistrationDto.getMobileNumber())
						.setResultTransformer(Transformers.aliasToBean(PatientDetailsDto.class)).list();
			}
			//----->
			else{
			
			getPatientDetaisList = sessionFactory.getCurrentSession()
					.getNamedQuery("CHECK_UNIQUE_PATIENT")
					.setInteger("orgId", patientRegistrationDto.getOrganizationId())
					.setInteger("unitId", patientRegistrationDto.getUnitId())
					.setInteger("genderId", patientRegistrationDto.getGenderId())
					.setDate("dob", GlobalCommonDateUtils.getDate(patientRegistrationDto.getDob(),"dd-M-yyyy"))
					.setString("mobileNumber", patientRegistrationDto.getMobileNumber())
					.setResultTransformer(Transformers.aliasToBean(PatientDetailsDto.class)).list();
			}
			return new Response<>(SUCCESS, SUCCESS_CODE, null, getPatientDetaisList, null);
		}catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Long getNextValPatientId() throws ApplicationException {
		try {
			Session session = sessionFactory.openSession();
				SQLQuery query = session.createSQLQuery("select nextval('patient.patient_registration_seq')");
				Long id = ((BigInteger) query.uniqueResult()).longValue();

				SQLQuery query2 = session.createSQLQuery("select setval('patient.patient_registration_seq'," + (id-1) + ")");
				query2.uniqueResult();

				return id;
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Response patientSearchOTC(PatientRegistrationDto patientRegistrationDto) throws ApplicationException {
		try {
			Session session = sessionFactory.openSession();
			StringBuilder stringQuery=new StringBuilder(session.getNamedQuery("GET_PATIENT_DATA").getQueryString());
			StringBuilder resQuery = this.generateQueryForOTCSearch(stringQuery,patientRegistrationDto);
			Query actualQuery = session.createQuery(resQuery.toString());
			List<PatientRegistrationDto> unknownPatientRegistrationDtosList = actualQuery
					.setInteger("orgId", patientRegistrationDto.getOrganizationId())
					.setInteger("unitId", patientRegistrationDto.getUnitId())
					.setResultTransformer(Transformers.aliasToBean(PatientRegistrationDto.class)).list();
			return new Response(SUCCESS, null, null, unknownPatientRegistrationDtosList, null);

		} catch (HibernateException he) {
			he.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
	}

	private StringBuilder generateQueryForOTCSearch(StringBuilder stringQuery,
			PatientRegistrationDto patientRegistrationDto) {
		try{
			stringQuery.append(" AND patient.registrationTypeId="+patientRegistrationDto.getRegistrationTypeId());
			
			if(patientRegistrationDto.getUhidNumber() != null && !patientRegistrationDto.getUhidNumber().isEmpty())
				stringQuery.append(" AND lower(patient.uhidNumber) LIKE concat('%',LOWER('"+patientRegistrationDto.getUhidNumber()+"'),'%') ");
			
			if(patientRegistrationDto.getPatientName() != null && !patientRegistrationDto.getPatientName().isEmpty())
				stringQuery.append(" AND lower(concat(coalesce(patient.firstName),coalesce(patient.lastName))) LIKE concat('%',lower('"+patientRegistrationDto.getPatientName()+"'),'%') ");
			
			if(patientRegistrationDto.getGenderId() != null)
				stringQuery.append(" AND patient.genderId="+patientRegistrationDto.getGenderId()+" ");
			
			if(patientRegistrationDto.getMobileNumber() != null && !patientRegistrationDto.getMobileNumber().isEmpty())
				stringQuery.append(" AND patient.mobileNumber='"+patientRegistrationDto.getMobileNumber()+"' ");
			
			if(patientRegistrationDto.getDob() != null && !patientRegistrationDto.getDob().isEmpty())
				stringQuery.append(" AND patient.dob='"+GlobalCommonDateUtils.getDate(patientRegistrationDto.getDob(),"dd-M-yyyy")+"' ");
			
			if(patientRegistrationDto.getIdentificationNumber() != null && !patientRegistrationDto.getIdentificationNumber().isEmpty())
				stringQuery.append(" AND patient.identificationNumber='"+patientRegistrationDto.getIdentificationNumber()+"' ");
			
			if(patientRegistrationDto.getIdentificationTypeId() != null)
				stringQuery.append(" AND patient.identificationTypeId='"+patientRegistrationDto.getIdentificationTypeId()+"' ");
			
			return stringQuery;
		}catch(Exception e){
			e.printStackTrace();
		}
		return stringQuery;
	}

	@Override
	public Response patientSearchER(PatientRegistrationDto patientRegistrationDto) throws ApplicationException {
		try {
			Session session = sessionFactory.openSession();
			StringBuilder stringQuery=new StringBuilder(session.getNamedQuery("GET_UNKNOWN_PATIENT_LIST").getQueryString());
			StringBuilder resQuery = this.generateQueryForERSearch(stringQuery,patientRegistrationDto);
			SQLQuery actualQuery = session.createSQLQuery(resQuery.toString());
			List<UnknownPatientRegistrationDto> unknownPatientRegistrationDtosList = actualQuery
					.setInteger("orgId", patientRegistrationDto.getOrganizationId())
					.setInteger("unitId", patientRegistrationDto.getUnitId())
					.setResultTransformer(Transformers.aliasToBean(UnknownPatientRegistrationDto.class)).list();
			return new Response(SUCCESS, null, null, unknownPatientRegistrationDtosList, null);

		} catch (HibernateException he) {
			he.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR,COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

	private StringBuilder generateQueryForERSearch(StringBuilder query,PatientRegistrationDto patientRegistrationDto) 
	{
		try{
			if(patientRegistrationDto.getUhidNumber() != null && !patientRegistrationDto.getUhidNumber().isEmpty())
				query.append(" AND lower(patient.t_uhid) LIKE concat('%',LOWER('"+patientRegistrationDto.getUhidNumber()+"'),'%')");
			
			if(patientRegistrationDto.getPatientName() != null && !patientRegistrationDto.getPatientName().isEmpty())
				query.append(" AND lower(coalesce(patient.patient_name,'')) LIKE concat('%',lower('"+patientRegistrationDto.getPatientName()+"'),'%')");
			
			if(patientRegistrationDto.getGenderId() != null)
				query.append(" AND patient.gender_id="+patientRegistrationDto.getGenderId());
			
			if(patientRegistrationDto.getMobileNumber() != null && !patientRegistrationDto.getMobileNumber().isEmpty())
				query.append(" AND patient.mobile='"+patientRegistrationDto.getMobileNumber()+"'");
		
			return query;
		}catch(Exception e){
			e.printStackTrace();
		}
		return query;
		
	}

	@Override
	public Response patientSearchNormal(PatientRegistrationDto patientRegistrationDto) throws ApplicationException {
		try {
			Session session = sessionFactory.openSession();
			StringBuilder stringQuery=new StringBuilder(session.getNamedQuery("GET_PATIENT_DATA").getQueryString());
			StringBuilder resQuery = this.generateQueryForNormalSearch(stringQuery,patientRegistrationDto);
			resQuery.append(" AND patient.registrationTypeId!=2");
			Query actualQuery = session.createQuery(stringQuery.toString());
			List<PatientRegistrationDto> patientRegistrationsList = actualQuery
					.setInteger("orgId", patientRegistrationDto.getOrganizationId())
					.setInteger("unitId", patientRegistrationDto.getUnitId())
					.setResultTransformer(Transformers.aliasToBean(PatientRegistrationDto.class)).list();
			return new Response(SUCCESS, null, null, patientRegistrationsList, null);

		} catch (HibernateException he) {
			he.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
	}
	
	private StringBuilder generateQueryForNormalSearch(StringBuilder stringQuery,
			PatientRegistrationDto patientRegistrationDto) {
		try{
			stringQuery.append(" AND patient.registrationTypeId="+patientRegistrationDto.getRegistrationTypeId());
			
			if(patientRegistrationDto.getUhidNumber() != null && !patientRegistrationDto.getUhidNumber().isEmpty())
				stringQuery.append(" AND lower(patient.uhidNumber) LIKE concat('%',LOWER('"+patientRegistrationDto.getUhidNumber()+"'),'%') ");
			
			if(patientRegistrationDto.getPatientName() != null && !patientRegistrationDto.getPatientName().isEmpty())
				stringQuery.append(" AND lower(concat(coalesce(patient.firstName),coalesce(patient.lastName))) LIKE concat('%',lower('"+patientRegistrationDto.getPatientName()+"'),'%') ");
			
			if(patientRegistrationDto.getGenderId() != null)
				stringQuery.append(" AND patient.genderId="+patientRegistrationDto.getGenderId()+" ");
			
			if(patientRegistrationDto.getMobileNumber() != null && !patientRegistrationDto.getMobileNumber().isEmpty())
				stringQuery.append(" AND patient.mobileNumber='"+patientRegistrationDto.getMobileNumber()+"' ");
			
			if(patientRegistrationDto.getDob() != null && !patientRegistrationDto.getDob().isEmpty())
				stringQuery.append(" AND patient.dob='"+GlobalCommonDateUtils.getDate(patientRegistrationDto.getDob(),"dd-M-yyyy")+"' ");
			
			if(patientRegistrationDto.getIdentificationNumber() != null && !patientRegistrationDto.getIdentificationNumber().isEmpty())
				stringQuery.append(" AND patient.identificationNumber='"+patientRegistrationDto.getIdentificationNumber()+"' ");
			
			if(patientRegistrationDto.getIdentificationTypeId() != null)
				stringQuery.append(" AND patient.identificationTypeId='"+patientRegistrationDto.getIdentificationTypeId()+"' ");
			
			return stringQuery;
		}catch(Exception e){
			e.printStackTrace();
		}
		return stringQuery;
	}
	
	@Override
	public Response patientSearchNormalByPatientId(PatientRegistrationDto patientRegistrationDto) throws ApplicationException {
		try {
			Session session = sessionFactory.openSession();
			StringBuilder stringQuery=new StringBuilder(session.getNamedQuery("GET_PATIENT_DATA_FOR_UPDATE").getQueryString());
			stringQuery.append(" AND patient.registrationTypeId!=2");
			stringQuery.append(" AND patient.patientId="+patientRegistrationDto.getPatientId());
			Query actualQuery = session.createQuery(stringQuery.toString());
			List<PatientRegistrationDto> patientRegistrationsList = actualQuery
					.setInteger("orgId", patientRegistrationDto.getOrganizationId())
					.setInteger("unitId", patientRegistrationDto.getUnitId())
					.setResultTransformer(Transformers.aliasToBean(PatientRegistrationDto.class)).list();
			return new Response(SUCCESS, null, null, patientRegistrationsList, null);

		} catch (HibernateException he) {
			he.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response updatePatient(PatientRegistrationDto patientRegistrationDto) throws ApplicationException {
		try{
		Session session=sessionFactory.getCurrentSession();
			
			PatientRegistration patientRegistration = mapper.map(patientRegistrationDto,PatientRegistration.class,
					"PatientRegistrationDto_to_PatientRegistration");
			session.update(patientRegistration);
			
			PatientDetails patientDetails = mapper.map(patientRegistrationDto,PatientDetails.class,
						"PatientRegistrationDto_to_PatientDetails");
			patientDetails.setPatientDetailsId(patientRegistrationDto.getPatientDetailsId());
			session.update(patientDetails);
			
		return new Response(SUCCESS, null, COMMON_UPDATE, null, null);
		} catch (HibernateException he) {
			he.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response savePatientRegistrationLog(PatientRegistrationDto patientRegistrationDto) throws ApplicationException {
		try{	
			Session session=sessionFactory.openSession();
			Transaction tr=session.beginTransaction();
				PatientRegistrationLog patientRegistrationLog = mapper.map(patientRegistrationDto,PatientRegistrationLog.class,
						"PatientRegistrationDto_to_PatientRegistrationLog");
				session.save(patientRegistrationLog);
			tr.commit();
			return new Response(SUCCESS, SUCCESS_CODE, COMMON_SUCCESS, null, null);
		}catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response patientDetailsByIdForBilling(int patientId) throws ApplicationException {
		try{
			List<PatientSearchBillRes> listPatientSearchBillRes = sessionFactory.getCurrentSession().getNamedQuery("GET_PATIENT_DETAILS_BY_ID_FOR_BILLING_SEARCH").setInteger("patientId", patientId).setResultTransformer(Transformers.aliasToBean(PatientSearchBillRes.class)).list();
			return new Response<>(SUCCESS, SUCCESS_CODE, null, listPatientSearchBillRes, null);
		}catch (HibernateException he) {
			he.printStackTrace();
			throw he;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public Response getPatientByIdForSync(Integer recordId) throws ApplicationException {
		try{
			List<PatientMasterDto> patientDetailsDtosList=sessionFactory.getCurrentSession()
					.getNamedQuery("FORSYNC_GET_PATIENT_RECORD_BY_ID")
					.setInteger("PatientId",recordId)
					.setResultTransformer(Transformers.aliasToBean(PatientMasterDto.class)).list();
			return new Response(SUCCESS,SUCCESS_CODE,null,patientDetailsDtosList,null);
			   
		}catch(HibernateException he){
			he.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR,ERROR_CODE,null,null,null);
}

	@Override
	public Response patientSearchPre(PatientRegistrationDto patientRegistrationDto) throws ApplicationException {
		try {
			Session session = sessionFactory.openSession();
			StringBuilder stringQuery=new StringBuilder(session.getNamedQuery("GET_PATIENT_DATA").getQueryString());
			StringBuilder resQuery = this.generateQueryForOTCSearch(stringQuery,patientRegistrationDto);
			Query actualQuery = session.createQuery(resQuery.toString());
			List<PatientRegistrationDto> unknownPatientRegistrationDtosList = actualQuery
					.setInteger("orgId", patientRegistrationDto.getOrganizationId())
					.setInteger("unitId", patientRegistrationDto.getUnitId())
					.setResultTransformer(Transformers.aliasToBean(PatientRegistrationDto.class)).list();
			return new Response(SUCCESS, null, null, unknownPatientRegistrationDtosList, null);

		} catch (HibernateException he) {
			he.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);

	}
}
