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
import com.param.global.dto.AreaMasterDto;
import com.param.global.dto.CityMasterDto;
import com.param.global.model.AreaMaster;

import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;

@Repository
@SuppressWarnings({ "rawtypes", "unchecked" })
public class AreaMasterDaoImpl extends GenericDao<AreaMaster, Integer> implements ICommonConstants, IAreaMasterDao {

	@Autowired
	private DozerBeanMapper mapper;

	@Autowired
	private SessionFactory sessionFactory;

	public AreaMasterDaoImpl() {
		super(AreaMaster.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Response getAreaByName(AreaMasterDto areaMasterDto) throws ApplicationException {
		try {
			List<AreaMasterDto> areaMasterDtoList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_AREA_LIST_BY_NAME")
					.setString("areaName", areaMasterDto.getAreaName().toLowerCase())
					.setResultTransformer(Transformers.aliasToBean(AreaMasterDto.class)).list();
			return new Response(SUCCESS, null, null, areaMasterDtoList, null);

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
	public Response addAreaMaster(AreaMasterDto areaMasterDto) throws ApplicationException {
		try {
			AreaMaster areaMaster = mapper.map(areaMasterDto, AreaMaster.class, "AreaMasterDto_to_AreaMaster");
			areaMaster = save(areaMaster);
			return new Response(SUCCESS, null, null, null, null);
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getAreaMasterList(AreaMasterDto areaMasterDto) throws ApplicationException {
		try {
			List<AreaMasterDto> areaMasterDtoList = sessionFactory.getCurrentSession().getNamedQuery("GET_AREA_LIST")
					.setInteger("orgId", areaMasterDto.getOrganizationId())
					.setFirstResult(areaMasterDto.getOffset() != null ? areaMasterDto.getOffset() : 0)
					.setMaxResults(areaMasterDto.getNoOfRecordsPerPage() != null ? areaMasterDto.getNoOfRecordsPerPage() : 10)
					.setResultTransformer(Transformers.aliasToBean(AreaMasterDto.class)).list();
			return new Response(SUCCESS, null, null, areaMasterDtoList, null);

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
	public Response getAreaByNameNotId(AreaMasterDto areaMasterDto) throws ApplicationException {
		try {
			List<AreaMasterDto> areaMasterDtoList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_AREA_LIST_BY_NAME_NOT_ID").setInteger("areaId", areaMasterDto.getAreaId())
					.setString("areaName", areaMasterDto.getAreaName().toLowerCase())
					.setResultTransformer(Transformers.aliasToBean(AreaMasterDto.class)).list();
			return new Response(SUCCESS, null, null, areaMasterDtoList, null);

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
	public Response getAreaById(AreaMasterDto areaMasterDto) throws ApplicationException {
		try {
			List<AreaMasterDto> areaMasterDtoList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_AREA_LIST_BY_ID").setInteger("areaId", areaMasterDto.getAreaId())
					.setResultTransformer(Transformers.aliasToBean(AreaMasterDto.class)).list();
			return new Response(SUCCESS, null, null, areaMasterDtoList, null);

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
	public Response getActiveAreaList() throws ApplicationException {
		try {
			List<AreaMasterDto> areaMasterDtoList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_ACTIVE_AREA_LIST")
					.setResultTransformer(Transformers.aliasToBean(AreaMasterDto.class)).list();
			return new Response(SUCCESS, null, null, areaMasterDtoList, null);

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
	public Response getActiveCityListByDistrictId(CityMasterDto cityMasterDto) throws ApplicationException {
		try {
			List<CityMasterDto> cityMasterDtoList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_CITY_LIST_BY_DISTRICT_ID")
					.setInteger("districtId", cityMasterDto.getDistrictId())
					.setResultTransformer(Transformers.aliasToBean(AreaMasterDto.class)).list();
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
	public Response updateAreaStatus(AreaMasterDto areaMasterDto) throws ApplicationException {
		try {

			AreaMaster areaMaster = findById(areaMasterDto.getAreaId());
			areaMaster.setStatus(areaMasterDto.getStatus() == ACTIVE ? ACTIVE : INACTIVE);
			areaMaster.setUpdatedBy(areaMasterDto.getUpdatedBy());
			areaMaster.setUpdatedDate(GlobalCommonDateUtils.getDate(areaMasterDto.getUpdatedDate(), "dd-M-yyyy HH:mm:ss"));
			update(areaMaster);
			return new Response<AreaMasterDto, Integer>(SUCCESS, null, COMMON_UPDATE, null, null);

		} catch (HibernateException he) {
			he.printStackTrace();
			return new Response<AreaMasterDto, Integer>(ERROR, null, null, null, null);
		} catch (Exception e) {
			e.printStackTrace();
			return new Response<AreaMasterDto, Integer>(ERROR, null, null, null, null);
		} finally {
			 ;
		}
	}

	@Override
	public Response updateAreaMaster(AreaMasterDto areaMasterDto) throws ApplicationException {
		try {

			AreaMaster areaMaster = findById(areaMasterDto.getAreaId());

			areaMaster.setAreaName(areaMasterDto.getAreaName());
			areaMaster.setAreaCode(areaMasterDto.getAreaCode());
			areaMaster.setCityId(areaMasterDto.getCityId());
			areaMaster.setCountryId(areaMasterDto.getCountryId());
			areaMaster.setStateId(areaMasterDto.getStateId());
			areaMaster.setDistrictId(areaMasterDto.getDistrictId());
			areaMaster.setCityId(areaMasterDto.getCityId());
			areaMaster.setUpdatedBy(areaMasterDto.getUpdatedBy());
			areaMaster.setPostCode(areaMasterDto.getPostCode());
			areaMaster.setUpdatedDate(GlobalCommonDateUtils.getDate(areaMasterDto.getUpdatedDate(), "dd-M-yyyy HH:mm:ss"));
			update(areaMaster);
			return new Response<AreaMasterDto, Integer>(SUCCESS, null, COMMON_UPDATE, null, null);

		} catch (HibernateException he) {
			he.printStackTrace();
			return new Response<AreaMasterDto, Integer>(ERROR, null, null, null, null);
		} catch (Exception e) {
			e.printStackTrace();
			return new Response<AreaMasterDto, Integer>(ERROR, null, null, null, null);
		} finally {
			 ;
		}
	}

	@Override
	public Response getCount(AreaMasterDto areaMasterDto)
	{
		try{
			Session session=sessionFactory.openSession();
			Criteria c=session.createCriteria(AreaMaster.class); 
			c.add(Restrictions.eq("organizationId", areaMasterDto.getOrganizationId()));
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
