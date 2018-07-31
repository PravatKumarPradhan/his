package com.param.global.billing.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.param.global.billing.dto.DoctorCategoryMasterDto;
import com.param.global.common.GlobalCommonDateUtils;
import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;
import com.param.global.model.DoctorCategoryMaster;

import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;

@Repository
@SuppressWarnings({ "rawtypes", "unchecked" })
public class DoctorCategoryMasterDaoImpl extends GenericDao<DoctorCategoryMaster, Integer>
		implements IDoctorCategoryMasterDao, ICommonConstants {

	@Autowired
	SessionFactory sessionFactory;

	public DoctorCategoryMasterDaoImpl() {
		super(DoctorCategoryMaster.class);
	}

	@Override
	public Response saveDoctorCategoryMaster(DoctorCategoryMasterDto doctorCategoryMasterDto)
			throws ApplicationException {
		try {
			DoctorCategoryMaster doctorCategoryMaster = new DoctorCategoryMaster();
			doctorCategoryMaster.setCreatedBy(doctorCategoryMasterDto.getCreatedBy());
			doctorCategoryMaster.setUpdatedBy(doctorCategoryMasterDto.getUpdatedBy());
			doctorCategoryMaster.setDoctorCategoryCode(doctorCategoryMasterDto.getDoctorCategoryCode());
			doctorCategoryMaster.setDoctorCategoryDescription(doctorCategoryMasterDto.getDoctorCategoryDescription());
			doctorCategoryMaster.setStatus(doctorCategoryMasterDto.getStatus());
			doctorCategoryMaster.setCreatedDate(
					GlobalCommonDateUtils.getDate(doctorCategoryMasterDto.getCreatedDate(), "dd-M-yyyy HH:mm:ss"));
			doctorCategoryMaster.setUpdatedDate(
					GlobalCommonDateUtils.getDate(doctorCategoryMasterDto.getUpdatedDate(), "dd-M-yyyy HH:mm:ss"));
			doctorCategoryMaster.setOrganizationId(doctorCategoryMasterDto.getOrganizationId());
			save(doctorCategoryMaster);
			return new Response<>(SUCCESS, SUCCESS_CODE, SUCCESS_MESSAGE, null, null);
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, null, null, null);
	}

	@Override
	public Response getDoctorCategoryMasterById(int doctorId, int orgId) throws ApplicationException {
		try {
			List<DoctorCategoryMasterDto> doctorCategoryMasterDtoList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_DOCTOR_CATEGORY_MASTER_BY_ID").setInteger("doctorId", doctorId)
					.setInteger("orgId", orgId)
					.setResultTransformer(Transformers.aliasToBean(DoctorCategoryMasterDto.class)).list();
			return new Response<>(SUCCESS, SUCCESS_CODE, SUCCESS_MESSAGE, doctorCategoryMasterDtoList, null);

		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, null, null, null);
	}

	@Override
	public Response getDoctorCategoryMasterList(DoctorCategoryMasterDto doctorCategoryMasterDto) throws ApplicationException {
		try {
			List<DoctorCategoryMasterDto> doctorCategoryMasterDtoList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_DOCTOR_CATEGORY_MASTER_LIST")
					.setInteger("orgId", doctorCategoryMasterDto.getOrganizationId())
					.setFirstResult(doctorCategoryMasterDto.getOffset() != null ? doctorCategoryMasterDto.getOffset() : 0)
					.setMaxResults(doctorCategoryMasterDto.getNoOfRecordsPerPage() != null ? doctorCategoryMasterDto.getNoOfRecordsPerPage() : 10)
					.setResultTransformer(Transformers.aliasToBean(DoctorCategoryMasterDto.class)).list();
			return new Response<>(SUCCESS, SUCCESS_CODE, SUCCESS_MESSAGE, doctorCategoryMasterDtoList, null);
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, null, null, null);
	}

	@Override
	public Response updateDoctorCategoryMaster(DoctorCategoryMasterDto doctorCategoryMasterDto)
			throws ApplicationException {
		try {

			DoctorCategoryMaster doctorCategoryMaster = findById(doctorCategoryMasterDto.getDoctorCategoryId());
			doctorCategoryMaster.setUpdatedBy(doctorCategoryMasterDto.getUpdatedBy());
			doctorCategoryMaster.setDoctorCategoryCode(doctorCategoryMasterDto.getDoctorCategoryCode());
			doctorCategoryMaster.setDoctorCategoryDescription(doctorCategoryMasterDto.getDoctorCategoryDescription());
			doctorCategoryMaster.setUpdatedDate(
					GlobalCommonDateUtils.getDate(doctorCategoryMasterDto.getUpdatedDate(), "dd-M-yyyy HH:mm:ss"));
			update(doctorCategoryMaster);
			return new Response<>(SUCCESS, SUCCESS_CODE, SUCCESS_MESSAGE, null, null);
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, null, null, null);
	}

	@Override
	public Response updateDoctorCategoryMasterStatus(DoctorCategoryMasterDto doctorCategoryMasterDto)
			throws ApplicationException {
		try {
			DoctorCategoryMaster doctorCategoryMaster = findById(doctorCategoryMasterDto.getDoctorCategoryId());
			doctorCategoryMaster.setStatus(doctorCategoryMasterDto.getStatus() == ACTIVE ? ACTIVE : INACTIVE);
			doctorCategoryMaster.setUpdatedBy(doctorCategoryMasterDto.getUpdatedBy());
			doctorCategoryMaster.setUpdatedDate(
					GlobalCommonDateUtils.getDate(doctorCategoryMasterDto.getUpdatedDate(), "dd-M-yyyy HH:mm:ss"));

			update(doctorCategoryMaster);
			return new Response<>(SUCCESS, SUCCESS_CODE, SUCCESS_MESSAGE, null, null);
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, null, null, null);
	}

	@Override
	public Response getDoctorCategoryMasterByNameNotId(DoctorCategoryMasterDto doctorCategoryMasterDto)
			throws ApplicationException {
		try {
			List<DoctorCategoryMasterDto> doctorCategoryMasterDtoList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_DOCTOR_CATEGORY_MASTER_BY_NAME_NOT_ID")
					.setInteger("doctorId", doctorCategoryMasterDto.getDoctorCategoryId())
					.setString("doctorDesc", doctorCategoryMasterDto.getDoctorCategoryDescription().toLowerCase())
					.setResultTransformer(Transformers.aliasToBean(DoctorCategoryMasterDto.class)).list();
			return new Response<>(SUCCESS, SUCCESS_CODE, SUCCESS_MESSAGE, doctorCategoryMasterDtoList, null);
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, null, null, null);
	}

	@Override
	public Response getDoctorCategoryMasterByName(DoctorCategoryMasterDto doctorCategoryMasterDto)
			throws ApplicationException {
		try {

			List<DoctorCategoryMasterDto> doctorCategoryMasterDtoList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_DOCTOR_CATEGORY_MASTER_BY_NAME")
					.setInteger("orgId", doctorCategoryMasterDto.getOrganizationId())
					.setString("doctorDesc", doctorCategoryMasterDto.getDoctorCategoryDescription().toLowerCase())
					.setResultTransformer(Transformers.aliasToBean(DoctorCategoryMasterDto.class)).list();
			return new Response<>(SUCCESS, SUCCESS_CODE, SUCCESS_MESSAGE, doctorCategoryMasterDtoList, null);
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, null, null, null);
	}

	@Override
	public Response getDoctorCategoryCount(DoctorCategoryMasterDto doctorCategoryMasterDto) throws ApplicationException {
		try{
			Session session=sessionFactory.openSession();
			Criteria c=session.createCriteria(DoctorCategoryMaster.class); 
			c.add(Restrictions.eq("organizationId", doctorCategoryMasterDto.getOrganizationId()));
			c.setProjection(Projections.rowCount());  
			Long count = (Long)c.uniqueResult();
			return new Response(SUCCESS, null, null, null, count);
		}catch(Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, null, null, null, null);
		}
	}

}
