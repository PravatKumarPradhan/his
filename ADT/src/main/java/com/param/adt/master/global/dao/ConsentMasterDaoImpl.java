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
import com.param.adt.master.global.dto.ConsentMasterDto;
import com.param.adt.master.global.model.ConsentMaster;

import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;
@Repository
@SuppressWarnings({"rawtypes","unchecked"})
public class ConsentMasterDaoImpl extends GenericDao<ConsentMaster, Integer> implements IConsentMasterDao,ICommonConstants{

	
	@Autowired
	private DozerBeanMapper mapper;

	@Autowired
	private SessionFactory sessionFactory;

	public ConsentMasterDaoImpl() {
		super(ConsentMaster.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Response addConsentMaster(ConsentMasterDto consentMasterDto) throws ApplicationException {
		try {
			ConsentMaster consentMaster = mapper.map(consentMasterDto, ConsentMaster.class, "ConsentMasterDto_to_ConsentMaster");
			consentMaster = save(consentMaster);
			return new Response(SUCCESS, null, null, null, null);
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response updateConsentMaster(ConsentMasterDto consentMasterDto) {
		try {

			ConsentMaster consentMaster = findById(consentMasterDto.getConsentMasterId());
			consentMaster.setConsentMasterCode(consentMasterDto.getConsentMasterCode());
			consentMaster.setConsentMasterName(consentMasterDto.getConsentMasterName());
			consentMaster.setConsentTypeMasterId(consentMasterDto.getConsentTypeMasterId());
			consentMaster.setUpdatedBy(consentMasterDto.getUpdatedBy());
			consentMaster.setUpdatedDate(ADTCommonDateUtils.getDate(consentMasterDto.getUpdatedDate(), "dd-M-yyyy HH:mm:ss"));
			update(consentMaster);
			return new Response<ConsentMasterDto, Integer>(SUCCESS, null, COMMON_SUCCESS, null, null);

		} catch (HibernateException he) {
			he.printStackTrace();
			return new Response<ConsentMasterDto, Integer>(ERROR, null, null, null, null);
		} catch (Exception e) {
			e.printStackTrace();
			return new Response<ConsentMasterDto, Integer>(ERROR, null, null, null, null);
		} finally {
			 ;
		}
	}

	@Override
	public Response getConsentByName(ConsentMasterDto consentMasterDto) throws ApplicationException {
		try {
			List<ConsentMasterDto> consentMasterDtosList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_CONSENT_LIST_BY_NAME")
					.setString("consentName", consentMasterDto.getConsentMasterName())
					.setResultTransformer(Transformers.aliasToBean(ConsentMasterDto.class)).list();
			return new Response(SUCCESS, null, null, consentMasterDtosList, null);

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
	public Response getConsentByNameNotId(ConsentMasterDto consentMasterDto) throws ApplicationException {
		try {
			List<ConsentMasterDto> consentMasterDtosList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_CONSENT_LIST_BY_NAME_NOT_ID")
					.setString("consentName", consentMasterDto.getConsentMasterName())
					.setInteger("consentId", consentMasterDto.getConsentMasterId())
					.setResultTransformer(Transformers.aliasToBean(ConsentMasterDto.class)).list();
			return new Response(SUCCESS, null, null, consentMasterDtosList, null);

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
	public Response getConsentById(ConsentMasterDto consentMasterDto) throws ApplicationException {
		try {
			List<ConsentMasterDto> consentMasterDtosList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_CONSENT_LIST_BY_ID")
					.setInteger("consentId", consentMasterDto.getConsentMasterId())
					.setResultTransformer(Transformers.aliasToBean(ConsentMasterDto.class)).list();
			return new Response(SUCCESS, null, null, consentMasterDtosList, null);

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
	public Response updateConsentStatus(ConsentMasterDto consentMasterDto) throws ApplicationException {
		try {

			ConsentMaster consentMaster = findById(consentMasterDto.getConsentMasterId());
			consentMaster.setStatus(consentMasterDto.getStatus() == ACTIVE ? ACTIVE : INACTIVE);
			consentMaster.setUpdatedBy(consentMasterDto.getUpdatedBy());
			consentMaster.setUpdatedDate(ADTCommonDateUtils.getDate(consentMasterDto.getUpdatedDate(), "dd-M-yyyy HH:mm:ss"));
			update(consentMaster);
			return new Response<ConsentMasterDto, Integer>(SUCCESS, null, COMMON_SUCCESS, null, null);

		} catch (HibernateException he) {
			he.printStackTrace();
			return new Response<ConsentMasterDto, Integer>(ERROR, null, null, null, null);
		} catch (Exception e) {
			e.printStackTrace();
			return new Response<ConsentMasterDto, Integer>(ERROR, null, null, null, null);
		} finally {
			 ;
		}
	}

	@Override
	public Response getActiveConsentList() throws ApplicationException {
		try {
			List<ConsentMasterDto> consentMasterDtosList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_ACTIVE_CONSENT_LIST")
					.setResultTransformer(Transformers.aliasToBean(ConsentMasterDto.class)).list();
			return new Response(SUCCESS, null, null, consentMasterDtosList, null);

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
	public Response getConsentMasterList(ConsentMasterDto consentMasterDto) throws ApplicationException {
		try {
			List<ConsentMasterDto> consentMasterDtosList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_CONSENT_LIST")
					.setInteger("orgId", consentMasterDto.getOrganizationId())
					.setFirstResult(consentMasterDto.getOffset() != null ? consentMasterDto.getOffset() : 0)
					.setMaxResults(consentMasterDto.getNoOfRecordsPerPage() != null ? consentMasterDto.getNoOfRecordsPerPage() : 10)
					.setResultTransformer(Transformers.aliasToBean(ConsentMasterDto.class)).list();
			return new Response(SUCCESS, null, null, consentMasterDtosList, null);

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
	public Response getCount(ConsentMasterDto consentMasterDto)
	{
		try{
			Session session=sessionFactory.openSession();
			Criteria c=session.createCriteria(ConsentMaster.class); 
			c.add(Restrictions.eq("organizationId", consentMasterDto.getOrganizationId()));
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
