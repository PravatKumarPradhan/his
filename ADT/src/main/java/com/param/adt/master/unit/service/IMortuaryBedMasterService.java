package com.param.adt.master.unit.service;

import javax.transaction.Transactional;

import com.param.adt.common.Response;
import com.param.adt.master.unit.dto.MortuaryBedMasterDto;

import in.param.exception.ApplicationException;
@SuppressWarnings("rawtypes")
public interface IMortuaryBedMasterService {

	@Transactional
	Response saveMortuaryBeds(MortuaryBedMasterDto mortuaryBedMasterDto)throws ApplicationException;

	@Transactional
	Response getMortuaryBedList(MortuaryBedMasterDto mortuaryBedMasterDto)throws ApplicationException;

	@Transactional
	Response updateStatusForMortuaryBed(MortuaryBedMasterDto mortuaryBedMasterDto)throws ApplicationException;

	@Transactional
	Response getActiveMortuaryBeds(MortuaryBedMasterDto mortuaryBedMasterDto)throws ApplicationException;

	@Transactional
	Response getMortuaryBedCount(MortuaryBedMasterDto mortuaryBedMasterDto)throws ApplicationException;

	@Transactional
	Response updateMortuaryBedMaster(MortuaryBedMasterDto mortuaryBedMasterDto) throws ApplicationException;

	@Transactional
	Response getMortuaryBedListById(MortuaryBedMasterDto mortuaryBedMasterDto) throws ApplicationException;

	@Transactional
	Response getMorturyBedListByStatusId(MortuaryBedMasterDto mortuaryBedMasterDto)throws ApplicationException;

	@Transactional
	Response saveMortuaryBedLogStatus(MortuaryBedMasterDto mortuaryBedMasterDto)throws ApplicationException;

	@Transactional
	Response allotMortuaryBed(MortuaryBedMasterDto mortuaryBedMasterDto)throws ApplicationException;


}
