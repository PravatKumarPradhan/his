package com.param.adt.master.global.dao;

import com.param.adt.common.Response;
import com.param.adt.master.global.dto.RaceMasterDto;
import com.param.adt.master.global.model.RaceMaster;

import in.param.common.dao.IGenericDao;
import in.param.exception.ApplicationException;
@SuppressWarnings("rawtypes")
public interface IRaceMasterDao extends IGenericDao<RaceMaster, Integer>{

	Response getRaceByName(RaceMasterDto raceMasterDto) throws ApplicationException;

	Response addRaceTypeMaster(RaceMasterDto raceMasterDto)throws ApplicationException;

	Response getRaceMasterList(RaceMasterDto raceMasterDto)throws ApplicationException;

	Response getRaceByNameNotId(RaceMasterDto raceMasterDto)throws ApplicationException;

	Response updateRaceMaster(RaceMasterDto raceMasterDto)throws ApplicationException;

	Response getRaceByID(RaceMasterDto raceMasterDto)throws ApplicationException;

	Response updateRaceStatus(RaceMasterDto raceMasterDto)throws ApplicationException;

	Response getActiveRaceList() throws ApplicationException;

	Response getCount(RaceMasterDto raceMasterDto) throws ApplicationException;

}
