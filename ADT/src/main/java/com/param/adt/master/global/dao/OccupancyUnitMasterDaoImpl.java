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
import com.param.adt.master.global.dto.OccupancyUnitMasterDto;
import com.param.adt.master.global.model.OccupancyUnitMaster;

import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;
@Repository
@SuppressWarnings({"rawtypes","unchecked"})
public class OccupancyUnitMasterDaoImpl extends GenericDao<OccupancyUnitMaster, Integer>implements IOccupancyUnitMasterDao,ICommonConstants{

	public OccupancyUnitMasterDaoImpl() 
	{
		super(OccupancyUnitMaster.class);
	}

	@Autowired
	SessionFactory sessionFactory;
	
	@Autowired
	DozerBeanMapper mapper;
	
	@Override
	public Response getOccupancyUnitByName(OccupancyUnitMasterDto occupancyUnitMasterDto) throws ApplicationException {
		try {
			List<OccupancyUnitMasterDto> occupancyUnitMasterDtosList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_OCCUPANCY_UNIT_LIST_BY_NAME")
					.setString("occDesc", occupancyUnitMasterDto.getOccupancyUnitDesc().toLowerCase())
					.setResultTransformer(Transformers.aliasToBean(OccupancyUnitMasterDto.class)).list();
			if (occupancyUnitMasterDtosList != null) {
				return new Response(SUCCESS, null, null, occupancyUnitMasterDtosList, null);
			}
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response addOccupancyUnitTypeMaster(OccupancyUnitMasterDto occupancyUnitMasterDto)
			throws ApplicationException {
		try {
			OccupancyUnitMaster occupancyUnitMaster = mapper.map(occupancyUnitMasterDto, OccupancyUnitMaster.class,
					"OccupancyUnitMasterDto_to_OccupancyUnitMaster");
			occupancyUnitMaster = save(occupancyUnitMaster);
			return new Response(SUCCESS, null, null, null, null);
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getOccupancyUnitByNameNotId(OccupancyUnitMasterDto occupancyUnitMasterDto)
			throws ApplicationException {
		try {
			List<OccupancyUnitMasterDto> occupancyUnitMasterDtosList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_OCCUPANCY_UNIT_LIST_BY_NAME_NOT_ID")
					.setInteger("occId", occupancyUnitMasterDto.getOccupancyUnitId())
					.setString("occDesc", occupancyUnitMasterDto.getOccupancyUnitDesc().toLowerCase())
					.setResultTransformer(Transformers.aliasToBean(OccupancyUnitMasterDto.class)).list();
			if (occupancyUnitMasterDtosList != null) {
				return new Response(SUCCESS, null, null, occupancyUnitMasterDtosList, null);
			}
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response updateOccupancyUnitMaster(OccupancyUnitMasterDto occupancyUnitMasterDto)
			throws ApplicationException {
		try {
			OccupancyUnitMaster occupancyUnitMaster = findById(occupancyUnitMasterDto.getOccupancyUnitId());

			occupancyUnitMaster.setOccupancyUnitCode(occupancyUnitMasterDto.getOccupancyUnitCode());
			occupancyUnitMaster.setOccupancyUnitDesc(occupancyUnitMasterDto.getOccupancyUnitDesc());
			occupancyUnitMaster.setNumberOfHours(occupancyUnitMasterDto.getNumberOfHours());
			
			occupancyUnitMaster.setUpdatedBy(occupancyUnitMasterDto.getUpdatedBy());
			occupancyUnitMaster.setUpdatedDate(ADTCommonDateUtils.getDate(occupancyUnitMasterDto.getUpdatedDate(),"dd-M-yyy HH:mm:ss"));
			update(occupancyUnitMaster);
			return new Response<OccupancyUnitMasterDto, Integer>(SUCCESS, null, COMMON_SUCCESS, null, null);

		} catch (HibernateException he) {
			he.printStackTrace();
			return new Response<OccupancyUnitMasterDto, Integer>(ERROR, null, null, null, null);
		} catch (Exception e) {
			e.printStackTrace();
			return new Response<OccupancyUnitMasterDto, Integer>(ERROR, null, null, null, null);
		} finally {
			 ;
		}
	}

	@Override
	public Response getOccupancyUnitById(OccupancyUnitMasterDto occupancyUnitMasterDto) throws ApplicationException {
		try {
			List<OccupancyUnitMasterDto> occupancyUnitMasterDtosList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_OCCUPANCY_UNIT_LIST_BY_ID")
					.setInteger("occId", occupancyUnitMasterDto.getOccupancyUnitId())
					.setResultTransformer(Transformers.aliasToBean(OccupancyUnitMasterDto.class)).list();
			if (occupancyUnitMasterDtosList != null) {
				return new Response(SUCCESS, null, null, occupancyUnitMasterDtosList, null);
			}
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getOccupancyUnitMasterList(OccupancyUnitMasterDto occupancyUnitMasterDto) throws ApplicationException {
		try {
			List<OccupancyUnitMasterDto> occupancyUnitMasterDtosList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_OCCUPANCY_UNIT_LIST")
					.setFirstResult(occupancyUnitMasterDto.getOffset() != null ? occupancyUnitMasterDto.getOffset() : 0)
					.setMaxResults(occupancyUnitMasterDto.getNoOfRecordsPerPage() != null ? occupancyUnitMasterDto.getNoOfRecordsPerPage() : 10)
					.setInteger("orgId", occupancyUnitMasterDto.getOrganizationId())
					.setResultTransformer(Transformers.aliasToBean(OccupancyUnitMasterDto.class)).list();
			if (occupancyUnitMasterDtosList != null) {
				return new Response(SUCCESS, null, null, occupancyUnitMasterDtosList, null);
			}
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getActiveOccupancyUnitList() throws ApplicationException {
		try {
			List<OccupancyUnitMasterDto> occupancyUnitMasterDtosList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_ACTIVE_OCCUPANCY_UNIT_LIST")
					.setResultTransformer(Transformers.aliasToBean(OccupancyUnitMasterDto.class)).list();
			if (occupancyUnitMasterDtosList != null) {
				return new Response(SUCCESS, null, null, occupancyUnitMasterDtosList, null);
			}
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response updateStatusForOccupancyUnit(OccupancyUnitMasterDto occupancyUnitMasterDto)
			throws ApplicationException {
		try {
			OccupancyUnitMaster occupancyUnitMaster = findById(occupancyUnitMasterDto.getOccupancyUnitId());

			occupancyUnitMaster.setStatus(occupancyUnitMasterDto.getStatus() == ACTIVE ? ACTIVE : INACTIVE);
			occupancyUnitMaster.setUpdatedBy(occupancyUnitMasterDto.getUpdatedBy());
			occupancyUnitMaster.setUpdatedDate(ADTCommonDateUtils.getDate(occupancyUnitMasterDto.getUpdatedDate(),"dd-M-yyy HH:mm:ss"));
			update(occupancyUnitMaster);
			return new Response<OccupancyUnitMasterDto, Integer>(SUCCESS, null, COMMON_SUCCESS, null, null);

		} catch (HibernateException he) {
			he.printStackTrace();
			return new Response<OccupancyUnitMasterDto, Integer>(ERROR, null, null, null, null);
		} catch (Exception e) {
			e.printStackTrace();
			return new Response<OccupancyUnitMasterDto, Integer>(ERROR, null, null, null, null);
		} finally {
			 ;
		}
	}
	
	public Response getCount(OccupancyUnitMasterDto occupancyUnitMasterDto) throws ApplicationException {
		try{
			Session session=sessionFactory.openSession();
			Criteria c=session.createCriteria(OccupancyUnitMaster.class); 
			c.add(Restrictions.eq("organizationId", occupancyUnitMasterDto.getOrganizationId()));
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
