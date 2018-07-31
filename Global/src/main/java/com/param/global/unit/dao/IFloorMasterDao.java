package com.param.global.unit.dao;

import in.param.common.dao.IGenericDao;
import in.param.exception.ApplicationException;

import com.param.adt.master.unit.model.FloorMaster;
import com.param.global.common.Response;
import com.param.global.unit.dto.FloorMasterDto;
@SuppressWarnings("rawtypes")
public interface IFloorMasterDao extends IGenericDao<FloorMaster, Integer>{

	
	Response addFloorMaster(FloorMasterDto floorMasterDto) throws ApplicationException;

	Response getFloorByName(FloorMasterDto floorMasterDto) throws ApplicationException;

	Response getFloorMasterList(FloorMasterDto floorMasterDto) throws ApplicationException;

	Response getFloorByNameNotId(FloorMasterDto floorMasterDto) throws ApplicationException;

	Response updateFloorMaster(FloorMasterDto floorMasterDto) throws ApplicationException;

	Response getFloorByID(FloorMasterDto floorMasterDto) throws ApplicationException;

	Response updateFloorStatus(FloorMasterDto floorMasterDto) throws ApplicationException;

	Response getActiveFloorList() throws ApplicationException;

	Response getCount(FloorMasterDto floorMasterDto) throws ApplicationException;
	
	

}
