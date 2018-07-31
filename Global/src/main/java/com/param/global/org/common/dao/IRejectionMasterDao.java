package com.param.global.org.common.dao;

import com.param.global.common.Response;
import com.param.global.org.common.dto.RejectionMasterDto;

import in.param.exception.ApplicationException;

@SuppressWarnings("rawtypes")
public interface IRejectionMasterDao {

	Response saveRejectionMaster(RejectionMasterDto rejectionMasterDto) throws ApplicationException;

	Response getRejectionMasterById(int rejectId, int orgId) throws ApplicationException;

	Response getRejectionMasterList(RejectionMasterDto rejectionMasterDto) throws ApplicationException;

	Response updateRejectionMaster(RejectionMasterDto rejectionMasterDto) throws ApplicationException;

	Response updateRejectionMasterStatus(RejectionMasterDto rejectionMasterDto) throws ApplicationException;

	Response getRejectionMasterCount(RejectionMasterDto rejectionMasterDto) throws ApplicationException;

	Response getRejectionMasterByName(RejectionMasterDto rejectionMasterDto) throws ApplicationException;

	Response getRejectionMasterByNameNotById(RejectionMasterDto rejectionMasterDto) throws ApplicationException;
}
