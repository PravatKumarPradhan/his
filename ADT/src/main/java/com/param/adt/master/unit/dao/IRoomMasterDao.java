package com.param.adt.master.unit.dao;

import com.param.adt.master.unit.dto.RoomMasterDto;
import com.param.global.common.Response;

import in.param.exception.ApplicationException;

@SuppressWarnings("rawtypes")
public interface IRoomMasterDao {

	Response saveRoomMasterMaster(RoomMasterDto roomMasterDto) throws ApplicationException;

	Response getRoomMasterById(int roomId, int orgId, int unitId) throws ApplicationException;

	Response getRoomMasterList(RoomMasterDto roomMasterDto) throws ApplicationException;

	Response updateRoomMaster(RoomMasterDto roomMasterDto) throws ApplicationException;

	Response updateRoomMasterStatus(RoomMasterDto roomMasterDto) throws ApplicationException;

	Response getRoomMasterCount(RoomMasterDto roomMasterDto) throws ApplicationException;

	Response getRoomMasterByName(RoomMasterDto roomMasterDto) throws ApplicationException;

	Response getRoomMasterByNameNotById(RoomMasterDto roomMasterDto) throws ApplicationException;
}
