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
import com.param.global.dto.DayMasterDto;
import com.param.global.dto.HolidayCalenderMasterDto;
import com.param.global.model.HolidayCalenderMaster;

import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;

@SuppressWarnings({ "rawtypes", "unchecked" })
@Repository
public class HolidayCalenderMasterDaoImpl extends GenericDao<HolidayCalenderMaster, Integer>
		implements IHolidayCalenderMasterDao, ICommonConstants {

	@Autowired
	private DozerBeanMapper mapper;

	@Autowired
	private SessionFactory sessionFactory;

	public HolidayCalenderMasterDaoImpl() {
		super(HolidayCalenderMaster.class);

	}

	@Override
	public Response getHolidayCalenderByName(HolidayCalenderMasterDto holidayCalenderMasterDto)
			throws ApplicationException {
		try {
			List<HolidayCalenderMasterDto> holidayCalenderMasterDtoList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_HOLIDAY_LIST_BY_NAME")
					.setString("holidayName", holidayCalenderMasterDto.getHolidayCalenderDesc().toLowerCase())
					.setResultTransformer(Transformers.aliasToBean(HolidayCalenderMasterDto.class)).list();
			return new Response(SUCCESS, null, null, holidayCalenderMasterDtoList, null);

		} catch (HibernateException he) {
			he.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response addHolidayCalenderTypeMaster(HolidayCalenderMasterDto holidayCalenderMasterDto)
			throws ApplicationException {
		try {
			HolidayCalenderMaster holidayCalenderMaster = mapper.map(holidayCalenderMasterDto,
					HolidayCalenderMaster.class, "HolidayMasterDto_to_HolidayMaster");
			holidayCalenderMaster = save(holidayCalenderMaster);
			return new Response(SUCCESS, null, COMMON_SUCCESS, null, null);
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getHolidayCalenderMasterList(HolidayCalenderMasterDto holidayCalenderMasterDto) throws ApplicationException {
		try {
			List<HolidayCalenderMasterDto> holidayCalenderMasterDtoList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_HOLIDAY_LIST")
					.setInteger("orgId", holidayCalenderMasterDto.getOrganizationId())
					.setFirstResult(holidayCalenderMasterDto.getOffset() != null ? holidayCalenderMasterDto.getOffset() : 0)
					.setMaxResults(holidayCalenderMasterDto.getNoOfRecordsPerPage() != null ? holidayCalenderMasterDto.getNoOfRecordsPerPage() : 10)
					.setResultTransformer(Transformers.aliasToBean(HolidayCalenderMasterDto.class)).list();
			return new Response(SUCCESS, null, null, holidayCalenderMasterDtoList, null);

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
	public Response getHolidayByNameNotId(HolidayCalenderMasterDto holidayCalenderMasterDto)
			throws ApplicationException {
		try {
			List<HolidayCalenderMasterDto> holidayCalenderMasterDtoList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_HOLIDAY_LIST_BY_NAME_NOT_ID")
					.setInteger("holidayId", holidayCalenderMasterDto.getHolidayCalenderId())
					.setString("holidayName", holidayCalenderMasterDto.getHolidayCalenderDesc().toLowerCase())
					.setResultTransformer(Transformers.aliasToBean(HolidayCalenderMasterDto.class)).list();
			return new Response(SUCCESS, null, null, holidayCalenderMasterDtoList, null);

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
	public Response updateHolidayMaster(HolidayCalenderMasterDto holidayCalenderMasterDto) throws ApplicationException {
		try {
			HolidayCalenderMaster holidayCalenderMaster = findById(holidayCalenderMasterDto.getHolidayCalenderId());
			holidayCalenderMaster.setHolidayCalenderCode(holidayCalenderMasterDto.getHolidayCalenderCode());
			holidayCalenderMaster.setHolidayCalenderDesc(holidayCalenderMasterDto.getHolidayCalenderDesc());
			holidayCalenderMaster.setUpdatedBy(holidayCalenderMasterDto.getUpdatedBy());
			holidayCalenderMaster.setUpdatedDate(GlobalCommonDateUtils.getDate(holidayCalenderMasterDto.getUpdatedDate(),"dd-M-yyyy HH:mm:ss"));
			holidayCalenderMaster.setHolidayDate(GlobalCommonDateUtils.getDate(holidayCalenderMasterDto.getHolidayDate(),"dd-M-yyyy HH:mm:ss"));			
			holidayCalenderMaster.setDayId(holidayCalenderMasterDto.getDayId());
			update(holidayCalenderMaster);
			return new Response<HolidayCalenderMasterDto, Integer>(SUCCESS, null, COMMON_UPDATE, null, null);
		} catch (HibernateException he) {
			he.printStackTrace();
			return new Response<HolidayCalenderMasterDto, Integer>(ERROR, null, null, null, null);
		} catch (Exception e) {
			e.printStackTrace();
			return new Response<HolidayCalenderMasterDto, Integer>(ERROR, null, null, null, null);
		} finally {
			 ;
		}
	}

	@Override
	public Response getHolidayByID(HolidayCalenderMasterDto holidayCalenderMasterDto) throws ApplicationException {
		try {
			List<HolidayCalenderMasterDto> holidayCalenderMasterDtoList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_HOLIDAY_LIST_BY_ID")
					.setInteger("holidayId", holidayCalenderMasterDto.getHolidayCalenderId())
					.setResultTransformer(Transformers.aliasToBean(HolidayCalenderMasterDto.class)).list();
			return new Response(SUCCESS, null, null, holidayCalenderMasterDtoList, null);

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
	public Response updateHolidayStatus(HolidayCalenderMasterDto holidayCalenderMasterDto) throws ApplicationException {
		try {
			HolidayCalenderMaster holidayCalenderMaster = findById(holidayCalenderMasterDto.getHolidayCalenderId());
			holidayCalenderMaster.setStatus(holidayCalenderMasterDto.getStatus() == ACTIVE ? ACTIVE : INACTIVE);
			holidayCalenderMaster.setUpdatedBy(holidayCalenderMasterDto.getUpdatedBy());
			holidayCalenderMaster.setUpdatedDate(GlobalCommonDateUtils.getDate(holidayCalenderMasterDto.getUpdatedDate(),"dd-M-yyyy HH:mm:ss"));
			update(holidayCalenderMaster);
			return new Response<HolidayCalenderMasterDto, Integer>(SUCCESS, null, null, null, null);

		} catch (HibernateException he) {
			he.printStackTrace();
			return new Response<HolidayCalenderMasterDto, Integer>(ERROR, null, null, null, null);
		} catch (Exception e) {
			e.printStackTrace();
			return new Response<HolidayCalenderMasterDto, Integer>(ERROR, null, null, null, null);
		} finally {
			 ;
		}
	}

	@Override
	public Response getActiveHolidayMasterList() throws ApplicationException {
		try {
			List<HolidayCalenderMasterDto> holidayCalenderMasterDtoList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_ACTIVE_HOLIDAY_LIST")
					.setResultTransformer(Transformers.aliasToBean(HolidayCalenderMasterDto.class)).list();
			return new Response(SUCCESS, null, null, holidayCalenderMasterDtoList, null);

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
	public Response getActiveHolidayDayList(DayMasterDto dayMasterDto) throws ApplicationException {
		try {
			List<HolidayCalenderMasterDto> holidayCalenderMasterDtosList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_ACTIVE_HOLIDAY_LIST")
					.setInteger("orgId", dayMasterDto.getOrganizationId())
					.setResultTransformer(Transformers.aliasToBean(HolidayCalenderMasterDto.class)).list();
			return new Response(SUCCESS, null, null, holidayCalenderMasterDtosList, null);

		} catch (HibernateException he) {
			he.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		} 
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getCount(HolidayCalenderMasterDto holidayCalenderMasterDto) {
		try {
			Session session = sessionFactory.openSession();
			Criteria c = session.createCriteria(HolidayCalenderMaster.class);
			c.add(Restrictions.eq("organizationId", holidayCalenderMasterDto.getOrganizationId()));
			c.setProjection(Projections.rowCount());
			Long count = (Long) c.uniqueResult();
			return new Response(SUCCESS, null, null, null, count);
		} catch (Exception e) {
			e.printStackTrace();
			return new Response(ERROR, null, null, null, null);
		}
	}

	@Override
	public Response getActiveDayListById(DayMasterDto dayMasterDto) throws ApplicationException {
		try {
			List<DayMasterDto> dayMasterDtoList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_ACTIVE_DAY_LIST_BY_ID").setInteger("dayId", dayMasterDto.getDayId())
					.setResultTransformer(Transformers.aliasToBean(DayMasterDto.class)).list();
			return new Response(SUCCESS, null, null, dayMasterDtoList, null);

		} catch (HibernateException he) {
			he.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		} 
		return new Response(ERROR, null, null, null, null);
	}
}
