package com.param.adt.master.global.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.param.adt.common.ICommonConstants;
import com.param.adt.common.Response;
import com.param.adt.master.global.dao.IOccupancyUnitMasterDao;
import com.param.adt.master.global.dto.OccupancyUnitMasterDto;

import in.param.exception.ApplicationException;

@Service
@SuppressWarnings({ "rawtypes", "unchecked" })
public class OccupancyUnitMasterServiceImpl implements IOccupancyUnitMasterService, ICommonConstants {

	@Autowired
	IOccupancyUnitMasterDao iOccupancyUnitMasterDao;

	@Override
	public Response addOccupancyUnitMaster(OccupancyUnitMasterDto occupancyUnitMasterDto) throws ApplicationException {
		try {
			Response deptResponse = iOccupancyUnitMasterDao.getOccupancyUnitByName(occupancyUnitMasterDto);
			if (deptResponse.getListObject() != null && deptResponse.getListObject().size() > 0) {

				return new Response(ERROR, null, ALREADY_EXIST, null, null);

			} else {

				/*occupancyUnitMasterDto
						.setCreatedDate(CommonDateUtils.getStringDateByzone("Asia/Calcutta", "dd-M-yyy HH:mm:ss"));
				occupancyUnitMasterDto
						.setUpdatedDate(CommonDateUtils.getStringDateByzone("Asia/Calcutta", "dd-M-yyy HH:mm:ss"));*/
				iOccupancyUnitMasterDao.addOccupancyUnitTypeMaster(occupancyUnitMasterDto);
				return new Response(SUCCESS, null, COMMON_SUCCESS, null, null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getOccupancyUnitMasterList(OccupancyUnitMasterDto occupancyUnitMasterDto) throws ApplicationException {
		try {
			return iOccupancyUnitMasterDao.getOccupancyUnitMasterList(occupancyUnitMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response updateOccupancyUnitMaster(OccupancyUnitMasterDto occupancyUnitMasterDto)
			throws ApplicationException {
		try {
			Response deptResponse = iOccupancyUnitMasterDao.getOccupancyUnitByNameNotId(occupancyUnitMasterDto);
			if (deptResponse.getListObject() != null && deptResponse.getListObject().size() > 0) {
				return new Response(ERROR, null, ALREADY_EXIST, null, null);

			} else {

				/*occupancyUnitMasterDto
						.setUpdatedDate(CommonDateUtils.getStringDateByzone("Asia/Calcutta", "dd-M-yyy HH:mm:ss"));*/

				iOccupancyUnitMasterDao.updateOccupancyUnitMaster(occupancyUnitMasterDto);
				return new Response(SUCCESS, null, COMMON_UPDATE, null, null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getOccupancyUnitById(OccupancyUnitMasterDto occupancyUnitMasterDto) throws ApplicationException {
		try {
			return iOccupancyUnitMasterDao.getOccupancyUnitById(occupancyUnitMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response updateStatusForOccupancyUnit(OccupancyUnitMasterDto occupancyUnitMasterDto)
			throws ApplicationException {
		try {
			Response deptResponse = iOccupancyUnitMasterDao.getOccupancyUnitById(occupancyUnitMasterDto);
			if (deptResponse.getListObject() != null && deptResponse.getListObject().size() > 0) {
				/*occupancyUnitMasterDto
						.setUpdatedDate(CommonDateUtils.getStringDateByzone("Asia/Calcutta", "dd-M-yyy HH:mm:ss"));*/

				iOccupancyUnitMasterDao.updateStatusForOccupancyUnit(occupancyUnitMasterDto);
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
	public Response getActiveOccupancyUnitList() throws ApplicationException {
		try {
			return iOccupancyUnitMasterDao.getActiveOccupancyUnitList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getOccupancyUnitCount(OccupancyUnitMasterDto occupancyUnitMasterDto) {
		try {
			return iOccupancyUnitMasterDao.getCount(occupancyUnitMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);

	}

}
