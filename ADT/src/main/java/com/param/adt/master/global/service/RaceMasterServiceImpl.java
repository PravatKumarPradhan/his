package com.param.adt.master.global.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.param.adt.common.ICommonConstants;
import com.param.adt.common.Response;
import com.param.adt.master.global.dao.IRaceMasterDao;
import com.param.adt.master.global.dto.RaceMasterDto;

import in.param.exception.ApplicationException;

@Service
@SuppressWarnings({ "rawtypes", "unchecked" })
public class RaceMasterServiceImpl implements IRaceMasterService, ICommonConstants {

	@Autowired
	private IRaceMasterDao iRaceMasterDao;

	@Override
	public Response addRaceMaster(RaceMasterDto raceMasterDto) throws ApplicationException {
		try {

			Response deptResponse = iRaceMasterDao.getRaceByName(raceMasterDto);
			if (deptResponse.getListObject() != null && deptResponse.getListObject().size() > 0) {

				return new Response(ERROR, null, ALREADY_EXIST, null, null);

			} else {
				/*raceMasterDto
						.setCreatedDate(CommonDateUtils.getStringDateByzone("Asia/Calcutta", "dd-M-yyyy HH:mm:ss"));
				raceMasterDto
						.setUpdatedDate(CommonDateUtils.getStringDateByzone("Asia/Calcutta", "dd-M-yyyy HH:mm:ss"));*/
				iRaceMasterDao.addRaceTypeMaster(raceMasterDto);
				return new Response(SUCCESS, null, COMMON_SUCCESS, null, null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getRaceMasterList(RaceMasterDto raceMasterDto) throws ApplicationException {
		try {
			return iRaceMasterDao.getRaceMasterList(raceMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response updateRaceMaster(RaceMasterDto raceMasterDto) throws ApplicationException {
		try {
			Response deptResponse = iRaceMasterDao.getRaceByNameNotId(raceMasterDto);
			if (deptResponse.getListObject() != null && deptResponse.getListObject().size() > 0) {

				return new Response(ERROR, null, ALREADY_EXIST, null, null);

			} else {
				/*raceMasterDto
						.setUpdatedDate(CommonDateUtils.getStringDateByzone("Asia/Calcutta", "dd-M-yyyy HH:mm:ss"));*/
				iRaceMasterDao.updateRaceMaster(raceMasterDto);
				return new Response(SUCCESS, null, COMMON_UPDATE, null, null);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getRaceById(RaceMasterDto raceMasterDto) throws ApplicationException {
		try {
			return iRaceMasterDao.getRaceByID(raceMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response updateStatusForRace(RaceMasterDto raceMasterDto) throws ApplicationException {
		try {
			Response deptResponse = iRaceMasterDao.getRaceByID(raceMasterDto);
			if (deptResponse.getListObject() != null && deptResponse.getListObject().size() > 0) {

				/*raceMasterDto
						.setUpdatedDate(CommonDateUtils.getStringDateByzone("Asia/Calcutta", "dd-M-yyyy HH:mm:ss"));*/
				iRaceMasterDao.updateRaceStatus(raceMasterDto);
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
	public Response getActiveRaceList() throws ApplicationException {
		try {
			return iRaceMasterDao.getActiveRaceList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);

	}

	@Override
	public Response getRaceCount(RaceMasterDto raceMasterDto) throws ApplicationException {
		try {
			return iRaceMasterDao.getCount(raceMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

}
