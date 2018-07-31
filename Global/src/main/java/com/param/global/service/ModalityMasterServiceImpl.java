package com.param.global.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.param.global.common.GlobalCommonDateUtils;
import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;
import com.param.global.common.ScheduleActions;
import com.param.global.dao.IModalityMasterDao;
import com.param.global.dao.IScheduleLogsDao;
import com.param.global.dto.ModalityMasterDto;
import com.param.global.dto.ScheduleLogsDto;
import com.param.global.model.ModalityMaster;

import in.param.exception.ApplicationException;

@Service
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ModalityMasterServiceImpl implements IModalityMasterService, ICommonConstants {

	@Autowired
	IModalityMasterDao iModalityMasterDao;

	@Autowired
	IScheduleLogsDao iScheduleLogsDao;

	@Override
	public Response saveModalityMaster(ModalityMasterDto modalityMasterDto) throws ApplicationException {
		try {
			Response res = iModalityMasterDao.getModalityByName(modalityMasterDto);
			if (res.getListObject() != null && res.getListObject().size() > 0) {
				return new Response(ERROR, null, ALREADY_EXIST, null, null);
			} else {
				Response response = iModalityMasterDao.saveModalityMaster(modalityMasterDto);
				//modality Entry in log table start
				if (response.getStatus().equalsIgnoreCase(SUCCESS) && response.getObject() != null) {
					ModalityMaster modalityMaster = (ModalityMaster) response.getObject();
					ScheduleLogsDto scheduleLogsDto=new ScheduleLogsDto();
					scheduleLogsDto.setAction(ScheduleActions.INSERT);
					scheduleLogsDto.setAddedBy(1);
					scheduleLogsDto.setAddedDate(GlobalCommonDateUtils.getStringDate(new Date(), "dd-M-yyyy ss:mm:yyyy"));
					scheduleLogsDto.setApplication(ScheduleActions.HIS);
					scheduleLogsDto.setDateTime(GlobalCommonDateUtils.getStringDate(modalityMaster.getCreatedDate(), "dd-M-yyyy ss:mm:yyyy"));
					scheduleLogsDto.setErrorCount(0);
					scheduleLogsDto.setPriority(ScheduleActions.PRIORITY_NORM);
					scheduleLogsDto.setRecordId(modalityMaster.getModalityId());
					scheduleLogsDto.setStatus(ScheduleActions.STATUS_PENDING);
					String [] tableName=modalityMaster.getClass().getName().split("\\.");
					scheduleLogsDto.setTableName(tableName[tableName.length-1]);
					iScheduleLogsDao.saveScheduleLogs(scheduleLogsDto);
				}
				//modality Entry in log table end
				return new Response(SUCCESS, null, COMMON_SUCCESS, null, null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getModalityMasterList(ModalityMasterDto modalityMasterDto) throws ApplicationException {
		try {
			return iModalityMasterDao.getModalityMasterList(modalityMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response updateModalityMaster(ModalityMasterDto modalityMasterDto) throws ApplicationException {
		try {
			Response res = iModalityMasterDao.getModalityByNameNotId(modalityMasterDto);
			if (res.getListObject() != null && res.getListObject().size() > 0) {
				return new Response(ERROR, null, ALREADY_EXIST, null, null);
			} else {
				iModalityMasterDao.updateModalityMaster(modalityMasterDto);
				return new Response(SUCCESS, null, COMMON_UPDATE, null, null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response updateStatusForModality(ModalityMasterDto modalityMasterDto) throws ApplicationException {
		try {
			Response res = iModalityMasterDao.getModalityById(modalityMasterDto);
			if (res.getListObject() != null && res.getListObject().size() > 0) {
				iModalityMasterDao.updateModalityStatus(modalityMasterDto);
				return new Response(SUCCESS, null, COMMON_UPDATE, null, null);
			} else {
				return new Response(ERROR, null, ALREADY_EXIST, null, null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getActiveModalityList(ModalityMasterDto modalityMasterDto) throws ApplicationException {
		try {
			return iModalityMasterDao.getActiveModalityList(modalityMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getModalityCount(ModalityMasterDto modalityMasterDto) throws ApplicationException {
		try {
			return iModalityMasterDao.getModalityCount(modalityMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getModalityById(ModalityMasterDto modalityMasterDto) throws ApplicationException {
		try {
			return iModalityMasterDao.getModalityById(modalityMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getModalityBySubSpecialityId(ModalityMasterDto modalityMasterDto) throws ApplicationException {
		try {
			return iModalityMasterDao.getModalityBySubSpecialityId(modalityMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getModalityBySubSpecialityArray(ModalityMasterDto modalityMasterDto) throws ApplicationException {
		try {
			return iModalityMasterDao.getModalityBySubSpecialityArray(modalityMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

}
