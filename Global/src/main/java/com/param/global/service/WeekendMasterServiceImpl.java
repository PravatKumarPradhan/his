package com.param.global.service;

import java.util.List;
import java.util.ListIterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.param.global.common.GlobalCommonDateUtils;
import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;
import com.param.global.dao.IWeekendMasterDao;
import com.param.global.dto.WeekendMasterDto;
import in.param.exception.ApplicationException;

@Service
@SuppressWarnings({"rawtypes","unchecked"})
public class WeekendMasterServiceImpl implements IWeekendMasterService,ICommonConstants{

	@Autowired
	IWeekendMasterDao iWeekendMasterDao;
	
	@Override
	public Response saveWeekendMaster(WeekendMasterDto weekendMasterDto) throws ApplicationException {
		try {

			List<String> datesList = GlobalCommonDateUtils.getDatesOfSpecificDayInSingleYear(weekendMasterDto.getYear(),
					weekendMasterDto.getDayId());

			ListIterator<String> itr = datesList.listIterator();
			while (itr.hasNext()) {
				weekendMasterDto.setWeekendDate(itr.next());
				iWeekendMasterDao.saveWeekendMaster(weekendMasterDto);
			}

			return new Response(SUCCESS, SUCCESS_CODE, COMMON_SUCCESS, null, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, ERROR_CODE, null, null, null);

	}

	@Override
	public Response getWeekendMasterList(WeekendMasterDto weekendMasterDto) throws ApplicationException {
		try {
				return iWeekendMasterDao.getWeekendMasterList(weekendMasterDto);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, ERROR_CODE, null, null, null);
	}

}
