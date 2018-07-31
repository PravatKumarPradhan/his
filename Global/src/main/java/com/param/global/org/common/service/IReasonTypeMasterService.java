package com.param.global.org.common.service;

import javax.transaction.Transactional;

import com.param.global.common.Response;
import com.param.global.org.common.dto.ReasonTypeMasterDto;

import in.param.exception.ApplicationException;
@SuppressWarnings("rawtypes")
@Transactional
public interface IReasonTypeMasterService {

	Response getReasonTypeMasterById(int reasonTypeId, int orgId) throws ApplicationException;

	Response getReasonTypeMasterList(ReasonTypeMasterDto reasonTypeMasterDto) throws ApplicationException;

	Response updateReasonTypeMaster(ReasonTypeMasterDto reasonTypeMasterDto) throws ApplicationException;

	Response updateReasonTypeMasterStatus(ReasonTypeMasterDto reasonTypeMasterDto) throws ApplicationException;

	Response getReasonTypeMasterCount(ReasonTypeMasterDto reasonTypeMasterDto) throws ApplicationException;

	Response saveReasonTypeMaster(ReasonTypeMasterDto reasonTypeMasterDto) throws ApplicationException;

}
