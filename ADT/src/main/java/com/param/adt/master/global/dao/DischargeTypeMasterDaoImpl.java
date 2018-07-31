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
import com.param.adt.master.global.dto.DischargeTypeMasterDto;
import com.param.adt.master.global.model.DischargeTypeMaster;

import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;

@Repository
@SuppressWarnings({ "rawtypes", "unchecked" })
public class DischargeTypeMasterDaoImpl extends GenericDao<DischargeTypeMaster, Integer> implements IDischargeTypeMasterDao,ICommonConstants
{
	
	@Autowired
	SessionFactory sessionFactory;

	@Autowired
	DozerBeanMapper mapper;

	public DischargeTypeMasterDaoImpl() {
		super(DischargeTypeMaster.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Response getDischargeTypeByName(DischargeTypeMasterDto dischargeTypeMasterDto) throws ApplicationException {
		try {
			List<DischargeTypeMasterDto> DischargeTypeMasterDtosList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_DISCHARGE_TYPE_LIST_BY_NAME")
					.setString("dischargeTypeName", dischargeTypeMasterDto.getDischargeTypeName().toLowerCase())
					.setResultTransformer(Transformers.aliasToBean(DischargeTypeMasterDto.class)).list();
			if (DischargeTypeMasterDtosList != null) {
				return new Response(SUCCESS, null, null, DischargeTypeMasterDtosList, null);
			}
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response addDischargeTypeMaster(DischargeTypeMasterDto dischargeTypeMasterDto) throws ApplicationException {
		try {
			DischargeTypeMaster dischargeTypeMaster = mapper.map(dischargeTypeMasterDto, DischargeTypeMaster.class,
					"DischargeTypeMasterDto_to_DischargeTypeMaster");
			dischargeTypeMaster = save(dischargeTypeMaster);
			return new Response(SUCCESS, null, null, null, null);
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getDischargeTypeByNameNotId(DischargeTypeMasterDto dischargeTypeMasterDto)
			throws ApplicationException {
		try {
			List<DischargeTypeMasterDto> DischargeTypeMasterDtosList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_DISCHARGE_TYPE_LIST_BY_NAME_NOT_ID")
					.setString("dischargeTypeName", dischargeTypeMasterDto.getDischargeTypeName().toLowerCase())
					.setInteger("dischargeTypeId", dischargeTypeMasterDto.getDischargeTypeId())
					.setResultTransformer(Transformers.aliasToBean(DischargeTypeMasterDto.class)).list();
			if (DischargeTypeMasterDtosList != null) {
				return new Response(SUCCESS, null, null, DischargeTypeMasterDtosList, null);
			}
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response updateDischargeTypeMaster(DischargeTypeMasterDto dischargeTypeMasterDto)
			throws ApplicationException {
		try {
			DischargeTypeMaster dischargeTypeMaster = findById(dischargeTypeMasterDto.getDischargeTypeId());
			dischargeTypeMaster.setDischargeTypeCode(dischargeTypeMasterDto.getDischargeTypeCode());
			dischargeTypeMaster.setDischargeTypeName(dischargeTypeMasterDto.getDischargeTypeName());
			dischargeTypeMaster.setUpdatedBy(dischargeTypeMasterDto.getUpdatedBy());
			dischargeTypeMaster.setUpdatedDate(ADTCommonDateUtils.getDate(dischargeTypeMasterDto.getUpdatedDate(),"dd-M-yyy HH:mm:ss"));
			update(dischargeTypeMaster);
			return new Response<DischargeTypeMasterDto, Integer>(SUCCESS, null, COMMON_SUCCESS, null, null);

		} catch (HibernateException he) {
			he.printStackTrace();
			return new Response<DischargeTypeMasterDto, Integer>(ERROR, null, null, null, null);
		} catch (Exception e) {
			e.printStackTrace();
			return new Response<DischargeTypeMasterDto, Integer>(ERROR, null, null, null, null);
		} finally {
			 ;
		}
	}

	@Override
	public Response getDischargeTypeMasterList(DischargeTypeMasterDto dischargeTypeMasterDto) throws ApplicationException {
		try {
			List<DischargeTypeMasterDto> DischargeTypeMasterDtosList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_DISCHARGE_TYPE_LIST")
					.setFirstResult(dischargeTypeMasterDto.getOffset() != null ? dischargeTypeMasterDto.getOffset() : 0)
					.setMaxResults(dischargeTypeMasterDto.getNoOfRecordsPerPage() != null ? dischargeTypeMasterDto.getNoOfRecordsPerPage() : 10)
					.setInteger("orgId", dischargeTypeMasterDto.getOrganizationId())
					.setResultTransformer(Transformers.aliasToBean(DischargeTypeMasterDto.class)).list();
			if (DischargeTypeMasterDtosList != null) {
				return new Response(SUCCESS, null, null, DischargeTypeMasterDtosList, null);
			}
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getDischargeTypeById(DischargeTypeMasterDto dischargeTypeMasterDto) throws ApplicationException {
		try {
			List<DischargeTypeMasterDto> DischargeTypeMasterDtosList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_DISCHARGE_TYPE_LIST_BY_ID")
					.setInteger("dischargeTypeId", dischargeTypeMasterDto.getDischargeTypeId())
					.setResultTransformer(Transformers.aliasToBean(DischargeTypeMasterDto.class)).list();
			if (DischargeTypeMasterDtosList != null) {
				return new Response(SUCCESS, null, null, DischargeTypeMasterDtosList, null);
			}
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getActiveDischargeTypeList(DischargeTypeMasterDto dischargeTypeMasterDto) throws ApplicationException {
		try {
			List<DischargeTypeMasterDto> DischargeTypeMasterDtosList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_ACTIVE_DISCHARGE_TYPE_LIST")
					.setInteger("orgId", dischargeTypeMasterDto.getOrganizationId())
					.setResultTransformer(Transformers.aliasToBean(DischargeTypeMasterDto.class)).list();
			if (DischargeTypeMasterDtosList != null) {
				return new Response(SUCCESS, null, null, DischargeTypeMasterDtosList, null);
			}
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response updateStatusForDischargeType(DischargeTypeMasterDto dischargeTypeMasterDto)
			throws ApplicationException {
		try {
			DischargeTypeMaster dischargeTypeMaster = findById(dischargeTypeMasterDto.getDischargeTypeId());

			dischargeTypeMaster.setStatus(dischargeTypeMasterDto.getStatus() == ACTIVE ? ACTIVE : INACTIVE);
			dischargeTypeMaster.setUpdatedBy(dischargeTypeMasterDto.getUpdatedBy());
			dischargeTypeMaster.setUpdatedDate(ADTCommonDateUtils.getDate(dischargeTypeMasterDto.getUpdatedDate(),"dd-M-yyy HH:mm:ss"));
			update(dischargeTypeMaster);
			return new Response<DischargeTypeMasterDto, Integer>(SUCCESS, null, COMMON_SUCCESS, null, null);

		} catch (HibernateException he) {
			he.printStackTrace();
			return new Response<DischargeTypeMasterDto, Integer>(ERROR, null, null, null, null);
		} catch (Exception e) {
			e.printStackTrace();
			return new Response<DischargeTypeMasterDto, Integer>(ERROR, null, null, null, null);
		} finally {
			 ;
		}
	}

	@Override
	public Response getCount(DischargeTypeMasterDto dischargeTypeMasterDto) throws ApplicationException {
		try{
			Session session=sessionFactory.openSession();
			Criteria c=session.createCriteria(DischargeTypeMaster.class); 
			c.add(Restrictions.eq("organizationId", dischargeTypeMasterDto.getOrganizationId()));
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
