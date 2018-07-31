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
import com.param.adt.master.global.dto.ReferralTypeMasterDto;
import com.param.adt.master.global.model.ReferralTypeMaster;

import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;
@Repository
@SuppressWarnings({"rawtypes","unchecked"})
public class ReferralTypeMasterDaoImpl extends GenericDao<ReferralTypeMaster, Integer> implements IReferralTypeMasterDao,ICommonConstants
{

	public ReferralTypeMasterDaoImpl() {
		super(ReferralTypeMaster.class);
		// TODO Auto-generated constructor stub
	}

	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired 
	private DozerBeanMapper mapper;
	
	@Override
	public Response getReferralTypeByName(ReferralTypeMasterDto referralTypeMasterDto) throws ApplicationException {
		try {
			List<ReferralTypeMasterDto> referralTypeMasterDtosList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_REFFERAL_TYPE_MASTER_LIST_BY_NAME")
					.setString("desc", referralTypeMasterDto.getDesc().toLowerCase())
					.setResultTransformer(Transformers.aliasToBean(ReferralTypeMasterDto.class)).list();
			return new Response(SUCCESS, null, null, referralTypeMasterDtosList, null);

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
	public Response addReferralTypeTypeMaster(ReferralTypeMasterDto referralTypeMasterDto) throws ApplicationException {
		try {
			ReferralTypeMaster referralTypeMaster = mapper.map(referralTypeMasterDto, ReferralTypeMaster.class, "ReferralTypeMasterDto_to_ReferralTypeMaster");
			referralTypeMaster = save(referralTypeMaster);
			return new Response(SUCCESS, null, null, null, null);
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getReferralTypeMasterList(ReferralTypeMasterDto referralTypeMasterDto) throws ApplicationException {
		try {
			List<ReferralTypeMasterDto> referralTypeMasterDtosList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_REFERRAL_TYPE_MASTER_LIST")
					.setInteger("orgId", referralTypeMasterDto.getOrganizationId())
					.setFirstResult(referralTypeMasterDto.getOffset() != null ? referralTypeMasterDto.getOffset() : 0)
					.setMaxResults(referralTypeMasterDto.getNoOfRecordsPerPage() != null ? referralTypeMasterDto.getNoOfRecordsPerPage() : 10)
					.setResultTransformer(Transformers.aliasToBean(ReferralTypeMasterDto.class)).list();
			return new Response(SUCCESS, null, null, referralTypeMasterDtosList, null);

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
	public Response getReferralTypeById(ReferralTypeMasterDto referralTypeMasterDto) throws ApplicationException {
		try {
			List<ReferralTypeMasterDto> referralTypeMasterDtosList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_REFFERAL_TYPE_MASTER_LIST_BY_ID")
					.setInteger("id", referralTypeMasterDto.getId())
					.setResultTransformer(Transformers.aliasToBean(ReferralTypeMasterDto.class)).list();
			return new Response(SUCCESS, null, null, referralTypeMasterDtosList, null);

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
	public Response getActiveReferralTypeList(ReferralTypeMasterDto referralTypeMasterDto) throws ApplicationException {
		try {
			List<ReferralTypeMasterDto> referralTypeMasterDtosList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_ACTIVE_REFFERAL_TYPE_MASTER_LIST")
					.setInteger("orgId", referralTypeMasterDto.getOrganizationId())
					.setResultTransformer(Transformers.aliasToBean(ReferralTypeMasterDto.class)).list();
			return new Response(SUCCESS, null, null, referralTypeMasterDtosList, null);

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
	public Response getReferralTypeByNameNotId(ReferralTypeMasterDto referralTypeMasterDto)
			throws ApplicationException {
		try {
			List<ReferralTypeMasterDto> referralTypeMasterDtosList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_REFFERAL_TYPE_MASTER_LIST_BY_NAME_NOT_ID")
					.setString("desc", referralTypeMasterDto.getDesc().toLowerCase())
					.setInteger("id", referralTypeMasterDto.getId())
					.setResultTransformer(Transformers.aliasToBean(ReferralTypeMasterDto.class)).list();
			return new Response(SUCCESS, null, null, referralTypeMasterDtosList, null);

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
	public Response updateReferralTypeMaster(ReferralTypeMasterDto referralTypeMasterDto) throws ApplicationException {
		try {
			ReferralTypeMaster referralTypeMaster = findById(referralTypeMasterDto.getId());
			referralTypeMaster.setDesc(referralTypeMasterDto.getDesc());
			referralTypeMaster.setCode(referralTypeMasterDto.getCode());
			referralTypeMaster.setUpdatedBy(referralTypeMasterDto.getUpdatedBy());
			referralTypeMaster.setUpdatedDate(ADTCommonDateUtils.getDate(referralTypeMasterDto.getUpdatedDate(),"dd-M-yyyy HH:mm:ss"));
			update(referralTypeMaster);
			return new Response<ReferralTypeMaster, Integer>(SUCCESS, null, COMMON_SUCCESS, null, null);

		} catch (HibernateException he) {
			he.printStackTrace();
			return new Response<ReferralTypeMaster, Integer>(ERROR, null, null, null, null);
		} catch (Exception e) {
			e.printStackTrace();
			return new Response<ReferralTypeMaster, Integer>(ERROR, null, null, null, null);
		} finally {
			 ;
		}
	}


	@Override
	public Response updateStatusForReferralType(ReferralTypeMasterDto referralTypeMasterDto)
			throws ApplicationException {
		try {
			ReferralTypeMaster referralTypeMaster = findById(referralTypeMasterDto.getId());
			referralTypeMaster.setUpdatedBy(referralTypeMasterDto.getUpdatedBy());
			referralTypeMaster.setUpdatedDate(ADTCommonDateUtils.getDate(referralTypeMasterDto.getUpdatedDate(),"dd-M-yyyy HH:mm:ss"));
			referralTypeMaster.setStatus(referralTypeMasterDto.getStatus() == ACTIVE ? ACTIVE : INACTIVE);
			update(referralTypeMaster);
			return new Response<ReferralTypeMaster, Integer>(SUCCESS, null, COMMON_SUCCESS, null, null);

		} catch (HibernateException he) {
			he.printStackTrace();
			return new Response<ReferralTypeMaster, Integer>(ERROR, null, null, null, null);
		} catch (Exception e) {
			e.printStackTrace();
			return new Response<ReferralTypeMaster, Integer>(ERROR, null, null, null, null);
		} finally {
			 ;
		}
	}
	
	@Override
	public Response getCount(ReferralTypeMasterDto referralTypeMasterDto)
	{
		try{
			Session session=sessionFactory.openSession();
			Criteria c=session.createCriteria(ReferralTypeMaster.class); 
			c.add(Restrictions.eq("organizationId", referralTypeMasterDto.getOrganizationId()));
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
