package com.param.lis.global.service;

import in.param.exception.ApplicationException;

import com.param.lis.global.common.Response;
import com.param.lis.global.dto.ColonyMasterDto;

@SuppressWarnings("rawtypes")
public interface IColonyMasterService {
	public Response addColonyMaster(ColonyMasterDto colonyMasterDto)throws ApplicationException;
	public Response updateColonyMaster(ColonyMasterDto colonyMasterDto) throws ApplicationException;
	public Response ActivateInactivateColonyMaster(Integer orgId, Integer colonyId, Character colonyStatus) throws ApplicationException;
	public Response listColonyMaster(Integer orgId, Integer offset, Integer recordPerPage) throws ApplicationException;
	public Response getToTalColonyMaster(Integer orgId) throws ApplicationException;
	public Response getColonyMasterById(Integer orgId, Integer reagentId)
			throws ApplicationException;
}
