package com.param.global.dao;

import java.util.List;

import org.dozer.DozerBeanMapper;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.param.global.common.GlobalCommonDateUtils;
import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;
import com.param.global.dto.DoctorConsultationServiceMapperDto;
import com.param.global.dto.GenderMasterDto;
import com.param.global.model.DoctorConsultationServiceMapper;

import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;

@SuppressWarnings({ "rawtypes", "unchecked" })
@Repository
public class DoctorConsultationServiceMapperDaoImpl extends GenericDao<DoctorConsultationServiceMapper, Integer>
		implements IDoctorConsultationServiceMapperDao, ICommonConstants {

	public DoctorConsultationServiceMapperDaoImpl() {
		super(DoctorConsultationServiceMapper.class);
	}

	@Autowired
	SessionFactory sessionFactory;

	@Autowired
	DozerBeanMapper mapper;

	@Override
	public Response saveDoctorConsultationServiceMapperService(
			DoctorConsultationServiceMapperDto doctorConsultationServiceMapperDto) throws ApplicationException {
		try {
			DoctorConsultationServiceMapper doctorConsultationServiceMapper = mapper.map(
					doctorConsultationServiceMapperDto, DoctorConsultationServiceMapper.class,
					"DoctorConsultationServiceMapperDto_to_DoctorConsultationServiceMapper");
			save(doctorConsultationServiceMapper);
			return new Response(SUCCESS, SUCCESS_CODE, COMMON_SUCCESS, null, null);
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR_CODE, COMMON_ERROR, null, null);
	}

	@Override
	public Response getDoctorConsultationServiceMapperList(
			DoctorConsultationServiceMapperDto doctorConsultationServiceMapperDto) throws ApplicationException {
		try {
			List<DoctorConsultationServiceMapperDto> doctorConsultationServiceMapperDtosList=sessionFactory.getCurrentSession()
					.getNamedQuery("GET_DOCTOR_CONSULTATION_SERVICE_MAPPER_LIST")
					.setInteger("orgId", doctorConsultationServiceMapperDto.getOrganizationId())
					.setInteger("unitId", doctorConsultationServiceMapperDto.getUnitId())
					.setResultTransformer(Transformers.aliasToBean(DoctorConsultationServiceMapperDto.class)).list();
			
			return new Response<>(SUCCESS, SUCCESS_CODE, null, doctorConsultationServiceMapperDtosList, null);
		}catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response updateDoctorConsultationServiceMapper(
			DoctorConsultationServiceMapperDto doctorConsultationServiceMapperDto) throws ApplicationException {
		try {
			
				DoctorConsultationServiceMapper doctorConsultationServiceMapper = findById(doctorConsultationServiceMapperDto.getDoctorConsultationServiceMapperId());
					doctorConsultationServiceMapper.setDoctorId(doctorConsultationServiceMapperDto.getDoctorId());
					doctorConsultationServiceMapper.setNewVisitServiceId(doctorConsultationServiceMapperDto.getNewVisitServiceId());
					doctorConsultationServiceMapper.setFollowupVisitServiceId(doctorConsultationServiceMapperDto.getFollowupVisitServiceId());
					doctorConsultationServiceMapper.setSecondaryVisitServiceId(doctorConsultationServiceMapperDto.getSecondaryVisitServiceId());
					doctorConsultationServiceMapper.setUpdatedBy(doctorConsultationServiceMapperDto.getUpdatedBy());
					doctorConsultationServiceMapper.setUpdatedDate(GlobalCommonDateUtils.getDate(doctorConsultationServiceMapperDto.getUpdatedDate(), "dd-M-yyyy HH:mm:ss"));
				
				update(doctorConsultationServiceMapper);
				return new Response<GenderMasterDto, Integer>(SUCCESS, null, COMMON_UPDATE, null, null);

			} catch (HibernateException he) {
				he.printStackTrace();
				return new Response<GenderMasterDto, Integer>(ERROR, null, null, null, null);
			} catch (Exception e) {
				e.printStackTrace();
			} 
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Integer checkDoctorConsultationExistOrNot(DoctorConsultationServiceMapperDto doctorConsultationServiceMapperDto)
			throws ApplicationException {
		try {
			DoctorConsultationServiceMapperDto doctorConsultationServiceMapperDtoo=(DoctorConsultationServiceMapperDto)sessionFactory.getCurrentSession()
					.getNamedQuery("CHECK_DOCTOR_CONSULTATION_EXIST_OR_NOT")
					.setInteger("orgId", doctorConsultationServiceMapperDto.getOrganizationId())
					.setInteger("unitId", doctorConsultationServiceMapperDto.getUnitId())
					.setInteger("doctorId", doctorConsultationServiceMapperDto.getDoctorId())
					.setInteger("newVisitServiceId", doctorConsultationServiceMapperDto.getNewVisitServiceId())
					.setInteger("followupVisitServiceId",doctorConsultationServiceMapperDto.getFollowupVisitServiceId())
					.setInteger("secondaryVisitServiceId", doctorConsultationServiceMapperDto.getSecondaryVisitServiceId())
					.setResultTransformer(Transformers.aliasToBean(DoctorConsultationServiceMapperDto.class)).uniqueResult();
			
			Integer id=null;
			if(doctorConsultationServiceMapperDtoo!=null)
				id =doctorConsultationServiceMapperDtoo.getDoctorConsultationServiceMapperId();
			
			return id;
		}catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Response getDoctorConsultationServiceMapperById(
			DoctorConsultationServiceMapperDto doctorConsultationServiceMapperDto) throws ApplicationException {
		try {
			List<DoctorConsultationServiceMapperDto> doctorConsultationServiceMapperDtosList= sessionFactory.getCurrentSession()
					.getNamedQuery("GET_DOCTOR_CONSULTATION_SERVICE_BY_ID")
					.setInteger("doctorConsultationServiceMapperId", doctorConsultationServiceMapperDto.getDoctorConsultationServiceMapperId())
					.setResultTransformer(Transformers.aliasToBean(DoctorConsultationServiceMapperDto.class)).list();
			
			return new Response<>(SUCCESS, SUCCESS_CODE, null, doctorConsultationServiceMapperDtosList, null);
		}catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response inactivePreviousEntry(Integer id)
			throws ApplicationException {
		try {
			sessionFactory.getCurrentSession().createQuery("UPDATE DoctorConsultationServiceMapper dcsm set dcsm.status='I' "
					+ "WHERE dcsm.doctorConsultationServiceMapperId=:doctorConsultationServiceMapperId ")
					.setInteger("doctorConsultationServiceMapperId", id).executeUpdate();
			
			return new Response<>(SUCCESS, SUCCESS_CODE, null, null, null);
		}catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getDoctorConsultationServiceBySpecialityAndDoctorId(
			DoctorConsultationServiceMapperDto doctorConsultationServiceMapperDto) throws ApplicationException {
		try {
			DoctorConsultationServiceMapperDto doctorConsultationServiceMapper= (DoctorConsultationServiceMapperDto)sessionFactory.getCurrentSession()
					.getNamedQuery("GET_DOCTOR_CONSULTATION_SERVICE_BY_SPECIALITY_AND_DOCTOR_ID")
					.setInteger("orgId", doctorConsultationServiceMapperDto.getOrganizationId())
					.setInteger("unitId",doctorConsultationServiceMapperDto.getUnitId())
					.setInteger("doctorId", doctorConsultationServiceMapperDto.getDoctorId())
					.setInteger("specialityId", doctorConsultationServiceMapperDto.getSpecialityId())
					.setResultTransformer(Transformers.aliasToBean(DoctorConsultationServiceMapperDto.class)).uniqueResult();
			
			return new Response<>(SUCCESS, SUCCESS_CODE, null, null, doctorConsultationServiceMapper);
		}catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

}
