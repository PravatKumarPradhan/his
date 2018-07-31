package com.param.global.org.common.dao;

import com.param.global.common.Response;
import com.param.global.org.common.dto.ReasonTypeMasterDto;

import in.param.exception.ApplicationException;

@SuppressWarnings("rawtypes")
public interface IReasonTypeMasterDao {

	
	Response getReasonTypeMasterById(int reasonTypeId, int orgId) throws ApplicationException;

	Response getReasonTypeMasterList(ReasonTypeMasterDto reasonTypeMasterDto) throws ApplicationException;

	Response updateReasonTypeMaster(ReasonTypeMasterDto reasonTypeMasterDto)  throws ApplicationException;

	Response updateReasonTypeMasterStatus(ReasonTypeMasterDto reasonTypeMasterDto)  throws ApplicationException;

	Response getReasonTypeMasterCount(ReasonTypeMasterDto reasonTypeMasterDto) throws ApplicationException;

	Response saveReasonTypeMaster(ReasonTypeMasterDto reasonTypeMasterDto) throws ApplicationException;

	Response getReasonTypeMasterByName(ReasonTypeMasterDto reasonTypeMasterDto) throws ApplicationException;

	Response getReasonTypeMasterByNameNotById(ReasonTypeMasterDto reasonTypeMasterDto) throws ApplicationException;

}
