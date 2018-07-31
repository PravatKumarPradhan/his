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
import com.param.global.dto.DistrictMasterDto;
import com.param.global.dto.StateMasterDto;
import com.param.global.model.CountryMaster;
import com.param.global.model.DistrictMaster;

import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;

@SuppressWarnings({ "rawtypes", "unchecked" })
@Repository
public class DistrictMasterDaoImpl extends GenericDao<DistrictMaster, Integer>
		implements IDistrictMasterDao, ICommonConstants {

	public DistrictMasterDaoImpl() {
		super(DistrictMaster.class);
		// TODO Auto-generated constructor stub
	}

	@Autowired
	private DozerBeanMapper mapper;

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Response getDistrictByName(DistrictMasterDto districtMasterDto) throws ApplicationException {
		try {
			List<DistrictMaster> districtMastersList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_DISTRICT_LIST_BY_NAME")
					.setString("districtName", districtMasterDto.getDistrictName().toLowerCase())
					.setResultTransformer(Transformers.aliasToBean(DistrictMasterDto.class)).list();
			return new Response(SUCCESS, null, null, districtMastersList, null);

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
	public Response addDistrictMaster(DistrictMasterDto districtMasterDto) throws ApplicationException {
		try {
			DistrictMaster districtMaster = mapper.map(districtMasterDto, DistrictMaster.class,
					"DistrictMasterDto_to_DistrictMaster");
			districtMaster = save(districtMaster);
			return new Response(SUCCESS, null, null, null, null);
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getDistrictMasterList(DistrictMasterDto districtMasterDto) throws ApplicationException {
		try {
			List<DistrictMaster> districtMastersList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_DISTRICT_LIST")
					.setInteger("orgId", districtMasterDto.getOrganizationId())
					.setFirstResult(districtMasterDto.getOffset() != null ? districtMasterDto.getOffset() : 0)
					.setMaxResults(districtMasterDto.getNoOfRecordsPerPage() != null ? districtMasterDto.getNoOfRecordsPerPage() : 10)
					.setResultTransformer(Transformers.aliasToBean(DistrictMasterDto.class)).list();
			return new Response(SUCCESS, null, null, districtMastersList, null);

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
	public Response getDistrictByNameNotId(DistrictMasterDto districtMasterDto) throws ApplicationException {
		try {
			List<DistrictMaster> districtMastersList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_DISTRICT_LIST_BY_NAME_NOT_ID")
					.setString("districtName", districtMasterDto.getDistrictName().toLowerCase())
					.setInteger("districtId", districtMasterDto.getDistrictId())
					.setResultTransformer(Transformers.aliasToBean(DistrictMasterDto.class)).list();
			return new Response(SUCCESS, null, null, districtMastersList, null);

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
	public Response updateDistrictMaster(DistrictMasterDto districtMasterDto) throws ApplicationException {
		try {
			DistrictMaster districtMaster = findById(districtMasterDto.getDistrictId());
			districtMaster.setDistrictName(districtMasterDto.getDistrictName());
			districtMaster.setDistrictCode(districtMasterDto.getDistrictCode());
			districtMaster.setCountryId(districtMasterDto.getCountryId());
			districtMaster.setUpdatedBy(districtMasterDto.getUpdatedBy());
			districtMaster
					.setUpdatedDate(GlobalCommonDateUtils.getDate(districtMasterDto.getUpdatedDate(), "dd-M-yyyy HH:mm:ss"));
			update(districtMaster);
			return new Response<StateMasterDto, Integer>(SUCCESS, null, COMMON_SUCCESS, null, null);

		} catch (HibernateException he) {
			he.printStackTrace();
			return new Response<StateMasterDto, Integer>(ERROR, null, null, null, null);
		} catch (Exception e) {
			e.printStackTrace();
			return new Response<StateMasterDto, Integer>(ERROR, null, null, null, null);
		} finally {
			 ;
		}
	}

	@Override
	public Response getDistrictById(DistrictMasterDto districtMasterDto) throws ApplicationException {
		try {
			List<DistrictMaster> districtMastersList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_DISTRICT_LIST_BY_ID")
					.setInteger("districtId", districtMasterDto.getDistrictId())
					.setResultTransformer(Transformers.aliasToBean(DistrictMasterDto.class)).list();
			return new Response(SUCCESS, null, null, districtMastersList, null);

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
	public Response updateDistrictStatus(DistrictMasterDto districtMasterDto) throws ApplicationException {
		try {

			DistrictMaster districtMaster = findById(districtMasterDto.getDistrictId());
			districtMaster.setStatus(districtMasterDto.getStatus() == ACTIVE ? ACTIVE : INACTIVE);
			districtMaster.setUpdatedBy(districtMasterDto.getUpdatedBy());
			districtMaster
					.setUpdatedDate(GlobalCommonDateUtils.getDate(districtMasterDto.getUpdatedDate(), "dd-M-yyyy HH:mm:ss"));
			update(districtMaster);
			return new Response<StateMasterDto, Integer>(SUCCESS, null, COMMON_SUCCESS, null, null);

		} catch (HibernateException he) {
			he.printStackTrace();
			return new Response<StateMasterDto, Integer>(ERROR, null, null, null, null);
		} catch (Exception e) {
			e.printStackTrace();
			return new Response<StateMasterDto, Integer>(ERROR, null, null, null, null);
		} finally {
			 ;
		}
	}

	@Override
	public Response getActiveDistrictList() throws ApplicationException {
		try {
			List<DistrictMaster> districtMastersList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_ACTIVE_DISTRICT_LIST")
					.setResultTransformer(Transformers.aliasToBean(DistrictMasterDto.class)).list();
			return new Response(SUCCESS, null, null, districtMastersList, null);

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
	public Response getActiveStateListByCountryId(StateMasterDto stateMaster) throws ApplicationException {
		try {
			List<CountryMaster> countryMastersList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_STATE_LIST_BY_COUNTRY_ID").setInteger("countryId", stateMaster.getCountryId())
					.setResultTransformer(Transformers.aliasToBean(StateMasterDto.class)).list();

			return new Response(SUCCESS, null, null, countryMastersList, null);

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
	public Response getCount(DistrictMasterDto districtMasterDto) throws ApplicationException {
		try {
			Session session = sessionFactory.openSession();
			Criteria c = session.createCriteria(DistrictMaster.class);
			c.add(Restrictions.eq("organizationId", districtMasterDto.getOrganizationId()));
			c.setProjection(Projections.rowCount());
			Long count = (Long) c.uniqueResult();
			return new Response(SUCCESS, null, null, null, count);
		} catch (Exception e) {
			e.printStackTrace();
			return new Response(ERROR, null, null, null, null);
		}
	}

}
