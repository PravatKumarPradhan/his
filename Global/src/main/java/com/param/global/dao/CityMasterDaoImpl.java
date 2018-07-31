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
import com.param.global.dto.CityMasterDto;
import com.param.global.dto.DistrictMasterDto;
import com.param.global.dto.StateMasterDto;
import com.param.global.model.CityMaster;

import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;
@SuppressWarnings({"rawtypes","unchecked"})
@Repository
public class CityMasterDaoImpl extends GenericDao<CityMaster, Integer> implements ICityMasterDao,ICommonConstants
{

	@Autowired
	private DozerBeanMapper mapper;

	@Autowired
	private SessionFactory sessionFactory;


	public CityMasterDaoImpl() {
		super(CityMaster.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Response getCityByName(CityMasterDto cityMasterDto) throws ApplicationException {
		try {
			List<CityMasterDto> cityMasterDtoList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_CITY_LIST_BY_NAME")
					.setString("cityName", cityMasterDto.getCityName().toLowerCase())
					.setResultTransformer(Transformers.aliasToBean(CityMasterDto.class)).list();
			return new Response(SUCCESS, null, null, cityMasterDtoList, null);

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
	public Response getCityByNameNotId(CityMasterDto cityMasterDto) throws ApplicationException {
		try {
			List<CityMasterDto> cityMasterDtoList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_CITY_LIST_BY_NAME_NOT_ID")
					.setInteger("cityId", cityMasterDto.getCityId())
					.setString("cityName", cityMasterDto.getCityName().toLowerCase())
					.setResultTransformer(Transformers.aliasToBean(CityMasterDto.class)).list();
			return new Response(SUCCESS, null, null, cityMasterDtoList, null);

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
	public Response getCityByID(CityMasterDto cityMasterDto) throws ApplicationException {
		try {
			List<CityMasterDto> cityMasterDtoList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_CITY_LIST_BY_ID")
					.setInteger("cityId", cityMasterDto.getCityId())
					.setResultTransformer(Transformers.aliasToBean(CityMasterDto.class)).list();
			return new Response(SUCCESS, null, null, cityMasterDtoList, null);

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
	public Response getCityMasterList(CityMasterDto cityMasterDto) throws ApplicationException {
		try {
			List<CityMasterDto> cityMasterDtoList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_CITY_LIST")
					.setInteger("orgId", cityMasterDto.getOrganizationId())
					.setFirstResult(cityMasterDto.getOffset() != null ? cityMasterDto.getOffset() : 0)
					.setMaxResults(cityMasterDto.getNoOfRecordsPerPage() != null ? cityMasterDto.getNoOfRecordsPerPage() : 10)
					.setResultTransformer(Transformers.aliasToBean(CityMasterDto.class)).list();
			return new Response(SUCCESS, null, null, cityMasterDtoList, null);

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
	public Response getActiveCityList() throws ApplicationException {
		try {
			List<CityMasterDto> cityMasterDtoList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_ACTIVE_CITY_LIST")
					.setResultTransformer(Transformers.aliasToBean(CityMasterDto.class)).list();
			return new Response(SUCCESS, null, null, cityMasterDtoList, null);

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
	public Response getActiveDistrictListByStateId(DistrictMasterDto districtMasterDto) throws ApplicationException {
		try {
			List<StateMasterDto> stateMasterDtoList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_ACTIVE_DISTRICT_LIST_BY_STATE_ID")
					.setInteger("stateId", districtMasterDto.getStateId())
					.setResultTransformer(Transformers.aliasToBean(DistrictMasterDto.class)).list();
			return new Response(SUCCESS, null, null, stateMasterDtoList, null);

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
	public Response updateCityStatus(CityMasterDto cityMasterDto) throws ApplicationException {
		try {

			CityMaster cityMaster = findById(cityMasterDto.getCityId());
			cityMaster.setStatus(cityMasterDto.getStatus() == ACTIVE ? ACTIVE : INACTIVE);
			cityMaster.setUpdatedBy(cityMasterDto.getUpdatedBy());
			cityMaster.setUpdatedDate(GlobalCommonDateUtils.getDate(cityMasterDto.getUpdatedDate(), "dd-M-yyyy HH:mm:ss"));
			update(cityMaster);
			return new Response<CityMasterDto, Integer>(SUCCESS, null, COMMON_SUCCESS, null, null);

		} catch (HibernateException he) {
			he.printStackTrace();
			return new Response<CityMasterDto, Integer>(ERROR, null, null, null, null);
		} catch (Exception e) {
			e.printStackTrace();
			return new Response<CityMasterDto, Integer>(ERROR, null, null, null, null);
		} finally {
			 ;
		}
	}
	
	@Override
	public Response addCityMaster(CityMasterDto cityMasterDto) throws ApplicationException {
		try {
			CityMaster cityMaster = mapper.map(cityMasterDto, CityMaster.class, "CityMasterDto_to_CityMaster");
			cityMaster = save(cityMaster);
			return new Response(SUCCESS, null, null, null, null);
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response updateCityMaster(CityMasterDto cityMasterDto) throws ApplicationException {
		try {
			CityMaster cityMaster = findById(cityMasterDto.getCityId());
			cityMaster.setCityCode(cityMasterDto.getCityCode());
			cityMaster.setCityName(cityMasterDto.getCityName());
			cityMaster.setCountryId(cityMasterDto.getCountryId());
			cityMaster.setDistrictId(cityMasterDto.getDistrictId());
			cityMaster.setStateId(cityMasterDto.getStateId());
			cityMaster.setUpdatedBy(cityMasterDto.getUpdatedBy());
			cityMaster.setUpdatedDate(GlobalCommonDateUtils.getDate(cityMasterDto.getUpdatedDate(), "dd-M-yyyy HH:mm:ss"));
			update(cityMaster);
			return new Response<CityMasterDto, Integer>(SUCCESS, null, COMMON_SUCCESS, null, null);

		} catch (HibernateException he) {
			he.printStackTrace();
			return new Response<CityMasterDto, Integer>(ERROR, null, null, null, null);
		} catch (Exception e) {
			e.printStackTrace();
			return new Response<CityMasterDto, Integer>(ERROR, null, null, null, null);
		} finally {
			 ;
		}
	}
	
	@Override
	public Response getCount(CityMasterDto cityMasterDto)
	{
		try{
			Session session=sessionFactory.openSession();
			Criteria c=session.createCriteria(CityMaster.class); 
			c.add(Restrictions.eq("organizationId", cityMasterDto.getOrganizationId()));
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
	public Response getCityByStateId(CityMasterDto cityMasterDto)throws ApplicationException {
		try {
			List<CityMasterDto> cityMasterDtoList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_CITY_LIST_BY_STATE_ID")
					.setInteger("stateId", cityMasterDto.getStateId())
					.setResultTransformer(Transformers.aliasToBean(CityMasterDto.class)).list();
			return new Response(SUCCESS, null, null, cityMasterDtoList, null);

		} catch (HibernateException he) {
			he.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			 ;
		}
		return new Response(ERROR, null, null, null, null);
	}
}
