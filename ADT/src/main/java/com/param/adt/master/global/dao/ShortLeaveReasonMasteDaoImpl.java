package com.param.adt.master.global.dao;

import java.util.List;

import org.dozer.DozerBeanMapper;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.param.adt.common.ADTCommonDateUtils;
import com.param.adt.common.ICommonConstants;
import com.param.adt.common.Response;
import com.param.adt.master.global.dto.ShortLeaveReasonMasterDto;
import com.param.adt.master.global.model.ShortLeaveReasonMaster;

import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;

@Repository
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ShortLeaveReasonMasteDaoImpl extends GenericDao<ShortLeaveReasonMaster, Integer>
		implements IShortLeaveReasonMasterDao, ICommonConstants {

	public ShortLeaveReasonMasteDaoImpl() {
		super(ShortLeaveReasonMaster.class);
		// TODO Auto-generated constructor stub
	}

	@Autowired
	SessionFactory sessionFactory;

	@Autowired
	DozerBeanMapper mapper;

	@Override
	public Response getShortLeaveReasonByName(ShortLeaveReasonMasterDto shortLeaveReasonMasterDto)
			throws ApplicationException {
		try {
			List<ShortLeaveReasonMasterDto> leaveReasonMasterDtosList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_SHORT_LEAVE_LIST_BY_NAME")
					.setString("shortLeaveReasonName",
							shortLeaveReasonMasterDto.getShortLeaveReasonName().toLowerCase())
					.setResultTransformer(Transformers.aliasToBean(ShortLeaveReasonMasterDto.class)).list();
			if (leaveReasonMasterDtosList != null) {
				return new Response(SUCCESS, null, null, leaveReasonMasterDtosList, null);
			}
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response addShortLeaveReasonMaster(ShortLeaveReasonMasterDto shortLeaveReasonMasterDto)
			throws ApplicationException {
		try {
			ShortLeaveReasonMaster shortLeaveReasonMaster = mapper.map(shortLeaveReasonMasterDto,
					ShortLeaveReasonMaster.class, "ShortLeaveReasonMasterDto_to_ShortLeaveReasonMaster");
			shortLeaveReasonMaster = save(shortLeaveReasonMaster);
			return new Response(SUCCESS, null, null, null, null);
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getShortLeaveReasonByNameNotId(ShortLeaveReasonMasterDto shortLeaveReasonMasterDto)
			throws ApplicationException {
		try {
			List<ShortLeaveReasonMasterDto> leaveReasonMasterDtosList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_SHORT_LEAVE_LIST_BY_NAME_NOT_ID")
					.setString("shortLeaveReasonName",
							shortLeaveReasonMasterDto.getShortLeaveReasonName().toLowerCase())
					.setInteger("shortLeaveReasonId", shortLeaveReasonMasterDto.getShortLeaveReasonId())
					.setResultTransformer(Transformers.aliasToBean(ShortLeaveReasonMasterDto.class)).list();
			if (leaveReasonMasterDtosList != null) {
				return new Response(SUCCESS, null, null, leaveReasonMasterDtosList, null);
			}
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response updateShortLeaveReasonMaster(ShortLeaveReasonMasterDto shortLeaveReasonMasterDto)
			throws ApplicationException {
		try {
			ShortLeaveReasonMaster shortLeaveReasonMaster = findById(shortLeaveReasonMasterDto.getShortLeaveReasonId());
			shortLeaveReasonMaster.setShortLeaveReasonName(shortLeaveReasonMasterDto.getShortLeaveReasonName());
			shortLeaveReasonMaster.setShortLeaveReasonCode(shortLeaveReasonMasterDto.getShortLeaveReasonCode());
			shortLeaveReasonMaster.setUpdatedBy(shortLeaveReasonMasterDto.getUpdatedBy());
			shortLeaveReasonMaster.setUpdatedDate(
					ADTCommonDateUtils.getDate(shortLeaveReasonMasterDto.getUpdatedDate(), "dd-M-yyy HH:mm:ss"));
			update(shortLeaveReasonMaster);
			return new Response<ShortLeaveReasonMasterDto, Integer>(SUCCESS, null, COMMON_SUCCESS, null, null);

		} catch (HibernateException he) {
			he.printStackTrace();
			return new Response<ShortLeaveReasonMasterDto, Integer>(ERROR, null, null, null, null);
		} catch (Exception e) {
			e.printStackTrace();
			return new Response<ShortLeaveReasonMasterDto, Integer>(ERROR, null, null, null, null);
		} finally {
			 ;
		}
	}

	@Override
	public Response getShortLeaveReasonById(ShortLeaveReasonMasterDto shortLeaveReasonMasterDto)
			throws ApplicationException {
		try {
			List<ShortLeaveReasonMasterDto> leaveReasonMasterDtosList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_SHORT_LEAVE_LIST_BY_ID")
					.setInteger("shortLeaveReasonId", shortLeaveReasonMasterDto.getShortLeaveReasonId())
					.setResultTransformer(Transformers.aliasToBean(ShortLeaveReasonMasterDto.class)).list();
			if (leaveReasonMasterDtosList != null) {
				return new Response(SUCCESS, null, null, leaveReasonMasterDtosList, null);
			}
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response updateShortLeaveReasonStatus(ShortLeaveReasonMasterDto shortLeaveReasonMasterDto)
			throws ApplicationException {
		try {
			ShortLeaveReasonMaster shortLeaveReasonMaster = findById(shortLeaveReasonMasterDto.getShortLeaveReasonId());

			shortLeaveReasonMaster.setStatus(shortLeaveReasonMasterDto.getStatus() == ACTIVE ? ACTIVE : INACTIVE);
			shortLeaveReasonMaster.setUpdatedBy(shortLeaveReasonMasterDto.getUpdatedBy());
			shortLeaveReasonMaster.setUpdatedDate(
					ADTCommonDateUtils.getDate(shortLeaveReasonMasterDto.getUpdatedDate(), "dd-M-yyy HH:mm:ss"));
			update(shortLeaveReasonMaster);
			return new Response<ShortLeaveReasonMasterDto, Integer>(SUCCESS, null, COMMON_SUCCESS, null, null);

		} catch (HibernateException he) {
			he.printStackTrace();
			return new Response<ShortLeaveReasonMasterDto, Integer>(ERROR, null, null, null, null);
		} catch (Exception e) {
			e.printStackTrace();
			return new Response<ShortLeaveReasonMasterDto, Integer>(ERROR, null, null, null, null);
		} finally {
			 ;
		}
	}

	@Override
	public Response getActiveShortLeaveReasonList(ShortLeaveReasonMasterDto shortLeaveReasonMasterDto) throws ApplicationException {
		try {
			List<ShortLeaveReasonMasterDto> leaveReasonMasterDtosList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_ACTIVE_SHORT_LEAVE_LIST")
					.setInteger("orgId", shortLeaveReasonMasterDto.getOrganizationId())
					.setResultTransformer(Transformers.aliasToBean(ShortLeaveReasonMasterDto.class)).list();
			if (leaveReasonMasterDtosList != null) {
				return new Response(SUCCESS, null, null, leaveReasonMasterDtosList, null);
			}
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getShortLeaveReasonMasterList(ShortLeaveReasonMasterDto shortLeaveReasonMasterDto)
			throws ApplicationException {
		try {
			List<ShortLeaveReasonMasterDto> leaveReasonMasterDtosList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_SHORT_LEAVE_LIST")
					.setFirstResult(
							shortLeaveReasonMasterDto.getOffset() != null ? shortLeaveReasonMasterDto.getOffset() : 0)
					.setMaxResults(shortLeaveReasonMasterDto.getNoOfRecordsPerPage() != null
							? shortLeaveReasonMasterDto.getNoOfRecordsPerPage() : 10)
					.setInteger("orgId", shortLeaveReasonMasterDto.getOrganizationId())
					.setResultTransformer(Transformers.aliasToBean(ShortLeaveReasonMasterDto.class)).list();
			if (leaveReasonMasterDtosList != null) {
				return new Response(SUCCESS, null, null, leaveReasonMasterDtosList, null);
			}
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getCount(ShortLeaveReasonMasterDto shortLeaveReasonMasterDto) throws ApplicationException {
		try {
			Session session = sessionFactory.openSession();
			Criteria c = session.createCriteria(ShortLeaveReasonMaster.class);
			c.add(Restrictions.eq("organizationId", shortLeaveReasonMasterDto.getOrganizationId()));
			c.setProjection(Projections.rowCount());
			Long count = (Long) c.uniqueResult();
			return new Response(SUCCESS, null, null, null, count);
		} catch (Exception e) {
			e.printStackTrace();
			return new Response(ERROR, null, null, null, null);

		}
	}

}
