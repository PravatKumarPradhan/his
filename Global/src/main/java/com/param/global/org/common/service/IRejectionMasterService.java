package com.param.global.org.common.service;

import javax.transaction.Transactional;

import com.param.global.common.Response;
import com.param.global.org.common.dto.RejectionMasterDto;
import in.param.exception.ApplicationException;

@Transactional
@SuppressWarnings("rawtypes")
public interface IRejectionMasterService {

	
	Response saveRejectionMaster(RejectionMasterDto rejectionMasterDto) throws ApplicationException;

	Response getRejectionMasterById(int rejectId, int orgId) throws ApplicationException;

	Response getRejectionMasterList(RejectionMasterDto rejectionMasterDto) throws ApplicationException;

	Response updateRejectionMaster(RejectionMasterDto rejectionMasterDto) throws ApplicationException;

	Response updateRejectionMasterStatus(RejectionMasterDto rejectionMasterDto) throws ApplicationException;

	Response getRejectionMasterCount(RejectionMasterDto rejectionMasterDto) throws ApplicationException;

}
