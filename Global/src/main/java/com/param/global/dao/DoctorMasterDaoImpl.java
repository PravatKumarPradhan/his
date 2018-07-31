package com.param.global.dao;

import java.util.List;

import org.dozer.DozerBeanMapper;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.StatelessSession;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.param.global.common.GlobalCommonDateUtils;
import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;
import com.param.global.dto.DoctorMasterDto;
import com.param.global.dto.sync.DoctorMasterDtoSync;
import com.param.global.model.DoctorDetails;
import com.param.global.model.DoctorMaster;
import com.param.global.model.DoctorSpecialityMapper;

import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;

@Repository
@SuppressWarnings({"rawtypes","unchecked"})
public class DoctorMasterDaoImpl extends GenericDao<DoctorMaster, Integer> implements ICommonConstants, IDoctorMasterDao{

	@Autowired
	SessionFactory sessionFactory;
	
	@Autowired
	DozerBeanMapper mapper;
	
	public DoctorMasterDaoImpl() {
		super(DoctorMaster.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Response getDoctorMasterList(DoctorMasterDto doctorMasterDto)throws ApplicationException {
		try
		{
			StatelessSession statlessSession = sessionFactory.openStatelessSession();
			List<DoctorMasterDto> listDoctorMasterDto = statlessSession.getNamedQuery("AUTO_FILL_SEARCH_FOR_DOCTOR_NAME")
					.setInteger("unitId", doctorMasterDto.getUnitId())
					.setInteger("orgId", doctorMasterDto.getOrganizationId())
					.setParameter("doctorName", "%" + doctorMasterDto.getDoctorName().toLowerCase() + "%")
					.setResultTransformer(Transformers.aliasToBean(DoctorMasterDto.class)).list();
			statlessSession.close();
			return new Response(SUCCESS, SUCCESS_CODE, null, listDoctorMasterDto, null);
		}catch(HibernateException he) { 
			he.printStackTrace();
			throw new HibernateException(he.getMessage());
		}catch(Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, SERVER_ERROR, null, null);
	}

	@Override
	public Response checkUniqueDoctor(DoctorMasterDto doctorMasterDto) throws ApplicationException {
		return new Response(SUCCESS, SUCCESS_CODE, null, null, null);
	}

	@Override
	public Response saveDoctorRegistration(DoctorMasterDto doctorMasterDto) throws ApplicationException {
		try {
			DoctorMaster doctorMaster = mapper.map(doctorMasterDto,DoctorMaster.class,
					"DoctorMasterDto_to_DoctorMaster");
			doctorMaster=save(doctorMaster);
			Integer dId=doctorMaster.getDoctorId();
			return new Response(SUCCESS, SUCCESS_CODE, null, null, dId);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response saveDoctorDetails(DoctorMasterDto doctorMasterDto) throws ApplicationException {
		try {
			DoctorDetails doctorDetails = mapper.map(doctorMasterDto,DoctorDetails.class,
					"DoctorMasterDto_to_DoctorDetails");
			Integer doctorDetailsId=(Integer) sessionFactory.getCurrentSession().save(doctorDetails);
			doctorMasterDto.setDoctorDetailsId(doctorDetailsId);
			return new Response(SUCCESS, SUCCESS_CODE, null, null, doctorMasterDto);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response getDoctorDetails(DoctorMasterDto doctorMasterDto) throws ApplicationException {
		try {

			List<DoctorMasterDto> doctorMasterDtosList = sessionFactory.openSession()
					.getNamedQuery("GET_DOCTOR_DETAILS")
					.setInteger("organizationId", doctorMasterDto.getOrganizationId())
					.setInteger("unitId", doctorMasterDto.getUnitId())
					.setResultTransformer(Transformers.aliasToBean(DoctorMasterDto.class)).list();
			return new Response(SUCCESS, SUCCESS_CODE, null, doctorMasterDtosList, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response updateDoctorDetails(DoctorMasterDto doctorMasterDto) throws ApplicationException {
		try{
			Session session=sessionFactory.getCurrentSession();
				DoctorMaster doctorMaster = mapper.map(doctorMasterDto,DoctorMaster.class,
						"DoctorMasterDto_to_DoctorMaster");
				session.update(doctorMaster);
				
				DoctorDetails docDetails = mapper.map(doctorMasterDto,DoctorDetails.class,
						"DoctorMasterDto_to_DoctorDetails");
				session.update(docDetails);
			return new Response(SUCCESS, null, COMMON_UPDATE, null, null);
			
			} catch (HibernateException he) {
				he.printStackTrace();

			} catch (Exception e) {
				e.printStackTrace();
			}
			return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response updateDoctorStatus(DoctorMasterDto doctorMasterDto) throws ApplicationException {
		try{
			sessionFactory.getCurrentSession().createQuery("UPDATE DoctorMaster emp set emp.status='I', "
						+ "emp.updatedBy=:updatedBy, "
						+ "emp.updatedDate=:updatedDate "
						+ "WHERE emp.doctorId=:doctorId "
						+ "AND emp.status='A' ")
				.setInteger("updatedBy", doctorMasterDto.getUpdatedBy())
				.setTimestamp("updatedDate", GlobalCommonDateUtils.getDate(doctorMasterDto.getUpdatedDate(), "yyyy-M-dd"))
				.setInteger("doctorId", doctorMasterDto.getDoctorId())
			.executeUpdate();
			
			sessionFactory.getCurrentSession().createQuery("UPDATE DoctorDetails emp set emp.status='I', "
						+ "emp.updatedBy=:updatedBy, "
						+ "emp.updatedDate=:updatedDate "
						+ "WHERE emp.doctorId=:doctorId "
						+ "AND emp.status='A' ")
				.setInteger("updatedBy", doctorMasterDto.getUpdatedBy())
				.setTimestamp("updatedDate", GlobalCommonDateUtils.getDate(doctorMasterDto.getUpdatedDate(), "yyyy-M-dd"))
				.setInteger("doctorId", doctorMasterDto.getDoctorId())
			.executeUpdate();
			
			sessionFactory.getCurrentSession().createQuery("UPDATE DependentDetails depDet set depDet.status='I', "
					+ "depDet.updatedBy=:updatedBy, "
					+ "depDet.updatedDate=:updatedDate "
					+ "WHERE depDet.empDocId=:doctorId "
					+ "AND depDet.typeId=:typeId "
					+ "AND depDet.status='A' ")
				.setInteger("updatedBy", doctorMasterDto.getUpdatedBy())
				.setTimestamp("updatedDate", GlobalCommonDateUtils.getDate(doctorMasterDto.getUpdatedDate(), "yyyy-M-dd"))
				.setInteger("doctorId", doctorMasterDto.getDoctorId())
				.setInteger("typeId", doctorMasterDto.getTypeId())
			.executeUpdate();
			
			return new Response(SUCCESS, null, COMMON_UPDATE, null, null);
			
			} catch (HibernateException he) {
				he.printStackTrace();

			} catch (Exception e) {
				e.printStackTrace();
			}
			return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response saveDoctorSpecialityMapper(DoctorMasterDto doctorMasterDto) throws ApplicationException {
		try{
			
			DoctorSpecialityMapper doctorSpecialityMapper = mapper.map(doctorMasterDto,DoctorSpecialityMapper.class,
						"DoctorMasterDto_to_DoctorSpecialityMapper");
			sessionFactory.getCurrentSession().save(doctorSpecialityMapper);
			
			return new Response(SUCCESS, null, COMMON_SUCCESS, null, null);
			
			} catch (HibernateException he) {
				he.printStackTrace();

			} catch (Exception e) {
				e.printStackTrace();
			}
			return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response searchDoctorDetailsByCriteria(DoctorMasterDto doctorMasterDto) throws ApplicationException {
		try {

			StringBuilder rawQuery = new StringBuilder(sessionFactory.openSession()
					.getNamedQuery("GET_DOCTOR_DETAILS").getQueryString());
			
			if(doctorMasterDto.getDoctorName()!=null && !doctorMasterDto.getDoctorName().isEmpty())
				rawQuery.append(" AND lower(concat(coalesce(doctor.firstName),coalesce(doctor.lastName))) LIKE concat('%',LOWER('"+doctorMasterDto.getDoctorName()+"'),'%') ");
			if(doctorMasterDto.getDocCategoryId()>0)
				rawQuery.append(" AND doctor.docCategoryId="+doctorMasterDto.getDocCategoryId());
			if(doctorMasterDto.getDocDesignationId()>0)
				rawQuery.append(" AND doctor.docDesignationId="+doctorMasterDto.getDocDesignationId());
			if(doctorMasterDto.getSpecialityId()>0)
				rawQuery.append(" AND doctor.specialityId="+doctorMasterDto.getSpecialityId());
			if(doctorMasterDto.getDocCode()!=null && !doctorMasterDto.getDocCode().isEmpty())
				rawQuery.append(" AND doctor.docCode='"+doctorMasterDto.getDocCode()+"' ");
			if(doctorMasterDto.getIdentificationTypeId()>0)
				rawQuery.append(" AND doctor.identificationTypeId="+doctorMasterDto.getIdentificationTypeId());
			if(doctorMasterDto.getIdentificationNumber()!=null && !doctorMasterDto.getIdentificationNumber().isEmpty())
				rawQuery.append(" AND doctor.identificationNumber='"+doctorMasterDto.getIdentificationNumber()+"' ");
			
			List<DoctorMasterDto> doctorRegistrationDtosList = sessionFactory.openSession()
					.createQuery(rawQuery.toString())
					.setInteger("organizationId", doctorMasterDto.getOrganizationId())
					.setInteger("unitId", doctorMasterDto.getUnitId())
					.setResultTransformer(Transformers.aliasToBean(DoctorMasterDto.class)).list();
			return new Response(SUCCESS, SUCCESS_CODE, null, doctorRegistrationDtosList, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response getDoctorForSyncById(Integer docId) throws ApplicationException {
		try{
			List<DoctorMasterDtoSync> doctorList=sessionFactory.getCurrentSession()
					.getNamedQuery("GET_DOCTORDETAILS_FORSYNC_BY_ID")
					.setParameter("doctorId", docId)
					.setResultTransformer(Transformers.aliasToBean(DoctorMasterDtoSync.class)).list();
			return new Response(SUCCESS, SUCCESS_CODE, SUCCESS_MESSAGE, doctorList, null);
		}catch(HibernateException he){
			he.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR,ERROR_CODE,null,null,null);
	}

}
