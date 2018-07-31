package com.param.opd.coversheet.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.StatelessSession;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;
import com.param.opd.common.CommonDateUtils;
import com.param.opd.coversheet.dto.PatientPersonalHistoryDetailsDto;
import com.param.opd.coversheet.dto.PatientTravelHistoryDetailsDto;
import com.param.opd.coversheet.model.PatientPersonalHistoryDetails;
import com.param.opd.coversheet.model.PatientTravelHistoryDetails;
import com.param.opd.coversheet.service.IPatientSurgicalHistoryService;
import com.param.opd.coversheet.service.IPatientTravelHistoryDetailsService;

import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;


@Repository
@SuppressWarnings({ "unused", "unchecked" })
public class PatientTravelHistoryDetailsDaoImpl extends GenericDao<PatientTravelHistoryDetails, Integer> implements IPatientTravelHistoryDetailsDao, ICommonConstants{

	public PatientTravelHistoryDetailsDaoImpl() {
		super(PatientTravelHistoryDetails.class);
		// TODO Auto-generated constructor stub
	}

	@Autowired
	private IPatientTravelHistoryDetailsService iPatientTravelHistoryDetailsService;
	@Override
	public Response getListOfPatientTravelHistoryDetails(
			PatientTravelHistoryDetailsDto patientTravelHistoryDetailsDto) throws ApplicationException {
			
		try
		{
			StatelessSession statlessSession = sessionFactory.openStatelessSession();
			List<PatientTravelHistoryDetailsDto> listPatientTravelHistoryDetailsDto = statlessSession.getNamedQuery("GET_PATIENT_TRAVEL_HISTORY_DEATILS")
					.setInteger("patientId", patientTravelHistoryDetailsDto.getPatientId())
					.setInteger("unitId", patientTravelHistoryDetailsDto.getUnitId())
					.setInteger("organizationId", patientTravelHistoryDetailsDto.getOrganizationId())
					.setResultTransformer(Transformers.aliasToBean(PatientTravelHistoryDetailsDto.class)).list();
			statlessSession.close();
			return new Response(SUCCESS, SUCCESS_CODE, null, listPatientTravelHistoryDetailsDto, null);
		}catch(HibernateException he) { 
			he.printStackTrace();
			throw new HibernateException(he.getMessage());
		}catch(Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, SERVER_ERROR, null, null);
	}

