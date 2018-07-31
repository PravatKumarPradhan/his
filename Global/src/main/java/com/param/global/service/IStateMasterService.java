package com.param.global.service;

import javax.transaction.Transactional;

import com.param.global.common.Response;
import com.param.global.dto.StateMasterDto;

import in.param.exception.ApplicationException;

@SuppressWarnings("rawtypes")
public interface IStateMasterService {

	@Transactional
	Response addStateMaster(StateMasterDto stateMasterDto)throws ApplicationException;

	@Transactional
	Response getStateMasterList(StateMasterDto stateMasterDto)throws ApplicationException;

	@Transactional
	Response updateStateMaster(StateMasterDto stateMasterDto)throws ApplicationException;

	@Transactional
	Response getStateById(StateMasterDto stateMasterDto)throws ApplicationException;

	@Transactional
	Response updateStatusForState(StateMasterDto stateMasterDto)throws ApplicationException;

	@Transactional
	Response getActiveStateList()throws ApplicationException;

	@Transactional
	Response getStateCount(StateMasterDto stateMasterDto) throws ApplicationException;
	
	@Transactional
	Response getStateByCountryId(StateMasterDto stateMasterDto) throws ApplicationException;

}
