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
import com.param.adt.master.global.dto.BedTypeMasterDto;
import com.param.adt.master.global.model.BedTypeMaster;

import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;

@Repository
@SuppressWarnings({ "rawtypes", "unchecked" })
public class BedTypeMasterDaoImpl  extends GenericDao<BedTypeMaster, Integer> implements IBedTypeMasterDao,ICommonConstants {

	public BedTypeMasterDaoImpl() {
		super(BedTypeMaster.class);
		// TODO Auto-generated constructor stub
	}
	
	@Autowired
	SessionFactory sessionFactory;

	@Autowired
	DozerBeanMapper mapper;

	@Override
	public Response getBedTypeByName(BedTypeMasterDto bedTypeMasterDto) throws ApplicationException {
		try {
			List<BedTypeMasterDto> BedTypeMasterDtosList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_BED_TYPE_LIST_BY_NAME")
					.setString("bedTypeDesc", bedTypeMasterDto.getBedTypeDesc().toLowerCase())
					.setResultTransformer(Transformers.aliasToBean(BedTypeMasterDto.class)).list();
			if (BedTypeMasterDtosList != null) {
				return new Response(SUCCESS, null, null, BedTypeMasterDtosList, null);
			}
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response addBedTypeMaster(BedTypeMasterDto bedTypeMasterDto) throws ApplicationException {
		try {
			BedTypeMaster bedTypeMaster = mapper.map(bedTypeMasterDto, BedTypeMaster.class,
					"BedTypeMasterDto_to_BedTypeMaster");
			bedTypeMaster = save(bedTypeMaster);
			return new Response(SUCCESS, null, null, null, null);
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getBedTypeMasterList(BedTypeMasterDto bedTypeMasterDto) throws ApplicationException {
		try {
			List<BedTypeMasterDto> bedTyperDtosList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_BED_TYPE_LIST")
					.setFirstResult(bedTypeMasterDto.getOffset() != null ? bedTypeMasterDto.getOffset() : 0)
					.setMaxResults(bedTypeMasterDto.getNoOfRecordsPerPage() != null ? bedTypeMasterDto.getNoOfRecordsPerPage() : 10)
					.setInteger("orgId", bedTypeMasterDto.getOrganizationId())
					.setResultTransformer(Transformers.aliasToBean(BedTypeMasterDto.class)).list();
			if (bedTyperDtosList != null) {
				return new Response(SUCCESS, null, null, bedTyperDtosList, null);
			}
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getBedTypeByNameNotId(BedTypeMasterDto bedTypeMasterDto) throws ApplicationException {
		try {
			List<BedTypeMasterDto> bedTypeMasterDtosList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_BED_TYPE_LIST_BY_NAME_NOT_ID")
					.setInteger("bedTypeId",bedTypeMasterDto.getBedTypeId())
					.setString("bedTypeDesc", bedTypeMasterDto.getBedTypeDesc().toLowerCase())
					.setResultTransformer(Transformers.aliasToBean(BedTypeMasterDto.class)).list();
			if (bedTypeMasterDtosList != null) {
				return new Response(SUCCESS, null, null, bedTypeMasterDtosList, null);
			}
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response updateBedTypeMaster(BedTypeMasterDto bedTypeMasterDto) throws ApplicationException {
		try {
			BedTypeMaster bedTypeMaster = findById(bedTypeMasterDto.getBedTypeId());
			bedTypeMaster.setBedTypeDesc(bedTypeMasterDto.getBedTypeDesc());
			bedTypeMaster.setBedTypeCode(bedTypeMasterDto.getBedTypeCode());
			bedTypeMaster.setUpdatedBy(bedTypeMasterDto.getUpdatedBy());
			bedTypeMaster.setUpdatedDate(ADTCommonDateUtils.getDate(bedTypeMasterDto.getUpdatedDate(),"dd-M-yyy HH:mm:ss"));
			update(bedTypeMaster);
			return new Response<BedTypeMasterDto, Integer>(SUCCESS, null, COMMON_SUCCESS, null, null);

		} catch (HibernateException he) {
			he.printStackTrace();
			return new Response<BedTypeMasterDto, Integer>(ERROR, null, null, null, null);
		} catch (Exception e) {
			e.printStackTrace();
			return new Response<BedTypeMasterDto, Integer>(ERROR, null, null, null, null);
		} finally {
			 ;
		}
	}

	@Override
	public Response getBedTypeById(BedTypeMasterDto bedTypeMasterDto) throws ApplicationException {
		try {
			List<BedTypeMasterDto> bedTypeDtosList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_BED_TYPE_LIST_BY_ID")
					.setInteger("bedTypeId",bedTypeMasterDto.getBedTypeId())
					.setResultTransformer(Transformers.aliasToBean(BedTypeMasterDto.class)).list();
			if (bedTypeDtosList != null) {
				return new Response(SUCCESS, null, null, bedTypeDtosList, null);
			}
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response updateBedTypeStatus(BedTypeMasterDto bedTypeMasterDto) throws ApplicationException {
		try {
			BedTypeMaster bedTypeMaster = findById(bedTypeMasterDto.getBedTypeId());

			bedTypeMaster.setStatus(bedTypeMasterDto.getStatus() == ACTIVE ? ACTIVE : INACTIVE);
			bedTypeMaster.setUpdatedBy(bedTypeMasterDto.getUpdatedBy());
			bedTypeMaster.setUpdatedDate(ADTCommonDateUtils.getDate(bedTypeMasterDto.getUpdatedDate(),"dd-M-yyy HH:mm:ss"));
			update(bedTypeMaster);
			return new Response<BedTypeMasterDto, Integer>(SUCCESS, null, COMMON_SUCCESS, null, null);

		} catch (HibernateException he) {
			he.printStackTrace();
			return new Response<BedTypeMasterDto, Integer>(ERROR, null, null, null, null);
		} catch (Exception e) {
			e.printStackTrace();
			return new Response<BedTypeMasterDto, Integer>(ERROR, null, null, null, null);
		} finally {
			 ;
		}
	}

	@Override
	public Response getActiveBedTypeMasterList() throws ApplicationException {
		try {
			List<BedTypeMasterDto> bedTypeDtosList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_ACTIVE_BED_TYPE_LIST")
					.setResultTransformer(Transformers.aliasToBean(BedTypeMasterDto.class)).list();
			if (bedTypeDtosList != null) {
				return new Response(SUCCESS, null, null, bedTypeDtosList, null);
			}
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}
	
	public Response getCount(BedTypeMasterDto bedTypeMasterDto) throws ApplicationException {
		try{
			Session session=sessionFactory.openSession();
			Criteria c=session.createCriteria(BedTypeMaster.class); 
			c.add(Restrictions.eq("organizationId", bedTypeMasterDto.getOrganizationId()));
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
