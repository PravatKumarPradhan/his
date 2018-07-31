package com.param.adt.master.global.dao;

import com.param.adt.common.Response;
import com.param.adt.master.global.dto.OccupancyUnitMasterDto;
import com.param.adt.master.global.model.OccupancyUnitMaster;

import in.param.common.dao.IGenericDao;
import in.param.exception.ApplicationException;
@SuppressWarnings("rawtypes")
public interface IOccupancyUnitMasterDao extends IGenericDao<OccupancyUnitMaster, Integer>{

	
	Response getOccupancyUnitByName(OccupancyUnitMasterDto occupancyUnitMasterDto) throws ApplicationException;

	Response addOccupancyUnitTypeMaster(OccupancyUnitMasterDto occupancyUnitMasterDto) throws ApplicationException;

	Response getOccupancyUnitByNameNotId(OccupancyUnitMasterDto occupancyUnitMasterDto) throws ApplicationException;

	Response updateOccupancyUnitMaster(OccupancyUnitMasterDto occupancyUnitMasterDto) throws ApplicationException;

	Response getOccupancyUnitById(OccupancyUnitMasterDto occupancyUnitMasterDto) throws ApplicationException;

	Response getOccupancyUnitMasterList(OccupancyUnitMasterDto occupancyUnitMasterDto) throws ApplicationException;

	Response getActiveOccupancyUnitList() throws ApplicationException;

	Response updateStatusForOccupancyUnit(OccupancyUnitMasterDto occupancyUnitMasterDto) throws ApplicationException;

	Response getCount(OccupancyUnitMasterDto occupancyUnitMasterDto);

}
