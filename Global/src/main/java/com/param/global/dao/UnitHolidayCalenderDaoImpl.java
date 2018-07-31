package com.param.global.dao;

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

import com.param.global.common.GlobalCommonDateUtils;
import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;
import com.param.global.dto.HolidayCalenderMasterDto;
import com.param.global.dto.UnitHolidayCalenderMapperDto;
import com.param.global.model.UnitHolidayCalenderMapper;

import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;
@SuppressWarnings({"rawtypes","unchecked"})
@Repository
public class UnitHolidayCalenderDaoImpl extends GenericDao<UnitHolidayCalenderMapper, Integer>implements IUnitHolidayCalenderDao,ICommonConstants{

	@Autowired
	SessionFactory sessionFactory;
	
	@Autowired
	DozerBeanMapper mapper;
	
	public UnitHolidayCalenderDaoImpl() {
		super(UnitHolidayCalenderMapper.class);
		// TODO Auto-generated constructor stub
	}

	
	@Override
	public Response truncateUnitHolidayCalener(UnitHolidayCalenderMapperDto obj) throws ApplicationException {
		try {
			// Updating status from A(active) to T(Truncated)
			Session session = sessionFactory.openSession();
			session.createQuery("update UnitHolidayCalenderMapper uhcm set uhcm.status='T', uhcm.updatedBy='"
					+ obj.getUpdatedBy() + "', uhcm.updatedDate='"
					+ GlobalCommonDateUtils.getDate(obj.getUpdatedDate(), "dd-M-yyyy HH:mm:ss") + "' WHERE uhcm.status='A'")
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
	public Response saveUnitHolidayCalener(UnitHolidayCalenderMapperDto unitHolidayCalenderMapperDto)
			throws ApplicationException {
		try {
			UnitHolidayCalenderMapper unitHolidayCalenderMapper = mapper.map(unitHolidayCalenderMapperDto, UnitHolidayCalenderMapper.class,
					"UnitHolidayCalenderMapperDto_to_UnitHolidayCalenderMapper");
			save(unitHolidayCalenderMapper);
			return new Response(SUCCESS, null, COMMON_SUCCESS, null, null);
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getUnitHolidayCalenerList(UnitHolidayCalenderMapperDto unitHolidayCalenderMapperDto)
			throws ApplicationException {
		try {
			List<UnitHolidayCalenderMapperDto> unitHolidayCalenderMapperDtosList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_UNIT_HOLIDAY_CALENDER_LIST")
					.setInteger("orgId", unitHolidayCalenderMapperDto.getOrganizationId())
					.setInteger("unitId", unitHolidayCalenderMapperDto.getUnitId())
					.setFirstResult(unitHolidayCalenderMapperDto.getOffset() != null ? unitHolidayCalenderMapperDto.getOffset() : 0)
					.setMaxResults(unitHolidayCalenderMapperDto.getNoOfRecordsPerPage() != null ? unitHolidayCalenderMapperDto.getNoOfRecordsPerPage() : 10)
					.setResultTransformer(Transformers.aliasToBean(UnitHolidayCalenderMapperDto.class)).list();
			return new Response(SUCCESS, null, null, unitHolidayCalenderMapperDtosList, null);

		} catch (HibernateException he) {
			he.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		} 
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getUnitHolidayCalenerById(UnitHolidayCalenderMapperDto unitHolidayCalenderMapperDto)
			throws ApplicationException {
		try
		{
		UnitHolidayCalenderMapper unitHolidayCalenderMapper=findById(unitHolidayCalenderMapperDto.getUnitHolidayCalenderId());
		if(unitHolidayCalenderMapper.getUnitHolidayCalenderId()==unitHolidayCalenderMapperDto.getUnitHolidayCalenderId())
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
	public Response updateStatusForUnitHolidayCalener(UnitHolidayCalenderMapperDto unitHolidayCalenderMapperDto) {
		try {
			UnitHolidayCalenderMapper unitHolidayCalenderMapper = findById(unitHolidayCalenderMapperDto.getUnitHolidayCalenderId());

			unitHolidayCalenderMapper.setStatus(unitHolidayCalenderMapperDto.getStatus() == ACTIVE ? ACTIVE : INACTIVE);
			unitHolidayCalenderMapper.setUpdatedBy(unitHolidayCalenderMapperDto.getUpdatedBy());
			unitHolidayCalenderMapper.setUpdatedDate(
					GlobalCommonDateUtils.getDate(unitHolidayCalenderMapperDto.getUpdatedDate(), "dd-M-yyy HH:mm:ss"));
			update(unitHolidayCalenderMapper);
			return new Response(SUCCESS, null, COMMON_UPDATE, null, null);

		} catch (HibernateException he) {
			he.printStackTrace();
			return new Response(ERROR, null, null, null, null);
		} catch (Exception e) {
			e.printStackTrace();
			return new Response(ERROR, null, null, null, null);
		} 
	}

	@Override
	public Response getCount(UnitHolidayCalenderMapperDto unitHolidayCalenderMapperDto) throws ApplicationException {
		try{
			Session session=sessionFactory.openSession();
			Criteria c=session.createCriteria(UnitHolidayCalenderMapper.class); 
			Criterion unit=Restrictions.eq("unitId", unitHolidayCalenderMapperDto.getUnitId());
			Criterion org=Restrictions.eq("organizationId", unitHolidayCalenderMapperDto.getOrganizationId());
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
	public Response getActiveUnitHolidayCalenerList(UnitHolidayCalenderMapperDto unitHolidayCalenderMapperDto)
			throws ApplicationException {
		try {
			List<HolidayCalenderMasterDto> unitHolidayCalenderMapperDtosList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_ACTIVE_UNIT_HOLIDAY_CALENDER_LIST")
					.setInteger("orgId", unitHolidayCalenderMapperDto.getOrganizationId())
					.setInteger("unitId", unitHolidayCalenderMapperDto.getUnitId())
					.setFirstResult(unitHolidayCalenderMapperDto.getOffset() != null ? unitHolidayCalenderMapperDto.getOffset() : 0)
					.setMaxResults(unitHolidayCalenderMapperDto.getNoOfRecordsPerPage() != null ? unitHolidayCalenderMapperDto.getNoOfRecordsPerPage() : 10)
					.setResultTransformer(Transformers.aliasToBean(HolidayCalenderMasterDto.class)).list();
			return new Response(SUCCESS, null, null, unitHolidayCalenderMapperDtosList, null);

		} catch (HibernateException he) {
			he.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		} 
		return new Response(ERROR, null, null, null, null);
	}

}
