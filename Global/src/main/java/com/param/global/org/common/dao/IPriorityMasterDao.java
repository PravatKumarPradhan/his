package com.param.global.org.common.dao;

import com.param.global.common.Response;
import com.param.global.org.common.dto.PriorityMasterDto;

import in.param.exception.ApplicationException;

@SuppressWarnings("rawtypes")
public interface IPriorityMasterDao {

	Response savePriorityMasterMaster(PriorityMasterDto priorityMasterDto) throws ApplicationException;

	Response getPriorityMasterById(int priorityId, int orgId) throws ApplicationException;

	Response getPriorityMasterList(PriorityMasterDto priorityMasterDto) throws ApplicationException;

	Response updatePriorityMaster(PriorityMasterDto priorityMasterDto) throws ApplicationException;

	Response updatePriorityMasterStatus(PriorityMasterDto priorityMasterDto) throws ApplicationException;

	Response getPriorityMasterCount(PriorityMasterDto priorityMasterDto) throws ApplicationException;

	Response getPriorityMasterByName(PriorityMasterDto priorityMasterDto) throws ApplicationException;

	Response getPriorityMasterByNameNotById(PriorityMasterDto priorityMasterDto) throws ApplicationException;
}
