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
import com.param.adt.master.global.dto.ReligionMasterDto;
import com.param.adt.master.global.model.ReligionMaster;

import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;

@Repository
@SuppressWarnings({ "unchecked", "rawtypes" })
public class ReligionMasterDaoImpl extends GenericDao<ReligionMaster, Integer>
		implements IReligionMasterDao, ICommonConstants {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private DozerBeanMapper mapper;

	public ReligionMasterDaoImpl() {
		super(ReligionMaster.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Response getReligionByName(ReligionMasterDto religionMasterDto) throws ApplicationException {
		try {

			List<ReligionMasterDto> religionMasterDtoList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_RELIGION_LIST_BY_NAME")
					.setString("desc", religionMasterDto.getDesc().toLowerCase())
					.setResultTransformer(Transformers.aliasToBean(ReligionMasterDto.class)).list();
			return new Response(SUCCESS, null, null, religionMasterDtoList, null);

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
	public Response addReligionMaster(ReligionMasterDto religionMasterDto) throws ApplicationException {
		try {
			ReligionMaster religionMaster = mapper.map(religionMasterDto, ReligionMaster.class, "ReligionMasterDto_to_ReligionMaster");
			religionMaster = save(religionMaster);
			return new Response(SUCCESS, null, null, null, null);
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getReligionMasterList(ReligionMasterDto religionMasterDto) throws ApplicationException {
		try {

			List<ReligionMasterDto> religionMasterDtoList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_RELIGION_LIST")
					.setInteger("orgId", religionMasterDto.getOrganizationId())
					.setFirstResult(religionMasterDto.getOffset() != null ? religionMasterDto.getOffset() : 0)
					.setMaxResults(religionMasterDto.getNoOfRecordsPerPage() != null ? religionMasterDto.getNoOfRecordsPerPage() : 10)
					.setResultTransformer(Transformers.aliasToBean(ReligionMasterDto.class)).list();
			return new Response(SUCCESS, null, null, religionMasterDtoList, null);

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
	public Response getReligionById(ReligionMasterDto religionMasterDto) throws ApplicationException {
		try {

			List<ReligionMasterDto> religionMasterDtoList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_RELIGION_LIST_BY_ID")
					.setInteger("id", religionMasterDto.getId())
					.setResultTransformer(Transformers.aliasToBean(ReligionMasterDto.class)).list();
			return new Response(SUCCESS, null, null, religionMasterDtoList, null);

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
	public Response getActiveReligionList() throws ApplicationException {
		try {

			List<ReligionMasterDto> religionMasterDtoList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_ACTIVE_RELIGION_LIST")
					.setResultTransformer(Transformers.aliasToBean(ReligionMasterDto.class)).list();
			return new Response(SUCCESS, null, null, religionMasterDtoList, null);

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
	public Response getReligionByNameNotId(ReligionMasterDto religionMasterDto) throws ApplicationException {
		try {

			List<ReligionMasterDto> religionMasterDtoList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_RELIGION_LIST_BY_NAME_NOT_ID")
					.setString("desc", religionMasterDto.getDesc())
					.setInteger("id", religionMasterDto.getId())
					.setResultTransformer(Transformers.aliasToBean(ReligionMasterDto.class)).list();
			return new Response(SUCCESS, null, null, religionMasterDtoList, null);

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
	public Response updateReligionStatus(ReligionMasterDto religionMasterDto) throws ApplicationException {
		try {
			ReligionMaster religionMaster = findById(religionMasterDto.getId());
			religionMaster.setUpdatedBy(religionMasterDto.getUpdatedBy());
			religionMaster.setUpdatedDate(ADTCommonDateUtils.getDate(religionMasterDto.getUpdatedDate(),"dd-M-yyyy HH:mm:ss"));
			religionMaster.setStatus(religionMasterDto.getStatus() == ACTIVE ? ACTIVE : INACTIVE);
			update(religionMaster);
			return new Response<ReligionMasterDto, Integer>(SUCCESS, null, COMMON_SUCCESS, null, null);

		} catch (HibernateException he) {
			he.printStackTrace();
			return new Response<ReligionMasterDto, Integer>(ERROR, null, null, null, null);
		} catch (Exception e) {
			e.printStackTrace();
			return new Response<ReligionMasterDto, Integer>(ERROR, null, null, null, null);
		} finally {
			 ;
		}
	}

	@Override
	public Response updateReligionMaster(ReligionMasterDto religionMasterDto) throws ApplicationException {
		try {
			ReligionMaster religionMaster = findById(religionMasterDto.getId());
			religionMaster.setDesc(religionMasterDto.getDesc());
			religionMaster.setCode(religionMasterDto.getCode());
			religionMaster.setUpdatedBy(religionMasterDto.getUpdatedBy());
			religionMaster.setUpdatedDate(ADTCommonDateUtils.getDate(religionMasterDto.getUpdatedDate(),"dd-M-yyyy HH:mm:ss"));
			update(religionMaster);
			return new Response<ReligionMasterDto, Integer>(SUCCESS, null, COMMON_SUCCESS, null, null);

		} catch (HibernateException he) {
			he.printStackTrace();
			return new Response<ReligionMasterDto, Integer>(ERROR, null, null, null, null);
		} catch (Exception e) {
			e.printStackTrace();
			return new Response<ReligionMasterDto, Integer>(ERROR, null, null, null, null);
		} finally {
			 ;
		}
	}
	
	@Override
	public Response getCount(ReligionMasterDto religionMasterDto)
	{
		try{
			Session session=sessionFactory.openSession();
			Criteria c=session.createCriteria(ReligionMaster.class); 
			c.add(Restrictions.eq("organizationId", religionMasterDto.getOrganizationId()));
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
