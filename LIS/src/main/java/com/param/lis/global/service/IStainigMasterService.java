package com.param.lis.global.service;

import in.param.exception.ApplicationException;

import com.param.lis.global.common.Response;
import com.param.lis.global.dto.StainigMasterDto;

@SuppressWarnings("rawtypes")
public interface IStainigMasterService {
	
	public Response addStainigMaster(StainigMasterDto organismMasterDto)throws ApplicationException;
	public Response updateStainigMaster(StainigMasterDto organismMasterDto) throws ApplicationException;
	public Response ActivateInactivateStainigMaster(Integer orgId, Integer  organismId, Character  organismStatus) throws ApplicationException;
	public Response listStainigMaster(Integer orgId, Integer offset, Integer recordPerPage) throws ApplicationException;
	public Response getToTalStainigMaster(Integer orgId) throws ApplicationException;
	public Response getStainigMasterMasterById(Integer orgId, Integer  organismpId)
			throws ApplicationException;
}
