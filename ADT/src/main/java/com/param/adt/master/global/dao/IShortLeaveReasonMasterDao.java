package com.param.adt.master.global.dao;

import com.param.adt.common.Response;
import com.param.adt.master.global.dto.ShortLeaveReasonMasterDto;
import com.param.adt.master.global.model.ShortLeaveReasonMaster;

import in.param.common.dao.IGenericDao;
import in.param.exception.ApplicationException;
@SuppressWarnings("rawtypes")
public interface IShortLeaveReasonMasterDao extends IGenericDao<ShortLeaveReasonMaster, Integer>{
	
	Response getShortLeaveReasonByName(ShortLeaveReasonMasterDto shortLeaveReasonMasterDto) throws ApplicationException;

	Response addShortLeaveReasonMaster(ShortLeaveReasonMasterDto shortLeaveReasonMasterDto) throws ApplicationException;

	Response getShortLeaveReasonByNameNotId(ShortLeaveReasonMasterDto shortLeaveReasonMasterDto) throws ApplicationException;

	Response updateShortLeaveReasonMaster(ShortLeaveReasonMasterDto shortLeaveReasonMasterDto)throws ApplicationException;

	Response getShortLeaveReasonById(ShortLeaveReasonMasterDto shortLeaveReasonMasterDto) throws ApplicationException;

	Response updateShortLeaveReasonStatus(ShortLeaveReasonMasterDto shortLeaveReasonMasterDto) throws ApplicationException;

	Response getActiveShortLeaveReasonList(ShortLeaveReasonMasterDto shortLeaveReasonMasterDto) throws ApplicationException;

	Response getShortLeaveReasonMasterList(ShortLeaveReasonMasterDto shortLeaveReasonMasterDto) throws ApplicationException;

	Response getCount(ShortLeaveReasonMasterDto shortLeaveReasonMasterDto)throws ApplicationException;

}
