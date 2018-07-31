package com.param.opd.coversheet.dao;

import java.util.List;

import javax.transaction.Transactional;

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
import com.param.opd.coversheet.dto.ImmunizationHistoryMapperDto;
import com.param.opd.coversheet.dto.PatientHabitDetailsDto;
import com.param.opd.coversheet.model.PatientHabitDetails;
import com.param.opd.coversheet.model.PatientHabitDetailsId;
import com.param.opd.coversheet.service.IPatientHabitDetailsMapperService;

@Repository
@SuppressWarnings({ "unused", "unchecked" })
public class PatientHabitDetailsMapperDaoImpl extends GenericDao<PatientHabitDetails, Integer> implements IPatientHabitDetailsMapperDao, ICommonConstants {

	
	public PatientHabitDetailsMapperDaoImpl() {
		super(PatientHabitDetails.class);
		// TODO Auto-generated constructor stub
	}

	@Autowired
	private IPatientHabitDetailsMapperService iPatientHabitDetailsMapperService;
	
	@Override
	@Transactional
	public Response savePatientHabitDetailsMapper(PatientHabitDetailsDto patientHabitDetailsDto)throws ApplicationException {
		try
		{
			for(int i=0;i<patientHabitDetailsDto.getListOfPatientHabitDetailsDto().size();i++)
			{
				if(patientHabitDetailsDto.getListOfPatientHabitDetailsDto().get(i).getPatientHabitDetailsId() != null  && 	
				   patientHabitDetailsDto.getListOfPatientHabitDetailsDto().get(i).getPatientHabitDetailsId()>0 )
				{
						PatientHabitDetails patientHabitDetails = findById(patientHabitDetailsDto.getListOfPatientHabitDetailsDto().get(i).getPatientHabitDetailsId());
						patientHabitDetails.setIsReviewedFlag(YES);
						iPatientHabitDetailsMapperService.updateSaveNurseReviewedFlag(patientHabitDetails);
					}
				
				
				PatientHabitDetails patientHabitDetails = new PatientHabitDetails();
				
				patientHabitDetails.setPatientId(patientHabitDetailsDto.getPatientId());
				patientHabitDetails.setHabitId(patientHabitDetailsDto.getListOfPatientHabitDetailsDto().get(i).getHabitId());
				patientHabitDetails.setStatus(patientHabitDetailsDto.getStatus());
				
				patientHabitDetails.setCigarettesPerDay(patientHabitDetailsDto.getListOfPatientHabitDetailsDto().get(i).getCigarettesPerDay());
				patientHabitDetails.setFrequency(patientHabitDetailsDto.getListOfPatientHabitDetailsDto().get(i).getFrequency());
				patientHabitDetails.setGmsPerDay(patientHabitDetailsDto.getListOfPatientHabitDetailsDto().get(i).getGmsPerDay());
				patientHabitDetails.setLeftWhen(patientHabitDetailsDto.getListOfPatientHabitDetailsDto().get(i).getLeftWhen());
				patientHabitDetails.setDurationOfLeftWhen(patientHabitDetailsDto.getListOfPatientHabitDetailsDto().get(i).getDurationOfLeftWhen());
				patientHabitDetails.setDrug(patientHabitDetailsDto.getListOfPatientHabitDetailsDto().get(i).getDrug());
				/*patientHabitDetails.setExerciseWhen(patientHabitDetailsDto.getListOfPatientHabitDetailsDto().get(i).getExerciseWhen());*/
				
				patientHabitDetails.setMlsPerDay(patientHabitDetailsDto.getListOfPatientHabitDetailsDto().get(i).getMlsPerDay());
				patientHabitDetails.setPackYear(patientHabitDetailsDto.getListOfPatientHabitDetailsDto().get(i).getPackYear());
				patientHabitDetails.setRemark(patientHabitDetailsDto.getListOfPatientHabitDetailsDto().get(i).getRemark());
				patientHabitDetails.setTypeOfExercise(patientHabitDetailsDto.getListOfPatientHabitDetailsDto().get(i).getTypeOfExercise());
				patientHabitDetails.setYearsSmoked(patientHabitDetailsDto.getListOfPatientHabitDetailsDto().get(i).getYearsSmoked());
				patientHabitDetails.setYearsUsed(patientHabitDetailsDto.getListOfPatientHabitDetailsDto().get(i).getYearsUsed());
				patientHabitDetails.setHabitTypeId(patientHabitDetailsDto.getListOfPatientHabitDetailsDto().get(i).getHabitTypeId());;
				patientHabitDetails.setCreatedDate(CommonDateUtils.getDate(patientHabitDetailsDto.getCreatedDate(), "dd-MM-yyyy"));
				patientHabitDetails.setCreatedBy(patientHabitDetailsDto.getCreatedBy());
				patientHabitDetails.setUpdatedBy(patientHabitDetailsDto.getUpdatedBy());
				patientHabitDetails.setUpdatedDate(CommonDateUtils.getDate(patientHabitDetailsDto.getUpdatedDate(), "dd-MM-yyyy"));
				patientHabitDetails.setOrganizationId(patientHabitDetailsDto.getOrganizationId());
				patientHabitDetails.setUnitId(patientHabitDetailsDto.getUnitId());
				
				patientHabitDetails.setEncounterId(patientHabitDetailsDto.getEncounterId());
				patientHabitDetails.setRoleId(patientHabitDetailsDto.getRoleId());
				patientHabitDetails.setIsReviewedFlag(
						patientHabitDetailsDto.getListOfPatientHabitDetailsDto().get(i).getIsReviewedFlag() == '\0' ? NO :
						patientHabitDetailsDto.getListOfPatientHabitDetailsDto().get(i).getIsReviewedFlag()
					);
			
					patientHabitDetails.setIsEnterInErrorStatus(patientHabitDetailsDto.getListOfPatientHabitDetailsDto().get(i).getIsEnterInErrorStatus());
				
				
				PatientHabitDetails historyId=save(patientHabitDetails);
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
	public Response getListOfPatientHabitDetailsMapper(PatientHabitDetailsDto patientHabitDetailsDto)
			throws ApplicationException {
		try
		{
			StatelessSession statlessSession = sessionFactory.openStatelessSession();
			List<PatientHabitDetailsDto> listPatientHabitDetailsDto = statlessSession.getNamedQuery("GET_PATIENT_HABIT_DETAILS_LIST")
					.setInteger("patientId", patientHabitDetailsDto.getPatientId())
					.setInteger("unitId", patientHabitDetailsDto.getUnitId())
					.setInteger("organizationId", patientHabitDetailsDto.getOrganizationId())
					.setResultTransformer(Transformers.aliasToBean(PatientHabitDetailsDto.class)).list();
			statlessSession.close();
			return new Response(SUCCESS, SUCCESS_CODE, null, listPatientHabitDetailsDto, null);
		}catch(HibernateException he) { 
			he.printStackTrace();
			throw new HibernateException(he.getMessage());
		}catch(Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, SERVER_ERROR, null, null);
	}

	@Override
	@Transactional
	public Response updatePatientHabitDetailsMapper(
			PatientHabitDetailsDto patientHabitDetailsDto)
			throws ApplicationException {
		try
		{
			for(int i=0;i<patientHabitDetailsDto.getListOfPatientHabitDetailsDto().size();i++)
			{
				
				
					StatelessSession statlessSession = sessionFactory.openStatelessSession();
					int updateCount= statlessSession.getNamedQuery("UPDATE_OLD_HABITS_BYPATIENT")
							.setInteger("patientId", patientHabitDetailsDto.getPatientId())
							.setInteger("habitId", patientHabitDetailsDto.getListOfPatientHabitDetailsDto().get(i).getHabitId())
							.setInteger("encounterId", patientHabitDetailsDto.getEncounterId())
							.setInteger("roleId", patientHabitDetailsDto.getRoleId())
							.executeUpdate();
					statlessSession.close();
				
				
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
	public Response getOldPatientHabitDetails(
			PatientHabitDetailsDto patientHabitDetailsDto)
			throws ApplicationException {
		try
		{
			List<PatientHabitDetailsDto> listPatientHabitDetailsDto;
			
			/*if(patientHabitDetailsDto.getRoleId() == 3)
			{
				StatelessSession statlessSession = sessionFactory.openStatelessSession();
				listPatientHabitDetailsDto = statlessSession.getNamedQuery("GET_PATIENT_HABIT_DETAILS_LIST_BY_ROLE_ID")
						.setInteger("patientId", patientHabitDetailsDto.getPatientId())
						.setInteger("unitId", patientHabitDetailsDto.getUnitId())
						.setInteger("organizationId", patientHabitDetailsDto.getOrganizationId())
						.setInteger("encounterId", patientHabitDetailsDto.getEncounterId())
						.setInteger("roleId", patientHabitDetailsDto.getRoleId())
						.setResultTransformer(Transformers.aliasToBean(PatientHabitDetailsDto.class)).list();
				statlessSession.close();
			}
			else
			{*/
				StatelessSession statlessSession = sessionFactory.openStatelessSession();
				listPatientHabitDetailsDto = statlessSession.getNamedQuery("GET_PATIENT_HABIT_DETAILS_LIST")
						.setInteger("patientId", patientHabitDetailsDto.getPatientId())
						.setInteger("unitId", patientHabitDetailsDto.getUnitId())
						.setInteger("organizationId", patientHabitDetailsDto.getOrganizationId())
						.setInteger("encounterId", patientHabitDetailsDto.getEncounterId())
						.setResultTransformer(Transformers.aliasToBean(PatientHabitDetailsDto.class)).list();
				statlessSession.close();
		/*	}
			
			*/
			return new Response(SUCCESS, SUCCESS_CODE, null, listPatientHabitDetailsDto, null);
		}catch(HibernateException he) { 
			he.printStackTrace();
			throw new HibernateException(he.getMessage());
		}catch(Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, SERVER_ERROR, null, null);
	}

	@Override
	public Response getCurrentPatientHabitDetails(
			PatientHabitDetailsDto patientHabitDetailsDto)
			throws ApplicationException {
		try
		{
			List<PatientHabitDetailsDto> listPatientHabitDetailsDto;
			
			if(patientHabitDetailsDto.getRoleId() == 3)
			{
				StatelessSession statlessSession = sessionFactory.openStatelessSession();
				listPatientHabitDetailsDto = statlessSession.getNamedQuery("GET_CURRENT_PATIENT_HABIT_DETAILS_LIST_BY_ROLE_ID")
						.setInteger("patientId", patientHabitDetailsDto.getPatientId())
						.setInteger("unitId", patientHabitDetailsDto.getUnitId())
						.setInteger("organizationId", patientHabitDetailsDto.getOrganizationId())
						.setInteger("encounterId", patientHabitDetailsDto.getEncounterId())
						.setInteger("roleId", patientHabitDetailsDto.getRoleId())
						.setResultTransformer(Transformers.aliasToBean(PatientHabitDetailsDto.class)).list();
				statlessSession.close();
			}
			else
			{
				StatelessSession statlessSession = sessionFactory.openStatelessSession();
				listPatientHabitDetailsDto = statlessSession.getNamedQuery("GET_CURRENT_PATIENT_HABIT_DETAILS_LIST")
						.setInteger("patientId", patientHabitDetailsDto.getPatientId())
						.setInteger("unitId", patientHabitDetailsDto.getUnitId())
						.setInteger("organizationId", patientHabitDetailsDto.getOrganizationId())
						.setInteger("encounterId", patientHabitDetailsDto.getEncounterId())
						.setResultTransformer(Transformers.aliasToBean(PatientHabitDetailsDto.class)).list();
				statlessSession.close();
			}
			
			
			return new Response(SUCCESS, SUCCESS_CODE, null, listPatientHabitDetailsDto, null);
		}catch(HibernateException he) { 
			he.printStackTrace();
			throw new HibernateException(he.getMessage());
		}catch(Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, SERVER_ERROR, null, null);
	}

	@Override
	public Response updatePatientHabitDetails(
			PatientHabitDetailsDto patientHabitDetailsDto)
			throws ApplicationException {
		try
		{
			for(int i=0;i<patientHabitDetailsDto.getListOfPatientHabitDetailsDto().size();i++)
			{
				Session s = sessionFactory.getCurrentSession();
				PatientHabitDetails patientHabitDetails =  findById(patientHabitDetailsDto.getListOfPatientHabitDetailsDto().get(i).getPatientHabitDetailsId());
				
				if(patientHabitDetailsDto.getRoleId() != null 
						&& patientHabitDetailsDto.getRoleId() == 2 
						&& patientHabitDetails.getRoleId() != 2 
						&& patientHabitDetails.getIsReviewedFlag() == 'N'
						)
				{
					iPatientHabitDetailsMapperService.savePatientHabitDetailsMapper(patientHabitDetailsDto);
				}
				
				patientHabitDetails.setCigarettesPerDay(patientHabitDetailsDto.getListOfPatientHabitDetailsDto().get(i).getCigarettesPerDay());
				patientHabitDetails.setFrequency(patientHabitDetailsDto.getListOfPatientHabitDetailsDto().get(i).getFrequency());
				patientHabitDetails.setGmsPerDay(patientHabitDetailsDto.getListOfPatientHabitDetailsDto().get(i).getGmsPerDay());
				patientHabitDetails.setLeftWhen(patientHabitDetailsDto.getListOfPatientHabitDetailsDto().get(i).getLeftWhen());
				patientHabitDetails.setDurationOfLeftWhen(patientHabitDetailsDto.getListOfPatientHabitDetailsDto().get(i).getDurationOfLeftWhen());
				patientHabitDetails.setDrug(patientHabitDetailsDto.getListOfPatientHabitDetailsDto().get(i).getDrug());
				/*patientHabitDetails.setExerciseWhen(patientHabitDetailsDto.getListOfPatientHabitDetailsDto().get(i).getExerciseWhen());*/
				
				patientHabitDetails.setMlsPerDay(patientHabitDetailsDto.getListOfPatientHabitDetailsDto().get(i).getMlsPerDay());
				patientHabitDetails.setPackYear(patientHabitDetailsDto.getListOfPatientHabitDetailsDto().get(i).getPackYear());
				patientHabitDetails.setRemark(patientHabitDetailsDto.getListOfPatientHabitDetailsDto().get(i).getRemark());
				patientHabitDetails.setTypeOfExercise(patientHabitDetailsDto.getListOfPatientHabitDetailsDto().get(i).getTypeOfExercise());
				patientHabitDetails.setYearsSmoked(patientHabitDetailsDto.getListOfPatientHabitDetailsDto().get(i).getYearsSmoked());
				patientHabitDetails.setYearsUsed(patientHabitDetailsDto.getListOfPatientHabitDetailsDto().get(i).getYearsUsed());
				patientHabitDetails.setHabitTypeId(patientHabitDetailsDto.getListOfPatientHabitDetailsDto().get(i).getHabitTypeId());;
				patientHabitDetails.setCreatedDate(CommonDateUtils.getDate(patientHabitDetailsDto.getCreatedDate(), "dd-MM-yyyy"));
				patientHabitDetails.setCreatedBy(patientHabitDetailsDto.getCreatedBy());
				patientHabitDetails.setUpdatedBy(patientHabitDetailsDto.getUpdatedBy());
				patientHabitDetails.setUpdatedDate(CommonDateUtils.getDate(patientHabitDetailsDto.getUpdatedDate(), "dd-MM-yyyy"));
				patientHabitDetails.setOrganizationId(patientHabitDetailsDto.getOrganizationId());
				patientHabitDetails.setUnitId(patientHabitDetailsDto.getUnitId());
				
				patientHabitDetails.setEncounterId(patientHabitDetailsDto.getEncounterId());
				patientHabitDetails.setRoleId(patientHabitDetails.getRoleId());
				patientHabitDetails.setIsReviewedFlag(patientHabitDetailsDto.getRoleId() == 2 
						&& patientHabitDetails.getRoleId() == 3
						? YES : patientHabitDetailsDto.getListOfPatientHabitDetailsDto().get(i).getIsReviewedFlag());
			
				patientHabitDetails.setIsEnterInErrorStatus(patientHabitDetailsDto.getListOfPatientHabitDetailsDto().get(i).getIsEnterInErrorStatus());
				
				
				PatientHabitDetails historyId=save(patientHabitDetails);
				
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
			PatientHabitDetails patientHabitDetails)
			throws ApplicationException {
		try
		{
			save(patientHabitDetails);
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
			PatientHabitDetailsDto patientHabitDetailsDto)
			throws ApplicationException {
		try
		{
			for(int i=0;i<patientHabitDetailsDto.getListOfPatientHabitDetailsDto().size();i++)
			{
				Session s = sessionFactory.getCurrentSession();
				PatientHabitDetails patientHabit =  findById(patientHabitDetailsDto.getListOfPatientHabitDetailsDto().get(i).getPatientHabitDetailsId());
				
				patientHabit.setIsEnterInErrorStatus(patientHabitDetailsDto.getListOfPatientHabitDetailsDto().get(i).getIsEnterInErrorStatus());
				patientHabit.setIsReviewedFlag(patientHabit.getIsReviewedFlag() == 'N' && 
						patientHabit.getRoleId() == 3 && patientHabitDetailsDto.getRoleId() == 2 ? YES : patientHabit.getIsReviewedFlag());
				
				if(patientHabitDetailsDto.getRoleId() != null && patientHabitDetailsDto.getRoleId() == 2 && patientHabitDetailsDto.getRoleId() == 3)
				{
					
					
					
					PatientHabitDetails patientHabitDetails = new PatientHabitDetails();
					patientHabitDetails.setPatientId(patientHabitDetailsDto.getPatientId());
					patientHabitDetails.setHabitId(patientHabitDetailsDto.getListOfPatientHabitDetailsDto().get(i).getHabitId());
					patientHabitDetails.setStatus(patientHabitDetailsDto.getStatus());
					patientHabitDetails.setCigarettesPerDay(patientHabitDetailsDto.getListOfPatientHabitDetailsDto().get(i).getCigarettesPerDay());
					patientHabitDetails.setFrequency(patientHabitDetailsDto.getListOfPatientHabitDetailsDto().get(i).getFrequency());
					patientHabitDetails.setGmsPerDay(patientHabitDetailsDto.getListOfPatientHabitDetailsDto().get(i).getGmsPerDay());
					patientHabitDetails.setLeftWhen(patientHabitDetailsDto.getListOfPatientHabitDetailsDto().get(i).getLeftWhen());
					patientHabitDetails.setDurationOfLeftWhen(patientHabitDetailsDto.getListOfPatientHabitDetailsDto().get(i).getDurationOfLeftWhen());
					patientHabitDetails.setDrug(patientHabitDetailsDto.getListOfPatientHabitDetailsDto().get(i).getDrug());
					/*patientHabitDetails.setExerciseWhen(patientHabitDetailsDto.getListOfPatientHabitDetailsDto().get(i).getExerciseWhen());*/
					
					patientHabitDetails.setMlsPerDay(patientHabitDetailsDto.getListOfPatientHabitDetailsDto().get(i).getMlsPerDay());
					patientHabitDetails.setPackYear(patientHabitDetailsDto.getListOfPatientHabitDetailsDto().get(i).getPackYear());
					patientHabitDetails.setRemark(patientHabitDetailsDto.getListOfPatientHabitDetailsDto().get(i).getRemark());
					patientHabitDetails.setTypeOfExercise(patientHabitDetailsDto.getListOfPatientHabitDetailsDto().get(i).getTypeOfExercise());
					patientHabitDetails.setYearsSmoked(patientHabitDetailsDto.getListOfPatientHabitDetailsDto().get(i).getYearsSmoked());
					patientHabitDetails.setYearsUsed(patientHabitDetailsDto.getListOfPatientHabitDetailsDto().get(i).getYearsUsed());
					patientHabitDetails.setHabitTypeId(patientHabitDetailsDto.getListOfPatientHabitDetailsDto().get(i).getHabitTypeId());;
					patientHabitDetails.setCreatedDate(CommonDateUtils.getDate(patientHabitDetailsDto.getCreatedDate(), "dd-MM-yyyy"));
					patientHabitDetails.setCreatedBy(patientHabitDetailsDto.getCreatedBy());
					patientHabitDetails.setUpdatedBy(patientHabitDetailsDto.getUpdatedBy());
					patientHabitDetails.setUpdatedDate(CommonDateUtils.getDate(patientHabitDetailsDto.getUpdatedDate(), "dd-MM-yyyy"));
					patientHabitDetails.setOrganizationId(patientHabitDetailsDto.getOrganizationId());
					patientHabitDetails.setUnitId(patientHabitDetailsDto.getUnitId());
					
					patientHabitDetails.setEncounterId(patientHabitDetailsDto.getEncounterId());
					patientHabitDetails.setRoleId(patientHabitDetailsDto.getRoleId());
					patientHabitDetails.setIsReviewedFlag(
							patientHabitDetailsDto.getListOfPatientHabitDetailsDto().get(i).getIsReviewedFlag() == '\0' ? NO :
							patientHabitDetailsDto.getListOfPatientHabitDetailsDto().get(i).getIsReviewedFlag()
						);
				
						patientHabitDetails.setIsEnterInErrorStatus(NO);
					
					
					PatientHabitDetails historyId=save(patientHabitDetails);
					
				}

				
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

}
