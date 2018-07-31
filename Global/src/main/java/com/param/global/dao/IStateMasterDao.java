package com.param.global.dao;

import com.param.global.common.Response;
import com.param.global.dto.StateMasterDto;
import com.param.global.model.StateMaster;

import in.param.common.dao.IGenericDao;
import in.param.exception.ApplicationException;
@SuppressWarnings("rawtypes")
public interface IStateMasterDao extends IGenericDao<StateMaster, Integer>{

	
	Response getStateByName(StateMasterDto stateMasterDto)throws ApplicationException;

	Response addStateMaster(StateMasterDto stateMasterDto)throws ApplicationException;

	Response getStateMasterList(StateMasterDto stateMasterDto) throws ApplicationException;

	Response getStateByNameNotId(StateMasterDto stateMasterDto)throws ApplicationException;

	Response getStateById(StateMasterDto stateMasterDto) throws ApplicationException;

	Response updateStateStatus(StateMasterDto stateMasterDto)throws ApplicationException;

	Response getActiveStateList() throws ApplicationException;

	Response updateStateMaster(StateMasterDto stateMasterDto)throws ApplicationException;

	Response getCount(StateMasterDto stateMasterDto)throws ApplicationException;
	
	Response getStateByCountryId(StateMasterDto stateMasterDto) throws ApplicationException;

}
