package com.param.global.unit.service;

import in.param.exception.ApplicationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;
import com.param.global.unit.dao.IFloorMasterDao;
import com.param.global.unit.dto.FloorMasterDto;
@Service
@SuppressWarnings({"rawtypes","unchecked"})
public class FloorMasterServiceImpl implements IFloorMasterService,ICommonConstants
{

	@Autowired
	IFloorMasterDao iFloorDao;
	
	@Override
	public Response addFloorMaster(FloorMasterDto floorMasterDto) throws ApplicationException {
		try {

			Response deptResponse = iFloorDao.getFloorByName(floorMasterDto);
			if (deptResponse.getListObject() != null && deptResponse.getListObject().size() > 0) {

				return new Response(ERROR, null, ALREADY_EXIST, null, null);

			} else {
				/*floorMasterDto
						.setCreatedDate(CommonDateUtils.getStringDateByzone("Asia/Calcutta", "dd-M-yyyy HH:mm:ss"));
				floorMasterDto
						.setUpdatedDate(CommonDateUtils.getStringDateByzone("Asia/Calcutta", "dd-M-yyyy HH:mm:ss"));
				*/
				iFloorDao.addFloorMaster(floorMasterDto);
				return new Response(SUCCESS, null, COMMON_SUCCESS, null, null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getFloorMasterList(FloorMasterDto floorMasterDto) throws ApplicationException {
		try {
			return iFloorDao.getFloorMasterList(floorMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response updateFloorMaster(FloorMasterDto floorMasterDto) throws ApplicationException {
		try {
			Response deptResponse = iFloorDao.getFloorByNameNotId(floorMasterDto);
			if (deptResponse.getListObject() != null && deptResponse.getListObject().size() > 0) {

				return new Response(ERROR, null, ALREADY_EXIST, null, null);

			} else {
				/*floorMasterDto
						.setUpdatedDate(CommonDateUtils.getStringDateByzone("Asia/Calcutta", "dd-M-yyyy HH:mm:ss"));*/
				iFloorDao.updateFloorMaster(floorMasterDto);
				return new Response(SUCCESS, null, COMMON_UPDATE, null, null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getFloorById(FloorMasterDto floorMasterDto) throws ApplicationException {
		try {
			return iFloorDao.getFloorByID(floorMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response updateStatusForFloor(FloorMasterDto floorMasterDto) throws ApplicationException {
		try {
			Response deptResponse = iFloorDao.getFloorByID(floorMasterDto);
			if (deptResponse.getListObject() != null && deptResponse.getListObject().size() > 0) {
				
				iFloorDao.updateFloorStatus(floorMasterDto);
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
	public Response getActiveFloorList() throws ApplicationException {
		try {
			return iFloorDao.getActiveFloorList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getFloorCount(FloorMasterDto floorMasterDto) throws ApplicationException {
		try {
			return iFloorDao.getCount(floorMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}


}
