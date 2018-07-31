package com.param.adt.master.global.service;

import javax.transaction.Transactional;

import com.param.adt.common.Response;
import com.param.adt.master.global.dto.OccupationMasterDto;

import in.param.exception.ApplicationException;
@SuppressWarnings("rawtypes")
public interface IOccupationMasterService {

	
	@Transactional
	Response getOccupationMasterList(OccupationMasterDto occupationMasterDto) throws ApplicationException;

	@Transactional
	Response addOccupationMaster(OccupationMasterDto occupationMasterDto) throws ApplicationException;

	@Transactional
	Response getOccupationById(OccupationMasterDto occupationMasterDto) throws ApplicationException;

	@Transactional
	Response updateOccupationMaster(OccupationMasterDto occupationMasterDto) throws ApplicationException;

	@Transactional
	Response updateStatusForOccupation(OccupationMasterDto occupationMasterDto) throws ApplicationException;

	@Transactional
	Response getActiveMaritalStatusList() throws ApplicationException;

	@Transactional
	Response getOccupationCount(OccupationMasterDto occupationMasterDto)throws ApplicationException;

}
