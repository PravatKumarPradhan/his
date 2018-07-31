package com.param.adt.master.global.service;

import javax.transaction.Transactional;

import com.param.adt.common.Response;
import com.param.adt.master.global.dto.RaceMasterDto;

import in.param.exception.ApplicationException;


@SuppressWarnings("rawtypes")
public interface IRaceMasterService {
	
	@Transactional
	Response addRaceMaster(RaceMasterDto raceMasterDto) throws ApplicationException;
	
	@Transactional
	Response getRaceMasterList(RaceMasterDto raceMasterDto) throws ApplicationException;

	@Transactional
	Response updateRaceMaster(RaceMasterDto raceMasterDto)throws ApplicationException;

	@Transactional
	Response getRaceById(RaceMasterDto raceMasterDto)throws ApplicationException;

	@Transactional
	Response getActiveRaceList() throws ApplicationException;
	
	@Transactional
	Response updateStatusForRace(RaceMasterDto raceMasterDto)throws ApplicationException;

	@Transactional
	Response getRaceCount(RaceMasterDto raceMasterDto) throws ApplicationException;
	
}
