package com.param.global.org.common.service;
import javax.transaction.Transactional;

import com.param.global.common.Response;
import com.param.global.org.common.dto.PriorityMasterDto;

import in.param.exception.ApplicationException;

@SuppressWarnings("rawtypes")
@Transactional
public interface IPriorityMasterService {

	
	Response savePriorityMasterMaster(PriorityMasterDto priorityMasterDto) throws ApplicationException;

	Response getPriorityMasterById(int priorityId, int orgId) throws ApplicationException;

	Response getPriorityMasterList(PriorityMasterDto priorityMasterDto) throws ApplicationException;

	Response updatePriorityMaster(PriorityMasterDto priorityMasterDto) throws ApplicationException;

	Response updatePriorityMasterStatus(PriorityMasterDto priorityMasterDto) throws ApplicationException;

	Response getPriorityMasterCount(PriorityMasterDto priorityMasterDto) throws ApplicationException;

}
