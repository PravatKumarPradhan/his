package com.param.global.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.param.global.common.GlobalCommonDateUtils;
import com.param.global.common.ICommonConstants;
import com.param.global.common.IError;
import com.param.global.common.Response;
import com.param.global.dto.CommonDto;
import com.param.global.dto.PatientCategoryMasterDto;
import com.param.global.model.DoctorCategoryMaster;
import com.param.global.model.PatientCategoryMaster;

import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;

@Repository
@SuppressWarnings({ "rawtypes", "unchecked" })
public class PatientCategoryMasterDaoImpl extends GenericDao<PatientCategoryMaster, Integer>
		implements IPatientCategoryMasterDao, ICommonConstants, IError {

	public PatientCategoryMasterDaoImpl() {
		super(PatientCategoryMaster.class);
	}

	@Override
	public Response getPatientCategoryMaster(PatientCategoryMasterDto patientCategoryMasterDto)
			throws ApplicationException {
		try {
			List<PatientCategoryMasterDto> listPatientCategoryMasterDto = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_ACTIVE_PATIENT_CATEGORY_LIST")
					.setResultTransformer(Transformers.aliasToBean(PatientCategoryMasterDto.class)).list();
			return new Response<>(SUCCESS, SUCCESS_CODE, "", listPatientCategoryMasterDto, null);
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
	}

	@Override
	public Response getPatientCategoryMasterByName(PatientCategoryMasterDto patientCategoryMasterDto)
			throws ApplicationException {
		try {
			List<PatientCategoryMasterDto> listPatientCategoryMasterDto = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_PATIENT_CATEGORY_MASTER_BY_NAME")
					.setInteger("orgId", patientCategoryMasterDto.getOrganizationId())
					.setString("category", patientCategoryMasterDto.getPatientCategory().toLowerCase())
					.setResultTransformer(Transformers.aliasToBean(PatientCategoryMasterDto.class)).list();
			return new Response<>(SUCCESS, SUCCESS_CODE, "", listPatientCategoryMasterDto, null);
		} catch (HibernateException he) {
			he.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
	}

	@Override
	public Response savePatientCategoryMaster(PatientCategoryMasterDto patientCategoryMasterDto)
			throws ApplicationException {
		try {
			PatientCategoryMaster patientCategoryMaster = new PatientCategoryMaster();
			patientCategoryMaster.setCreatedBy(patientCategoryMasterDto.getCreatedBy());
			patientCategoryMaster.setCreatedDate(
					GlobalCommonDateUtils.getDate(patientCategoryMasterDto.getCreatedDate(), "dd-M-yyyy HH:mm:ss"));
			patientCategoryMaster.setIsForeginerCategory(patientCategoryMasterDto.getIsForeginerCategory());
			patientCategoryMaster.setOrganizationId(patientCategoryMasterDto.getOrganizationId());
			patientCategoryMaster.setPatientCategory(patientCategoryMasterDto.getPatientCategory());
			patientCategoryMaster.setStatus(patientCategoryMasterDto.getStatus());
			patientCategoryMaster.setUpdatedBy(patientCategoryMasterDto.getUpdatedBy());
			patientCategoryMaster.setPatientCategoryCode(patientCategoryMasterDto.getPatientCategoryCode());
			patientCategoryMaster.setUpdatedDate(
					GlobalCommonDateUtils.getDate(patientCategoryMasterDto.getUpdatedDate(), "dd-M-yyyy HH:mm:ss"));
			save(patientCategoryMaster);
			return new Response<>(SUCCESS, SUCCESS_CODE, SUCCESS_MESSAGE, null, null);
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
	}

	@Override
	public Response getPatientCategoryMasterById(int patientCategoryId, int orgId) throws ApplicationException {
		try {

			List<PatientCategoryMasterDto> listPatientCategoryMasterDto = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_PATIENT_CATEGORY_MASTER_BY_ID")
					.setInteger("patientCategoryId", patientCategoryId).setInteger("orgId", orgId)
					.setResultTransformer(Transformers.aliasToBean(PatientCategoryMasterDto.class)).list();
			return new Response<>(SUCCESS, SUCCESS_CODE, "", listPatientCategoryMasterDto, null);
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
	}

	@Override
	public Response getPatientCategoryMasterByNameNotId(PatientCategoryMasterDto patientCategoryMasterDto)
			throws ApplicationException {
		try {
			List<PatientCategoryMasterDto> listPatientCategoryMasterDto = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_PATIENT_CATEGORY_MASTER_BY_NAME_NOT_ID")
					.setInteger("patientCategoryId", patientCategoryMasterDto.getPatientCategoryId())
					.setString("category", patientCategoryMasterDto.getPatientCategory().toLowerCase())
					.setResultTransformer(Transformers.aliasToBean(PatientCategoryMasterDto.class)).list();
			return new Response<>(SUCCESS, SUCCESS_CODE, "", listPatientCategoryMasterDto, null);
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
	}

	@Override
	public Response updatePatientCategoryMaster(PatientCategoryMasterDto patientCategoryMasterDto)
			throws ApplicationException {
		try {
			PatientCategoryMaster patientCategoryMaster = findById(patientCategoryMasterDto.getPatientCategoryId());
			patientCategoryMaster.setIsForeginerCategory(patientCategoryMasterDto.getIsForeginerCategory());
			patientCategoryMaster.setPatientCategory(patientCategoryMasterDto.getPatientCategory());
			patientCategoryMaster.setUpdatedBy(patientCategoryMasterDto.getUpdatedBy());
			patientCategoryMaster.setUpdatedDate(
					GlobalCommonDateUtils.getDate(patientCategoryMasterDto.getUpdatedDate(), "dd-M-yyyy HH:mm:ss"));
			patientCategoryMaster.setPatientCategoryCode(patientCategoryMasterDto.getPatientCategoryCode());
			update(patientCategoryMaster);
			return new Response<>(SUCCESS, SUCCESS_CODE, SUCCESS_MESSAGE, null, null);
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
	}

	@Override
	public Response updatePatientCategoryMasterStatus(PatientCategoryMasterDto patientCategoryMasterDto)
			throws ApplicationException {
		try {
			PatientCategoryMaster patientCategoryMaster = findById(patientCategoryMasterDto.getPatientCategoryId());
			patientCategoryMaster.setStatus(patientCategoryMasterDto.getStatus() == ACTIVE ? ACTIVE : INACTIVE);
			patientCategoryMaster.setUpdatedBy(patientCategoryMasterDto.getUpdatedBy());
			patientCategoryMaster.setUpdatedDate(
					GlobalCommonDateUtils.getDate(patientCategoryMasterDto.getUpdatedDate(), "dd-M-yyyy HH:mm:ss"));
			update(patientCategoryMaster);
			return new Response<>(SUCCESS, SUCCESS_CODE, COMMON_UPDATE, null, null);

		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
	}

	@Override
	public Response getPatientCategoryMasterList(PatientCategoryMasterDto patientCategoryMasterDto)
			throws ApplicationException {
		try {
			List<PatientCategoryMasterDto> listPatientCategoryMasterDto = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_PATIENT_CATEGORY_MASTER_LIST")
					.setInteger("orgId", patientCategoryMasterDto.getOrganizationId())
					.setFirstResult(patientCategoryMasterDto.getOffset() != null ? patientCategoryMasterDto.getOffset() : 0)
					.setMaxResults(patientCategoryMasterDto.getNoOfRecordsPerPage() != null ? patientCategoryMasterDto.getNoOfRecordsPerPage() : 10)
					.setResultTransformer(Transformers.aliasToBean(PatientCategoryMasterDto.class)).list();
			return new Response<>(SUCCESS, SUCCESS_CODE, "", listPatientCategoryMasterDto, null);
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
	}

	@Override
	public Response getPatientCategoryMasterCount(PatientCategoryMasterDto patientCategoryMasterDto)
			throws ApplicationException {
		try {
			Session session=sessionFactory.openSession();
			Criteria c=session.createCriteria(DoctorCategoryMaster.class); 
			c.add(Restrictions.eq("organizationId", patientCategoryMasterDto.getOrganizationId()));
			c.setProjection(Projections.rowCount());  
			Long count = (Long)c.uniqueResult();
			return new Response(SUCCESS, null, null, null, count);

		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);

	}

	public Response getPatientCategoryByOrg(int orgId) throws ApplicationException {
		try {
			List<CommonDto> listCommonDto = sessionFactory.getCurrentSession().getNamedQuery("GET_PATIENT_CATEGORY_LIST_BY_ORG").setInteger("orgId", orgId).setResultTransformer(Transformers.aliasToBean(CommonDto.class)).list();
			return new Response<>(SUCCESS, SUCCESS_CODE, "", listCommonDto, null);
		}catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
	}
}
