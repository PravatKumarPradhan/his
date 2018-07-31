package com.param.global.org.common.dao;

import com.param.global.common.Response;
import com.param.global.org.common.dto.ReasonMasterDto;

import in.param.exception.ApplicationException;

@SuppressWarnings("rawtypes")
public interface IReasonMasterDao {

	Response getReasonMasterById(int reasonId, int orgId) throws ApplicationException;

	Response saveReasonMaster(ReasonMasterDto reasonMasterDto) throws ApplicationException;

	Response getReasonMasterList(ReasonMasterDto reasonMasterDto) throws ApplicationException;

	Response updateReasonMaster(ReasonMasterDto reasonMasterDto) throws ApplicationException;

	Response updateReasonMasterStatus(ReasonMasterDto reasonMasterDto) throws ApplicationException;

	Response getReasonMasterCount(ReasonMasterDto reasonMasterDto) throws ApplicationException;

	Response getReasonMasterByName(ReasonMasterDto reasonMasterDto) throws ApplicationException;

	Response getReasonMasterByNameNotById(ReasonMasterDto reasonMasterDto) throws ApplicationException;
}
