package com.param.global.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.param.global.common.GlobalCommonDateUtils;
import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;
import com.param.global.dao.IHolidayCalenderMasterDao;
import com.param.global.dto.DayMasterDto;
import com.param.global.dto.HolidayCalenderMasterDto;

import in.param.exception.ApplicationException;

@Service
@SuppressWarnings({ "rawtypes", "unchecked" })
public class HolidayCalenderMasterServiceImpl implements IHolidayCalenderMasterService, ICommonConstants {

	@Autowired
	private IHolidayCalenderMasterDao iHolidayMasterDao;

	@Override
	public Response addHolidayCalenderMaster(HolidayCalenderMasterDto holidayCalenderMasterDto)
			throws ApplicationException {
		try {
			Response deptResponse = iHolidayMasterDao.getHolidayCalenderByName(holidayCalenderMasterDto);
			if (deptResponse.getListObject() != null && deptResponse.getListObject().size() > 0) {

				return new Response(ERROR, null, ALREADY_EXIST, null, null);

			} else {

				/*
				 * holidayCalenderMasterDto
				 * .setCreatedDate(ADTCommonDateUtils.getStringDateByzone(
				 * "Asia/Calcutta", "dd-M-yyyy HH:mm:ss"));
				 * holidayCalenderMasterDto
				 * .setUpdatedDate(ADTCommonDateUtils.getStringDateByzone(
				 * "Asia/Calcutta", "dd-M-yyyy HH:mm:ss"));
				 */
				iHolidayMasterDao.addHolidayCalenderTypeMaster(holidayCalenderMasterDto);
				return new Response(SUCCESS, null, COMMON_SUCCESS, null, null);
			}
		} catch (Exception e) {
			e.printStackTrace();

			return new Response(ERROR, null, null, null, null);
		}
	}

	@Override
	public Response getHolidayCalenderMasterList(HolidayCalenderMasterDto holidayCalenderMasterDto)
			throws ApplicationException {
		try {
			return iHolidayMasterDao.getHolidayCalenderMasterList(holidayCalenderMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response updateHolidayCalenderMaster(HolidayCalenderMasterDto holidayCalenderMasterDto)
			throws ApplicationException {
		try {
			Response deptResponse = iHolidayMasterDao.getHolidayByNameNotId(holidayCalenderMasterDto);
			if (deptResponse.getListObject() != null && deptResponse.getListObject().size() > 0) {
				return new Response(ERROR, null, ALREADY_EXIST, null, null);

			} else {

				holidayCalenderMasterDto.setHolidayDate(holidayCalenderMasterDto.getHolidayDate());
				holidayCalenderMasterDto
						.setUpdatedDate(GlobalCommonDateUtils.getStringDateByzone("Asia/Calcutta", "dd-M-yyyy HH:mm:ss"));

				iHolidayMasterDao.updateHolidayMaster(holidayCalenderMasterDto);
				return new Response(SUCCESS, null, COMMON_UPDATE, null, null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getHolidayCalenderById(HolidayCalenderMasterDto holidayCalenderMasterDto)
			throws ApplicationException {
		try {
			return iHolidayMasterDao.getHolidayByID(holidayCalenderMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response updateStatusForHolidayCalender(HolidayCalenderMasterDto holidayCalenderMasterDto)
			throws ApplicationException {
		try {
			Response deptResponse = iHolidayMasterDao.getHolidayByID(holidayCalenderMasterDto);
			if (deptResponse.getListObject() != null && deptResponse.getListObject().size() > 0) {

				holidayCalenderMasterDto
						.setUpdatedDate(GlobalCommonDateUtils.getStringDateByzone("Asia/Calcutta", "dd-M-yyyy HH:mm:ss"));

				iHolidayMasterDao.updateHolidayStatus(holidayCalenderMasterDto);
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
	public Response getActiveHolidayCalenderList() throws ApplicationException {
		try {
			return iHolidayMasterDao.getActiveHolidayMasterList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getActiveHolidayDayList(DayMasterDto dayMasterDto) throws ApplicationException {
		try {
			return iHolidayMasterDao.getActiveHolidayDayList(dayMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getHolidayCount(HolidayCalenderMasterDto holidayCalenderMasterDto) throws ApplicationException {
		try {
			return iHolidayMasterDao.getCount(holidayCalenderMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getActiveDayListById(DayMasterDto dayMasterDto) throws ApplicationException {
		try {
			return iHolidayMasterDao.getActiveDayListById(dayMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

}
