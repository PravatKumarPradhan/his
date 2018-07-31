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
import com.param.adt.master.global.dto.MaritalStatusDto;
import com.param.adt.master.global.model.MaritalStatusMaster;

import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;

@Repository
@SuppressWarnings({"rawtypes","unchecked"})
public class MaritalStatusMasterDaoImpl extends GenericDao<MaritalStatusMaster, Integer>
		implements IMaritalStatusMasterDao, ICommonConstants {

	@Autowired
	private DozerBeanMapper mapper;

	@Autowired
	private SessionFactory sessionFactory;

	public MaritalStatusMasterDaoImpl() {
		super(MaritalStatusMaster.class);
		// TODO Auto-generated constructor stub
	}

	
	@Override
	public Response getMaritalStatusByName(MaritalStatusDto maritalStatusDto) throws ApplicationException {
		try {
			List<MaritalStatusDto> maritalStatusMasterDtoList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_MARITAL_STATUS_BY_NAME")
					.setString("desc", maritalStatusDto.getDesc().toLowerCase())
					.setResultTransformer(Transformers.aliasToBean(MaritalStatusDto.class)).list();
			return new Response(SUCCESS, null, null, maritalStatusMasterDtoList, null);

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
	public Response addMaritalStatusMaster(MaritalStatusDto maritalStatusDto) throws ApplicationException {
		try {
			MaritalStatusMaster maritalStatusMaster = mapper.map(maritalStatusDto, MaritalStatusMaster.class,
					"maritalStatusDto_to_maritalStatusMaster");
			maritalStatusMaster = save(maritalStatusMaster);
			return new Response(SUCCESS, null, null, null, null);
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getMaritalStatusByNameNotId(MaritalStatusDto maritalStatusDto) throws ApplicationException {
		try {
			List<MaritalStatusDto> maritalMasterDtoList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_MARITAL_STATUS_BY_NAME_NOT_ID")
					.setInteger("id", maritalStatusDto.getId())
					.setString("desc", maritalStatusDto.getDesc().toLowerCase())
					.setResultTransformer(Transformers.aliasToBean(MaritalStatusDto.class)).list();
			return new Response(SUCCESS, null, null, maritalMasterDtoList, null);

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
	public Response updateMaritalStatus(MaritalStatusDto maritalStatusDto) throws ApplicationException {
		try {
			MaritalStatusMaster maritalMaster = findById(maritalStatusDto.getId());
			maritalMaster.setUpdatedBy(maritalStatusDto.getUpdatedBy());
			maritalMaster.setStatus(maritalStatusDto.getStatus() == ACTIVE ? ACTIVE : INACTIVE);
			maritalMaster
					.setUpdatedDate(ADTCommonDateUtils.getDate(maritalStatusDto.getUpdatedDate(), "dd-M-yyyy HH:mm:ss"));
			update(maritalMaster);
			update(maritalMaster);
			return new Response<MaritalStatusDto, Integer>(SUCCESS, null, COMMON_SUCCESS, null, null);

		} catch (HibernateException he) {
			he.printStackTrace();
			return new Response<MaritalStatusDto, Integer>(ERROR, null, null, null, null);
		} catch (Exception e) {
			e.printStackTrace();
			return new Response<MaritalStatusDto, Integer>(ERROR, null, null, null, null);
		} finally {
			 ;
		}
		
	}

	@Override
	public Response getMaritalStatusByID(MaritalStatusDto maritalStatusDto) throws ApplicationException {
		try {
			List<MaritalStatusDto> maritalMasterDtoList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_MARITAL_STATUS_BY_ID")
					.setInteger("id", maritalStatusDto.getId())
					.setResultTransformer(Transformers.aliasToBean(MaritalStatusDto.class)).list();
			return new Response(SUCCESS, null, null, maritalMasterDtoList, null);

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
	public Response getActiveMaritalStatusList() throws ApplicationException {
		try {
			List<MaritalStatusDto> maritalMasterDtoList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_ACTIVE_MARITAL_STATUS_LIST")
					.setResultTransformer(Transformers.aliasToBean(MaritalStatusDto.class)).list();
			return new Response(SUCCESS, null, null, maritalMasterDtoList, null);

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
	public Response getMaritalStatusMasterList(MaritalStatusDto maritalStatusDto) throws ApplicationException {
		try {
			List<MaritalStatusDto> maritalStatusMasterDtoList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_MARITAL_STATUS_LIST")
					.setInteger("orgId",maritalStatusDto.getOrganizationId())
					.setFirstResult(maritalStatusDto.getOffset() != null ? maritalStatusDto.getOffset() : 0)
					.setMaxResults(maritalStatusDto.getNoOfRecordsPerPage() != null ? maritalStatusDto.getNoOfRecordsPerPage() : 10)
					.setResultTransformer(Transformers.aliasToBean(MaritalStatusDto.class)).list();
			return new Response(SUCCESS, null, null, maritalStatusMasterDtoList, null);

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
	public Response updateMaritalStatusMaster(MaritalStatusDto maritalStatusDto) throws ApplicationException {
	
		try {

			MaritalStatusMaster maritalMaster = findById(maritalStatusDto.getId());
			maritalMaster.setDesc(maritalStatusDto.getDesc());
			maritalMaster.setCode(maritalStatusDto.getCode());
			maritalMaster.setUpdatedBy(maritalStatusDto.getUpdatedBy());
			maritalMaster
					.setUpdatedDate(ADTCommonDateUtils.getDate(maritalStatusDto.getUpdatedDate(), "dd-M-yyyy HH:mm:ss"));
			update(maritalMaster);
			return new Response<MaritalStatusDto, Integer>(SUCCESS, null, COMMON_SUCCESS, null, null);

		} catch (HibernateException he) {
			he.printStackTrace();
			return new Response<MaritalStatusDto, Integer>(ERROR, null, null, null, null);
		} catch (Exception e) {
			e.printStackTrace();
			return new Response<MaritalStatusDto, Integer>(ERROR, null, null, null, null);
		} finally {
			 ;
		}
	}
	
	@Override
	public Response getCount(MaritalStatusDto maritalStatusDto)
	{
		try{
			Session session=sessionFactory.openSession();
			Criteria c=session.createCriteria(MaritalStatusMaster.class); 
			c.add(Restrictions.eq("organizationId", maritalStatusDto.getOrganizationId()));
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
