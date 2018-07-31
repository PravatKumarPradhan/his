package com.param.adt.master.unit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.param.adt.master.unit.dto.RoomMasterDto;
import com.param.adt.master.unit.service.IRoomMasterService;
import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;

@RestController
@RequestMapping("api/adt")
@SuppressWarnings({ "rawtypes", "unchecked" })

public class RoomMasterController implements ICommonConstants {

	@Autowired
	private IRoomMasterService iRoomMasterService;

	
	@RequestMapping(value = "/saveRoomMaster", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response saveRoomMaster(@RequestBody RoomMasterDto roomMasterDto) {
		try {
			return iRoomMasterService.saveRoomMasterMaster(roomMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@RequestMapping(value = "/getRoomMasterById/{roomId}/{orgId}/{unitId}", method = RequestMethod.GET)
	public @ResponseBody Response getRoomMasterById(@PathVariable("roomId") int roomId,
			@PathVariable("orgId") int orgId, @PathVariable("unitId") int unitId) {
		try {
			return iRoomMasterService.getRoomMasterById(roomId, orgId, unitId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@RequestMapping(value = "/getRoomMasterList", method = RequestMethod.POST)
	public @ResponseBody Response getRoomMasterList(@RequestBody RoomMasterDto roomMasterDto) {
		try {
			return iRoomMasterService.getRoomMasterList(roomMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@RequestMapping(value = "/updateRoomMaster", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response updateRoomMaster(@RequestBody RoomMasterDto roomMasterDto) {
		try {
			return iRoomMasterService.updateRoomMaster(roomMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@RequestMapping(value = "/updateRoomMasterStatus", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response updateRoomMasterStatus(@RequestBody RoomMasterDto roomMasterDto) {
		try {
			return iRoomMasterService.updateRoomMasterStatus(roomMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@RequestMapping(value = "/getRoomMasterCount", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getRoomMasterCount(@RequestBody RoomMasterDto roomMasterDto) {
		try {
			return iRoomMasterService.getRoomMasterCount(roomMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}
}
