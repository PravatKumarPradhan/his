package com.param.global.service;

import javax.transaction.Transactional;

import com.param.global.common.Response;
import com.param.global.dto.GenderMasterDto;

import in.param.exception.ApplicationException;
@SuppressWarnings("rawtypes")
public interface IGenderMasterService {

	
	@Transactional
	Response getGenderMasterList(GenderMasterDto genderMasterDto) throws ApplicationException;

	@Transactional
	Response addGenderMaster(GenderMasterDto genderMasterDto) throws ApplicationException;

	@Transactional
	Response getGenderById(GenderMasterDto genderMasterDto) throws ApplicationException;

	@Transactional
	Response updateGenderMaster(GenderMasterDto genderMasterDto) throws ApplicationException;
	
	@Transactional
	Response updateStatusForGender(GenderMasterDto genderMasterDto) throws ApplicationException;

	@Transactional
	Response getActiveGenderList() throws ApplicationException;

	@Transactional
	Response getActiveGenderList(GenderMasterDto genderMasterDto)throws ApplicationException;

}
