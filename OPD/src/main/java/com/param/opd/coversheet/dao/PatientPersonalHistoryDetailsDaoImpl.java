package com.param.opd.coversheet.dao;

import java.util.List;

import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.StatelessSession;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;
import com.param.opd.common.CommonDateUtils;
import com.param.opd.coversheet.dto.ComplaintAppointmentDto;
import com.param.opd.coversheet.dto.PatientPersonalHistoryDetailsDto;
import com.param.opd.coversheet.model.ComplaintAppointmentMapper;
import com.param.opd.coversheet.model.PatientPersonalHistoryDetails;
import com.param.opd.coversheet.service.IPatientPersonalHistoryDetailsService;

@Repository
@SuppressWarnings({ "unused", "unchecked" })
public class PatientPersonalHistoryDetailsDaoImpl extends GenericDao<PatientPersonalHistoryDetails, Integer> implements IPatientPersonalHistoryDetailsDao, ICommonConstants{

	public PatientPersonalHistoryDetailsDaoImpl() {
		super(PatientPersonalHistoryDetails.class);
		// TODO Auto-generated constructor stub
	}

	@Autowired
	private IPatientPersonalHistoryDetailsService iPatientPersonalHistoryDetailsService;
	
	@SuppressWarnings("rawtypes")
	@Override
	public Response savePatientPersonalHistoryDetails(PatientPersonalHistoryDetailsDto patientPersonalHistoryDetailsDto)throws ApplicationException {
		try
		{
			for(int i=0;i<patientPersonalHistoryDetailsDto.getListOfPatientPersonalHistoryDtos().size();i++)
			{
				if(patientPersonalHistoryDetailsDto.getListOfPatientPersonalHistoryDtos().get(i).getPersonalHistoryDetailsId() != null && patientPersonalHistoryDetailsDto.getListOfPatientPersonalHistoryDtos().get(i).getPersonalHistoryDetailsId()>0)
				{
					PatientPersonalHistoryDetails patientPersonalHistoryDetails = findById(patientPersonalHistoryDetailsDto.getListOfPatientPersonalHistoryDtos().get(i).getPersonalHistoryDetailsId());
					patientPersonalHistoryDetails.setIsReviewedFlag(YES);
					iPatientPersonalHistoryDetailsService.updateSaveNurseReviewedFlag(patientPersonalHistoryDetails);
				}
				
				PatientPersonalHistoryDetails patientPersonalHistoryDetails = new PatientPersonalHistoryDetails();
				patientPersonalHistoryDetails.setDescription(patientPersonalHistoryDetailsDto.getListOfPatientPersonalHistoryDtos().get(i).getDescription());
				patientPersonalHistoryDetails.setIsNoSignificantHistoryStatus(patientPersonalHistoryDetailsDto.getListOfPatientPersonalHistoryDtos().get(i).getIsNoSignificantHistoryStatus());
				patientPersonalHistoryDetails.setRemark(patientPersonalHistoryDetailsDto.getListOfPatientPersonalHistoryDtos().get(i).getRemark());
				
				patientPersonalHistoryDetails.setRoleId(patientPersonalHistoryDetailsDto.getRoleId());
				
				patientPersonalHistoryDetails.setIsReviewedFlag(
						patientPersonalHistoryDetailsDto.getListOfPatientPersonalHistoryDtos().get(i).getIsReviewedFlag() == '\0'? NO :
						patientPersonalHistoryDetailsDto.getListOfPatientPersonalHistoryDtos().get(i).getIsReviewedFlag());
				
				patientPersonalHistoryDetails.setIsEnterInErrorStatus(patientPersonalHistoryDetailsDto.getListOfPatientPersonalHistoryDtos().get(i).getIsEnterInErrorStatus());
				patientPersonalHistoryDetails.setCreatedDate(CommonDateUtils.getDate(patientPersonalHistoryDetailsDto.getCreatedDate(), "dd-MM-yyyy"));
				patientPersonalHistoryDetails.setEncounterId(patientPersonalHistoryDetailsDto.getEncounterId());
				patientPersonalHistoryDetails.setCreatedBy(patientPersonalHistoryDetailsDto.getCreatedBy());
				patientPersonalHistoryDetails.setUpdatedBy(patientPersonalHistoryDetailsDto.getUpdatedBy());
				patientPersonalHistoryDetails.setUpdatedDate(CommonDateUtils.getDate(patientPersonalHistoryDetailsDto.getUpdatedDate(), "dd-MM-yyyy"));
				patientPersonalHistoryDetails.setStatus(patientPersonalHistoryDetailsDto.getStatus());
				patientPersonalHistoryDetails.setOrganizationId(patientPersonalHistoryDetailsDto.getOrganizationId());
				patientPersonalHistoryDetails.setUnitId(patientPersonalHistoryDetailsDto.getUnitId());
				patientPersonalHistoryDetails.setPatientId(patientPersonalHistoryDetailsDto.getPatientId());
				
				PatientPersonalHistoryDetails historyId=save(patientPersonalHistoryDetails);
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
	public Response getListOfOldPatientPersonalHistoryDetails(PatientPersonalHistoryDetailsDto patientPersonalHistoryDetailsDto)throws ApplicationException {
		try
		{
			List<PatientPersonalHistoryDetailsDto> listPatientPersonalHistoryDetailsDto;
			
			/*if(patientPersonalHistoryDetailsDto.getRoleId() == 3)
			{
				StatelessSession statlessSession = sessionFactory.openStatelessSession();
				listPatientPersonalHistoryDetailsDto = statlessSession.getNamedQuery("GET_PATIENT_PERSONAL_HISTORY_DEATILS_BY_ROLE_ID")
						.setInteger("roleId", patientPersonalHistoryDetailsDto.getRoleId())
						.setInteger("patientId", patientPersonalHistoryDetailsDto.getPatientId())
						.setInteger("unitId", patientPersonalHistoryDetailsDto.getUnitId())
						.setInteger("encounterId", patientPersonalHistoryDetailsDto.getEncounterId())
						.setInteger("organizationId", patientPersonalHistoryDetailsDto.getOrganizationId())
						.setResultTransformer(Transformers.aliasToBean(PatientPersonalHistoryDetailsDto.class)).list();
				statlessSession.close();
			}else
			{*/
				StatelessSession statlessSession = sessionFactory.openStatelessSession();
				listPatientPersonalHistoryDetailsDto = statlessSession.getNamedQuery("GET_PATIENT_PERSONAL_HISTORY_DEATILS")
						.setInteger("patientId", patientPersonalHistoryDetailsDto.getPatientId())
						.setInteger("unitId", patientPersonalHistoryDetailsDto.getUnitId())
						.setInteger("encounterId", patientPersonalHistoryDetailsDto.getEncounterId())
						.setInteger("organizationId", patientPersonalHistoryDetailsDto.getOrganizationId())
						.setResultTransformer(Transformers.aliasToBean(PatientPersonalHistoryDetailsDto.class)).list();
				statlessSession.close();
			/*}
		*/
			return new Response(SUCCESS, SUCCESS_CODE, null, listPatientPersonalHistoryDetailsDto, null);
		}catch(HibernateException he) { 
			he.printStackTrace();
			throw new HibernateException(he.getMessage());
		}catch(Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, SERVER_ERROR, null, null);
	}

	@Override
	public Response getListOfCurrentPatientPersonalHistoryDetails(
			PatientPersonalHistoryDetailsDto patientPersonalHistoryDetailsDto)
			throws ApplicationException {
		try
		{
			
			List<PatientPersonalHistoryDetailsDto> listPatientPersonalHistoryDetailsDto;
			
			if(patientPersonalHistoryDetailsDto.getRoleId() == 3)
			{
				StatelessSession statlessSession = sessionFactory.openStatelessSession();
				listPatientPersonalHistoryDetailsDto = statlessSession.getNamedQuery("GET_CURRENT_PATIENT_PERSONAL_HISTORY_DEATILS_BY_ROLE_ID")
						.setInteger("roleId", patientPersonalHistoryDetailsDto.getRoleId())
						.setInteger("patientId", patientPersonalHistoryDetailsDto.getPatientId())
						.setInteger("unitId", patientPersonalHistoryDetailsDto.getUnitId())
						.setInteger("encounterId", patientPersonalHistoryDetailsDto.getEncounterId())
						.setInteger("organizationId", patientPersonalHistoryDetailsDto.getOrganizationId())
						.setResultTransformer(Transformers.aliasToBean(PatientPersonalHistoryDetailsDto.class)).list();
				statlessSession.close();
			}else
			{
				StatelessSession statlessSession = sessionFactory.openStatelessSession();
				listPatientPersonalHistoryDetailsDto = statlessSession.getNamedQuery("GET_CURRENT_PATIENT_PERSONAL_HISTORY_DEATILS")
						.setInteger("patientId", patientPersonalHistoryDetailsDto.getPatientId())
						.setInteger("unitId", patientPersonalHistoryDetailsDto.getUnitId())
						.setInteger("encounterId", patientPersonalHistoryDetailsDto.getEncounterId())
						.setInteger("organizationId", patientPersonalHistoryDetailsDto.getOrganizationId())
						.setResultTransformer(Transformers.aliasToBean(PatientPersonalHistoryDetailsDto.class)).list();
				statlessSession.close();
			}
			
			return new Response(SUCCESS, SUCCESS_CODE, null, listPatientPersonalHistoryDetailsDto, null);
		}catch(HibernateException he) { 
			he.printStackTrace();
			throw new HibernateException(he.getMessage());
		}catch(Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, SERVER_ERROR, null, null);
	}

	@Override
	public Response updatePatientPersonalHistoryDetails(PatientPersonalHistoryDetailsDto patientPersonalHistoryDetailsDto)
			throws ApplicationException {
		try
		{
			for(int i=0;i<patientPersonalHistoryDetailsDto.getListOfPatientPersonalHistoryDtos().size();i++)
			{
				
				Session statlessSession = sessionFactory.getCurrentSession();
				PatientPersonalHistoryDetails patientPersonalHistoryDetails = findById(patientPersonalHistoryDetailsDto.getListOfPatientPersonalHistoryDtos().get(i).getPersonalHistoryDetailsId());
				
				if(patientPersonalHistoryDetailsDto.getRoleId() != null 
						&& patientPersonalHistoryDetailsDto.getRoleId() == 2 
						&& patientPersonalHistoryDetails.getRoleId() != 2 
						&& patientPersonalHistoryDetails.getIsReviewedFlag() == 'N')
				{
					iPatientPersonalHistoryDetailsService.savePatientPersonalHistoryDetails(patientPersonalHistoryDetailsDto);
				}
				
				patientPersonalHistoryDetails.setDescription(patientPersonalHistoryDetailsDto.getListOfPatientPersonalHistoryDtos().get(i).getDescription());
				patientPersonalHistoryDetails.setIsNoSignificantHistoryStatus(patientPersonalHistoryDetailsDto.getListOfPatientPersonalHistoryDtos().get(i).getIsNoSignificantHistoryStatus());
				patientPersonalHistoryDetails.setRemark(patientPersonalHistoryDetailsDto.getListOfPatientPersonalHistoryDtos().get(i).getRemark());
				patientPersonalHistoryDetails.setRoleId(patientPersonalHistoryDetails.getRoleId());
				
				patientPersonalHistoryDetails.setIsReviewedFlag(patientPersonalHistoryDetailsDto.getRoleId() == 2 
						&& patientPersonalHistoryDetails.getRoleId() == 3 ? YES : patientPersonalHistoryDetailsDto.getListOfPatientPersonalHistoryDtos().get(i).getIsReviewedFlag());
				patientPersonalHistoryDetails.setIsEnterInErrorStatus(patientPersonalHistoryDetailsDto.getListOfPatientPersonalHistoryDtos().get(i).getIsEnterInErrorStatus());
				
				patientPersonalHistoryDetails.setCreatedDate(CommonDateUtils.getDate(patientPersonalHistoryDetailsDto.getCreatedDate(), "dd-MM-yyyy"));
				patientPersonalHistoryDetails.setEncounterId(patientPersonalHistoryDetailsDto.getEncounterId());
				patientPersonalHistoryDetails.setCreatedBy(patientPersonalHistoryDetailsDto.getCreatedBy());
				patientPersonalHistoryDetails.setUpdatedBy(patientPersonalHistoryDetailsDto.getUpdatedBy());
				patientPersonalHistoryDetails.setUpdatedDate(CommonDateUtils.getDate(patientPersonalHistoryDetailsDto.getUpdatedDate(), "dd-MM-yyyy"));
				patientPersonalHistoryDetails.setStatus(patientPersonalHistoryDetailsDto.getStatus());
				patientPersonalHistoryDetails.setOrganizationId(patientPersonalHistoryDetailsDto.getOrganizationId());
				patientPersonalHistoryDetails.setUnitId(patientPersonalHistoryDetailsDto.getUnitId());
				patientPersonalHistoryDetails.setPatientId(patientPersonalHistoryDetailsDto.getPatientId());
			
				save(patientPersonalHistoryDetails);
				
					
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
			PatientPersonalHistoryDetails patientPersonalHistoryDetails)
			throws ApplicationException {
		try
		{
			save(patientPersonalHistoryDetails);
			return new Response(SUCCESS, SUCCESS_CODE, COMMON_SUCCESS, null, null);
		}catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, SERVER_ERROR, null, null);
	}

	@Override
	public Response updateStatusEnterInError(PatientPersonalHistoryDetailsDto patientPersonalHistoryDetailsDto)
			throws ApplicationException {
		// TODO Auto-generated method stub
		try
		{
			for(int i=0;i<patientPersonalHistoryDetailsDto.getListOfPatientPersonalHistoryDtos().size();i++)
			{
				PatientPersonalHistoryDetails patientPersonalHistory = findById(patientPersonalHistoryDetailsDto.getListOfPatientPersonalHistoryDtos().get(i).getPersonalHistoryDetailsId());
				patientPersonalHistory.setIsEnterInErrorStatus(patientPersonalHistoryDetailsDto.getListOfPatientPersonalHistoryDtos().get(i).getIsEnterInErrorStatus());
				patientPersonalHistory.setIsReviewedFlag(patientPersonalHistory.getIsReviewedFlag() == 'N' && 
														 patientPersonalHistory.getRoleId() == 3 &&
														 patientPersonalHistoryDetailsDto.getRoleId() == 2
														 ? YES : patientPersonalHistory.getIsReviewedFlag());
				iPatientPersonalHistoryDetailsService.updateSaveNurseReviewedFlag(patientPersonalHistory);
				
				if(patientPersonalHistoryDetailsDto.getRoleId() != null && patientPersonalHistoryDetailsDto.getRoleId() == 2 && patientPersonalHistory.getRoleId() == 3)
				{
					PatientPersonalHistoryDetails patientPersonalHistoryDetails = new PatientPersonalHistoryDetails();
					patientPersonalHistoryDetails.setDescription(patientPersonalHistoryDetailsDto.getListOfPatientPersonalHistoryDtos().get(i).getDescription());
					patientPersonalHistoryDetails.setIsNoSignificantHistoryStatus(patientPersonalHistoryDetailsDto.getListOfPatientPersonalHistoryDtos().get(i).getIsNoSignificantHistoryStatus());
					patientPersonalHistoryDetails.setRemark(patientPersonalHistoryDetailsDto.getListOfPatientPersonalHistoryDtos().get(i).getRemark());
					
					patientPersonalHistoryDetails.setRoleId(patientPersonalHistoryDetailsDto.getRoleId());
					
					patientPersonalHistoryDetails.setIsReviewedFlag(
							patientPersonalHistoryDetailsDto.getListOfPatientPersonalHistoryDtos().get(i).getIsReviewedFlag() == '\0'? NO :
							patientPersonalHistoryDetailsDto.getListOfPatientPersonalHistoryDtos().get(i).getIsReviewedFlag());
					
					//patientPersonalHistoryDetails.setIsEnterInErrorStatus(patientPersonalHistoryDetailsDto.getRoleId() == 3? NO : YES);
					patientPersonalHistoryDetails.setIsEnterInErrorStatus(NO);
					patientPersonalHistoryDetails.setCreatedDate(CommonDateUtils.getDate(patientPersonalHistoryDetailsDto.getCreatedDate(), "dd-MM-yyyy"));
					patientPersonalHistoryDetails.setEncounterId(patientPersonalHistoryDetailsDto.getEncounterId());
					patientPersonalHistoryDetails.setCreatedBy(patientPersonalHistoryDetailsDto.getCreatedBy());
					patientPersonalHistoryDetails.setUpdatedBy(patientPersonalHistoryDetailsDto.getUpdatedBy());
					patientPersonalHistoryDetails.setUpdatedDate(CommonDateUtils.getDate(patientPersonalHistoryDetailsDto.getUpdatedDate(), "dd-MM-yyyy"));
					patientPersonalHistoryDetails.setStatus(patientPersonalHistoryDetailsDto.getStatus());
					patientPersonalHistoryDetails.setOrganizationId(patientPersonalHistoryDetailsDto.getOrganizationId());
					patientPersonalHistoryDetails.setUnitId(patientPersonalHistoryDetailsDto.getUnitId());
					patientPersonalHistoryDetails.setPatientId(patientPersonalHistoryDetailsDto.getPatientId());
					
					save(patientPersonalHistoryDetails);
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
