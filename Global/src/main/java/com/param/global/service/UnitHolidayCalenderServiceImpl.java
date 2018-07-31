package com.param.global.service;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;
import com.param.global.dao.IUnitHolidayCalenderDao;
import com.param.global.dto.UnitHolidayCalenderMapperDto;

import in.param.exception.ApplicationException;
@Service
@SuppressWarnings({ "rawtypes", "unchecked" })
public class UnitHolidayCalenderServiceImpl implements IUnitHolidayCalenerService, ICommonConstants {

	@Autowired
	IUnitHolidayCalenderDao iUnitHolidayCalenderDao;

	@Override
	public Response saveUnitHolidayCalener(UnitHolidayCalenderMapperDto unitHolidayCalenderMapperDto)
			throws ApplicationException {
		Response resTrunc = iUnitHolidayCalenderDao.truncateUnitHolidayCalener(unitHolidayCalenderMapperDto);
		if (resTrunc.getStatus() == SUCCESS) {
			Iterator<UnitHolidayCalenderMapperDto> itr = unitHolidayCalenderMapperDto
					.getUnitHolidayCalenderMapperDtosList().iterator();
			while (itr.hasNext()) {
				UnitHolidayCalenderMapperDto UnitHolidayCalenderMapperDto = new UnitHolidayCalenderMapperDto();

				UnitHolidayCalenderMapperDto obj = itr.next();

				UnitHolidayCalenderMapperDto.setUnitId(obj.getUnitId());
				UnitHolidayCalenderMapperDto.setOrganizationId(obj.getOrganizationId());
				UnitHolidayCalenderMapperDto.setHolidayId(obj.getHolidayId());			
				UnitHolidayCalenderMapperDto.setStatus('A');
				UnitHolidayCalenderMapperDto.setCreatedBy(obj.getCreatedBy());
				UnitHolidayCalenderMapperDto.setCreatedDate(obj.getCreatedDate());
				UnitHolidayCalenderMapperDto.setUpdatedBy(obj.getUpdatedBy());
				UnitHolidayCalenderMapperDto.setUpdatedDate(obj.getUpdatedDate());

				iUnitHolidayCalenderDao.saveUnitHolidayCalener(UnitHolidayCalenderMapperDto);

			}
			return new Response(SUCCESS, null, COMMON_SUCCESS, null, null);
		} else {
			return new Response(ERROR, null, null, null, null);
		}

	}

	@Override
	public Response getUnitHolidayCalenerList(UnitHolidayCalenderMapperDto unitHolidayCalenderMapperDto)
			throws ApplicationException {
		try {

			return iUnitHolidayCalenderDao.getUnitHolidayCalenerList(unitHolidayCalenderMapperDto);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}


	@Override
	public Response updateStatusForUnitHolidayCalener(UnitHolidayCalenderMapperDto unitHolidayCalenderMapperDto)
			throws ApplicationException {
		try {
			Response deptResponse = iUnitHolidayCalenderDao.getUnitHolidayCalenerById(unitHolidayCalenderMapperDto);
			if (deptResponse.getStatus().equals(SUCCESS)) {

				iUnitHolidayCalenderDao.updateStatusForUnitHolidayCalener(unitHolidayCalenderMapperDto);
				return new Response(SUCCESS, null, COMMON_UPDATE, null, null);

			} else {
				return new Response(ERROR, null, "ID does not exist", null, null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getUnitHolidayCalenerCount(UnitHolidayCalenderMapperDto unitHolidayCalenderMapperDto)
			throws ApplicationException {
		try {
			return iUnitHolidayCalenderDao.getCount(unitHolidayCalenderMapperDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getActiveUnitHolidayCalenerList(UnitHolidayCalenderMapperDto unitHolidayCalenderMapperDto)
			throws ApplicationException {
		try {
			return iUnitHolidayCalenderDao.getActiveUnitHolidayCalenerList(unitHolidayCalenderMapperDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

}
