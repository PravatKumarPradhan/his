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
import com.param.adt.master.global.dto.RaceMasterDto;
import com.param.adt.master.global.model.RaceMaster;

import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;

@SuppressWarnings({ "rawtypes", "unchecked" })
@Repository
public class RaceMasterDaoImpl extends GenericDao<RaceMaster, Integer> implements ICommonConstants, IRaceMasterDao {
	@Autowired
	private DozerBeanMapper mapper;

	@Autowired
	private SessionFactory sessionFactory;

	public RaceMasterDaoImpl() {
		super(RaceMaster.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Response getRaceByName(RaceMasterDto raceMasterDto) throws ApplicationException {
		try {
			List<RaceMasterDto> raceMasterDtoList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_RACE_LIST_BY_NAME")
					.setString("raceName", raceMasterDto.getRaceName().toLowerCase())
					.setResultTransformer(Transformers.aliasToBean(RaceMasterDto.class)).list();
			return new Response(SUCCESS, null, null, raceMasterDtoList, null);

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
	public Response addRaceTypeMaster(RaceMasterDto raceMasterDto) throws ApplicationException {
		try {
			RaceMaster raceMaster = mapper.map(raceMasterDto, RaceMaster.class, "RaceMasterDto_to_RaceMaster");
			raceMaster = save(raceMaster);
			return new Response(SUCCESS, null, null, null, null);
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getRaceMasterList(RaceMasterDto raceMasterDto) throws ApplicationException {
		try {
			List<RaceMasterDto> raceMasterDtoList = sessionFactory.getCurrentSession().getNamedQuery("GET_RACE_LIST")
					.setFirstResult(raceMasterDto.getOffset() != null ? raceMasterDto.getOffset() : 0)
					.setMaxResults(raceMasterDto.getNoOfRecordsPerPage() != null ? raceMasterDto.getNoOfRecordsPerPage() : 10)
					.setInteger("orgId", raceMasterDto.getOrganizationId())
					.setResultTransformer(Transformers.aliasToBean(RaceMasterDto.class)).list();
			return new Response(SUCCESS, null, null, raceMasterDtoList, null);

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
	public Response getRaceByNameNotId(RaceMasterDto raceMasterDto) throws ApplicationException {
		try {
			List<RaceMasterDto> raceMasterDtoList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_RACE_LIST_BY_NAME_NOT_BY_ID")
					.setString("raceName", raceMasterDto.getRaceName().toLowerCase())
					.setInteger("raceId", raceMasterDto.getRaceId())
					.setResultTransformer(Transformers.aliasToBean(RaceMasterDto.class)).list();
			return new Response(SUCCESS, null, null, raceMasterDtoList, null);

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
	public Response updateRaceMaster(RaceMasterDto raceMasterDto) throws ApplicationException {
		try {
			RaceMaster raceMaster = findById(raceMasterDto.getRaceId());
			raceMaster.setRaceName(raceMasterDto.getRaceName());
			raceMaster.setRaceCode(raceMasterDto.getRaceCode());
			raceMaster.setUpdatedBy(raceMasterDto.getUpdatedBy());
			raceMaster.setUpdatedDate(ADTCommonDateUtils.getDate(raceMasterDto.getUpdatedDate(), "dd-M-yyyy HH:mm:ss"));
			update(raceMaster);
			return new Response<RaceMasterDto, Integer>(SUCCESS, null, COMMON_SUCCESS, null, null);

		} catch (HibernateException he) {
			he.printStackTrace();
			return new Response<RaceMasterDto, Integer>(ERROR, null, null, null, null);
		} catch (Exception e) {
			e.printStackTrace();
			return new Response<RaceMasterDto, Integer>(ERROR, null, null, null, null);
		} finally {
			 ;
		}
	}

	@Override
	public Response getRaceByID(RaceMasterDto raceMasterDto) throws ApplicationException {
		try {
			List<RaceMasterDto> raceMasterDtoList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_RACE_LIST_BY_ID").setInteger("raceId", raceMasterDto.getRaceId())
					.setResultTransformer(Transformers.aliasToBean(RaceMasterDto.class)).list();
			return new Response(SUCCESS, null, null, raceMasterDtoList, null);

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
	public Response updateRaceStatus(RaceMasterDto raceMasterDto) throws ApplicationException {
		try {
			RaceMaster raceMaster = findById(raceMasterDto.getRaceId());
			raceMaster.setUpdatedBy(raceMasterDto.getUpdatedBy());
			raceMaster.setUpdatedDate(ADTCommonDateUtils.getDate(raceMasterDto.getUpdatedDate(), "dd-M-yyyy HH:mm:ss"));
			raceMaster.setStatus(raceMasterDto.getStatus() == ACTIVE ? ACTIVE : INACTIVE);
			update(raceMaster);
			return new Response<RaceMasterDto, Integer>(SUCCESS, null, COMMON_SUCCESS, null, null);

		} catch (HibernateException he) {
			he.printStackTrace();
			return new Response<RaceMasterDto, Integer>(ERROR, null, null, null, null);
		} catch (Exception e) {
			e.printStackTrace();
			return new Response<RaceMasterDto, Integer>(ERROR, null, null, null, null);
		} finally {
			 ;
		}
	}

	@Override
	public Response getActiveRaceList() throws ApplicationException {
		try {
			List<RaceMasterDto> raceMasterDtoList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_ACTIVE_RACE_LIST")
					.setResultTransformer(Transformers.aliasToBean(RaceMasterDto.class)).list();
			return new Response(SUCCESS, null, null, raceMasterDtoList, null);

		} catch (HibernateException he) {
			he.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			 ;
		}
		return new Response(ERROR, null, null, null, null);
	}
	
	public Response getCount(RaceMasterDto raceMasterDto) throws ApplicationException {
		try{
			Session session=sessionFactory.openSession();
			Criteria c=session.createCriteria(RaceMaster.class); 
			c.add(Restrictions.eq("organizationId", raceMasterDto.getOrganizationId()));
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
