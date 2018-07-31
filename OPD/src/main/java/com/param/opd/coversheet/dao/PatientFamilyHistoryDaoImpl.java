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
import com.param.opd.coversheet.dto.PatientFamilyHistoryDto;
import com.param.opd.coversheet.dto.PatientPersonalHistoryDetailsDto;
import com.param.opd.coversheet.model.PatientFamilyHistory;
import com.param.opd.coversheet.model.PatientPersonalHistoryDetails;
import com.param.opd.coversheet.service.IpatientFamilyHistoryService;

@Repository
@SuppressWarnings({"rawtypes","unchecked"})
public class PatientFamilyHistoryDaoImpl extends GenericDao<PatientFamilyHistory, Integer> implements IPatientFamilyHistoryDao, ICommonConstants {

	
	public PatientFamilyHistoryDaoImpl() {
		super(PatientFamilyHistory.class);
		// TODO Auto-generated constructor stub
	}

	@Autowired
	private IpatientFamilyHistoryService ipatientFamilyHistoryService;
	
	@Override
	public Response savePatientFamilyHistory(PatientFamilyHistoryDto patientFamilyHistoryDto)throws ApplicationException {
		try
		{
			for(int i=0;i<patientFamilyHistoryDto.getListOfPatientFamilyHistoryDtos().size();i++)
			{
				if(patientFamilyHistoryDto.getListOfPatientFamilyHistoryDtos().get(i).getFamilyHistoryId() != null && patientFamilyHistoryDto.getListOfPatientFamilyHistoryDtos().get(i).getFamilyHistoryId()>0)
				{
					PatientFamilyHistory patientFamilyHistory = findById(patientFamilyHistoryDto.getListOfPatientFamilyHistoryDtos().get(i).getFamilyHistoryId());
					patientFamilyHistory.setIsReviewedFlag(YES);
					ipatientFamilyHistoryService.updateSaveNurseReviewedFlag(patientFamilyHistory);
				}
				
				PatientFamilyHistory patientFamilyHistory = new PatientFamilyHistory();
				patientFamilyHistory.setIsNoFamilyHistoryStatus(patientFamilyHistoryDto.getListOfPatientFamilyHistoryDtos().get(i).getIsNoFamilyHistoryStatus());
				patientFamilyHistory.setRemark(patientFamilyHistoryDto.getListOfPatientFamilyHistoryDtos().get(i).getRemark());
				patientFamilyHistory.setDiagnosisId(patientFamilyHistoryDto.getListOfPatientFamilyHistoryDtos().get(i).getDiagnosisId());
				patientFamilyHistory.setRelationId(patientFamilyHistoryDto.getListOfPatientFamilyHistoryDtos().get(i).getRelationId());
				
				patientFamilyHistory.setRoleId(patientFamilyHistoryDto.getRoleId());
				patientFamilyHistory.setIsReviewedFlag(
							patientFamilyHistoryDto.getListOfPatientFamilyHistoryDtos().get(i).getIsReviewedFlag() == '\0' ? NO :
							patientFamilyHistoryDto.getListOfPatientFamilyHistoryDtos().get(i).getIsReviewedFlag()
						);
				
				patientFamilyHistory.setIsEnterInErrorStatus( patientFamilyHistoryDto.getListOfPatientFamilyHistoryDtos().get(i).getIsEnterInErrorStatus() );
				patientFamilyHistory.setCreatedDate(CommonDateUtils.getDate(patientFamilyHistoryDto.getCreatedDate(), "dd-MM-yyyy"));
				patientFamilyHistory.setEncounterId(patientFamilyHistoryDto.getEncounterId());
				patientFamilyHistory.setCreatedBy(patientFamilyHistoryDto.getCreatedBy());
				patientFamilyHistory.setUpdatedBy(patientFamilyHistoryDto.getUpdatedBy());
				patientFamilyHistory.setUpdatedDate(CommonDateUtils.getDate(patientFamilyHistoryDto.getUpdatedDate(), "dd-MM-yyyy"));
				patientFamilyHistory.setStatus(patientFamilyHistoryDto.getStatus());
				patientFamilyHistory.setOrganizationId(patientFamilyHistoryDto.getOrganizationId());
				patientFamilyHistory.setUnitId(patientFamilyHistoryDto.getUnitId());
				patientFamilyHistory.setPatientId(patientFamilyHistoryDto.getPatientId());
				
				PatientFamilyHistory historyId=save(patientFamilyHistory);
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
	public Response getOldPatientFamilyHistory(
			PatientFamilyHistoryDto patientFamilyHistoryDto)
			throws ApplicationException {
		try
		{
			List<PatientFamilyHistoryDto> listPatientFamilyHistoryDto;
			/*if(patientFamilyHistoryDto.getRoleId() == 3)
			{
				StatelessSession statlessSession = sessionFactory.openStatelessSession();
				listPatientFamilyHistoryDto = statlessSession.getNamedQuery("GET_PATIENT_FAMILY_HISTORY_BY_ROLE_ID")
						.setInteger("patientId", patientFamilyHistoryDto.getPatientId())
						.setInteger("unitId", patientFamilyHistoryDto.getUnitId())
						.setInteger("organizationId", patientFamilyHistoryDto.getOrganizationId())
						.setInteger("encounterId", patientFamilyHistoryDto.getEncounterId())
						.setInteger("roleId", patientFamilyHistoryDto.getRoleId())
						.setResultTransformer(Transformers.aliasToBean(PatientFamilyHistoryDto.class)).list();
				statlessSession.close();
			}
			else
			{*/
				StatelessSession statlessSession = sessionFactory.openStatelessSession();
				listPatientFamilyHistoryDto = statlessSession.getNamedQuery("GET_PATIENT_FAMILY_HISTORY")
						.setInteger("patientId", patientFamilyHistoryDto.getPatientId())
						.setInteger("unitId", patientFamilyHistoryDto.getUnitId())
						.setInteger("organizationId", patientFamilyHistoryDto.getOrganizationId())
						.setInteger("encounterId", patientFamilyHistoryDto.getEncounterId())
						.setResultTransformer(Transformers.aliasToBean(PatientFamilyHistoryDto.class)).list();
				statlessSession.close();
			/*}
			*/
			
			return new Response(SUCCESS, SUCCESS_CODE, null, listPatientFamilyHistoryDto, null);
		}catch(HibernateException he) { 
			he.printStackTrace();
			throw new HibernateException(he.getMessage());
		}catch(Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, SERVER_ERROR, null, null);
	}


	@Override
	public Response getCurrentPatientFamilyHistory(
			PatientFamilyHistoryDto patientFamilyHistoryDto)
			throws ApplicationException {
		try
		{
			List<PatientFamilyHistoryDto> listPatientFamilyHistoryDto;
			if(patientFamilyHistoryDto.getRoleId() == 3)
			{
				StatelessSession statlessSession = sessionFactory.openStatelessSession();
				listPatientFamilyHistoryDto = statlessSession.getNamedQuery("GET_CURRENT_PATIENT_FAMILY_HISTORY_BY_ROLE_ID")
						.setInteger("patientId", patientFamilyHistoryDto.getPatientId())
						.setInteger("unitId", patientFamilyHistoryDto.getUnitId())
						.setInteger("organizationId", patientFamilyHistoryDto.getOrganizationId())
						.setInteger("encounterId", patientFamilyHistoryDto.getEncounterId())
						.setInteger("roleId", patientFamilyHistoryDto.getRoleId())
						.setResultTransformer(Transformers.aliasToBean(PatientFamilyHistoryDto.class)).list();
				statlessSession.close();
			}
			else
			{
				StatelessSession statlessSession = sessionFactory.openStatelessSession();
				listPatientFamilyHistoryDto = statlessSession.getNamedQuery("GET_CURRENT_PATIENT_FAMILY_HISTORY")
						.setInteger("patientId", patientFamilyHistoryDto.getPatientId())
						.setInteger("unitId", patientFamilyHistoryDto.getUnitId())
						.setInteger("organizationId", patientFamilyHistoryDto.getOrganizationId())
						.setInteger("encounterId", patientFamilyHistoryDto.getEncounterId())
						.setResultTransformer(Transformers.aliasToBean(PatientFamilyHistoryDto.class)).list();
				statlessSession.close();
			}
			
			
			return new Response(SUCCESS, SUCCESS_CODE, null, listPatientFamilyHistoryDto, null);
		}catch(HibernateException he) { 
			he.printStackTrace();
			throw new HibernateException(he.getMessage());
		}catch(Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, SERVER_ERROR, null, null);
	}


	@Override
	public Response updatePatientFamilyHistory(
			PatientFamilyHistoryDto patientFamilyHistoryDto)
			throws ApplicationException {
		// TODO Auto-generated method stub
		try
		{
			for(int i=0;i<patientFamilyHistoryDto.getListOfPatientFamilyHistoryDtos().size();i++)
			{
				Session statlessSession = sessionFactory.getCurrentSession();
				PatientFamilyHistory patientFamilyHistory = findById(patientFamilyHistoryDto.getListOfPatientFamilyHistoryDtos().get(i).getFamilyHistoryId());
				

				if(patientFamilyHistoryDto.getRoleId() != null 
						&& patientFamilyHistoryDto.getRoleId() == 2 
						&& patientFamilyHistory.getRoleId() != 2 
						&& patientFamilyHistory.getIsReviewedFlag() == 'N'
						)
				{
					ipatientFamilyHistoryService.savePatientFamilyHistory(patientFamilyHistoryDto);
				}
				
				patientFamilyHistory.setIsNoFamilyHistoryStatus(patientFamilyHistoryDto.getListOfPatientFamilyHistoryDtos().get(i).getIsNoFamilyHistoryStatus());
				patientFamilyHistory.setRemark(patientFamilyHistoryDto.getListOfPatientFamilyHistoryDtos().get(i).getRemark());
				patientFamilyHistory.setDiagnosisId(patientFamilyHistoryDto.getListOfPatientFamilyHistoryDtos().get(i).getDiagnosisId());
				patientFamilyHistory.setRelationId(patientFamilyHistoryDto.getListOfPatientFamilyHistoryDtos().get(i).getRelationId());
				
				patientFamilyHistory.setRoleId(patientFamilyHistory.getRoleId());
				patientFamilyHistory.setIsReviewedFlag(patientFamilyHistoryDto.getRoleId() == 2 
														&& patientFamilyHistory.getRoleId() == 3
														? YES : patientFamilyHistoryDto.getListOfPatientFamilyHistoryDtos().get(i).getIsReviewedFlag());
				patientFamilyHistory.setIsEnterInErrorStatus(patientFamilyHistoryDto.getListOfPatientFamilyHistoryDtos().get(i).getIsEnterInErrorStatus());
				patientFamilyHistory.setCreatedDate(CommonDateUtils.getDate(patientFamilyHistoryDto.getCreatedDate(), "dd-MM-yyyy"));
				patientFamilyHistory.setEncounterId(patientFamilyHistoryDto.getEncounterId());
				patientFamilyHistory.setCreatedBy(patientFamilyHistoryDto.getCreatedBy());
				patientFamilyHistory.setUpdatedBy(patientFamilyHistoryDto.getUpdatedBy());
				patientFamilyHistory.setUpdatedDate(CommonDateUtils.getDate(patientFamilyHistoryDto.getUpdatedDate(), "dd-MM-yyyy"));
				patientFamilyHistory.setStatus(patientFamilyHistoryDto.getStatus());
				patientFamilyHistory.setOrganizationId(patientFamilyHistoryDto.getOrganizationId());
				patientFamilyHistory.setUnitId(patientFamilyHistoryDto.getUnitId());
				patientFamilyHistory.setPatientId(patientFamilyHistoryDto.getPatientId());
				
				PatientFamilyHistory historyId=save(patientFamilyHistory);
				
			}
			
			return new Response(SUCCESS, SUCCESS_CODE, COMMON_SUCCESS, null, null);
		}catch(HibernateException he) { 
			he.printStackTrace();
			throw new HibernateException(he.getMessage());
		}catch(Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, SERVER_ERROR, null, null);
	}



	@Override
	public Response updateSaveNurseReviewedFlag(
			PatientFamilyHistory patientFamilyHistory)
			throws ApplicationException {
		try
		{
			save(patientFamilyHistory);
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
			PatientFamilyHistoryDto patientFamilyHistoryDto)
			throws ApplicationException {
		try
		{
			for(int i=0;i<patientFamilyHistoryDto.getListOfPatientFamilyHistoryDtos().size();i++)
			{
				PatientFamilyHistory patientFamilyHistoryDetails = findById(patientFamilyHistoryDto.getListOfPatientFamilyHistoryDtos().get(i).getFamilyHistoryId());
				patientFamilyHistoryDetails.setIsEnterInErrorStatus(patientFamilyHistoryDto.getListOfPatientFamilyHistoryDtos().get(i).getIsEnterInErrorStatus());
				patientFamilyHistoryDetails.setIsReviewedFlag(patientFamilyHistoryDetails.getIsReviewedFlag() == 'N' && 
						patientFamilyHistoryDetails.getRoleId() == 3 && patientFamilyHistoryDto.getRoleId() == 2 ? YES : patientFamilyHistoryDetails.getIsReviewedFlag());
				ipatientFamilyHistoryService.updateSaveNurseReviewedFlag(patientFamilyHistoryDetails);
				
				if(patientFamilyHistoryDto.getRoleId() != null && patientFamilyHistoryDto.getRoleId() == 2 && patientFamilyHistoryDetails.getRoleId() == 3)
				{

					PatientFamilyHistory patientFamilyHistory = new PatientFamilyHistory();
					patientFamilyHistory.setIsNoFamilyHistoryStatus(patientFamilyHistoryDto.getListOfPatientFamilyHistoryDtos().get(i).getIsNoFamilyHistoryStatus());
					patientFamilyHistory.setRemark(patientFamilyHistoryDto.getListOfPatientFamilyHistoryDtos().get(i).getRemark());
					patientFamilyHistory.setDiagnosisId(patientFamilyHistoryDto.getListOfPatientFamilyHistoryDtos().get(i).getDiagnosisId());
					patientFamilyHistory.setRelationId(patientFamilyHistoryDto.getListOfPatientFamilyHistoryDtos().get(i).getRelationId());
					
					patientFamilyHistory.setRoleId(patientFamilyHistoryDto.getRoleId());
					
					patientFamilyHistory.setIsReviewedFlag(
							patientFamilyHistoryDto.getListOfPatientFamilyHistoryDtos().get(i).getIsReviewedFlag() == '\0' ? NO :
							patientFamilyHistoryDto.getListOfPatientFamilyHistoryDtos().get(i).getIsReviewedFlag()
						);
					
					patientFamilyHistory.setIsEnterInErrorStatus( NO );
					patientFamilyHistory.setCreatedDate(CommonDateUtils.getDate(patientFamilyHistoryDto.getCreatedDate(), "dd-MM-yyyy"));
					patientFamilyHistory.setEncounterId(patientFamilyHistoryDto.getEncounterId());
					patientFamilyHistory.setCreatedBy(patientFamilyHistoryDto.getCreatedBy());
					patientFamilyHistory.setUpdatedBy(patientFamilyHistoryDto.getUpdatedBy());
					patientFamilyHistory.setUpdatedDate(CommonDateUtils.getDate(patientFamilyHistoryDto.getUpdatedDate(), "dd-MM-yyyy"));
					patientFamilyHistory.setStatus(patientFamilyHistoryDto.getStatus());
					patientFamilyHistory.setOrganizationId(patientFamilyHistoryDto.getOrganizationId());
					patientFamilyHistory.setUnitId(patientFamilyHistoryDto.getUnitId());
					patientFamilyHistory.setPatientId(patientFamilyHistoryDto.getPatientId());
					
					PatientFamilyHistory historyId=save(patientFamilyHistory);
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
