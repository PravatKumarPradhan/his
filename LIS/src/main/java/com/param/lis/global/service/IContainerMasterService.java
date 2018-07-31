package com.param.lis.global.service;

import com.param.lis.global.common.Response;
import com.param.lis.global.dto.ContainerMasterDto;
import com.param.lis.global.dto.SampleMasterDto;

import in.param.exception.ApplicationException;

@SuppressWarnings("rawtypes")
public interface IContainerMasterService
{
	public Response addContainerMaster(ContainerMasterDto containerMasterDto) throws ApplicationException;

	public Response getContainerMasterById(Integer orgId, Integer ContainerId) throws ApplicationException;

	public Response updateContainerMaster(ContainerMasterDto containerMasterDto) throws ApplicationException;
	
	public Response ActivateInactivateContainerMaster(Integer orgId, Integer containerId, Character containerStatus) throws ApplicationException;

	public Response listContainerMaster(Integer orgId, Integer offset, Integer recordPerPage) throws ApplicationException;

	public Response getToTalContainerMasterRecord(Integer orgId) throws ApplicationException;

}
