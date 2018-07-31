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
import com.param.adt.master.global.dto.NationalityMasterDto;
import com.param.adt.master.global.model.NationalityMaster;

import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;

@SuppressWarnings({ "rawtypes", "unchecked" })
@Repository
public class NationalityMasterDaoImpl extends GenericDao<NationalityMaster, Integer>
		implements INationalityMasterDao, ICommonConstants {

	public NationalityMasterDaoImpl() {
		super(NationalityMaster.class);
		// TODO Auto-generated constructor stub
	}

	@Autowired
	private DozerBeanMapper mapper;

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Response getNationalityByName(NationalityMasterDto nationalityMasterDto) throws ApplicationException {
		try {
			List<NationalityMasterDto> nationalityMasterDtoList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_NATIONALITY_LIST_BY_NAME")
					.setString("nationalityName", nationalityMasterDto.getNationalityName().toLowerCase())
					.setResultTransformer(Transformers.aliasToBean(NationalityMasterDto.class)).list();
			return new Response(SUCCESS, null, null, nationalityMasterDtoList, null);

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
	public Response getNationalityMasterList(NationalityMasterDto nationalityMasterDto) throws ApplicationException {
		try {
			List<NationalityMasterDto> nationalityMasterDtoList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_NATIONALITY_LIST")
					.setFirstResult(nationalityMasterDto.getOffset() != null ? nationalityMasterDto.getOffset() : 0)
					.setMaxResults(nationalityMasterDto.getNoOfRecordsPerPage() != null ? nationalityMasterDto.getNoOfRecordsPerPage() : 10)
					.setInteger("orgId", nationalityMasterDto.getOrganizationId())
					.setResultTransformer(Transformers.aliasToBean(NationalityMasterDto.class)).list();
			return new Response(SUCCESS, null, null, nationalityMasterDtoList, null);

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
	public Response getNationalityByID(NationalityMasterDto nationalityMasterDto) throws ApplicationException {
		try {
			List<NationalityMasterDto> nationalityMasterDtoList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_NATIONALITY_LIST_BY_ID")
					.setInteger("nationalityId", nationalityMasterDto.getNationalityId())
					.setResultTransformer(Transformers.aliasToBean(NationalityMasterDto.class)).list();
			return new Response(SUCCESS, null, null, nationalityMasterDtoList, null);

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
	public Response getActiveNationalityMasterList() throws ApplicationException {
		try {
			List<NationalityMasterDto> nationalityMasterDtoList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_ACTIVE_NATIONALITY_LIST")
					.setResultTransformer(Transformers.aliasToBean(NationalityMasterDto.class)).list();
			return new Response(SUCCESS, null, null, nationalityMasterDtoList, null);

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
	public Response getNationalityByNameNotId(NationalityMasterDto nationalityMasterDto) throws ApplicationException {
		try {
			List<NationalityMasterDto> nationalityMasterDtoList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_NATIONALITY_LIST_BY_NAME_NOT_ID")
					.setInteger("nationalityId", nationalityMasterDto.getNationalityId())
					.setString("nationalityName", nationalityMasterDto.getNationalityName().toLowerCase())
					.setResultTransformer(Transformers.aliasToBean(NationalityMasterDto.class)).list();
			return new Response(SUCCESS, null, null, nationalityMasterDtoList, null);

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
	public Response updateNationalityMaster(NationalityMasterDto nationalityMasterDto) throws ApplicationException {
		try {
			NationalityMaster nationalityMaster = findById(nationalityMasterDto.getNationalityId());
			nationalityMaster.setNationalityName(nationalityMasterDto.getNationalityName());
			nationalityMaster.setNationalityCode(nationalityMasterDto.getNationalityCode());
			nationalityMaster.setUpdatedBy(nationalityMasterDto.getUpdatedBy());
			nationalityMaster.setUpdatedDate(
					ADTCommonDateUtils.getDate(nationalityMasterDto.getUpdatedDate(), "dd-M-yyyy HH:mm:ss"));
			update(nationalityMaster);
			return new Response<NationalityMasterDto, Integer>(SUCCESS, null, COMMON_SUCCESS, null, null);

		} catch (HibernateException he) {
			he.printStackTrace();
			return new Response<NationalityMasterDto, Integer>(ERROR, null, null, null, null);
		} catch (Exception e) {
			e.printStackTrace();
			return new Response<NationalityMasterDto, Integer>(ERROR, null, null, null, null);
		} finally {
			 ;
		}
	}

	@Override
	public Response addNationalityTypeMaster(NationalityMasterDto nationalityMasterDto) throws ApplicationException {
		try {
			NationalityMaster nationalityMaster = mapper.map(nationalityMasterDto, NationalityMaster.class,
					"NationalityMasterDto_to_NationalityMaster");
			nationalityMaster = save(nationalityMaster);
			return new Response(SUCCESS, null, null, null, null);
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response updateNationalityStatus(NationalityMasterDto nationalityMasterDto) throws ApplicationException {
		try {
			NationalityMaster nationalityMaster = findById(nationalityMasterDto.getNationalityId());

			nationalityMaster.setStatus(nationalityMasterDto.getStatus() == ACTIVE ? ACTIVE : INACTIVE);
			nationalityMaster.setUpdatedBy(nationalityMasterDto.getUpdatedBy());
			nationalityMaster.setUpdatedDate(
					ADTCommonDateUtils.getDate(nationalityMasterDto.getUpdatedDate(), "dd-M-yyyy HH:mm:ss"));
			update(nationalityMaster);
			return new Response<NationalityMasterDto, Integer>(SUCCESS, null, COMMON_SUCCESS, null, null);

		} catch (HibernateException he) {
			he.printStackTrace();
			return new Response<NationalityMasterDto, Integer>(ERROR, null, null, null, null);
		} catch (Exception e) {
			e.printStackTrace();
			return new Response<NationalityMasterDto, Integer>(ERROR, null, null, null, null);
		} finally {
			 ;
		}

	}
	
	public Response getCount(NationalityMasterDto nationalityMasterDto)
	{
		try{
			Session session=sessionFactory.openSession();
			Criteria c=session.createCriteria(NationalityMaster.class); 
			c.add(Restrictions.eq("organizationId", nationalityMasterDto.getOrganizationId()));
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
