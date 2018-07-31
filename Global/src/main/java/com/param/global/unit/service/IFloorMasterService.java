package com.param.global.unit.service;

import in.param.exception.ApplicationException;

import javax.transaction.Transactional;

import com.param.global.common.Response;
import com.param.global.unit.dto.FloorMasterDto;
@SuppressWarnings("rawtypes")
public interface IFloorMasterService {

	@Transactional
	Response addFloorMaster(FloorMasterDto floorMasterDto) throws ApplicationException;

	@Transactional
	Response getFloorMasterList(FloorMasterDto floorMasterDto) throws ApplicationException;

	@Transactional
	Response updateFloorMaster(FloorMasterDto floorMasterDto) throws ApplicationException;

	@Transactional
	Response getFloorById(FloorMasterDto floorMasterDto) throws ApplicationException;

	@Transactional
	Response updateStatusForFloor(FloorMasterDto floorMasterDto) throws ApplicationException;

	@Transactional
	Response getActiveFloorList() throws ApplicationException;

	@Transactional
	Response getFloorCount(FloorMasterDto floorMasterDto) throws ApplicationException;
	
}
