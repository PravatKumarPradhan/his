package com.param.global.org.common.service;

import javax.transaction.Transactional;

import com.param.global.common.Response;
import com.param.global.org.common.dto.ReasonMasterDto;

import in.param.exception.ApplicationException;

@SuppressWarnings("rawtypes")
@Transactional
public interface IReasonMasterService {


	
	Response getReasonMasterById(int reasonId, int orgId) throws ApplicationException;

	Response saveReasonMaster(ReasonMasterDto reasonMasterDto) throws ApplicationException;

	Response getReasonMasterList(ReasonMasterDto reasonMasterDto) throws ApplicationException;

	Response updateReasonMaster(ReasonMasterDto reasonMasterDto) throws ApplicationException;

	Response updateReasonMasterStatus(ReasonMasterDto reasonMasterDto) throws ApplicationException;

	Response getReasonMasterCount(ReasonMasterDto reasonMasterDto) throws ApplicationException;

}
