package com.param.global.dao;

import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;

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
import com.param.global.dto.RelationMasterDto;
import com.param.global.model.RelationMaster;

@SuppressWarnings({ "unchecked", "rawtypes" })
@Repository
public class RelationMasterDaoImpl extends GenericDao<RelationMaster, Integer>
		implements IRelationMasterDao, ICommonConstants {

	public RelationMasterDaoImpl() {
		super(RelationMaster.class);
		// TODO Auto-generated constructor stub
	}

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private DozerBeanMapper mapper;

	@Override
	public Response getCountryByName(RelationMasterDto relationMasterDto) throws ApplicationException {
		try {
			List<RelationMasterDto> relationMasterDtoList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_RELATION_LIST_BY_NAME")
					.setString("desc", relationMasterDto.getDesc().toLowerCase())
					.setResultTransformer(Transformers.aliasToBean(RelationMasterDto.class)).list();
			return new Response(SUCCESS, null, null, relationMasterDtoList, null);

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
	public Response addCountryMaster(RelationMasterDto relationMasterDto) throws ApplicationException {
		try {
			RelationMaster relationMaster = mapper.map(relationMasterDto, RelationMaster.class,
					"RelationMasterDto_to_RelationMaster");
			relationMaster = save(relationMaster);
			return new Response(SUCCESS, null, null, null, null);
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getRelationMasterList(RelationMasterDto relationMasterDto) throws ApplicationException {
		try {
			List<RelationMasterDto> relationMasterDtoList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_RELATION_LIST")
					.setInteger("orgId", relationMasterDto.getOrganizationId())
					.setFirstResult(relationMasterDto.getOffset() != null ? relationMasterDto.getOffset() : 0)
					.setMaxResults(relationMasterDto.getNoOfRecordsPerPage() != null ? relationMasterDto.getNoOfRecordsPerPage() : 10)
					.setResultTransformer(Transformers.aliasToBean(RelationMasterDto.class)).list();
			return new Response(SUCCESS, null, null, relationMasterDtoList, null);

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
	public Response getRelationById(RelationMasterDto relationMasterDto) throws ApplicationException {
		try {
			List<RelationMasterDto> relationMasterDtoList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_RELATION_LIST_BY_ID").setInteger("id", relationMasterDto.getId())
					.setResultTransformer(Transformers.aliasToBean(RelationMasterDto.class)).list();
			return new Response(SUCCESS, null, null, relationMasterDtoList, null);

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
	public Response getActiveRelationList() throws ApplicationException {
		try {
			List<RelationMasterDto> relationMasterDtoList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_ACTIVE_RELATION_LIST")
					.setResultTransformer(Transformers.aliasToBean(RelationMasterDto.class)).list();
			return new Response(SUCCESS, null, null, relationMasterDtoList, null);

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
	public Response getRelationByNameNotId(RelationMasterDto relationMasterDto) throws ApplicationException {
		try {
			List<RelationMasterDto> relationMasterDtoList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_RELATION_LIST_BY_NAME_NOT_ID")
					.setString("desc", relationMasterDto.getDesc())
					.setInteger("id", relationMasterDto.getId())
					.setResultTransformer(Transformers.aliasToBean(RelationMasterDto.class)).list();
			return new Response(SUCCESS, null, null, relationMasterDtoList, null);

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
	public Response updateRelationMaster(RelationMasterDto relationMasterDto) throws ApplicationException {
		try {

			RelationMaster relationMaster = findById(relationMasterDto.getId());
			relationMaster.setCode(relationMasterDto.getCode());
			relationMaster.setDesc(relationMasterDto.getDesc());
			relationMaster.setUpdatedBy(relationMasterDto.getUpdatedBy());
			relationMaster
					.setUpdatedDate(GlobalCommonDateUtils.getDate(relationMasterDto.getUpdatedDate(), "dd-M-yyyy HH:mm:ss"));
			update(relationMaster);
			return new Response<RelationMasterDto, Integer>(SUCCESS, null, COMMON_SUCCESS, null, null);

		} catch (HibernateException he) {
			he.printStackTrace();
			return new Response<RelationMasterDto, Integer>(ERROR, null, null, null, null);
		} catch (Exception e) {
			e.printStackTrace();
			return new Response<RelationMasterDto, Integer>(ERROR, null, null, null, null);
		} finally {
			 ;
		}
	}

	@Override
	public Response updateRelationStatus(RelationMasterDto relationMasterDto) throws ApplicationException {
		try {

			RelationMaster relationMaster = findById(relationMasterDto.getId());
			relationMaster.setUpdatedBy(relationMasterDto.getUpdatedBy());
			relationMaster.setStatus(relationMasterDto.getStatus() == ACTIVE ? ACTIVE : INACTIVE);
			relationMaster
					.setUpdatedDate(GlobalCommonDateUtils.getDate(relationMasterDto.getUpdatedDate(), "dd-M-yyyy HH:mm:ss"));
			update(relationMaster);
			return new Response<RelationMasterDto, Integer>(SUCCESS, null, COMMON_SUCCESS, null, null);

		} catch (HibernateException he) {
			he.printStackTrace();
			return new Response<RelationMasterDto, Integer>(ERROR, null, null, null, null);
		} catch (Exception e) {
			e.printStackTrace();
			return new Response<RelationMasterDto, Integer>(ERROR, null, null, null, null);
		} finally {
			 ;
		}
	}
	
	@Override
	public Response getCount(RelationMasterDto relationMasterDto)
	{
		try{
			Session session=sessionFactory.openSession();
			Criteria c=session.createCriteria(RelationMaster.class); 
			c.add(Restrictions.eq("organizationId", relationMasterDto.getOrganizationId()));
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
