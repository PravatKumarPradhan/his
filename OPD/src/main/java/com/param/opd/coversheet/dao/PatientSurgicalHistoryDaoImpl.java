package com.param.opd.coversheet.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.StatelessSession;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;

import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;
import com.param.opd.common.CommonDateUtils;
import com.param.opd.coversheet.dto.PatientPersonalHistoryDetailsDto;
import com.param.opd.coversheet.dto.PatientSurgicalHistoryDto;
import com.param.opd.coversheet.model.PatientPersonalHistoryDetails;
import com.param.opd.coversheet.model.PatientSurgicalHistory;
import com.param.opd.coversheet.service.IPatientSurgicalHistoryService;

@Repository
@SuppressWarnings({ "unused", "unchecked","rawtypes" })
public class PatientSurgicalHistoryDaoImpl extends GenericDao<PatientSurgicalHistory, Integer> implements IPatientSurgicalHistoryDao, ICommonConstants {

	public PatientSurgicalHistoryDaoImpl() {
		super(PatientSurgicalHistory.class);
		// TODO Auto-generated constructor stub
	}

	@Autowired
	private IPatientSurgicalHistoryService iPatientSurgicalHistoryService;
	
	@Override
	public Response savePatientSurgicalHistory(PatientSurgicalHistoryDto patientSurgicalHistoryDto)throws ApplicationException {
		try
		{
			for(int i=0;i<patientSurgicalHistoryDto.getListOfPatientSurgicalHistoryDto().size();i++)
			{
				if(patientSurgicalHistoryDto.getListOfPatientSurgicalHistoryDto().get(i).getSurgicalHistoryDetailsId() != null &&
						patientSurgicalHistoryDto.getListOfPatientSurgicalHistoryDto().get(i).getSurgicalHistoryDetailsId() >0
						)
				{
					PatientSurgicalHistory patientSurgicalHistory = findById(patientSurgicalHistoryDto.getListOfPatientSurgicalHistoryDto().get(i).getSurgicalHistoryDetailsId());
					patientSurgicalHistory.setIsReviewedFlag(YES);
					iPatientSurgicalHistoryService.updateSaveNurseReviewedFlag(patientSurgicalHistory);
				}
				
				PatientSurgicalHistory patientSurgicalHistory = new PatientSurgicalHistory();
				patientSurgicalHistory.setMonth(patientSurgicalHistoryDto.getListOfPatientSurgicalHistoryDto().get(i).getMonth());
				patientSurgicalHistory.setIsNoSignificantStatus(patientSurgicalHistoryDto.getListOfPatientSurgicalHistoryDto().get(i).getIsNoSignificantStatus());
				patientSurgicalHistory.setYear(patientSurgicalHistoryDto.getListOfPatientSurgicalHistoryDto().get(i).getYear());
				patientSurgicalHistory.setRemark(patientSurgicalHistoryDto.getListOfPatientSurgicalHistoryDto().get(i).getRemark());
				patientSurgicalHistory.setSurgeryId(patientSurgicalHistoryDto.getListOfPatientSurgicalHistoryDto().get(i).getSurgeryId());
				patientSurgicalHistory.setSurgenName(patientSurgicalHistoryDto.getListOfPatientSurgicalHistoryDto().get(i).getSurgenName());
				
				patientSurgicalHistory.setEncounterId(patientSurgicalHistoryDto.getEncounterId());
				patientSurgicalHistory.setRoleId(patientSurgicalHistoryDto.getRoleId());
				patientSurgicalHistory.setIsEnterInErrorStatus(patientSurgicalHistoryDto.getListOfPatientSurgicalHistoryDto().get(i).getIsEnterInErrorStatus());
				
				patientSurgicalHistory.setIsReviewedFlag(patientSurgicalHistoryDto.getListOfPatientSurgicalHistoryDto().get(i).getIsReviewedFlag() == '\0' ? NO:
							patientSurgicalHistoryDto.getListOfPatientSurgicalHistoryDto().get(i).getIsReviewedFlag()
						);
				
				patientSurgicalHistory.setCreatedDate(CommonDateUtils.getDate(patientSurgicalHistoryDto.getCreatedDate(), "dd-MM-yyyy"));
				//patientSurgicalHistory.setAppointmentId(patientSurgicalHistoryDto.getAppointmentId());
				patientSurgicalHistory.setCreatedBy(patientSurgicalHistoryDto.getCreatedBy());
				patientSurgicalHistory.setUpdatedBy(patientSurgicalHistoryDto.getUpdatedBy());
				patientSurgicalHistory.setUpdatedDate(CommonDateUtils.getDate(patientSurgicalHistoryDto.getUpdatedDate(), "dd-MM-yyyy"));
				patientSurgicalHistory.setStatus(patientSurgicalHistoryDto.getStatus());
				patientSurgicalHistory.setOrganizationId(patientSurgicalHistoryDto.getOrganizationId());
				patientSurgicalHistory.setUnitId(patientSurgicalHistoryDto.getUnitId());
				patientSurgicalHistory.setPatientId(patientSurgicalHistoryDto.getPatientId());
				
				PatientSurgicalHistory historyId=save(patientSurgicalHistory);
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
	public Response getOldListOfPatientSurgicalHistory(PatientSurgicalHistoryDto patientSurgicalHistoryDto)
			throws ApplicationException {
		try
		{
			List<PatientSurgicalHistoryDto> listPatientSurgicalHistoryDto;
			
			/*if(patientSurgicalHistoryDto.getRoleId() == 3)
			{
				StatelessSession statlessSession = sessionFactory.openStatelessSession();
				listPatientSurgicalHistoryDto = statlessSession.getNamedQuery("GET_PATIENT_SURGICAL_HISTORY_BY_ROLE_ID")
						.setInteger("roleId", patientSurgicalHistoryDto.getRoleId())
						.setInteger("patientId", patientSurgicalHistoryDto.getPatientId())
						.setInteger("unitId", patientSurgicalHistoryDto.getUnitId())
						.setInteger("encounterId", patientSurgicalHistoryDto.getEncounterId())
						.setInteger("organizationId", patientSurgicalHistoryDto.getOrganizationId())
						.setResultTransformer(Transformers.aliasToBean(PatientSurgicalHistoryDto.class)).list();
				statlessSession.close();
			}else
			{*/
				StatelessSession statlessSession = sessionFactory.openStatelessSession();
				listPatientSurgicalHistoryDto = statlessSession.getNamedQuery("GET_PATIENT_SURGICAL_HISTORY")
						.setInteger("patientId", patientSurgicalHistoryDto.getPatientId())
						.setInteger("unitId", patientSurgicalHistoryDto.getUnitId())
						.setInteger("encounterId", patientSurgicalHistoryDto.getEncounterId())
						.setInteger("organizationId", patientSurgicalHistoryDto.getOrganizationId())
						.setResultTransformer(Transformers.aliasToBean(PatientSurgicalHistoryDto.class)).list();
				statlessSession.close();
			/*}
		*/
			return new Response(SUCCESS, SUCCESS_CODE, null, listPatientSurgicalHistoryDto, null);
		}catch(HibernateException he) { 
			he.printStackTrace();
			throw new HibernateException(he.getMessage());
		}catch(Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, SERVER_ERROR, null, null);
	}

	@Override
	public Response getCurrentListOfPatientSurgicalHistory(PatientSurgicalHistoryDto patientSurgicalHistoryDto)throws ApplicationException {
		try
		{
			List<PatientSurgicalHistoryDto> listPatientSurgicalHistoryDto;
			
			if(patientSurgicalHistoryDto.getRoleId() == 3)
			{
				StatelessSession statlessSession = sessionFactory.openStatelessSession();
				listPatientSurgicalHistoryDto = statlessSession.getNamedQuery("GET_CURRENT_PATIENT_SURGICAL_HISTORY_BY_ROLE_ID")
						.setInteger("roleId", patientSurgicalHistoryDto.getRoleId())
						.setInteger("patientId", patientSurgicalHistoryDto.getPatientId())
						.setInteger("unitId", patientSurgicalHistoryDto.getUnitId())
						.setInteger("encounterId", patientSurgicalHistoryDto.getEncounterId())
						.setInteger("organizationId", patientSurgicalHistoryDto.getOrganizationId())
						.setResultTransformer(Transformers.aliasToBean(PatientSurgicalHistoryDto.class)).list();
				statlessSession.close();
			}else
			{
				StatelessSession statlessSession = sessionFactory.openStatelessSession();
				listPatientSurgicalHistoryDto = statlessSession.getNamedQuery("GET_CURRENT_PATIENT_SURGICAL_HISTORY")
						.setInteger("patientId", patientSurgicalHistoryDto.getPatientId())
						.setInteger("unitId", patientSurgicalHistoryDto.getUnitId())
						.setInteger("encounterId", patientSurgicalHistoryDto.getEncounterId())
						.setInteger("organizationId", patientSurgicalHistoryDto.getOrganizationId())
						.setResultTransformer(Transformers.aliasToBean(PatientSurgicalHistoryDto.class)).list();
				statlessSession.close();
			}
		
			return new Response(SUCCESS, SUCCESS_CODE, null, listPatientSurgicalHistoryDto, null);
		}catch(HibernateException he) { 
			he.printStackTrace();
			throw new HibernateException(he.getMessage());
		}catch(Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, SERVER_ERROR, null, null);
	}

	@Override
	public Response updatePatientSurgicalHistory(PatientSurgicalHistoryDto patientSurgicalHistoryDto)throws ApplicationException {
		try
		{
			for(int i=0;i<patientSurgicalHistoryDto.getListOfPatientSurgicalHistoryDto().size();i++)
			{
				Session statlessSession = sessionFactory.getCurrentSession();
				PatientSurgicalHistory patientSurgicalHistory = findById(patientSurgicalHistoryDto.getListOfPatientSurgicalHistoryDto().get(i).getSurgicalHistoryDetailsId());
				

				if(patientSurgicalHistoryDto.getRoleId() != null && 
				   patientSurgicalHistoryDto.getRoleId() == 2 && 
				   patientSurgicalHistory.getRoleId() != 2 && 
				   patientSurgicalHistory.getIsReviewedFlag() == 'N')
				{
					iPatientSurgicalHistoryService.savePatientSurgicalHistory(patientSurgicalHistoryDto);
				}
				
				patientSurgicalHistory.setMonth(patientSurgicalHistoryDto.getListOfPatientSurgicalHistoryDto().get(i).getMonth());
				patientSurgicalHistory.setIsNoSignificantStatus(patientSurgicalHistoryDto.getListOfPatientSurgicalHistoryDto().get(i).getIsNoSignificantStatus());
				patientSurgicalHistory.setYear(patientSurgicalHistoryDto.getListOfPatientSurgicalHistoryDto().get(i).getYear());
				patientSurgicalHistory.setRemark(patientSurgicalHistoryDto.getListOfPatientSurgicalHistoryDto().get(i).getRemark());
				patientSurgicalHistory.setSurgeryId(patientSurgicalHistoryDto.getListOfPatientSurgicalHistoryDto().get(i).getSurgeryId());
				patientSurgicalHistory.setSurgenName(patientSurgicalHistoryDto.getListOfPatientSurgicalHistoryDto().get(i).getSurgenName());
				
				patientSurgicalHistory.setEncounterId(patientSurgicalHistoryDto.getEncounterId());
				patientSurgicalHistory.setIsEnterInErrorStatus(patientSurgicalHistoryDto.getListOfPatientSurgicalHistoryDto().get(i).getIsEnterInErrorStatus());
				patientSurgicalHistory.setRoleId(patientSurgicalHistory.getRoleId());
				patientSurgicalHistory.setIsReviewedFlag(patientSurgicalHistoryDto.getRoleId() == 2 && patientSurgicalHistory.getRoleId() == 3 ? YES :
					patientSurgicalHistoryDto.getListOfPatientSurgicalHistoryDto().get(i).getIsReviewedFlag()
					);
				
				patientSurgicalHistory.setCreatedDate(CommonDateUtils.getDate(patientSurgicalHistoryDto.getCreatedDate(), "dd-MM-yyyy"));
				patientSurgicalHistory.setCreatedBy(patientSurgicalHistoryDto.getCreatedBy());
				patientSurgicalHistory.setUpdatedBy(patientSurgicalHistoryDto.getUpdatedBy());
				patientSurgicalHistory.setUpdatedDate(CommonDateUtils.getDate(patientSurgicalHistoryDto.getUpdatedDate(), "dd-MM-yyyy"));
				patientSurgicalHistory.setStatus(patientSurgicalHistoryDto.getStatus());
				patientSurgicalHistory.setOrganizationId(patientSurgicalHistoryDto.getOrganizationId());
				patientSurgicalHistory.setUnitId(patientSurgicalHistoryDto.getUnitId());
				patientSurgicalHistory.setPatientId(patientSurgicalHistoryDto.getPatientId());
				
				save(patientSurgicalHistory);
				
				
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
			PatientSurgicalHistory patientSurgicalHistory)
			throws ApplicationException {
		try
		{
			save(patientSurgicalHistory);
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
			PatientSurgicalHistoryDto patientSurgicalHistoryDto)
			throws ApplicationException {
		try
		{
			for(int i=0;i<patientSurgicalHistoryDto.getListOfPatientSurgicalHistoryDto().size();i++)
			{
				Session statlessSession = sessionFactory.getCurrentSession();
				PatientSurgicalHistory patientSurgicalHistoryDetails = findById(patientSurgicalHistoryDto.getListOfPatientSurgicalHistoryDto().get(i).getSurgicalHistoryDetailsId());
				patientSurgicalHistoryDetails.setIsEnterInErrorStatus(patientSurgicalHistoryDto.getListOfPatientSurgicalHistoryDto().get(i).getIsEnterInErrorStatus());
				patientSurgicalHistoryDetails.setIsReviewedFlag(patientSurgicalHistoryDetails.getIsReviewedFlag() == 'N' && 
						patientSurgicalHistoryDetails.getRoleId() == 3 &&
						patientSurgicalHistoryDto.getRoleId() == 2
						? YES : patientSurgicalHistoryDetails.getIsReviewedFlag());
				iPatientSurgicalHistoryService.updateSaveNurseReviewedFlag(patientSurgicalHistoryDetails);
				
				if(patientSurgicalHistoryDto.getRoleId() != null &&
				   patientSurgicalHistoryDto.getRoleId() == 2 &&
				   patientSurgicalHistoryDetails.getRoleId() == 3
				   )
				{
					PatientSurgicalHistory patientSurgicalHistory = new PatientSurgicalHistory();
					patientSurgicalHistory.setMonth(patientSurgicalHistoryDto.getListOfPatientSurgicalHistoryDto().get(i).getMonth());
					patientSurgicalHistory.setIsNoSignificantStatus(patientSurgicalHistoryDto.getListOfPatientSurgicalHistoryDto().get(i).getIsNoSignificantStatus());
					patientSurgicalHistory.setYear(patientSurgicalHistoryDto.getListOfPatientSurgicalHistoryDto().get(i).getYear());
					patientSurgicalHistory.setRemark(patientSurgicalHistoryDto.getListOfPatientSurgicalHistoryDto().get(i).getRemark());
					patientSurgicalHistory.setSurgeryId(patientSurgicalHistoryDto.getListOfPatientSurgicalHistoryDto().get(i).getSurgeryId());
					patientSurgicalHistory.setSurgenName(patientSurgicalHistoryDto.getListOfPatientSurgicalHistoryDto().get(i).getSurgenName());
					
					patientSurgicalHistory.setEncounterId(patientSurgicalHistoryDto.getEncounterId());
					patientSurgicalHistory.setRoleId(patientSurgicalHistoryDto.getRoleId());
					patientSurgicalHistory.setIsEnterInErrorStatus(NO);
					
					patientSurgicalHistory.setIsReviewedFlag(patientSurgicalHistoryDto.getListOfPatientSurgicalHistoryDto().get(i).getIsReviewedFlag() == '\0' ? NO:
								patientSurgicalHistoryDto.getListOfPatientSurgicalHistoryDto().get(i).getIsReviewedFlag()
							);
					
					patientSurgicalHistory.setCreatedDate(CommonDateUtils.getDate(patientSurgicalHistoryDto.getCreatedDate(), "dd-MM-yyyy"));
					//patientSurgicalHistory.setAppointmentId(patientSurgicalHistoryDto.getAppointmentId());
					patientSurgicalHistory.setCreatedBy(patientSurgicalHistoryDto.getCreatedBy());
					patientSurgicalHistory.setUpdatedBy(patientSurgicalHistoryDto.getUpdatedBy());
					patientSurgicalHistory.setUpdatedDate(CommonDateUtils.getDate(patientSurgicalHistoryDto.getUpdatedDate(), "dd-MM-yyyy"));
					patientSurgicalHistory.setStatus(patientSurgicalHistoryDto.getStatus());
					patientSurgicalHistory.setOrganizationId(patientSurgicalHistoryDto.getOrganizationId());
					patientSurgicalHistory.setUnitId(patientSurgicalHistoryDto.getUnitId());
					patientSurgicalHistory.setPatientId(patientSurgicalHistoryDto.getPatientId());
					
					PatientSurgicalHistory historyId=save(patientSurgicalHistory);
				}
				
				return new Response(SUCCESS, SUCCESS_CODE, COMMON_SUCCESS, null, null);
			}
		}catch(HibernateException he) { 
				he.printStackTrace();
				throw new HibernateException(he.getMessage());
			}catch(Exception e) {
				e.printStackTrace();
			}
			return new Response<>(ERROR, ERROR_CODE, SERVER_ERROR, null, null);		
	}

}
