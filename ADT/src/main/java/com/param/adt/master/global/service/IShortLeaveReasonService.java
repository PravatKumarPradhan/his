package com.param.adt.master.global.service;

import javax.transaction.Transactional;

import com.param.adt.common.Response;
import com.param.adt.master.global.dto.ShortLeaveReasonMasterDto;

import in.param.exception.ApplicationException;
@SuppressWarnings("rawtypes")
public interface IShortLeaveReasonService {

	@Transactional
	Response addShortLeaveReasonMaster(ShortLeaveReasonMasterDto shortLeaveReasonMasterDto) throws ApplicationException;

	@Transactional
	Response getShortLeaveReasonMasterList(ShortLeaveReasonMasterDto shortLeaveReasonMasterDto) throws ApplicationException;

	@Transactional
	Response updateShortLeaveReasonMaster(ShortLeaveReasonMasterDto shortLeaveReasonMasterDto) throws ApplicationException;

	@Transactional
	Response getShortLeaveReasonById(ShortLeaveReasonMasterDto shortLeaveReasonMasterDto) throws ApplicationException;

	@Transactional
	Response updateStatusForShortLeaveReason(ShortLeaveReasonMasterDto shortLeaveReasonMasterDto)throws ApplicationException;

	@Transactional
	Response getActiveShortLeaveReasonList(ShortLeaveReasonMasterDto shortLeaveReasonMasterDto) throws ApplicationException;

	@Transactional
	Response getShortLeaveCount(ShortLeaveReasonMasterDto shortLeaveReasonMasterDto) throws ApplicationException;

}
