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
import com.param.adt.master.global.dto.ICUTypeMasterDto;
import com.param.adt.master.unit.dto.UnitICUTypeMapperDto;
import com.param.adt.master.unit.model.UnitICUTypeMapper;

import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;

@SuppressWarnings({ "rawtypes", "unchecked" })
@Repository

public class UnitICUTypeDaoImpl extends GenericDao<UnitICUTypeMapper, Integer>
		implements ICommonConstants, IUnitICUDao {

	public UnitICUTypeDaoImpl() {
		super(UnitICUTypeMapper.class);
		// TODO Auto-generated constructor stub
	}

	@Autowired
	SessionFactory sessionFactory;

	@Autowired
	DozerBeanMapper mapper;

	@Override
	public Response saveUnitICUType(UnitICUTypeMapperDto unIcuTypeMapperDto) throws ApplicationException {
		try {
			UnitICUTypeMapper unitUnitICUTypeMapper = mapper.map(unIcuTypeMapperDto, UnitICUTypeMapper.class,
					"UnitICUTypeMapperDto_to_UnitICUTypeMapper");
			save(unitUnitICUTypeMapper);
			return new Response(SUCCESS, null, COMMON_SUCCESS, null, null);
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getUnitICUTypeList(UnitICUTypeMapperDto unIcuTypeMapperDto) throws ApplicationException {
		try {
			List<UnitICUTypeMapperDto> unitICUTypeMapperDtosList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_UNIT_ICU_TYPE_LIST")
					.setInteger("orgId", unIcuTypeMapperDto.getOrganizationId())
					.setInteger("unitId", unIcuTypeMapperDto.getUnitId())
					.setFirstResult(unIcuTypeMapperDto.getOffset() != null ? unIcuTypeMapperDto.getOffset() : 0)
					.setMaxResults(unIcuTypeMapperDto.getNoOfRecordsPerPage() != null ? unIcuTypeMapperDto.getNoOfRecordsPerPage() : 10)
					.setResultTransformer(Transformers.aliasToBean(UnitICUTypeMapperDto.class)).list();
			return new Response(SUCCESS, null, null, unitICUTypeMapperDtosList, null);

		} catch (HibernateException he) {
			he.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		} 
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getUnitICUTypeById(UnitICUTypeMapperDto unitICUTypeMapperDto) throws ApplicationException {
		try
		{
			UnitICUTypeMapper unitICUTypeMapper=findById(unitICUTypeMapperDto.getUnitICUTypeId());
		if(unitICUTypeMapper.getUnitICUTypeId()==unitICUTypeMapperDto.getUnitICUTypeId())
			return new Response(SUCCESS, null, null, null, null);
		else 
			return new Response(ERROR, null, null, null, null);
		}catch(Exception e)
		{
			
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response updateStatusForUnitICUType(UnitICUTypeMapperDto unitICUTypeMapperDto) throws ApplicationException {
		try {
			UnitICUTypeMapper unitICUTypeMapper = findById(unitICUTypeMapperDto.getUnitICUTypeId());

			unitICUTypeMapper.setStatus(unitICUTypeMapperDto.getStatus() == ACTIVE ? ACTIVE : INACTIVE);
			unitICUTypeMapper.setUpdatedBy(unitICUTypeMapperDto.getUpdatedBy());
			unitICUTypeMapper.setUpdatedDate(
					ADTCommonDateUtils.getDate(unitICUTypeMapperDto.getUpdatedDate(), "dd-M-yyy HH:mm:ss"));
			update(unitICUTypeMapper);
			return new Response(SUCCESS, null, COMMON_UPDATE, null, null);

		} catch (HibernateException he) {
			he.printStackTrace();
			return new Response(ERROR, null, null, null, null);
		} catch (Exception e) {
			e.printStackTrace();
			return new Response(ERROR, null, null, null, null);
		} finally {
			 ;
		}
	}

	@Override
	public Response truncateUnitICUType(UnitICUTypeMapperDto obj) throws ApplicationException {
		try {
			// Updating status from A(active) to T(Truncated)
			Session session = sessionFactory.openSession();
			session.createQuery("update UnitICUTypeMapper ussm set ussm.status='T', ussm.updatedBy='"
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
	
	public Response getCount(UnitICUTypeMapperDto unitICUTypeMapperDto)
	{
		try{
			Session session=sessionFactory.openSession();
			Criteria c=session.createCriteria(UnitICUTypeMapper.class); 
			Criterion unit=Restrictions.eq("unitId", unitICUTypeMapperDto.getUnitId());
			Criterion org=Restrictions.eq("organizationId", unitICUTypeMapperDto.getOrganizationId());
			LogicalExpression andExp = Restrictions.and(unit, org);
			c.add(andExp);
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
	public Response getActiveUnitICUTypeList(UnitICUTypeMapperDto unitICUTypeMapperDto) throws ApplicationException {
		try {
			List<ICUTypeMasterDto> unitICUTypeMapperDtosList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_ACTIVE_UNIT_ICU_TYPE_LIST")
					.setInteger("orgId", unitICUTypeMapperDto.getOrganizationId())
					.setInteger("unitId", unitICUTypeMapperDto.getUnitId())
					.setResultTransformer(Transformers.aliasToBean(ICUTypeMasterDto.class)).list();
			return new Response(SUCCESS, null, null, unitICUTypeMapperDtosList, null);

		} catch (HibernateException he) {
			he.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

}
