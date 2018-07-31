package com.param.adt.master.unit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.param.adt.master.unit.dao.IRoomMasterDao;
import com.param.adt.master.unit.dto.RoomMasterDto;
import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;

import in.param.exception.ApplicationException;

@Service
@SuppressWarnings({ "rawtypes", "unchecked" })
public class RoomMasterServiceImpl implements IRoomMasterService, ICommonConstants {

	@Autowired
	IRoomMasterDao iRoomMasterDao;

	@Override
	public Response saveRoomMasterMaster(RoomMasterDto roomMasterDto) throws ApplicationException {
		Response response = iRoomMasterDao.getRoomMasterByName(roomMasterDto);

		try {
			if (response.getListObject().size() > 0 && response.getListObject() != null) {
				return new Response(ERROR, null, ALREADY_EXIST, null, null);
			} else {
				return iRoomMasterDao.saveRoomMasterMaster(roomMasterDto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response getRoomMasterById(int roomId, int orgId, int unitId) throws ApplicationException {
		try {
			return iRoomMasterDao.getRoomMasterById(roomId, orgId, unitId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response getRoomMasterList(RoomMasterDto roomMasterDto) throws ApplicationException {
		try {
			return iRoomMasterDao.getRoomMasterList(roomMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response updateRoomMaster(RoomMasterDto roomMasterDto) throws ApplicationException {
		Response response = iRoomMasterDao.getRoomMasterByNameNotById(roomMasterDto);

		try {
			if (response.getListObject().size() > 0 && response.getListObject() != null) {
				return new Response(ERROR, null, ALREADY_EXIST, null, null);
			} else {
				return iRoomMasterDao.updateRoomMaster(roomMasterDto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response updateRoomMasterStatus(RoomMasterDto roomMasterDto) throws ApplicationException {
		try {
			return iRoomMasterDao.updateRoomMasterStatus(roomMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response getRoomMasterCount(RoomMasterDto roomMasterDto) throws ApplicationException {
		try {
			return iRoomMasterDao.getRoomMasterCount(roomMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

}
