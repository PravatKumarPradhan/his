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
import com.param.adt.master.global.dto.EncounterTypeMasterDto;
import com.param.adt.master.global.model.EncounterTypeMaster;

import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;

@SuppressWarnings({ "rawtypes", "unchecked" })
@Repository
public class EncounterTypeMasterDaoImpl extends GenericDao<EncounterTypeMaster, Integer>
		implements IEncounterTypeMasterDao, ICommonConstants {

	public EncounterTypeMasterDaoImpl() {
		super(EncounterTypeMaster.class);
		// TODO Auto-generated constructor stub
	}
	
	@Autowired
	private DozerBeanMapper mapper;
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Response getEncounterTypeByName(EncounterTypeMasterDto encounterTypeMasterDto) throws ApplicationException {
		try {
			List<EncounterTypeMaster> encounterTypeMasters = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_ENCOUNTER_LIST_BY_NAME")
					.setString("encounterName", encounterTypeMasterDto.getEncounterTypeName().toLowerCase())
					.setResultTransformer(Transformers.aliasToBean(EncounterTypeMasterDto.class)).list();
			return new Response(SUCCESS, null, null, encounterTypeMasters, null);

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
	public Response addEncounterTypeMaster(EncounterTypeMasterDto encounterTypeMasterDto) throws ApplicationException {
		try
		{
		EncounterTypeMaster encounterTypeMaster = mapper.map(encounterTypeMasterDto, EncounterTypeMaster.class,"EncounterMasterDto_to_EncounterMaster");
		encounterTypeMaster = save(encounterTypeMaster);
			return new Response(SUCCESS, null, null, null,null);
		}catch(HibernateException he){
			he.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}

	@Override
	public Response getEncounterTypeByNameNotId(EncounterTypeMasterDto encounterTypeMasterDto)
			throws ApplicationException {
		try {
			List<EncounterTypeMaster> encounterTypeMasters = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_ENCOUNTER_LIST_BY_NAME_NOT_ID")
					.setString("encounterName", encounterTypeMasterDto.getEncounterTypeName().toLowerCase())
					.setInteger("encounterId", encounterTypeMasterDto.getEncounterTypeId())
					.setResultTransformer(Transformers.aliasToBean(EncounterTypeMasterDto.class)).list();
			return new Response(SUCCESS, null, null, encounterTypeMasters, null);

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
	public Response updateEncounterTypeMaster(EncounterTypeMasterDto encounterTypeMasterDto)
			throws ApplicationException {
		try {

			EncounterTypeMaster encounterTypeMaster = findById(encounterTypeMasterDto.getEncounterTypeId());

			encounterTypeMaster.setEncounterTypeCode(encounterTypeMasterDto.getEncounterTypeCode());
			encounterTypeMaster.setEncounterTypeName(encounterTypeMasterDto.getEncounterTypeName());
			
			encounterTypeMaster.setUpdatedBy(encounterTypeMasterDto.getUpdatedBy());
			encounterTypeMaster.setUpdatedDate(ADTCommonDateUtils.getDate(encounterTypeMasterDto.getUpdatedDate(), "dd-M-yyyy HH:mm:ss"));
			update(encounterTypeMaster);
			return new Response<EncounterTypeMasterDto, Integer>(SUCCESS, null, COMMON_SUCCESS, null, null);

		} catch (HibernateException he) {
			he.printStackTrace();
			return new Response<EncounterTypeMasterDto, Integer>(ERROR, null, null, null, null);
		} catch (Exception e) {
			e.printStackTrace();
			return new Response<EncounterTypeMasterDto, Integer>(ERROR, null, null, null, null);
		} finally {
			 ;
		}
	}

	@Override
	public Response getEncounterTypeById(EncounterTypeMasterDto encounterTypeMasterDto) throws ApplicationException {
		try {
			List<EncounterTypeMaster> encounterTypeMasters = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_ENCOUNTER_LIST_BY_ID")
					.setInteger("encounterId", encounterTypeMasterDto.getEncounterTypeId())
					.setResultTransformer(Transformers.aliasToBean(EncounterTypeMasterDto.class)).list();
			return new Response(SUCCESS, null, null, encounterTypeMasters, null);

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
	public Response updateEncounterTypeStatus(EncounterTypeMasterDto encounterTypeMasterDto)
			throws ApplicationException {
		try {

			EncounterTypeMaster encounterTypeMaster = findById(encounterTypeMasterDto.getEncounterTypeId());
			encounterTypeMaster.setStatus(encounterTypeMasterDto.getStatus() == ACTIVE ? ACTIVE : INACTIVE);
			encounterTypeMaster.setUpdatedBy(encounterTypeMasterDto.getUpdatedBy());
			encounterTypeMaster.setUpdatedDate(ADTCommonDateUtils.getDate(encounterTypeMasterDto.getUpdatedDate(), "dd-M-yyyy HH:mm:ss"));
			update(encounterTypeMaster);
			return new Response<EncounterTypeMasterDto, Integer>(SUCCESS, null, COMMON_SUCCESS, null, null);

		} catch (HibernateException he) {
			he.printStackTrace();
			return new Response<EncounterTypeMasterDto, Integer>(ERROR, null, null, null, null);
		} catch (Exception e) {
			e.printStackTrace();
			return new Response<EncounterTypeMasterDto, Integer>(ERROR, null, null, null, null);
		} finally {
			 ;
		}
	}

	@Override
	public Response getActiveEncounterTypeList() throws ApplicationException {
		try {
			List<EncounterTypeMaster> encounterTypeMasters = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_ACTIVE_ENCOUNTER_LIST")
					.setResultTransformer(Transformers.aliasToBean(EncounterTypeMasterDto.class)).list();
			return new Response(SUCCESS, null, null, encounterTypeMasters, null);

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
	public Response getEncounterTypeMasterList(EncounterTypeMasterDto encounterTypeMasterDto) throws ApplicationException {
		try {
			List<EncounterTypeMaster> encounterTypeMasters = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_ENCOUNTER_LIST")
					.setInteger("orgId", encounterTypeMasterDto.getOrganizationId())
					.setFirstResult(encounterTypeMasterDto.getOffset() != null ? encounterTypeMasterDto.getOffset() : 0)
					.setMaxResults(encounterTypeMasterDto.getNoOfRecordsPerPage() != null ? encounterTypeMasterDto.getNoOfRecordsPerPage() : 10)
					.setResultTransformer(Transformers.aliasToBean(EncounterTypeMasterDto.class)).list();
			return new Response(SUCCESS, null, null, encounterTypeMasters, null);

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
	public Response getCount(EncounterTypeMasterDto encounterTypeMasterDto) throws ApplicationException {
		try{
			Session session=sessionFactory.openSession();
			Criteria c=session.createCriteria(EncounterTypeMaster.class); 
			c.add(Restrictions.eq("organizationId", encounterTypeMasterDto.getOrganizationId()));
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
