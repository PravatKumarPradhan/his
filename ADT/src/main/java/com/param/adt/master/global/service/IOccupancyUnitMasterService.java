package com.param.adt.master.global.service;

import javax.transaction.Transactional;

import com.param.adt.common.Response;
import com.param.adt.master.global.dto.OccupancyUnitMasterDto;

import in.param.exception.ApplicationException;
@SuppressWarnings("rawtypes")
public interface IOccupancyUnitMasterService 
{

	@Transactional
	Response addOccupancyUnitMaster(OccupancyUnitMasterDto occupancyUnitMasterDto) throws ApplicationException;

	@Transactional
	Response getOccupancyUnitMasterList(OccupancyUnitMasterDto occupancyUnitMasterDto) throws ApplicationException;

	@Transactional
	Response updateOccupancyUnitMaster(OccupancyUnitMasterDto occupancyUnitMasterDto) throws ApplicationException;

	@Transactional
	Response getOccupancyUnitById(OccupancyUnitMasterDto occupancyUnitMasterDto) throws ApplicationException;

	@Transactional
	Response updateStatusForOccupancyUnit(OccupancyUnitMasterDto occupancyUnitMasterDto) throws ApplicationException;

	@Transactional
	Response getActiveOccupancyUnitList() throws ApplicationException;

	@Transactional
	Response getOccupancyUnitCount(OccupancyUnitMasterDto occupancyUnitMasterDto);
	
}
