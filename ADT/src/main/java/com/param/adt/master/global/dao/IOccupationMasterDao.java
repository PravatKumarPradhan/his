package com.param.adt.master.global.dao;

import com.param.adt.common.Response;
import com.param.adt.master.global.dto.OccupationMasterDto;

import in.param.exception.ApplicationException;
@SuppressWarnings("rawtypes")
public interface IOccupationMasterDao {

	
	Response getOccupationMasterList(OccupationMasterDto occupationMasterDto)  throws ApplicationException;

	Response getOccupationByName(OccupationMasterDto occupationMasterDto) throws ApplicationException;

	Response addOccupationMaster(OccupationMasterDto occupationMasterDto) throws ApplicationException;

	Response getOccupationByID(OccupationMasterDto occupationMasterDto) throws ApplicationException;

	Response getOccupationByNameNotId(OccupationMasterDto occupationMasterDto)  throws ApplicationException;

	Response updateOccupationMaster(OccupationMasterDto occupationMasterDto) throws ApplicationException;

	Response updateOccupationStatus(OccupationMasterDto occupationMasterDto) throws ApplicationException;

	Response getActiveOccupationList() throws ApplicationException;

	Response getCount(OccupationMasterDto occupationMasterDto)throws ApplicationException;

}
