package com.param.opd.coversheet.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SharedSessionContract;
import org.hibernate.StatelessSession;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;

import com.param.global.common.GlobalCommonDateUtils;
import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;
import com.param.opd.common.CommonDateUtils;
import com.param.opd.coversheet.dto.AppointmentVitalMapperDto;
import com.param.opd.coversheet.dto.ImmunizationHistoryMapperDto;
import com.param.opd.coversheet.model.AllergyPatientMapper;
import com.param.opd.coversheet.model.AllergyPatientMapperId;
import com.param.opd.coversheet.model.AppointmentVitalMapper;
import com.param.opd.coversheet.service.IAppointmentVitalMapperService;

@Repository
@SuppressWarnings({ "rawtypes", "unchecked" })
public class AppointmentVitalMapperDaoImpl extends GenericDao<AppointmentVitalMapper, Integer> implements IAppointmentVitalMapperDao, ICommonConstants {

	public AppointmentVitalMapperDaoImpl() {
		super(AppointmentVitalMapper.class);
		// TODO Auto-generated constructor stub
	}

	@Autowired
	private IAppointmentVitalMapperService iAppointmentVitalMapperService;
	
	@Override
	@Transactional
	public Response saveAppointmentVitalMapper(AppointmentVitalMapperDto appointmentVitalMapperDto)throws ApplicationException {
		try
		{
			for(int i=0;i<appointmentVitalMapperDto.getListAppointmentVitalMapperDtos().size();i++)
			{
				if(appointmentVitalMapperDto.getListAppointmentVitalMapperDtos().get(i).getAppointmentVitalMapperId() != null &&
						appointmentVitalMapperDto.getListAppointmentVitalMapperDtos().get(i).getAppointmentVitalMapperId()>0	
						)
				{
					AppointmentVitalMapper appointmentVitalMapper = findById(appointmentVitalMapperDto.getListAppointmentVitalMapperDtos().get(i).getAppointmentVitalMapperId());
					appointmentVitalMapper.setIsReviewedFlag(YES);
					iAppointmentVitalMapperService.updateSaveNurseReviewedFlag(appointmentVitalMapper);
				}
				AppointmentVitalMapper appointmentVitalMapper = new AppointmentVitalMapper();
				
				appointmentVitalMapper.setValue(appointmentVitalMapperDto.getListAppointmentVitalMapperDtos().get(i).getValue());
				appointmentVitalMapper.setVitalId(appointmentVitalMapperDto.getListAppointmentVitalMapperDtos().get(i).getVitalId());
				
				appointmentVitalMapper.setRoleId(appointmentVitalMapperDto.getRoleId());
				appointmentVitalMapper.setIsReviewedFlag(
						appointmentVitalMapperDto.getListAppointmentVitalMapperDtos().get(i).getIsReviewedFlag() == '\0' ? NO :
							appointmentVitalMapperDto.getListAppointmentVitalMapperDtos().get(i).getIsReviewedFlag()
						);
				
				appointmentVitalMapper.setIsSelfTriage(INACTIVE);
				appointmentVitalMapper.setEncounterId(appointmentVitalMapperDto.getEncounterId());
				appointmentVitalMapper.setCreatedBy(appointmentVitalMapperDto.getCreatedBy());
				appointmentVitalMapper.setCreatedDate(CommonDateUtils.getDate(appointmentVitalMapperDto.getCreatedDate(), "dd-MM-yyyy"));
				appointmentVitalMapper.setOrganizationId(appointmentVitalMapperDto.getOrganizationId());
				appointmentVitalMapper.setPatientId(appointmentVitalMapperDto.getPatientId());
				appointmentVitalMapper.setStatus(appointmentVitalMapperDto.getStatus());
				appointmentVitalMapper.setUnitId(appointmentVitalMapperDto.getUnitId());
				appointmentVitalMapper.setUpdatedBy(appointmentVitalMapperDto.getUpdatedBy());
				appointmentVitalMapper.setUpdatedDate(CommonDateUtils.getDate(appointmentVitalMapperDto.getUpdatedDate(), "dd-MM-yyyy"));
				
				AppointmentVitalMapper vitalMappId= save(appointmentVitalMapper);
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
	public Response getListOfAppointmentVitalMapper(AppointmentVitalMapperDto appointmentVitalMapperDto)throws ApplicationException {
		try
		{
			StatelessSession statlessSession = sessionFactory.openStatelessSession();
			List<AppointmentVitalMapperDto> listAppointmentVitalMapperDto = statlessSession.getNamedQuery("GET_LIST_APPOINTMENT_VITAL_MAPPER")
					.setInteger("patientId", appointmentVitalMapperDto.getPatientId())
					.setInteger("unitId", appointmentVitalMapperDto.getUnitId())
					.setInteger("organizationId", appointmentVitalMapperDto.getOrganizationId())
					.setDate("formDate", GlobalCommonDateUtils.getDate(appointmentVitalMapperDto.getFromDate(), "yyyy-MM-dd"))
					.setDate("toDate", GlobalCommonDateUtils.getDate(appointmentVitalMapperDto.getToDate(), "yyyy-MM-dd"))
					.setInteger("vitalId", appointmentVitalMapperDto.getVitalId())
					.setInteger("encounterId", appointmentVitalMapperDto.getEncounterId())
					.setResultTransformer(Transformers.aliasToBean(AppointmentVitalMapperDto.class)).list();
			
			System.out.println(listAppointmentVitalMapperDto.size());
			statlessSession.close();
			return new Response(SUCCESS, SUCCESS_CODE, null, listAppointmentVitalMapperDto, null);
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
	public Response updateAppointmentVitalMapper(
			AppointmentVitalMapperDto appointmentVitalMapperDto)
			throws ApplicationException {
		try
		{
			for(int i=0;i<appointmentVitalMapperDto.getListAppointmentVitalMapperDtos().size();i++)
			{
				Session statlessSession = sessionFactory.getCurrentSession();
				int appointmentVitalMapperId= statlessSession.getNamedQuery("UPDATE_OLD_VITAL")
						.setInteger("patientId", appointmentVitalMapperDto.getPatientId())
						.setInteger("encounterId", appointmentVitalMapperDto.getEncounterId())
						.setInteger("roleId", appointmentVitalMapperDto.getRoleId())
						.setInteger("vitalId", appointmentVitalMapperDto.getListAppointmentVitalMapperDtos().get(i).getVitalId())
						.executeUpdate();
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
	public Response getListOfAppointmentVitalMapperByPatientId(
			AppointmentVitalMapperDto appointmentVitalMapperDto)
			throws ApplicationException {
		try
		{
			StatelessSession statlessSession = sessionFactory.openStatelessSession();
			List<AppointmentVitalMapperDto> listAppointmentVitalMapperDto = statlessSession.getNamedQuery("GET_LIST_APPOINTMENT_VITAL_MAPPER_BY_PATIENT_ID")
					.setInteger("unitId", appointmentVitalMapperDto.getUnitId())
					.setInteger("organizationId", appointmentVitalMapperDto.getOrganizationId())
					.setInteger("patientId", appointmentVitalMapperDto.getPatientId())
					.setResultTransformer(Transformers.aliasToBean(AppointmentVitalMapperDto.class)).list();
			
			System.out.println(listAppointmentVitalMapperDto.size());
			statlessSession.close();
			return new Response(SUCCESS, SUCCESS_CODE, null, listAppointmentVitalMapperDto, null);
		}catch(HibernateException he) { 
			he.printStackTrace();
			throw new HibernateException(he.getMessage());
		}catch(Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, SERVER_ERROR, null, null);
	}

	@Override
	public Response getOldAppointmentVitalMapper(
			AppointmentVitalMapperDto appointmentVitalMapperDto)
			throws ApplicationException {
		try
		{
			List<AppointmentVitalMapperDto> listAppointmentVitalMapperDto;
			/*if(appointmentVitalMapperDto.getRoleId() == 3)
			{
				Session statlessSession = sessionFactory.getCurrentSession();
				listAppointmentVitalMapperDto = statlessSession.getNamedQuery("GET_LIST_APPOINTMENT_VITAL")
						.setInteger("unitId", appointmentVitalMapperDto.getUnitId())
						.setInteger("organizationId", appointmentVitalMapperDto.getOrganizationId())
						.setInteger("encounterId", appointmentVitalMapperDto.getEncounterId())
						.setInteger("roleId", appointmentVitalMapperDto.getRoleId())
						.setInteger("patientId", appointmentVitalMapperDto.getPatientId())
						.setResultTransformer(Transformers.aliasToBean(AppointmentVitalMapperDto.class)).list();
			}
			else
			{*/
				Session statlessSession = sessionFactory.getCurrentSession();
				listAppointmentVitalMapperDto = statlessSession.getNamedQuery("GET_LIST_APPOINTMENT_VITAL_MAPPER_BY_ROLE_ID")
						.setInteger("unitId", appointmentVitalMapperDto.getUnitId())
						.setInteger("organizationId", appointmentVitalMapperDto.getOrganizationId())
						.setInteger("encounterId", appointmentVitalMapperDto.getEncounterId())
						.setInteger("patientId", appointmentVitalMapperDto.getPatientId())
						.setResultTransformer(Transformers.aliasToBean(AppointmentVitalMapperDto.class)).list();
			/*}
			*/
			return new Response(SUCCESS, SUCCESS_CODE, null, listAppointmentVitalMapperDto, null);
		}catch(HibernateException he) { 
			he.printStackTrace();
			throw new HibernateException(he.getMessage());
		}catch(Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, SERVER_ERROR, null, null);
	}
	
	

	@Override
	public Response getCurrentAppointmentVitalMapper(
			AppointmentVitalMapperDto appointmentVitalMapperDto)
			throws ApplicationException {
		try
		{
			List<AppointmentVitalMapperDto> listAppointmentVitalMapperDto;
			if(appointmentVitalMapperDto.getRoleId() == 3)
			{
				Session statlessSession = sessionFactory.getCurrentSession();
				listAppointmentVitalMapperDto = statlessSession.getNamedQuery("GET_CURRENT_LIST_APPOINTMENT_VITAL_MAPPER_BY_ROLE_ID")
						.setInteger("unitId", appointmentVitalMapperDto.getUnitId())
						.setInteger("organizationId", appointmentVitalMapperDto.getOrganizationId())
						.setInteger("encounterId", appointmentVitalMapperDto.getEncounterId())
						.setInteger("roleId", appointmentVitalMapperDto.getRoleId())
						.setInteger("patientId", appointmentVitalMapperDto.getPatientId())
						.setResultTransformer(Transformers.aliasToBean(AppointmentVitalMapperDto.class)).list();
			}
			else
			{
				Session statlessSession = sessionFactory.getCurrentSession();
				listAppointmentVitalMapperDto = statlessSession.getNamedQuery("GET_CURRENT_LIST_APPOINTMENT_VITAL")
						.setInteger("unitId", appointmentVitalMapperDto.getUnitId())
						.setInteger("organizationId", appointmentVitalMapperDto.getOrganizationId())
						.setInteger("encounterId", appointmentVitalMapperDto.getEncounterId())
						.setInteger("patientId", appointmentVitalMapperDto.getPatientId())
						.setResultTransformer(Transformers.aliasToBean(AppointmentVitalMapperDto.class)).list();
			}
			
			return new Response(SUCCESS, SUCCESS_CODE, null, listAppointmentVitalMapperDto, null);
		}catch(HibernateException he) { 
			he.printStackTrace();
			throw new HibernateException(he.getMessage());
		}catch(Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, SERVER_ERROR, null, null);
	}

	@Override
	public Response updateAppointmentVitalMapperDto(
			AppointmentVitalMapperDto appointmentVitalMapperDto)
			throws ApplicationException {
		try
		{
			for(int i=0;i<appointmentVitalMapperDto.getListAppointmentVitalMapperDtos().size();i++)
			{
				
				AppointmentVitalMapper appointmentVitalMapper = findById(appointmentVitalMapperDto.getListAppointmentVitalMapperDtos().get(i).getAppointmentVitalMapperId());
				
				
				if(appointmentVitalMapperDto.getRoleId() != null 
						&& appointmentVitalMapperDto.getRoleId() == 2 
						&& appointmentVitalMapper.getRoleId() != 2 
						&& appointmentVitalMapper.getIsReviewedFlag() == 'N'
						)
				{
					iAppointmentVitalMapperService.saveAppointmentVitalMapper(appointmentVitalMapperDto);
				}
				
				appointmentVitalMapper.setValue(appointmentVitalMapperDto.getListAppointmentVitalMapperDtos().get(i).getValue());
				appointmentVitalMapper.setVitalId(appointmentVitalMapperDto.getListAppointmentVitalMapperDtos().get(i).getVitalId());
				
				appointmentVitalMapper.setRoleId(appointmentVitalMapperDto.getRoleId());
				appointmentVitalMapper.setIsReviewedFlag(
						appointmentVitalMapperDto.getListAppointmentVitalMapperDtos().get(i).getIsReviewedFlag() == '\0' ? NO :
							appointmentVitalMapperDto.getListAppointmentVitalMapperDtos().get(i).getIsReviewedFlag()
						);
				
				appointmentVitalMapper.setIsSelfTriage(INACTIVE);
				appointmentVitalMapper.setEncounterId(appointmentVitalMapperDto.getEncounterId());
				appointmentVitalMapper.setCreatedBy(appointmentVitalMapperDto.getCreatedBy());
				appointmentVitalMapper.setCreatedDate(CommonDateUtils.getDate(appointmentVitalMapperDto.getCreatedDate(), "dd-MM-yyyy"));
				appointmentVitalMapper.setOrganizationId(appointmentVitalMapperDto.getOrganizationId());
				appointmentVitalMapper.setPatientId(appointmentVitalMapperDto.getPatientId());
				appointmentVitalMapper.setStatus(appointmentVitalMapperDto.getStatus());
				appointmentVitalMapper.setUnitId(appointmentVitalMapperDto.getUnitId());
				appointmentVitalMapper.setUpdatedBy(appointmentVitalMapperDto.getUpdatedBy());
				appointmentVitalMapper.setUpdatedDate(CommonDateUtils.getDate(appointmentVitalMapperDto.getUpdatedDate(), "dd-MM-yyyy"));
				
				AppointmentVitalMapper vitalMappId= save(appointmentVitalMapper);
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
			AppointmentVitalMapper appointmentVitalMapper)
			throws ApplicationException {
		try
		{
			save(appointmentVitalMapper);
			return new Response(SUCCESS, SUCCESS_CODE, COMMON_SUCCESS, null, null);
		}catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, SERVER_ERROR, null, null);
	}

}
