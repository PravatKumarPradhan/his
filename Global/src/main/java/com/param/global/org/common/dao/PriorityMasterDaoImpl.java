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
import com.param.global.model.PriorityMaster;
import com.param.global.org.common.dto.PriorityMasterDto;
import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;

@Repository
@SuppressWarnings({ "rawtypes", "unchecked" })
public class PriorityMasterDaoImpl extends GenericDao<PriorityMaster, Integer>
		implements IPriorityMasterDao, ICommonConstants {

	public PriorityMasterDaoImpl() {
		super(PriorityMaster.class);
	}

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public Response savePriorityMasterMaster(PriorityMasterDto priorityMasterDto) throws ApplicationException {
		try {
			PriorityMaster priorityMaster = new PriorityMaster();
			priorityMaster.setCreatedBy(priorityMasterDto.getCreatedBy());
			priorityMaster.setCreatedDate(GlobalCommonDateUtils.getDate(priorityMasterDto.getCreatedDate(), "dd-M-yyyy HH:mm:ss"));
			priorityMaster.setOrganizationId(priorityMasterDto.getOrganizationId());
			priorityMaster.setUnitId(priorityMasterDto.getUnitId());
			priorityMaster.setPriorityCode(priorityMasterDto.getPriorityCode());
			priorityMaster.setPriorityDesc(priorityMasterDto.getPriorityDesc());
			priorityMaster.setStatus(priorityMasterDto.getStatus());
			priorityMaster.setUpdatedBy(priorityMasterDto.getUpdatedBy());
			priorityMaster.setUpdatedDate(GlobalCommonDateUtils.getDate(priorityMasterDto.getUpdatedDate(), "dd-M-yyyy HH:mm:ss"));
			save(priorityMaster);
			return new Response<>(SUCCESS, SUCCESS_CODE, SUCCESS_MESSAGE, null, null);
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response getPriorityMasterById(int priorityId, int orgId) throws ApplicationException {
		try {
			List<PriorityMasterDto> priorityMasterDtolist = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_PRIORITY_MASTER_BY_ID")
					.setInteger("priorityId", priorityId)
					.setInteger("orgId", orgId)
					.setResultTransformer(Transformers.aliasToBean(PriorityMasterDto.class))
					.list();
			return new Response<>(SUCCESS, SUCCESS_CODE, SUCCESS_MESSAGE, priorityMasterDtolist, null);
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response getPriorityMasterList(PriorityMasterDto priorityMasterDto) throws ApplicationException {
		try {
			List<PriorityMasterDto> priorityMasterDtolist = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_PRIORITY_MASTER_LIST")
					.setInteger("orgId", priorityMasterDto.getOrganizationId())
					.setFirstResult(priorityMasterDto.getOffset() != null ? priorityMasterDto.getOffset() : 0)
					.setMaxResults(priorityMasterDto.getNoOfRecordsPerPage() != null ? priorityMasterDto.getNoOfRecordsPerPage() : 10)
					.setResultTransformer(Transformers.aliasToBean(PriorityMasterDto.class)).list();
			return new Response<>(SUCCESS, SUCCESS_CODE, SUCCESS_MESSAGE, priorityMasterDtolist, null);

		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response updatePriorityMaster(PriorityMasterDto priorityMasterDto) throws ApplicationException {
		try {
			PriorityMaster priorityMaster = findById(priorityMasterDto.getPriorityId());
			priorityMaster.setPriorityCode(priorityMasterDto.getPriorityCode());
			priorityMaster.setPriorityDesc(priorityMasterDto.getPriorityDesc());
			priorityMaster.setUpdatedBy(priorityMasterDto.getUpdatedBy());
			priorityMaster.setUpdatedDate(GlobalCommonDateUtils.getDate(priorityMasterDto.getUpdatedDate(), "dd-M-yyyy HH:mm:ss"));
			update(priorityMaster);
			return new Response<>(SUCCESS, SUCCESS_CODE, COMMON_UPDATE, null, null);
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response updatePriorityMasterStatus(PriorityMasterDto priorityMasterDto) throws ApplicationException {
		try {
			PriorityMaster priorityMaster=findById(priorityMasterDto.getPriorityId());
			priorityMaster.setStatus(priorityMasterDto.getStatus() == ACTIVE ? ACTIVE : INACTIVE);
			priorityMaster.setUpdatedBy(priorityMasterDto.getUpdatedBy());
			priorityMaster.setUpdatedDate(
					GlobalCommonDateUtils.getDate(priorityMasterDto.getUpdatedDate(), "dd-M-yyyy HH:mm:ss"));
			update(priorityMaster);
			return new Response<>(SUCCESS, SUCCESS_CODE, COMMON_UPDATE, null, null);

		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response getPriorityMasterCount(PriorityMasterDto priorityMasterDto) throws ApplicationException {
		try {
			Session session=sessionFactory.openSession();
			Criteria c=session.createCriteria(PriorityMaster.class); 
			c.add(Restrictions.eq("organizationId", priorityMasterDto.getOrganizationId()));
			c.setProjection(Projections.rowCount());  
			Long count = (Long)c.uniqueResult();
			return new Response(SUCCESS, null, null, null, count);
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response getPriorityMasterByName(PriorityMasterDto priorityMasterDto) throws ApplicationException {
		try {
			List<PriorityMasterDto> priorityMasterDtolist = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_PRIORITY_MASTER_BY_NAME")
					.setInteger("orgId", priorityMasterDto.getOrganizationId())
					.setString("priorityDesc", priorityMasterDto.getPriorityDesc())
					.setResultTransformer(Transformers.aliasToBean(PriorityMasterDto.class)).list();
			return new Response<>(SUCCESS, SUCCESS_CODE, SUCCESS_MESSAGE, priorityMasterDtolist, null);
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response getPriorityMasterByNameNotById(PriorityMasterDto priorityMasterDto) throws ApplicationException {
		try {
			List<PriorityMasterDto> priorityMasterDtolist = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_PRIORITY_MASTER_BY_NAME_NOT_BY_ID")
					.setInteger("priorityId", priorityMasterDto.getPriorityId())
					.setString("priorityDesc", priorityMasterDto.getPriorityDesc().toLowerCase())
					.setResultTransformer(Transformers.aliasToBean(PriorityMasterDto.class)).list();
			return new Response<>(SUCCESS, SUCCESS_CODE, SUCCESS_MESSAGE, priorityMasterDtolist, null);

		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

}
