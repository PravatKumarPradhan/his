package com.param.lis.global.service;

import in.param.exception.ApplicationException;

import com.param.lis.global.common.Response;
import com.param.lis.global.dto.IncubationPeriodMasterDto;
@SuppressWarnings("rawtypes")
public interface IIncubationPeriodMasterService {
	public Response addIncubationPeriodMaster(IncubationPeriodMasterDto incubationPeriodMasterDto)throws ApplicationException;
	public Response getIncubationPeriodMasterById(Integer orgId, Integer incubationPeriodId) throws ApplicationException;
	public Response updateIncubationPeriodMaster(IncubationPeriodMasterDto incubationPeriodMasterDto) throws ApplicationException;
	public Response ActivateInactivateIncubationPeriodMaster(Integer orgId, Integer incubationPeriodId, Character incubationPeriodStatus) throws ApplicationException;
	public Response listIncubationPeriodMaster(Integer orgId, Integer offset, Integer recordPerPage) throws ApplicationException;
	public Response getToTalIncubationPeriodMaster(Integer orgId) throws ApplicationException;
}
