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
import com.param.adt.master.global.dto.HierarchyMasterDto;
import com.param.adt.master.global.model.HierarchyMaster;

import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;

@Repository
@SuppressWarnings({ "rawtypes", "unchecked" })
public class HierarchyMasterDaoImpl extends GenericDao<HierarchyMaster, Integer>
		implements IHierarchyMasterDao, ICommonConstants {

	public HierarchyMasterDaoImpl() {
		super(HierarchyMaster.class);
		// TODO Auto-generated constructor stub
	}

	@Autowired
	SessionFactory sessionFactory;

	@Autowired
	DozerBeanMapper mapper;

	@Override
	public Response getHierarchyByName(HierarchyMasterDto hierarchyMasterDto) throws ApplicationException {
		try {
			List<HierarchyMasterDto> hierarchyMasterDtosList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_HIERARCHY_LIST_BY_NAME")
					.setString("hierarchyDesc", hierarchyMasterDto.getHierarchyDesc().toLowerCase())
					.setResultTransformer(Transformers.aliasToBean(HierarchyMasterDto.class)).list();
			if (hierarchyMasterDtosList != null) {
				return new Response(SUCCESS, null, null, hierarchyMasterDtosList, null);
			}
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response addHierarchyTypeMaster(HierarchyMasterDto hierarchyMasterDto) throws ApplicationException {
		try {
			HierarchyMaster hierarchyMaster = mapper.map(hierarchyMasterDto, HierarchyMaster.class,
					"HierarchyMasterDto_to_HierarchyMaster");
			hierarchyMaster = save(hierarchyMaster);
			return new Response(SUCCESS, null, null, null, null);
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getHierarchyMasterList(HierarchyMasterDto hierarchyMasterDto) throws ApplicationException {
		try {
			List<HierarchyMasterDto> hierarchyMasterDtosList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_HIERARCHY_LIST")
					.setFirstResult(hierarchyMasterDto.getOffset() != null ? hierarchyMasterDto.getOffset() : 0)
					.setMaxResults(hierarchyMasterDto.getNoOfRecordsPerPage() != null ? hierarchyMasterDto.getNoOfRecordsPerPage() : 10)
					.setInteger("orgId", hierarchyMasterDto.getOrganizationId())
					.setResultTransformer(Transformers.aliasToBean(HierarchyMasterDto.class)).list();
			if (hierarchyMasterDtosList != null) {
				return new Response(SUCCESS, null, null, hierarchyMasterDtosList, null);
			}
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getHierarchyById(HierarchyMasterDto hierarchyMasterDto) throws ApplicationException {
		try {
			List<HierarchyMasterDto> hierarchyMasterDtosList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_HIERARCHY_LIST_BY_ID")
					.setInteger("hierarchyId", hierarchyMasterDto.getHierarchyId())
					.setResultTransformer(Transformers.aliasToBean(HierarchyMasterDto.class)).list();
			if (hierarchyMasterDtosList != null) {
				return new Response(SUCCESS, null, null, hierarchyMasterDtosList, null);
			}
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getActiveHierarchyList() throws ApplicationException {
		try {
			List<HierarchyMasterDto> hierarchyMasterDtosList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_ACTIVE_HIERARCHY_LIST")
					.setResultTransformer(Transformers.aliasToBean(HierarchyMasterDto.class)).list();
			if (hierarchyMasterDtosList != null) {
				return new Response(SUCCESS, null, null, hierarchyMasterDtosList, null);
			}
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getHierarchyByNameNotId(HierarchyMasterDto hierarchyMasterDto) throws ApplicationException {
		try {
			List<HierarchyMasterDto> hierarchyMasterDtosList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_HIERARCHY_LIST_BY_NAME_NOT_ID")
					.setInteger("hierarchyId", hierarchyMasterDto.getHierarchyId())
					.setString("hierarchyDesc", hierarchyMasterDto.getHierarchyDesc().toLowerCase())
					.setResultTransformer(Transformers.aliasToBean(HierarchyMasterDto.class)).list();
			if (hierarchyMasterDtosList != null) {
				return new Response(SUCCESS, null, null, hierarchyMasterDtosList, null);
			}
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response updateHierarchyMaster(HierarchyMasterDto hierarchyMasterDto) throws ApplicationException {
		try {
			HierarchyMaster hierarchyMaster = findById(hierarchyMasterDto.getHierarchyId());
			hierarchyMaster.setHierarchyDesc(hierarchyMasterDto.getHierarchyDesc());
			hierarchyMaster.setHierarchyCode(hierarchyMasterDto.getHierarchyCode());
			hierarchyMaster.setUpdatedBy(hierarchyMasterDto.getUpdatedBy());
			hierarchyMaster.setUpdatedDate(ADTCommonDateUtils.getDate(hierarchyMasterDto.getUpdatedDate(),"dd-M-yyy HH:mm:ss"));
			update(hierarchyMaster);
			return new Response<HierarchyMasterDto, Integer>(SUCCESS, null, COMMON_SUCCESS, null, null);

		} catch (HibernateException he) {
			he.printStackTrace();
			return new Response<HierarchyMasterDto, Integer>(ERROR, null, null, null, null);
		} catch (Exception e) {
			e.printStackTrace();
			return new Response<HierarchyMasterDto, Integer>(ERROR, null, null, null, null);
		} finally {
			 ;
		}
	}

	@Override
	public Response updateHolidayStatus(HierarchyMasterDto hierarchyMasterDto) throws ApplicationException {
		try {
			HierarchyMaster hierarchyMaster = findById(hierarchyMasterDto.getHierarchyId());

			hierarchyMaster.setStatus(hierarchyMasterDto.getStatus() == ACTIVE ? ACTIVE : INACTIVE);
			hierarchyMaster.setUpdatedBy(hierarchyMasterDto.getUpdatedBy());
			hierarchyMaster.setUpdatedDate(ADTCommonDateUtils.getDate(hierarchyMasterDto.getUpdatedDate(),"dd-M-yyy HH:mm:ss"));
			update(hierarchyMaster);
			return new Response<HierarchyMasterDto, Integer>(SUCCESS, null, COMMON_SUCCESS, null, null);

		} catch (HibernateException he) {
			he.printStackTrace();
			return new Response<HierarchyMasterDto, Integer>(ERROR, null, null, null, null);
		} catch (Exception e) {
			e.printStackTrace();
			return new Response<HierarchyMasterDto, Integer>(ERROR, null, null, null, null);
		} finally {
			 ;
		}

	}
	
	@Override
	public Response getCount(HierarchyMasterDto hierarchyMasterDto) throws ApplicationException {
		try{
			Session session=sessionFactory.openSession();
			Criteria c=session.createCriteria(HierarchyMaster.class); 
			c.add(Restrictions.eq("organizationId", hierarchyMasterDto.getOrganizationId()));
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