	@Override
	public Response savePatientTravelHistoryDetails(PatientTravelHistoryDetailsDto patientTravelHistoryDetailsDto) throws ApplicationException {
		try
		{
			for(int i=0;i<patientTravelHistoryDetailsDto.getListOfPatientTravelHistoryDtos().size();i++)
			{
				if(patientTravelHistoryDetailsDto.getListOfPatientTravelHistoryDtos().get(i).getTravelHistoryDetailsId() != null && 
						patientTravelHistoryDetailsDto.getListOfPatientTravelHistoryDtos().get(i).getTravelHistoryDetailsId() >0)
				{
					PatientTravelHistoryDetails patientTravelHistoryDetails = findById(patientTravelHistoryDetailsDto.getListOfPatientTravelHistoryDtos().get(i).getTravelHistoryDetailsId());
					patientTravelHistoryDetails.setIsReviewedFlag(YES);
					iPatientTravelHistoryDetailsService.updateSaveNurseReviewedFlag(patientTravelHistoryDetails);
				}
				
				PatientTravelHistoryDetails patientTravelHistoryDetails = new PatientTravelHistoryDetails();
				patientTravelHistoryDetails.setDescription(patientTravelHistoryDetailsDto.getListOfPatientTravelHistoryDtos().get(i).getDescription());
				patientTravelHistoryDetails.setWhenTravel(CommonDateUtils.getDate(patientTravelHistoryDetailsDto.getListOfPatientTravelHistoryDtos().get(i).getWhenTravel(), "dd-MM-yyyy"));
				
				patientTravelHistoryDetails.setRoleId(patientTravelHistoryDetailsDto.getRoleId());
				patientTravelHistoryDetails.setIsReviewedFlag(
						patientTravelHistoryDetailsDto.getListOfPatientTravelHistoryDtos().get(i).getIsReviewedFlag() == '\0' ? NO :
							patientTravelHistoryDetailsDto.getListOfPatientTravelHistoryDtos().get(i).getIsReviewedFlag()
						);
				
				patientTravelHistoryDetails.setIsEnterInErrorStatus( patientTravelHistoryDetailsDto.getListOfPatientTravelHistoryDtos().get(i).getIsEnterInErrorStatus() );
				
				patientTravelHistoryDetails.setCreatedDate(CommonDateUtils.getDate(patientTravelHistoryDetailsDto.getCreatedDate(), "dd-MM-yyyy"));
				patientTravelHistoryDetails.setEncounterId(patientTravelHistoryDetailsDto.getEncounterId());
				patientTravelHistoryDetails.setCreatedBy(patientTravelHistoryDetailsDto.getCreatedBy());
				patientTravelHistoryDetails.setUpdatedBy(patientTravelHistoryDetailsDto.getUpdatedBy());
				patientTravelHistoryDetails.setUpdatedDate(CommonDateUtils.getDate(patientTravelHistoryDetailsDto.getUpdatedDate(), "dd-MM-yyyy"));
				patientTravelHistoryDetails.setStatus(patientTravelHistoryDetailsDto.getStatus());
				patientTravelHistoryDetails.setOrganizationId(patientTravelHistoryDetailsDto.getOrganizationId());
				patientTravelHistoryDetails.setUnitId(patientTravelHistoryDetailsDto.getUnitId());
				patientTravelHistoryDetails.setPatientId(patientTravelHistoryDetailsDto.getPatientId());
				
				PatientTravelHistoryDetails historyId=save(patientTravelHistoryDetails);
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
	public Response getOldPatientTravelHistory(
			PatientTravelHistoryDetailsDto patientTravelHistoryDetailsDto)
			throws ApplicationException {
		try
		{
			List<PatientTravelHistoryDetailsDto> listPatientTravelHistoryDetailsDto;
			/*if(patientTravelHistoryDetailsDto.getRoleId() == 3)
			{
				StatelessSession statlessSession = sessionFactory.openStatelessSession();
				listPatientTravelHistoryDetailsDto = statlessSession.getNamedQuery("GET_PATIENT_TRAVEL_HISTORY_DEATILS_BY_ROLE_ID")
						.setInteger("patientId", patientTravelHistoryDetailsDto.getPatientId())
						.setInteger("unitId", patientTravelHistoryDetailsDto.getUnitId())
						.setInteger("organizationId", patientTravelHistoryDetailsDto.getOrganizationId())
						.setInteger("encounterId", patientTravelHistoryDetailsDto.getEncounterId())
						.setInteger("roleId", patientTravelHistoryDetailsDto.getRoleId())
						.setResultTransformer(Transformers.aliasToBean(PatientTravelHistoryDetailsDto.class)).list();
				statlessSession.close();
			}
			else
			{*/
				StatelessSession statlessSession = sessionFactory.openStatelessSession();
				listPatientTravelHistoryDetailsDto = statlessSession.getNamedQuery("GET_PATIENT_TRAVEL_HISTORY_DEATILS")
			
						.setInteger("patientId", patientTravelHistoryDetailsDto.getPatientId())
						.setInteger("unitId", patientTravelHistoryDetailsDto.getUnitId())
						.setInteger("organizationId", patientTravelHistoryDetailsDto.getOrganizationId())
						.setInteger("encounterId", patientTravelHistoryDetailsDto.getEncounterId())
						
						.setResultTransformer(Transformers.aliasToBean(PatientTravelHistoryDetailsDto.class)).list();
				statlessSession.close();
			/*}
			*/
			return new Response(SUCCESS, SUCCESS_CODE, null, listPatientTravelHistoryDetailsDto, null);
		}catch(HibernateException he) { 
			he.printStackTrace();
			throw new HibernateException(he.getMessage());
		}catch(Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, SERVER_ERROR, null, null);
	}

	@Override
	public Response getCurrentPatientTravelHistory(
			PatientTravelHistoryDetailsDto patientTravelHistoryDetailsDto)
			throws ApplicationException {
		try
		{
			List<PatientTravelHistoryDetailsDto> listPatientTravelHistoryDetailsDto;
			if(patientTravelHistoryDetailsDto.getRoleId() == 3)
			{
				StatelessSession statlessSession = sessionFactory.openStatelessSession();
				listPatientTravelHistoryDetailsDto = statlessSession.getNamedQuery("GET_CURRENT_PATIENT_TRAVEL_HISTORY_DEATILS_BY_ROLE_ID")
						.setInteger("patientId", patientTravelHistoryDetailsDto.getPatientId())
						.setInteger("unitId", patientTravelHistoryDetailsDto.getUnitId())
						.setInteger("organizationId", patientTravelHistoryDetailsDto.getOrganizationId())
						.setInteger("encounterId", patientTravelHistoryDetailsDto.getEncounterId())
						.setInteger("roleId", patientTravelHistoryDetailsDto.getRoleId())
						.setResultTransformer(Transformers.aliasToBean(PatientTravelHistoryDetailsDto.class)).list();
				statlessSession.close();
			}
			else
			{
				StatelessSession statlessSession = sessionFactory.openStatelessSession();
				listPatientTravelHistoryDetailsDto = statlessSession.getNamedQuery("GET_CURRENT_PATIENT_TRAVEL_HISTORY_DEATILS")
				
						.setInteger("patientId", patientTravelHistoryDetailsDto.getPatientId())
						.setInteger("unitId", patientTravelHistoryDetailsDto.getUnitId())
						.setInteger("organizationId", patientTravelHistoryDetailsDto.getOrganizationId())
						.setInteger("encounterId", patientTravelHistoryDetailsDto.getEncounterId())
						
						.setResultTransformer(Transformers.aliasToBean(PatientTravelHistoryDetailsDto.class)).list();
				statlessSession.close();
			}
			
			return new Response(SUCCESS, SUCCESS_CODE, null, listPatientTravelHistoryDetailsDto, null);
		}catch(HibernateException he) { 
			he.printStackTrace();
			throw new HibernateException(he.getMessage());
		}catch(Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, SERVER_ERROR, null, null);
	}

	@Override
	public Response updatePatientTravelHistory(
			PatientTravelHistoryDetailsDto patientTravelHistoryDetailsDto)
			throws ApplicationException {
		try
		{
			for(int i=0;i<patientTravelHistoryDetailsDto.getListOfPatientTravelHistoryDtos().size();i++)
			{
				
				PatientTravelHistoryDetails patientTravelHistoryDetails = findById(patientTravelHistoryDetailsDto.getListOfPatientTravelHistoryDtos().get(i).getTravelHistoryDetailsId());
				if(patientTravelHistoryDetailsDto.getRoleId() != null 
						&& patientTravelHistoryDetailsDto.getRoleId() == 2 
						&& patientTravelHistoryDetails.getRoleId() != 2 
						&& patientTravelHistoryDetails.getIsReviewedFlag() == 'N'
						)
				{
					iPatientTravelHistoryDetailsService.savePatientTravelHistoryDetails(patientTravelHistoryDetailsDto);
				}
				
				
				patientTravelHistoryDetails.setDescription(patientTravelHistoryDetailsDto.getListOfPatientTravelHistoryDtos().get(i).getDescription());
				patientTravelHistoryDetails.setWhenTravel(CommonDateUtils.getDate(patientTravelHistoryDetailsDto.getListOfPatientTravelHistoryDtos().get(i).getWhenTravel(), "dd-MM-yyyy"));
				
				patientTravelHistoryDetails.setRoleId(patientTravelHistoryDetailsDto.getRoleId());
				patientTravelHistoryDetails.setIsReviewedFlag(
						patientTravelHistoryDetailsDto.getRoleId() == 2 
						&& patientTravelHistoryDetails.getRoleId() == 3
						? YES : patientTravelHistoryDetailsDto.getListOfPatientTravelHistoryDtos().get(i).getIsReviewedFlag()
						);
				
				patientTravelHistoryDetails.setIsEnterInErrorStatus( patientTravelHistoryDetailsDto.getListOfPatientTravelHistoryDtos().get(i).getIsEnterInErrorStatus() );
				
				patientTravelHistoryDetails.setCreatedDate(CommonDateUtils.getDate(patientTravelHistoryDetailsDto.getCreatedDate(), "dd-MM-yyyy"));
				patientTravelHistoryDetails.setEncounterId(patientTravelHistoryDetailsDto.getEncounterId());
				patientTravelHistoryDetails.setCreatedBy(patientTravelHistoryDetailsDto.getCreatedBy());
				patientTravelHistoryDetails.setUpdatedBy(patientTravelHistoryDetailsDto.getUpdatedBy());
				patientTravelHistoryDetails.setUpdatedDate(CommonDateUtils.getDate(patientTravelHistoryDetailsDto.getUpdatedDate(), "dd-MM-yyyy"));
				patientTravelHistoryDetails.setStatus(patientTravelHistoryDetailsDto.getStatus());
				patientTravelHistoryDetails.setOrganizationId(patientTravelHistoryDetailsDto.getOrganizationId());
				patientTravelHistoryDetails.setUnitId(patientTravelHistoryDetailsDto.getUnitId());
				patientTravelHistoryDetails.setPatientId(patientTravelHistoryDetailsDto.getPatientId());
				
				PatientTravelHistoryDetails historyId=save(patientTravelHistoryDetails);
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
			PatientTravelHistoryDetails patientTravelHistoryDetails)
			throws ApplicationException {
		try
		{
			save(patientTravelHistoryDetails);
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
			PatientTravelHistoryDetailsDto patientTravelHistoryDetailsDto)
			throws ApplicationException {
		try
		{
			for(int i=0;i<patientTravelHistoryDetailsDto.getListOfPatientTravelHistoryDtos().size();i++)
			{
				
					PatientTravelHistoryDetails patientTravelHistory = findById(patientTravelHistoryDetailsDto.getListOfPatientTravelHistoryDtos().get(i).getTravelHistoryDetailsId());
					patientTravelHistory.setIsEnterInErrorStatus(patientTravelHistoryDetailsDto.getListOfPatientTravelHistoryDtos().get(i).getIsEnterInErrorStatus());
					patientTravelHistory.setIsReviewedFlag(patientTravelHistory.getIsReviewedFlag() == 'N' && 
							patientTravelHistory.getRoleId() == 3 &&
							patientTravelHistoryDetailsDto.getRoleId() == 2
							? YES : patientTravelHistory.getIsReviewedFlag());
					iPatientTravelHistoryDetailsService.updateSaveNurseReviewedFlag(patientTravelHistory);
					if(patientTravelHistoryDetailsDto.getRoleId() != null && patientTravelHistoryDetailsDto.getRoleId() == 2 && patientTravelHistory.getRoleId() == 3)
					{
					
						PatientTravelHistoryDetails patientTravelHistoryDetails = new PatientTravelHistoryDetails();
						patientTravelHistoryDetails.setDescription(patientTravelHistoryDetailsDto.getListOfPatientTravelHistoryDtos().get(i).getDescription());
						patientTravelHistoryDetails.setWhenTravel(CommonDateUtils.getDate(patientTravelHistoryDetailsDto.getListOfPatientTravelHistoryDtos().get(i).getWhenTravel(), "dd-MM-yyyy"));
						
						patientTravelHistoryDetails.setRoleId(patientTravelHistoryDetailsDto.getRoleId());
						patientTravelHistoryDetails.setIsReviewedFlag(
								patientTravelHistoryDetailsDto.getListOfPatientTravelHistoryDtos().get(i).getIsReviewedFlag() == '\0' ? NO :
									patientTravelHistoryDetailsDto.getListOfPatientTravelHistoryDtos().get(i).getIsReviewedFlag()
								);
						
						patientTravelHistoryDetails.setIsEnterInErrorStatus( NO );
						
						patientTravelHistoryDetails.setCreatedDate(CommonDateUtils.getDate(patientTravelHistoryDetailsDto.getCreatedDate(), "dd-MM-yyyy"));
						patientTravelHistoryDetails.setEncounterId(patientTravelHistoryDetailsDto.getEncounterId());
						patientTravelHistoryDetails.setCreatedBy(patientTravelHistoryDetailsDto.getCreatedBy());
						patientTravelHistoryDetails.setUpdatedBy(patientTravelHistoryDetailsDto.getUpdatedBy());
						patientTravelHistoryDetails.setUpdatedDate(CommonDateUtils.getDate(patientTravelHistoryDetailsDto.getUpdatedDate(), "dd-MM-yyyy"));
						patientTravelHistoryDetails.setStatus(patientTravelHistoryDetailsDto.getStatus());
						patientTravelHistoryDetails.setOrganizationId(patientTravelHistoryDetailsDto.getOrganizationId());
						patientTravelHistoryDetails.setUnitId(patientTravelHistoryDetailsDto.getUnitId());
						patientTravelHistoryDetails.setPatientId(patientTravelHistoryDetailsDto.getPatientId());
						
						PatientTravelHistoryDetails historyId=save(patientTravelHistoryDetails);
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
