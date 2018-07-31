package com.param.opd.coversheet.dao;

import java.util.List;

import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;

import org.hibernate.HibernateException;
import org.hibernate.StatelessSession;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;
import com.param.opd.common.CommonDateUtils;
import com.param.opd.coversheet.dto.PatientFamilyHistoryDto;
import com.param.opd.coversheet.dto.PatientMedicalHistoryDto;
import com.param.opd.coversheet.model.PatientFamilyHistory;
import com.param.opd.coversheet.model.PatientMedicalHistory;
import com.param.opd.coversheet.service.IPatientMedicalHistoryService;

@Repository
@SuppressWarnings({"rawtypes","unchecked"})
public class PatientMedicalHistoryDaoImpl extends GenericDao<PatientMedicalHistory, Integer> implements IPatientMedicalHistoryDao, ICommonConstants {

	public PatientMedicalHistoryDaoImpl() {
		super(PatientMedicalHistory.class);
		// TODO Auto-generated constructor stub
	}

	@Autowired
	private IPatientMedicalHistoryService iPatientMedicalHistoryService;
	
	@Override
	public Response savePatientMedicalHistory(PatientMedicalHistoryDto patientMedicalHistoryDto)throws ApplicationException {
		try
		{
			for(int i=0;i<patientMedicalHistoryDto.getListOfPatientMedicalHistoryDto().size();i++)
			{
				if(patientMedicalHistoryDto.getListOfPatientMedicalHistoryDto().get(i).getPatientMedicalHistoryId() != null &&
						patientMedicalHistoryDto.getListOfPatientMedicalHistoryDto().get(i).getPatientMedicalHistoryId()>0	
						)
				{
					PatientMedicalHistory patientMedicalHistory = findById(patientMedicalHistoryDto.getListOfPatientMedicalHistoryDto().get(i).getPatientMedicalHistoryId());
					patientMedicalHistory.setIsReviewedFlag(YES);
					iPatientMedicalHistoryService.updateSaveNurseReviewedFlag(patientMedicalHistory);
				}
				
				PatientMedicalHistory patientMedicalHistory = new PatientMedicalHistory();
				patientMedicalHistory.setIsNoSignificantStatus(patientMedicalHistoryDto.getListOfPatientMedicalHistoryDto().get(i).getIsNoSignificantStatus());
				patientMedicalHistory.setRemark(patientMedicalHistoryDto.getListOfPatientMedicalHistoryDto().get(i).getRemark());
				patientMedicalHistory.setDiagnosisId(patientMedicalHistoryDto.getListOfPatientMedicalHistoryDto().get(i).getDiagnosisId());
				patientMedicalHistory.setSince(patientMedicalHistoryDto.getListOfPatientMedicalHistoryDto().get(i).getSince());
				patientMedicalHistory.setDuration(patientMedicalHistoryDto.getListOfPatientMedicalHistoryDto().get(i).getDuration());
				
				patientMedicalHistory.setRoleId(patientMedicalHistoryDto.getRoleId());
				patientMedicalHistory.setIsReviewedFlag(patientMedicalHistoryDto.getListOfPatientMedicalHistoryDto().get(i).getIsReviewedFlag() == '\0'? NO :
					patientMedicalHistoryDto.getListOfPatientMedicalHistoryDto().get(i).getIsReviewedFlag()
						);
				
				patientMedicalHistory.setIsEnterInError(patientMedicalHistoryDto.getListOfPatientMedicalHistoryDto().get(i).getIsEnterInError());
				patientMedicalHistory.setCreated_date(CommonDateUtils.getDate(patientMedicalHistoryDto.getCreated_date(), "dd-MM-yyyy"));
				patientMedicalHistory.setEncounterId(patientMedicalHistoryDto.getEncounterId());
				patientMedicalHistory.setCreated_by(patientMedicalHistoryDto.getCreated_by());
				patientMedicalHistory.setUpdated_by(patientMedicalHistoryDto.getUpdated_by());
				patientMedicalHistory.setUpdated_date(CommonDateUtils.getDate(patientMedicalHistoryDto.getUpdated_date(), "dd-MM-yyyy"));
				patientMedicalHistory.setStatus(patientMedicalHistoryDto.getStatus());
				patientMedicalHistory.setOrganizationId(patientMedicalHistoryDto.getOrganizationId());
				patientMedicalHistory.setUnitId(patientMedicalHistoryDto.getUnitId());
				patientMedicalHistory.setPatientId(patientMedicalHistoryDto.getPatientId());
				
				PatientMedicalHistory historyId=save(patientMedicalHistory);
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
	public Response getOldPatientMedicalHistory(
			PatientMedicalHistoryDto patientMedicalHistoryDto)
			throws ApplicationException {
		try
		{
			List<PatientMedicalHistoryDto> listPatientFamilyHistoryDto ;
			/*if(patientMedicalHistoryDto.getRoleId() == 3)
			{
				StatelessSession statlessSession = sessionFactory.openStatelessSession();
				listPatientFamilyHistoryDto = statlessSession.getNamedQuery("GET_PATIENT_MEDICAL_HISTORY_BY_ROLE_ID")
						.setInteger("patientId", patientMedicalHistoryDto.getPatientId())
						.setInteger("unitId", patientMedicalHistoryDto.getUnitId())
						.setInteger("organizationId", patientMedicalHistoryDto.getOrganizationId())
						.setInteger("encounterId", patientMedicalHistoryDto.getEncounterId())
						.setInteger("roleId", patientMedicalHistoryDto.getRoleId())
						.setResultTransformer(Transformers.aliasToBean(PatientMedicalHistoryDto.class)).list();
				statlessSession.close();
			}
			else
			{*/
				StatelessSession statlessSession = sessionFactory.openStatelessSession();
				listPatientFamilyHistoryDto = statlessSession.getNamedQuery("GET_PATIENT_MEDICAL_HISTORY")
						.setInteger("patientId", patientMedicalHistoryDto.getPatientId())
						.setInteger("unitId", patientMedicalHistoryDto.getUnitId())
						.setInteger("organizationId", patientMedicalHistoryDto.getOrganizationId())
						.setInteger("encounterId", patientMedicalHistoryDto.getEncounterId())
						.setResultTransformer(Transformers.aliasToBean(PatientMedicalHistoryDto.class)).list();
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
	public Response getCurrentPatientMedicalHistory(
			PatientMedicalHistoryDto patientMedicalHistoryDto)
			throws ApplicationException {
		try
		{
			List<PatientMedicalHistoryDto> listPatientFamilyHistoryDto ;
			if(patientMedicalHistoryDto.getRoleId() == 3)
			{
				StatelessSession statlessSession = sessionFactory.openStatelessSession();
				listPatientFamilyHistoryDto = statlessSession.getNamedQuery("GET_CURRENT_PATIENT_MEDICAL_HISTORY_BY_ROLE_ID")
						.setInteger("patientId", patientMedicalHistoryDto.getPatientId())
						.setInteger("unitId", patientMedicalHistoryDto.getUnitId())
						.setInteger("organizationId", patientMedicalHistoryDto.getOrganizationId())
						.setInteger("encounterId", patientMedicalHistoryDto.getEncounterId())
						.setInteger("roleId", patientMedicalHistoryDto.getRoleId())
						.setResultTransformer(Transformers.aliasToBean(PatientMedicalHistoryDto.class)).list();
				statlessSession.close();
			}
			else
			{
				StatelessSession statlessSession = sessionFactory.openStatelessSession();
				listPatientFamilyHistoryDto = statlessSession.getNamedQuery("GET_CURRENT_PATIENT_MEDICAL_HISTORY")
						.setInteger("patientId", patientMedicalHistoryDto.getPatientId())
						.setInteger("unitId", patientMedicalHistoryDto.getUnitId())
						.setInteger("organizationId", patientMedicalHistoryDto.getOrganizationId())
						.setInteger("encounterId", patientMedicalHistoryDto.getEncounterId())
						.setResultTransformer(Transformers.aliasToBean(PatientMedicalHistoryDto.class)).list();
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
	public Response updatePatientMedicalHistory(
			PatientMedicalHistoryDto patientMedicalHistoryDto)
			throws ApplicationException {
		try
		{
			for(int i=0;i<patientMedicalHistoryDto.getListOfPatientMedicalHistoryDto().size();i++)
			{
				
				PatientMedicalHistory patientMedicalHistory = findById(patientMedicalHistoryDto.getListOfPatientMedicalHistoryDto().get(i).getPatientMedicalHistoryId());
				
				if(patientMedicalHistoryDto.getRoleId() != null 
						&& patientMedicalHistoryDto.getRoleId() == 2 
						&& patientMedicalHistory.getRoleId() != 2 
						&& patientMedicalHistory.getIsReviewedFlag() == 'N'
						)
				{
					iPatientMedicalHistoryService.savePatientMedicalHistory(patientMedicalHistoryDto);
				}
				
				patientMedicalHistory.setIsNoSignificantStatus(patientMedicalHistoryDto.getListOfPatientMedicalHistoryDto().get(i).getIsNoSignificantStatus());
				patientMedicalHistory.setRemark(patientMedicalHistoryDto.getListOfPatientMedicalHistoryDto().get(i).getRemark());
				patientMedicalHistory.setDiagnosisId(patientMedicalHistoryDto.getListOfPatientMedicalHistoryDto().get(i).getDiagnosisId());
				patientMedicalHistory.setSince(patientMedicalHistoryDto.getListOfPatientMedicalHistoryDto().get(i).getSince());
				patientMedicalHistory.setDuration(patientMedicalHistoryDto.getListOfPatientMedicalHistoryDto().get(i).getDuration());
				
				patientMedicalHistory.setRoleId(patientMedicalHistoryDto.getRoleId());
				patientMedicalHistory.setIsReviewedFlag(patientMedicalHistoryDto.getRoleId() == 2 
						&& patientMedicalHistory.getRoleId() == 3
						? YES  :
					patientMedicalHistoryDto.getListOfPatientMedicalHistoryDto().get(i).getIsReviewedFlag()
						);
				
				patientMedicalHistory.setIsEnterInError(patientMedicalHistoryDto.getListOfPatientMedicalHistoryDto().get(i).getIsEnterInError());
				patientMedicalHistory.setCreated_date(CommonDateUtils.getDate(patientMedicalHistoryDto.getCreated_date(), "dd-MM-yyyy"));
				patientMedicalHistory.setEncounterId(patientMedicalHistoryDto.getEncounterId());
				patientMedicalHistory.setCreated_by(patientMedicalHistoryDto.getCreated_by());
				patientMedicalHistory.setUpdated_by(patientMedicalHistoryDto.getUpdated_by());
				patientMedicalHistory.setUpdated_date(CommonDateUtils.getDate(patientMedicalHistoryDto.getUpdated_date(), "dd-MM-yyyy"));
				patientMedicalHistory.setStatus(patientMedicalHistoryDto.getStatus());
				patientMedicalHistory.setOrganizationId(patientMedicalHistoryDto.getOrganizationId());
				patientMedicalHistory.setUnitId(patientMedicalHistoryDto.getUnitId());
				patientMedicalHistory.setPatientId(patientMedicalHistoryDto.getPatientId());
				
				PatientMedicalHistory historyId=save(patientMedicalHistory);
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
			PatientMedicalHistory patientMedicalHistory)
			throws ApplicationException {
		try
		{
			save(patientMedicalHistory);
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
	public Response updateStatusEnterInError(
			PatientMedicalHistoryDto patientMedicalHistoryDto)
			throws ApplicationException {
		try
		{
			for(int i=0;i<patientMedicalHistoryDto.getListOfPatientMedicalHistoryDto().size();i++)
			{
				
				PatientMedicalHistory patientMedicalHistoryDetails = findById(patientMedicalHistoryDto.getListOfPatientMedicalHistoryDto().get(i).getPatientMedicalHistoryId());
				patientMedicalHistoryDetails.setIsEnterInError(patientMedicalHistoryDto.getListOfPatientMedicalHistoryDto().get(i).getIsEnterInError());
				patientMedicalHistoryDetails.setIsReviewedFlag(patientMedicalHistoryDetails.getIsReviewedFlag() == 'N' && 
						patientMedicalHistoryDetails.getRoleId() == 3 &&
						patientMedicalHistoryDto.getRoleId() == 2 
						? YES : patientMedicalHistoryDetails.getIsReviewedFlag());
				iPatientMedicalHistoryService.updateSaveNurseReviewedFlag(patientMedicalHistoryDetails);
				
				if(patientMedicalHistoryDto.getRoleId() != null && patientMedicalHistoryDto.getRoleId() == 2 && patientMedicalHistoryDetails.getRoleId() == 3)
				{
					PatientMedicalHistory patientMedicalHistory = new PatientMedicalHistory();
					patientMedicalHistory.setIsNoSignificantStatus(patientMedicalHistoryDto.getListOfPatientMedicalHistoryDto().get(i).getIsNoSignificantStatus());
					patientMedicalHistory.setRemark(patientMedicalHistoryDto.getListOfPatientMedicalHistoryDto().get(i).getRemark());
					patientMedicalHistory.setDiagnosisId(patientMedicalHistoryDto.getListOfPatientMedicalHistoryDto().get(i).getDiagnosisId());
					patientMedicalHistory.setSince(patientMedicalHistoryDto.getListOfPatientMedicalHistoryDto().get(i).getSince());
					patientMedicalHistory.setDuration(patientMedicalHistoryDto.getListOfPatientMedicalHistoryDto().get(i).getDuration());
					
					patientMedicalHistory.setRoleId(patientMedicalHistoryDto.getRoleId());
					patientMedicalHistory.setIsReviewedFlag(patientMedicalHistoryDto.getListOfPatientMedicalHistoryDto().get(i).getIsReviewedFlag() == '\0'? NO :
						patientMedicalHistoryDto.getListOfPatientMedicalHistoryDto().get(i).getIsReviewedFlag()
							);
					
					patientMedicalHistory.setIsEnterInError(NO);
					patientMedicalHistory.setCreated_date(CommonDateUtils.getDate(patientMedicalHistoryDto.getCreated_date(), "dd-MM-yyyy"));
					patientMedicalHistory.setEncounterId(patientMedicalHistoryDto.getEncounterId());
					patientMedicalHistory.setCreated_by(patientMedicalHistoryDto.getCreated_by());
					patientMedicalHistory.setUpdated_by(patientMedicalHistoryDto.getUpdated_by());
					patientMedicalHistory.setUpdated_date(CommonDateUtils.getDate(patientMedicalHistoryDto.getUpdated_date(), "dd-MM-yyyy"));
					patientMedicalHistory.setStatus(patientMedicalHistoryDto.getStatus());
					patientMedicalHistory.setOrganizationId(patientMedicalHistoryDto.getOrganizationId());
					patientMedicalHistory.setUnitId(patientMedicalHistoryDto.getUnitId());
					patientMedicalHistory.setPatientId(patientMedicalHistoryDto.getPatientId());
					
					PatientMedicalHistory historyId=save(patientMedicalHistory);
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
