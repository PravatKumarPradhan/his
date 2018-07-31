package com.param.opd.coversheet.dao;

import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.StatelessSession;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;
import com.param.opd.common.CommonDateUtils;
import com.param.opd.coversheet.dto.AllergyPatientMapperDto;
import com.param.opd.coversheet.model.AllergyPatientMapper;
import com.param.opd.coversheet.service.IAllergyPatientMapperService;

@Repository
@SuppressWarnings({ "rawtypes", "unchecked" })
public class AllergyPatientMapperDaoImpl extends GenericDao<AllergyPatientMapper, Integer> implements IAllergyPatientMapperDao, ICommonConstants {

	public AllergyPatientMapperDaoImpl() {
		super(AllergyPatientMapper.class);
		// TODO Auto-generated constructor stub
	}

	@Autowired
	private IAllergyPatientMapperService iAllergyPatientMapperService;
	
	@Override
	public Response saveAllergyPatientMapper(AllergyPatientMapperDto allergyPatientMapperDto)throws ApplicationException {
		try
		{
			for(int i=0;i<allergyPatientMapperDto.getListOfAllergyPatientMapperDto().size();i++)
			{
				if(allergyPatientMapperDto.getListOfAllergyPatientMapperDto().get(i).getAllergyPatientMapperId() != null && 
						allergyPatientMapperDto.getListOfAllergyPatientMapperDto().get(i).getAllergyPatientMapperId() >0
					)
				{
					AllergyPatientMapper allergyPatientMapper = findById(allergyPatientMapperDto.getListOfAllergyPatientMapperDto().get(i).getAllergyPatientMapperId());
					allergyPatientMapper.setIsReviewedFlag(YES);
					iAllergyPatientMapperService.updateSaveNurseReviewedFlag(allergyPatientMapper);
				}
			
				AllergyPatientMapper allergyPatientMapper = new AllergyPatientMapper();
				
				allergyPatientMapper.setAllergyId(allergyPatientMapperDto.getListOfAllergyPatientMapperDto().get(i).getAllergyId());
				allergyPatientMapper.setAllergyTypeId(allergyPatientMapperDto.getListOfAllergyPatientMapperDto().get(i).getAllergyTypeId());
				allergyPatientMapper.setPatientId(allergyPatientMapperDto.getPatientId());
				
				allergyPatientMapper.setRoleId(allergyPatientMapperDto.getRoleId());
				allergyPatientMapper.setIsReviewedFlag(
						allergyPatientMapperDto.getListOfAllergyPatientMapperDto().get(i).getIsReviewedFlag() == '\0' ? NO :
							allergyPatientMapperDto.getListOfAllergyPatientMapperDto().get(i).getIsReviewedFlag()
						);
				
				allergyPatientMapper.setIsEnterInErrorStatus( allergyPatientMapperDto.getListOfAllergyPatientMapperDto().get(i).getIsEnterInErrorStatus() );
				
				allergyPatientMapper.setEncounterId(allergyPatientMapperDto.getEncounterId());
				
				allergyPatientMapper.setComments(allergyPatientMapperDto.getComments());
				allergyPatientMapper.setCreatedBy(allergyPatientMapperDto.getCreatedBy());
				allergyPatientMapper.setCreatedDate(CommonDateUtils.getDate(allergyPatientMapperDto.getCreatedDate(), "dd-mm-yyyy"));
				allergyPatientMapper.setIsNoKnownAllergies(allergyPatientMapperDto.getListOfAllergyPatientMapperDto().get(i).getIsNoKnownAllergies());
				allergyPatientMapper.setMonth(allergyPatientMapperDto.getListOfAllergyPatientMapperDto().get(i).getMonth());
				allergyPatientMapper.setYear(allergyPatientMapperDto.getListOfAllergyPatientMapperDto().get(i).getYear());
				allergyPatientMapper.setNatureOfReaction(allergyPatientMapperDto.getListOfAllergyPatientMapperDto().get(i).getNatureOfReaction());
				allergyPatientMapper.setOrganizationId(allergyPatientMapperDto.getOrganizationId());
				allergyPatientMapper.setRemark(allergyPatientMapperDto.getListOfAllergyPatientMapperDto().get(i).getRemark());
				allergyPatientMapper.setSeverityId(allergyPatientMapperDto.getListOfAllergyPatientMapperDto().get(i).getSeverityId());
				allergyPatientMapper.setStatus(allergyPatientMapperDto.getStatus());
				allergyPatientMapper.setType(allergyPatientMapperDto.getListOfAllergyPatientMapperDto().get(i).getType());
				allergyPatientMapper.setUnitId(allergyPatientMapperDto.getUnitId());
				allergyPatientMapper.setUpdatedBy(allergyPatientMapperDto.getUpdatedBy());
				allergyPatientMapper.setUpdatedDate(CommonDateUtils.getDate(allergyPatientMapperDto.getUpdatedDate(),"dd-mm-yyyy"));
				AllergyPatientMapper historyId=save(allergyPatientMapper);
			}	
			return new Response(SUCCESS, SUCCESS_CODE, COMMON_SUCCESS, null, null);
		}catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, SERVER_ERROR, null, null);
	}

	@Override
	public Response getListOfAllergyPatientMapper(AllergyPatientMapperDto allergyPatientMapperDto)throws ApplicationException {
		try
		{
			StatelessSession statlessSession = sessionFactory.openStatelessSession();
			List<AllergyPatientMapperDto> listAllergyPatientMapperDto = statlessSession.getNamedQuery("GET_LIST_ALLERGY_PATIENT_MAPPER")
					.setInteger("patientId", allergyPatientMapperDto.getPatientId())
					.setInteger("unitId", allergyPatientMapperDto.getUnitId())
					.setInteger("organizationId", allergyPatientMapperDto.getOrganizationId())
					.setResultTransformer(Transformers.aliasToBean(AllergyPatientMapperDto.class)).list();
			statlessSession.close();
			return new Response(SUCCESS, SUCCESS_CODE, null, listAllergyPatientMapperDto, null);
		}catch(HibernateException he) { 
			he.printStackTrace();
			throw new HibernateException(he.getMessage());
		}catch(Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, SERVER_ERROR, null, null);
	}


	@Override
	public Response cancelAllergyPatientMapper(
			AllergyPatientMapperDto allergyPatientMapperDto)
			throws ApplicationException {
		try
		{
			StatelessSession statlessSession = sessionFactory.openStatelessSession();
			int updateCount = statlessSession.getNamedQuery("CANCEL_ALLERGY_PATIENT_MAPPER")
					.setInteger("allergyId", allergyPatientMapperDto.getAllergyId())
					.setInteger("allergyTypeId", allergyPatientMapperDto.getAllergyTypeId())
					.setInteger("patientId", allergyPatientMapperDto.getPatientId())
					.setResultTransformer(Transformers.aliasToBean(AllergyPatientMapperDto.class)).executeUpdate();
			statlessSession.close();
			return new Response(SUCCESS, SUCCESS_CODE, SUCCESS, null, null);
		}catch(HibernateException he) { 
			he.printStackTrace();
			throw new HibernateException(he.getMessage());
		}catch(Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, SERVER_ERROR, null, null);
	}


	@Override
	public Response getOldAllergyPatientMapper(
			AllergyPatientMapperDto allergyPatientMapperDto)
			throws ApplicationException {
		try
		{
			List<AllergyPatientMapperDto> listAllergyPatientMapperDto;
			/*if(allergyPatientMapperDto.getRoleId() == 3)
			{
				Session statlessSession = sessionFactory.getCurrentSession();
				listAllergyPatientMapperDto = statlessSession.getNamedQuery("GET_LIST_ALLERGY_PATIENT_MAPPER_BY_ROLE_ID")
						.setInteger("patientId", allergyPatientMapperDto.getPatientId())
						.setInteger("unitId", allergyPatientMapperDto.getUnitId())
						.setInteger("organizationId", allergyPatientMapperDto.getOrganizationId())
						.setInteger("encounterId", allergyPatientMapperDto.getEncounterId())
						.setInteger("roleId", allergyPatientMapperDto.getRoleId())
						.setResultTransformer(Transformers.aliasToBean(AllergyPatientMapperDto.class)).list();
			}
			else
			{*/
				Session statlessSession = sessionFactory.getCurrentSession();
				listAllergyPatientMapperDto = statlessSession.getNamedQuery("GET_LIST_ALLERGY_PATIENT_MAPPER")
						.setInteger("patientId", allergyPatientMapperDto.getPatientId())
						.setInteger("unitId", allergyPatientMapperDto.getUnitId())
						.setInteger("organizationId", allergyPatientMapperDto.getOrganizationId())
						.setInteger("encounterId", allergyPatientMapperDto.getEncounterId())
						.setResultTransformer(Transformers.aliasToBean(AllergyPatientMapperDto.class)).list();
			/*}
			*/
			return new Response(SUCCESS, SUCCESS_CODE, null, listAllergyPatientMapperDto, null);
		}catch(HibernateException he) { 
			he.printStackTrace();
			throw new HibernateException(he.getMessage());
		}catch(Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, SERVER_ERROR, null, null);
	}


	@Override
	public Response getCurrentAllergyPatientMapper(
			AllergyPatientMapperDto allergyPatientMapperDto)
			throws ApplicationException {
		try
		{
			List<AllergyPatientMapperDto> listAllergyPatientMapperDto;
			if(allergyPatientMapperDto.getRoleId() == 3)
			{
				Session statlessSession = sessionFactory.getCurrentSession();
				listAllergyPatientMapperDto = statlessSession.getNamedQuery("GET_CURRENT_LIST_ALLERGY_PATIENT_MAPPER_BY_ROLE_ID")
						.setInteger("patientId", allergyPatientMapperDto.getPatientId())
						.setInteger("unitId", allergyPatientMapperDto.getUnitId())
						.setInteger("organizationId", allergyPatientMapperDto.getOrganizationId())
						.setInteger("encounterId", allergyPatientMapperDto.getEncounterId())
						.setInteger("roleId", allergyPatientMapperDto.getRoleId())
						.setResultTransformer(Transformers.aliasToBean(AllergyPatientMapperDto.class)).list();
			}
			else
			{
				Session statlessSession = sessionFactory.getCurrentSession();
				listAllergyPatientMapperDto = statlessSession.getNamedQuery("GET_CURRENT_LIST_ALLERGY_PATIENT_MAPPER")
						.setInteger("patientId", allergyPatientMapperDto.getPatientId())
						.setInteger("unitId", allergyPatientMapperDto.getUnitId())
						.setInteger("organizationId", allergyPatientMapperDto.getOrganizationId())
						.setInteger("encounterId", allergyPatientMapperDto.getEncounterId())
						.setResultTransformer(Transformers.aliasToBean(AllergyPatientMapperDto.class)).list();
			}
			
			return new Response(SUCCESS, SUCCESS_CODE, null, listAllergyPatientMapperDto, null);
		}catch(HibernateException he) { 
			he.printStackTrace();
			throw new HibernateException(he.getMessage());
		}catch(Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, SERVER_ERROR, null, null);
	}


	@Override
	public Response updateAllergyPatientMapper(
			AllergyPatientMapperDto allergyPatientMapperDto)
			throws ApplicationException {
		try
		{
			for(int i=0; i<allergyPatientMapperDto.getListOfAllergyPatientMapperDto().size();i++)
			{
				AllergyPatientMapper allergyPatientMapper = findById(allergyPatientMapperDto.getAllergyPatientMapperId());

				if(allergyPatientMapperDto.getRoleId() != null 
						&& allergyPatientMapperDto.getRoleId() == 2 
						&& allergyPatientMapper.getRoleId() != 2 
						&& allergyPatientMapper.getIsReviewedFlag() == 'N'
						)
				{
					iAllergyPatientMapperService.saveAllergyPatientMapper(allergyPatientMapperDto);
				}
				
				allergyPatientMapper.setAllergyId(allergyPatientMapperDto.getListOfAllergyPatientMapperDto().get(i).getAllergyId());
				allergyPatientMapper.setAllergyTypeId(allergyPatientMapperDto.getListOfAllergyPatientMapperDto().get(i).getAllergyTypeId());
				allergyPatientMapper.setPatientId(allergyPatientMapperDto.getPatientId());
				
				allergyPatientMapper.setRoleId(allergyPatientMapperDto.getRoleId());
				allergyPatientMapper.setIsReviewedFlag(
							allergyPatientMapperDto.getRoleId() == 2 && allergyPatientMapper.getRoleId() == 3 ? YES :
								allergyPatientMapperDto.getListOfAllergyPatientMapperDto().get(i).getIsReviewedFlag()
						);
				
				allergyPatientMapper.setIsEnterInErrorStatus( allergyPatientMapperDto.getListOfAllergyPatientMapperDto().get(i).getIsEnterInErrorStatus() );
				allergyPatientMapper.setEncounterId(allergyPatientMapperDto.getEncounterId());
				
				allergyPatientMapper.setComments(allergyPatientMapperDto.getComments());
				allergyPatientMapper.setCreatedBy(allergyPatientMapperDto.getCreatedBy());
				allergyPatientMapper.setCreatedDate(CommonDateUtils.getDate(allergyPatientMapperDto.getCreatedDate(), "dd-mm-yyyy"));
				allergyPatientMapper.setIsNoKnownAllergies(allergyPatientMapperDto.getListOfAllergyPatientMapperDto().get(i).getIsNoKnownAllergies());
				allergyPatientMapper.setMonth(allergyPatientMapperDto.getListOfAllergyPatientMapperDto().get(i).getMonth());
				allergyPatientMapper.setYear(allergyPatientMapperDto.getListOfAllergyPatientMapperDto().get(i).getYear());
				allergyPatientMapper.setNatureOfReaction(allergyPatientMapperDto.getListOfAllergyPatientMapperDto().get(i).getNatureOfReaction());
				allergyPatientMapper.setOrganizationId(allergyPatientMapperDto.getOrganizationId());
				allergyPatientMapper.setRemark(allergyPatientMapperDto.getListOfAllergyPatientMapperDto().get(i).getRemark());
				allergyPatientMapper.setSeverityId(allergyPatientMapperDto.getListOfAllergyPatientMapperDto().get(i).getSeverityId());
				allergyPatientMapper.setStatus(allergyPatientMapperDto.getStatus());
				allergyPatientMapper.setType(allergyPatientMapperDto.getListOfAllergyPatientMapperDto().get(i).getType());
				allergyPatientMapper.setUnitId(allergyPatientMapperDto.getUnitId());
				allergyPatientMapper.setUpdatedBy(allergyPatientMapperDto.getUpdatedBy());
				allergyPatientMapper.setUpdatedDate(CommonDateUtils.getDate(allergyPatientMapperDto.getUpdatedDate(),"dd-mm-yyyy"));
				AllergyPatientMapper historyId=save(allergyPatientMapper);
			}
				return new Response(SUCCESS, SUCCESS_CODE, COMMON_SUCCESS, null, null);
		}catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, SERVER_ERROR, null, null);
	}


	@Override
	public Response updateSaveNurseReviewedFlag(
			AllergyPatientMapper allergyPatientMapper)
			throws ApplicationException {
		try
		{
			save(allergyPatientMapper);
			return new Response(SUCCESS, SUCCESS_CODE, COMMON_SUCCESS, null, null);
		}catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, SERVER_ERROR, null, null);
	}


	@Override
	public Response updateStatusEnterInError(
			AllergyPatientMapperDto allergyPatientMapperDto)
			throws ApplicationException {
		try
		{
			for(int i=0; i<allergyPatientMapperDto.getListOfAllergyPatientMapperDto().size();i++)
			{
				AllergyPatientMapper allergyPatient = findById(allergyPatientMapperDto.getListOfAllergyPatientMapperDto().get(i).getAllergyPatientMapperId());
				
				allergyPatient.setIsEnterInErrorStatus(allergyPatientMapperDto.getListOfAllergyPatientMapperDto().get(i).getIsEnterInErrorStatus());
				allergyPatient.setIsReviewedFlag(allergyPatient.getIsReviewedFlag() == 'N' && 
						allergyPatient.getRoleId() == 3 && allergyPatientMapperDto.getRoleId() == 2 ? YES : allergyPatient.getIsReviewedFlag());
				iAllergyPatientMapperService.updateSaveNurseReviewedFlag(allergyPatient);
				
				if(allergyPatientMapperDto.getRoleId() != null && allergyPatientMapperDto.getRoleId() == 2 && allergyPatient.getRoleId() == 3)
				{
					AllergyPatientMapper allergyPatientMapper = new AllergyPatientMapper();
					
					allergyPatientMapper.setAllergyId(allergyPatientMapperDto.getListOfAllergyPatientMapperDto().get(i).getAllergyId());
					allergyPatientMapper.setAllergyTypeId(allergyPatientMapperDto.getListOfAllergyPatientMapperDto().get(i).getAllergyTypeId());
					allergyPatientMapper.setPatientId(allergyPatientMapperDto.getPatientId());
					
					allergyPatientMapper.setRoleId(allergyPatientMapperDto.getRoleId());
					allergyPatientMapper.setIsReviewedFlag(
							allergyPatientMapperDto.getListOfAllergyPatientMapperDto().get(i).getIsReviewedFlag() == '\0' ? NO :
								allergyPatientMapperDto.getListOfAllergyPatientMapperDto().get(i).getIsReviewedFlag()
							);
					
					allergyPatientMapper.setIsEnterInErrorStatus(NO);
					
					allergyPatientMapper.setEncounterId(allergyPatientMapperDto.getEncounterId());
					
					allergyPatientMapper.setComments(allergyPatientMapperDto.getComments());
					allergyPatientMapper.setCreatedBy(allergyPatientMapperDto.getCreatedBy());
					allergyPatientMapper.setCreatedDate(CommonDateUtils.getDate(allergyPatientMapperDto.getCreatedDate(), "dd-mm-yyyy"));
					allergyPatientMapper.setIsNoKnownAllergies(allergyPatientMapperDto.getListOfAllergyPatientMapperDto().get(i).getIsNoKnownAllergies());
					allergyPatientMapper.setMonth(allergyPatientMapperDto.getListOfAllergyPatientMapperDto().get(i).getMonth());
					allergyPatientMapper.setYear(allergyPatientMapperDto.getListOfAllergyPatientMapperDto().get(i).getYear());
					allergyPatientMapper.setNatureOfReaction(allergyPatientMapperDto.getListOfAllergyPatientMapperDto().get(i).getNatureOfReaction());
					allergyPatientMapper.setOrganizationId(allergyPatientMapperDto.getOrganizationId());
					allergyPatientMapper.setRemark(allergyPatientMapperDto.getListOfAllergyPatientMapperDto().get(i).getRemark());
					allergyPatientMapper.setSeverityId(allergyPatientMapperDto.getListOfAllergyPatientMapperDto().get(i).getSeverityId());
					allergyPatientMapper.setStatus(allergyPatientMapperDto.getStatus());
					allergyPatientMapper.setType(allergyPatientMapperDto.getListOfAllergyPatientMapperDto().get(i).getType());
					allergyPatientMapper.setUnitId(allergyPatientMapperDto.getUnitId());
					allergyPatientMapper.setUpdatedBy(allergyPatientMapperDto.getUpdatedBy());
					allergyPatientMapper.setUpdatedDate(CommonDateUtils.getDate(allergyPatientMapperDto.getUpdatedDate(),"dd-mm-yyyy"));
					AllergyPatientMapper historyId=save(allergyPatientMapper);
				}
			}
				return new Response(SUCCESS, SUCCESS_CODE, COMMON_SUCCESS, null, null);
		}catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, SERVER_ERROR, null, null);
	}

}
