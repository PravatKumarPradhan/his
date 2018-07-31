package com.param.adt.master.global.service;

import javax.transaction.Transactional;

import com.param.adt.common.Response;
import com.param.adt.master.global.dto.ReligionMasterDto;

import in.param.exception.ApplicationException;
@SuppressWarnings("rawtypes")
public interface IReligionMasterService {

	@Transactional
	Response addReligionMaster(ReligionMasterDto religionMasterDto) throws ApplicationException;

	@Transactional
	Response getReligionMasterList(ReligionMasterDto religionMasterDto)throws ApplicationException;

	@Transactional
	Response updateReligionMaster(ReligionMasterDto religionMasterDto)throws ApplicationException;

	@Transactional
	Response getReligionById(ReligionMasterDto religionMasterDto)throws ApplicationException;

	@Transactional
	Response updateStatusForReligion(ReligionMasterDto religionMasterDto)throws ApplicationException;

	@Transactional
	Response getActiveReligionList() throws ApplicationException;

	@Transactional
	Response getReligionCount(ReligionMasterDto religionMasterDto)throws ApplicationException;
}
