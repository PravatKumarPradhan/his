package com.param.global.dao;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;
import com.param.global.dto.KinDetailsDto;
import com.param.global.dto.MortuaryDto;
import com.param.global.model.KinDetails;

import in.param.exception.ApplicationException;

@Repository
@SuppressWarnings({ "unchecked", "rawtypes" })
public class KinDetailsDaoImpl implements IKinDetailsDao,ICommonConstants{
	
	@Autowired
	SessionFactory sessionFactory;

	@Autowired
	DozerBeanMapper mapper;
	
	@Override
	public Response saveKinDetails(KinDetailsDto kinDetailsDto) throws ApplicationException {
		try {
			KinDetails kinDetails = mapper.map(kinDetailsDto, KinDetails.class, "KinDetailsDto_to_KinDetails");
			Session session = sessionFactory.openSession();
				Transaction tr = session.beginTransaction();
					Integer id=(Integer)session.save(kinDetails);
				tr.commit();
			session.close();
			return new Response(SUCCESS, null, COMMON_SUCCESS, null, id);
		} catch (Exception e) {
			e.printStackTrace();
			return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
		}
	}

	@Override
	public Response getKinDetailsByPatientId(Integer patientId, Integer unitId, Integer orgId) throws ApplicationException {
		try {
			List<KinDetailsDto> listKinDetailsDto = sessionFactory.getCurrentSession().getNamedQuery("GET_KIN_DETAILS_BY_PATIENT_ID")
					.setInteger("patientId", patientId)
					.setInteger("unitId", unitId)
					.setInteger("organizationId", orgId)
					.setResultTransformer(Transformers.aliasToBean(KinDetailsDto.class))
					.list();
			return new Response<>(SUCCESS, SUCCESS_CODE, SUCCESS_MESSAGE, listKinDetailsDto, null);
		}catch (Exception e) {
			e.printStackTrace();
			return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
		}
	}

	@Override
	public Response getKinDetailsById(Integer kinDetailsId) throws ApplicationException {
		try {
			KinDetailsDto kinDetailsDto = (KinDetailsDto) sessionFactory.getCurrentSession()
					.getNamedQuery("GET_KIN_DETAILS_BY_ID")
					.setInteger("kinDetailsId", kinDetailsId)
					.setResultTransformer(Transformers.aliasToBean(KinDetailsDto.class))
					.uniqueResult();
			return new Response<>(SUCCESS, SUCCESS_CODE, null, null, kinDetailsDto);
		}catch (Exception e) {
			e.printStackTrace();
			return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
		}
	}

	@Override
	public Response changeKinDetailsStatus(Integer kinDetailsId, Character status) throws ApplicationException {
		try {
			sessionFactory.getCurrentSession()
			.getNamedQuery("CHANGE_KIN_DETAILS_STATUS")
			.setInteger("kinDetailsId", kinDetailsId)
			.setCharacter("status", status)
			.executeUpdate();
			return new  Response<>(SUCCESS, SUCCESS_CODE, COMMON_UPDATE, null, null);
		}catch (Exception e) {
			e.printStackTrace();
			return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
		}
	}

	@Override
	public Response getkinDeatilsForMortuaryAllocation(MortuaryDto mortuaryDto) throws ApplicationException {
		try {
			Session session=sessionFactory.openSession();
			
			//String stringQuery=session.getNamedQuery("GET_KIN_DETAILS_BY_PATIENT_ID").getQueryString();
			StringBuilder finalQuery=new StringBuilder("SELECT kin.kin_id as \"kinDetailsId\", "
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
					+ "kin.address as \"address\", "
					+ "kin.patient_id as \"patientId\", "
					+ "kin.t_patient_id as \"tPatientId\", "
					+ "kin.d_patient_id as \"dPatientId\" "
					+ "FROM public.m_kin_details kin "
					+ "INNER JOIN  public.m_prefix_master prefix ON  prefix.prefix_id = kin.prefix_id "
					+ "INNER JOIN  public.m_relation_master relation ON  relation.relation_id = kin.relation_id "
					+ "INNER JOIN  adt.m_identification_master id ON id.identification_id = kin.identification_id "
					+ "WHERE kin.organization_id=:organizationId "
					+ "AND kin.unit_id=:unitId "
					+ "AND kin.status='A' ");
			List<KinDetailsDto> listKinDetailsDto=new ArrayList<>();
			if(mortuaryDto.getPatientId()!=null)
			{
				finalQuery.append(" AND kin.patient_id=:patientId ");
			
				listKinDetailsDto = session.createSQLQuery(finalQuery.toString())
					.setInteger("patientId", mortuaryDto.getPatientId())
					.setInteger("unitId", mortuaryDto.getOrganizationId())
					.setInteger("organizationId", mortuaryDto.getUnitId())
					.setResultTransformer(Transformers.aliasToBean(KinDetailsDto.class))
					.list();
			}
			if(mortuaryDto.getdPatientId()!=null)
			{
				finalQuery.append(" AND kin.d_patient_id=:dPatientId ");
			
				listKinDetailsDto = session.createSQLQuery(finalQuery.toString())
					.setInteger("dPatientId", mortuaryDto.getdPatientId())
					.setInteger("unitId",mortuaryDto.getUnitId())
					.setInteger("organizationId", mortuaryDto.getOrganizationId())
					.setResultTransformer(Transformers.aliasToBean(KinDetailsDto.class))
					.list();
			}
			if(mortuaryDto.gettPatientId()!=null)
			{
				finalQuery.append(" AND kin.t_patient_id=:tPatientId ");
			
				listKinDetailsDto = session.createSQLQuery(finalQuery.toString())
					.setInteger("dPatientId", mortuaryDto.gettPatientId())
					.setInteger("unitId",mortuaryDto.getUnitId())
					.setInteger("organizationId", mortuaryDto.getOrganizationId())
					.setResultTransformer(Transformers.aliasToBean(KinDetailsDto.class))
					.list();
			}
			
			return new Response<>(SUCCESS, SUCCESS_CODE, SUCCESS_MESSAGE, listKinDetailsDto, null);
		}catch (Exception e) {
			e.printStackTrace();
			return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
		}
	}

	@Override
	public Response updateKinDetails(KinDetailsDto kinDetailsDto) throws ApplicationException {
		try {
			KinDetails kinDetails = mapper.map(kinDetailsDto, KinDetails.class, "KinDetailsDto_to_KinDetails");
			sessionFactory.getCurrentSession().update(kinDetails);
			return new Response<>(SUCCESS, SUCCESS_CODE, COMMON_UPDATE, null, kinDetails);
		}catch (Exception e) {
			e.printStackTrace();
			return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
		}
	}
	
}
