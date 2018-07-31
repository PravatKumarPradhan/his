package com.param.global.dao;

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

import com.param.global.common.GlobalCommonDateUtils;
import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;
import com.param.global.dto.StateMasterDto;
import com.param.global.model.StateMaster;

import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;

@SuppressWarnings({ "rawtypes", "unchecked" })
@Repository
public class StateMasterDaoImpl extends GenericDao<StateMaster, Integer> implements IStateMasterDao, ICommonConstants {

	public StateMasterDaoImpl() {
		super(StateMaster.class);
		// TODO Auto-generated constructor stub
	}

	@Autowired
	private DozerBeanMapper mapper;

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Response getStateByName(StateMasterDto stateMasterDto) throws ApplicationException {
		try {
			List<StateMasterDto> stateMasterDtoList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_STATE_LIST_BY_NAME")
					.setString("stateName", stateMasterDto.getStateName().toLowerCase())
					.setResultTransformer(Transformers.aliasToBean(StateMasterDto.class)).list();
			return new Response(SUCCESS, null, null, stateMasterDtoList, null);

		} catch (HibernateException he) {
			he.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			 ;
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response addStateMaster(StateMasterDto stateMasterDto) throws ApplicationException {
		try {
			StateMaster stateMaster = mapper.map(stateMasterDto, StateMaster.class, "stateMasterDto_to_stateMaster");
			stateMaster = save(stateMaster);
			return new Response(SUCCESS, null, null, null, null);
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getStateMasterList(StateMasterDto stateMasterDto) throws ApplicationException {
		try {
			List<StateMasterDto> stateMasterDtoList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_STATE_LIST")
					.setInteger("orgId", stateMasterDto.getOrganizationId())
					.setFirstResult(stateMasterDto.getOffset() != null ? stateMasterDto.getOffset() : 0)
					.setMaxResults(stateMasterDto.getNoOfRecordsPerPage() != null ? stateMasterDto.getNoOfRecordsPerPage() : 10)
					.setResultTransformer(Transformers.aliasToBean(StateMasterDto.class)).list();
			return new Response(SUCCESS, null, null, stateMasterDtoList, null);

		} catch (HibernateException he) {
			he.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			 ;
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getStateByNameNotId(StateMasterDto stateMasterDto) throws ApplicationException {
		try {
			List<StateMasterDto> stateMasterDtoList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_STATE_LIST_BY_NAME_NOT_ID").setInteger("stateId", stateMasterDto.getStateId())
					.setString("stateName", stateMasterDto.getStateName().toLowerCase())
					.setResultTransformer(Transformers.aliasToBean(StateMasterDto.class)).list();
			return new Response(SUCCESS, null, null, stateMasterDtoList, null);

		} catch (HibernateException he) {
			he.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			 ;
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getStateById(StateMasterDto stateMasterDto) throws ApplicationException {
		try {
			List<StateMasterDto> stateMasterDtoList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_STATE_LIST_BY_ID").setInteger("stateId", stateMasterDto.getStateId())
					.setResultTransformer(Transformers.aliasToBean(StateMasterDto.class)).list();
			return new Response(SUCCESS, null, null, stateMasterDtoList, null);

		} catch (HibernateException he) {
			he.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			 ;
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response updateStateStatus(StateMasterDto stateMasterDto) throws ApplicationException {
		try {

			StateMaster stateMaster = findById(stateMasterDto.getStateId());
			stateMaster.setStatus(stateMasterDto.getStatus() == ACTIVE ? ACTIVE : INACTIVE);
			stateMaster.setUpdatedBy(stateMasterDto.getUpdatedBy());
			stateMaster.setUpdatedDate(GlobalCommonDateUtils.getDate(stateMasterDto.getUpdatedDate(), "dd-M-yyyy HH:mm:ss"));
			update(stateMaster);
			return new Response<StateMasterDto, Integer>(SUCCESS, null, COMMON_SUCCESS, null, null);

		} catch (HibernateException he) {
			he.printStackTrace();
			return new Response<StateMasterDto, Integer>(ERROR, null, null, null, null);
		} catch (Exception e) {
			e.printStackTrace();
			return new Response<StateMasterDto, Integer>(ERROR, null, null, null, null);
		} finally {
			 ;
		}
	}

	@Override
	public Response getActiveStateList() throws ApplicationException {
		try {
			List<StateMasterDto> stateMasterDtoList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_ACTIVE_STATE_LIST")
					.setResultTransformer(Transformers.aliasToBean(StateMasterDto.class)).list();
			return new Response(SUCCESS, null, null, stateMasterDtoList, null);

		} catch (HibernateException he) {
			he.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			 ;
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response updateStateMaster(StateMasterDto stateMasterDto) throws ApplicationException {
		try {
			StateMaster stateMaster = findById(stateMasterDto.getStateId());
			stateMaster.setStateName(stateMasterDto.getStateName());
			stateMaster.setStateCode(stateMasterDto.getStateCode());
			stateMaster.setCountryId(stateMasterDto.getCountryId());
			stateMaster.setUpdatedBy(stateMasterDto.getUpdatedBy());
			stateMaster.setUpdatedDate(GlobalCommonDateUtils.getDate(stateMasterDto.getUpdatedDate(), "dd-M-yyyy HH:mm:ss"));
			update(stateMaster);
			return new Response<StateMasterDto, Integer>(SUCCESS, null, COMMON_SUCCESS, null, null);

		} catch (HibernateException he) {
			he.printStackTrace();
			return new Response<StateMasterDto, Integer>(ERROR, null, null, null, null);
		} catch (Exception e) {
			e.printStackTrace();
			return new Response<StateMasterDto, Integer>(ERROR, null, null, null, null);
		} finally {
			 ;
		}
	}
	

	public Response getCount(StateMasterDto stateMasterDto)
	{
		try{
			Session session=sessionFactory.openSession();
			Criteria c=session.createCriteria(StateMaster.class); 
			c.add(Restrictions.eq("organizationId", stateMasterDto.getOrganizationId()));
			c.setProjection(Projections.rowCount());  
			Long count = (Long)c.uniqueResult();
			return new Response(SUCCESS, null, null, null, count);
		}catch(Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, null, null, null, null);
		}
	}

	@Override
	public Response getStateByCountryId(StateMasterDto stateMasterDto)throws ApplicationException {
		try {
			List<StateMasterDto> stateMasterDtoList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_STATE_BY_COUNTRY_ID").setInteger("countryId", stateMasterDto.getCountryId())
					.setResultTransformer(Transformers.aliasToBean(StateMasterDto.class)).list();
			return new Response(SUCCESS, null, null, stateMasterDtoList, null);

		} catch (HibernateException he) {
			he.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			 ;
		}
		return new Response(ERROR, null, null, null, null);
	}

}
