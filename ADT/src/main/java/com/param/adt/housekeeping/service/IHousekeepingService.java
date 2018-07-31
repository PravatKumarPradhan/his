package com.param.adt.housekeeping.service;

import javax.transaction.Transactional;

import com.param.adt.common.Response;
import com.param.adt.master.global.dto.BedMasterDto;

import in.param.exception.ApplicationException;
@SuppressWarnings("rawtypes")
public interface IHousekeepingService {

	@Transactional
	Response getBedsForHousekeepingList(BedMasterDto bedMasterDto)throws ApplicationException;

	@Transactional
	Response getActiveUserList(BedMasterDto bedMasterDto)throws ApplicationException;

	@Transactional
	Response assignUserForCleaning(BedMasterDto bedMasterDto) throws ApplicationException;

	@Transactional
	Response markAsCleaned(BedMasterDto bedMasterDto)throws ApplicationException;

	@Transactional
	Response getListOfVacantBedsForNursing(BedMasterDto bedMasterDto)throws ApplicationException;

	@Transactional
	Response acceptRequestForNursing(BedMasterDto bedMasterDto)throws ApplicationException;

	@Transactional
	Response rejectRequestForNursing(BedMasterDto bedMasterDto)throws ApplicationException;

	@Transactional
	Response searchBedByMultipleCriteriaForHousekeeping(BedMasterDto bedMasterDto)throws ApplicationException;

	@Transactional
	Response searchBedByMultipleCriteriaForNursing(BedMasterDto bedMasterDto)throws ApplicationException;

}
