package com.param.adt.master.unit.dao;

import java.util.List;

import org.dozer.DozerBeanMapper;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.param.adt.common.ADTCommonDateUtils;
import com.param.adt.common.ICommonConstants;
import com.param.adt.common.Response;
import com.param.adt.master.unit.dto.UnitSpecialtyMapperDto;
import com.param.adt.master.unit.dto.UnitSubSpecialityMapperDto;
import com.param.adt.master.unit.model.UnitSubSpecialityMapper;
import com.param.global.dto.SpecialityMasterDto;

import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;

@SuppressWarnings({ "unchecked", "rawtypes" })
@Repository
public class UnitSubSpecialityDaoImpl extends GenericDao<UnitSubSpecialityMapper, Integer>
		implements IUnitSubSpecialityDao, ICommonConstants {

	public UnitSubSpecialityDaoImpl() {
		super(UnitSubSpecialityMapper.class);
		// TODO Auto-generated constructor stub
	}

	@Autowired
	SessionFactory sessionFactory;

	@Autowired
	DozerBeanMapper mapper;

	@Override
	public Response saveUnitSubSpecialitiy(UnitSubSpecialityMapperDto subSpecialityMapperDto) {
		try {
			UnitSubSpecialityMapper unitSubSpecialityMapper = mapper.map(subSpecialityMapperDto,
					UnitSubSpecialityMapper.class, "UnitSubSpecialtyMapperDto_to_UnitSubSpecialtyMapper");
			save(unitSubSpecialityMapper);
			return new Response(SUCCESS, null, COMMON_SUCCESS, null, null);
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getUnitSubSpecialityList(UnitSubSpecialityMapperDto subSpecialityMapperDto)
			throws ApplicationException {
		try {
			List<UnitSubSpecialityMapperDto> unitSubSpecialtyMapperDtosList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_UNIT_SUB_SPECIALITY_LIST")
					.setInteger("orgId", subSpecialityMapperDto.getOrganizationId())
					.setInteger("unitId", subSpecialityMapperDto.getUnitId())
					.setFirstResult(subSpecialityMapperDto.getOffset() != null ? subSpecialityMapperDto.getOffset() : 0)
					.setMaxResults(subSpecialityMapperDto.getNoOfRecordsPerPage() != null
							? subSpecialityMapperDto.getNoOfRecordsPerPage() : 10)
					.setResultTransformer(Transformers.aliasToBean(UnitSubSpecialityMapperDto.class)).list();
			return new Response(SUCCESS, null, null, unitSubSpecialtyMapperDtosList, null);

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
	public Response getUnitSubSpecialityById(UnitSubSpecialityMapperDto unitSubSpecialityMapperDto)
			throws ApplicationException {
		try
		{
			UnitSubSpecialityMapper unitSubSpecialityMapper=findById(unitSubSpecialityMapperDto.getUnitSubSpecialityId());
		if(unitSubSpecialityMapper.getUnitSubSpecialityId()==unitSubSpecialityMapperDto.getUnitSubSpecialityId())
			return new Response(SUCCESS, null, null, null, null);
		else 
			return new Response(ERROR, null, null, null, null);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response updateStatusForUnitSpecialityList(UnitSubSpecialityMapperDto unitSubSpecialityMapperDto)
			throws ApplicationException {
		try {
			UnitSubSpecialityMapper unitSubSpecialityMapper = findById(
					unitSubSpecialityMapperDto.getUnitSubSpecialityId());

			unitSubSpecialityMapper.setStatus(unitSubSpecialityMapperDto.getStatus() == ACTIVE ? ACTIVE : INACTIVE);
			unitSubSpecialityMapper.setUpdatedBy(unitSubSpecialityMapperDto.getUpdatedBy());
			unitSubSpecialityMapper.setUpdatedDate(
					ADTCommonDateUtils.getDate(unitSubSpecialityMapperDto.getUpdatedDate(), "dd-M-yyy HH:mm:ss"));
			update(unitSubSpecialityMapper);
			return new Response<SpecialityMasterDto, Integer>(SUCCESS, null, COMMON_SUCCESS, null, null);

		} catch (HibernateException he) {
			he.printStackTrace();
			return new Response<SpecialityMasterDto, Integer>(ERROR, null, null, null, null);
		} catch (Exception e) {
			e.printStackTrace();
			return new Response<SpecialityMasterDto, Integer>(ERROR, null, null, null, null);
		} finally {
			 ;
		}
	}

	@Override
	public Response truncateUnitSubSpecialtiy(UnitSubSpecialityMapperDto obj) throws ApplicationException {
		try {
			// Updating status from A(active) to T(Truncated)
			Session session = sessionFactory.openSession();
			session.createQuery("update UnitSubSpecialityMapper ussm set ussm.status='T', ussm.updatedBy='"
					+ obj.getUpdatedBy() + "', ussm.updatedDate='"
					+ ADTCommonDateUtils.getDate(obj.getUpdatedDate(), "dd-M-yyyy HH:mm:ss") + "' WHERE ussm.status='A'")
					.executeUpdate();
			session.close();
			return new Response(SUCCESS, null, COMMON_SUCCESS, null, null);
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getUnitSubSpecialityCount(UnitSubSpecialityMapperDto unitSubSpecialityMapperDto)
			throws ApplicationException {
		try {
			Session session = sessionFactory.openSession();
			Criteria c = session.createCriteria(UnitSubSpecialityMapper.class);
			Criterion unit = Restrictions.eq("unitId", unitSubSpecialityMapperDto.getUnitId());
			Criterion org = Restrictions.eq("organizationId", unitSubSpecialityMapperDto.getOrganizationId());
			LogicalExpression andExp = Restrictions.and(unit, org);
			c.add(andExp);
			c.setProjection(Projections.rowCount());
			Long count = (Long) c.uniqueResult();
			return new Response(SUCCESS, null, null, null, count);
		} catch (Exception e) {
			e.printStackTrace();
			return new Response(ERROR, null, null, null, null);
		}
	}

	@Override
	public Response getSubSpecialityBySpecialityId(UnitSubSpecialityMapperDto unitSubSpecialityMapperDto)
			throws ApplicationException {
		try {
			List<UnitSubSpecialityMapperDto> unitSubSpecialtyMapperDtosList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_UNIT_SUB_SPECIALITY_LIST_BY_SPECIALITY_ID")
					.setInteger("orgId", unitSubSpecialityMapperDto.getOrganizationId())
					.setInteger("unitId", unitSubSpecialityMapperDto.getUnitId())
					.setInteger("specialityId", unitSubSpecialityMapperDto.getSpecialityId())
					.setResultTransformer(Transformers.aliasToBean(UnitSubSpecialityMapperDto.class)).list();
			return new Response(SUCCESS, null, null, unitSubSpecialtyMapperDtosList, null);

		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getSubSpecialityBySpecialityArray(UnitSubSpecialityMapperDto unitSubSpecialityMapperDto)
			throws ApplicationException {
		try {
			List<UnitSubSpecialityMapperDto> unitSubSpecialtyMapperDtosList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_UNIT_SUB_SPECIALITY_LIST_BY_SPECIALITY_ARRAY")
					.setInteger("orgId", unitSubSpecialityMapperDto.getOrganizationId())
					.setInteger("unitId", unitSubSpecialityMapperDto.getUnitId())
					.setParameterList("specialityList", unitSubSpecialityMapperDto.getSpecialityIdList())
					.setResultTransformer(Transformers.aliasToBean(UnitSubSpecialityMapperDto.class)).list();
			return new Response(SUCCESS, null, null, unitSubSpecialtyMapperDtosList, null);

		} catch (HibernateException he) {
			he.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getActiveUnitSubSpecialityList(UnitSubSpecialityMapperDto unitSubSpecialityMapperDto)
			throws ApplicationException {
		try {
			List<UnitSubSpecialityMapperDto> unitSubSpecialtyMapperDtosList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_ACTIVE_UNIT_SUB_SPECIALITY_LIST")
					.setInteger("orgId", unitSubSpecialityMapperDto.getOrganizationId())
					.setInteger("unitId", unitSubSpecialityMapperDto.getUnitId())
					.setResultTransformer(Transformers.aliasToBean(UnitSubSpecialityMapperDto.class)).list();
			return new Response(SUCCESS, null, null, unitSubSpecialtyMapperDtosList, null);

		} catch (HibernateException he) {
			he.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		} 
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getSubSpecialityBySpecialityArrayForTariff(UnitSubSpecialityMapperDto unitSubSpecialityMapperDto)
			throws ApplicationException {
		try {
			List<UnitSubSpecialityMapperDto> unitSubSpecialtyMapperDtosList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_UNIT_SUB_SPECIALITY_LIST_BY_SPECIALITY_ARRAY_FOR_TARIFF")
					.setInteger("orgId", unitSubSpecialityMapperDto.getOrganizationId())
					.setInteger("unitId", unitSubSpecialityMapperDto.getUnitId())
					.setParameterList("specialityList", unitSubSpecialityMapperDto.getSpecialityIdList())
					.setResultTransformer(Transformers.aliasToBean(UnitSubSpecialityMapperDto.class)).list();
			return new Response(SUCCESS, null, null, unitSubSpecialtyMapperDtosList, null);

		} catch (HibernateException he) {
			he.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}
}
