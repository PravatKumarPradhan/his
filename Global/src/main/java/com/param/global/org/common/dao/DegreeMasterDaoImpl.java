package com.param.global.org.common.dao;

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

import com.param.global.common.GlobalCommonDateUtils;
import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;
import com.param.global.org.common.dto.DegreeMasterDto;
import com.param.global.org.common.model.DegreeMaster;

import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;

@Repository
@SuppressWarnings({ "rawtypes", "unchecked" })
public class DegreeMasterDaoImpl extends GenericDao<DegreeMaster, Integer>
		implements IDegreeMasterDao, ICommonConstants {

	@Autowired
	SessionFactory sessionFactory;

	public DegreeMasterDaoImpl() {
		super(DegreeMaster.class);
	}

	@Override
	public Response saveDegreeMaster(DegreeMasterDto degreeMasterDto) throws ApplicationException {
		try {
			DegreeMaster degreeMaster = new DegreeMaster();
			degreeMaster.setCreatedBy(degreeMasterDto.getCreatedBy());
			degreeMaster.setCreatedDate(GlobalCommonDateUtils.getDate(degreeMasterDto.getCreatedDate(), "dd-M-yyyy HH:mm:ss"));
			degreeMaster.setUpdatedBy(degreeMasterDto.getUpdatedBy());
			degreeMaster.setUpdatedDate(GlobalCommonDateUtils.getDate(degreeMasterDto.getUpdatedDate(), "dd-M-yyyy HH:mm:ss"));
			degreeMaster.setOrganizationId(degreeMasterDto.getOrganizationId());
			degreeMaster.setStatus(degreeMasterDto.getStatus());
			degreeMaster.setDegreeCode(degreeMasterDto.getDegreeCode());
			degreeMaster.setDegreeDesc(degreeMasterDto.getDegreeDesc());
			save(degreeMaster);
			return new Response<>(SUCCESS, SUCCESS_CODE, SUCCESS_MESSAGE, null, null);

		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response getDegreeMasterById(int degreeId, int orgId) throws ApplicationException {
		try {
			List<DegreeMasterDto> degreeMasterDtoList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_DEGREE_MASTER_BY_ID")
					.setInteger("degreeId", degreeId)
					.setInteger("orgId", orgId)
					.setResultTransformer(Transformers.aliasToBean(DegreeMasterDto.class)).list();
			return new Response<>(SUCCESS, SUCCESS_CODE, SUCCESS_MESSAGE, degreeMasterDtoList, null);
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response getDegreeMasterList(DegreeMasterDto degreeMasterDto) throws ApplicationException {
		try {
			List<DegreeMasterDto> degreeMasterDtoList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_DEGREE_MASTER_LIST")
					.setInteger("orgId", degreeMasterDto.getOrganizationId())
					.setFirstResult(degreeMasterDto.getOffset() != null ? degreeMasterDto.getOffset() : 0)
					.setMaxResults(degreeMasterDto.getNoOfRecordsPerPage() != null ? degreeMasterDto.getNoOfRecordsPerPage() : 10)
					.setResultTransformer(Transformers.aliasToBean(DegreeMasterDto.class)).list();
			return new Response<>(SUCCESS, SUCCESS_CODE, SUCCESS_MESSAGE, degreeMasterDtoList, null);

		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response updateDegreeMaster(DegreeMasterDto degreeMasterDto) throws ApplicationException {
		try {

			DegreeMaster degreeMaster = findById(degreeMasterDto.getDegreeId());
			degreeMaster.setUpdatedBy(degreeMasterDto.getUpdatedBy());
			degreeMaster.setUpdatedDate(GlobalCommonDateUtils.getDate(degreeMasterDto.getUpdatedDate(), "dd-M-yyyy HH:mm:ss"));
			degreeMaster.setDegreeCode(degreeMasterDto.getDegreeCode());
			degreeMaster.setDegreeDesc(degreeMasterDto.getDegreeDesc());
			update(degreeMaster);
			return new Response<>(SUCCESS, SUCCESS_CODE, COMMON_UPDATE, null, null);
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response updateDegreeMasterStatus(DegreeMasterDto degreeMasterDto) throws ApplicationException {
		try {
			DegreeMaster degreeMaster = findById(degreeMasterDto.getDegreeId());
			degreeMaster.setStatus(degreeMasterDto.getStatus() == ACTIVE ? ACTIVE : INACTIVE);
			degreeMaster.setUpdatedBy(degreeMasterDto.getUpdatedBy());
			degreeMaster.setUpdatedDate(
					GlobalCommonDateUtils.getDate(degreeMasterDto.getUpdatedDate(), "dd-M-yyyy HH:mm:ss"));
			update(degreeMaster);
			return new Response<>(SUCCESS, SUCCESS_CODE, COMMON_UPDATE, null, null);
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response getDegreeMasterCount(DegreeMasterDto degreeMasterDto) throws ApplicationException {
		try {
			Session session=sessionFactory.openSession();
			Criteria c=session.createCriteria(DegreeMaster.class); 
			c.add(Restrictions.eq("organizationId", degreeMasterDto.getOrganizationId()));
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
	public Response getDegreeMasterByName(DegreeMasterDto degreeMasterDto) throws ApplicationException {
		try {

			List<DegreeMasterDto> degreeMasterDtoList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_DEGREE_MASTER_BY_NAME").setInteger("orgId", degreeMasterDto.getOrganizationId())
					.setString("degreeDesc", degreeMasterDto.getDegreeDesc().toLowerCase())
					.setResultTransformer(Transformers.aliasToBean(DegreeMasterDto.class)).list();
			return new Response<>(SUCCESS, SUCCESS_CODE, SUCCESS_MESSAGE, degreeMasterDtoList, null);
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response getDegreeMasterByNameNotById(DegreeMasterDto degreeMasterDto) throws ApplicationException {
		try {
			List<DegreeMasterDto> degreeMasterDtoList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_DEGREE_MASTER_BY_NAME_NOT_BY_ID")
					.setInteger("degreeId", degreeMasterDto.getDegreeId())
					.setString("degreeDesc", degreeMasterDto.getDegreeDesc().toLowerCase())
					.setResultTransformer(Transformers.aliasToBean(DegreeMasterDto.class)).list();
			return new Response<>(SUCCESS, SUCCESS_CODE, SUCCESS_MESSAGE, degreeMasterDtoList, null);
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

}
