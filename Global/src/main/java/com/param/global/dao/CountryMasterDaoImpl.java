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
import com.param.global.dto.CountryMasterDto;
import com.param.global.model.CountryMaster;

import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;

@Repository
@SuppressWarnings({ "rawtypes", "unchecked" })
public class CountryMasterDaoImpl extends GenericDao<CountryMaster, Integer>
		implements ICountryMasterDao, ICommonConstants {

	@Autowired
	private DozerBeanMapper mapper;

	@Autowired
	private SessionFactory sessionFactory;

	public CountryMasterDaoImpl() {
		super(CountryMaster.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Response getCountryByName(CountryMasterDto countryMasterDto) throws ApplicationException {
		try {
			List<CountryMasterDto> nationalityMasterDtoList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_COUNTRY_LIST_BY_NAME")
					.setString("countryName", countryMasterDto.getCountryName().toLowerCase())
					.setResultTransformer(Transformers.aliasToBean(CountryMasterDto.class)).list();
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
	public Response addCountryMaster(CountryMasterDto countryMasterDto) throws ApplicationException {
		try {
			CountryMaster countryMaster = mapper.map(countryMasterDto, CountryMaster.class,
					"countryMasterDto_to_countryMaster");
			countryMaster = save(countryMaster);
			return new Response(SUCCESS, null, null, null, null);
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getCountryMasterList(CountryMasterDto countryMasterDto) throws ApplicationException {
		try {
			List<CountryMasterDto> nationalityMasterDtoList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_COUNTRY_LIST")
					.setInteger("orgId", countryMasterDto.getOrganizationId())
					.setFirstResult(countryMasterDto.getOffset() != null ? countryMasterDto.getOffset() : 0)
					.setMaxResults(countryMasterDto.getNoOfRecordsPerPage() != null ? countryMasterDto.getNoOfRecordsPerPage() : 10)
					.setResultTransformer(Transformers.aliasToBean(CountryMasterDto.class)).list();
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
	public Response getCountryByID(CountryMasterDto countryMasterDto) throws ApplicationException {
		try {
			List<CountryMasterDto> nationalityMasterDtoList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_COUNTRY_LIST_BY_ID").setInteger("countryId", countryMasterDto.getCountryId())
					.setResultTransformer(Transformers.aliasToBean(CountryMasterDto.class)).list();
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
	public Response updateCountryStatus(CountryMasterDto countryMasterDto) throws ApplicationException {
		try {

			CountryMaster countryMaster = findById(countryMasterDto.getCountryId());
			countryMaster.setUpdatedBy(countryMasterDto.getUpdatedBy());
			countryMaster.setStatus(countryMasterDto.getStatus() == ACTIVE ? ACTIVE : INACTIVE);
			countryMaster
					.setUpdatedDate(GlobalCommonDateUtils.getDate(countryMasterDto.getUpdatedDate(), "dd-M-yyyy HH:mm:ss"));
			update(countryMaster);
			return new Response<CountryMasterDto, Integer>(SUCCESS, null, COMMON_SUCCESS, null, null);

		} catch (HibernateException he) {
			he.printStackTrace();
			return new Response<CountryMasterDto, Integer>(ERROR, null, null, null, null);
		} catch (Exception e) {
			e.printStackTrace();
			return new Response<CountryMasterDto, Integer>(ERROR, null, null, null, null);
		} finally {
			 ;
		}
	}

	@Override
	public Response updateCountryMaster(CountryMasterDto countryMasterDto) throws ApplicationException {
		try {
			CountryMaster countryMaster = findById(countryMasterDto.getCountryId());
			countryMaster.setCountryName(countryMasterDto.getCountryName());
			countryMaster.setCountryCode(countryMasterDto.getCountryCode());
			countryMaster.setCountryCallingCode(countryMasterDto.getCountryCallingCode());
			countryMaster.setUpdatedBy(countryMasterDto.getUpdatedBy());
			countryMaster.setCountryInitial(countryMasterDto.getCountryInitial());
			countryMaster
					.setUpdatedDate(GlobalCommonDateUtils.getDate(countryMasterDto.getUpdatedDate(), "dd-M-yyyy HH:mm:ss"));
			update(countryMaster);
			return new Response<CountryMasterDto, Integer>(SUCCESS, null, COMMON_SUCCESS, null, null);

		} catch (HibernateException he) {
			he.printStackTrace();
			return new Response<CountryMasterDto, Integer>(ERROR, null, null, null, null);
		} catch (Exception e) {
			e.printStackTrace();
			return new Response<CountryMasterDto, Integer>(ERROR, null, null, null, null);
		} finally {
			 ;
		}
	}

	@Override
	public Response getCountryByNameNotId(CountryMasterDto countryMasterDto) throws ApplicationException {
		try {
			List<CountryMasterDto> nationalityMasterDtoList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_COUNTRY_LIST_BY_NAME_NOT_ID")
					.setInteger("countryId", countryMasterDto.getCountryId())
					.setString("countryName", countryMasterDto.getCountryName().toLowerCase())
					.setResultTransformer(Transformers.aliasToBean(CountryMasterDto.class)).list();
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
	public Response getActiveCountryList() throws ApplicationException {
		try {
			List<CountryMasterDto> nationalityMasterDtoList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_ACTIVE_COUNTRY_LIST")
					.setResultTransformer(Transformers.aliasToBean(CountryMasterDto.class)).list();
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
	public Response getCount(CountryMasterDto countryMasterDto)
	{
		try{
			Session session=sessionFactory.openSession();
			Criteria c=session.createCriteria(CountryMaster.class); 
			c.add(Restrictions.eq("organizationId", countryMasterDto.getOrganizationId()));
			c.setProjection(Projections.rowCount());  
			Long count = (Long)c.uniqueResult();
			return new Response(SUCCESS, null, null, null, count);
		}catch(Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, null, null, null, null);
		}
	}

	@Override
	public Response getCountryMasterList() throws ApplicationException {
		try{
			List<CountryMasterDto> countryList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_COUNTRY_MASTER_LIST")
					.setResultTransformer(Transformers.aliasToBean(CountryMasterDto.class)).list();
			return new Response(SUCCESS, null, null, countryList, null);
			
			
		}catch(HibernateException he){
			he.printStackTrace();
		}
		return null;
	}
}
