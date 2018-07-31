package com.param.global.org.common.dao;

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
import com.param.global.common.Response;
import com.param.global.org.common.dto.RejectionMasterDto;
import com.param.global.org.common.model.RejectionMaster;

import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;

@Repository

@SuppressWarnings({ "rawtypes", "unchecked" })
public class RejectionMasterDaoImpl extends GenericDao<RejectionMaster, Integer>
		implements IRejectionMasterDao, ICommonConstants {

	public RejectionMasterDaoImpl() {
		super(RejectionMaster.class);
	}

	@Override
	public Response saveRejectionMaster(RejectionMasterDto rejectionMasterDto) throws ApplicationException {
		try {
			RejectionMaster rejectionMaster = new RejectionMaster();
			rejectionMaster.setReasonDesc(rejectionMasterDto.getReasonDesc());
			rejectionMaster.setCode(rejectionMasterDto.getCode());
			rejectionMaster.setCreatedBy(rejectionMasterDto.getCreatedBy());
			rejectionMaster.setOrganizationId(rejectionMasterDto.getOrganizationId());
			rejectionMaster.setIsDeleted(false);
			rejectionMaster.setCreatedDate(
					GlobalCommonDateUtils.getDate(rejectionMasterDto.getCreatedDate(), "dd-M-yyyy HH:mm:ss"));
			rejectionMaster.setIsActive(rejectionMasterDto.getIsActive());
			rejectionMaster.setUnitId(rejectionMasterDto.getUnitId());
			rejectionMaster.setUpdatedBy(rejectionMasterDto.getUpdatedBy());
			rejectionMaster.setUpdatedDate(
					GlobalCommonDateUtils.getDate(rejectionMasterDto.getUpdatedDate(), "dd-M-yyyy HH:mm:ss"));
			save(rejectionMaster);
			return new Response<>(SUCCESS, SUCCESS_CODE, SUCCESS_MESSAGE, null, null);
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response getRejectionMasterById(int rejectId, int orgId) throws ApplicationException {
		try {

			List<RejectionMasterDto> rejectionMasterDtoList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_REJECTION_MASTER_BY_ID").setInteger("orgId", orgId)
					.setInteger("rejectId", rejectId)
					.setResultTransformer(Transformers.aliasToBean(RejectionMasterDto.class)).list();
			return new Response<>(SUCCESS, SUCCESS_CODE, SUCCESS_MESSAGE, rejectionMasterDtoList, null);
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response getRejectionMasterList(RejectionMasterDto rejectionMasterDto) throws ApplicationException {
		try {
			List<RejectionMasterDto> rejectionMasterDtoList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_REJECTION_MASTER_LIST")
					.setInteger("orgId", rejectionMasterDto.getOrganizationId())
					.setFirstResult(rejectionMasterDto.getOffset() != null ? rejectionMasterDto.getOffset() : 0)
					.setMaxResults(rejectionMasterDto.getNoOfRecordsPerPage() != null ? rejectionMasterDto.getNoOfRecordsPerPage() : 10)
					.setResultTransformer(Transformers.aliasToBean(RejectionMasterDto.class)).list();
			return new Response<>(SUCCESS, SUCCESS_CODE, SUCCESS_MESSAGE, rejectionMasterDtoList, null);
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response updateRejectionMaster(RejectionMasterDto rejectionMasterDto) throws ApplicationException {
		try {
			RejectionMaster rejectionMaster = findById(rejectionMasterDto.getId());
			rejectionMaster.setReasonDesc(rejectionMasterDto.getReasonDesc());
			rejectionMaster.setCode(rejectionMasterDto.getCode());
			rejectionMaster.setUpdatedBy(rejectionMasterDto.getUpdatedBy());
			rejectionMaster.setUpdatedDate(
					GlobalCommonDateUtils.getDate(rejectionMasterDto.getUpdatedDate(), "dd-M-yyyy HH:mm:ss"));
			update(rejectionMaster);
			return new Response<>(SUCCESS, SUCCESS_CODE, COMMON_UPDATE, null, null);
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response updateRejectionMasterStatus(RejectionMasterDto rejectionMasterDto) throws ApplicationException {
		try {
			RejectionMaster rejectionMaster = findById(rejectionMasterDto.getId());
			rejectionMaster.setIsActive(rejectionMasterDto.getIsActive() == TRUE ? TRUE : FALSE);
			rejectionMaster.setUpdatedBy(rejectionMasterDto.getUpdatedBy());
			rejectionMaster.setUpdatedDate(
					GlobalCommonDateUtils.getDate(rejectionMasterDto.getUpdatedDate(), "dd-M-yyyy HH:mm:ss"));
			update(rejectionMaster);
			return new Response<>(SUCCESS, SUCCESS_CODE, COMMON_UPDATE, null, null);
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response getRejectionMasterCount(RejectionMasterDto rejectionMasterDto) throws ApplicationException {
		try {
			Session session = sessionFactory.openSession();
			Criteria c = session.createCriteria(RejectionMaster.class);
			c.add(Restrictions.eq("organizationId", rejectionMasterDto.getOrganizationId()));
			c.setProjection(Projections.rowCount());
			Long count = (Long) c.uniqueResult();
			return new Response(SUCCESS, null, null, null, count);
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response getRejectionMasterByName(RejectionMasterDto rejectionMasterDto) throws ApplicationException {
		try {
			List<RejectionMasterDto> rejectionMasterDtoList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_REJECTION_MASTER_BY_NAME")
					.setInteger("orgId", rejectionMasterDto.getOrganizationId())
					.setString("rejectDesc", rejectionMasterDto.getReasonDesc().toLowerCase())
					.setResultTransformer(Transformers.aliasToBean(RejectionMasterDto.class)).list();
			return new Response<>(SUCCESS, SUCCESS_CODE, SUCCESS_MESSAGE, rejectionMasterDtoList, null);
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response getRejectionMasterByNameNotById(RejectionMasterDto rejectionMasterDto) throws ApplicationException {
		try {
			List<RejectionMasterDto> rejectionMasterDtoList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_REJECTION_MASTER_BY_NAME_NOT_BY_ID")
					.setInteger("rejectId", rejectionMasterDto.getId())
					.setString("rejectDesc", rejectionMasterDto.getReasonDesc().toLowerCase())
					.setResultTransformer(Transformers.aliasToBean(RejectionMasterDto.class)).list();
			return new Response<>(SUCCESS, SUCCESS_CODE, SUCCESS_MESSAGE, rejectionMasterDtoList, null);
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

}
