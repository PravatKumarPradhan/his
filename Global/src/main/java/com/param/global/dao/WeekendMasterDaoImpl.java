package com.param.global.dao;

import java.util.List;

import org.dozer.DozerBeanMapper;
import org.hibernate.HibernateException;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.param.global.common.GlobalCommonDateUtils;
import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;
import com.param.global.dto.VisitTypeMasterDto;
import com.param.global.dto.WeekendMasterDto;
import com.param.global.model.WeekendMaster;

import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;

@Repository
@SuppressWarnings({ "rawtypes", "unchecked" })
public class WeekendMasterDaoImpl extends GenericDao<WeekendMaster, Integer>implements IWeekendMasterDao, ICommonConstants {

	@Autowired
	DozerBeanMapper mapper;

	public WeekendMasterDaoImpl() {
		super(WeekendMaster.class);
	}

	@Override
	public Response saveWeekendMaster(WeekendMasterDto weekendMasterDto) throws ApplicationException {
		try {
			WeekendMaster weekendMaster = new WeekendMaster();
				weekendMaster.setOrganizationId(weekendMasterDto.getOrganizationId());
				weekendMaster.setUnitId(weekendMasterDto.getUnitId());
				weekendMaster.setWeekendDate(GlobalCommonDateUtils.getDate(weekendMasterDto.getWeekendDate(),"yyyy-dd-mm"));
				weekendMaster.setDayId(weekendMasterDto.getDayId());
				weekendMaster.setIsHalfDay(weekendMasterDto.getIsHalfDay());
				weekendMaster.setFromTime(weekendMasterDto.getFromTime());
				weekendMaster.setToTime(weekendMasterDto.getToTime());
				weekendMaster.setCreatedBy(weekendMasterDto.getCreatedBy());
				weekendMaster.setUpdatedBy(weekendMasterDto.getUpdatedBy());
				weekendMaster.setCreatedDate(GlobalCommonDateUtils.getDate(weekendMasterDto.getCreatedDate(),"dd-mm-yyyy HH:mm:ss"));
				weekendMaster.setCreatedDate(GlobalCommonDateUtils.getDate(weekendMasterDto.getUpdatedDate(),"dd-mm-yyyy HH:mm:ss"));
				weekendMaster.setStatus('A');
			save(weekendMaster);
			return new Response(SUCCESS, SUCCESS_CODE, COMMON_SUCCESS, null, null);
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getWeekendMasterList(WeekendMasterDto weekendMasterDto) throws ApplicationException {
		try {
			List<WeekendMasterDto> getWeekendMasterDtosList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_ACTIVE_WEEKENDS_LIST")
					.setInteger("organizationId", weekendMasterDto.getOrganizationId())
					.setInteger("unitId",weekendMasterDto.getUnitId() )
					.setResultTransformer(Transformers.aliasToBean(WeekendMasterDto.class)).list();
			return new Response (SUCCESS, SUCCESS_CODE, null, getWeekendMasterDtosList, null);
		}catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

}
