package com.param.global.dao;

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

import com.param.global.common.GlobalCommonDateUtils;
import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;
import com.param.global.dto.GenderMasterDto;
import com.param.global.model.GenderMaster;

import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;

@Repository
@SuppressWarnings({"rawtypes","unchecked"})
public class GenderMasterDaoImpl extends GenericDao<GenderMaster, Integer>
		implements IGenderMasterDao, ICommonConstants {

	@Autowired
	private DozerBeanMapper mapper;

	@Autowired
	private SessionFactory sessionFactory;

	public GenderMasterDaoImpl() {
		super(GenderMaster.class);
		// TODO Auto-generated constructor stub
	}

	
	@Override
	public Response getGenderMasterList(GenderMasterDto genderMasterDto) throws ApplicationException {
		// TODO Auto-generated method stub
		try {
			List<GenderMasterDto> GenderMasterDtoList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_GENDER_LIST")
					.setFirstResult(genderMasterDto.getOffset() != null ? genderMasterDto.getOffset() : 0)
					.setMaxResults(genderMasterDto.getNoOfRecordsPerPage() != null ? genderMasterDto.getNoOfRecordsPerPage() : 10)
					.setInteger("orgId", genderMasterDto.getOrganizationId())
					.setResultTransformer(Transformers.aliasToBean(GenderMasterDto.class)).list();
			return new Response(SUCCESS, null, null, GenderMasterDtoList, null);

		} catch (HibernateException he) {
			he.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		} 
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getGenderByName(GenderMasterDto genderMasterDto) throws ApplicationException {
		try
		{
			List<GenderMasterDto> genderMasterDtoList = sessionFactory.getCurrentSession().getNamedQuery("GET_GENDER_BY_NAME")
					.setString("desc", genderMasterDto.getDesc().toLowerCase())
					.setResultTransformer(Transformers.aliasToBean(GenderMasterDto.class)).list();
			return new Response(SUCCESS, null, null, genderMasterDtoList,null);
 			
		}catch(HibernateException he){
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response addGenderMaster(GenderMasterDto genderMasterDto) throws ApplicationException {
		try {
			GenderMaster genderMaster = mapper.map(genderMasterDto, GenderMaster.class,
					"genderMasterDto_to_genderMaster");
			genderMaster = save(genderMaster);
			return new Response(SUCCESS, null, null, null, null);
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);

	}

	@Override
	public Response getGenderByID(GenderMasterDto genderMasterDto) throws ApplicationException {
		try {
			List<GenderMasterDto> genderyMasterDtoList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_GENDER_BY_ID").setInteger("id", genderMasterDto.getId())
					.setResultTransformer(Transformers.aliasToBean(GenderMasterDto.class)).list();
			return new Response(SUCCESS, null, null, genderyMasterDtoList, null);

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
	public Response getGenderByNameNotId(GenderMasterDto genderMasterDto) throws ApplicationException {

		try
		{
			List<GenderMasterDto> genderMasterDtoList = sessionFactory.getCurrentSession().getNamedQuery("GET_GENDER_BY_NAME_NOT_ID").setInteger("id", genderMasterDto.getId()).setString("desc", genderMasterDto.getDesc().toLowerCase()).setResultTransformer(Transformers.aliasToBean(GenderMasterDto.class)).list();
			return new Response(SUCCESS, null, null, genderMasterDtoList,null);
 			
		}catch(HibernateException he){

			he.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			 ;
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response updateGenderMaster(GenderMasterDto genderMasterDto) throws ApplicationException {
		try {
			GenderMaster genderMaster = findById(genderMasterDto.getId());
			genderMaster.setDesc(genderMasterDto.getDesc());
			genderMaster.setCode(genderMasterDto.getCode());
			genderMaster.setUpdatedBy(genderMasterDto.getUpdatedBy());
			genderMaster
					.setUpdatedDate(GlobalCommonDateUtils.getDate(genderMasterDto.getUpdatedDate(), "dd-M-yyyy HH:mm:ss"));
			update(genderMaster);
			return new Response<GenderMasterDto, Integer>(SUCCESS, null, COMMON_SUCCESS, null, null);

		} catch (HibernateException he) {
			he.printStackTrace();
			return new Response<GenderMasterDto, Integer>(ERROR, null, null, null, null);
		} catch (Exception e) {
			e.printStackTrace();
			return new Response<GenderMasterDto, Integer>(ERROR, null, null, null, null);
		} finally {
			 ;
		}

	}

	@Override
	public Response updateGenderStatus(GenderMasterDto genderMasterDto) throws ApplicationException {
		try {

			GenderMaster genderMaster = findById(genderMasterDto.getId());
			genderMaster.setUpdatedBy(genderMasterDto.getUpdatedBy());
			genderMaster.setStatus(genderMasterDto.getStatus() == ACTIVE ? ACTIVE : INACTIVE);
			// System.out.println(CommonDateUtils.getDate(countryMasterDto.getUpdatedDate(),"dd-M-yyyy
			// HH:mm:ss"));
			genderMaster
					.setUpdatedDate(GlobalCommonDateUtils.getDate(genderMasterDto.getUpdatedDate(), "dd-M-yyyy HH:mm:ss"));
			update(genderMaster);
			return new Response<GenderMasterDto, Integer>(SUCCESS, null, COMMON_SUCCESS, null, null);

		} catch (HibernateException he) {
			he.printStackTrace();
			return new Response<GenderMasterDto, Integer>(ERROR, null, null, null, null);
		} catch (Exception e) {
			e.printStackTrace();
			return new Response<GenderMasterDto, Integer>(ERROR, null, null, null, null);
		} finally {
			 ;
		}

	}

	@Override
	public Response getActiveGenderList() throws ApplicationException {
		try {
			List<GenderMasterDto> genderMasterDtoList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_ACTIVE_GENDER_LIST")
					.setResultTransformer(Transformers.aliasToBean(GenderMasterDto.class)).list();
			return new Response(SUCCESS, null, null, genderMasterDtoList, null);

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
	public Response getCount(GenderMasterDto genderMasterDto)
	{
		try{
			Session session=sessionFactory.openSession();
			Criteria c=session.createCriteria(GenderMaster.class); 
			c.add(Restrictions.eq("organizationId", genderMasterDto.getOrganizationId()));
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
