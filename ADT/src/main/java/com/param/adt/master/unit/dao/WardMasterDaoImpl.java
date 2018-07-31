package com.param.adt.master.unit.dao;

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

import com.param.adt.master.unit.dto.WardMasterDto;
import com.param.adt.master.unit.model.WardMaster;
import com.param.global.common.GlobalCommonDateUtils;
import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;
import com.param.global.dto.SpecialityMasterDto;

import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;

@Repository
@SuppressWarnings({ "rawtypes", "unchecked" })
public class WardMasterDaoImpl extends GenericDao<WardMaster, Integer> implements IWardMasterDao, ICommonConstants {

	public WardMasterDaoImpl() {
		super(WardMaster.class);
	}

	@Autowired
	SessionFactory sessionFactory;
	@Autowired
	private DozerBeanMapper mapper;

	@Override
	public Response saveWardMasterMaster(WardMasterDto wardMasterDto) throws ApplicationException {
		try {
			WardMaster wardMaster = mapper.map(wardMasterDto, WardMaster.class, "UnitWardMasterDto_to_UnitWardMaster");
			save(wardMaster);
			return new Response<>(SUCCESS, SUCCESS_CODE, SUCCESS_MESSAGE, null, null);
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response getWardMasterById(int wardId, int orgId, int unitId) throws ApplicationException {
		try {
			List<WardMasterDto> WardMasterDtoList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_WARD_MASTER_BY_ID").setInteger("wardId", wardId).setInteger("orgId", orgId)
					.setInteger("unitId", unitId).setResultTransformer(Transformers.aliasToBean(WardMasterDto.class))
					.list();
			return new Response<>(SUCCESS, SUCCESS_CODE, SUCCESS_MESSAGE, WardMasterDtoList, null);
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response getWardMasterList(WardMasterDto wardMasterDto) throws ApplicationException {
		try {
			List<WardMasterDto> WardMasterDtoList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_WARD_MASTER_LIST").setInteger("orgId", wardMasterDto.getOrganizationId())
					.setInteger("unitId", wardMasterDto.getUnitId())
					.setFirstResult(wardMasterDto.getOffset() != null ? wardMasterDto.getOffset() : 0)
					.setMaxResults(
							wardMasterDto.getNoOfRecordsPerPage() != null ? wardMasterDto.getNoOfRecordsPerPage() : 10)
					.setResultTransformer(Transformers.aliasToBean(WardMasterDto.class)).list();
			return new Response<>(SUCCESS, SUCCESS_CODE, SUCCESS_MESSAGE, WardMasterDtoList, null);
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response updateWardMaster(WardMasterDto wardMasterDto) throws ApplicationException {
		try {
			WardMaster wardMaster = findById(wardMasterDto.getWardId());
			wardMaster.setFloorId(wardMasterDto.getFloorId());
			wardMaster.setWardCode(wardMasterDto.getWardCode());
			wardMaster.setWardName(wardMasterDto.getWardName());
			wardMaster.setGenderId(wardMasterDto.getGenderId());
			wardMaster.setIsEr(wardMasterDto.getIsEr());
			wardMaster.setIsIcu(wardMasterDto.getIsIcu());
			wardMaster.setIsDayCare(wardMasterDto.getIsDayCare());
			wardMaster.setStoreId(wardMasterDto.getStoreId());
			wardMaster.setUpdatedBy(wardMasterDto.getUpdatedBy());
			wardMaster.setUpdatedDate(
					GlobalCommonDateUtils.getDate(wardMasterDto.getUpdatedDate(), "dd-M-yyyy HH:mm:ss"));
			update(wardMaster);
			return new Response<>(SUCCESS, SUCCESS_CODE, COMMON_UPDATE, null, null);
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response updateWardMasterStatus(WardMasterDto wardMasterDto) throws ApplicationException {
		try {
			WardMaster wardMaster = findById(wardMasterDto.getWardId());
			wardMaster.setStatus(wardMasterDto.getStatus() == ACTIVE ? ACTIVE : INACTIVE);
			wardMaster.setUpdatedBy(wardMasterDto.getUpdatedBy());
			wardMaster.setUpdatedDate(
					GlobalCommonDateUtils.getDate(wardMasterDto.getUpdatedDate(), "dd-M-yyyy HH:mm:ss"));
			update(wardMaster);
			return new Response<>(SUCCESS, SUCCESS_CODE, COMMON_UPDATE, null, null);
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response getWardMasterCount(WardMasterDto wardMasterDto) throws ApplicationException {
		try {
			Session session = sessionFactory.openSession();
			Criteria c = session.createCriteria(WardMaster.class);
			c.add(Restrictions.eq("organizationId", wardMasterDto.getOrganizationId()));
			c.setProjection(Projections.rowCount());
			Long count = (Long) c.uniqueResult();
			return new Response(SUCCESS, null, null, null, count);
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response getWardMasterByName(WardMasterDto wardMasterDto) throws ApplicationException {
		try {
			List<WardMasterDto> WardMasterDtoList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_WARD_MASTER_BY_NAME")
					.setInteger("orgId", wardMasterDto.getOrganizationId())
					.setString("wardName", wardMasterDto.getWardName().toLowerCase())
					.setResultTransformer(Transformers.aliasToBean(WardMasterDto.class)).list();
			return new Response<>(SUCCESS, SUCCESS_CODE, SUCCESS_MESSAGE, WardMasterDtoList, null);
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response getWardMasterByNameNotById(WardMasterDto wardMasterDto) throws ApplicationException {
		try {
			List<WardMasterDto> WardMasterDtoList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_WARD_MASTER_BY_NAME_NOT_BY_ID").setInteger("wardId", wardMasterDto.getWardId())
					.setString("wardName", wardMasterDto.getWardName().toLowerCase())
					.setResultTransformer(Transformers.aliasToBean(WardMasterDto.class)).list();
			return new Response<>(SUCCESS, SUCCESS_CODE, SUCCESS_MESSAGE, WardMasterDtoList, null);
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

}
