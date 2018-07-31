package com.param.global.dao;

import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;

import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.param.global.common.GlobalCommonDateUtils;
import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;
import com.param.global.dto.ScheduleLogsDto;
import com.param.global.model.ScheduleLogs;

@Repository
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ScheduleLogsDaoImpl extends GenericDao<ScheduleLogs, Integer>
		implements IScheduleLogsDao, ICommonConstants {

	public ScheduleLogsDaoImpl() {
		super(ScheduleLogs.class);
	}

	@Override
	public Response saveScheduleLogs(ScheduleLogsDto scheduleLogsDto) throws ApplicationException {
		try {
			ScheduleLogs scheduleLogs = new ScheduleLogs();
			scheduleLogs.setAction(scheduleLogsDto.getAction());
			scheduleLogs.setAddedBy(scheduleLogsDto.getAddedBy());
			scheduleLogs.setDateTime(GlobalCommonDateUtils.getDate(scheduleLogsDto.getDateTime(),"dd-M-yyyy HH:mm:ss"));
			scheduleLogs.setAddedDate(scheduleLogsDto.getAddedDate() != null ?GlobalCommonDateUtils.getDate(scheduleLogsDto.getAddedDate(), "dd-M-yyyy hh:mm:ss"): GlobalCommonDateUtils.getDate(new Date(), "dd-M-yyyy hh:mm:ss"));
			scheduleLogs.setApplication("HIS");
			scheduleLogs.setErrorCount(0);
			scheduleLogs.setPriority("NORMAL");
			scheduleLogs.setRecordId(scheduleLogsDto.getRecordId());
			scheduleLogs.setStatus("PENDING");
			scheduleLogs.setTableName(scheduleLogsDto.getTableName());
			scheduleLogs.setDateTime(scheduleLogsDto.getDateTime() != null ?GlobalCommonDateUtils.getDate(scheduleLogsDto.getDateTime(), "dd-M-yyyy hh:mm:ss") : GlobalCommonDateUtils.getDate(new Date(), "dd-M-yyyy hh:mm:ss"));
			scheduleLogs = save(scheduleLogs);
			return new Response(SUCCESS, SUCCESS_CODE, null, null, null);
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getPendingScheduleLogs() throws ApplicationException {
		try {
			List<ScheduleLogsDto> pendingLogsList = sessionFactory.getCurrentSession()
			.getNamedQuery("GET_PENDING_SCHEDULED_LOGS")
			.setResultTransformer(Transformers.aliasToBean(ScheduleLogsDto.class)).list();
			return new Response(SUCCESS, SUCCESS_CODE, null, pendingLogsList, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, ERROR_CODE, null, null, null);
	}

	@Override
	public Response markScheduleLogsCompleted(Integer scheduleLogsId) throws ApplicationException {
		try {
			sessionFactory.getCurrentSession()
			.getNamedQuery("MARK_SCHEDULED_LOGS_COMPLETED")
			.setInteger("scheduleLogsId", scheduleLogsId).executeUpdate();
			return new Response<>(SUCCESS, SUCCESS_CODE, null, null, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, null, null, null);
	}

	@Override
	public Response markScheduleLogsError(Integer scheduleLogsId) throws ApplicationException {
		try {
			sessionFactory.getCurrentSession()
			.getNamedQuery("MARK_SCHEDULED_LOGS_ERROR")
			.setInteger("scheduleLogsId", scheduleLogsId).executeUpdate();
			return new Response<>(SUCCESS, SUCCESS_CODE, null, null, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, null, null, null);
	}

}
